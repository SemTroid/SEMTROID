from unit import crop_image_with_padding_bytes


def get_processed_image(byte_array):
    # Java byte[] 转为 python bytes
    python_ints = [b & 0xff for b in byte_array]
    pythonBytes = bytes(python_ints)

    # 调用图像处理方法
    byte_encode = crop_image_with_padding_bytes(pythonBytes, 5)

    # python bytes 转为 Java byte[]
    java_bytes = [int(i) - 256 if int(i) > 127 else int(i) for i in byte_encode]

    return java_bytes
