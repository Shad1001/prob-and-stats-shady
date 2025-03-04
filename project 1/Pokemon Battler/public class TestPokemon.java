public class TestPokemon {
    public static void main(String[] args) {
        // Create a Charmander instance
        Charmander charmanderSteve = new Charmander();
        System.out.println("Charmander Steve HP: " + charmanderSteve.getHp());

        // Create a Pikachu instance
        Pikachu ummhmm = new Pikachu();
        System.out.println("Pikachu ummhmm HP: " + ummhmm.getHp());

        // Create a Stadium instance and initiate a battle
        Stadium tester = new Stadium();
        tester.battle(ummhmm, charmanderSteve);
    }
}
