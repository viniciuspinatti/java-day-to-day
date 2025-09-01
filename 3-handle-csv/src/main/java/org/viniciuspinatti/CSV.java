package org.viniciuspinatti;

import org.viniciuspinatti.service.CsvService;

public class CSV {
  public static void main(String[] args) {
    CsvService csvService = new CsvService();
    csvService.createCsvFileWithRandomData(20_000_000);
  }
}
