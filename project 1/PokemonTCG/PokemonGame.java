import java.util.ArrayList;

public class PokemonGame {
    private Player player1;
    private Player player2;
    private boolean gameOver;
    
    public PokemonGame() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        gameOver = false;
        initializeDecks();
        setupPlayers();
    }
    
    // Builds a 60-card deck for each player.
    // - First 8 cards: 8 different Pokémon.
    // - Next 4 cards: Trainer cards (using "Bill").
    // - The remaining cards: Energy cards (here, Fire Energy).
    private void initializeDecks() {
        for (int i = 0; i < 60; i++) {
            if (i < 8) {
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
            } else if (i < 8 + 4) {
                player1.addToDeck(new TrainerCard("Bill"));
            } else {
                player1.addToDeck(new EnergyCard("Fire"));
            }
        }
        player1.shuffleDeck();
        
        for (int i = 0; i < 60; i++) {
            if (i < 8) {
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
            } else if (i < 8 + 4) {
                player2.addToDeck(new TrainerCard("Bill"));
            } else {
                player2.addToDeck(new EnergyCard("Fire"));
            }
        }
        player2.shuffleDeck();
    }
    
    private void setupPlayers() {
        // Both players draw 7 cards.
        player1.fillHand();
        player2.fillHand();
        
        // Ensure each player has at least one Pokémon.
        if (!player1.hasPokemonInHand()) {
            System.out.println("Player 1 has no Pokémon in hand. Reshuffling and drawing extra card for opponent.");
            player1.shuffleDeck();
            player2.drawCard();
        }
        if (!player2.hasPokemonInHand()) {
            System.out.println("Player 2 has no Pokémon in hand. Reshuffling and drawing extra card for opponent.");
            player2.shuffleDeck();
            player1.drawCard();
        }
        
        // Deploy an initial Pokémon from each player's hand (max 6 in play).
        deployInitialPokemon(player1);
        deployInitialPokemon(player2);
        
        // Fill each player's prize pile.
        player1.fillPrizePile();
        player2.fillPrizePile();
    }
    
    private void deployInitialPokemon(Player player) {
        ArrayList<Card> handCopy = new ArrayList<>(player.getHand());
        for (Card card : handCopy) {
            if (card instanceof Pokemon) {
                player.getInPlay().add((Pokemon) card);
                player.getHand().remove(card);
                System.out.println(player.getName() + " deploys " + card.getName() + " to the field.");
                break;
            }
        }
    }
    
    // Returns true for heads, false for tails.
    private boolean coinflip() {
        return Math.random() < 0.5;
    }
    
    public void startGame() {
        boolean player1Starts = coinflip();
        System.out.println("Coin flip: " + (player1Starts ? "Player 1 starts." : "Player 2 starts."));
        
        while (!gameOver) {
            if (player1Starts) {
                playerTurn(player1, player2);
                if (determineWinner()) break;
                playerTurn(player2, player1);
                if (determineWinner()) break;
            } else {
                playerTurn(player2, player1);
                if (determineWinner()) break;
                playerTurn(player1, player2);
                if (determineWinner()) break;
            }
        }
    }
    
    private void playerTurn(Player currentPlayer, Player opponent) {
        System.out.println("----- " + currentPlayer.getName() + "'s Turn -----");
        
        if (currentPlayer.getDeck().isEmpty()) {
            System.out.println(currentPlayer.getName() + " cannot draw a card because the deck is empty. " 
                               + opponent.getName() + " wins!");
            gameOver = true;
            return;
        }
        
        // Draw a card.
        currentPlayer.drawCard();
        
        // Play one Energy card if available (attach it to the first Pokémon in play).
        ArrayList<Card> handCopy = new ArrayList<>(currentPlayer.getHand());
        for (Card card : handCopy) {
            if (card instanceof EnergyCard) {
                if (!currentPlayer.getInPlay().isEmpty()) {
                    Pokemon pkm = currentPlayer.getInPlay().get(0);
                    pkm.attachEnergy();
                    currentPlayer.getHand().remove(card);
                    System.out.println(currentPlayer.getName() + " attaches an energy to " + pkm.getName());
                    break;
                }
            }
        }
        
        // Play one Trainer card if available.
        handCopy = new ArrayList<>(currentPlayer.getHand());
        for (Card card : handCopy) {
            if (card instanceof TrainerCard) {
                TrainerCard trainer = (TrainerCard) card;
                trainer.useEffect(currentPlayer);
                currentPlayer.getHand().remove(card);
                System.out.println(currentPlayer.getName() + " played trainer card: " + trainer.getName());
                break;
            }
        }
        
        // Deploy a Pokémon if there is space (max 6 in play).
        if (currentPlayer.getInPlay().size() < 6) {
            handCopy = new ArrayList<>(currentPlayer.getHand());
            for (Card card : handCopy) {
                if (card instanceof Pokemon) {
                    currentPlayer.getInPlay().add((Pokemon) card);
                    currentPlayer.getHand().remove(card);
                    System.out.println(currentPlayer.getName() + " puts " + card.getName() + " into play.");
                    break;
                }
            }
        }
        
        // Attack phase: first Pokémon in play attacks opponent's first Pokémon.
        if (!currentPlayer.getInPlay().isEmpty() && !opponent.getInPlay().isEmpty()) {
            Pokemon attacker = currentPlayer.getInPlay().get(0);
            Pokemon defender = opponent.getInPlay().get(0);
            System.out.println(currentPlayer.getName() + "'s " + attacker.getName() + " attacks " 
                               + opponent.getName() + "'s " + defender.getName());
            attacker.attack(defender);
            if (defender.hp <= 0) {
                System.out.println(opponent.getName() + "'s " + defender.getName() + " is knocked out!");
                opponent.getInPlay().remove(defender);
                if (!currentPlayer.getPrizePile().isEmpty()) {
                    Card prize = currentPlayer.getPrizePile().remove(0);
                    currentPlayer.getHand().add(prize);
                    System.out.println(currentPlayer.getName() + " collects a prize card: " + prize.getName());
                }
            }
        }
        
        System.out.println("End of " + currentPlayer.getName() + "'s turn.\n");
    }
    
    // Checks win conditions:
    // 1. A player wins if their prize pile is empty (all 6 prizes collected).
    // 2. A player wins if the opponent has no Pokémon in play.
    // 3. A player wins if the opponent cannot draw a card because their deck is empty.
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
            System.out.println("Player 2 wins because Player 1 has no Pokémon in play!");
            gameOver = true;
            return true;
        }
        if (player2.getInPlay().isEmpty()) {
            System.out.println("Player 1 wins because Player 2 has no Pokémon in play!");
            gameOver = true;
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        PokemonGame game = new PokemonGame();
        game.startGame();
    }
}
