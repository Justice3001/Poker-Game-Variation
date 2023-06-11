import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Card {
    private final String rank;
    private final String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};

        for (String suit : suits) {
            for (String rank : ranks) {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }
}

public class PokerGame {
    private final Deck deck;
    private final List<Card> playerHand;
    private final List<Card> computerHand;

    public PokerGame() {
        deck = new Deck();
        playerHand = new ArrayList<>();
        computerHand = new ArrayList<>();
    }

    public void dealHands() {
        deck.shuffle();
        for (int i = 0; i < 5; i++) {
            playerHand.add(deck.drawCard());
            computerHand.add(deck.drawCard());
        }
    }

    public void displayHands() {
        System.out.println("Player hand:");
        for (Card card : playerHand) {
            System.out.println(card);
        }

        System.out.println("\nComputer hand:");
        for (Card card : computerHand) {
            System.out.println(card);
        }
    }

    public static void main(String[] args) {
        PokerGame game = new PokerGame();
        game.dealHands();
        game.displayHands();
    }
}
