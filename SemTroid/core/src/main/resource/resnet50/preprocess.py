import os
# from shutil import copyfile
#
# import cairosvg
# import numpy as np
# import torch
# from PIL import Image
from torchvision import transforms


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
