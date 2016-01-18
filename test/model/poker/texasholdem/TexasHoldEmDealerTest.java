package model.poker.texasholdem;

import junit.framework.TestCase;
import model.cards.Deck;

import java.util.Arrays;

public class TexasHoldEmDealerTest extends TestCase {
    private TexasHoldEmDealer dealer;
    private TexasHoldEmPlayer player1;
    private TexasHoldEmPlayer player2;

    public void setUp() {
        this.dealer = new TexasHoldEmDealer();
        this.player1 = new TexasHoldEmPlayer("Player 1");
        this.player2 = new TexasHoldEmPlayer("Player 2");
    }

    public void testInit() {
        // it should start them with a normal deck and an empty board
        assertEquals(this.dealer.getBoard().getCards().size(), 0);
        assertEquals(this.dealer.getDeck().getCards().size(), 52);
    }

    public void testDeal() {
        // it should be shuffled by the time it is dealing
        assertTrue(!this.dealer.getDeck().getCards().equals(new Deck().getCards()));

        // it should deal two cards to each hand
        this.dealer.dealToPlayers(Arrays.asList(this.player1, this.player2));
        assertEquals(this.player1.getHand().getCards().size(), 2);
        assertEquals(this.player2.getHand().getCards().size(), 2);
        assertEquals(this.dealer.getDeck().getCards().size(), 48);
    }

    public void testFlop() {
        // it should burn a card then deal three cards to the board
        this.dealer.flop();
        assertEquals(this.dealer.getBoard().getCards().size(), 3);
        assertEquals(this.dealer.getDeck().getCards().size(), 48);
    }

    public void testTurn() {
        this.dealer.flop();

        // it should burn a card then deal a card to the board
        this.dealer.turn();
        assertEquals(this.dealer.getBoard().getCards().size(), 4);
        assertEquals(this.dealer.getDeck().getCards().size(), 46);
    }

    public void testRiver() {
        this.dealer.flop();
        this.dealer.turn();

        // it should burn a card then deal a card to the board
        this.dealer.river();
        assertEquals(this.dealer.getBoard().getCards().size(), 5);
        assertEquals(this.dealer.getDeck().getCards().size(), 44);
    }
}
