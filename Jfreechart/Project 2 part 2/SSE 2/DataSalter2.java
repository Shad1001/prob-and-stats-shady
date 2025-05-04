import org.apache.commons.math3.distribution.NormalDistribution;
import java.io.IOException;
import java.util.*;

public class DataSalter2 {
    public static void main(String[] args) throws IOException {
        // 1) read raw
        List<DataHandler2.Point> raw = DataHandler2.readCSV("data.csv");

        // 2) salt with N(0,1000^2)
        NormalDistribution nd = new NormalDistribution(0, 1000);
        List<DataHandler2.Point> salted = new ArrayList<>(raw.size());
        for (DataHandler2.Point p : raw) {
            double y2 = p.y + nd.sample();
            salted.add(new DataHandler2.Point(p.x, y2));
        }

        // 3) write it out
        DataHandler2.writeCSV("salted.csv", salted);
        System.out.println(">> wrote salted.csv");

        // 4) plot
        DataPlotter2.plotSeries("Salted", salted);
    }
}
