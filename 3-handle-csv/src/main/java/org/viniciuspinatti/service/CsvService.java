package org.viniciuspinatti.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.viniciuspinatti.utils.CsvUtils;
import org.viniciuspinatti.utils.RandomUtils;

/**
 * This implementation works, but needs a lot of Java memory heap space. With a common -Xmx2g value
 * it will not work for a 20mi of lines (about 518 mb file). It is necessary to increase for a
 * -Xmx5g parameter. The main problem is try to allocate the full array in a list before populate
 * the file.
 */
public class CsvService {
  private static final String FILE_NAME = "csv_file.csv";

  private List<String[]> buildArrayLines(int numOfLines) {
    List<String[]> dataLines = new ArrayList<>(numOfLines);
    for (int i = 0; i < numOfLines; i++) {
      dataLines.add(
          new String[] {
            String.valueOf(i + 1),
            RandomUtils.createRandomString(5, 12),
            String.valueOf(RandomUtils.generateRandomNumberBetweenMinAndMax(0, 120)),
            String.valueOf(RandomUtils.generateRandomNumberBetweenMinAndMax(0.25f, 2.50f))
          });
    }

    return dataLines;
  }

  public void createCsvFileWithRandomData(int numOfLines) {
    System.out.println("Creating csv file using array with all lines approach");
    List<String[]> dataLines = buildArrayLines(numOfLines);
    File csvOutputFile = new File(FILE_NAME);

    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
      dataLines.stream().map(CsvUtils::convertToCSV).forEach(pw::println);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Error trying to generate csv file");
    }

    System.out.println("Finished csv file using array with all lines approach");
  }
}
