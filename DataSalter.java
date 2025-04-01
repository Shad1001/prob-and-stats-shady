import java.util.List;
import java.util.Random;

public class DataSalter {

    // Use the Point class from DataHandler
    public static List<DataHandler.Point> saltData(List<DataHandler.Point> data, double saltRange) {
        Random rand = new Random();
        for (DataHandler.Point p : data) {
            // Generate a random salt between -saltRange and +saltRange
            double salt = (rand.nextDouble() * 2 * saltRange) - saltRange;
            p.y += salt;
        }
        return data;
    }

    public static void main(String[] args) {
        // Specify the CSV file path
        String filePath = "data.csv";  // Update the file path if needed
        List<DataHandler.Point> data = DataHandler.loadCSV(filePath);  // Using DataHandler to load the data

        if (data.isEmpty()) {
            System.out.println("No data loaded. Please check the CSV file.");
            return;
        }

        // Salt the data by modifying only the y-values
        List<DataHandler.Point> saltedData = saltData(data, 5.0);  // Using a salt range of 5.0

        // Export the salted data using DataHandler
        DataHandler.exportCSV(saltedData, "salted_data.csv");
    }
}
