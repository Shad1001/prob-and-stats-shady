//This is my class for all the Pokemon cards I in my TCG Game.
//Every type of card revolves around it for examples my Pokemon, Energy Trainer 
//It will extend from this class 
public abstract class Card {
    protected String name;
    
    public Card(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
