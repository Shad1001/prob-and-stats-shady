public abstract class Pokemon extends Card {
    protected int hp;
    protected int attack;
    protected int defense;
    protected int specialAttack;
    protected int specialDefense;
    protected int retreatCost;
    protected int attachedEnergy;
    protected String[] moves;
    
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
    
    public void attachEnergy() {
        attachedEnergy++;
    }
    
    public boolean canRetreat() {
        return attachedEnergy >= retreatCost;
    }
    
    public void retreat() {
        if (canRetreat()) {
            attachedEnergy -= retreatCost;
            System.out.println(name + " retreats by discharging " + retreatCost + " energy.");
        } else {
            System.out.println(name + " cannot retreat due to insufficient energy.");
        }
    }
    
    public abstract void attack(Pokemon opponent);
}
