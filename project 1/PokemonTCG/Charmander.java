// This class represents the Charmander Pokemon card
// It extends the abstract Pokemon class as I said previously in my pokemon.java file and provides its own implementation of the attack method
public class Charmander extends Pokemon {
    // Constructor for Charmander
    // Initializes Charmander with its specific attributes 
    public Charmander() {
        super("Charmander", 50, 10, 5, 15, 5, 1, new String[]{"Ember", "Scratch"});
    }
    
    // Implementation of the attack method for Charmander
    // Charmander uses its first move which is Ember to attack subtracting its specialAttack value from the opponents HP
    @Override
    public void attack(Pokemon opponent) {
        System.out.println(getName() + " uses " + moves[0] + "!");
        opponent.hp -= specialAttack; // Inflict damage using special attack
    }
}
