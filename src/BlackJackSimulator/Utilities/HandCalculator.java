package BlackJackSimulator.Utilities;

import BlackJackSimulator.Model.Card;
import BlackJackSimulator.Model.Hand;
import BlackJackSimulator.Model.HandType;

import java.util.List;

public class HandCalculator {

    /**
     * Takes a list of Card objs and returns a Hand representing the number of points its worth and its HandType
     * Used for Basic strategy
     * It is necessary to return Type as well as Points because Basic strat relies on the hand type to make a judgement
     * @param cards
     * @return Hand
     */
    public static Hand convertToHand(List<Card> cards) {

        int aceCount = 0;
        int points = 0;
        HandType type = null;

        // Calculate points
        for (Card c : cards) {
            if (c.getValue() == 1) {
                aceCount++;
            }
            points += c.getPoints();
        }
        while (points>21 && aceCount>0) {
            points -= 10;
            aceCount--;
        }

        // Work out the type of hand
        // if cards are a pair of the same card
        if (cards.size() == 2 && (cards.get(0).getPoints() == cards.get(1).getPoints())) {
            type = HandType.SPLITS;
        } else if (aceCount>0) {
            type = HandType.SOFT;
        } else {
            type = HandType.HARD;
        }
        return new Hand(type, points);
    }

    /**
     * Takes in a list of cards and returns an int representing the total points that list of cards is worth
     * Way to quickly see what the worth of a hand is without caring about the hand type
     * @param cards
     * @return points
     */
    public static int calculatePoints(List<Card> cards) {

        int aceCount = 0;
        int points = 0;
        HandType type = null;

        // Calculate points
        for (Card c : cards) {
            if (c.getValue() == 1) {
                aceCount++;
            }
            points += c.getPoints();
        }
        while (points>21 && aceCount>0) {
            points -= 10;
            aceCount--;
        }

        return points;
    }


}
