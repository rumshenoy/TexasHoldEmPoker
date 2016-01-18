package model.poker;

import junit.framework.TestCase;
import model.cards.Card;

import java.util.Arrays;


public class PokerHandTest extends TestCase {
    private PokerHand straightFlushQueenHigh;
    private PokerHand fourJacksAndAQueen;
    private PokerHand fullHouseKingsAndSixes;
    private PokerHand flushKingHigh;
    private PokerHand straightQueenHigh;
    private PokerHand threeFours;
    private PokerHand twoFoursTwoJacks;
    private PokerHand onePairOfKings;
    private PokerHand jackHigh;
    private PokerHand sevenHigh;

    public void setUp() {
        this.straightFlushQueenHigh = new PokerHand(Arrays.asList(
                new Card(Card.Rank.Eight, Card.Suit.Clubs),
                new Card(Card.Rank.Nine, Card.Suit.Clubs),
                new Card(Card.Rank.Ten, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Clubs),
                new Card(Card.Rank.Queen, Card.Suit.Clubs)));
        this.fourJacksAndAQueen = new PokerHand(Arrays.asList(
                new Card(Card.Rank.Jack, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Diamonds),
                new Card(Card.Rank.Jack, Card.Suit.Hearts),
                new Card(Card.Rank.Jack, Card.Suit.Spades),
                new Card(Card.Rank.Queen, Card.Suit.Clubs)));
        this.fullHouseKingsAndSixes = new PokerHand(Arrays.asList(
                new Card(Card.Rank.King, Card.Suit.Hearts),
                new Card(Card.Rank.King, Card.Suit.Spades),
                new Card(Card.Rank.King, Card.Suit.Clubs),
                new Card(Card.Rank.Six, Card.Suit.Clubs),
                new Card(Card.Rank.Six, Card.Suit.Diamonds)));
        this.flushKingHigh = new PokerHand(Arrays.asList(
                new Card(Card.Rank.Eight, Card.Suit.Clubs),
                new Card(Card.Rank.Nine, Card.Suit.Clubs),
                new Card(Card.Rank.King, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Clubs),
                new Card(Card.Rank.Queen, Card.Suit.Clubs)));
        this.straightQueenHigh = new PokerHand(Arrays.asList(
                new Card(Card.Rank.Eight, Card.Suit.Diamonds),
                new Card(Card.Rank.Nine, Card.Suit.Clubs),
                new Card(Card.Rank.Ten, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Clubs),
                new Card(Card.Rank.Queen, Card.Suit.Clubs)));
        this.threeFours = new PokerHand(Arrays.asList(
                new Card(Card.Rank.Four, Card.Suit.Hearts),
                new Card(Card.Rank.Four, Card.Suit.Spades),
                new Card(Card.Rank.Four, Card.Suit.Clubs),
                new Card(Card.Rank.Six, Card.Suit.Clubs),
                new Card(Card.Rank.King, Card.Suit.Diamonds)));
        this.twoFoursTwoJacks = new PokerHand(Arrays.asList(
                new Card(Card.Rank.Four, Card.Suit.Hearts),
                new Card(Card.Rank.Four, Card.Suit.Spades),
                new Card(Card.Rank.King, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Diamonds)));
        this.onePairOfKings = new PokerHand(Arrays.asList(
                new Card(Card.Rank.King, Card.Suit.Hearts),
                new Card(Card.Rank.King, Card.Suit.Spades),
                new Card(Card.Rank.Three, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Clubs),
                new Card(Card.Rank.Seven, Card.Suit.Diamonds)));
        this.jackHigh = new PokerHand(Arrays.asList(
                new Card(Card.Rank.Four, Card.Suit.Hearts),
                new Card(Card.Rank.Six, Card.Suit.Spades),
                new Card(Card.Rank.Three, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Clubs),
                new Card(Card.Rank.Seven, Card.Suit.Diamonds)));
        this.sevenHigh = new PokerHand(Arrays.asList(
                new Card(Card.Rank.Two, Card.Suit.Hearts),
                new Card(Card.Rank.Three, Card.Suit.Spades),
                new Card(Card.Rank.Four, Card.Suit.Clubs),
                new Card(Card.Rank.Five, Card.Suit.Clubs),
                new Card(Card.Rank.Seven, Card.Suit.Diamonds)));
    }

    public void testHandType() {
        assertEquals(this.straightFlushQueenHigh.getHandType(), PokerHand.Type.StraightFlush);
        assertEquals(this.fourJacksAndAQueen.getHandType(), PokerHand.Type.FourOfAKind);
        assertEquals(this.fullHouseKingsAndSixes.getHandType(), PokerHand.Type.FullHouse);
        assertEquals(this.flushKingHigh.getHandType(), PokerHand.Type.Flush);
        assertEquals(this.straightQueenHigh.getHandType(), PokerHand.Type.Straight);
        assertEquals(this.threeFours.getHandType(), PokerHand.Type.ThreeOfAKind);
        assertEquals(this.twoFoursTwoJacks.getHandType(), PokerHand.Type.TwoPair);
        assertEquals(this.onePairOfKings.getHandType(), PokerHand.Type.OnePair);
        assertEquals(this.jackHigh.getHandType(), PokerHand.Type.HighCard);
        assertEquals(this.sevenHigh.getHandType(), PokerHand.Type.HighCard);
    }

