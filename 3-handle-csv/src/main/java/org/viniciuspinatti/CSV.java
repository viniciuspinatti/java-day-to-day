package org.viniciuspinatti;

import java.io.IOException;
import org.viniciuspinatti.service.CsvChunkService;

public class CSV {
  public static void main(String[] args) throws IOException {
    // You can uncomment this approach, but needs to increase the memory heap to work.
    //    CsvService csvService = new CsvService();
    //    csvService.createCsvFileWithRandomData(20_000_000);
    CsvChunkService csvChunkService = new CsvChunkService();
    csvChunkService.createCsvFileWithRandomData(20_000_000, true);
  }
}
