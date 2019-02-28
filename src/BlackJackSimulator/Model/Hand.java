package BlackJackSimulator.Model;

public class Hand {

    HandType type;
    int value;

    public Hand(HandType type, int value) {
        this.type = type;
        this.value = value;
    }

    public HandType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}

