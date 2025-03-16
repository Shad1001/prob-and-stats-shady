import java.util.ArrayList;

/**
 * Pokemon.java
 * Abstract class representing a Pokemon TCG card.
 * Stores HP, type, weakness, retreat cost, available attacks, and attached Energy cards.
 */
public abstract class Pokemon extends Card {
    protected int hp;
    protected String type;
    protected String weakness;
    protected int retreatCost;
    protected Attack[] attacks;
    protected ArrayList<EnergyCard> attachedEnergies;
    
    /**
     * Constructor for a Pokemon card.
     *
     * @param name         The name of the Pokemon.
     * @param hp           The hit points of the Pokemon.
     * @param type         The Pokemon's type.
     * @param weakness     The type this Pokemon is weak against.
     * @param retreatCost  The number of energy cards required to retreat.
     * @param attacks      An array of available attacks.
     */
    public Pokemon(String name, int hp, String type, String weakness, int retreatCost, Attack[] attacks) {
        super(name);
        this.hp = hp;
        this.type = type;
        this.weakness = weakness;
        this.retreatCost = retreatCost;
        this.attacks = attacks;
        this.attachedEnergies = new ArrayList<>();
    }
    
    /**
     * Trys to attach an Energy card to this Pokemon.
     * The Energy card's type must exactly match the Pokemon's type.
     *
     * @param energy The Energy card to attach.
     */
    public void attachEnergy(EnergyCard energy) {
        // Require an exact match between the Pokemon's type and the Energy card's type.
        if (!energy.getEnergyType().equalsIgnoreCase(this.type)) {
            System.out.println("Cannot attach " + energy.getName() + " to " + getName() +
                               ". " + getName() + " requires " + this.type + " energy.");
            return;
        }
        attachedEnergies.add(energy);
        System.out.println(energy.getName() + " attached to " + getName());
    }
    
    /**
     * Checks to see if the Pokemon has enough attached Energy cards to use a given attack.
     *  The energy type must exactly match the required type or it will not attach.
     *
     * @param attack The attack to check.
     * @return true if the Pokemon meets the energy requirement, false otherwise.
     */
    public boolean hasEnergyForAttack(Attack attack) {
        int required = attack.getEnergyCost();
        String reqType = attack.getRequiredType();
        int count = 0;
        for (EnergyCard energy : attachedEnergies) {
            if (energy.getEnergyType().equalsIgnoreCase(reqType))
                count++;
        }
        return count >= required;
    }
    
    /**
     * Checks if the Pokemon can retreat (this means it has enough attached energies).
     *
     * @return true if it can retreat, false otherwise.
     */
    public boolean canRetreat() {
        return attachedEnergies.size() >= retreatCost;
    }
    
    /**
     * Retreats the Pokemon by discarding the number of Energy cards equal to the retreat cost.
     */
    public void retreat() {
        if (canRetreat()) {
            for (int i = 0; i < retreatCost; i++) {
                attachedEnergies.remove(0);
            }
            System.out.println(getName() + " retreats, using " + retreatCost + " energy.");
        } else {
            System.out.println(getName() + " cannot retreat (insufficient energy).");
        }
    }
    
    /**
     * Abstract method for attacking.
     *
     * @param attackingPlayer The player using this Pokemon.
     * @param opponent        The opposing Pokemon.
     * @param attackIndex     The index of the chosen attack.
     */
    public abstract void attack(Player attackingPlayer, Pokemon opponent, int attackIndex);
}
