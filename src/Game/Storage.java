package Game;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Storage {
    // References : https://www.baeldung.com/java-csv for csv convention conversion

    private final String CSV_FILE_NAME = "game_data.csv";
    private File csvOutputFile;
    private List<String[]> dataLines;

    public Storage() {
        csvOutputFile = new File(CSV_FILE_NAME);
        dataLines = new ArrayList<>();
        //TODO: add headers for data to be stored in csv
        String[] header = new String[] {};
        dataLines.add(header);
    }

    // prepares data to be written into csv file
    public void prepareToWrite(String[] data) {
        dataLines.add(data);
    }

    // Writes all game data into csv file.
    // MUST be called at the end of the game simulation.
    public void closeWriter() {
        try {
            givenDataArray_whenConvertToCSV_thenOutputCreated(dataLines);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    private String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    private void givenDataArray_whenConvertToCSV_thenOutputCreated(List<String[]> dataLines) throws IOException {
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }
}
