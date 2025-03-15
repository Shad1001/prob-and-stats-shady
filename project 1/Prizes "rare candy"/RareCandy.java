import java.util.*;

public class RareCandy {
    // Numberes that defines our deck and simulation parameters.
    static final int DECK_SIZE = 60;      // Total number of cards in the deck.
    static final int HAND_SIZE = 7;       // Number of cards drawn for the player's hand.
    static final int PRIZE_SIZE = 6;      // Number of prize cards drawn after the hand.
    static final int TRIALS = 10000;      // How many simulation iterations to run.
    static final int POKEMON_COUNT = 12;  // Total number of Pokémon cards in the deck.

    public static void main(String[] args) {
        // Loop through scenarios with varying numbers of RareCandy cards (from 1 to 4).
        for (int rareCandyCount = 1; rareCandyCount <= 4; rareCandyCount++) {
            int brickCount = 0; // This will count how many times we hit a "brick" (all RareCandies in prizes).

            // Run the simulation TRIALS a number of times for each rareCandyCount.
            for (int i = 0; i < TRIALS; i++) {
                if (simulate(rareCandyCount)) {
                    brickCount++; // If the simulation returns true, it means we got all rare candies in the prize cards.
                }
            }

            // Calculate the probability (as a percentage) that this scenario results in a brick.
            double probability = (brickCount / (double) TRIALS) * 100;
            // Print out the result in a friendly format.
            System.out.printf("Rare Candies: %d, Brick Probability: %.2f%%%n", rareCandyCount, probability);
        }
    }

    /**
     * Simulates drawing cards from a deck that contains Pokémon cards, RareCandy cards, and other cards.
     * It first constructs the deck, shuffles it, draws a hand ensuring at least one Pokémon card is present,
     * and then draws prize cards. The method checks if all the RareCandy cards ended up among the prize cards.
     *
     * @param rareCandyCount The number of RareCandy cards to include in the deck.
     * @return True if all RareCandy cards are found in the prize cards, indicating a "brick"; otherwise false.
     */
    public static boolean simulate(int rareCandyCount) {
        // Create a new deck as a list of strings.
        List<String> deck = new ArrayList<>();

        // Add Pokémon cards to the deck.
        for (int i = 0; i < POKEMON_COUNT; i++) {
            deck.add("Pokemon");
        }

        // Add the specified number of RareCandy cards to the deck.
        for (int i = 0; i < rareCandyCount; i++) {
            deck.add("RareCandy");
        }

        // Fill the remaining slots in the deck with other cards.
        while (deck.size() < DECK_SIZE) {
            deck.add("Other");
        }

        // Shuffle the deck to randomize the order of cards.
        Collections.shuffle(deck);

        // Draw a hand of HAND_SIZE cards.
        // The hand must contain at least one Pokémon card.
        List<String> hand = new ArrayList<>(deck.subList(0, HAND_SIZE));
        while (!hand.contains("Pokemon")) {
            // If no Pokémon is found, reshuffle and draw a new hand.
            Collections.shuffle(deck);
            hand = new ArrayList<>(deck.subList(0, HAND_SIZE));
        }

        // Draw the next PRIZE_SIZE cards to form the prize cards.
        List<String> prizeCards = new ArrayList<>(deck.subList(HAND_SIZE, HAND_SIZE + PRIZE_SIZE));

        // Count how many RareCandy cards appear among the prize cards.
        long rareCandyInPrize = prizeCards.stream().filter(card -> card.equals("RareCandy")).count();
        // Return true if the count of RareCandy cards in prizes equals the rareCandyCount passed to the simulation.
        return rareCandyInPrize == rareCandyCount;
    }
}
