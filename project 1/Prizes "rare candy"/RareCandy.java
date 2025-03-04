import java.util.*;

public class RareCandy {
    static final int DECK_SIZE = 60;
    static final int HAND_SIZE = 7;
    static final int PRIZE_SIZE = 6;
    static final int TRIALS = 10000;
    static final int POKEMON_COUNT = 12; // Assume 12 Pokémon in the deck

    public static void main(String[] args) {
        for (int rareCandyCount = 1; rareCandyCount <= 4; rareCandyCount++) {
            int brickCount = 0;

            for (int i = 0; i < TRIALS; i++) {
                if (simulate(rareCandyCount)) {
                    brickCount++;
                }
            }

            double probability = (brickCount / (double) TRIALS) * 100;
            System.out.printf("Rare Candies: %d, Brick Probability: %.2f%%%n", rareCandyCount, probability);
        }
    }

    public static boolean simulate(int rareCandyCount) {
        List<String> deck = new ArrayList<>();

        // Add Pokémon
        for (int i = 0; i < POKEMON_COUNT; i++) {
            deck.add("Pokemon");
        }

        // Add Rare Candies
        for (int i = 0; i < rareCandyCount; i++) {
            deck.add("RareCandy");
        }

        // Fill remaining deck with other cards
        while (deck.size() < DECK_SIZE) {
            deck.add("Other");
        }

        // Shuffle deck
        Collections.shuffle(deck);

        // Draw a hand of 7 cards, ensuring at least one Pokémon
        List<String> hand = new ArrayList<>(deck.subList(0, HAND_SIZE));
        while (!hand.contains("Pokemon")) {
            Collections.shuffle(deck);
            hand = new ArrayList<>(deck.subList(0, HAND_SIZE));
        }

        // Draw 6 prize cards
        List<String> prizeCards = new ArrayList<>(deck.subList(HAND_SIZE, HAND_SIZE + PRIZE_SIZE));

        // Check if all rare candies are in the prize cards
        long rareCandyInPrize = prizeCards.stream().filter(card -> card.equals("RareCandy")).count();
        return rareCandyInPrize == rareCandyCount;
    }
}
