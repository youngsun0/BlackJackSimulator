package BlackJackSimulator.Utilities;

import BlackJackSimulator.Model.*;

import java.util.List;

public class Strategy {

    /**
     * Basic Strategy for the player's use which takes into account the player's own cards and
     * the dealer's cards and makes the statistically best call in that scenario.
     * Used the table in https://wizardofodds.com/games/blackjack/strategy/4-decks/
     * @param playerCards, dealerCards
     * @return Action
     */
    public static Action basicStrategy(List<Card> playerCards, Card dealerCard) {
        Hand hand = HandCalculator.convertToHand(playerCards);
        HandType type = hand.getType();
        int value = hand.getValue();
        int d = dealerCard.getPoints();

        if (type == HandType.SPLITS) {
            // check for [A, A] specifically because it causes problems
            if (playerCards.get(0).getValue() == 1){
                return Action.SPLIT;
            } else if (value >= 4 && value <= 6) {
                if (d >= 2 && d <= 7) {
                    return Action.SPLIT;
                } else {
                    return Action.HIT;
                }
            } else if (value == 8) {
                if (d >= 2 && d <= 4) {
                    return Action.HIT;
                } else if (d >= 5 && d <= 6) {
                    return Action.SPLIT;
                } else {
                    return Action.HIT;
                }
            } else if (value == 10) {
                if (d >= 2 && d <= 9) {
                    return Action.DOUBLE;
                } else {
                    return Action.HIT;
                }
            } else if (value == 12) {
                // SPECIAL case where this could be 6,6 or AA
                // if pCards is A,A
                if (d >= 2 && d <= 6) {
                    return Action.SPLIT;
                } else {
                    return Action.HIT;
                }
            } else if (value == 14) {
                if (d >= 2 && d <= 7) {
                    return Action.SPLIT;
                } else {
                    return Action.HIT;
                }
            // if pair 8's or pair A's
            } else if (value == 16) {
                return Action.SPLIT;
            } else if (value == 18) {
                if (d >= 2 && d <= 6) {
                    return Action.SPLIT;
                } else if (d >= 8 && d <= 9) {
                    return Action.SPLIT;
                } else {
                    return Action.STAND;
                }
            } else {
                return Action.STAND;
            }
        } else if (type == HandType.SOFT) {
            if (value >= 13 && value <= 14) {
                if (d >= 5 && d <= 6) {
                    return Action.DOUBLE;
                } else {
                    return Action.HIT;
                }
            } else if (value >= 15 && value <= 16) {
                if (d >= 4 && d <= 6) {
                    return Action.DOUBLE;
                } else {
                    return Action.HIT;
                }
            } else if (value == 17) {
                if (d >= 3 && d <= 6) {
                    return Action.DOUBLE;
                } else {
                    return Action.HIT;
                }
            } else if (value == 18) {
                if (d >= 3 && d <= 6) {
                    return Action.DOUBLE;
                } else if (d == 2){
                    return Action.STAND;
                } else if (d >= 7 && d <= 8) {
                    return Action.STAND;
                } else {
                    return Action.HIT;
                }
            } else {
                return Action.STAND;
            }
        } else { // HandType is HARD
            if (value >= 4 && value <= 8) {
                return Action.HIT;
            } else if (value == 9) {
                if (d == 2) {
                    return Action.HIT;
                } else if (d >= 3 && d <= 6) {
                    return Action.DOUBLE;
                } else {
                    return Action.HIT;
                }
            } else if (value == 10) {
                if (d >= 2 && d <= 9) {
                    return Action.DOUBLE;
                } else {
                    return Action.HIT;
                }
            } else if (value == 11) {
                if (d >= 2 && d <= 10) {
                    return Action.DOUBLE;
                } else {
                    return Action.HIT;
                }
            } else if (value == 12) {
                if (d >= 4 && d <= 6) {
                    return Action.STAND;
                } else {
                    return Action.HIT;
                }
            } else if (value >= 13 && value <= 16) {
                if (d >= 2 && d <= 6) {
                    return Action.STAND;
                } else {
                    return Action.HIT;
                }
            } else {
                return Action.STAND;
            }
        }
    }
}
