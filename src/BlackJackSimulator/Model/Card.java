package BlackJackSimulator.Model;


public class Card {

    public final static int SPADES = 0,       // Codes for the 4 suits.
            HEARTS = 1,
            DIAMONDS = 2,
            CLUBS = 3;

    private final int suit;   // The suit of this card, one of the constants

    private final int value;  // The value of this card, from 1 to 13.

    public Card(int _value, int _suit) {
        // Construct a card with the specified value and suit.
        // Value must be between 1 and 13.  Suit must be between
        // 0 and 3.  If the parameters are outside these ranges,
        // the constructed card object will be invalid.
        value = _value;
        suit = _suit;
    }

    public int getSuit() {
        // Return the int that codes for this card's suit.
        return suit;
    }

    public int getValue() {
        // Return the int that codes for this card's value.
        return value;
    }

    public String getSuitAsString() {
        // Return a String representing the card's suit.
        // (If the card's suit is invalid, "??" is returned.)
        switch ( suit ) {
            case SPADES:   return "S";
            case HEARTS:   return "H";
            case DIAMONDS: return "D";
            case CLUBS:    return "C";
            default:       return "??";
        }
    }

    public int getPoints() {
        // Return the number of value that card is worth
        if (value == 1) {
            return 11;
        } else if (value >=10 && value <= 13) {
            return 10;
        } else {
            return value;
        }
    }

    public String getValueAsString() {
        // Return a String representing the card's value.
        // If the card's value is invalid, "??" is returned.
        switch ( value ) {
            case 1:   return "A";
            case 2:   return "2";
            case 3:   return "3";
            case 4:   return "4";
            case 5:   return "5";
            case 6:   return "6";
            case 7:   return "7";
            case 8:   return "8";
            case 9:   return "9";
            case 10:  return "10";
            case 11:  return "J";
            case 12:  return "Q";
            case 13:  return "K";
            default:  return "??";
        }
    }

    public String toString() {
        // Return a String representation of this card, such as
        // "10 of Hearts" or "Queen of Spades".
        return getValueAsString()+getSuitAsString();
    }


} // end class Card