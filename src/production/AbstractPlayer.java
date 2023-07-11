package production;
import java.util.ArrayList;

/**
 * Represents an AbstractPlayer
 */
public abstract class AbstractPlayer{
    private ArrayList<Card> hand;
    private Deck deck;

    /**
     * constructor, creates an arraylist of the player hand
     * 
     * @param deck
     */
    public AbstractPlayer(Deck deck){
        hand = new ArrayList<Card>();
        this.deck = deck;
    }

    /**
     * takes the top cards off of the deck and adds it to the player hand
     */
    public void takeCard(){
        hand.add(deck.takeCard());
    }

    public int handSize(){
        return hand.size();
    }

    /**
     * @return player has bust
     */
    public boolean isBust(){
        return currentTotal() > 21;
    }

    /**
     * @return player has twenty-one
     */
    public boolean isTwentyOne(){
        return currentTotal() == 21;
    }

    /**
     * clears the hand
     */
    public void clearHand(){
        hand.clear();
    }

    /**
     * @return the number total of the players current hand
     */
    public int currentTotal(){
        int currentCount = 0;
        int aceCount = 0;
        for(int i = 0; i < hand.size(); i++){
            currentCount += hand.get(i).getNum();
            if(hand.get(i).getNum() == 11) aceCount++;
        }
        if(currentCount > 21 && aceCount > 0){
            for(int i = 0; i < aceCount; i++){
                if(currentCount > 21){
                    currentCount -= 10;
                }
            }
        }
        return currentCount;
    }

    /**
     * prints the hand
     */
    public void printHand(){
        for(int i = 0; i < hand.size(); i++){
            try{Thread.sleep(1000);}
            catch (Exception e) {System.out.println(e);}
            System.out.println(hand.get(i).getNum() + " of " + hand.get(i).getSuit());
        }
    }

    /**
     * prints the last card
     */
    public void printLastCard(){
        try{Thread.sleep(1000);}
        catch (Exception e) {System.out.println(e);}
        System.out.println(hand.get(hand.size()-1).getNum() + " of " + hand.get(hand.size()-1).getSuit());
    }

    public void printHandFaceCardDown(){
        System.out.println("Face Down");
        for(int i = 1; i < hand.size(); i++){
            try{Thread.sleep(1000);}
            catch (Exception e) {System.out.println(e);}
            System.out.println(hand.get(i).getNum() + " of " + hand.get(i).getSuit());
        }
    }


    /**
     * gives the player an predetermined hand 
     * 
     * @param firstValue
     * @param secondValue
     */
    public void predeterminedHumanHand(int firstValue, int secondValue){
        Suit s1 = Suit.Clubs;
        if(firstValue == 11) firstValue = 12;
        else{
            firstValue = firstValue - 2;
        }
        if(secondValue == 11) secondValue = 12;
        else{
            secondValue = secondValue - 2;
        }
        Value r1 = Value.values()[firstValue];
        Card c1 = new Card(s1, r1);
        hand.add(c1);
        Value r2 = Value.values()[secondValue];
        Suit s2 = Suit.Diamonds;
        Card c2 = new Card(s2, r2);
        hand.add(c2);

    }

    /**
     * gives the dealer a predetermined hand
     * 
     * @param value
     */
    public void predeterminedDealerCard(int value){
        Suit s = Suit.Clubs;
        if(value == 11) value = 12;
        else{
            value = value - 2;
        }
        Value r = Value.values()[value];
        Card c = new Card(s, r);
        hand.add(c);
    }

    /**
     * @return total of the first card
     */
    public int firstCardTotal(){
        return hand.get(hand.size()-1).getNum();
    }
}