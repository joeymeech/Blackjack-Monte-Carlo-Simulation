package test;

// import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import production.Dealer;
import production.Deck;
import production.Human;
import production.Main;
import production.MonteCarloPlayer;
import production.RandomPlayer;
import production.AbstractPlayer;
import production.Bank;
import production.BookPlayer;

public class MainTest {

  @BeforeAll
  public static void noDelay(){
    Main main = new Main();
    main.setDelay(0);
  }

  @Test
  public void welcomeTest(){
    Main main = new Main();
    System.out.println(main.getDelay());
    main.welcome();
  }

  @Test
  public void mainTest(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());
    System.setIn(inputStream);
    Main.main(null);
  }

  @Test
  public void playHandTest(){
      ByteArrayInputStream inputStream = 
      new ByteArrayInputStream("0 0 1".getBytes());
      System.setIn(inputStream);
      Main main = new Main();
      main.welcome();
  }

  @Test
  public void startCashOutTest(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("7 1".getBytes());
    System.setIn(inputStream);
    Main main = new Main();
    main.start();
  }

  @Test
  public void startHardGenerateTest(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("5 1".getBytes());
    System.setIn(inputStream);
    Main main = new Main();
    main.start();
  }

  @Test
  public void startSoftGenerateTest(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("6 1".getBytes());
    System.setIn(inputStream);
    Main main = new Main();
    main.start();
  }


  @Test
  public void addBetTest(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1000".getBytes());
    System.setIn(inputStream);
    Main main = new Main();
    Bank bank = new Bank();
    main.addBet(bank);
  }


  @Test
  public void addChipsTest(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("g 1000 1".getBytes());
    System.setIn(inputStream);
    Main main = new Main();
    Bank bank = new Bank();
    main.addChips(bank);
    
  }

  @Test
  public void dealCardsTest(){
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    Human human = new Human(deck);
    Dealer dealer = new Dealer(deck);
    players.add(human);
    players.add(dealer);
    main.dealCards(players);
    main.clearHands(players);
    main.delay(0);
  }

  @Test
  public void addPlayerTest(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("3 0 1".getBytes());
    System.setIn(inputStream);
    Main main = new Main();
    main.start();

    inputStream = new ByteArrayInputStream("3 1 1".getBytes());
    System.setIn(inputStream);
    main = new Main();
    main.start();

    inputStream = new ByteArrayInputStream("3 2 1".getBytes());
    System.setIn(inputStream);
    main = new Main();
    main.start();


    inputStream = new ByteArrayInputStream("3 g 1 1".getBytes());
    System.setIn(inputStream);
    main = new Main();
    main.start();
      
  }

  @Test
  public void printPlayersTest(){
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    Human human = new Human(deck);
    Dealer dealer = new Dealer(deck);
    players.add(human);
    players.add(dealer);
    main.printPlayers(players);
  }

  @Test
  public void pickPlayerToRemove(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("3 1 4 1 1".getBytes());
    System.setIn(inputStream);
    Main main = new Main();
    main.start();

    inputStream = new ByteArrayInputStream("3 1 4 g 1 1".getBytes());
    System.setIn(inputStream);
    main = new Main();
    main.start();

    inputStream = new ByteArrayInputStream("4 1".getBytes());
    System.setIn(inputStream);
    main = new Main();
    main.start();
  }
  

  @Test
  public void removePlayerTest(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1 1".getBytes());
    System.setIn(inputStream);
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    Human human = new Human(deck);
    Dealer dealer = new Dealer(deck);
    players.add(human);
    players.add(dealer);
    main.addPlayer(main.pickPlayerToAdd(deck), players);
    main.removePlayer(main.pickPlayerToRemove(players), players);
  }

  @Test
  public void setBestTest(){
    Main main = new Main();
    main.setBet(1000);
  }

  @Test
  public void gameLogicTestOne(){
    Bank bank = new Bank();
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    Human human = new Human(deck);
    Dealer dealer = new Dealer(deck);
    human.predeterminedCard(10, 0);
    human.predeterminedCard(11, 0);
    dealer.predeterminedCard(10, 0);
    dealer.predeterminedCard(7, 0);
    players.add(human);
    players.add(dealer);
    main.winCondition(players, bank);
  }

  @Test
  public void gameLogicTestTwo(){
    Bank bank = new Bank();
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    Human human = new Human(deck);
    Dealer dealer = new Dealer(deck);
    human.predeterminedCard(10, 0);
    human.predeterminedCard(10, 0);
    dealer.predeterminedCard(11, 0);
    dealer.predeterminedCard(10, 0);
    players.add(human);
    players.add(dealer);
    
    main.winCondition(players, bank);
  }

  @Test
  public void gameLogicTestThree(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("0".getBytes());
    System.setIn(inputStream);
    Bank bank = new Bank();
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    Human human = new Human(deck);
    Dealer dealer = new Dealer(deck);
    human.predeterminedCard(10, 0);
    human.predeterminedCard(10, 0);
    dealer.predeterminedCard(10, 0);
    dealer.predeterminedCard(11, 0);
    players.add(human);
    players.add(dealer);
    main.winCondition(players, bank);
  }

  @Test
  public void gameLogicTestFour(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("g 1".getBytes());
    System.setIn(inputStream);
    Main main = new Main();
    Bank bank = new Bank();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    Human human = new Human(deck);
    Dealer dealer = new Dealer(deck);
    human.predeterminedCard(10, 0);
    human.predeterminedCard(10, 0);
    dealer.predeterminedCard(10, 0);
    dealer.predeterminedCard(11, 0);
    players.add(human);
    players.add(dealer);
    main.winCondition(players, bank);
  }

  @Test
  public void gameLogicTestFive(){
    Bank bank = new Bank();
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    Human human = new Human(deck);
    Dealer dealer = new Dealer(deck);
    human.predeterminedCard(11, 0);
    human.predeterminedCard(10, 0);
    dealer.predeterminedCard(11, 0);
    dealer.predeterminedCard(10, 0);
    players.add(human);
    players.add(dealer);
    main.winCondition(players, bank);
  }

  @Test
  public void gameLogicTestSix(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());
    System.setIn(inputStream);
    Bank bank = new Bank();
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    Human human = new Human(deck);
    Dealer dealer = new Dealer(deck);
    human.predeterminedCard(11, 0);
    human.predeterminedCard(10, 0);
    dealer.predeterminedCard(11, 0);
    dealer.predeterminedCard(10, 0);
    players.add(human);
    players.add(dealer);
    main.winCondition(players, bank);
  }

  @Test
  public void gameLogicTestSeven(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());
    System.setIn(inputStream);
    Bank bank = new Bank();
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    Human human = new Human(deck);
    Dealer dealer = new Dealer(deck);
    human.predeterminedCard(10, 0);
    human.predeterminedCard(10, 0);
    dealer.predeterminedCard(7, 0);
    dealer.predeterminedCard(10, 0);
    players.add(human);
    players.add(dealer);
    main.winCondition(players, bank);
  }
  
  @Test
  public void gameLogicTestEight(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("0".getBytes());
    System.setIn(inputStream);
    Bank bank = new Bank();
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    Human human = new Human(deck);
    Dealer dealer = new Dealer(deck);
    human.predeterminedCard(2, 0);
    human.predeterminedCard(2, 0);
    dealer.predeterminedCard(7, 0);
    dealer.predeterminedCard(10, 0);
    players.add(human);
    players.add(dealer);
    main.winCondition(players, bank);
  }

  @Test
  public void gameLogicTestNine(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1 0".getBytes());
    System.setIn(inputStream);
    Bank bank = new Bank();
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    Human human = new Human(deck);
    Dealer dealer = new Dealer(deck);
    human.predeterminedCard(2, 0);
    human.predeterminedCard(2, 0);
    dealer.predeterminedCard(6, 0);
    dealer.predeterminedCard(10, 0);
    players.add(human);
    players.add(dealer);
    main.winCondition(players, bank);
  }

  @Test
  public void gameLogicTestTen(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1 0".getBytes());
    System.setIn(inputStream);
    Bank bank = new Bank();
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    Human human = new Human(deck);
    Dealer dealer = new Dealer(deck);
    human.predeterminedCard(10, 0);
    human.predeterminedCard(10, 0);
    dealer.predeterminedCard(11, 0);
    dealer.predeterminedCard(7, 0);
    players.add(human);
    players.add(dealer);
    main.winCondition(players, bank);
  }

  @Test
  public void gameLogicTestAll(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());
    System.setIn(inputStream);
    Bank bank = new Bank();
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    RandomPlayer randomPlayer = new RandomPlayer(deck);
    Dealer dealer = new Dealer(deck);
    players.add(randomPlayer);
    players.add(dealer);
    main.gameLogic(deck, players, bank);
  }

  @Test
  public void gameLogicTestAllTwo(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());
    System.setIn(inputStream);
    Bank bank = new Bank();
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    RandomPlayer randomPlayer = new RandomPlayer(deck);
    Dealer dealer = new Dealer(deck);
    players.add(randomPlayer);
    players.add(dealer);
    main.gameLogic(deck, players, bank);
  }

  @Test
  public void gameLogicTestAllThree(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());
    System.setIn(inputStream);
    Bank bank = new Bank();
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    RandomPlayer randomPlayer = new RandomPlayer(deck);
    Dealer dealer = new Dealer(deck);
    players.add(randomPlayer);
    players.add(dealer);
    main.gameLogic(deck, players, bank);
  }

  @Test
  public void gameLogicTestAllFour(){
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());
    System.setIn(inputStream);
    Bank bank = new Bank();
    Main main = new Main();
    Deck deck = new Deck(Main.AMOUNTOFDECKS);
    List<AbstractPlayer> players = new ArrayList<>();
    BookPlayer bookPlayer = new BookPlayer(deck);
    Dealer dealer = new Dealer(deck);
    players.add(bookPlayer);
    players.add(dealer);
    main.gameLogic(deck, players, bank);
  }

  @Test
  public void gameLogicTestAllFive(){
    Bank bank = new Bank();
    Main main = new Main();
    main.setDelay(0);
    Deck deck = new Deck(1);
    List<AbstractPlayer> players = new ArrayList<>();
    MonteCarloPlayer monteCarloPlayer = new MonteCarloPlayer(deck);
    Dealer dealer = new Dealer(deck);
    players.add(monteCarloPlayer);
    players.add(dealer);
    for(int i = 0; i < 10; i++){
      main.gameLogic(deck, players, bank);
    }
    
  }

  

  @After
  public void end(){
    Main main = new Main();
    main.setDelay(2000);
    System.setIn(System.in);
  }



    



}
