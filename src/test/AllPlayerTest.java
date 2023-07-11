package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import production.BookPlayer;
import production.Dealer;
import production.Deck;
import production.Human;
import production.MonteCarlo;
import production.MonteCarloPlayer;
import production.Player;
import production.RandomPlayer;


public class AllPlayerTest {
    @BeforeAll
    static void beforeAll(){
        System.out.println("Starting the tests");
    }

    @Test
    public void takeCardTest(){
        Deck deck = new Deck(1);
        Player player = new Player(deck);
        player.takeCard(0);
        assertEquals(1, player.handSize(0));
        assertEquals(false, player.isBust(0));
    }

    @Test
    public void checkNameTest(){
        Deck deck = new Deck(1);
        Player player = new Player(deck);
        player.playHand(0,0);
        player.getName();
        assertEquals("Generic Player", player.getName());
        
    }

    @Test
    public void isBustTest(){
        Deck deck = new Deck(1);
        Player player = new Player(deck);
        player.takeCard(0);
        assertEquals(false, player.isBust(0));

        player.clearHand(0);
        player.predeterminedCard(10, 0);
        player.predeterminedCard(10, 0);
        player.takeCard(0);
        player.takeCard(0);
        assertEquals(true, player.isBust(0));
    }

    @Test
    public void isTwentyOneTest(){
        Deck deck = new Deck(1);
        Player player = new Player(deck);
        player.takeCard(0);
        assertEquals(false, player.isTwentyOne(0));

        player.clearHand(0);
        player.predeterminedCard(10, 0);
        player.predeterminedCard(11, 0);
        assertEquals(true, player.isTwentyOne(0));
    }

    @Test
    public void clearHandTest(){
        Deck deck = new Deck(1);
        Player player = new Player(deck);
        player.takeCard(0);
        player.clearHand(0);
        assertEquals(0, player.handSize(0));
    }

    @Test
    public void currentTotalTest(){
        Deck deck = new Deck(1);
        Player player = new Player(deck);

        player.predeterminedCard(2, 0);
        player.predeterminedCard(8, 0);
        assertEquals(10, player.currentTotal(0));
        player.clearHand(0);

        
        player.predeterminedCard(11, 0);
        player.predeterminedCard(11, 0);
        assertEquals(12, player.currentTotal(0));
        player.clearHand(0);

        player.predeterminedCard(11, 0);
        player.predeterminedCard(10, 0);
        assertEquals(21, player.currentTotal(0));
    }

    @Test
    public void printHandTest(){
        Deck deck = new Deck(1);
        Player player = new Player(deck);
        player.takeCard(0);
        player.predeterminedCard(11, 0);
        player.printHand(0);
    }

    @Test
    public void printDealerHandTest(){
        Deck deck = new Deck(1);
        Dealer dealer = new Dealer(deck);
        dealer.takeCard(0);
        dealer.predeterminedCard(10, 0);
        dealer.printHand(0);
    }

    @Test
    public void printLastCardTest(){
        Deck deck = new Deck(1);
        Player player = new Player(deck);
        player.predeterminedCard(10, 0);
        player.printLastCard(0);
        player.predeterminedCard(11, 0);
        player.printLastCard(0);
    }

    @Test
    public void humanPlayerNameTest(){
        Deck deck = new Deck(1);
        Human human = new Human(deck);
        human.setName("meech");
        assertEquals("meech", human.getName());
        Human human2 = new Human(deck);
        assertEquals("Human Player", human2.getName());
    }

    @Test
    public void humanPlayHandTest(){
        Deck deck = new Deck(1);
        Human human = new Human(deck);
        human.predeterminedCard(10, 0);
        human.predeterminedCard(10, 0);
        human.predeterminedCard(10, 0);
        ByteArrayInputStream inputStream = 
				new ByteArrayInputStream("g 1".getBytes());
		System.setIn(inputStream);
        human.playHand(5, 0);
        human.clearHand(0);
        human.predeterminedCard(5, 0);
        human.predeterminedCard(5, 0);
        inputStream = 
				new ByteArrayInputStream("1 0".getBytes());
		System.setIn(inputStream);
        human.playHand(5, 0);
        human.clearHand(0);
        System.setIn(System.in);
    }

    @Test
    public void dealerFaceUpTotalTest(){
        Deck deck = new Deck(1);
        Dealer dealer = new Dealer(deck);
        dealer.predeterminedCard(10, 0);
        dealer.predeterminedCard(10, 0);
        assertEquals(10, dealer.firstCardTotal(0));
    }

    @Test
    public void dealerIsSeventeenMinTest(){
        Deck deck = new Deck(1);
        Dealer dealer = new Dealer(deck);
        dealer.predeterminedCard(11, 0);
        dealer.clearHand(0);
        dealer.predeterminedCard(10, 0);
        assertEquals(false, dealer.isSeventeenMin(0));
        dealer.clearHand(0);
        dealer.predeterminedCard(7, 0);
        dealer.predeterminedCard(10, 0);
        assertEquals(true, dealer.isSeventeenMin(0));
    }

