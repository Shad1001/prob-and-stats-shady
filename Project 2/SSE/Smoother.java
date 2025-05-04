import java.io.*;
import java.util.*;

public class Smoother {
    public static void main(String[] args) throws IOException {
        // smooth range:
        int window = 5;   

        // 1) load the salted data
        List<Point> data = loadCSV("salted_data.csv");
        int n = data.size();

        // 2) smooth 
        List<Point> out = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int start = Math.max(0, i - window);
            int end   = Math.min(n - 1, i + window);

            double sum = 0;
            for (int j = start; j <= end; j++) {
                sum += data.get(j).y;
            }
            double avg = sum / (end - start + 1);
            out.add(new Point(data.get(i).x, avg));
        }

        // 3) smoothed series
        writeCSV("smoothed_data.csv", out);
        System.out.println("Wrote smoothed_data.csv (" + out.size() + " points, window=" + window + ").");
    }

    // CSV I/O for XY points
    static List<Point> loadCSV(String file) throws IOException {
        List<Point> pts = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            for (String L; (L = r.readLine()) != null; ) {
                String[] tok = L.trim().split(",");
                if (tok.length < 2) continue;
                pts.add(new Point(
                    Double.parseDouble(tok[0]),
                    Double.parseDouble(tok[1])
                ));
            }
        }
        return pts;
    }

    static void writeCSV(String file, List<Point> pts) throws IOException {
        try (PrintWriter w = new PrintWriter(new FileWriter(file))) {
            for (Point p : pts) {
                w.printf(Locale.US, "%f,%f%n", p.x, p.y);
            }
        }
    }

    static class Point {
        final double x, y;
        Point(double x, double y) { this.x = x; this.y = y; }
    }
}
