package main.java.util;

import java.io.FilenameFilter;

/**
 * @author QS
 * @version 1.0
 * @date 2022/1/13 11:52
 */
public class FileFilter {
    public static FilenameFilter javaFileFilter = (dir, name) -> name.toLowerCase().endsWith(".java");
}
