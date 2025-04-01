import java.util.ArrayList;
import java.util.List;

public class Smoother {

    public static List<DataHandler.Point> smoothData(List<DataHandler.Point> data, int windowSize) {
        List<DataHandler.Point> smoothedData = new ArrayList<>(data);
        for (int i = 0; i < data.size(); i++) {
            int left = Math.max(0, i - windowSize); // Ensure we don't go out of bounds on the left.
            int right = Math.min(data.size() - 1, i + windowSize); // Ensure we don't go out of bounds on the right.

            double sum = 0;
            int count = 0;
            // Sum all the y-values in the window
            for (int j = left; j <= right; j++) {
                sum += data.get(j).y;
                count++;
            }

            // Compute the average y-value and set it for the current point
            double averageY = sum / count;
            smoothedData.get(i).y = averageY;
        }
        return smoothedData;
    }

    public static void main(String[] args) {
        // Specify the CSV file path.
        String filePath = "data.csv";  // Update the file path if needed.
        List<DataHandler.Point> data = DataHandler.loadCSV(filePath);  // Using DataHandler to load the data

        if (data.isEmpty()) {
            System.out.println("No data loaded. Please check the CSV file.");
            return;
        }

        // Smooth the data using the specified window size.
        List<DataHandler.Point> smoothedData = smoothData(data, 2);  // Using a window size of 2

        // Export the smoothed data using DataHandler.
        DataHandler.exportCSV(smoothedData, "smoothed_data.csv");  // Export to a new CSV file
    }
}
