public class Charmander extends Pokemon {
    public Charmander() {
        super("Charmander", 50, 10, 5, 15, 5, 1, new String[]{"Ember", "Scratch"});
    }
    
    @Override
    public void attack(Pokemon opponent) {
        System.out.println(getName() + " uses " + moves[0] + "!");
        opponent.hp -= specialAttack;
    }
}
