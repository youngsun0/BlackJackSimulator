package BlackJackSimulator.View;

import BlackJackSimulator.Model.Dealer;
import BlackJackSimulator.Model.Deck;
import BlackJackSimulator.Model.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Board {
    public static void display() {
        Player player = Player.getInstance();
        int playerBank = player.getBank();
        Deck deck = Deck.getInstance();

        int cardsLeft = deck.getSize();
        int size = Math.floorDiv(cardsLeft, 6);
        for (int j = 0; j < size; j++) {
            System.out.print("|");
        }
        for (int k = 0; k < 52 - size; k++) {
            System.out.print("-");
        }
        System.out.println();

        System.out.print("Cards Left: " + cardsLeft);
        System.out.print("      Count: " + deck.getCount());
        System.out.println("      Bank: " + playerBank);

        if (playerBank < 1) {
            System.out.println("You're BROKE! GAME OVER!!!");
        } else {
            player.setBetList(getBetAmount(playerBank));
            Dealer.getInstance().deal();
        }
    }

    /**
     * Get console input from user for how much they wish to bet
     * @return bet amount
     */
    private static int getBetAmount(int playerBank) {
        // Get input from user
        while (true) {
            try {
                System.out.print("How much to bet? : ");
                Scanner reader = new Scanner(System.in);  // Reading from System.in
                int n = reader.nextInt(); // Scans the next token of the input as an int.
                if (n < 1) {
                    throw new InputMismatchException();
                }
                if (n > playerBank) {
                    n = playerBank;
                }
                return n;
            } catch (InputMismatchException e) {}
        }
    }
}
