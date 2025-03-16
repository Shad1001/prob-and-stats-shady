/**
 * Meowth.java
 * Represents the Meowth card in the TCG.
 * This card has the following stats:
 * HP: 60
 * Type: Basic
 * Retreat Cost: 1
 */
public class Meowth extends Pokemon {

    /**
     * Constructor for Meowth.
     * Sets up the card by calling the parent class constructor with Meowth's specific stats and attacks.
     */
    public Meowth() {
        super("Meowth", 60, "Basic", "Fighting", 1, new Attack[] {
            // "Nap" is an attack that doesn't cost energy and lets Meowth recover health.
            new Attack("Nap", 0, 0, ""),
            // "Jump On" is an attack that deals 20 damage, costing 1 energy.
            new Attack("Jump On", 20, 1, "Basic")
        });
    }
    
    /**
     * Does an attack on an opponent Pokemon.
     * This method picks an attack based on the index and handles the special effects
     * @param attackingPlayer The player using Meowth to attack.
     * @param opponent        The opponent Pokemon that is being attacked.
     * @param attackIndex     The index of the attack to be used.
     */
    @Override
    public void attack(Player attackingPlayer, Pokemon opponent, int attackIndex) {
        // See that the chosen attack index is within the valid range.
        if (attackIndex < 0 || attackIndex >= attacks.length) {
            System.out.println(getName() + " does not have that attack.");
            return;
        }
        
        // Get the selected attack from Meowth's array of attacks.
        Attack chosenAttack = attacks[attackIndex];
        String moveName = chosenAttack.getName();
        
        // Before performing the attack, check if there's enough energy.
        if (chosenAttack.getEnergyCost() > 0 && !hasEnergyForAttack(chosenAttack)) {
            System.out.println(getName() + " does not have enough energy to use " + moveName + "!");
            return;
        }
        
        // Announce the chosen move.
        System.out.println(getName() + " uses " + moveName + "!");
        
        // Special handling for the "Nap" attack:
        // When Meowth takes a nap, it recovers 20 HP.
        if (moveName.equalsIgnoreCase("Nap")) {
            System.out.println(getName() + " takes a nap and recovers 20 HP!");
            this.hp += 20;
            System.out.println(getName() + " now has " + this.hp + " HP.");
        } 
        // Special handling for the "Jump On" attack:
        // This attack deals damage to the opponent, with a chance for extra damage.
        else if (moveName.equalsIgnoreCase("Jump On")) {
            // Start with the base damage defined in the attack.
            int damage = chosenAttack.getDamage();
            
            // Simulate a coin flip to see if extra damage is added.
            boolean coinFlip = Math.random() < 0.5;
            if (coinFlip) {
                damage += 10;
                System.out.println("Coin flip: Heads! Extra 10 damage added.");
            } else {
                System.out.println("Coin flip: Tails! No extra damage.");
            }
            
            // If the opponent is weak to Meowth's type, double the damage.
            if (opponent.weakness.equalsIgnoreCase(this.type)) {
                damage *= 2;
                System.out.println("It's super effective! Damage is doubled.");
            }
            
            // Apply the damage to the opponent and display their remaining HP.
            opponent.hp -= damage;
            System.out.println(opponent.getName() + " takes " + damage + " damage. Remaining HP: " + opponent.hp);
        }
    }
}
