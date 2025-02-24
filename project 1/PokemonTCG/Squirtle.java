public class Squirtle extends Pokemon {
    public Squirtle() {
        super("Squirtle", 50, 7, 10, 10, 12, 1, new String[]{"Water Gun", "Tackle"});
    }
    
    @Override
    public void attack(Pokemon opponent) {
        System.out.println(getName() + " uses " + moves[0] + "!");
        opponent.hp -= specialAttack;
    }
}
