/**
 * TrainerCard.java
 *
 * Base class for Trainer cards.
 * Uses a String field "trainerCategory" to indicate the category 
 */
public abstract class TrainerCard extends Card {
    protected String trainerCategory;
    
    public TrainerCard(String name, String trainerCategory) {
        super(name);
        this.trainerCategory = trainerCategory;
    }
    
    public String getTrainerCategory() {
        return trainerCategory;
    }
    
    /**
     * Executes the Trainer card’s effect.
     *
     * @param currentPlayer The player who plays this Trainer card.
     * @param opponent The opposing player.
     * @param game The current PokemonGame instance.
     */
    public abstract void playEffect(Player currentPlayer, Player opponent, PokemonGame game);
}
