package Chess;

/**
 * Bishop piece class.
 * @author Chris Qin
 * @version 1.0
 */
public class Bishop extends Piece  {


    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1L;
    private String image;
    private boolean black;

    /**
     * Constructs Piece and sets Image based on color.
     * @param black true if piece is black.
     */
    public Bishop(final boolean black) {
        this.black = black;
        if (black) {
            image = this.getClass().getResource("/images/blackBishop.png").getPath();
        } else {
            image = this.getClass().getResource("/images/whiteBishop.png").getPath();
        }
    }

    /**
     * returns piece.
     * @return code word of piece
     */
    public String getPiece() {
        if (black) {
            return "bB";
        } else {
            return "wB";
        }
    }

    /**
     * Return image path of this piece.
     * @return the image path
     */
    public String getImagePath() {
        return image;
    }
    
    /**
     * Returns color of the piece.
     * @return color of the piece
     */
    public String getColor() {
        if (black) {
            return "black";
        } else {
            return "white";
        }
    }
    
    /**
     * Checks if move is valid.
     * @param currentPosId current position
     * @param moveToId move to position
     */
    public boolean isValidMove(String currentPosId, String moveToId) {
        int[] currentPos = getX_Y(currentPosId);
        int[] moveTo = getX_Y(moveToId);
        if (Math.abs(moveTo[0] - currentPos[0]) ==  Math.abs(moveTo[1] - currentPos[1]) 
                && checkIfPathClear(currentPos, moveTo)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if Path is clear.
     * @param currentPos first position
     * @param moveTo moveto position
     * @return if path is clear
     */
    protected boolean checkIfPathClear(int[] currentPos, int[] moveTo) {
        int stepCountX = moveTo[0] - currentPos[0];
        int stepCountY = moveTo[1] - currentPos[1];
        System.out.println("Bishop: " + stepCountX + ", " + stepCountY);
        return checkInDirectionStraight(stepCountX, getVector(stepCountX), 
                stepCountX + stepCountY, currentPos, true);
    }

}