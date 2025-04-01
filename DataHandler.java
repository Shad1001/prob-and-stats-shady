import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {

    // A simple inner class to represent an (x, y) point.
    static class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + ", " + y;
        }
    }

    /**
     * Loads xy data from a CSV file.
     * Assumes each row has two columns: x and y.
     *
     * @param filePath The path to the CSV file.
     * @return A List of Point objects.
     */
    public static List<Point> loadCSV(String filePath) {
        List<Point> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line by commas.
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    try {
                        double x = Double.parseDouble(parts[0].trim());
                        double y = Double.parseDouble(parts[1].trim());
                        data.add(new Point(x, y));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return data;
    }

    /**
     * Exports the list of Point data to a CSV file.
     * Each Point's x and y values will be written as a row.
     *
     * @param data      The list of Point data to export.
     * @param filePath  The path to the output CSV file.
     */
    public static void exportCSV(List<Point> data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Point point : data) {
                writer.write(point.x + ", " + point.y + "\n");
            }
            System.out.println("Data successfully exported to " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
