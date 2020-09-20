package devo.complementary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Complementary
{
  public static List<ComplementaryPair> findKComplementary(Integer[] A, int K)
  {
    if (A == null || A.length < 2)
    {
      return new ArrayList<>();
    }
    List<Integer> listInteger = Arrays.asList(A);

    return listInteger.stream().flatMap(
        e -> listInteger.subList(listInteger.indexOf(e) + 1, listInteger.size()).stream()
                        .map(x -> new ComplementaryPair(e, x))
                        .filter(x -> (x.getX1() + x.getX2()) == K))
                      .collect(Collectors.toList());
  }
}
