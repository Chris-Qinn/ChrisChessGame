package Chess;

/**
 * Knight piece class.
 * @author Chris Qin
 * @version 1.0
 */
public class Knight extends Piece {

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
    public Knight(final boolean black) {
        this.black = black;
        if (black) {
            image = this.getClass().getResource("/images/blackKnight.png").getPath();
        } else {
            image = this.getClass().getResource("/images/whiteKnight.png").getPath();
        }
    }

    /**
     * returns piece.
     * @return code word of piece
     */
    public String getPiece() {
        if (black) {
            return "bH";
        } else {
            return "wH";
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
        if (((Math.abs(moveTo[0] - currentPos[0]) == 1) 
                && Math.abs(moveTo[1] - currentPos[1]) == 2)
                || ((Math.abs(moveTo[1] - currentPos[1]) == 1) 
                        && Math.abs(moveTo[0] - currentPos[0]) == 2)) {
            return true;
        } else {
            return false;
        }
    }
}