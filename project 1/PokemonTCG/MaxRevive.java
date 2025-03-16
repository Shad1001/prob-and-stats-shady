/**
 * MaxRevive.java
 * An Item Trainer card.
 * Effect: "Put a pokemon from your discard pile into your deck."
 */
public class MaxRevive extends TrainerCard {

    /**
     * Constructor for the Max Revive card.
     * Initializes the card with its name and category.
     */
    public MaxRevive() {
        super("Max Revive", "Item");
    }
    
    /**
     * effect of Max Revive.
     * Searches the current player's discard pile for a pokemon.
     * If a pokemon found, it is removed from the discard pile and added to the deck.
     * If no pokemon found, a message is printed indicating that no pokemon are available.
     *
     * @param currentPlayer The player using this card.
     * @param opponent      The opposing player.
     * @param game          The current game instance.
     */
    @Override
    public void playEffect(Player currentPlayer, Player opponent, PokemonGame game) {
        // Find the first pokemon in the discard pile.
        Pokemon chosen = null;
        for (Card c : currentPlayer.getDiscardPile()) {
            if (c instanceof Pokemon) {
                chosen = (Pokemon)c;
                break;
            }
        }
        
        // If a pokemon was found, remove it from discard and add it to the deck.
        if (chosen != null) {
            currentPlayer.getDiscardPile().remove(chosen);
            currentPlayer.getDeck().add(0, chosen);
            System.out.println(currentPlayer.getName() + " used Max Revive and revived " + chosen.getName() + "!");
        } else {
            System.out.println("No pokemon in discard to revive!");
        }
    }
}
