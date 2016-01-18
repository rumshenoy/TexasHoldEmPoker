package model.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        List<Card> mCards = new ArrayList<Card>();
        for (Card.Rank rank : Card.Rank.values()) {
            for (Card.Suit suit : Card.Suit.values()) {
                mCards.add(new Card(rank, suit));
            }
        }
        this.cards = mCards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isEmpty() {
        return cards.size() == 0;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.remove(0);
    }
}
