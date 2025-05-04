import java.io.IOException;
import java.util.*;

public class Smoother2 {
    public static void main(String[] args) throws IOException {
        // 1) read salted
        List<DataHandler2.Point> salted = DataHandler2.readCSV("salted.csv");

        // 2) simple moving average
        int w = 7;  // window size
        List<DataHandler2.Point> smooth = new ArrayList<>(salted.size());
        for (int i = 0; i < salted.size(); i++) {
            int lo = Math.max(0, i - w/2);
            int hi = Math.min(salted.size(), i + w/2 + 1);
            double sum = 0;
            for (int k = lo; k < hi; k++) sum += salted.get(k).y;
            smooth.add(new DataHandler2.Point(salted.get(i).x, sum / (hi - lo)));
        }

        // 3) write it out
        DataHandler2.writeCSV("smoothed.csv", smooth);
        System.out.println(">> wrote smoothed.csv");

        // 4) plot
        DataPlotter2.plotSeries("Smoothed", smooth);
    }
}
