package BlackJackSimulator.Model;

import BlackJackSimulator.View.Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private int count;
    private List<Card> cards = new ArrayList<Card>();

    private static Deck ourInstance = new Deck();

    public static Deck getInstance() {
        return ourInstance;
    }

    private Deck() {
        initialize();
    }

    public Card getCard() {
        // if deck runs out
        if(getSize()==0){
            initialize();
        }
        Card card = cards.get(0);
        cards.remove(0);

        // 2~6 = +1
        // 7~9 = 0
        // 10~A = -1
        if (card.getPoints() > 9) {
            count--;
        } else if (card.getPoints() < 7) {
            count ++;
        }
        return card;
    }

    private void initialize(){
        count = 0;

        cards.add(new Card(5,1));
        cards.add(new Card(10,1));
        cards.add(new Card(1,1));
        cards.add(new Card(10,1));
        cards.add(new Card(10,1));
        for (int decks = 0; decks < 6; decks++) {
            for (int i = 1; i <= 13; i++) {
                for (int j = 0; j < 4; j++) {
                    Card card = new Card(i, j);
                    cards.add(card);
                }
            }
        }
        //shuffle();
    }

    private void shuffle(){
        Collections.shuffle(cards);
    }

    public int getSize(){
        return cards.size();
    }

    public int getCount() {
        return count;
    }
}

