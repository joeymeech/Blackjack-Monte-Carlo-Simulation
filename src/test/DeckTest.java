package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import org.junit.jupiter.api.Test;

import production.Card;
import production.Deck;

public class DeckTest {

    @Test
    public void amountOfDeckTest(){
        Deck deck = new Deck(1);
        assertEquals(1, deck.amountOfDecks());
    }

    @Test
    public void takeCardTest(){
        Deck deck = new Deck(1);
        Card card = new Card(null, null);
        assertNotEquals(card, deck.takeCard());
        assertEquals(false, deck.isEmpty());
    }

    @Test
    public void isDeckEmptyTest(){
        Deck deck = new Deck(1);
        assertEquals(false, deck.isEmpty());
        for(int i = 0; i < 52; i++){
            deck.takeCard();
        }
        assertEquals(true, deck.isEmpty());
        deck.resetCards();
    }

    @Test
    public void hasCutCardBeenHitTest(){
        Deck deck = new Deck(1);
        assertEquals(false, deck.hasCutCardBeenHit()); 
    }

    @Test
    public void printDeckTest(){
        Deck deck = new Deck(1);
        deck.printDeck();
        
    }
}