package com.ziheliu;

import org.junit.Assert;
import org.junit.Test;

public class MyArrayTest {
  @Test
  public void positiveNumTest() {
    MyArray array = new MyArray(10);
    array.set(1, 10);
    Assert.assertEquals(10, array.get(1));
  }

  @Test
  public void negativeNumTest() {
    MyArray array = new MyArray(10);
    array.set(1, -10);
    Assert.assertEquals(-10, array.get(1));
  }
}
