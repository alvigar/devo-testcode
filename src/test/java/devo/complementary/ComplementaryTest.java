package devo.complementary;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ComplementaryTest
{

  @Test
  public void testNullEmptyArrayComplementary()
  {
    assertEquals(new ArrayList<>(), Complementary.findKComplementary(null, 1));
    assertEquals(new ArrayList<>(), Complementary.findKComplementary(new Integer[] {}, 1));
  }

  @Test
  public void testComplementary()
  {
    try
    {
      BufferedReader infilePalindromes = new BufferedReader(
          new FileReader("src/test/resources/complementary.file"));

      List<Integer[]> listArray = new ArrayList<>();
      List<Integer> listK = new ArrayList<>();
      List<List<ComplementaryPair>> listComplementary = new ArrayList<>();

      String str;
      while ((str = infilePalindromes.readLine()) != null)
      {
        String[] values = str.split(";");
        listK.add(Integer.valueOf(values[0]));
        List<Integer> listIntegers = Arrays.asList(values[1].split(",")).stream()
                                           .map(e -> Integer.parseInt(e.trim()))
                                           .collect(Collectors.toList());
        listArray.add(listIntegers.toArray(new Integer[listIntegers.size()]));
      }
      ObjectMapper o = new ObjectMapper();

      listComplementary = o.readValue(new File("src/test/resources/complementary.result.file"),
                                      new TypeReference<List<List<ComplementaryPair>>>()
                                      {
                                      });

      for (int i = 0; i < listArray.size(); i++)
      {
        assertEquals(listComplementary.get(i),
                     Complementary.findKComplementary(listArray.get(i), listK.get(i)));
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
      fail();
    }
  }

}
