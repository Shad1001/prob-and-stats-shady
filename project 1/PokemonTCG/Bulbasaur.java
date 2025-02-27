public class Bulbasaur extends Pokemon {
    public Bulbasaur() {
        super("Bulbasaur", 60, 8, 7, 12, 10, 1, new String[]{"Razor Leaf", "Tackle"});
    }
    
    @Override
    public void attack(Pokemon opponent) {
        System.out.println(getName() + " uses " + moves[0] + "!");
        opponent.hp -= specialAttack;
    }
}
