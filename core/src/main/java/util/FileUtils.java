package main.java.util;

import java.io.*;


public class FileUtils {
    public static void createDirectory(String filename) throws Exception {
        createDirectory(new File(filename));
    }

    public static void createDirectory(File file) throws Exception {
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                throw new Exception("Can't create the directory: " + file);
            }
        }
    }

    public static Object readObject(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return objectInputStream.readObject();
    }

    public static void writeObject(Object object, String path) throws Exception {
        FileUtils.createDirectory(path);
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        fileOutputStream.close();
    }

}
