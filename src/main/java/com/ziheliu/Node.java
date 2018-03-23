package com.ziheliu;

public class Node {
  private int coordinateX;
  private int coordinateY;

  public Node(int coordinateX, int coordinateY) {
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
  }

  public int getCoordinateX() {
    return coordinateX;
  }

  public int getCoordinateY() {
    return coordinateY;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Node)) {
      return false;
    }
    Node node = (Node) obj;
    return node.getCoordinateX() == this.getCoordinateX()
        && node.getCoordinateY() == this.getCoordinateY();
  }
}
