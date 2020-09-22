package devo.termfrequency;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.system.OutputCaptureRule;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TermFrequencyTest
{
  @Rule
  public OutputCaptureRule outputCapture = new OutputCaptureRule();

  @Test
  public void termFrequencyTest()
  {
    try
    {
      new TermFrequency("src/test/resources/prueba", 5, 300, new String[] {"test", "devo", "code"});
      Thread.sleep(2000L);
      String[] logOutArray = outputCapture.toString().split("----------");
      assertEquals(
          "src\\test\\resources\\prueba\\p2 - 0.42857142857142855" + System.lineSeparator() +
              "src\\test\\resources\\prueba\\p1 - 0.42857142857142855" + System.lineSeparator() +
              "src\\test\\resources\\prueba\\p7 - 0.41333333333333333" + System.lineSeparator() +
              "src\\test\\resources\\prueba\\p6 - 0.41333333333333333" + System.lineSeparator() +
              "src\\test\\resources\\prueba\\p8 - 0.41333333333333333" + System.lineSeparator(),
          logOutArray[0]);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      fail();
    }
  }
}
