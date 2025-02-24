public class Bulbasaur extends Pokemon {
    public Bulbasaur() {
        super("Bulbasaur", 45, 8, 7, 12, 10, 1, new String[]{"Vine Whip", "Tackle"});
    }
    
    @Override
    public void attack(Pokemon opponent) {
        System.out.println(getName() + " uses " + moves[0] + "!");
        opponent.hp -= specialAttack;
    }
}
