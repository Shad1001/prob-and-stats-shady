// This class represents the Bulbasaur Pokemon card
// It extends the abstract Pokemon class as all the pokemon will and provides its own implementation of the attack method

public class Bulbasaur extends Pokemon {
    // Constructor for the pokemon Bulbasaur
    // Sets Bulbasaur's attributes 
    public Bulbasaur() {
        super("Bulbasaur", 45, 8, 7, 12, 10, 1, new String[]{"Vine Whip", "Tackle"});
    }
    
    // Implementation of Bulbasaurs attack method
    // Uses its first move Vine Whip to attack the opponent
    @Override
    public void attack(Pokemon opponent) {
        System.out.println(getName() + " uses " + moves[0] + "!");
        opponent.hp -= specialAttack; // Inflict damage using special attack
    }
}
