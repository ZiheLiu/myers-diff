package com.ziheliu.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class FileUtil {
  /**
   * Read from filename, and return the whole char of it.
   * @param filename String. The input filename.
   * @return The whole char reading from filename.
   * @throws IOException Exception.
   */
  public static String getStringFromFile(String filename) throws IOException {
    BufferedReader fileReader = new BufferedReader(new FileReader(filename));

    String line;
    StringBuilder stringBuilder = new StringBuilder();

    while ((line = fileReader.readLine()) != null) {
      stringBuilder.append(line).append("\n");
    }

    return stringBuilder.toString();
  }
}
