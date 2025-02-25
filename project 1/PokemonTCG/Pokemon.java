//this represents a pokemon card in my game
//It will extend the base card class and includes attriubtes for all of their stats

public abstract class Pokemon extends Card {
    // Basic attributes for my Pokemon cards
    protected int hp;               // Hit points (health) 
    protected int attack;           // Basic attack strength
    protected int defense;          // Basic defense strength
    protected int specialAttack;    // Special attack strength
    protected int specialDefense;   // Special defense strength
    protected int retreatCost;      // Energy cost required to retreat the Pokemon
    protected int attachedEnergy;   // Number of energy cards currently attached
    protected String[] moves;       // Array of move names that the Pokémon can use
    
    //This is the constructer made to initialize a pokemon with its specific atributes 
    //then I call the superclass
    //I made a superclass because it looks better in the code and it is way more organized
    public Pokemon(String name, int hp, int attack, int defense, int specialAttack, int specialDefense, int retreatCost, String[] moves) {
        super(name);
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.retreatCost = retreatCost;
        this.moves = moves;
        this.attachedEnergy = 0;
    }
    
    // This is the method to attach an energy card to the Pokemon
    public void attachEnergy() {
        attachedEnergy++; // increasing the count of attached energy cards
    }
    
    // My method to check if the Pokemon can retreat
    // If they are able to retreat it returns true if the attached energy is at least equal to the retreat cost
    public boolean canRetreat() {
        return attachedEnergy >= retreatCost;
    }
    
    // The method to perform a retreat action
    // Now if the Pokemon can retreat subtract the retreat cost from the attached energy
    public void retreat() {
        if (canRetreat()) {
            attachedEnergy -= retreatCost; // Pay the retreat cost.
            System.out.println(name + " retreats by discharging " + retreatCost + " energy.");
        } else {
            System.out.println(name + " cannot retreat due to insufficient energy.");
        }
    }
    
    // method for attacking an opponent's Pokemon
    // Each Pokemon subclass must have its own version of the attack method
    // the reason each pokemon needs its own version of the attack method is because they can all have different moves 
    // the method ensures that all the pokemons moves will be accurete
    public abstract void attack(Pokemon opponent);
}
