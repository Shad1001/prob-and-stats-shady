public class Pikachu extends Pokemon {
    public Pikachu() {
        super("Pikachu", 40, 12, 6, 14, 8, 1, new String[]{"Thunder Shock", "Quick Attack"});
    }
    
    @Override
    public void attack(Pokemon opponent) {
        System.out.println(getName() + " uses " + moves[0] + "!");
        opponent.hp -= specialAttack;
    }
}
