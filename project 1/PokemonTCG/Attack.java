/**
 * Attack.java
 * Represents a Pokemon attack/move.
 * Each attack has a name, base damage, an energy cost, and a required energy type.
 */
public class Attack {
    // The name of the attack.
    private String name;
    
    // The base damage that the attack inflicts.
    private int damage;
    
    // The amount of energy required to perform the attack.
    private int energyCost;
    
    // The type of energy required.
    private String requiredType;
    
    /**
     * Constructor for the Attack class.
     *
     * Initializes a new Attack instance with the specified attributes.
     * 
     * @param name The name of the attack.
     * @param damage The base damage of the attack.
     * @param energyCost The energy cost required to execute the attack.
     * @param requiredType The required energy type to perform the attack.
     */
    public Attack(String name, int damage, int energyCost, String requiredType) {
        this.name = name;
        this.damage = damage;
        this.energyCost = energyCost;
        this.requiredType = requiredType;
    }
    
    /**
     * Gets the name of the attack.
     *
     * @return The attack's name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the base damage of the attack.
     *
     * @return The damage value.
     */
    public int getDamage() {
        return damage;
    }
    
    /**
     * Gets the energy cost required to perform the attack.
     *
     * @return The energy cost.
     */
    public int getEnergyCost() {
        return energyCost;
    }
    
    /**
     * Gets the required energy type for the attack.
     *
     * @return The required energy type.
     */
    public String getRequiredType() {
        return requiredType;
    }
}
