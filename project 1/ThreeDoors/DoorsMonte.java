import java.util.Random;

public class DoorsMonte {
    public double monteDoors(int runtime, boolean swap) {
        int wins = 0;
        Random rand = new Random();
        for (int i = 0; i < runtime; i++) {
            int prize = rand.nextInt(3);    // Random door 0, 1, or 2 has the prize
            int choice = rand.nextInt(3);     // Player's initial choice
            // When swapping, win if initial choice is not the prize.
            // When not swapping, win if initial choice is the prize.
            if ((swap && choice != prize) || (!swap && choice == prize))
                wins++;
        }
        double winPercent = (double) wins / runtime * 100;
        System.out.println("Win percentage (" + (swap ? "swap" : "no swap") + "): " + winPercent + "%");
        return winPercent;
    }
    
    public static void main(String[] args) {
        DoorsMonte dm = new DoorsMonte();
        dm.monteDoors(10000, false);
        dm.monteDoors(10000, true);
    }
}
