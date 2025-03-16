/**
 * AceTrainer.java
 *
 * A Supporter Trainer card.
 * Effect: "Shuffle your hand into your deck, then draw 6 cards; your opponent shuffles their hand into their deck, then draws 3 cards."
 */
public class AceTrainer extends TrainerCard {
    public AceTrainer() {
        super("Ace Trainer", "Supporter");
    }
    
    @Override
    public void playEffect(Player currentPlayer, Player opponent, PokemonGame game) {
        if (currentPlayer.getPrizePile().size() <= opponent.getPrizePile().size()) {
            System.out.println("Cannot play Ace Trainer! You need more prize cards left than your opponent.");
            return;
        }
        currentPlayer.getDeck().addAll(currentPlayer.getHand());
        currentPlayer.getHand().clear();
        currentPlayer.shuffleDeck();
        opponent.getDeck().addAll(opponent.getHand());
        opponent.getHand().clear();
        opponent.shuffleDeck();
        for (int i = 0; i < 6; i++) {
            currentPlayer.drawCard();
        }
        for (int i = 0; i < 3; i++) {
            opponent.drawCard();
        }
        System.out.println(currentPlayer.getName() + " used Ace Trainer!");
    }
}
