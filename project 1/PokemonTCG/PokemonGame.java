// This is the main class that simulates the Pokemon Trading Card Game
// It handles game initialization, player turns, and win conditions
import java.util.ArrayList;

public class PokemonGame {
    // Two players in the game
    private Player player1;
    private Player player2;
    
    // Flag to indicate if the game is over
    private boolean gameOver;
    
    // Constructor for the game
    // Initializes players, builds decks, and sets up the initial game state
    public PokemonGame() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        gameOver = false;
        // Build decks for both players with the new composition
        initializeDecks();
        // Set up players by drawing hands, deploying initial Pokemon
        setupPlayers();
    }
    
    // Method to initialize decks for both players
    // Each deck will have:
    //  8 different Pokemon cards
    //  15 basic Energy cards
    //  The remaining 37 cards (to reach a total of 60) as Trainer cards
    private void initializeDecks() {
        // For Player 1's deck:
        // Step 1: Add 8 different Pokemon cards (one of each type).
        for (int i = 0; i < 8; i++) {
            switch(i) {
                case 0: player1.addToDeck(new Charmander()); break;
                case 1: player1.addToDeck(new Bulbasaur()); break;
                case 2: player1.addToDeck(new Squirtle()); break;
                case 3: player1.addToDeck(new Pikachu()); break;
                case 4: player1.addToDeck(new Eevee()); break;
                case 5: player1.addToDeck(new Snorlax()); break;
                case 6: player1.addToDeck(new Jigglypuff()); break;
                case 7: player1.addToDeck(new Meowth()); break;
            }
        }
        // Step 2: Add 15 basic Energy cards.
        for (int i = 0; i < 15; i++) {
            // Here we're adding "Fire" Energy cards; adjust the energy type as needed.
            player1.addToDeck(new EnergyCard("Fire"));
        }
        // Step 3: Fill the remaining card slots with Trainer cards.
        // Total deck size should be 60 cards.
        int remaining = 60 - 8 - 15; // 60 - 23 = 37 cards.
        for (int i = 0; i < remaining; i++) {
            // Here we're using "Bill" as the trainer card; adjust if needed.
            player1.addToDeck(new TrainerCard("Bill"));
        }
        // Shuffle Player 1's deck after adding all cards.
        player1.shuffleDeck();
        
        // Repeat the same process for Player 2's deck.
        // Step 1: Add 8 different Pokémon cards.
        for (int i = 0; i < 8; i++) {
            switch(i) {
                case 0: player2.addToDeck(new Charmander()); break;
                case 1: player2.addToDeck(new Bulbasaur()); break;
                case 2: player2.addToDeck(new Squirtle()); break;
                case 3: player2.addToDeck(new Pikachu()); break;
                case 4: player2.addToDeck(new Eevee()); break;
                case 5: player2.addToDeck(new Snorlax()); break;
                case 6: player2.addToDeck(new Jigglypuff()); break;
                case 7: player2.addToDeck(new Meowth()); break;
            }
        }
        // Step 2: Add 15 basic Energy cards.
        for (int i = 0; i < 15; i++) {
            player2.addToDeck(new EnergyCard("Fire"));
        }
        // Step 3: Fill the remaining slots with Trainer cards.
        remaining = 60 - 8 - 15; // Again, 37 cards.
        for (int i = 0; i < remaining; i++) {
            player2.addToDeck(new TrainerCard("Bill"));
        }
        // Shuffle Player 2's deck.
        player2.shuffleDeck();
    }
    
    // Sets up both players: draws initial hands, deploys one Pokémon to the field,
    // and fills the prize piles.
    private void setupPlayers() {
        // Both players draw 7 cards for their starting hand.
        player1.fillHand();
        player2.fillHand();
        
        // Check that each player has at least one Pokémon in hand.
        if (!player1.hasPokemonInHand()) {
            System.out.println("Player 1 has no Pokémon in hand. Reshuffling deck and awarding extra card to opponent.");
            player1.shuffleDeck();
            player2.drawCard();
        }
        if (!player2.hasPokemonInHand()) {
            System.out.println("Player 2 has no Pokémon in hand. Reshuffling deck and awarding extra card to opponent.");
            player2.shuffleDeck();
            player1.drawCard();
        }
        
        // Deploy an initial Pokemon from each player's hand
        deployInitialPokemon(player1);
        deployInitialPokemon(player2);
        
        // Each player sets aside 6 cards from their deck as prize cards
        player1.fillPrizePile();
        player2.fillPrizePile();
    }
    
    // Deploys the first available Pokemon from a player's hand onto the field
    private void deployInitialPokemon(Player player) {
        // Use a copy of the hand to avoid modifying the list while iterating
        ArrayList<Card> handCopy = new ArrayList<>(player.getHand());
        for (Card card : handCopy) {
            if (card instanceof Pokemon) {
                // Add the Pokemon to the in-play area and remove it from the hand
                player.getInPlay().add((Pokemon) card);
                player.getHand().remove(card);
                System.out.println(player.getName() + " deploys " + card.getName() + " to the field.");
                break; // Deploy only one Pokémon initially
            }
        }
    }
    
    // Helper method to perform a coinflip
    // Returns true for heads and false for tails
    private boolean coinflip() {
        return Math.random() < 0.5;
    }
    
    // Starts the game loop
    // Alternates turns between players until a win condition is met
    public void startGame() {
        // Decide randomly which player starts
        boolean player1Starts = coinflip();
        System.out.println("Coin flip: " + (player1Starts ? "Player 1 starts." : "Player 2 starts."));
        
        // Continue the game loop until the gameOver flag is set
        while (!gameOver) {
            if (player1Starts) {
                playerTurn(player1, player2); // Player 1 takes a turn.
                if (determineWinner()) break;  // Check if win condition is met.
                playerTurn(player2, player1); // Player 2 takes a turn.
                if (determineWinner()) break;
            } else {
                playerTurn(player2, player1); // Player 2 takes a turn.
                if (determineWinner()) break;
                playerTurn(player1, player2); // Player 1 takes a turn.
                if (determineWinner()) break;
            }
        }
    }
    
    // Simulates a single turn for the current player
    // The turn includes drawing a card, playing energy and trainer cards
    // deploying a Pokemon (if there is room), and attacking
    private void playerTurn(Player currentPlayer, Player opponent) {
        System.out.println("----- " + currentPlayer.getName() + "'s Turn -----");
        
        // If the current player's deck is empty, they lose
        if (currentPlayer.getDeck().isEmpty()) {
            System.out.println(currentPlayer.getName() + " cannot draw a card because the deck is empty. " 
                               + opponent.getName() + " wins!");
            gameOver = true;
            return;
        }
        
        // Draw a card at the start of the turn
        currentPlayer.drawCard();
        
        // Attempt to play one Energy card from the hand
        // The energy is attached to the first Pokemon in play
        ArrayList<Card> handCopy = new ArrayList<>(currentPlayer.getHand());
        for (Card card : handCopy) {
            if (card instanceof EnergyCard) {
                if (!currentPlayer.getInPlay().isEmpty()) {
                    Pokemon pkm = currentPlayer.getInPlay().get(0);
                    pkm.attachEnergy(); // Attach the energy card
                    currentPlayer.getHand().remove(card); // Remove the energy card from the hand
                    System.out.println(currentPlayer.getName() + " attaches an energy to " + pkm.getName());
                    break; // Only one energy card can be played per turn
                }
            }
        }
        
        // Attempt to play one Trainer card from the hand
        // The trainer card's effect (such as drawing an extra card) is activated
        handCopy = new ArrayList<>(currentPlayer.getHand());
        for (Card card : handCopy) {
            if (card instanceof TrainerCard) {
                TrainerCard trainer = (TrainerCard) card;
                trainer.useEffect(currentPlayer); // Activate the trainer card effect
                currentPlayer.getHand().remove(card); // Remove the trainer card from the hand
                System.out.println(currentPlayer.getName() + " played trainer card: " + trainer.getName());
                break; // Only one trainer card per turn.
            }
        }
        
        // Deploy an additional Pokemon from the hand if there is space (maximum 6 allowed)
        if (currentPlayer.getInPlay().size() < 6) {
            handCopy = new ArrayList<>(currentPlayer.getHand());
            for (Card card : handCopy) {
                if (card instanceof Pokemon) {
                    currentPlayer.getInPlay().add((Pokemon) card); // Deploy the Pokemon
                    currentPlayer.getHand().remove(card);          // Remove it from the hand
                    System.out.println(currentPlayer.getName() + " puts " + card.getName() + " into play.");
                    break; // Only deploy one Pokemon per turn
                }
            }
        }
        
        // Attack phase: the first Pokemon in play attacks the opponent's first Pokemon
        if (!currentPlayer.getInPlay().isEmpty() && !opponent.getInPlay().isEmpty()) {
            Pokemon attacker = currentPlayer.getInPlay().get(0);
            Pokemon defender = opponent.getInPlay().get(0);
            System.out.println(currentPlayer.getName() + "'s " + attacker.getName() + " attacks " 
                               + opponent.getName() + "'s " + defender.getName());
            attacker.attack(defender); // Execute the attack
            
            // Check if the defending Pokemon has been knocked out
            if (defender.hp <= 0) {
                System.out.println(opponent.getName() + "'s " + defender.getName() + " is knocked out!");
                opponent.getInPlay().remove(defender); // Remove the knocked-out Pokemon
                
                // Award a prize card to the current player, if available
                if (!currentPlayer.getPrizePile().isEmpty()) {
                    Card prize = currentPlayer.getPrizePile().remove(0);
                    currentPlayer.getHand().add(prize);
                    System.out.println(currentPlayer.getName() + " collects a prize card: " + prize.getName());
                }
            }
        }
        
        // End of turn.
        System.out.println("End of " + currentPlayer.getName() + "'s turn.\n");
    }
    
    // Checks whether any win condition has been met
    // Win conditions include: a player collecting all prize cards or having no Pokemon in play
    // The messages displayed below are all the outcomes of the game
    
    private boolean determineWinner() {
        if (player1.getPrizePile().isEmpty()) {
            System.out.println("Player 1 wins by collecting all prize cards!");
            gameOver = true;
            return true;
        }
        if (player2.getPrizePile().isEmpty()) {
            System.out.println("Player 2 wins by collecting all prize cards!");
            gameOver = true;
            return true;
        }
        if (player1.getInPlay().isEmpty()) {
            System.out.println("Player 2 wins because Player 1 has no Pokemon in play!");
            gameOver = true;
            return true;
        }
        if (player2.getInPlay().isEmpty()) {
            System.out.println("Player 1 wins because Player 2 has no Pokemon in play!");
            gameOver = true;
            return true;
        }
        return false;
    }
    
    // Main method 
    public static void main(String[] args) {
        PokemonGame game = new PokemonGame();                     // Create the game instance
        System.out.println("Initializing Pokémon Game...");       // Print initialization message
        game.startGame();                                           // Start the game loop
        System.out.println("Game over.");                           // Print game over message
        System.out.println("Thank you for playing!");               // Thank the player
        System.exit(0);                                             // Exit the program
    }
}
