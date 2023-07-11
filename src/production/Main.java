package production;
import java.util.Scanner;
import java.lang.Thread;

/**
 * Main Driver Class
 */
public class Main{
    // make this file nicer, possibly split into more classes
    private int bet;
    private boolean justWon = false;
    private boolean pushed = false;
    private Bank bank;

    private final int AMOUNTOFDECKS = 1;
    private final int DELAY = 2000;

    private Scanner sc;
    private Deck deck;
    private Human player;
    private Dealer dealer;

    public Main(){
        bank = new Bank();
        sc = new Scanner(System.in);
        deck = new Deck(AMOUNTOFDECKS);
        player = new Human(deck);
        dealer = new Dealer(deck);
    }


    /**
     * main method
     * @param args
     */
    public static void main(String[] args){
        Main main = new Main();
        main.start();
    }

    public void start(){
        welcome();
        int selection;
        do{
            System.out.println();
            if(bet != 0){
                if(pushed) System.out.println("+0");
                else if(justWon) System.out.println("+" + bet);
                else System.out.println("-" + bet);
            }
            bet = 0;
            justWon = false;
            pushed = false;
            System.out.println("Total chips = " + bank.getChips());
            System.out.println();
            System.out.println("Play hand:  [0]");
            System.out.println("Cash out:   [1]");
            System.out.println("Add chips:  [2]");
            selection = sc.nextInt();
            switch(selection){
                case 0:
                    addBet();
                    gameLogic();
                    break;
                case 1:
                    System.out.println("You have left with " + bank.getChips() + " chips.");
                    break;
                case 2:
                    addChips();
                    break;
                default:
                    System.out.println("Please enter one of the options");
            }

        } while(selection != 1);

        sc.close();
    }

    public void addBet(){
        if(bank.isOutOfChips()){
            System.out.println("Sorry you do not have any chips.");
            bet = 0;
            return;
        }
        if(!bank.hasTableMinium(bank.getChips())){
            System.out.println("You do not have enough chips to play the table minimum.");
            System.out.println("Please add more chips through the bank.");
            bet = 0;
            return;
        }
        do{
            System.out.println("How many chips would you like to bet?");
            System.out.println("The table minimum is " + bank.getTableMinimum() + " chips");
            while(!sc.hasNextInt()) {
                System.out.println("Please enter a valid number");
                sc.next();
            }
            bet = sc.nextInt();
            if(bet < bank.getTableMinimum()){
                System.out.println("You need to play the table minimum which is " + bank.getTableMinimum() + ".");
            }
            if(bet > bank.getChips()){
                System.out.println("You do not have that many chips. Please place a smaller bet.");
            }
        } while(bet > bank.getChips() || bet < bank.getTableMinimum());
    }

    public void gameLogic(){
        // start of the game
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("----------");
        System.out.println("-- Hand --");
        System.out.println("----------");
        delay();
        clearHands(player, dealer);
        if(deck.hasCutCardBeenHit()){
            System.out.println("The cut card has been hit!");
            System.out.println("Reshuffling the deck!");
            deck.resetCards();
            deck.shuffle();
            deck.putCutCard();
            delay();
        }
        dealCards(player, dealer);
        if(checkFor21(player.currentTotal(), dealer.currentTotal())){
            return;
        }
        int playerTotal = wantToHit(player);
        if(winCondition(playerTotal)) {
            return;
        }
        int dealerTotal =  getDealerToMin(dealer);
        winCondition(playerTotal, dealerTotal);
    }

    public void addChips(){
        System.out.println("How many chips do you want to add?");
        while(!sc.hasNextInt()) {
            System.out.println("Please enter a valid number");
            sc.next();
        }
        int addChips = sc.nextInt();
        bank.addChips(addChips);
    }

    /**
     * Welcome message
     */
    public void welcome(){
        System.out.println("----------------------------------------------");
        System.out.println("Welcome to blackjack!");
        System.out.println("The aim of BlackJack is to beat the Dealer's hand without going over 21.");
        System.out.println("This game uses " + AMOUNTOFDECKS + " standard 52-card decks, which are shuffled at the beginning");
        System.out.println("If a cut card is hit the current hand will finish but the deck will be shuffled");
        System.out.println("after the hand is over.");
        System.out.println("Being dealt blackjack pays 6:5");
        System.out.println("Splitting, insurance, and doubling down are not currently offered.");
        System.out.println("----------------------------------------------");
    }


    /**
     * Method that deals the cards
     * @param player
     * @param dealer
     */
    public void dealCards(Human player, Dealer dealer){
        player.takeCard();
        dealer.takeCard();
        player.takeCard();
        dealer.takeCard();
        printDealerHand(dealer);
        printHumanHand(player);
    }

