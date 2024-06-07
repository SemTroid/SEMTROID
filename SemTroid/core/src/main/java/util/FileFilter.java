package main.java.util;

import java.io.FilenameFilter;

/**
 * @author anonymous author
 * @version 1.0
 */
public class FileFilter {
    public static FilenameFilter javaFileFilter = (dir, name) -> name.toLowerCase().endsWith(".java");
}
