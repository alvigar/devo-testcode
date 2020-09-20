package devo;

import devo.complementary.Complementary;
import devo.complementary.ComplementaryPair;
import devo.palindrome.Palindrome;
import devo.termfrequency.TermFrequency;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class DevoApp
{
  public static void main(String[] args)
  {
    if (args[0].equals("-P"))
    {
      for (int i = 1; i < args.length; i++)
      {
        System.out.println(args[i] + " is palindrome: " + Palindrome.isPalindrome(args[i]));
      }
    }
    else if (args[0].equals("-C"))
    {
      for (int i = 1; i < args.length; i = i + 2)
      {
        List<Integer> listIntegers = Arrays.asList(args[i + 1].split(",")).stream()
                                           .map(e -> Integer.parseInt(e.trim()))
                                           .collect(Collectors.toList());
        Integer[] A = listIntegers.toArray(new Integer[listIntegers.size()]);
        List<ComplementaryPair> list = Complementary
            .findKComplementary(A, Integer.parseInt(args[i]));
        System.out
            .println(
                "Complementary of A = " + args[i + 1] + " and K = " + Integer.parseInt(args[i]));
        for (ComplementaryPair c : list)
        {
          System.out.println("\t - " + c.toString());
        }

      }
    }
    else if (args[0].equals("-T"))
    {
      List<String> arguments = Arrays.asList(args);
      String directory = arguments.get(arguments.indexOf("-d") + 1);
      Integer top = Integer.valueOf(arguments.get(arguments.indexOf("-n") + 1));
      Integer period = Integer.valueOf(arguments.get(arguments.indexOf("-p") + 1));
      String[] terms = arguments.get(arguments.indexOf("-t") + 1).split(" ");
      new TermFrequency(directory, top, period, terms);
    }
    else
    {
      System.out.println("Not valid option");
    }
  }
}
