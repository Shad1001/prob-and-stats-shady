/**
 * Snorlax.java
 * Snorlax TCG card.
 * Stats: HP 150, Type: Colorless, Weakness: Fighting, Retreat Cost: 4.
 */
public class Snorlax extends Pokemon {

    // Constructor for Snorlax, initializes its attributes and available attacks.
    public Snorlax() {
        // Call the superclass (Pokemon) constructor with:
        // Name: "Snorlax"
        // HP: 150
        // Type: "Colorless"
        // Retreat Cost: 4
        // Attacks: an array containing two Attack objects:
        //  "Rolling Tackle": 80 damage, requires 2 Colorless Energy.
        //  "Heavy Impact": 130 damage, requires 2 Colorless Energy.
        super("Snorlax", 150, "Colorless", "Fighting", 4, new Attack[] {
            new Attack("Rolling Tackle", 80, 2, "Colorless"),
            new Attack("Heavy Impact", 130, 2, "Colorless")
        });
    }
    
    // Override the attack method to define Snorlax's specific attack behavior.
    @Override
    public void attack(Player attackingPlayer, Pokemon opponent, int attackIndex) {
        // Validate the provided attack index to ensure it's within the range of available attacks.
        if (attackIndex < 0 || attackIndex >= attacks.length) {
            System.out.println(getName() + " does not have that attack.");
            return;
        }
        
        // Retrieve the chosen attack from the attacks array.
        Attack chosenAttack = attacks[attackIndex];
        String moveName = chosenAttack.getName();
        
        // Check if the attack requires energy and whether Snorlax has enough energy to perform it.
        if (chosenAttack.getEnergyCost() > 0 && !hasEnergyForAttack(chosenAttack)) {
            System.out.println(getName() + " does not have enough energy to use " + moveName + "!");
            return;
        }
        
        // Announce the attack being used.
        System.out.println(getName() + " uses " + moveName + "!");
        
        // Initialize damage based on the attack's base damage.
        int damage = chosenAttack.getDamage();
        
        // Check if the opponent's weakness matches Snorlax's type.
        // If so, double the damage and announce that the attack is super effective.
        if (opponent.weakness.equalsIgnoreCase(this.type)) {
            damage *= 2;
            System.out.println("It's super effective! Damage is doubled.");
        }
        
        // Apply the calculated damage to the opponent's HP.
        opponent.hp -= damage;
        
        // Display the damage dealt and the opponent's remaining HP.
        System.out.println(opponent.getName() + " takes " + damage + " damage. Remaining HP: " + opponent.hp);
    }
}
