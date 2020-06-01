package Chess;

/**
 * Pawn piece class.
 * @author Chris Qin
 * @version 1.0
 */
public class Pawn extends Piece {

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
    public Pawn(final boolean black) {
        this.black = black;
        if (black) {
            image = this.getClass().getResource("/images/blackPawn.png").getPath();
        } else {
            image = this.getClass().getResource("/images/whitePawn.png").getPath();

        }
    }

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
    public boolean isValidMove(String currentPosId, String moveToId) {
        int[] currentPos = getX_Y(currentPosId);
        int[] moveTo = getX_Y(moveToId);
        if (checkIfPathClear(currentPos, moveTo, 0)) {
            if (canCharge(currentPos)) {
                if (moveTo[1] == currentPos[1]) {
                    if (moveTo[0] - currentPos[0] >= -2 
                            && moveTo[0] - currentPos[0] < 0 
                            && !black) {
                        return true;
                    } else if (moveTo[0] - currentPos[0] <= 2 
                            && moveTo[0] - currentPos[0] > 0 
                            && black) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (moveTo[1] == currentPos[1]) {
                    if (moveTo[0] - currentPos[0] == -1 && !black) {
                        return true;
                    } else if (moveTo[0] - currentPos[0] == 1 && black) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if piece can charge.
     * @param currentPos current position of piece
     * @return if piece can change
     */
    private boolean canCharge(int[] currentPos) {
        if (currentPos[0] == 6 && !black) {
            return true; 
        } else if (currentPos[0] == 1 && black) {
            return true;
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