package org.viniciuspinatti.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.viniciuspinatti.utils.RandomUtils;

public class CsvService {
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

  private String escapeSpecialCharacters(String data) {
    if (data == null) {
      throw new IllegalArgumentException("Input data cannot be null");
    }
    String escapedData = data.replaceAll("\\R", " ");
    if (escapedData.contains(",") || escapedData.contains("\"") || escapedData.contains("'")) {
      escapedData = escapedData.replace("\"", "\"\"");
      escapedData = "\"" + escapedData + "\"";
    }
    return escapedData;
  }

  private String convertToCSV(String[] data) {
    return Stream.of(data).map(this::escapeSpecialCharacters).collect(Collectors.joining(";"));
  }

  public void createCsvFileWithRandomData(int numOfLines) {
    List<String[]> dataLines = buildArrayLines(numOfLines);
    File csvOutputFile = new File("csv_file.csv");

    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
      dataLines.stream().map(this::convertToCSV).forEach(pw::println);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Error trying to generate csv file");
    }
  }
}
