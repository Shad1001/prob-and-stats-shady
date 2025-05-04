import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class DataHandler {
    /** Simple (x,y) holder. */
    public static class Point {
        public double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
        @Override public String toString() { return x + "," + y; }
    }

    /**
     * Reads a two‐column CSV (no header) into a List<Point>.
     * @param path input file path
     * @return list of Points
     */
    public static List<Point> readCSV(String path) {
        List<Point> pts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tok = line.trim().split(",");
                if (tok.length < 2) continue;
                double x = Double.parseDouble(tok[0]);
                double y = Double.parseDouble(tok[1]);
                pts.add(new Point(x,y));
            }
        } catch (IOException e) {
            System.err.println("readCSV error: " + e.getMessage());
        }
        return pts;
    }

    /**
     * Writes a List<Point> out as a two‐column CSV (no header).
     * @param path output file path
     * @param pts  data to write
     */
    public static void writeCSV(String path, List<Point> pts) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (Point p : pts) pw.println(p.x + "," + p.y);
        } catch (IOException e) {
            System.err.println("writeCSV error: " + e.getMessage());
        }
    }
}
