from io import BytesIO
import cv2
import numpy as np
from PIL import Image


# 判断图片是否为近似纯色
def is_pure_color(image):
    # Java byte[] 转为 python bytes
    python_ints = [b & 0xff for b in image]
    pythonBytes = bytes(python_ints)
    # 读取图片
    image = Image.open(BytesIO(pythonBytes))
    # 获取图像最大和最小值（黑白通道）
    extrema = image.convert("L").getextrema()
    # print(extrema[0])
    # print(extrema[1])
    # 最大最小值之差小于等于30时，认为是纯色图片
    if abs(extrema[0] - extrema[1]) <= 30:
        return True
    else:
        return False


def get_bianjie(img, backgroud_color):
    x1, x2, y1, y2 = 0, 0, 0, 0
    rows, cols = img.shape

    for i in range(rows):
        if np.any(img[i, :] != backgroud_color):
            x1 = i
            break
    for i in range(rows - 1, 0, -1):
        if np.any(img[i, :] != backgroud_color):
            x2 = i
            break
    for j in range(cols):
        if np.any(img[:, j] != backgroud_color):
            y1 = j
            break
    for j in range(cols - 1, 0, -1):
        if np.any(img[:, j] != backgroud_color):
            y2 = j
            break
    # print(f'{x1}  {y1}  {x2}  {y2}')
    return x1, x2, y1, y2


# 输入 python bytes形式的图片，
def crop_image_with_padding_bytes(image_bytes, padding):
    # 读取图像 bytes格式解码为cv2对象
    image = cv2.imdecode(np.frombuffer(image_bytes, dtype=np.uint8), cv2.IMREAD_UNCHANGED)

    # 将图像转换为灰度图
    gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    # np.set_printoptions(threshold=np.inf)
    # print(gray_image)

    # 背景像素
    backgroud_color = gray_image[0][0]
    # 通过阈值分割找到图标区域

    x, x2, y, y2 = get_bianjie(gray_image, backgroud_color)

    mid_x = int((x2 + x) / 2)
    mid_y = int((y2 + y) / 2)
    # 添加padding
    max_temp = int(max(x2 - mid_x, y2 - mid_y))

    for i in range(padding):
        max_temp = max_temp + 1
        if (mid_x - max_temp + 1 >= 0 and mid_x + max_temp < image.shape[
            0] and mid_y - max_temp + 1 >= 0 and mid_y + max_temp < image.shape[1]):
            continue
        else:
            max_temp = max_temp - 1
            break
    # print(max_temp)

    # 裁剪图像致规定区域

    cropped_image = image[mid_x - max_temp + 1:mid_x + max_temp, mid_y - max_temp + 1:mid_y + max_temp]
    # print(f'{mid_x-max_temp+1}   {mid_x + max_temp}    { mid_y-max_temp+1}   {mid_y +max_temp} ')

    # 保存图像
    # cv2.imwrite(f'{result_path}/{result_name}', cropped_image)

    # cropped_image cv2对象 编码为 python的bytes格式
    # 若裁剪后的图像为空，返回原图的bytes
    if cropped_image is not None:
        res = cv2.imencode('.png', cropped_image)
        # 编码成功
        if res[0]:
            byte_encode = np.array(res[1]).tobytes()
        else:
            img_encode = cv2.imencode('.png', image)[1]
            byte_encode = np.array(img_encode).tobytes()

    else:
        img_encode = cv2.imencode('.png', image)[1]
        byte_encode = np.array(img_encode).tobytes()

    # 返回 python bytes 形式
    return byte_encode


def crop_image_with_padding(image_path, image_name, padding, result_path):
    # 读取图像
    image = cv2.imread(image_path, -1)

    # 将图像转换为灰度图
    gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    # np.set_printoptions(threshold=np.inf)
    # print(gray_image)

    # 背景像素
    backgroud_color = gray_image[0][0]
    # 通过阈值分割找到图标区域

    x, x2, y, y2 = get_bianjie(gray_image, backgroud_color)

    mid_x = int((x2 + x) / 2)
    mid_y = int((y2 + y) / 2)
    # 添加padding
    max_temp = int(max(x2 - mid_x, y2 - mid_y))

    for i in range(padding):
        max_temp = max_temp + 1
        if (mid_x - max_temp + 1 >= 0 and mid_x + max_temp < image.shape[
            0] and mid_y - max_temp + 1 >= 0 and mid_y + max_temp < image.shape[1]):
            continue
        else:
            max_temp = max_temp - 1
            break
    # print(max_temp)

    # 裁剪图像致规定区域

    cropped_image = image[mid_x - max_temp + 1:mid_x + max_temp, mid_y - max_temp + 1:mid_y + max_temp]
    # print(f'{mid_x-max_temp+1}   {mid_x + max_temp}    { mid_y-max_temp+1}   {mid_y +max_temp} ')

    # 保存姓名
    rs_name = image_name.replace('origin', 'new')
    # 保存图像
    cv2.imwrite(f'{result_path}/{rs_name}', cropped_image)
    return cropped_image


def cut_pic_with_num(image_path, image_name, padding, result_path):
    # 读取图像
    image = cv2.imread(image_path, -1)

    # 将图像转换为灰度图
    gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    unique_values, counts = np.unique(gray_image, return_counts=True)
    sorted_indices = np.argsort(-counts)
    sorted_values = unique_values[sorted_indices]

    # 输出排序后的像素值数组
    sorted_values_array = np.array(sorted_values)
    backgroud_color = sorted_values_array[0]
    main_color = sorted_values_array[1]

    gray_image[gray_image != main_color] = backgroud_color

    x, x2, y, y2 = get_bianjie(gray_image, backgroud_color)

    mid_x = int((x2 + x) / 2)
    mid_y = int((y2 + y) / 2)
    # 添加padding
    max_temp = int(max(x2 - mid_x, y2 - mid_y))

    for i in range(padding):
        max_temp = max_temp + 1
        if (mid_x - max_temp + 1 >= 0 and mid_x + max_temp < image.shape[
            0] and mid_y - max_temp + 1 >= 0 and mid_y + max_temp < image.shape[1]):
            continue
        else:
            max_temp = max_temp - 1
            break
    # print(max_temp)

    # 裁剪图像致规定区域

    cropped_image = image[mid_x - max_temp + 1:mid_x + max_temp, mid_y - max_temp + 1:mid_y + max_temp]
    # print(f'{mid_x-max_temp+1}   {mid_x + max_temp}    { mid_y-max_temp+1}   {mid_y +max_temp} ')

    # 保存的姓名，可修改
    rs_name = image_name.replace('origin', 'new')
    # 保存图像
    cv2.imwrite(f'{result_path}/{rs_name}', cropped_image)
    return cropped_image
