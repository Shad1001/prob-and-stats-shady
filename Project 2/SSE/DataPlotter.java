import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataPlotter {
    public static void main(String[] args) throws IOException {
        // generate x from 0.0 to 10.0 in steps of 0.1
        List<DataHandler.Point> pts = new ArrayList<>();
        for (double x = 0.0; x <= 10.0; x += 0.1) {
            double y = Math.pow(x, 4);
            pts.add(new DataHandler.Point(x, y));
        }
        // write (x, x^4) out as data.csv
        DataHandler.writeCSV("data.csv", pts);
    }
}
