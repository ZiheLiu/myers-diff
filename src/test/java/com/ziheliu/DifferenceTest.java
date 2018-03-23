package com.ziheliu;

import org.junit.Assert;
import org.junit.Test;

public class DifferenceTest {
  @Test
  public void constructorTestWithArray() {
    String[] oldLines = new String[]{"A", "B", "C"};
    String[] newLines = new String[]{"D", "B", "C"};

    Difference difference = new Difference(oldLines, newLines);

    Assert.assertArrayEquals(oldLines, difference.getOldLines());
    Assert.assertArrayEquals(newLines, difference.getNewLines());
  }

  @Test
  public void constructorTestWithString() {
    String oldString = "A\nB\nC\n";
    String newString = "D\nB\nC\n";

    String[] oldLines = oldString.split("\n");
    String[] newLines = newString.split("\n");

    Difference difference = new Difference(oldString, newString);

    Assert.assertArrayEquals(oldLines, difference.getOldLines());
    Assert.assertArrayEquals(newLines, difference.getNewLines());
  }

  @Test
  public void getDiffStringTest() {
    String oldString = "A\nB\nC\n";
    String newString = "B\nC\nD\n";

    Difference difference = new Difference(oldString, newString);
    String diffString = difference.getDiffString();

    String expectedDiffString = this.getStringWithRed("-\tA") + "\n"
        + "\tB\n"
        + "\tC\n"
        + this.getStringWithGreen("+\tD") + "\n";

    Assert.assertEquals(expectedDiffString, diffString);
  }

  @Test
  public void getDiffStringTestWithNoSame() {
    String oldString = "A\nB\nC\n";
    String newString = "D\nE\nF\n";

    Difference difference = new Difference(oldString, newString);
    String diffString = difference.getDiffString();

    String expectedDiffString = this.getStringWithRed("-\tA") + "\n"
        + this.getStringWithRed("-\tB") + "\n"
        + this.getStringWithRed("-\tC") + "\n"
        + this.getStringWithGreen("+\tD") + "\n"
        + this.getStringWithGreen("+\tE") + "\n"
        + this.getStringWithGreen("+\tF") + "\n";
    Assert.assertEquals(expectedDiffString, diffString);
  }

  @Test
  public void getDiffStringTestComplex() {
    String oldString = "A\nB\nM\nA\nB\n";
    String newString = "M\nA\nB\nN\nA\nB\n";

    Difference difference = new Difference(oldString, newString);
    String diffString = difference.getDiffString();

    String expectedDiffString = this.getStringWithGreen("+\tM") + "\n"
        + "\tA\n"
        + "\tB\n"
        + this.getStringWithRed("-\tM") + "\n"
        + this.getStringWithGreen("+\tN") + "\n"
        + "\tA\n"
        + "\tB\n";
    Assert.assertEquals(expectedDiffString, diffString);
  }



  private String getStringWithRed(String str) {
    return "\033[31m" + str + "\033[0m";
  }


  private String getStringWithGreen(String str) {
    return "\033[32m" + str + "\033[0m";
  }
}
