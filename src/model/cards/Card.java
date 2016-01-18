package model.cards;

public class Card implements Comparable<Card> {
    public enum Rank {
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine,
        Ten,
        Jack,
        Queen,
        King,
        Ace;

        public int asInteger() {
            return this.ordinal() + 2;
        }
    }

    public enum Suit {
        Clubs,
        Diamonds,
        Hearts,
        Spades
    }

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card another) {
        return this.getRank().compareTo(another.getRank());
    }

    @Override
    public boolean equals(Object another) {
        if (another == null || !another.getClass().equals(this.getClass())) {
            return false;
        }
        Card otherCard = (Card)another;
        return this.compareTo(otherCard) == 0;
    }
}
