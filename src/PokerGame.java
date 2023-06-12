import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PokerGame
{
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

    private int evaluateHand(List<Card> hand) {
        if (isRoyalFlush(hand)) {
            return 10; // Royal Flush
        } else if (isStraightFlush(hand)) {
            return 9; // Straight Flush
        } else if (isFourOfAKind(hand)) {
            return 8; // Four of a Kind
        } else if (isFullHouse(hand)) {
            return 7; // Full House
        } else if (isFlush(hand)) {
            return 6; // Flush
        } else if (isStraight(hand)) {
            return 5; // Straight
        } else if (isThreeOfAKind(hand)) {
            return 4; // Three of a Kind
        } else if (isTwoPair(hand)) {
            return 3; // Two Pair
        } else if (isPair(hand)) {
            return 2; // Pair
        } else {
            return 1; // High Card
        }
    }

    private boolean isRoyalFlush(List<Card> hand) {
        // Check if the hand is a Royal Flush
        // Royal Flush: A, K, Q, J, 10 of the same suit
        return isStraightFlush(hand) && containsRank(hand, "Ace") && containsRank(hand, "King");
    }

    private boolean isStraightFlush(List<Card> hand) {
        // Check if the hand is a Straight Flush
        // Straight Flush: Five consecutive cards of the same suit
        return isFlush(hand) && isStraight(hand);
    }

    private boolean isFourOfAKind(List<Card> hand) {
        // Check if the hand is Four of a Kind
        // Four of a Kind: Four cards of the same rank
        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.get(i);
            int count = 1;
            for (int j = 0; j < hand.size(); j++) {
                if (i != j && currentCard.getRank().equals(hand.get(j).getRank())) {
                    count++;
                }
            }
            if (count == 4) {
                return true;
            }
        }
        return false;
    }

    private boolean isFullHouse(List<Card> hand) {
        // Check if the hand is a Full House
        // Full House: Three cards of the same rank and a pair
        boolean hasThreeOfAKind = false;
        boolean hasPair = false;

        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.get(i);
            int count = 1;
            for (int j = 0; j < hand.size(); j++) {
                if (i != j && currentCard.getRank().equals(hand.get(j).getRank())) {
                    count++;
                }
            }
            if (count == 3) {
                hasThreeOfAKind = true;
            } else if (count == 2) {
                hasPair = true;
            }
        }

        return hasThreeOfAKind && hasPair;
    }

    private boolean isFlush(List<Card> hand) {
        // Check if the hand is a Flush
        // Flush: All cards of the same suit
        String suit = hand.get(0).getSuit();
        for (int i = 1; i < hand.size(); i++) {
            if (!hand.get(i).getSuit().equals(suit)) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight(List<Card> hand) {
        // Check if the hand is a Straight
        // Straight: Five consecutive cards of any suit
        List<String> ranks = new ArrayList<>();
        for (Card card : hand) {
            ranks.add(card.getRank());
        }
        Collections.sort(ranks);

        for (int i = 0; i < ranks.size() - 1; i++) {
            int currentRankIndex = getIndex(ranks.get(i));
            int nextRankIndex = getIndex(ranks.get(i + 1));
            if (nextRankIndex - currentRankIndex != 1) {
                return false;
            }
        }

        return true;
    }

    private int getIndex(String rank) {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        for (int i = 0; i < ranks.length; i++) {
            if (rank.equals(ranks[i])) {
                return i;
            }
        }
        return -1;
    }

    private boolean isThreeOfAKind(List<Card> hand) {
        // Check if the hand is Three of a Kind
        // Three of a Kind: Three cards of the same rank
        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.get(i);
            int count = 1;
            for (int j = 0; j < hand.size(); j++) {
                if (i != j && currentCard.getRank().equals(hand.get(j).getRank())) {
                    count++;
                }
            }
            if (count == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean isTwoPair(List<Card> hand) {
        // Check if the hand is Two Pair
        // Two Pair: Two cards of one rank and two cards of another rank
        int pairCount = 0;

        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.get(i);
            int count = 1;
            for (int j = 0; j < hand.size(); j++) {
                if (i != j && currentCard.getRank().equals(hand.get(j).getRank())) {
                    count++;
                }
            }
            if (count == 2) {
                pairCount++;
            }
        }

        return pairCount == 2;
    }

    private boolean isPair(List<Card> hand) {
        // Check if the hand is a Pair
        // Pair: Two cards of the same rank
        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.get(i);
            int count = 1;
            for (int j = 0; j < hand.size(); j++) {
                if (i != j && currentCard.getRank().equals(hand.get(j).getRank())) {
                    count++;
                }
            }
            if (count == 2) {
                return true;
            }
        }
        return false;
    }

    private boolean containsRank(List<Card> hand, String rank) {
        for (Card card : hand) {
            if (card.getRank().equals(rank)) {
                return true;
            }
        }
        return false;
    }


    public void determineWinner() {
        int playerRank = evaluateHand(playerHand);
        int computerRank = evaluateHand(computerHand);

        System.out.println("\nPlayer hand rank: " + playerRank);
        System.out.println("Computer hand rank: " + computerRank);

        if (playerRank > computerRank) {
            System.out.println("Player wins!");
        } else if (playerRank < computerRank) {
            System.out.println("Computer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public void playAgain()
    {
        Scanner sc=new Scanner(System.in);
        String playAgain= String.valueOf('y');
        boolean again=false;

        //loop that lets user play again
        do
        {
            PokerGame game = new PokerGame();
            game.dealHands();
            game.displayHands();
            game.determineWinner();

            System.out.print("Want to play again? (y/n)");
            playAgain = sc.nextLine();

            //user input validation
            if (playAgain.equalsIgnoreCase("y")) {
                again = true;
            } else if (playAgain.equalsIgnoreCase("n")) {
                System.out.println("Okay, bye! Thanks for playing.");
                again = false;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
                again = true;
            }
        } while (again);

        sc.close();
    }






}
