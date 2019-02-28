package BlackJackSimulator.Model;

import BlackJackSimulator.Utilities.HandCalculator;
import BlackJackSimulator.Utilities.Strategy;
import BlackJackSimulator.View.Board;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int bank;
    private List<Integer> betList;
    private List<List<Card>> cardsList;
    private int pos;

    private static Player ourInstance = new Player();

    public static Player getInstance() {
        return ourInstance;
    }

    private Player() {
        bank = 1000;
    }

    public List<List<Card>> getCardsList() {
        return cardsList;
    }

    private void decision() {
        Dealer dealer = Dealer.getInstance();
        Card dealerCard = dealer.getCards().get(0);
        Action action;

        // Check if player got a Black Jack first
        if (HandCalculator.calculatePoints(cardsList.get(0)) == 21) {
            double bet = (double)betList.get(0) * 1.5;
            betList.set(0, (int)bet);
        }

        while (pos < cardsList.size()) {
            action = Strategy.basicStrategy(cardsList.get(pos), dealerCard);
            if (action == Action.HIT) {
                hit();
            } else if (action == Action.DOUBLE) {
                doubleDown();
            } else if (action == Action.SPLIT) {

                if (cardsList.size() <= 3) {
                    split();
                } else {
                    hit();
                }
            } else if (action == Action.STAND){
                stand();
            } else {
                System.out.println("Error: action " + action + " unknowns");
            }
        }
        int dealerPoints = dealer.hit();
        payout(dealerPoints);
    }

    private void payout(int dealerPoints) {
        int payment = 0;
        for (int i = 0; i < pos; i++) {
            int playerPoints = HandCalculator.calculatePoints(cardsList.get(i));
            int bet = betList.get(i);
            if (playerPoints > 21) {
                payment -= bet;
            } else if (dealerPoints > 21) {
                payment += bet;
            } else if (playerPoints > dealerPoints) {
                payment += bet;
            } else if (playerPoints < dealerPoints) {
                payment -= bet;
            }
        }
        bank += payment;
        if (payment > 0) {
            System.out.println("You Won $" + payment);
        } else if (payment < 0) {
            System.out.println("You Lost $" + -payment);
        } else {
            System.out.println("You Broke Even");
        }

        System.out.println();
        Board.display();
    }

    public void deal() {
        pos = 0;
        cardsList = new ArrayList<>();
        cardsList.add(new ArrayList<>());
        hit();
        hit();
        decision();
    }

    private void hit() {
        Deck deck = Deck.getInstance();
        cardsList.get(pos).add(deck.getCard());
    }

    private void doubleDown() {
        hit();
        betList.set(pos, betList.get(pos)*2);
        stand();
    }

    private void split() {
        Card tempCard = cardsList.get(pos).get(1);
        cardsList.get(pos).remove(1);
        List<Card> newHand = new ArrayList<>();
        newHand.add(tempCard);
        cardsList.add(newHand);

        betList.add(betList.get(pos));
    }

    private void stand() {
        System.out.println("Player: " + cardsList.get(pos));
        pos++;
    }

    public int getBank() {
        return bank;
    }

    public void setBetList(int betList) {
        this.betList = new ArrayList<>();
        this.betList.add(betList);
    }
}