    /**
     * Prints the dealers hand
     * @param dealer
     */
    public void printDealerHand(Dealer dealer){
        System.out.println("Dealers's hand:");
        
        dealer.printHandFaceCardDown();
    }

    /**
     * Prints the players hand
     * @param player
     */
    public void printHumanHand(Human player){
        System.out.println("-------");
        System.out.println("Player's hand:");
        player.printHand();
        int total = player.currentTotal();
        System.out.println("-------");
        System.out.println("Your total is " + total);
        System.out.println("-------");  
    }

    /**
     * Win condition to check for 21
     * @param player
     * @param dealer
     * @return
     */
    public boolean checkFor21(int playerTotal, int dealerTotal){
        if(playerTotal == 21 && dealerTotal == 21){
            System.out.println("You both have blackjack, it's a push.");
            pushed = true;
            return true;
        }
        else if(playerTotal == 21) {
            System.out.println("You have blackjack, you won!");
            // being dealt blackjack pays out 6:5
            // (6/5) == 1.2
            bet *= 1.2;
            //chips += bet;
            bank.addChips(bet);
            justWon = true;
            return true;
        }
        else if(dealerTotal == 21) {
            System.out.println("The dealer has blackjack, you lost!");
            bank.subtractChips(bet);
            //chips -= bet;
            return true;
        }
        return false;

    }


    /**
     * Asks if the player wants another card
     * @param player
     * @return
     */
    public int wantToHit(Human player){
        System.out.println("Do you want another card?");
        String answer = sc.next();
        int total = player.currentTotal();
        while(answer.equalsIgnoreCase("yes")){
            player.takeCard();
            System.out.println("-------");
            player.printLastCard();
            if(player.isBust()) return player.currentTotal();
            
            else{
                total = player.currentTotal();
                if(total == 21){
                    justWon = true;
                    return total;
                }
                System.out.println("-------");
                System.out.println("Your current total is " + total);
                System.out.println("Do you want another card?");
                answer = sc.next();
            }
        }
        return total;
    }

    /**
     * Gets the dealer to the 17 minimum
     * @param dealer
     * @return
     */
    public int getDealerToMin(Dealer dealer){
        System.out.println("-------");
        System.out.println("Dealers's hand:");
        dealer.printHand();
        System.out.println("-------");
        System.out.println("Dealer's current total is " + dealer.currentTotal());
        System.out.println("-------");
        if(dealer.isSeventeenMin()) return dealer.currentTotal();
        while(!dealer.isSeventeenMin()){
            delay();
            if(dealer.isBust()) {
                return dealer.currentTotal();
            }
            else{
                dealer.takeCard();
                dealer.printLastCard();
                System.out.println("-------");
                System.out.println("Dealer's current total is " + dealer.currentTotal());
                System.out.println("-------");
            }
        }
        return dealer.currentTotal();
        
    }

    /**
     * Checks to see who wins
     * @param playerTotal
     * @param dealerTotal
     */
    public void winCondition(int playerTotal, int dealerTotal){
        if(dealerTotal > 21){
            System.out.println("The dealer has bust with " + dealerTotal + ". You win!");
            justWon = true;
            // chips += bet;
            bank.addChips(bet);
        }
        else if(playerTotal > dealerTotal) {
            System.out.println("You win with " + playerTotal);
            justWon = true;
            // chips += bet;
            bank.addChips(bet);
        }
        else if(dealerTotal > playerTotal) {
            System.out.println("The dealer wins with " + dealerTotal);
            // chips -= bet;
            bank.subtractChips(bet);
        }
        else {
            System.out.println("It's a push. You both have " + playerTotal);
            pushed = true;
        }
    }

    /**
     * One param win condition
     * @param total
     * @return
     */
    public boolean winCondition(int total){
        if(total > 21){
            System.out.println("You have bust with " + total);
            bank.subtractChips(bet);
            //chips -= bet;
            return true;
        }
        if(total == 21){
            System.out.println("You have " + total);
            justWon = true;
            bank.addChips(bet);
            // chips += bet;
        }
        return false;
    }

    /**
     * Clears the players hands for a new round
     * @param player
     * @param dealer
     */
    public void clearHands(Human player, Dealer dealer){
        player.clearHand();
        dealer.clearHand();
    }

    /**
     * Delay so that the output is not all at once
     */
    public void delay(){
        try{Thread.sleep(DELAY);}
        catch (Exception e) {System.out.println(e);}
    }
}