public class EnergyCard extends Card {
    private String energyType;
    
    public EnergyCard(String energyType) {
        super(energyType + " Energy");
        this.energyType = energyType;
    }
    
    public String getEnergyType() {
        return energyType;
    }
}
