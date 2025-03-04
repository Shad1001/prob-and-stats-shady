import java.util.Random;

public class MulliganChance {
    //I set the deck size to 60 because theres always going to only be a total of 60 cards. 
    //The hand size will be 7 because you will have 7 cards in your hand while playing pokemon.
    //We were finally asked to run the trial 10000 times.
    private static  int DECK_SIZE = 60;
    private static  int HAND_SIZE = 7;
    private static  int TRIALS = 10000;

    public static void main(String[] args) {
        Random rand = new Random();
        
        System.out.println("Pokemon Count | Mulligan % Chance");        
        for (int pokemonCount = 1; pokemonCount <= DECK_SIZE; pokemonCount++) {
            int mulligans = 0;
            
            for (int trial = 0; trial < TRIALS; trial++) {
                int pokemonInHand = 0;
                
                for (int i = 0; i < HAND_SIZE; i++) {
                    if (rand.nextInt(DECK_SIZE) < pokemonCount) {
                        pokemonInHand++;
                    }
                }
                
                if (pokemonInHand == 0) {
                    mulligans++;
                }
            }
        
            double mulliganChance = (mulligans / (double) TRIALS) * 100;
            System.out.printf("%13d | %.2f%%\n", pokemonCount, mulliganChance);
        }
    }
}
