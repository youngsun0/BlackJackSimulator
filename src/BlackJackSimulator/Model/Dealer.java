package BlackJackSimulator.Model;

import BlackJackSimulator.Utilities.HandCalculator;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private List<Card> cards;
    private static Dealer ourInstance = new Dealer();
    private int pot;

    public static Dealer getInstance() {
        return ourInstance;
    }

    private Dealer() {
        pot = 0;
    }

    public int hit() {
        Deck deck = Deck.getInstance();
        // keep hitting while dealer's hand points is under 17 (dealer stands on soft 17)
        while (HandCalculator.calculatePoints(cards) < 17) {
            cards.add(deck.getCard());
        }
        System.out.println("Dealer: " + cards);
        return HandCalculator.calculatePoints(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getPot() {
        return pot;
    }

    /**
     * Dealer deals himself a card then gets player to deal himself 2 cards
     */
    public void deal() {
        cards = new ArrayList<>();
        cards.add(Deck.getInstance().getCard());
        Player.getInstance().deal();
    }
}
