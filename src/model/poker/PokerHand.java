package model.poker;


import model.cards.Card;
import model.cards.Hand;

import java.util.ArrayList;
import java.util.List;

public class PokerHand extends Hand {

    PokerHandUtility pokerHandUtility;

    public Type getHandType() {
        List<Card> cards = this.getCards();
        ArrayList<Card> sameSuitCards = pokerHandUtility.checkCardsOfTheSameSuit(cards, 5);

        if (sameSuitCards != null) {
            ArrayList<Card> sequence = pokerHandUtility.checkCardsInASequence(sameSuitCards, 5);
            if (sequence != null) {
                return Type.StraightFlush;
            }

        }

        ArrayList<Card> sameRank = pokerHandUtility.checkCardsOfTheSameRank(cards, 4);
        if (sameRank != null) {
            return Type.FourOfAKind;
        }

        List<Card> threeOfAKind = pokerHandUtility.checkThreeOfAKind(cards);
        if (threeOfAKind != null) {
            List<Card> copy = pokerHandUtility.filterAndReturnNewList(this.getCards(), threeOfAKind);
            List<Card> twoOfAKind = pokerHandUtility.checkTwoOfAKind(copy);
            if (twoOfAKind != null) {
                return Type.FullHouse;
            }
        }


        if (sameSuitCards != null) {
            return Type.Flush;
        }

        ArrayList<Card> straight = pokerHandUtility.checkCardsInASequence(cards, 5);
        if (straight != null) {
            return Type.Straight;
        }

        if (threeOfAKind != null) {
            return Type.ThreeOfAKind;
        }


        List<Card> twoOfAKind = pokerHandUtility.checkTwoOfAKind(cards);
        if (twoOfAKind != null) {
            List<Card> copy = pokerHandUtility.filterAndReturnNewList(this.getCards(), twoOfAKind);
            if (copy != null) {
                List<Card> twoMoreOfAKind = pokerHandUtility.checkTwoOfAKind(copy);
                if (twoMoreOfAKind != null) {
                    return Type.TwoPair;
                }
            }
        }


        if (twoOfAKind != null) {
            return Type.OnePair;
        }


        return Type.HighCard;
    }

