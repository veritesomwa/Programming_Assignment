import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck; // List to store the deck of cards

    // Constructor to initialize the deck with 52 cards
    public Deck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        deck = new ArrayList<>();
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Card(rank, suit)); // Add each card to the deck
            }
        }
        shuffle(); // Shuffle the deck after initializing
    }

    // Method to shuffle the deck
    public void shuffle() {
        Collections.shuffle(deck);
    }

    // Method to draw a card from the deck
    public Card drawCard() {
        if (deck.isEmpty()) {
            System.out.println("Deck is empty, creating a new deck.");
            new Deck();
        }
        return deck.remove(deck.size() - 1); // Remove and return the top card
    }
}
