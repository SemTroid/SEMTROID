import torchvision
from torch import nn
from torch.nn import Conv2d
import torch.nn.functional as F


class ResNet50(nn.Module):

    def __init__(self, channels):
        super().__init__()
        self.image_model = torchvision.models.resnet50()
        self.image_model.conv1 = Conv2d(channels, 64, kernel_size=(7, 7), stride=(2, 2), padding=(3, 3), bias=False)
        self.image_model.fc = nn.Linear(self.image_model.fc.in_features, 384)

    def forward(self, img):
        return F.normalize(self.image_model(img), p=2, dim=1)
