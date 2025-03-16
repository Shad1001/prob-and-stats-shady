/**
 * Jigglypuff.java
 * Jigglypuff TCG card.
 * Stats: HP 70, Type: Psychic, Weakness: Psychic, Retreat Cost: 1.
 */
public class Jigglypuff extends Pokemon {

    // Constructor for Jigglypuff that initializes its properties and available attacks.
    public Jigglypuff() {
        // Call the superclass (Pokemon) constructor with:
        // Name: "Jigglypuff"
        // HP: 70
        // Type: "Psychic"
        // Retreat Cost: 1
        // Attacks: an array containing two Attack objects ("Mumble" and "Moon Kick")
        super("Jigglypuff", 70, "Psychic", "Psychic", 1, new Attack[] {
            new Attack("Mumble", 10, 1, "Psychic"),
            new Attack("Moon Kick", 20, 1, "Psychic")
        });
    }
    
    // Override the attack method to implement Jigglypuff's specific attack behavior.
    @Override
    public void attack(Player attackingPlayer, Pokemon opponent, int attackIndex) {
        // Validate that the provided attack index is within the range of available attacks.
        if (attackIndex < 0 || attackIndex >= attacks.length) {
            System.out.println(getName() + " does not have that attack.");
            return;
        }
        
        // Retrieve the chosen attack using the attack index.
        Attack chosenAttack = attacks[attackIndex];
        String moveName = chosenAttack.getName();
        
        // Check if the attack requires energy and verify if Jigglypuff has enough energy attached.
        if (chosenAttack.getEnergyCost() > 0 && !hasEnergyForAttack(chosenAttack)) {
            System.out.println(getName() + " does not have enough energy to use " + moveName + "!");
            return;
        }
        
        // Announce the attack being used.
        System.out.println(getName() + " uses " + moveName + "!");
        
        // Initialize the damage value with the attack's base damage.
        int damage = chosenAttack.getDamage();
        
        // Check if the opponent's weakness matches Jigglypuff's type.
        // If it does, double the damage and inform the user.
        if (opponent.weakness.equalsIgnoreCase(this.type)) {
            damage *= 2;
            System.out.println("It's super effective! Damage is doubled.");
        }
        
        // Subtract the calculated damage from the opponent's HP.
        opponent.hp -= damage;
        
        // Display the opponent's name, the damage taken, and the remaining HP.
        System.out.println(opponent.getName() + " takes " + damage + " damage. Remaining HP: " + opponent.hp);
    }
}
