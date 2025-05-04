import java.io.*;
import java.util.*;

public class DataSalter {
    // Salt Range
    static final double SALT_RANGE = 2000.0;

    public static void main(String[] args) throws IOException {
        // 1) load the original data
        List<Point> data = loadCSV("data.csv");

        // 2) add salt to y-values only
        List<Point> salted = new ArrayList<>();
        Random rnd = new Random();
        for (Point p : data) {
            // uniform in [–SALT_RANGE … +SALT_RANGE]
            double noise = (rnd.nextDouble() * 2 - 1) * SALT_RANGE;
            salted.add(new Point(p.x, p.y + noise));
        }

        
        writeCSV("salted_data.csv", salted);
        System.out.println("Wrote salted_data.csv with " + salted.size() + " points.");
    }

    // simple loader for two‑column CSV
    static List<Point> loadCSV(String filename) throws IOException {
        List<Point> pts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tok = line.trim().split(",");
                if (tok.length < 2) continue;
                double x = Double.parseDouble(tok[0]);
                double y = Double.parseDouble(tok[1]);
                pts.add(new Point(x, y));
            }
        }
        return pts;
    }

    // writer for two‑column CSV
    static void writeCSV(String filename, List<Point> pts) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Point p : pts) {
                pw.printf(Locale.US, "%f,%f%n", p.x, p.y);
            }
        }
    }

    //pair of doubles
    static class Point {
        final double x, y;
        Point(double x, double y) { this.x = x; this.y = y; }
    }
}
