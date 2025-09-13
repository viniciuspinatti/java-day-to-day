package org.viniciuspinatti.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.viniciuspinatti.utils.CsvUtils;
import org.viniciuspinatti.utils.RandomUtils;

/**
 * This implementation is better and needs less memory. The main reason is: it will not populate the
 * list with all lines in one iteration, it will use chunks of data. So the max quantity of lines in
 * each iteration to populate the file will be 100k. After each iteration, the list with 100k can be
 * removed from the memory. It works even with a -Xmx1g JVM parameter.
 */
public class CsvChunkService {
  private static final String FILE_NAME = "csv_file.csv";

  private List<String[]> buildArrayLines(int chunkPart) {
    final int chunkSize = 100_000;
    List<String[]> dataLines = new ArrayList<>(chunkSize);
    final int indexAccordingChunkPart = chunkSize * chunkPart;

    for (int i = 0; i < chunkSize; i++) {
      dataLines.add(
          new String[] {
            String.valueOf(1 + i + indexAccordingChunkPart),
            RandomUtils.createRandomString(5, 12),
            String.valueOf(RandomUtils.generateRandomNumberBetweenMinAndMax(0, 120)),
            String.valueOf(RandomUtils.generateRandomNumberBetweenMinAndMax(0.25f, 2.50f))
          });
    }

    return dataLines;
  }

  public void createCsvFileWithRandomData(int numOfLines, boolean shouldRemoveFileIfExists)
      throws IOException {
    int lineCount = 0;
    final int chunkSizeOfLines = 100_000;

    Path filePath = Paths.get(FILE_NAME);

    if (shouldRemoveFileIfExists) {
      Files.deleteIfExists(filePath);
    }

    File csvOutputFile = new File(FILE_NAME);

    int chunkPart = 0;

    while (lineCount < numOfLines) {
      List<String[]> dataLines = buildArrayLines(chunkPart);

      if (Files.exists(filePath)) {
        try (FileWriter fileWriter = new FileWriter(FILE_NAME, true)) {
          fileWriter.append(
              dataLines.stream()
                  .map(line -> CsvUtils.convertToCSV(line) + System.lineSeparator())
                  .collect(Collectors.joining()));
        } catch (Exception e) {
          throw new RuntimeException("Error trying to append to csv file");
        }
      } else {
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
          dataLines.stream().map(CsvUtils::convertToCSV).forEach(pw::println);
        } catch (FileNotFoundException e) {
          throw new RuntimeException("Error trying to generate csv file");
        }
      }

      lineCount += chunkSizeOfLines;
      chunkPart++;
    }
  }
}
