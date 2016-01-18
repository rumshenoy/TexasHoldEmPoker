package model.cards;

import junit.framework.TestCase;

public class CardTest extends TestCase {
    public void testInitialization() {
        Card card = new Card(Card.Rank.Queen, Card.Suit.Clubs);
        assertEquals(card.getRank(), Card.Rank.Queen);
        assertEquals(card.getSuit(), Card.Suit.Clubs);

        card = new Card(Card.Rank.Three, Card.Suit.Spades);
        assertEquals(card.getRank(), Card.Rank.Three);
        assertEquals(card.getSuit(), Card.Suit.Spades);
    }

    public void testCompare() {
        Card kingOfClubs = new Card(Card.Rank.King, Card.Suit.Clubs);
        Card queenOfClubs = new Card(Card.Rank.Queen, Card.Suit.Clubs);
        Card jackOfClubs = new Card(Card.Rank.Jack, Card.Suit.Clubs);
        Card aceOfClubs = new Card(Card.Rank.Ace, Card.Suit.Clubs);
        Card twoOfDiamonds = new Card(Card.Rank.Two, Card.Suit.Diamonds);
        Card twoOfHearts = new Card(Card.Rank.Two, Card.Suit.Hearts);
        Card fourOfSpades = new Card(Card.Rank.Four, Card.Suit.Spades);

        assertTrue(kingOfClubs.compareTo(queenOfClubs) > 0);
        assertTrue(kingOfClubs.compareTo(jackOfClubs) > 0);
        assertTrue(kingOfClubs.compareTo(kingOfClubs) == 0);
        assertTrue(kingOfClubs.compareTo(aceOfClubs) < 0);

        assertTrue(jackOfClubs.compareTo(queenOfClubs) < 0);
        assertTrue(jackOfClubs.compareTo(fourOfSpades) > 0);

        assertTrue(aceOfClubs.compareTo(queenOfClubs) > 0);
        assertTrue(aceOfClubs.compareTo(twoOfHearts) > 0);

        assertTrue(twoOfHearts.compareTo(fourOfSpades) < 0);
        assertTrue(twoOfHearts.compareTo(twoOfDiamonds) == 0);

        assertEquals(kingOfClubs, new Card(Card.Rank.King, Card.Suit.Clubs));
        assertEquals(kingOfClubs, new Card(Card.Rank.King, Card.Suit.Spades));
    }
}