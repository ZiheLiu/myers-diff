package com.ziheliu;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class MyersTest {
  @Test
  public void diffTestWithTwoSameString() {
    String oldString = "A\nB\nC";
    String newString = "A\nB\nC";

    String[] oldLines = oldString.split("\n");
    String[] newLines = newString.split("\n");

    Myers myers = new Myers(oldLines, newLines);
    int pathLen = myers.diff();

    Assert.assertEquals(0, pathLen);
    Assert.assertEquals(0, myers.getPathLen());
  }

  @Test
  public void diffTestWithTwoDifferentString() {
    String oldString = "A\nB\nC";
    String newString = "B\nC\nD";

    String[] oldLines = oldString.split("\n");
    String[] newLines = newString.split("\n");

    Myers myers = new Myers(oldLines, newLines);
    int pathLen = myers.diff();

    Assert.assertEquals(2, pathLen);
    Assert.assertEquals(2, myers.getPathLen());
  }

  @Test
  public void diffTestWithTwoDifferentLengthString() {
    String oldString = "A\nB\nC";
    String newString = "A\nB\nC\nD";

    String[] oldLines = oldString.split("\n");
    String[] newLines = newString.split("\n");

    Myers myers = new Myers(oldLines, newLines);
    int pathLen = myers.diff();

    Assert.assertEquals(1, pathLen);
    Assert.assertEquals(1, myers.getPathLen());
  }

  @Test
  public void getPathTestWithTwoSameString() {
    String oldString = "A\nB\nC";
    String newString = "A\nB\nC";

    String[] oldLines = oldString.split("\n");
    String[] newLines = newString.split("\n");

    Myers myers = new Myers(oldLines, newLines);
    myers.diff();
    List<Node> path = myers.getPath();

    List<Node> expectedPath = new ArrayList<>();
    expectedPath.add(new Node(0, 0));
    expectedPath.add(new Node(3, 3));

    Assert.assertEquals(expectedPath, path);
  }

  @Test
  public void getPathTestWithTwoDifferentString() {
    String oldString = "A\nB\nC";
    String newString = "B\nC\nD";

    String[] oldLines = oldString.split("\n");
    String[] newLines = newString.split("\n");

    List<Node> expectedPath = new ArrayList<>();
    expectedPath.add(new Node(0, 0));
    expectedPath.add(new Node(3, 2));
    expectedPath.add(new Node(3, 3));

    Myers myers = new Myers(oldLines, newLines);
    myers.diff();
    List<Node> path = myers.getPath();

    Assert.assertEquals(expectedPath, path);
  }

  @Test
  public void getPathTestWithTwoDifferentLengthString() {
    String oldString = "A\nB\nC";
    String newString = "A\nB\nC\nD";

    String[] oldLines = oldString.split("\n");
    String[] newLines = newString.split("\n");

    List<Node> expectedPath = new ArrayList<>();
    expectedPath.add(new Node(0, 0));
    expectedPath.add(new Node(3, 3));
    expectedPath.add(new Node(3, 4));

    Myers myers = new Myers(oldLines, newLines);
    myers.diff();
    List<Node> path = myers.getPath();

    Assert.assertEquals(expectedPath, path);
  }
}
