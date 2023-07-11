package production;
/**
 * Represents an Bank
 */
public class Bank {
    private int chips;
    private int tableMinimum = 500;

    /**
     * Bank constructor
     */
    public Bank(){
        this.chips = 5000;
    }

    /**
     * Adds chips to chips var
     * @param chips
     */
    public void addChips(int chips){
        this.chips += chips;
    }

    /**
     * Subtracts chips from chips var
     * @param chips
     */
    public void subtractChips(int chips){
        this.chips -= chips;
    }

    /**
     * @return chips
     */
    public int getChips(){
        return this.chips;
    }

    /**
     * @return tableMinimum
     */
    public int getTableMinimum(){
        return tableMinimum;
    }

    /**
     * @return chips <= 0
     */
    public boolean isOutOfChips(){
        return chips <= 0;
    }

    /**
     * @param bet
     * @return boolean if bet is greater than table min
     */
    public boolean hasTableMinium(int bet){
        return bet >= tableMinimum;
    }
}
