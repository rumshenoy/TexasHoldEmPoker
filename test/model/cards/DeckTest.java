package model.cards;

import junit.framework.TestCase;

import java.util.*;

public class DeckTest extends TestCase {
    private Deck deck;

    public void setUp() {
        this.deck = new Deck();
    }

    public void testInitialization() {
        assertEquals(this.deck.getCards().size(), 52);
        assertEquals(this.deck.getCards(), new Deck().getCards());
        assertEquals(this.deck.getCards().get(0).getClass(), Card.class);

        Set<Card.Suit> suits = new HashSet<Card.Suit>();
        for (Card card : this.deck.getCards()) {
            suits.add(card.getSuit());
        }
        assertEquals(suits.size(), 4);
        List<Card.Suit> allSuits = Arrays.asList(Card.Suit.Clubs, Card.Suit.Diamonds, Card.Suit.Hearts, Card.Suit.Spades);
        assertTrue(suits.containsAll(allSuits));

        Set<Card.Rank> ranks = new HashSet<Card.Rank>();
        for (Card card : this.deck.getCards()) {
            ranks.add(card.getRank());
        }
        assertEquals(ranks.size(), 13);
        List<Card.Rank> allRanks = Arrays.asList(Card.Rank.Two, Card.Rank.Three,
                Card.Rank.Four, Card.Rank.Five, Card.Rank.Six, Card.Rank.Seven,
                Card.Rank.Eight, Card.Rank.Nine, Card.Rank.Ten, Card.Rank.Jack,
                Card.Rank.Queen, Card.Rank.King, Card.Rank.Ace);
        assertTrue(ranks.containsAll(allRanks));
    }

    public void testShuffle() {
        List<Card> cards = new ArrayList<Card>(this.deck.getCards());
        this.deck.shuffle();
        List<Card> shuffledCards = new ArrayList<Card>(this.deck.getCards());
        assertTrue(!cards.equals(shuffledCards));
    }

    public void testDraw() {
        Card firstCard = this.deck.getCards().get(0);
        List<Card> expectedCards = new ArrayList<Card>(this.deck.getCards());
        expectedCards.remove(0);
        assertEquals(this.deck.drawCard(), firstCard);
        assertEquals(this.deck.getCards(), expectedCards);
    }
}
