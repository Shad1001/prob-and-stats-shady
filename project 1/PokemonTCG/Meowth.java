public class Meowth extends Pokemon {
    public Meowth() {
        super("Meowth", 35, 9, 7, 8, 8, 1, new String[]{"Scratch", "Bite"});
    }
    
    @Override
    public void attack(Pokemon opponent) {
        System.out.println(getName() + " uses " + moves[1] + "!");
        opponent.hp -= attack;
    }
}
