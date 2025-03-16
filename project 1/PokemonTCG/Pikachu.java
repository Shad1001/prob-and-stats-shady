/**
 * Pikachu.java
 * Pikachu TCG card.
 * "Quick Attack": 10 base damage, requires 1 Lightning Energy; coin flip for extra 10 damage.
 * "Electro Ball": 50 damage, requires 1 Lightning Energy.
 */
public class Pikachu extends Pokemon {

    // Constructor for Pikachu that initializes its name, HP, type, weakness, retreat cost, and attacks.
    public Pikachu() {
        // Call the superclass (Pokemon) constructor with the following parameters:
        // Name: "Pikachu"
        // HP: 60
        // Type: "Lightning"
        // Retreat Cost: 1
        // Attacks: an array of two Attack objects ("Quick Attack" and "Electro Ball")
        super("Pikachu", 60, "Lightning", "Fighting", 1, new Attack[] {
            new Attack("Quick Attack", 10, 1, "Lightning"),
            new Attack("Electro Ball", 50, 1, "Lightning")
        });
    }
    
    // Override the attack method to implement Pikachu's unique attack behavior.
    @Override
    public void attack(Player attackingPlayer, Pokemon opponent, int attackIndex) {
        // Validate that the attack index is within the valid range of available attacks.
        if (attackIndex < 0 || attackIndex >= attacks.length) {
            System.out.println(getName() + " does not have that attack.");
            return;
        }
        
        // Retrieve the chosen attack based on the provided index.
        Attack chosenAttack = attacks[attackIndex];
        String moveName = chosenAttack.getName();
        
        // Check if the attack requires energy and verify if Pikachu has enough energy attached.
        if (chosenAttack.getEnergyCost() > 0 && !hasEnergyForAttack(chosenAttack)) {
            System.out.println(getName() + " does not have enough energy to use " + moveName + "!");
            return;
        }
        
        // Say the attack being used.
        System.out.println(getName() + " uses " + moveName + "!");
        
        // Initialize damage with the base damage of the chosen attack.
        int damage = chosenAttack.getDamage();
        
        // If the attack is "Quick Attack" do a coin flip for extra damage.
        if (moveName.equalsIgnoreCase("Quick Attack")) {
            // Simulate a coin flip (50% chance for Heads).
            boolean coinFlip = Math.random() < 0.5;
            if (coinFlip) {
                // If Heads, add extra 10 damage.
                damage += 10;
                System.out.println("Coin flip: Heads! Extra 10 damage added.");
            } else {
                // If Tails no extra damage is added.
                System.out.println("Coin flip: Tails! No extra damage.");
            }
        }
        
        // If the opponent's weakness matches Pikachu's type double the damage.
        if (opponent.weakness.equalsIgnoreCase(this.type)) {
            damage *= 2;
            System.out.println("It's super effective! Damage is doubled.");
        }
        
        // Apply the calculated damage to the opponent's HP.
        opponent.hp -= damage;
        System.out.println(opponent.getName() + " takes " + damage + " damage. Remaining HP: " + opponent.hp);
    }
}
