import java.util.List;

public class DataPlotter {

    public static void exportData(List<DataHandler.Point> data) {
        // Export the data using DataHandler's export method.
        DataHandler.exportCSV(data, "exported_data.csv");
    }
}
