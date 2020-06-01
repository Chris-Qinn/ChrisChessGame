package Chess;

/**
 * Player class.
 * @author Chris Qin
 * @version 1.0
 */
public class Player {
    protected String color;
    
    /**
     * Constructs player.
     * @param color the color
     */
    public Player(String color) {
        this.color = color;
    }
    
    /**
     * set's player color.
     * @param color the color to set to
     */
    public void setColor(String color) {
        this.color = color;
    }
}