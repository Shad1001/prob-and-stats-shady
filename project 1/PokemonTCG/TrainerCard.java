public class TrainerCard extends Card {
    public TrainerCard(String name) {
        super(name);
    }
    
    // Example trainer effect: draw a card for the player.
    public void useEffect(Player player) {
        System.out.println(getName() + " trainer card effect activated for " + player.getName() + "!");
        player.drawCard();
    }
}
