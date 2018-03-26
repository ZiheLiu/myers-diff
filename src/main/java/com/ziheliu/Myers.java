package com.ziheliu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Myers {
  private String[] oldLines;
  private String[] newLines;

  /**
   * Map currNode to prevNode.
   * int currNodeInt = currNodeInt.getX() * totalLength + currNodeInt.getY();
   * int prevNodeInt = prevNode.getX() * totalLength + prevNode.getY();
   */
  private Map<Integer, Integer> prevNode;
  private int gap;
  private int pathLen;


  /**
   * Constructor of Myers.
   * @param oldLines String[].
   * @param newLines String[].
   */
  public Myers(String[] oldLines, String[] newLines) {
    this.oldLines = oldLines;
    this.newLines = newLines;
    this.prevNode = new HashMap<>();
  }

  public int getGap() {
    return gap;
  }

  public int getPathLen() {
    return pathLen;
  }

  /**
   * Execute algorithm Myers.
   * <code>pathLen</code> is the sum of path steps.
   * <code>gap = x - y</code>, where x and y are refer to the end point.
   * @return int: the sum of different lines between oldString and newString.
   */
  public int diff() {
    int totalLength = this.oldLines.length + this.newLines.length + 1;

    MyArray mapGapToX = new MyArray(totalLength);

    for (int pathLen = 0; pathLen <= totalLength; pathLen++) {
      for (int gap = -pathLen; gap <= pathLen; gap += 2) {
        int prevX;
        int prevY;
        int currX;
        int currY;
        if (pathLen == 0) {
          prevY = prevX = 0;
          currY = currX = this.walkDiagonal(prevX, prevY);
        } else {
          final boolean down = (gap == -pathLen)
              || (gap != pathLen
              && mapGapToX.get(gap - 1) < mapGapToX.get(gap + 1));

          final int prevGap = down ? gap + 1 : gap - 1;

          prevX = mapGapToX.get(prevGap);
          prevY = prevX - prevGap;

          currX = down ? prevX : prevX + 1;
          currY = currX - gap;

          currX = this.walkDiagonal(currX, currY);
          currY = currX - gap;
        }

        mapGapToX.set(gap, currX);

        this.prevNode.put(currX * totalLength + currY, prevX * totalLength + prevY);

        if (currX >= this.oldLines.length && currY >= this.newLines.length) {
          this.gap = gap;
          this.pathLen = pathLen;
          return this.pathLen;
        }
      }
    }

    return -1;
  }

  /**
   * According to <code>this.prevNode</code>, get node list.
   * @return return node list from (0, 0) to (oldLines.length, newLines.length).
   */
  public List<Node> getPath() {
    List<Node> path = new ArrayList<>();

    int totalLength = this.oldLines.length + this.newLines.length + 1;

    Node node = new Node(this.oldLines.length, this.newLines.length);
    path.add(node);

    // (node.x >= 0 && node.y > 0) || (node.x > 0 && node.y >= 0)
    // node.x and node.y are both greater or equal to 0.
    // But they cannot be equal to 0 in the meanwhile.
    for (; node.getCoordinateX() >= 0 && node.getCoordinateY() >= 0
        && (node.getCoordinateY() + node.getCoordinateX() > 0); ) {
      int prevNode = this.prevNode.get(node.getCoordinateX() * totalLength + node.getCoordinateY());
      int prevX = prevNode / totalLength;
      int prevY = prevNode % totalLength;

      node = new Node(prevX, prevY);
      path.add(node);
    }

    Collections.reverse(path);
    return path;
  }

  /**
   * Walk through diagonal as far as we can, because the path through diagonal is 0.
   * @param currX the x coordinate.
   * @param currY the y coordinate.
   * @return the x coordinate after walking through diagonal.
   */
  private int walkDiagonal(int currX, int currY) {
    while (this.checkBoundary(currX, this.oldLines.length)
        && this.checkBoundary(currY, this.newLines.length)
        && this.oldLines[currX].equals(this.newLines[currY])) {
      currX++;
      currY++;
    }
    return currX;
  }

  /**
   * Check whether value is in range of [min, max).
   * @param value int.
   * @param max int: max value.
   * @param min int: min value.
   * @return whether value is in range of [min, max).
   */
  private boolean checkBoundary(int value, int max, int min) {
    return min <= value && max > value;
  }

  private boolean checkBoundary(int value, int max) {
    return this.checkBoundary(value, max, 0);
  }
}
