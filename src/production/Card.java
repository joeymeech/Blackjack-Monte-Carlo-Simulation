package production;

/**
 * Represents a Playing Card
 */
public class Card {
    private Value val;
    private Suit suit;

    /**
     * Card constructor
     * @param suit
     * @param val
     */
    public Card(Suit suit, Value val){
        this.suit = suit;
        this.val = val;
    }

    /**
     * @return Card Value
     */
    public Value getValue(){
        return this.val;
    }

    /**
     * @return Card Suit
     */
    public Suit getSuit(){
        return this.suit;
    }

    /**
     * the first 9 are their orders plus 2. Ex. Seven is position 5 in the enum, 5 + 2 = 7
     * if the enum is in position 12 it is an ace (worth 11)...
     * @return the number of the Value enum
     */
    public int getNum() {
        int ordinal = this.val.ordinal();
        if(ordinal > 8 && ordinal < 12) return 10;
        else if(ordinal == 12) return 11;
        else return ordinal + 2;
    }
}
