public class Jigglypuff extends Pokemon {
    public Jigglypuff() {
        super("Jigglypuff", 40, 7, 8, 10, 10, 1, new String[]{"Sing", "Pound"});
    }
    
    @Override
    public void attack(Pokemon opponent) {
        System.out.println(getName() + " uses " + moves[1] + "!");
        opponent.hp -= attack;
    }
}
