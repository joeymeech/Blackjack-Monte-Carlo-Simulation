package production;
/**
 * Human Player class
 */
public class Human extends AbstractPlayer{
    private String playerName;

    /**
     * @param deck
     * @param playerName
     */
    public Human(Deck deck, String playerName){
        super(deck);
        this.playerName = playerName;
    }
    
    /**
     * @param deck
     */
    public Human(Deck deck){
        super(deck);
    }

    /**
     * @return player name
     */
    public String getName(){
        return playerName;
    }

}
