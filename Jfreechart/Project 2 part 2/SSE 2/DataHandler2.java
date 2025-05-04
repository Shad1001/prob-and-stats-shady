import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class DataHandler2 {
  public static class Point {
    public final double x, y;
    public Point(double x, double y) { this.x = x; this.y = y; }
  }

  /** Read a two‑column CSV of doubles  */
  public static List<Point> readCSV(String path) throws IOException {
    var pts = new ArrayList<Point>();
    try (var lines = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
      String line;
      while ((line = lines.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) continue;
        String[] toks = line.split(",", -1);
        double x = Double.parseDouble(toks[0]);
        double y = Double.parseDouble(toks[1]);
        pts.add(new Point(x, y));
      }
    }
    return pts;
  }

  /** Write out a two‑column CSV of doubles */
  public static void writeCSV(String path, List<Point> pts) throws IOException {
    try (var out = Files.newBufferedWriter(Paths.get(path), StandardCharsets.UTF_8)) {
      for (Point p : pts) {
        out.write(p.x + "," + p.y);
        out.newLine();
      }
    }
  }
}
