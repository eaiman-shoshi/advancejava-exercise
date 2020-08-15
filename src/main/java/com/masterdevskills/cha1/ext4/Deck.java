package com.masterdevskills.cha1.ext4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public interface Deck {
    List<Card> getCards();

    Deck deckFactory();

    int size();

    void addCard(Card card);

    void addCards(List<Card> cards);

    void addDeck(Deck deck);

    void shuffle();

    void sort();

    void sort(Comparator<Card> c);

    String deckToString();

    /**
     * actually I don't know how card games works. so, just implemented a default method which will validate if
     * there is 52 cards in the deck or not.
     *
     * @return boolean
     */
    default boolean isDeckValid() {
        return size() == 52;
    }

    Map<Integer, Deck> deal(int players, int numberOfCards)
            throws IllegalArgumentException;
}