    @Test
    public void dealerPlayHandTest(){
        Deck deck = new Deck(1);
        Dealer dealer = new Dealer(deck);
        dealer.predeterminedCard(10, 0);
        dealer.predeterminedCard(7, 0);
        dealer.playHand(dealer.firstCardTotal(0), 0);
        dealer.clearHand(0);
        dealer.predeterminedCard(10, 0);
        dealer.predeterminedCard(6, 0);
        dealer.playHand(dealer.firstCardTotal(0), 0);
        dealer.clearHand(0);
        dealer.predeterminedCard(10, 0);
        dealer.predeterminedCard(5, 0);
        dealer.playHand(dealer.firstCardTotal(0), 0);
        dealer.clearHand(0);
    }

    @Test
    public void statCheckTest(){
        Deck deck = new Deck(1);
        Human human = new Human(deck);
        human.addToLosses();
        human.addToTies();
        human.addToWins();
        assertEquals(1, human.getLosses());
        assertEquals(1, human.getTies());
        assertEquals(1, human.getWins());
    }

    @Test
    public void predeterminedCardTest(){
        Deck deck = new Deck(1);
        Human human = new Human(deck);
        human.predeterminedCard(5, 0);
        human.predeterminedCard(5, 0);
        human.predeterminedCard(5, 0);
        human.predeterminedCard(5, 0);
        human.predeterminedCard(5, 0);
    }

    @Test
    public void printDealerHand(){
        Deck deck = new Deck(1);
        Dealer dealer = new Dealer(deck);
        dealer.takeCard(0);
        dealer.takeCard(0);
        dealer.printDealerHand(0);
    }

    @Test
    public void monteCarloPlayerNameTest(){
        Deck deck = new Deck(1);
        MonteCarloPlayer monte = new MonteCarloPlayer(deck);
        assertTrue(monte.getName().contains("Monte Carlo Bot #")); 


    }

    @Test
    public void monteCarloPlayerBestMoveTest(){
        Deck deck = new Deck(1);
        MonteCarloPlayer monte = new MonteCarloPlayer(deck);
        monte.getAllMoves();
        assertEquals("S", monte.getBestMove(17, 7, 0, false));
        assertEquals("H", monte.getBestMove(5, 7, 0, false));

    }

    @Test
    public void monteCarloPlayerPlayHandTest(){
        Deck deck = new Deck(1);
        MonteCarloPlayer monte = new MonteCarloPlayer(deck);
        monte.predeterminedCard(10, 0);
        monte.predeterminedCard(10, 0);
        monte.predeterminedCard(10, 0);
        monte.playHand(10, 0);
        monte.clearHand(0);
        monte.predeterminedCard(10, 0);
        monte.predeterminedCard(8, 0);
        monte.playHand(10, 0);
        monte.clearHand(0);
        monte.predeterminedCard(3, 0);
        monte.predeterminedCard(4, 0);
        monte.playHand(10, 0);
        monte.clearHand(0);
    }

    @Test
    public void monteCarloGenerateTable(){  
        MonteCarlo monte = new MonteCarlo();
        monte.bestMove(7,7, 0, false);
        monte.bestMove(18,10, 0, false);
    }


    @Test
    public void humanPlayerTest(){  
        ByteArrayInputStream inputStream = new ByteArrayInputStream("3 2".getBytes());
        System.setIn(inputStream);
        Deck deck = new Deck(1);
        Human human = new Human(deck);
        human.predeterminedCard(2, 0);
        human.predeterminedCard(3, 0);
        human.playHand(0, 0);

        inputStream = new ByteArrayInputStream("3 0 0".getBytes());
        System.setIn(inputStream);
        human.clearHand(0);
        human.predeterminedCard(2, 0);
        human.predeterminedCard(2, 0);
        human.playHand(0, 0);
    }

    @Test
    public void bookPlayerTest(){  
        Deck deck = new Deck(1);
        BookPlayer bookPlayer = new BookPlayer(deck);
        bookPlayer.predeterminedCard(10, 0);
        bookPlayer.predeterminedCard(10, 0);
        bookPlayer.predeterminedCard(2, 0);
        bookPlayer.playHand(10, 0);

        bookPlayer.clearHand(0);
        bookPlayer.predeterminedCard(11, 0);
        bookPlayer.predeterminedCard(11, 0);
        bookPlayer.playHand(10, 0);

        bookPlayer.clearHand(0);
        bookPlayer.predeterminedCard(3, 0);
        bookPlayer.predeterminedCard(4, 0);
        bookPlayer.playHand(10, 0);

        bookPlayer.clearHand(0);
        bookPlayer.predeterminedCard(11, 0);
        bookPlayer.predeterminedCard(2, 0);
        bookPlayer.playHand(10, 0);

        bookPlayer.clearHand(0);
        bookPlayer.predeterminedCard(5, 0);
        bookPlayer.predeterminedCard(6, 0);
        bookPlayer.playHand(10, 0);

        bookPlayer.clearHand(0);
        bookPlayer.predeterminedCard(10, 0);
        bookPlayer.predeterminedCard(8, 0);
        bookPlayer.playHand(10, 0);

        bookPlayer.clearHand(0);
        bookPlayer.predeterminedCard(10, 0);
        bookPlayer.predeterminedCard(10, 0);
        bookPlayer.playHand(10, 0);
    }




    



}
