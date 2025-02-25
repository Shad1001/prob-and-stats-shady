// This class represents a Trainer card in the game
// Trainer cards provide special effects (for example, drawing extra cards).
// It extends the base Card class
public class TrainerCard extends Card {
    // Constructor for TrainerCard
    public TrainerCard(String name) {
        super(name);
    }
    
    // Example method that simulates the trainer card's effect
    // In this example, the effect is to allow the player to draw a card
    // There will be different effects 
    public void useEffect(Player player) {
        System.out.println(getName() + " trainer card effect activated for " + player.getName() + "!");
        player.drawCard(); // Call the player's drawCard() method to draw one extra card
    }
}
