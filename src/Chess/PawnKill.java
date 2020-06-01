package Chess;

/**
 * Pawn piece class.
 * @author Chris Qin
 * @version 1.0
 */
public abstract class PawnKill extends Piece {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1L;
    private String image;
    private static boolean black;

    

    /**
     * returns piece.
     * @return code word of piece
     */
    public String getPiece() {
        if (black) {
            return "bP";
        } else {
            return "wP";
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
    public static boolean isValidKill(String currentPosId, String moveToId, String black) {
        int[] currentPos = getX_Y(currentPosId);
        int[] moveTo = getX_Y(moveToId);

        if (moveTo[1] != currentPos[1] && Math.abs(moveTo[1] - currentPos[1]) == 1) {
            System.out.println("{PPAWWWN KILLL: " + (moveTo[0] - currentPos[0]));
            if (black.equalsIgnoreCase("black") && (moveTo[0] - currentPos[0]) == 1) {
                return true;
            } else if (black.equals("white") && moveTo[0] - currentPos[0] == -1 ) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if Path is clear.
     * @param currentPos first position
     * @param moveTo moveto position
     * @param xy checks direction
     * @return if path is clear
     */
    protected boolean checkIfPathClear(int[] currentPos, int[] moveTo, int xy) {  
        int stepCount;
        stepCount = moveTo[xy] - currentPos[xy];
        System.out.println(stepCount + " XY: " + xy);
        return checkInDirectionStraight(stepCount, getVector(stepCount), xy, currentPos, false);
    }
}