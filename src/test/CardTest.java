package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import production.Card;
import production.Suit;
import production.Value;


public class CardTest {

    @Test
    public void eightOfDiamondsTest(){
        Card card = new Card(Suit.Diamonds, Value.Eight);
        assertEquals(Value.Eight, card.getValue());
        assertEquals(Suit.Diamonds, card.getSuit());
        assertEquals(8, card.getNum());
    }

    @Test
    public void aceOfClubsTest(){
        Card card = new Card(Suit.Clubs, Value.Ace);
        assertEquals(Value.Ace, card.getValue());
        assertEquals(Suit.Clubs, card.getSuit());
        assertEquals(11, card.getNum());
    }

    @Test
    public void kingOfSpadesTest(){
        Card card = new Card(Suit.Spades, Value.King);
        assertEquals(Value.King, card.getValue());
        assertEquals(Suit.Spades, card.getSuit());
        assertEquals(10, card.getNum());
    }

    @Test
    public void fiveOfHeartsTest(){
        Card card = new Card(Suit.Hearts, Value.Five);
        assertEquals(Value.Five, card.getValue());
        assertEquals(Suit.Hearts, card.getSuit());
        assertEquals(5, card.getNum());
    }
}
