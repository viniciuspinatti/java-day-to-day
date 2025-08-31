package org.viniciuspinatti.service;

import java.util.ArrayList;
import java.util.List;
import org.viniciuspinatti.utils.RandomUtils;

public class CsvService {
  public static List<String[]> buildArrayLines(int numOfLines) {
    List<String[]> dataLines = new ArrayList<>();
    for (int i = 1; i < numOfLines; i++) {
      dataLines.add(
          new String[] {
            String.valueOf(i),
            RandomUtils.createRandomString(),
            String.valueOf(RandomUtils.generateRandomNumberBetweenMinAndMax(0, 120)),
            String.valueOf(RandomUtils.generateRandomNumberBetweenMinAndMax(0.25f, 2.50f))
          });
    }

    return dataLines;
  }
}
