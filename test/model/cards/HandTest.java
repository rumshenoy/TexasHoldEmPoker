package model.cards;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

public class HandTest extends TestCase {
    private Deck deck;
    private Hand hand;
    private Card queenOfClubs;
    private Card threeOfSpades;
    private Card jackOfDiamonds;
    private Card sevenOfHearts;

    @Override
    public void setUp() {
        this.deck = new Deck();
        this.hand = new Hand();
        this.queenOfClubs = new Card(Card.Rank.Queen, Card.Suit.Clubs);
        this.threeOfSpades = new Card(Card.Rank.Three, Card.Suit.Spades);
        this.jackOfDiamonds = new Card(Card.Rank.Jack, Card.Suit.Diamonds);
        this.sevenOfHearts = new Card(Card.Rank.Seven, Card.Suit.Hearts);
    }

    public void testInitialization() {
        assertEquals(this.hand.getCards().size(), 0);

        List<Card> params = Arrays.asList(this.queenOfClubs, this.threeOfSpades);
        Hand hand = new Hand(params);
        assertEquals(hand.getCards().size(), 2);
        assertTrue(hand.getCards().containsAll(params));

        params = Arrays.asList(this.queenOfClubs, this.jackOfDiamonds, this.sevenOfHearts, this.threeOfSpades);
        hand = new Hand(params);
        assertEquals(hand.getCards().size(), 4);
        assertTrue(hand.getCards().containsAll(params));
    }

    public void testNormalDraw() {
        this.hand.drawCardsFromDeck(2, this.deck);
        assertEquals(this.hand.getCards().size(), 2);

        // first two cards in a new, un-shuffled deck are the two of clubs and two of diamonds
        Card twoOfClubs = new Card(Card.Rank.Two, Card.Suit.Clubs);
        Card twoOfDiamonds = new Card(Card.Rank.Two, Card.Suit.Diamonds);
        assertTrue(this.hand.getCards().containsAll(Arrays.asList(twoOfClubs, twoOfDiamonds)));

        this.hand.drawCardsFromDeck(2, this.deck);
        assertEquals(this.hand.getCards().size(), 4);
        // next two cards are the other two twos
        Card twoOfHearts = new Card(Card.Rank.Two, Card.Suit.Hearts);
        Card twoOfSpades = new Card(Card.Rank.Two, Card.Suit.Spades);
        assertTrue(this.hand.getCards().containsAll(Arrays.asList(twoOfClubs, twoOfDiamonds, twoOfHearts, twoOfSpades)));
    }

    public void testLowCardDraw() {
        Hand bunkHand = new Hand();
        bunkHand.drawCardsFromDeck(50, this.deck);

        // only 52 cards in a deck, so we only draw two cards even though we asked for four
        int numDrawn = this.hand.drawCardsFromDeck(4 , this.deck);
        assertEquals(numDrawn, 2);
        assertEquals(this.hand.getCards().size(), 2);

        // and then we draw nothing
        numDrawn = this.hand.drawCardsFromDeck(4 , this.deck);
        assertEquals(numDrawn, 0);
        assertEquals(this.hand.getCards().size(), 2);
    }
}
