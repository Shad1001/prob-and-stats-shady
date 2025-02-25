// This class represents a player in my Pokemon TCG game
// A player has a deck, a hand, a prize pile, Pokemon in play, and a discard pile
import java.util.ArrayList;
import java.util.Collections;

public class Player {
    // The player's name 
    private String name;
    
    // Collections representing the player's different card zones
    private ArrayList<Card> deck;       // The player's deck of cards
    private ArrayList<Card> hand;       // The player's current hand
    private ArrayList<Card> prizePile;  // Cards set aside as prizes
    private ArrayList<Pokemon> inPlay;  // Pokémon currently in play (up to 6 allowed)
    private ArrayList<Card> discardPile; // Discard pile for used or knocked-out cards
    
    // Constructor for the Player class
    public Player(String name) {
        this.name = name;
        deck = new ArrayList<>();
        hand = new ArrayList<>();
        prizePile = new ArrayList<>();
        inPlay = new ArrayList<>();
        discardPile = new ArrayList<>();
    }
    
    // Getter method for the player's name
    public String getName() {
        return name;
    }
    
    // Getter methods for each card collection
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
    
    // Adds a card to the player's deck
    public void addToDeck(Card card) {
        deck.add(card);
    }
    
    // Shuffles the player's deck randomly
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }
    
    // Draws a card from the top of the deck and adds it to the player's hand
    public void drawCard() {
        if (deck.isEmpty()) {
            System.out.println(name + " cannot draw a card because the deck is empty.");
            return;
        }
        Card drawn = deck.remove(0); // Remove the top card
        hand.add(drawn);             // Add it to the hand
        System.out.println(name + " draws: " + drawn.getName());
    }
    
    // Fills the player's hand until it has 7 cards (if possible)
    public void fillHand() {
        while (hand.size() < 7 && !deck.isEmpty()) {
            drawCard();
        }
    }
    
    // Checks whether the player's hand contains at least one Pokemon
    public boolean hasPokemonInHand() {
        for (Card card : hand) {
            if (card instanceof Pokemon) {
                return true;
            }
        }
        return false;
    }
    
    // Fills the player's prize pile by moving 6 cards from the deck
    public void fillPrizePile() {
        for (int i = 0; i < 6 && !deck.isEmpty(); i++) {
            prizePile.add(deck.remove(0)); // Remove a card from the deck and add to the prize pile
        }
    }
}
