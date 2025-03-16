/**
 * Potion.java
 * An Item Trainer card.
 * Effect: "Heal 30 damage from one of your pokemon."
 */
public class Potion extends TrainerCard {

    /**
     * Constructor for Potion.
     * Calls the TrainerCard constructor with the card name "Potion" and category "Item".
     */
    public Potion() {
        super("Potion", "Item");
    }
    
    /**
     * Executes the effect of the Potion card.
     * This method heals 30 HP from the first active pokemon in the current player's in-play area.
     * If there is no active pokemon an error message is printed.
     *
     * @param currentPlayer The player who uses the Potion card.
     * @param opponent      The opposing player 
     * @param game          The current game instance 
     */
    @Override
    public void playEffect(Player currentPlayer, Player opponent, PokemonGame game) {
        // Check if the current player has any pokemon in the active area.
        if (!currentPlayer.getInPlay().isEmpty()) {
            // Retrieve the first active pokemon.
            Pokemon p = currentPlayer.getInPlay().get(0);
            // Increase the pokemon's HP by 30.
            p.hp += 30;
            // Inform the player that the potion has healed the pokemon.
            System.out.println(currentPlayer.getName() + " uses Potion on " + p.getName() + ", healing 30 HP.");
        } else {
            // If there is no active pokemon, output an error message.
            System.out.println("No pokemon in play to heal!");
        }
    }
}
