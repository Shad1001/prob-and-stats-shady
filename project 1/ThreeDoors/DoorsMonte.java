import java.util.Random;

/**
 * This class simulates the famous Monty Hall problem.
 * It randomly places a prize behind one of three doors, and then simulates a player's choice.
 * Depending on whether the player decides to switch or not, the simulation tallies up wins.
 */
public class DoorsMonte {
    
    /**
     * Runs the Monty Hall simulation over a given number of iterations.
     *
     * @param runtime The number of times we run the simulation.
     * @param swap    If true, the player switches their door choice; if false, they stick with their first pick.
     * @return The percentage of wins out of all simulation runs.
     */
    public double monteDoors(int runtime, boolean swap) {
        // Keep track of how many times the player wins.
        int wins = 0;
        // Create a random number generator to choose doors at random.
        Random rand = new Random();
        
        // Loop through the simulation for the specified number of times.
        for (int i = 0; i < runtime; i++) {
            // Randomly decide which door (0, 1, or 2) hides the prize.
            int prize = rand.nextInt(3);
            // The player's first choice is also picked at random.
            int choice = rand.nextInt(3);
            
            // Check if the player wins
            // If they're switching, they win when their initial choice was NOT the prize door.
            // If they're not switching, they win only if their first choice was the prize door.
            if ((swap && choice != prize) || (!swap && choice == prize)) {
                wins++;  // Increase win count if the condition is met.
            }
        }
        
        // Calculate the win rate as a percentage.
        double winPercent = (double) wins / runtime * 100;
        // Output the win percentage with a clear label for swapping or not.
        System.out.println("Win percentage (" + (swap ? "swap" : "no swap") + "): " + winPercent + "%");
        return winPercent;
    }
    
    /**
     * The main method to run the simulation directly from this class.
     * It demonstrates both strategies: sticking with the initial choice and switching.
     */
    public static void main(String[] args) {
        // Create an instance of our simulation class.
        DoorsMonte dm = new DoorsMonte();
        // Run the simulation without swapping first.
        dm.monteDoors(10000, false);
        // Then run the simulation with swapping.
        dm.monteDoors(10000, true);
    }
}
