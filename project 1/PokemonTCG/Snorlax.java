public class Snorlax extends Pokemon {
    public Snorlax() {
        super("Snorlax", 70, 15, 10, 10, 10, 2, new String[]{"Body Slam", "Rest"});
    }
    
    @Override
    public void attack(Pokemon opponent) {
        System.out.println(getName() + " uses " + moves[0] + "!");
        opponent.hp -= attack;
    }
}
