public class Card {
    private String rank; // The rank of the card (e.g., 2, 3, ..., 10, J, Q, K, A)
    private String suit; // The suit of the card (e.g., Hearts, Diamonds, Clubs, Spades)

    // Constructor to initialize a card with a specific rank and suit
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Getter method for the rank of the card
    public String getRank() {
        return rank;
    }

    // Getter method for the suit of the card
    public String getSuit() {
        return suit;
    }

    // Method to get the value of the card based on Blackjack rules
    public int getValue() {
        switch (rank) {
            case "A":
                return 11; // Ace can be 11 or 1, initially treated as 11
            case "K":
            case "Q":
            case "J":
                return 10; // Face cards are worth 10 points
            default:
                return Integer.parseInt(rank); // Numeric cards are worth their face value
        }
    }

    // Override the toString method to provide a readable representation of the card
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
