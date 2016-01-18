package model.poker.texasholdem;

import junit.framework.TestCase;
import model.cards.Card;
import model.cards.Hand;
import model.poker.PokerHand;

import java.util.Arrays;
import java.util.Collections;

public class TexasHoldEmPlayerTest extends TestCase {
    private TexasHoldEmPlayer player;
    private PokerHand straightFlushQueenHigh;

    public void setUp() {
        this.player = new TexasHoldEmPlayer("Player");
        this.straightFlushQueenHigh = new PokerHand(Arrays.asList(
                new Card(Card.Rank.Eight, Card.Suit.Clubs),
                new Card(Card.Rank.Nine, Card.Suit.Clubs),
                new Card(Card.Rank.Ten, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Clubs),
                new Card(Card.Rank.Queen, Card.Suit.Clubs)));
    }

    public void testInitialization() {
        assertEquals(this.player.getHand().getCards().size(), 0);

        TexasHoldEmPlayer jack = new TexasHoldEmPlayer("Jack");
        assertEquals(jack.getName(), "Jack");
    }

    public void testBestHand() {
        this.player.getHand().getCards().clear();
        this.player.getHand().getCards().addAll(Arrays.asList(
                new Card(Card.Rank.Jack, Card.Suit.Clubs),
                new Card(Card.Rank.Eight, Card.Suit.Clubs)));
        Hand board = new Hand(Arrays.asList(
                new Card(Card.Rank.Eight, Card.Suit.Diamonds),
                new Card(Card.Rank.Nine, Card.Suit.Clubs),
                new Card(Card.Rank.Ten, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Diamonds),
                new Card(Card.Rank.Queen, Card.Suit.Clubs)));
        PokerHand sortedHand = this.player.bestHandWithBoardCards(board.getCards());
        Collections.sort(sortedHand.getCards());
        assertTrue(sortedHand.equals(this.straightFlushQueenHigh));
    }

    public void testBestHandWithAllBoard() {
        this.player.getHand().getCards().clear();
        this.player.getHand().getCards().addAll(Arrays.asList(
                new Card(Card.Rank.Two, Card.Suit.Clubs),
                new Card(Card.Rank.Six, Card.Suit.Diamonds)));
        Hand board = this.straightFlushQueenHigh;
        PokerHand sortedHand = this.player.bestHandWithBoardCards(board.getCards());
        Collections.sort(sortedHand.getCards());
        assertTrue(sortedHand.equals(this.straightFlushQueenHigh));
    }
}
