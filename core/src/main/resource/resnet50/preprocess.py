import os
# from shutil import copyfile
#
# import cairosvg
# import numpy as np
# import torch
# from PIL import Image
from torchvision import transforms


# def svg2png(from_path, to_path):
#     if os.path.exists(to_path):
#         print("File exists：", from_path, " -> ", to_path)
#     to_path_parent = os.path.dirname(to_path)
#     if not os.path.exists(to_path_parent):
#         os.makedirs(to_path_parent)
#     from_path_l = from_path.lower()
#     if from_path_l.endswith(".svg"):
#         try:
#             while True:
#                 width = np.random.randn()
#                 if width >= 0:
#                     width = round(32 * (1 + width))
#                 else:
#                     width = round(32 / (1 - width / 2))
#                 height = np.random.randn()
#                 if height >= 0:
#                     height = round(width * (1 + height / 4))
#                 else:
#                     height = round(width / (1 - height / 4))
#                 if width * height >= 256:
#                     break
#             cairosvg.svg2png(url=from_path, write_to=to_path, output_width=width, output_height=height)
#             if is_blank_image(to_path):
#                 print("空白图像：", from_path, " -> ", to_path)
#                 os.remove(to_path)
#         except:
#             print("转换失败：", from_path, " -> ", to_path)
#     elif from_path_l.endswith(".png"):
#         if is_blank_image(from_path):
#             print("空白图像：", from_path, " -> ", to_path)
#         else:
#             copyfile(from_path, to_path)
#     else:
#         print("非目标文件：", from_path, " -> ", to_path)


# def is_blank_image(path):
#     t_image = transforms.ToTensor()(Image.open(path))
#     shape = t_image.shape
#     if shape[0] == 0 or shape[1] == 0 or shape[2] == 0 or t_image.equal(torch.Tensor(shape).fill_(t_image[0][0][0])):
#         return True
#     else:
#         return False
#
#
# def batch_svg2png(from_dir, to_dir):
#     for root, dirs, files in os.walk(from_dir):
#         root2 = root[len(from_dir) + 1:]
#         for file in files:
#             svg2png(os.path.join(root, file), os.path.join(to_dir, root2, file[:-4] + ".png"))


train_transforms = transforms.Compose(
    [
        transforms.ToTensor(),
        transforms.Resize((64, 64),antialias=True),
    ]
)

eval_transforms = transforms.Compose(
    [
        transforms.ToTensor(),
        transforms.Resize((64, 64),antialias=True),
    ]
)

predict_transforms = transforms.Compose(
    [
        transforms.ToTensor(),
        transforms.Resize((64, 64),antialias=True),
    ]
)

# batch_svg2png(os.path.join("..", "datasets", "iconfont-original"), os.path.join("..", "datasets", "iconfont-preprocess"))
