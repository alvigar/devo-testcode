package devo.palindrome;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Palindrome
{

  public static boolean isPalindrome(String s)
  {
    if (s == null || s.isEmpty()){
      return true;
    }
    List<Character> charArray = s.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
    Collections.reverse(charArray);
    String inverse = charArray.stream().map(String::valueOf).collect(Collectors.joining());
    return s.equals(inverse);
  }
}
