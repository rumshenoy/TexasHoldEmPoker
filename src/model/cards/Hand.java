package model.cards;

import java.util.ArrayList;
import java.util.List;

public class    Hand {
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<Card>(cards);
    }

    public Hand() {
        this(new ArrayList<Card>());
    }

    public List<Card> getCards() {
        return cards;
    }

    public int drawCardsFromDeck(int numCards, Deck deck) {
        int numDrawn = 0;
        while (!deck.isEmpty() && numDrawn < numCards) {
            this.cards.add(deck.drawCard());
            numDrawn++;
        }
        return numDrawn;
    }

    @Override
    public boolean equals(Object another) {
        if (another == null || !another.getClass().equals(this.getClass())) {
            return false;
        }
        Hand otherHand = (Hand)another;
        if (this.getCards().size() != otherHand.getCards().size()) {
            return false;
        }
        for (int i = 0; i < this.getCards().size(); i++) {
            Card myCard = this.getCards().get(i);
            Card otherCard = otherHand.getCards().get(i);
            if (!myCard.equals(otherCard)) {
                return false;
            }
        }
        return true;
    }
}
