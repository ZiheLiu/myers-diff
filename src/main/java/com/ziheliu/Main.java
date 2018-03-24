package com.ziheliu;

import com.ziheliu.util.FileUtil;

import java.io.IOException;


public class Main {
  /**
   * 项目入口.
   * @param args Read args from shell. args[0] is oldString and args[1] is newString.
   */
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Please input two files");
      return;
    }

    String oldFilename = args[0];
    String newFilename = args[1];

    String oldString;
    try {
      oldString = FileUtil.readToString(oldFilename);
    } catch (IOException exception) {
      System.out.println("Can't find file: " + oldFilename);
      return;
    }

    String newString;
    try {
      newString = FileUtil.readToString(newFilename);
    } catch (IOException exception) {
      System.out.println("Can't find file: " + oldFilename);
      return;
    }

    Difference difference = new Difference(oldString, newString);
    System.out.println(difference.getDiffString());
  }
}
