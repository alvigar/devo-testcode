package devo.termfrequency;

import devo.exceptions.DirectoryException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

public class TermFrequency
{
  public TermFrequency(String directory, Integer top, Integer period, String[] terms)
  {
    Thread thread = new Thread(new TermFrequecyThread(directory, top, period, terms));
    thread.start();
  }

  class TermFrequecyThread implements Runnable
  {
    private String directory;

    private Integer top;

    private Integer period;

    private Set<String> setTerms;

    private Map<String, Double> termFrequencyFiles;

    protected TermFrequecyThread(String directory, Integer top, Integer period, String[] terms)
    {
      this.directory = directory;
      this.top = top;
      this.period = period;
      this.setTerms = new HashSet<String>(Arrays.asList(terms));
      this.termFrequencyFiles = new HashMap<>();
    }

    @Override
    public synchronized void run()
    {
      try
      {
        do
        {
          File directory = new File(this.directory);
          if (!directory.isDirectory())
          {
            throw new DirectoryException(this.directory + " is not a directory");
          }
          try (Stream<Path> walk = Files.walk(Paths.get(this.directory)))
          {
            List<String> result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(
                Collectors.toList());

            for (String pathFile : result)
            {
              if (!termFrequencyFiles.containsKey(pathFile))
              {
                termFrequencyFiles.put(pathFile, termFrequencyInFile(pathFile));
              }
            }
            printFiles();
          }
          sleep(this.period);
        }
        while (true);
      }
      catch (InterruptedException | DirectoryException | IOException e)
      {
        e.printStackTrace();
      }
    }

    private synchronized void printFiles()
    {
      termFrequencyFiles.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                        .limit(this.top).forEach(x -> System.out
          .println(x.getKey() + " - " + x.getValue()));
      System.out.println("----------");
    }

    private Double termFrequencyInFile(String path) throws IOException
    {
      Map<String, Long> totalWords = Files.lines(Paths.get(path))
                                          .flatMap(line -> Arrays.stream(line.trim().split(" ")))
                                          .map(
                                              word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase()
                                                          .trim())
                                          .filter(word -> !word.isEmpty())
                                          .collect(Collectors.groupingBy(Function.identity(),
                                                                         Collectors.counting()));

      Long nWords = totalWords.values().stream().mapToLong(Long::longValue).sum();
      Long nTermsWords = totalWords.entrySet().stream()
                                   .filter(x -> this.setTerms.contains(x.getKey()))
                                   .mapToLong(x -> x.getValue()).sum();

      return (nWords > 0) ? Double.valueOf(nTermsWords) / Double.valueOf(nWords) : 0;
    }
  }
}
