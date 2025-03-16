/**
 * GymTrainer.java
 *
 * A Supporter Trainer card.
 * Effect: "Draw 2 cards. If any of your Pokemon were Knocked Out during your opponent's last turn, draw 2 more cards."
 */
public class GymTrainer extends TrainerCard {
    public GymTrainer() {
        super("Gym Trainer", "Supporter");
    }
    
    public void playEffect(Player currentPlayer, Player opponent, PokemonGame game) {
        // Step 1: Check if the current player has more prize cards left than the opponent.
        if (currentPlayer.getPrizePile().size() <= opponent.getPrizePile().size()) {
            System.out.println("Cannot play Ace Trainer! You need more prize cards left than your opponent.");
            return;
        }
        
        // Step 2: Add all cards from the current player's hand to their deck,
        // then clear the hand and shuffle the deck.
        currentPlayer.getDeck().addAll(currentPlayer.getHand());
        currentPlayer.getHand().clear();
        currentPlayer.shuffleDeck();
        
        // Step 3: Do the same for the opponent.
        opponent.getDeck().addAll(opponent.getHand());
        opponent.getHand().clear();
        opponent.shuffleDeck();
        
        // Step 4: The current player draws 6 cards.
        for (int i = 0; i < 6; i++) {
            currentPlayer.drawCard();
        }
        
        // Step 5: The opponent draws 3 cards.
        for (int i = 0; i < 3; i++) {
            opponent.drawCard();
        }
        
        // Step 6: Print a confirmation message indicating the Ace Trainer card has been used.
        System.out.println(currentPlayer.getName() + " used Ace Trainer!");
    }
}