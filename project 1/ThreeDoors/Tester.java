/**
 * The Tester class serves as an alternative entry point to run the DoorsMonte simulation.
 * It instantiates the DoorsMonte class and executes the simulation for both strategies,
 * printing out the win percentages for the no-swap and swap scenarios.
 */
public class Tester {
    public static void main(String[] args) {
        // Start the DoorsMonte simulation.
        DoorsMonte dm = new DoorsMonte();
        // Execute the simulation with no swap strategy and print the resulting win percentage.
        System.out.println("No swap win percentage: " + dm.monteDoors(10000, false));
        // Execute the simulation with swap strategy and print the resulting win percentage.
        System.out.println("Swap win percentage: " + dm.monteDoors(10000, true));
    }
}
