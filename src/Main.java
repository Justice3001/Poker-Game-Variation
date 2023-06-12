import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String playAgain;
        boolean again;



        // loop that lets the user play again
        do
        {
            PokerGame game = new PokerGame();
            game.dealHands();
            game.displayHands();
            game.determineWinner();

            System.out.print("Want to play again? (y/n)");
            playAgain = sc.nextLine();


            /// Validate user input
            while (!playAgain.matches("[yYnN]")) {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
                playAgain = sc.nextLine();
            }

            again = playAgain.equalsIgnoreCase("y");
        } while (again);

        sc.close();
    }

}

// in the readme of this repo add simple introduction to poker and hand values and rank. aside from betting and rounds.
//Requires knowledge of poker hand card values.
//add more comments to all methods. be more specific to what each method does.