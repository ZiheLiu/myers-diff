package com.ziheliu;

public class Myters {
  private String[] oldLines;
  private String[] newLines;

  private MyArray mapGapToX;
  private int gap;
  private int pathLen;

  public Myters(String[] oldLines, String[] newLines) {
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
   * Execute algorithm Myters.
   * <code>pathLen</code> is the sum of path steps.
   * <code>gap = x - y</code>, where x and y are refer to the end point.
   * @return int: the sum of different lines between oldString and newString.
   */
  public int diff() {
    int totalLength = this.oldLines.length + this.newLines.length;

    this.mapGapToX = new MyArray(totalLength + 1);

    for (int pathLen = 0; pathLen <= totalLength; pathLen++) {
      for (int gap = -pathLen; gap <= pathLen; gap += 2) {
        boolean down = (gap == -pathLen)
            || (gap != pathLen
                && this.mapGapToX.get(gap - 1) < this.mapGapToX.get(gap + 1));

        int prevGap = down ? gap + 1 : gap - 1;

        int xstart = this.mapGapToX.get(prevGap);

        int xend = down ? xstart : xstart + 1;
        int yend = xend - gap;

        xend = this.walkDiagonal(xend, yend);
        yend = xend - gap;

        this.mapGapToX.set(gap, xend);

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
