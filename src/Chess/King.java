package Chess;

/**
 * King piece class.
 * @author Chris Qin
 * @version 1.0
 */
public class King extends Piece {
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
    public King(final boolean black) {
        this.black = black;
        if (black) {
            image = this.getClass().getResource("/images/blackKing.png").getPath();
        } else {
            image = this.getClass().getResource("/images/whiteKing.png").getPath();
        }
    }

    /**
     * returns piece.
     * @return code word of piece
     */
    public String getPiece() {
        if (black) {
            return "bK";
        } else {
            return "wK";
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
        if ((Math.abs(moveTo[0] - currentPos[0]) <= 1)
                && (Math.abs(moveTo[1] - currentPos[1]) <= 1)) {
            return true;
        } else {
            return false;
        }
    }
}