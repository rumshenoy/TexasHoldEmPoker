package model.poker.texasholdem;

import model.cards.Card;
import model.cards.Hand;
import model.poker.PokerHand;
import model.poker.PokerHandUtility;

import java.util.ArrayList;
import java.util.List;

public class TexasHoldEmPlayer {

    String name;
    Hand hand;

    public TexasHoldEmPlayer(String name) {
        this.name = name;
        hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public PokerHand bestHandWithBoardCards(List<Card> cards) {
        //merge all cards to find the best hand
        ArrayList<Card> allCards = new ArrayList<>();
        allCards.addAll(hand.getCards());
        allCards.addAll(cards);
        PokerHandUtility pokerHandUtility = new PokerHandUtility();
        ArrayList<Card> sameSuitCards = pokerHandUtility.checkCardsOfTheSameSuit(allCards, 5);


        if (sameSuitCards != null) {
            ArrayList<Card> sequence = pokerHandUtility.checkCardsInASequence(sameSuitCards, 5);
            if (sequence != null) {
                return new PokerHand(sequence);
            }

        }

        List<Card> sameRank = pokerHandUtility.checkFourOfAKind(allCards);
        if (sameRank != null) {
            return new PokerHand(sameRank);
        }

        List<Card> threeOfAKind = pokerHandUtility.checkThreeOfAKind(allCards);
        if (threeOfAKind != null) {
            List<Card> remainingCards = pokerHandUtility.filterAndReturnNewList(allCards, threeOfAKind);
            List<Card> twoOfAKind = pokerHandUtility.checkTwoOfAKind(remainingCards);
            if (twoOfAKind != null) {
                threeOfAKind.addAll(twoOfAKind);
                return new PokerHand(threeOfAKind);
            }
        }


        if (sameSuitCards != null) {
            return new PokerHand(sameSuitCards);
        }

        ArrayList<Card> straight = pokerHandUtility.checkCardsInASequence(allCards, 5);
        if (straight != null) {
            return new PokerHand(straight);
        }

        if (threeOfAKind != null) {
            return new PokerHand(threeOfAKind);
        }


        List<Card> twoOfAKind = pokerHandUtility.checkTwoOfAKind(allCards);
        if (twoOfAKind != null) {
            List<Card> remainingCards = pokerHandUtility.filterAndReturnNewList(allCards, twoOfAKind);
            if (remainingCards != null) {
                List<Card> twoMoreOfAnotherKind = pokerHandUtility.checkTwoOfAKind(remainingCards);
                twoOfAKind.addAll(twoMoreOfAnotherKind);
            }
            return new PokerHand(twoOfAKind);
        }


        if (twoOfAKind != null) {
            return new PokerHand(twoOfAKind);
        }

        Card highCard = pokerHandUtility.getHighCard(hand.getCards());
        List<Card> finalHand = new ArrayList<Card>();
        finalHand.add(highCard);
        return new PokerHand(finalHand);
    }


}
