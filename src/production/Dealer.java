package production;
/**
 * Dealer class
 */
public class Dealer extends AbstractPlayer{

    /**
     * @param deck
     */
    public Dealer(Deck deck){
        super(deck);
    }

    /**
     * @return dealer has the minimum
     */
    public boolean isSeventeenMin(){
        return currentTotal() >= 17;
    }

    /**
     * @return the total of the card that the players can see
     */
    public int faceUpTotal(){
        return firstCardTotal();
    }

}