    public int compareTo(PokerHand otherPokerHand) {
        Type handType1 = getHandType();
        Type handType2 = otherPokerHand.getHandType();
        List<Card> firstSetOfSimilarCardsHand1;
        List<Card> firstSetOfSimilarCardsHand2;
        List<Card> similarSetOfCards;
        List<Card> distinctSetOfCardsHand1;
        List<Card> distinctSetOfCardsHand2;

        if (handType1.equals(Type.HighCard) && handType2.equals(Type.HighCard)) {
            return pokerHandUtility.getHighCard(this.getCards()).compareTo(pokerHandUtility.getHighCard(otherPokerHand.getCards()));
        }
        if (handType1.equals(Type.StraightFlush) && handType2.equals(Type.StraightFlush)
                || handType1.equals(Type.Straight) && handType2.equals(Type.Straight)) {
            return pokerHandUtility.getHighCard(this.getCards()).compareTo(pokerHandUtility.getHighCard(otherPokerHand.getCards()));
        }

        if (handType1.equals(Type.Flush) && handType2.equals(Type.Flush)) {
            similarSetOfCards = pokerHandUtility.getSimilar(this.getCards(), otherPokerHand.getCards());
            distinctSetOfCardsHand1 = pokerHandUtility.filterAndReturnNewList(this.getCards(), similarSetOfCards);
            distinctSetOfCardsHand2 = pokerHandUtility.filterAndReturnNewList(otherPokerHand.getCards(), similarSetOfCards);
            return pokerHandUtility.getHighCard(distinctSetOfCardsHand1).compareTo(pokerHandUtility.getHighCard(distinctSetOfCardsHand2));
        }


        if (handType1.equals(Type.ThreeOfAKind) && handType2.equals(Type.ThreeOfAKind)) {

            similarSetOfCards = pokerHandUtility.getSimilar(this.getCards(), otherPokerHand.getCards());
            firstSetOfSimilarCardsHand1 = pokerHandUtility.checkThreeOfAKind(this.getCards());
            firstSetOfSimilarCardsHand2 = pokerHandUtility.checkThreeOfAKind(otherPokerHand.getCards());

            if (firstSetOfSimilarCardsHand1.get(0).getRank() == firstSetOfSimilarCardsHand2.get(0).getRank()) {
                distinctSetOfCardsHand1 = pokerHandUtility.filterAndReturnNewList(this.getCards(), similarSetOfCards);
                distinctSetOfCardsHand2 = pokerHandUtility.filterAndReturnNewList(otherPokerHand.getCards(), similarSetOfCards);

                return pokerHandUtility.getHighCard(distinctSetOfCardsHand1).compareTo(pokerHandUtility.getHighCard(distinctSetOfCardsHand2));
            } else {
                return firstSetOfSimilarCardsHand1.get(0).getRank().compareTo(firstSetOfSimilarCardsHand2.get(0).getRank());
            }


        }
        if (handType1.equals(Type.FourOfAKind) && handType2.equals(Type.FourOfAKind)) {
            similarSetOfCards = pokerHandUtility.getSimilar(this.getCards(), otherPokerHand.getCards());
            firstSetOfSimilarCardsHand1 = pokerHandUtility.checkFourOfAKind(this.getCards());
            firstSetOfSimilarCardsHand2 = pokerHandUtility.checkFourOfAKind(otherPokerHand.getCards());

            if (firstSetOfSimilarCardsHand1.get(0).getRank() == firstSetOfSimilarCardsHand2.get(0).getRank()) {
                List<Card> distinct1 = pokerHandUtility.filterAndReturnNewList(this.getCards(), similarSetOfCards);
                List<Card> distinct2 = pokerHandUtility.filterAndReturnNewList(otherPokerHand.getCards(), similarSetOfCards);

                return pokerHandUtility.getHighCard(distinct1).compareTo(pokerHandUtility.getHighCard(distinct2));
            } else {
                return firstSetOfSimilarCardsHand1.get(0).getRank().compareTo(firstSetOfSimilarCardsHand2.get(0).getRank());
            }
        }


        if (handType1.equals(Type.FullHouse) && handType2.equals(Type.FullHouse)) {
            firstSetOfSimilarCardsHand1 = pokerHandUtility.checkCardsOfTheSameRank(this.getCards(), 3);
            firstSetOfSimilarCardsHand2 = pokerHandUtility.checkCardsOfTheSameRank(otherPokerHand.getCards(), 3);

            if (firstSetOfSimilarCardsHand1.get(0).getRank() == firstSetOfSimilarCardsHand2.get(0).getRank()) {
                List<Card> nextSet1 = pokerHandUtility.filterAndReturnNewList(this.getCards(), firstSetOfSimilarCardsHand1);
                List<Card> nextSet2 = pokerHandUtility.filterAndReturnNewList(otherPokerHand.getCards(), firstSetOfSimilarCardsHand2);

                return nextSet1.get(0).compareTo(nextSet2.get(0));
            } else {
                return firstSetOfSimilarCardsHand1.get(0).compareTo(firstSetOfSimilarCardsHand2.get(0));
            }
        }

        if (handType1.equals(Type.TwoPair) && handType2.equals(Type.TwoPair)) {
            List<Card> sortedHand1 = pokerHandUtility.sortCardsDescending(this.getCards());
            List<Card> sortedHand2 = pokerHandUtility.sortCardsDescending(otherPokerHand.getCards());

            List<Card> sameSet1 = pokerHandUtility.checkCardsOfTheSameRank(sortedHand1, 2);
            List<Card> sameSet2 = pokerHandUtility.checkCardsOfTheSameRank(sortedHand2, 2);

            if (sameSet1.get(0) == sameSet2.get(0)) {
                List<Card> nextSet1 = pokerHandUtility.filterAndReturnNewList(this.getCards(), sameSet1);
                List<Card> nextSet2 = pokerHandUtility.filterAndReturnNewList(otherPokerHand.getCards(), sameSet2);

                List<Card> secondSameSet1 = pokerHandUtility.checkCardsOfTheSameRank(nextSet1, 2);
                List<Card> secondSameSet2 = pokerHandUtility.checkCardsOfTheSameRank(nextSet2, 2);

                if (secondSameSet1.get(0) == (secondSameSet2.get(0))) {
                    List<Card> similar = pokerHandUtility.getSimilar(this.getCards(), otherPokerHand.getCards());
                    sortedHand1.removeAll(similar);
                    sortedHand2.removeAll(similar);

                    return sortedHand1.get(0).compareTo(sortedHand2.get(0));
                } else {
                    return secondSameSet1.get(0).compareTo(secondSameSet2.get(0));
                }
            } else {
                return sameSet1.get(0).compareTo(sameSet2.get(0));
            }
        }

        if (handType1.equals(Type.OnePair) && handType2.equals(Type.OnePair)) {

            firstSetOfSimilarCardsHand1 = pokerHandUtility.checkTwoOfAKind(this.getCards());
            firstSetOfSimilarCardsHand2 = pokerHandUtility.checkTwoOfAKind(otherPokerHand.getCards());

            if (firstSetOfSimilarCardsHand1.get(0) == firstSetOfSimilarCardsHand2.get(0)) {
                distinctSetOfCardsHand1 = pokerHandUtility.filterAndReturnNewList(this.getCards(), firstSetOfSimilarCardsHand1);
                distinctSetOfCardsHand2 = pokerHandUtility.filterAndReturnNewList(otherPokerHand.getCards(), firstSetOfSimilarCardsHand2);
                return pokerHandUtility.getHighCard(distinctSetOfCardsHand1).compareTo(pokerHandUtility.getHighCard(distinctSetOfCardsHand2));
            } else {
                return firstSetOfSimilarCardsHand1.get(0).compareTo(firstSetOfSimilarCardsHand2.get(0));
            }
        }

        return handType1.compareTo(handType2);
    }


    public enum Type {
        HighCard,
        OnePair,
        TwoPair,
        ThreeOfAKind,
        Straight,
        Flush,
        FullHouse,
        FourOfAKind,
        StraightFlush
    }

    public PokerHand(List<Card> cards) {
        super(cards);
        pokerHandUtility = new PokerHandUtility();
    }
}
