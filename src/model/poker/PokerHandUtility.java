package model.poker;

import model.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ramyashenoy on 1/7/16.
 */
public class PokerHandUtility {
    public Card getHighCard(List<Card> cards) {
        sortCardsDescending(cards);
        return cards.get(0);
    }

    public ArrayList<Card> checkCardsOfTheSameSuit(List<Card> total, int numberOfCards) {

        for (Card.Suit suit : Card.Suit.values()) {
            ArrayList<Card> sameSuit = new ArrayList<>();
            for (Card card : total) {
                if (card.getSuit().compareTo(suit) == 0) {
                    sameSuit.add(card);
                }
            }
            if (sameSuit.size() >= numberOfCards) {
                return sameSuit;
            }
        }
        return null;
    }

    public ArrayList<Card> checkCardsOfTheSameRank(List<Card> cards, int numberOfCards) {
        ArrayList<Card> sameKind = new ArrayList<>();
        for(Card card1: cards){
            for(Card card2: cards){
                if(card1.equals(card2)){
                    sameKind.add(card2);
                }
            }

            if(sameKind.size() == numberOfCards){
                return sameKind;
            }else{
                sameKind.clear();
            }
        }
        return null;
    }


    public ArrayList<Card> checkCardsInASequence(List<Card> sortedCards, int numberOfCards) {
        List<Card> cards = sortCardsAscending(sortedCards);
        ArrayList<Card> highestNumberedSequence = new ArrayList<>();

        for (int i = 0; i < cards.size(); i++) {
            Card firstCard = sortedCards.get(i);
            ArrayList<Card> temporarySequence = new ArrayList<>();
            for (int j = i; j < sortedCards.size(); j++) {
                if (sortedCards.get(j).compareTo(firstCard) == j-i) {
                    temporarySequence.add(sortedCards.get(j));
                }
                if (temporarySequence.size() == numberOfCards) {
                    break;
                }
            }
            if(temporarySequence.size() == numberOfCards){
                highestNumberedSequence.clear();
                highestNumberedSequence.addAll(temporarySequence);
            }
        }


        if (highestNumberedSequence.size() == numberOfCards){
            return highestNumberedSequence;
        }

        return null;
    }

    public List<Card> sortCardsDescending(List<Card> cardArrayList) {
        Collections.sort(cardArrayList, new Comparator<Card>() {
            @Override
            public int compare(Card lhs, Card rhs) {
                return lhs.compareTo(rhs);
            }
        });
        Collections.reverse(cardArrayList);
        return cardArrayList;
    }

    public List<Card> sortCardsAscending(List<Card> cardArrayList) {
        Collections.sort(cardArrayList, new Comparator<Card>() {
            @Override
            public int compare(Card lhs, Card rhs) {
                return lhs.compareTo(rhs);
            }
        });
        return cardArrayList;
    }

    public List<Card> getSimilar(List<Card> firstList, List<Card> secondList) {
        List<Card> similarSet = new ArrayList<>();
        for(Card card1: firstList){
            for(Card card2 : secondList){
                if(card1.getRank() == card2.getRank() && card1.getSuit() == card2.getSuit()){
                    similarSet.add(card2);
                }
            }
        }
        return similarSet;
    }

    public List<Card> filterAndReturnNewList(List<Card> originalSet, List<Card> toFilter){
        List<Card> filteredSet = new ArrayList<>();
        if(originalSet != null && toFilter != null){
            filteredSet.addAll(originalSet);
            filteredSet.removeAll(toFilter);
            return filteredSet;
        }
        return null;
    }

    public List<Card> checkThreeOfAKind(List<Card> cards){
        return checkNCardsOfAKind(cards, 3);
    }

    public List<Card> checkFourOfAKind(List<Card> cards){
        return checkNCardsOfAKind(cards, 4);
    }

    public List<Card> checkTwoOfAKind(List<Card> cards){
        return checkNCardsOfAKind(cards, 2);
    }

    private List<Card> checkNCardsOfAKind(List<Card> cards, int numberOfCards) {
        return checkCardsOfTheSameRank(cards, numberOfCards);
    }
}
