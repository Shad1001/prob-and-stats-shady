import java.util.ArrayList;
import java.util.Collections;

/**
 * Player.java
 * Represents a player in the Pokemon TCG game.
 * Holds the player's deck, hand, prize pile, active Pokemon, bench, discard pile,
 * and tracks whether a Supporter Trainer card has been played this turn.
 */
public class Player {
    // The player's name.
    private String name;
    // The deck of cards.
    private ArrayList<Card> deck;
    // The player's current hand.
    private ArrayList<Card> hand;
    // The prize pile; a player wins when all prize cards are claimed.
    private ArrayList<Card> prizePile;
    // The active pokemon currently in play.
    private ArrayList<Pokemon> inPlay;
    // The pokemon on the bench.
    private ArrayList<Pokemon> bench;
    // The discard pile.
    private ArrayList<Card> discardPile;
    // Flag to track if a Supporter Trainer card has been played this turn.
    private boolean supporterPlayedThisTurn;
    /**
     * Constructs a new Player with the given name.
     * Initializes all lists and sets the supporter flag to false.
     *
     * @param name The name of the player.
     */
    
    public Player(String name) {
        this.name = name;
        deck = new ArrayList<>();
        hand = new ArrayList<>();
        prizePile = new ArrayList<>();
        inPlay = new ArrayList<>();
        bench = new ArrayList<>();
        discardPile = new ArrayList<>();
        supporterPlayedThisTurn = false;
    }
    
      /**
     * Returns the player's name.
     *
     * @return The player's name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the player's deck.
     *
     * @return The deck as an ArrayList of Card objects.
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }
    
    /**
     * Returns the player's hand.
     *
     * @return The hand as an ArrayList of Card objects.
     */
    public ArrayList<Card> getHand() {
        return hand;
    }
    
    /**
     * Returns the player's prize pile.
     *
     * @return The prize pile as an ArrayList of Card objects.
     */
    public ArrayList<Card> getPrizePile() {
        return prizePile;
    }
    
    /**
     * Returns the player's active pokemon in play.
     *
     * @return The in-play pokemon as an ArrayList of Pokemon objects.
     */
    public ArrayList<Pokemon> getInPlay() {
        return inPlay;
    }
    
    /**
     * Returns the player's bench.
     *
     * @return The bench as an ArrayList of Pokemon objects.
     */
    public ArrayList<Pokemon> getBench() {
        return bench;
    }
    
    /**
     * Returns the player's discard pile.
     *
     * @return The discard pile as an ArrayList of Card objects.
     */
    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }
    
    /**
     * Checks if a Supporter Trainer card has been played this turn.
     *
     * @return true if a supporter has been played, false otherwise.
     */
    public boolean hasSupporterPlayedThisTurn() {
        return supporterPlayedThisTurn;
    }
    
    /**
     * Sets the flag indicating whether a Supporter Trainer card has been played this turn.
     *
     * @param value true if a supporter has been played, false otherwise.
     */
    public void setSupporterPlayedThisTurn(boolean value) {
        supporterPlayedThisTurn = value;
    }
    
    /**
     * Adds a card to the player's deck.
     *
     * @param card The card to add.
     */
    public void addToDeck(Card card) {
        deck.add(card);
    }
    
    /**
     * Shuffles the player's deck.
     */
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }
    
    /**
     * Draws one card from the top of the deck and adds it to the player's hand.
     * If the deck is empty, prints an error message.
     */
    public void drawCard() {
        if (deck.isEmpty()) {
            System.out.println(name + " cannot draw a card because the deck is empty.");
            return;
        }
        Card drawn = deck.remove(0);
        hand.add(drawn);
        System.out.println(name + " draws: " + drawn.getName());
    }
    
    /**
     * Fills the player's hand until it has 7 cards.
     * Stops drawing if the deck is empty.
     */
    public void fillHand() {
        while (hand.size() < 7 && !deck.isEmpty()) {
            drawCard();
        }
    }
    
    /**
     * Checks if there is at least one pokemon in the player's hand.
     *
     * @return true if there is a pokemon in hand, false otherwise.
     */
    public boolean hasPokemonInHand() {
        for (Card c : hand) {
            if (c instanceof Pokemon)
                return true;
        }
        return false;
    }
    
    /**
     * Fills the prize pile with 6 cards.
     * A player wins when they collect all 6 prize cards.
     */
    public void fillPrizePile() {
        for (int i = 0; i < 6 && !deck.isEmpty(); i++) {
            prizePile.add(deck.remove(0));
        }
    }
}
