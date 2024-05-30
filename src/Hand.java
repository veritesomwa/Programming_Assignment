import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand; // List to store the cards in the hand

    // Constructor to initialize an empty hand
    public Hand() {
        hand = new ArrayList<>();
    }

    // Method to add a card to the hand
    public void addCard(Card card) {
        hand.add(card);
    }

    // Method to calculate the total points of the hand
    public int calculatePoints() {
        int totalPoints = 0;
        int acesCount = 0;

        for (Card card : hand) {
            int value = card.getValue();
            totalPoints += value;
            if (card.getRank().equals("A")) {
                acesCount++;
            }
        }

        // Adjust for Aces if total points exceed 21
        while (totalPoints > 21 && acesCount > 0) {
            totalPoints -= 10;
            acesCount--;
        }

        return totalPoints;
    }

    // Method to display all the cards in the hand
    public void display() {
        for (Card card : hand) {
            System.out.println(card);
        }
    }
}