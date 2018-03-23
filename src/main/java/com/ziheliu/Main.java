package com.ziheliu;

public class Main {
  /**
   * 项目入口.
   * @param args 从命令行读取的参数.
   */
  public static void main(String[] args) {
    String oldString = "A\nB\nC";
    String newString = "B\nC\nD";

    Difference difference = new Difference(oldString, newString);
    System.out.println(difference.getDiffString());
  }
}
