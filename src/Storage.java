import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Storage {
    // References : https://www.baeldung.com/java-csv for csv convention conversion

    public static final String CSV_FILE_NAME = "game_data.csv";
    public static File csvOutputFile;

    public Storage() {
        csvOutputFile = new File(CSV_FILE_NAME);
    }

    public void writeToFile(String[] data) {
        List<String[]> dataLines = new ArrayList<>();
        dataLines.add(data);
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
