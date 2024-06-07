import csv
import json
import os.path
import random
import re

import torch.nn
from sklearn.model_selection import train_test_split
from torch import optim, nn
from torch.utils.data import Dataset, DataLoader
from semantic_model import *

from preprocess import train_transforms, eval_transforms
from resnet50 import ResNet50

device = "cuda" if torch.cuda.is_available() else "cpu"
batch_size = 32  # 训练批大小
epochs = 1000  # 最大训练次数
# image_model = ResNet50(channels=4).to(device)  # 图像模型：ResNet50，接收4通道图片作为输入
image_model = torch.load(r"resnet50.pth", map_location=torch.device(device))
text_model = sentence_transformers.SentenceTransformer('all-MiniLM-L6-v2').to(device)  # 文本模型：SentenceTransformer
label_path = os.path.join("datasets", "label.csv")  # 图标CSV文件

print("Load sentences...")
exist_set = set()  # 保存所有存在的图标的文件名
with open(label_path, encoding="utf8") as file:
    sentence_set = set()  # 保存所有标签
    reader = csv.reader(file)
    for row in reader:  # 每条记录
        if len(row[1].strip()) == 0:  # row[1]保存图标标签
            continue
        row0 = row[0]
        if not os.path.exists(os.path.join("datasets2", "data", row0)):  # 如果图标不存在，跳过
            continue
        # 保存图标文件名和相应标签
        exist_set.add(row0)
        sentence_set.add(row[1].lower())

label_list = list(sentence_set)  # 用列表保存标签

print("Calculate word encodings...")
embeddings_list = text_model.encode(label_list, convert_to_tensor=True, device=device)  # 这里用文本模型直接拿到标签的编码
embeddings_dict = dict(zip(label_list, embeddings_list))  # 标签 -> 编码 映射表

# 以下代码加载数据集，并将其分成训练集、验证集与测试集
print("Load dataset...")
with open(label_path, encoding="utf8") as file:
    reader = csv.reader(file)
    icons = []  # 图标路径
    labels = []  # 标签编码
    labels_word = []  # 标签
    labels_inv = []  # 负采样的标签编码
    labels_inv_word = []  # 负采样的标签
    for row in reader:
        if len(row[1].strip()) == 0:
            continue
        row0 = row[0]
        if row0 not in exist_set:  # 图标不存在，跳过
            continue
        icons.append(row0)
        labels.append(embeddings_dict[row[1].lower()])
        labels_word.append(row[1].lower())
        inv = random.choice(label_list)  # 从标签编码集合中，随机选取一个标签编码，作为负采样的标签编码
        while embeddings_dict[row[1].lower()] @ embeddings_dict[inv] > 0.1:  # 保证标签与负采样的标签相似度足够低
            inv = random.choice(label_list)
        labels_inv.append(embeddings_dict[inv])
        labels_inv_word.append(inv)

# 按照8:2的比例划分为训练集与验证集
train_icons, valid_icons, train_labels, valid_labels, train_labels_word, valid_labels_word, \
train_labels_inv, valid_labels_inv, train_labels_inv_word, valid_labels_inv_word \
    = train_test_split(icons, labels, labels_word, labels_inv, labels_inv_word, test_size=0.2)
# 再将验证集以1:1的比例划分为验证集与测试集，这样训练集：验证集：测试集=8:1:1
valid_icons, test_icons, valid_labels, test_labels, valid_labels_word, test_labels_word,\
valid_labels_inv, test_labels_inv, valid_labels_inv_word, test_labels_inv_word \
    = train_test_split(valid_icons, valid_labels, valid_labels_word, valid_labels_inv, valid_labels_inv_word, test_size=0.5)
print("Size of training set：", len(train_icons))
print("Size of validation set：", len(valid_icons))
print("Size of test set：", len(test_icons))
# 保存训练集、验证集与测试集
with open(os.path.join("datasets2", "datasets.json"), "w") as file:
    json.dump(((train_icons, train_labels_word, train_labels_inv_word),
               (valid_icons, valid_labels_word, valid_labels_inv_word),
               (test_icons, test_labels_word, test_labels_inv_word)), file)

# 删除后面不需要的变量，节省内存
del exist_set, sentence_set, label_list, embeddings_list, embeddings_dict, icons, labels, labels_inv

# 数据集，训练的时候会根据这个函数读取数据
class IconDataset(Dataset):

    def __init__(self, base_dir, icons, labels, labels_inv, transform=None):
        self.base_dir = base_dir
        self.transform = transform
        self.icons = icons
        self.labels = labels
        self.labels_inv = labels_inv

    def __len__(self):
        return len(self.icons)

    def __getitem__(self, idx):
        img_path = os.path.join(self.base_dir, self.icons[idx])
        img = Image.open(img_path)
        # 转换为四通道
        img = img.convert('RGBA')
        if self.transform:
            img = self.transform(img)
        # 每次训练时，喂给它图标、图标标签编码、负采样的图标标签编码作为一条记录
        return img, self.labels[idx], self.labels_inv[idx]


train_data = IconDataset(base_dir=os.path.join("datasets2", "data"), icons=train_icons, labels=train_labels,
                         labels_inv=train_labels_inv,
                         transform=train_transforms)
valid_data = IconDataset(base_dir=os.path.join("datasets2", "data"), icons=valid_icons, labels=valid_labels,
                         labels_inv=valid_labels_inv,
                         transform=eval_transforms)

