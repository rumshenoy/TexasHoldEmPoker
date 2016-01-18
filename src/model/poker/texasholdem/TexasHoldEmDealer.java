package model.poker.texasholdem;


import model.cards.Card;
import model.cards.Deck;
import model.cards.Hand;

import java.util.List;

public class TexasHoldEmDealer {
    private Hand board;
    private Deck deck;

    public Hand getBoard() {
        return board;
    }

    public Deck getDeck() {
        return deck;
    }

    public TexasHoldEmDealer() {
        this.board = new Hand();
        this.deck = new Deck();
        prepareDeckToDeal();
    }

    private void prepareDeckToDeal() {
        this.deck.shuffle();
    }

    public void dealToPlayers(List<TexasHoldEmPlayer> texasHoldEmPlayers) {
        for (TexasHoldEmPlayer player : texasHoldEmPlayers) {
            Card firstCard = deck.drawCard();
            Card secondCard = deck.drawCard();

            player.getHand().getCards().add(firstCard);
            player.getHand().getCards().add(secondCard);
        }
    }

    public void flop() {
        //remove card for "burn"
        deck.drawCard();

        //deal three cards on the board
        this.board.getCards().add(deck.drawCard());
        this.board.getCards().add(deck.drawCard());
        this.board.getCards().add(deck.drawCard());

    }

    public void turn() {
        deck.drawCard();
        this.board.getCards().add(deck.drawCard());
    }

    public void river() {
        deck.drawCard();
        this.board.getCards().add(deck.drawCard());
    }
}
