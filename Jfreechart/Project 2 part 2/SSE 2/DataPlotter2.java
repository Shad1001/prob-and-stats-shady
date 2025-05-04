import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.*;
import java.io.IOException;
import java.util.*;

import static java.lang.Math.pow;

public class DataPlotter2 {
  public static void main(String[] args) throws IOException {
    // 1) Generate the raw function
    List<DataHandler2.Point> pts = new ArrayList<>();
    for (double x = 0; x <= 10; x += 0.1) {
      pts.add(new DataHandler2.Point(x, pow(x, 4)));
    }

    // 2) Export to CSV
    DataHandler2.writeCSV("data.csv", pts);
    System.out.println("wrote data.csv");

    // 3) Show it on‑screen
    plotSeries("Raw x^4", pts);
  }

  public static void plotSeries(String title, List<DataHandler2.Point> pts) {
    XYSeries s = new XYSeries(title);
    for (var p : pts) s.add(p.x, p.y);

    XYSeriesCollection ds = new XYSeriesCollection(s);
    JFreeChart c = ChartFactory.createScatterPlot(
      title, "x", "y", ds,
      PlotOrientation.VERTICAL, true, true, false
    );
    ChartFrame f = new ChartFrame("Plot: " + title, c);
    f.pack();
    f.setVisible(true);
  }
}