# shuffle表示随机打乱所有记录，drop_last表示丢弃最后不足32的批（例如每批32条记录，60条记录可以划分为2批，第2批只有28条记录，因此训练时丢弃第2批，只用第1批）
train_loader = DataLoader(dataset=train_data, batch_size=batch_size, shuffle=True, drop_last=True)
valid_loader = DataLoader(dataset=valid_data, batch_size=batch_size, shuffle=True, drop_last=True)

# 针对标签的损失函数（见论文）
class Criterion(nn.Module):

    def __init__(self):
        super(Criterion, self).__init__()

    def forward(self, x1, x2):  # x1与x2是两个批的编码（二维）
        sims = torch.cosine_similarity(x1, x2, dim=1)
        return (torch.ones(len(sims)).to(device) - sims).mean()

# 针对负采样的标签的损失函数（见论文）
class CriterionInv(nn.Module):

    def __init__(self):
        super(CriterionInv, self).__init__()

    def forward(self, x1, x2):
        sims = torch.cosine_similarity(x1, x2, dim=1)
        zeros = torch.zeros(len(sims)).to(device)
        return torch.max(sims, zeros).mean(), sims.mean()  # 返回两个值，第一个值是负采样的标签的损失，第二个值是自定义的损失（后面训练时不会用到）


criterion = Criterion().to(device)
criterion_inv = CriterionInv().to(device)
optimizer = optim.Adam(image_model.parameters(), lr=1e-4)  # 优化器，使用Adam，学习率为1e-5，经验证这个比随机梯度下降、动量法收敛速度快很多
scheduler = optim.lr_scheduler.ExponentialLR(optimizer, gamma=0.9)  # 指数衰减调整学习率，意思是每经过一个epoch，令lr = lr * 0.9

# 打印训练进度条
def progress_bar(times, tol_times, loss, loss_inv, metric_inv, is_train):
    progress = int(times * 50 / tol_times)
    prefix = '' if is_train else '\t'
    end = '' if times < tol_times else '\n'
    print('\r{}[{}][{}] [{:.4f}][{:.4f}|{:.4f}][{:.4f}] [{}]'
          .format(prefix, times, tol_times, loss / times, loss_inv / times, metric_inv / times,
                  (loss + loss_inv) / times,
                  '-' * progress + '>' * (min(50 - progress, 1)) + '·' * (49 - progress)),
          end=end)


# 训练开始前，先计算一下验证集的损失
print("Calculate loss...")
image_model.eval()
with torch.no_grad():
    epoch_val_loss = 0.
    epoch_val_loss_inv = 0.
    epoch_val_metric_inv = 0.
    for cnt, (data, label, label_inv) in enumerate(valid_loader):
        data = data.to(device)
        val_output = image_model(data)
        val_loss = criterion(val_output, label)
        val_loss_inv, val_metric_inv = criterion_inv(val_output, label_inv)
        val_loss_all = val_loss + val_loss_inv
        epoch_val_loss += val_loss.item()
        epoch_val_loss_inv += val_loss_inv.item()
        epoch_val_metric_inv += val_metric_inv.item()
        progress_bar(cnt + 1, len(valid_loader), epoch_val_loss, epoch_val_loss_inv, epoch_val_metric_inv, False)

print("Start training...")
min_loss = 2.
cur_times = 0
max_times = 3
for epoch in range(epochs):
    image_model.train()
    epoch_loss = 0.  # 图标与标签的平均损失
    epoch_loss_inv = 0.  # 图标与负采样的标签的平均损失
    epoch_metric_inv = 0.
    for cnt, (data, label, label_inv) in enumerate(train_loader):  # 从数据集中加载一批数据（每次循环，batch_size个样本一起训练一次）
        optimizer.zero_grad()
        data = data.to(device)
        output = image_model(data)  # 用图像模型生成这些图标的编码
        loss = criterion(output, label)  # 根据图标与标签的编码，拿到损失
        loss_inv, metric_inv = criterion_inv(output, label_inv)
        loss_all = loss + loss_inv  # 把两个损失加起来
        loss_all.backward()  # 根据总损失更新图像模型的参数
        optimizer.step()
        epoch_loss += loss.item()
        epoch_loss_inv += loss_inv.item()
        epoch_metric_inv += metric_inv.item()
        # 打印进度条
        progress_bar(cnt + 1, len(train_loader), epoch_loss, epoch_loss_inv, epoch_metric_inv, True)
    scheduler.step()
    image_model.eval()  # 切换到验证模式，在验证集上验证损失，在该模式下，不会训练模型
    with torch.no_grad():
        epoch_val_loss = 0.
        epoch_val_loss_inv = 0.
        epoch_val_metric_inv = 0.
        epoch_val_loss_all = 0.
        for cnt, (data, label, label_inv) in enumerate(valid_loader):
            data = data.to(device)
            val_output = image_model(data)
            val_loss = criterion(val_output, label)
            val_loss_inv, val_metric_inv = criterion_inv(val_output, label_inv)
            val_loss_all = val_loss + val_loss_inv
            epoch_val_loss += val_loss.item()
            epoch_val_loss_inv += val_loss_inv.item()
            epoch_val_metric_inv += val_metric_inv.item()
            epoch_val_loss_all += val_loss_all.item()
            progress_bar(cnt + 1, len(valid_loader), epoch_val_loss, epoch_val_loss_inv, epoch_val_metric_inv, False)
        epoch_val_loss_all /= len(valid_loader)
        if epoch_val_loss_all < min_loss:  # 如果验证的损失下降了，保存模型
            min_loss = epoch_val_loss_all
            torch.save(image_model, "resnet50_ft.pth")
            cur_times = 0
        else:
            cur_times += 1
        if cur_times >= max_times:  # 如果连续max_times损失不下降，结束训练过程
            break
