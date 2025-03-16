/**
 * Eevee.java
 * Eevee TCG card.
 * "Call for Family": 0 damage, 0 energy cost (effect: search deck for a Basic Pokemon, add it to the bench, then shuffle deck).
 * "Tackle": 30 damage, requires 1 Colorless Energy.
 */
public class Eevee extends Pokemon {

    // Constructor for Eevee that sets up the Pokemon's stats and attacks.
    public Eevee() {
        // Call the superclass (Pokemon) constructor with the following parameters:
        // Name: "Eevee"
        // HP: 60
        // Type: "Colorless"
        // Retreat Cost: 1
        // Attacks: an array containing two Attack objects ("Call for Family" and "Tackle")
        super("Eevee", 60, "Colorless", "Fighting", 1, new Attack[] {
            new Attack("Call for Family", 0, 0, ""),
            new Attack("Tackle", 30, 1, "Colorless")
        });
    }
    
    // Override the attack method to implement Eevee's attack behavior.
    @Override
    public void attack(Player attackingPlayer, Pokemon opponent, int attackIndex) {
        // See that the chosen attack index is within the available range.
        if (attackIndex < 0 || attackIndex >= attacks.length) {
            System.out.println(getName() + " does not have that attack.");
            return;
        }
        
        // Retrieve the chosen attack based on the index provided.
        Attack chosenAttack = attacks[attackIndex];
        String moveName = chosenAttack.getName();
        
        // Check if the attack requires energy and if Eevee has enough energy attached.
        if (chosenAttack.getEnergyCost() > 0 && !hasEnergyForAttack(chosenAttack)) {
            System.out.println(getName() + " does not have enough energy to use " + moveName + "!");
            return;
        }
        
        // Announce the attack being used.
        System.out.println(getName() + " uses " + moveName + "!");
        
        // Process the "Call for Family" attack.
        if (moveName.equalsIgnoreCase("Call for Family")) {
            Pokemon basicPokemon = null;
            // Loop through the player's deck to find a Basic Pokemon.
            for (Card c : attackingPlayer.getDeck()) {
                // For simplicity, every Pokemon in the deck is considered Basic.
                if (c instanceof Pokemon) {
                    basicPokemon = (Pokemon) c;
                    break;
                }
            }
            // If a Basic Pokemon is found, remove it from the deck, add it to the bench, and shuffle the deck.
            if (basicPokemon != null) {
                attackingPlayer.getDeck().remove(basicPokemon);
                attackingPlayer.getBench().add(basicPokemon);
                attackingPlayer.shuffleDeck();
                System.out.println(attackingPlayer.getName() + " places " + basicPokemon.getName() + " onto the Bench!");
            } else {
                // Inform the player if no Basic Pokemon was found in the deck.
                System.out.println("No Basic Pokemon found in deck!");
            }
        } 
        // Process the "Tackle" attack.
        else if (moveName.equalsIgnoreCase("Tackle")) {
            int damage = chosenAttack.getDamage();
            // If the opponent's weakness matches Eevee's type, double the damage.
            if (opponent.weakness.equalsIgnoreCase(this.type)) {
                damage *= 2;
                System.out.println("It's super effective! Damage is doubled.");
            }
            // Subtract the damage from the opponent's HP.
            opponent.hp -= damage;
            System.out.println(opponent.getName() + " takes " + damage + " damage. Remaining HP: " + opponent.hp);
        }
    }
}
