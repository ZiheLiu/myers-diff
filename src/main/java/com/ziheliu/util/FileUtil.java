package com.ziheliu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public final class FileUtil {
  /**
   * Read from filename, and return the whole char of it.
   * @param filename String. The input filename.
   * @return The whole char reading from filename.
   * @throws IOException Exception.
   */
  public static String readToString(String filename) throws IOException {
    String encoding = "UTF-8";
    File file = new File(filename);

    Long fileLength = file.length();
    byte[] fileContent = new byte[fileLength.intValue()];

    FileInputStream fileInputStream = new FileInputStream(file);
    fileInputStream.read(fileContent);
    fileInputStream.close();

    return new String(fileContent, encoding);
  }
}
