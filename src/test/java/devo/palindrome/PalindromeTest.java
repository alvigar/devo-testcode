package devo.palindrome;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PalindromeTest
{

  @Test
  public void testNullEmptyString()
  {
    assertEquals(true, Palindrome.isPalindrome(null));
    assertEquals(true, Palindrome.isPalindrome(""));
  }

  @Test
  public void testPalindromes()
  {
    try
    {
      BufferedReader infilePalindromes = new BufferedReader(
          new FileReader("src/test/resources/palindromes.file"));
      BufferedReader infilePalindromesResult = new BufferedReader(
          new FileReader("src/test/resources/palindromes.result.file"));

      List<String> listPalindromes = new ArrayList<>();
      List<Boolean> listPalindromesResult = new ArrayList<>();

      String str;
      while((str = infilePalindromes.readLine()) != null){
        listPalindromes.add(str);
      }
      while((str = infilePalindromesResult.readLine()) != null){
        listPalindromesResult.add(Boolean.parseBoolean(str));
      }

      for(int i = 0; i<listPalindromes.size(); i++){
        assertEquals(listPalindromesResult.get(i), Palindrome.isPalindrome(listPalindromes.get(i)));
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
      fail();
    }
  }

}
