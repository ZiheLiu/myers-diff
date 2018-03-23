package com.ziheliu;

public class Main {
  /**
   * 项目入口.
   * @param args 从命令行读取的参数.
   */
  public static void main(String[] args) {
    String oldString = "A\nB\nC";
    String newString = "A\nB\nC";

    String[] oldLines = oldString.split("\n");
    String[] newLines = newString.split("\n");

    Myters myters = new Myters(oldLines, newLines);
    myters.diff();
    System.out.println(myters.getPathLen());
  }
}
