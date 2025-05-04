import java.util.LinkedList;
import java.util.Random;

// This class experiments with a simple hash map implementation using separate chaining
public class HashMapExperiment {

    // Array of linked lists to hold strings at each bucket
    LinkedList<String>[] data;

    int collisions = 0;

    // Constructor: initializes the array to the given size and creates an empty list at each index
    public HashMapExperiment(int size) {
        data = new LinkedList[size];
        for (int i = 0; i < size; i++)
            data[i] = new LinkedList<>();  // set up each bucket
    }

    public int dumbHash(String value) {
        // Modulo by the number of buckets to keep index in range
        return value.length() % data.length;
    }

    // Adds a string into our hash map, incrementing collision count if bucket isn't empty
    public void add(String value) {
        int index = dumbHash(value);
        // If there's already something here, that's a collision
        if (!data[index].isEmpty())
            collisions++;
        data[index].add(value); 
    }

    // Checks whether a given string is in the map
    public boolean contains(String value) {
        int index = dumbHash(value);
        return data[index].contains(value);
    }

    // Returns how many collisions happened during inserts
    public int getCollisions() {
        return collisions;
    }

    public static String randomString(int length, Random rand) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++)
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        return sb.toString();
    }

    // Main method to test performance of the hash map at different sizes
    public static void main(String[] args) {

        int[] sizesToTest = {1000, 5000, 10000, 20000, 50000};
        int dataSize = 500000; // I inserted half a million strings BIG DATA SET!

        Random rand = new Random();
        String[] dataset = new String[dataSize];

        // Build the dataset of randomly generated strings, lengths between 10 and 29
        for (int i = 0; i < dataSize; i++)
            dataset[i] = randomString(10 + rand.nextInt(20), rand);

        // Got a nice header for my results table
        System.out.printf("%-10s %-20s %-20s %-15s%n", "MapSize", "InsertTime(ms)", "SearchTime(ms)", "Collisions");

        // Test each map size
        for (int mapSize : sizesToTest) {
            HashMapExperiment map = new HashMapExperiment(mapSize);

            // Measure how long inserts take
            long insertStart = System.currentTimeMillis();
            for (String s : dataset)
                map.add(s);
            long insertEnd = System.currentTimeMillis();

            // Measure search time by checking every 100th element
            long searchStart = System.currentTimeMillis();
            for (int i = 0; i < dataSize; i += 100) {
                map.contains(dataset[i]);  
            }
            long searchEnd = System.currentTimeMillis();

            // Output the results for this map size
            System.out.printf("%-10d %-20d %-20d %-15d%n",
                    mapSize,
                    (insertEnd - insertStart),
                    (searchEnd - searchStart),
                    map.getCollisions());
        }
    }
}
