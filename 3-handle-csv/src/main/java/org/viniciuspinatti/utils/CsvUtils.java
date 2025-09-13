package org.viniciuspinatti.utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvUtils {
  public static String escapeSpecialCharacters(String data) {
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

  public static String convertToCSV(String[] data) {
    return Stream.of(data).map(CsvUtils::escapeSpecialCharacters).collect(Collectors.joining(";"));
  }
}
