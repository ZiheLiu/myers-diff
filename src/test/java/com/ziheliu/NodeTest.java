package com.ziheliu;

import org.junit.Assert;
import org.junit.Test;

public class NodeTest {
  @Test
  public void constructorTest() {
    int coordinateX = 1;
    int coordinateY = 2;
    Node node = new Node(coordinateX, coordinateY);

    Assert.assertEquals(coordinateX, node.getCoordinateX());
    Assert.assertEquals(coordinateY, node.getCoordinateY());
  }

  @Test
  public void equalsTestWithSameNode() {
    int coordinateX = 1;
    int coordinateY = 2;
    Node node = new Node(coordinateX, coordinateY);
    Node node2 = new Node(coordinateX, coordinateY);

    Assert.assertTrue(node.equals(node2));
  }

  @Test
  public void equalsTestWithDifferentNode() {
    Node node = new Node(1, 2);
    Node node2 = new Node(2, 2);
    Assert.assertFalse(node.equals(node2));
  }

  @Test
  public void equalsTestWithNotNode() {
    Node node = new Node(1, 2);
    String str = "";
    Assert.assertFalse(node.equals(str));
  }

  @Test
  public void equalsTestWithNull() {
    Node node = new Node(1, 2);
    Assert.assertFalse(node.equals(null));
  }
}
