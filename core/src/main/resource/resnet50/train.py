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
batch_size = 32
epochs = 1000
image_model = ResNet50(channels=4).to(device)
text_model = sentence_transformers.SentenceTransformer('all-MiniLM-L6-v2').to(device)
label_path = os.path.join("..", "datasets", "label.csv")

print("Load sentences...")
exist_set = set()
with open(label_path, encoding="utf8") as file:
    sentence_set = set()
    reader = csv.reader(file)
    for row in reader:
        if len(row[2].strip()) == 0:
            continue
        # The dataset is preprocessed before training, and the result is written to directory icon-result
        row0 = re.sub("^iconfont", "iconfont-result", row[0][:-4] + ".png")
        if not os.path.exists(os.path.join("..", "datasets", row0)):
            continue
        exist_set.add(row0)
        splits = row[2].split(" | ")
        for split in splits:
            sentence_set.add(split.lower())

label_list = list(sentence_set)

print("Calculate word encodings...")
embeddings_list = text_model.encode(label_list, convert_to_tensor=True, device=device)
embeddings_dict = dict(zip(label_list, embeddings_list))

print("Load dataset...")
with open(label_path, encoding="utf8") as file:
    reader = csv.reader(file)
    icons = []
    labels = []
    labels_word = []
    labels_inv = []
    labels_inv_word = []
    for row in reader:
        if len(row[2].strip()) == 0:
            continue
        row0 = re.sub("^iconfont", "iconfont-result", row[0][:-4] + ".png")
        if row0 not in exist_set:
            continue
        splits = row[2].split(" | ")
        row2 = random.choice(splits)
        icons.append(row0)
        labels.append(embeddings_dict[row2.lower()])
        labels_word.append(row2.lower())
        inv = random.choice(label_list)
        while embeddings_dict[row2.lower()] @ embeddings_dict[inv] > 0.1:
            inv = random.choice(label_list)
        labels_inv.append(embeddings_dict[inv])
        labels_inv_word.append(inv)

train_icons, valid_icons, train_labels, valid_labels, train_labels_word, valid_labels_word, \
train_labels_inv, valid_labels_inv, train_labels_inv_word, valid_labels_inv_word \
    = train_test_split(icons, labels, labels_word, labels_inv, labels_inv_word, test_size=0.2)
valid_icons, test_icons, valid_labels, test_labels, valid_labels_word, test_labels_word,\
valid_labels_inv, test_labels_inv, valid_labels_inv_word, test_labels_inv_word \
    = train_test_split(valid_icons, valid_labels, valid_labels_word, valid_labels_inv, valid_labels_inv_word, test_size=0.5)
print("Size of training set：", len(train_icons))
print("Size of validation set：", len(valid_icons))
print("Size of test set：", len(test_icons))
with open(os.path.join("..", "datasets", "datasets.json"), "w") as file:
    json.dump(((train_icons, train_labels_word, train_labels_inv_word),
               (valid_icons, valid_labels_word, valid_labels_inv_word),
               (test_icons, test_labels_word, test_labels_inv_word)), file)

del exist_set, sentence_set, label_list, embeddings_list, embeddings_dict, icons, labels, labels_inv


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
        if self.transform:
            img = self.transform(img)
        return img, self.labels[idx], self.labels_inv[idx]


train_data = IconDataset(base_dir=os.path.join("..", "datasets"), icons=train_icons, labels=train_labels,
                         labels_inv=train_labels_inv,
                         transform=train_transforms)
valid_data = IconDataset(base_dir=os.path.join("..", "datasets"), icons=valid_icons, labels=valid_labels,
                         labels_inv=valid_labels_inv,
                         transform=eval_transforms)

train_loader = DataLoader(dataset=train_data, batch_size=batch_size, shuffle=True, drop_last=True)
valid_loader = DataLoader(dataset=valid_data, batch_size=batch_size, shuffle=True, drop_last=True)


class Criterion(nn.Module):

    def __init__(self):
        super(Criterion, self).__init__()

    def forward(self, x1, x2):
        sims = torch.cosine_similarity(x1, x2, dim=1)
        return (torch.ones(len(sims)).to(device) - sims).mean()


class CriterionInv(nn.Module):

    def __init__(self):
        super(CriterionInv, self).__init__()

    def forward(self, x1, x2):
        sims = torch.cosine_similarity(x1, x2, dim=1)
        zeros = torch.zeros(len(sims)).to(device)
        return torch.max(sims, zeros).mean(), sims.mean()


criterion = Criterion().to(device)
criterion_inv = CriterionInv().to(device)
optimizer = optim.Adam(image_model.parameters(), lr=1e-5)
scheduler = optim.lr_scheduler.ExponentialLR(optimizer, gamma=0.9)


def progress_bar(times, tol_times, loss, loss_inv, metric_inv, is_train):
    progress = int(times * 50 / tol_times)
    prefix = '' if is_train else '\t'
    end = '' if times < tol_times else '\n'
    print('\r{}[{}][{}] [{:.4f}][{:.4f}|{:.4f}][{:.4f}] [{}]'
          .format(prefix, times, tol_times, loss / times, loss_inv / times, metric_inv / times,
                  (loss + loss_inv) / times,
                  '-' * progress + '>' * (min(50 - progress, 1)) + '·' * (49 - progress)),
          end=end)


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
    epoch_loss = 0.
    epoch_loss_inv = 0.
    epoch_metric_inv = 0.
    for cnt, (data, label, label_inv) in enumerate(train_loader):
        optimizer.zero_grad()
        data = data.to(device)
        output = image_model(data)
        loss = criterion(output, label)
        loss_inv, metric_inv = criterion_inv(output, label_inv)
        loss_all = loss + loss_inv
        loss_all.backward()
        optimizer.step()
        epoch_loss += loss.item()
        epoch_loss_inv += loss_inv.item()
        epoch_metric_inv += metric_inv.item()
        progress_bar(cnt + 1, len(train_loader), epoch_loss, epoch_loss_inv, epoch_metric_inv, True)
    scheduler.step()
    image_model.eval()
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
        if epoch_val_loss_all < min_loss:
            min_loss = epoch_val_loss_all
            torch.save(image_model, "resnet50.pth")
            cur_times = 0
        else:
            cur_times += 1
        if cur_times >= max_times:
            break
