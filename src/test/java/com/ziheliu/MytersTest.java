package com.ziheliu;

import org.junit.Assert;
import org.junit.Test;

public class MytersTest {
  @Test
  public void diffTestWithTwoSameString() {
    String oldString = "A\nB\nC";
    String newString = "A\nB\nC";

    String[] oldLines = oldString.split("\n");
    String[] newLines = newString.split("\n");

    Myters myters = new Myters(oldLines, newLines);
    int pathLen = myters.diff();

    Assert.assertEquals(0, pathLen);
    Assert.assertEquals(0, myters.getPathLen());
  }

  @Test
  public void diffTestWithTwoDifferentString() {
    String oldString = "A\nB\nC";
    String newString = "B\nC\nD";

    String[] oldLines = oldString.split("\n");
    String[] newLines = newString.split("\n");

    Myters myters = new Myters(oldLines, newLines);
    int pathLen = myters.diff();

    Assert.assertEquals(2, pathLen);
    Assert.assertEquals(2, myters.getPathLen());
  }
}
