package org.viniciuspinatti.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.viniciuspinatti.utils.CsvUtils;
import org.viniciuspinatti.utils.RandomUtils;

/**
 * This implementation works, but needs a lot of Java memory heap space. For about 20mi of lines
 * (~518 mb file) it will require at least -Xmx5g parameter to avoid memory heap errors. The main
 * problem is try to allocate the full array in a list before populate the file.
 */
public class CsvService {
  private static final String FILE_NAME = "output/csv_file.csv";

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

  public void createCsvFileWithRandomData(int numOfLines) throws IOException {
    System.out.println("Creating csv file using array with all lines approach");
    List<String[]> dataLines = buildArrayLines(numOfLines);

    File csvOutputFile = new File(FILE_NAME);

    if (csvOutputFile.getParentFile() != null) {
      csvOutputFile.getParentFile().mkdirs();
    }

    if (csvOutputFile.createNewFile()) {
      System.out.println("File created: " + csvOutputFile.getAbsolutePath());
    } else {
      System.out.println(
          "File already exists or could not be created: " + csvOutputFile.getAbsolutePath());
    }

    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
      dataLines.stream().map(CsvUtils::convertToCSV).forEach(pw::println);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Error trying to generate csv file");
    }

    System.out.println("Finished csv file using array with all lines approach");
  }
}
