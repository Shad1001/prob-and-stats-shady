/**
 * Charmander.java
 * Charmander TCG card.
 * "Collect": 0 damage, 0 energy cost (effect: draw a card).
 * "Flare": 30 damage, requires 1 Fire Energy.
 */
public class Charmander extends Pokemon {

    // Constructor: Initializes the Charmander card with its attributes and available attacks.
    public Charmander() {
        // Call the superclass (Pokemon) constructor with the following parameters:
        // Name: "Charmander"
        // HP: 70
        // Type: "Fire"
        // Retreat Cost: 1
        // Attacks: an array containing two Attack objects:
        //  "Collect": 0 damage, no energy required (effect: draw a card)
        //  "Flare": 30 damage, requires 1 Fire Energy
        super("Charmander", 70, "Fire", "Water", 1, new Attack[] {
            new Attack("Collect", 0, 0, ""),
            new Attack("Flare", 30, 1, "Fire")
        });
    }
    
    // Override the attack method to implement Charmander's specific attack actions.
    @Override
    public void attack(Player attackingPlayer, Pokemon opponent, int attackIndex) {
        if (attackIndex < 0 || attackIndex >= attacks.length) {
            System.out.println(getName() + " does not have that attack.");
            return;
        }
        
        Attack chosenAttack = attacks[attackIndex];
        String moveName = chosenAttack.getName();
        
        // Check if the attack requires energy and if Charmander has sufficient energy to perform it.
        if (chosenAttack.getEnergyCost() > 0 && !hasEnergyForAttack(chosenAttack)) {
            System.out.println(getName() + " does not have enough energy to use " + moveName + "!");
            return;
        }
        
        // Say the attack being used.
        System.out.println(getName() + " uses " + moveName + "!");
        
        // Determine which attack to execute based on the move name.
        if (moveName.equalsIgnoreCase("Collect")) {
            // "Collect" attack: allows the player to draw a card.
            System.out.println(getName() + " activates Collect and draws a card!");
            attackingPlayer.drawCard();  // Player draws a card.
        } else if (moveName.equalsIgnoreCase("Flare")) {
            // "Flare" attack: deals damage to the opponent.
            int damage = chosenAttack.getDamage();
            
            // Check if the opponent's weakness matches Charmander's type ("Fire").
            // If so, double the damage dealt.
            if (opponent.weakness.equalsIgnoreCase(this.type)) {
                damage *= 2;
                System.out.println("It's super effective! Damage is doubled.");
            }
            
            // Apply the damage to the opponent's HP.
            opponent.hp -= damage;
            System.out.println(opponent.getName() + " takes " + damage + " damage. Remaining HP: " + opponent.hp);
        }
    }
}
