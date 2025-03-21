/**
 * Card.java
 *
 * Abstract base class for all cards.
 */
public abstract class Card {
    protected String name;
    
    public Card(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
