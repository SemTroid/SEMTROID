from io import BytesIO
import sentence_transformers
import torch
from PIL import Image
from LRUDict import LRUDict
from get_cut_pic_one import get_processed_image
from preprocess import predict_transforms
from unit import is_pure_color

device = "cuda" if torch.cuda.is_available() else "cpu"
image_model = torch.load(r"core/src/main/resource/resnet50/resnet50_ft.pth", map_location=torch.device(device))
text_model = sentence_transformers.SentenceTransformer('core/src/main/resource/all-MiniLM-L6-v2')
image_model.eval()

device = "cuda" if torch.cuda.is_available() else "cpu"
# 使用LRUDict存文本/图像的编码，存满后会优先删除最近最少使用的项
text_dict = LRUDict(max_len=1000000)
image_dict = LRUDict(max_len=1000)
image_batch_size = 32


def encode_texts(texts):
    texts = [text for text in texts if not text_dict.has_key(text)]
    if len(texts) != 0:
        encodes = text_model.encode(texts, convert_to_tensor=True, device=device)
        for text, encode in zip(texts, encodes):
            text_dict[text] = encode


def encode_images(images):
    images = [image for image in images if not image_dict.has_key(image)]
    images_c = [convert(image) for image in images]
    with torch.no_grad():
        for i in range((len(images) + image_batch_size - 1) // image_batch_size):
            begin = i * image_batch_size
            end = min(begin + image_batch_size, len(images))
            encodes = image_model(torch.stack([image for image in images_c[begin:end]], 0).to(device))
            for image, encode in zip(images[begin:end], encodes):
                image_dict[image] = encode


def sim_image2image(image1, image2):
    if not image_dict.has_key(image1):
        encode_images([image1])
    if not image_dict.has_key(image2):
        encode_images([image2])
    return (image_dict[image1] @ image_dict[image2]).item()


def sim_text2image(text, image):
    if not image_dict.has_key(image):
        encode_images([image])
    if not text_dict.has_key(text):
        encode_texts([text])
    return (image_dict[image] @ text_dict[text]).item()


def sim_text2text(text1, text2):
    if not text_dict.has_key(text1):
        encode_texts([text1])
    if not text_dict.has_key(text2):
        encode_texts([text2])
    return (text_dict[text1] @ text_dict[text2]).item()


def convert(image):
    # 裁剪处理图片边框
    image = get_processed_image(image)
    image = bytes(i % 256 for i in image)
    image = BytesIO(image)
    return predict_transforms(Image.open(image))


def is_pure(image):
    return is_pure_color(image)
