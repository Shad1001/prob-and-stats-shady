// This class represents my Energy cards in the game
// Energy cards are used to power Pokemon attacks and other actions
// It extends the base Card class
public class EnergyCard extends Card {
    // The type of energy this card represents
    private String energyType;
    
    // Constructor for EnergyCard
    // It appends "Energy" to the energyType to form the card's name
    public EnergyCard(String energyType) {
        super(energyType + " Energy");
        this.energyType = energyType;
    }
    
    // Getter method to retrieve the energy type
    public String getEnergyType() {
        return energyType;
    }
}
