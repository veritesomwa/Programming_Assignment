import java.util.Scanner;

public class BlackjackGame {
    private Deck deck; // Deck of cards used in the game
    private Hand playerHand; // Player's hand of cards
    private Hand dealerHand; // Dealer's hand of cards
    private Scanner scanner; // Scanner for user input

    // Constructor to initialize the game components
    public BlackjackGame() {
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();
        scanner = new Scanner(System.in);
    }

    // Method to deal the initial two cards to both player and dealer
    public void dealInitialCards() {
        playerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
    }

    // Method to handle the player's turn
    public void playerTurn() {
        boolean continuePlaying = true;
        while (continuePlaying) {
            System.out.println("\nPlayer's hand:");
            playerHand.display();
            System.out.println("Player's total points: " + playerHand.calculatePoints());

            if (playerHand.calculatePoints() > 21) {
                System.out.println("You bust! Dealer wins.");
                return;
            }

            System.out.print("Would you like to (H)it or (S)tand? ");
            String decision = scanner.nextLine().trim().toUpperCase();

            if (decision.equals("H")) {
                playerHand.addCard(deck.drawCard());
            } else if (decision.equals("S")) {
                continuePlaying = false;
            } else {
                System.out.println("Invalid input. Please enter 'H' to hit or 'S' to stand.");
            }
        }
    }

    // Method to handle the dealer's turn
    public void dealerTurn() {
        while (dealerHand.calculatePoints() < 17) {
            dealerHand.addCard(deck.drawCard());
        }
        System.out.println("\nDealer's hand:");
        dealerHand.display();
        System.out.println("Dealer's total points: " + dealerHand.calculatePoints());
    }

    // Method to determine and display the winner
    public void determineWinner() {
        int playerPoints = playerHand.calculatePoints();
        int dealerPoints = dealerHand.calculatePoints();

        if (playerPoints > 21) {
            System.out.println("You bust! Dealer wins.");
        } else if (dealerPoints > 21 || playerPoints > dealerPoints) {
            System.out.println("You win!");
        } else if (dealerPoints > playerPoints) {
            System.out.println("Dealer wins.");
        } else {
            System.out.println("It's a tie!");
        }
    }

    // Method to play the game
    public void playGame() {
        dealInitialCards(); // Deal initial cards
        playerTurn(); // Handle player's turn
        if (playerHand.calculatePoints() <= 21) {
            dealerTurn(); // Handle dealer's turn
            determineWinner(); // Determine the winner
        }
    }

    // Main method to start the game
    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.playGame(); // Play the game
    }
}