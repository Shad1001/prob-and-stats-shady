import java.util.ArrayList;

/**
 * The BirthdayExperiment class simulates the birthday paradox.
 * It repeatedly creates a group of people and checks if at least two share the same birthday.
 * The final result is reported as the percentage of experiments where a duplicate birthday was found.
 */
public class BirthdayExperiment {
    // Counter for the number of experiments (or "hits") where a duplicate birthday is found.
    int hitCount = 0;

    /**
     * Executes the birthday experiment.
     *
     * @param numPeople  The number of people in each trial.
     * @param iterations The number of experiment iterations to perform.
     * @return The percentage of iterations in which at least one duplicate birthday was detected.
     */
    public double execute(int numPeople, int iterations) {
        // Run the experiment iterations times
        for (int cycle = 0; cycle < iterations; cycle++) {
            // For each iteration, initialize a new group to simulate a trial.
            ArrayList<Person> group = new ArrayList<Person>();
            
            // Create a group of numPeople, each represented by a new Person object.
            for (int idx = 0; idx < numPeople; idx++) {
                group.add(new Person());
            }
            
            // Flag to indicate if a duplicate birthday is found in the current iteration.
            boolean duplicateFound = false;
            
            // Compare each unique pair of persons to check for a shared birthday.
            for (int i = 0; i < group.size() && !duplicateFound; i++) {
                for (int j = i + 1; j < group.size(); j++) {
                    // If the birthdays match, count it as a hit and stop further comparisons.
                    if (group.get(i).fetchBirthday() == group.get(j).fetchBirthday()) {
                        hitCount++;         // Increment the hit counter for a successful duplicate find.
                        duplicateFound = true;  // Set the flag to true to break out of the loops early.
                        break;              // Exit the inner loop since a duplicate was found.
                    }
                }
            }
        }
        
        // Calculate the percentage of iterations that resulted in a duplicate birthday.
        double percentage = (hitCount * 100.0) / iterations;
        System.out.println("Percentage: " + percentage + "%");
        return percentage;
    }
}
