package com.ziheliu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Myers {
  private String[] oldLines;
  private String[] newLines;

  /**
   * Two dimensional array[x][y].
   * X is pathLen, and y is gap.
   */
  private MyArray[] graph;
  private int gap;
  private int pathLen;

  public Myers(String[] oldLines, String[] newLines) {
    this.oldLines = oldLines;
    this.newLines = newLines;
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
    int totalLength = this.oldLines.length + this.newLines.length;

    this.graph = new MyArray[totalLength + 1];
    for (int i = 0; i < this.graph.length; i++) {
      this.graph[i] = new MyArray(totalLength + 1);
    }

    for (int pathLen = 0; pathLen <= totalLength; pathLen++) {
      MyArray column = this.graph[pathLen];

      for (int gap = -pathLen; gap <= pathLen; gap += 2) {
        MyArray prevColumn = pathLen > 0
            ? this.graph[pathLen - 1]
            : new MyArray(totalLength + 1);

        boolean down = (gap == -pathLen)
            || (gap != pathLen
            && prevColumn.get(gap - 1) < prevColumn.get(gap + 1));

        int prevGap = down ? gap + 1 : gap - 1;

        int xstart = prevColumn.get(prevGap);

        int xend = down ? xstart : xstart + 1;
        int yend = xend - gap;

        xend = this.walkDiagonal(xend, yend);
        yend = xend - gap;

        column.set(gap, xend);

        if (xend >= this.oldLines.length && yend >= this.newLines.length) {
          this.gap = gap;
          this.pathLen = pathLen;
          return this.pathLen;
        }
      }
    }

    return -1;
  }

  /**
   * According to <code>this.graph</code>, get node list.
   * @return return node list from (0, 0) to (oldLines.length, newLines.length).
   */
  public List<Node> getPath() {
    List<Node> path = new ArrayList<>();

    Node node = new Node(this.oldLines.length, this.newLines.length);
    path.add(node);

    for (int curPathLen = this.pathLen;
         curPathLen > 0 && node.getCoordinateX() >= 0 && node.getCoordinateY() >= 0; curPathLen--) {
      MyArray prevColumn = this.graph[curPathLen - 1];

      int gap = node.getCoordinateX() - node.getCoordinateY();

      boolean down = (gap == -curPathLen)
          || (gap != pathLen
          && prevColumn.get(gap - 1) < prevColumn.get(gap + 1));

      int prevGap = down ? gap + 1 : gap - 1;

      int xstart = prevColumn.get(prevGap);
      int ystart = xstart - prevGap;

      node = new Node(xstart, ystart);
      path.add(node);
    }

    if (node.getCoordinateY() != 0 || node.getCoordinateX() != 0) {
      path.add(new Node(0, 0));
    }

    Collections.reverse(path);
    return path;
  }

  /**
   * Walk through diagonal as far as we can, because the path through diagonal is 0.
   * @param xstart the x coordinate.
   * @param ystart the y coordinate.
   * @return the x coordinate after walking through diagonal.
   */
  private int walkDiagonal(int xstart, int ystart) {
    while (this.checkBoundary(xstart, this.oldLines.length)
        && this.checkBoundary(ystart, this.newLines.length)
        && this.oldLines[xstart].equals(this.newLines[ystart])) {
      xstart++;
      ystart++;
    }
    return xstart;
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
