import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private String name;
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> prizePile;
    private ArrayList<Pokemon> inPlay;
    private ArrayList<Card> discardPile;
    
    public Player(String name) {
        this.name = name;
        deck = new ArrayList<>();
        hand = new ArrayList<>();
        prizePile = new ArrayList<>();
        inPlay = new ArrayList<>();
        discardPile = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public ArrayList<Card> getDeck() {
        return deck;
    }
    
    public ArrayList<Card> getHand() {
        return hand;
    }
    
    public ArrayList<Card> getPrizePile() {
        return prizePile;
    }
    
    public ArrayList<Pokemon> getInPlay() {
        return inPlay;
    }
    
    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }
    
    public void addToDeck(Card card) {
        deck.add(card);
    }
    
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }
    
    public void drawCard() {
        if (deck.isEmpty()) {
            System.out.println(name + " cannot draw a card because the deck is empty.");
            return;
        }
        Card drawn = deck.remove(0);
        hand.add(drawn);
        System.out.println(name + " draws: " + drawn.getName());
    }
    
    public void fillHand() {
        while (hand.size() < 7 && !deck.isEmpty()) {
            drawCard();
        }
    }
    
    public boolean hasPokemonInHand() {
        for (Card card : hand) {
            if (card instanceof Pokemon) {
                return true;
            }
        }
        return false;
    }
    
    public void fillPrizePile() {
        for (int i = 0; i < 6 && !deck.isEmpty(); i++) {
            prizePile.add(deck.remove(0));
        }
    }
}
