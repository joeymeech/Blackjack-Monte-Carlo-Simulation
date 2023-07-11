package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import production.Dealer;
import production.Deck;
import production.Human;
import production.Player;


public class AllPlayerTest {
    @BeforeAll
    static void beforeAll(){
        System.out.println("Starting the tests");
    }

    @Test
    public void takeCardTest(){
        Deck deck = new Deck(1);
        Player player = new Player(deck);
        player.takeCard();
        assertEquals(1, player.handSize());
        assertEquals(false, player.isBust());
    }

    @Test
    public void isBustTest(){
        Deck deck = new Deck(1);
        Player player = new Player(deck);
        player.takeCard();
        assertEquals(false, player.isBust());

        player.clearHand();
        player.predeterminedHumanHand(10, 10);
        player.takeCard();
        player.takeCard();
        assertEquals(true, player.isBust());
    }

    @Test
    public void isTwentyOneTest(){
        Deck deck = new Deck(1);
        Player player = new Player(deck);
        player.takeCard();
        assertEquals(false, player.isTwentyOne());

        player.clearHand();
        player.predeterminedHumanHand(10, 11);
        assertEquals(true, player.isTwentyOne());
    }

    @Test
    public void clearHandTest(){
        Deck deck = new Deck(1);
        Player player = new Player(deck);
        player.takeCard();
        player.clearHand();
        assertEquals(0, player.handSize());
    }

    @Test
    public void currentTotalTest(){
        Deck deck = new Deck(1);
        Player player = new Player(deck);

        player.predeterminedHumanHand(2, 8);
        assertEquals(10, player.currentTotal());
        player.clearHand();

        player.predeterminedHumanHand(11, 11);
        assertEquals(12, player.currentTotal());
        player.clearHand();

        player.predeterminedHumanHand(11, 10);
        assertEquals(21, player.currentTotal());
    }

    @Test
    public void printHandTest(){
        Deck deck = new Deck(1);
        Player player = new Player(deck);
        player.takeCard();
        player.takeCard();
        player.printHand();
    }

    @Test
    public void printDealerHandTest(){
        Deck deck = new Deck(1);
        Dealer dealer = new Dealer(deck);
        dealer.takeCard();
        dealer.takeCard();
        dealer.printHandFaceCardDown();
    }

    @Test
    public void printLastCardTest(){
        Deck deck = new Deck(1);
        Player player = new Player(deck);
        player.takeCard();
        player.takeCard();
        player.printLastCard();
    }

    @Test
    public void humanPlayerNameTest(){
        Deck deck = new Deck(1);
        Human human = new Human(deck, "meech");
        assertEquals("meech", human.getName());
        Human human2 = new Human(deck);
        assertEquals(null, human2.getName());
    }

    @Test
    public void dealerFaceUpTotalTest(){
        Deck deck = new Deck(1);
        Dealer dealer = new Dealer(deck);
        dealer.predeterminedHumanHand(2, 10);
        assertEquals(10, dealer.faceUpTotal());
    }

    @Test
    public void dealerIsSeventeenMinTest(){
        Deck deck = new Deck(1);
        Dealer dealer = new Dealer(deck);
        dealer.predeterminedDealerCard(11);
        dealer.clearHand();
        dealer.predeterminedDealerCard(10);
        assertEquals(false, dealer.isSeventeenMin());
        dealer.clearHand();
        dealer.predeterminedHumanHand(10, 7);
        assertEquals(true, dealer.isSeventeenMin());
    }



}