    public void testSimpleComparisons() {
        assertTrue(this.straightFlushQueenHigh.compareTo(this.fourJacksAndAQueen) > 0);
        assertTrue(this.fourJacksAndAQueen.compareTo(this.fullHouseKingsAndSixes) > 0);
        assertTrue(this.fullHouseKingsAndSixes.compareTo(this.flushKingHigh) > 0);
        assertTrue(this.flushKingHigh.compareTo(this.straightQueenHigh) > 0);
        assertTrue(this.straightQueenHigh.compareTo(this.threeFours) > 0);
        assertTrue(this.threeFours.compareTo(this.twoFoursTwoJacks) > 0);
        assertTrue(this.twoFoursTwoJacks.compareTo(this.onePairOfKings) > 0);
        assertTrue(this.onePairOfKings.compareTo(this.jackHigh) > 0);
        assertTrue(this.jackHigh.compareTo(this.sevenHigh) > 0);
    }

    public void testStraightComparisons() {
        PokerHand straightFlushKingHigh = new PokerHand(Arrays.asList(
                new Card(Card.Rank.King, Card.Suit.Clubs),
                new Card(Card.Rank.Nine, Card.Suit.Clubs),
                new Card(Card.Rank.Ten, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Clubs),
                new Card(Card.Rank.Queen, Card.Suit.Clubs)));
        assertTrue(straightFlushKingHigh.compareTo(this.straightFlushQueenHigh) > 0);


        PokerHand straightKingHigh = new PokerHand(Arrays.asList(
                new Card(Card.Rank.King, Card.Suit.Diamonds),
                new Card(Card.Rank.Nine, Card.Suit.Clubs),
                new Card(Card.Rank.Ten, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Clubs),
                new Card(Card.Rank.Queen, Card.Suit.Clubs)));
        assertTrue(straightKingHigh.compareTo(this.straightQueenHigh) > 0);
    }

    public void testFlushComparisons() {
        PokerHand flushQueenHigh = new PokerHand(Arrays.asList(
                new Card(Card.Rank.Eight, Card.Suit.Clubs),
                new Card(Card.Rank.Nine, Card.Suit.Clubs),
                new Card(Card.Rank.Three, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Clubs),
                new Card(Card.Rank.Queen, Card.Suit.Clubs)));
        PokerHand flushKingHighThenJack = new PokerHand(Arrays.asList(
                new Card(Card.Rank.Eight, Card.Suit.Clubs),
                new Card(Card.Rank.Nine, Card.Suit.Clubs),
                new Card(Card.Rank.King, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Clubs),
                new Card(Card.Rank.Ten, Card.Suit.Clubs)));
        assertTrue(flushQueenHigh.compareTo(this.flushKingHigh) < 0);
        assertTrue(flushKingHighThenJack.compareTo(this.flushKingHigh) < 0);
        assertTrue(flushQueenHigh.compareTo(flushKingHighThenJack) < 0);
    }

    public void testXOfAKindComparisons() {
        PokerHand threeSevensNineHigh = new PokerHand(Arrays.asList(
                new Card(Card.Rank.Seven, Card.Suit.Hearts),
                new Card(Card.Rank.Seven, Card.Suit.Spades),
                new Card(Card.Rank.Seven, Card.Suit.Clubs),
                new Card(Card.Rank.Six, Card.Suit.Clubs),
                new Card(Card.Rank.Nine, Card.Suit.Diamonds)));
        PokerHand threeSevensEightHigh = new PokerHand(Arrays.asList(
                new Card(Card.Rank.Seven, Card.Suit.Hearts),
                new Card(Card.Rank.Seven, Card.Suit.Spades),
                new Card(Card.Rank.Seven, Card.Suit.Clubs),
                new Card(Card.Rank.Six, Card.Suit.Clubs),
                new Card(Card.Rank.Eight, Card.Suit.Diamonds)));
        assertTrue(threeSevensNineHigh.compareTo(this.threeFours) > 0);
        assertTrue(threeSevensNineHigh.compareTo(threeSevensEightHigh) > 0);
        assertTrue(this.threeFours.compareTo(threeSevensEightHigh) < 0);

        PokerHand fullHouseJacksAndAces = new PokerHand(Arrays.asList(
                new Card(Card.Rank.Jack, Card.Suit.Hearts),
                new Card(Card.Rank.Jack, Card.Suit.Spades),
                new Card(Card.Rank.Jack, Card.Suit.Clubs),
                new Card(Card.Rank.Ace, Card.Suit.Clubs),
                new Card(Card.Rank.Ace, Card.Suit.Diamonds)));
        PokerHand fullHouseKingsAndNines = new PokerHand(Arrays.asList(
                new Card(Card.Rank.King, Card.Suit.Hearts),
                new Card(Card.Rank.King, Card.Suit.Spades),
                new Card(Card.Rank.King, Card.Suit.Clubs),
                new Card(Card.Rank.Nine, Card.Suit.Clubs),
                new Card(Card.Rank.Nine, Card.Suit.Diamonds)));
        assertTrue(fullHouseKingsAndNines.compareTo(fullHouseJacksAndAces) > 0);
        assertTrue(fullHouseKingsAndNines.compareTo(this.fullHouseKingsAndSixes) > 0);
        assertTrue(this.fullHouseKingsAndSixes.compareTo(fullHouseJacksAndAces) > 0);
    }

    public void testTrueTieComparisons() {
        PokerHand twoFoursTwoJacksAgain = new PokerHand(Arrays.asList(
                new Card(Card.Rank.Four, Card.Suit.Hearts),
                new Card(Card.Rank.Four, Card.Suit.Spades),
                new Card(Card.Rank.King, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Clubs),
                new Card(Card.Rank.Jack, Card.Suit.Diamonds)));
        assertTrue(twoFoursTwoJacksAgain.compareTo(this.twoFoursTwoJacks) == 0);
    }
}
