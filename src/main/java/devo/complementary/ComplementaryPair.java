package devo.complementary;

import java.util.Objects;

public class ComplementaryPair
{
  private int x1;
  private int x2;

  public ComplementaryPair(){

  }

  public ComplementaryPair(Integer x1, Integer x2)
  {
    this.x1 = x1;
    this.x2 = x2;
  }

  public void setX1(int x1)
  {
    this.x1 = x1;
  }

  public int getX1()
  {
    return x1;
  }

  public void setX2(int x2)
  {
    this.x2 = x2;
  }

  public int getX2()
  {
    return x2;
  }

  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    ComplementaryPair that = (ComplementaryPair) o;
    return x1 == that.x1 &&
        x2 == that.x2;
  }

  @Override public int hashCode()
  {
    return Objects.hash(x1, x2);
  }

  @Override public String toString()
  {
    return "{" +
        "x1=" + x1 +
        ", x2=" + x2 +
        '}';
  }
}
