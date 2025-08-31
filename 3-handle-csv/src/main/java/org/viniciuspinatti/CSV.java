package org.viniciuspinatti;

import java.util.List;
import org.viniciuspinatti.service.CsvService;

public class CSV {
  public static void main(String[] args) {
    List<String[]> lines = CsvService.buildArrayLines(1000);

    lines.forEach(System.out::println);
  }
}
