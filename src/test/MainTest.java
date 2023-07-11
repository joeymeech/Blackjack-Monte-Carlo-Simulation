package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import production.Dealer;
import production.Deck;
import production.Human;
import production.Main;

public class MainTest {
    InputStream originalIn = System.in;

    @Test
    public void welcomeTest(){
        Main main = new Main();
        main.welcome();
    }
    @Test
    public void addBetTest(){
        String data = "2000";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Main main = new Main();
        main.addBet();
        System.setIn(originalIn);

        
    }
    
    @Test
    public void cashOutTest(){
        String data = "1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Main.main(null);
        System.setIn(originalIn);
    }

    @Test
    public void clearHandsTest(){
        Main main = new Main();
        Deck deck = new Deck(1);
        Human human = new Human(deck);
        Dealer dealer = new Dealer(deck);
        main.clearHands(human, dealer);
        assertEquals(human.handSize(), dealer.handSize());
    }

    @Test
    public void printDealerHandTest(){
        Main main = new Main();
        Deck deck = new Deck(1);
        Dealer dealer = new Dealer(deck);
        dealer.takeCard();
        dealer.takeCard();
        main.printDealerHand(dealer);
    }

    @Test
    public void printHumanHandTest(){
        Main main = new Main();
        Deck deck = new Deck(1);
        Human human = new Human(deck);
        human.takeCard();
        human.takeCard();
        main.printHumanHand(human);
    }

    @Test
    public void delayTest(){
        Main main = new Main();
        main.delay();
    }

    @Test
    public void dealCardsTest(){
        Main main = new Main();
        Deck deck = new Deck(1);
        Human human = new Human(deck);
        Dealer dealer = new Dealer(deck);
        main.dealCards(human, dealer);
        assertEquals(human.handSize(), dealer.handSize());
    }

    @Test
    public void addChipsTest(){
        String data = "2000";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Main main = new Main();
        main.addChips();
        System.setIn(originalIn);
    }

    @Test
    public void getDealerToMinTest(){
        Main main = new Main();
        Deck deck = new Deck(1);
        Human human = new Human(deck);
        Dealer dealer = new Dealer(deck);
        main.dealCards(human, dealer);
        main.getDealerToMin(dealer);
    }

    @Test
    public void winConditionTest(){
        Main main = new Main();
        Deck deck = new Deck(1);
        Human human = new Human(deck);
        Dealer dealer = new Dealer(deck);
        main.dealCards(human, dealer);
        main.getDealerToMin(dealer);
        main.winCondition(human.currentTotal(), dealer.currentTotal());
        main.winCondition(human.currentTotal(), 22);
        main.winCondition(21, 20);
        main.winCondition(20, 21);
        main.winCondition(20, 20);

        main.clearHands(human, dealer);
        main.dealCards(human, dealer);
        main.winCondition(human.currentTotal());
        main.winCondition(22);
        main.winCondition(21);
    }

    @Test
    public void checkFor21Test(){
        Main main = new Main();
        Deck deck = new Deck(1);
        Human human = new Human(deck);
        Dealer dealer = new Dealer(deck);
        main.dealCards(human, dealer);
        main.checkFor21(human.currentTotal(), dealer.currentTotal());

        main.checkFor21(21, 21);
        main.checkFor21(21, 20);
        main.checkFor21(20, 21);
    }

    @Test
    public void wantToHitTest(){
        String data = "yes";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Main main = new Main();
        Deck deck = new Deck(1);
        Human human = new Human(deck);
        Dealer dealer = new Dealer(deck);
        main.dealCards(human, dealer);
        human.predeterminedHumanHand(10, 11);
        main.wantToHit(human);

        String data2 = "no";
        System.setIn(new ByteArrayInputStream(data2.getBytes()));
        main = new Main();
        main.clearHands(human, dealer);
        main.dealCards(human, dealer);
        main.wantToHit(human);
    }

    // @Test
    // public void gameLogicTest(){
    //     Main main = new Main();
    //     main.gameLogic();
    // }



}
