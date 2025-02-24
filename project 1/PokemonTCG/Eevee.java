public class Eevee extends Pokemon {
    public Eevee() {
        super("Eevee", 45, 10, 8, 11, 9, 1, new String[]{"Tackle", "Quick Attack"});
    }
    
    @Override
    public void attack(Pokemon opponent) {
        // Demonstrate using the second move
        System.out.println(getName() + " uses " + moves[1] + "!");
        opponent.hp -= specialAttack;
    }
}
