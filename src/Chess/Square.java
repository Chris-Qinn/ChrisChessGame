package Chess;

import java.io.Serializable;

/**
 * Squares on the board. Has a color (black or white), a piece, and has 
 * necessary methods to keep track of each piece.
 * @author Chris Qin
 * @version 1.0
 */
public class Square implements Serializable {
    
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1L;
    private Piece piece;
    private boolean isBlack;
    
    public Square() {
        this.piece = null;
    }
    
    /**
     * Sets color.
     * @param color true for black, false for white
     */
    public void setColor(boolean color) {
        isBlack = color;
    }

    /**
     * Gets color.
     * @return true for black, false for white
     */
    public boolean getColor() {
        return isBlack;
    }
    
    /**
     * Returns piece in square.
     * @return current price, this square holds
     */
    public Piece getPiece() {
        return piece;
    }
    
    /**
     * Sets pieces.
     * @param piece the codeword of the piece
     */
    public void setPiece(final String piece) {
        if (piece.equals("wR")) {
            this.piece = new Rook(false);
        } else if (piece.equalsIgnoreCase("wH")) {
            this.piece = new Knight(false);
        } else if (piece.equalsIgnoreCase("wB")) {
            this.piece = new Bishop(false);
        } else if (piece.equalsIgnoreCase("wQ")) {
            this.piece = new Queen(false);
        } else if (piece.equalsIgnoreCase("wK")) {
            this.piece = new King(false);
        } else if (piece.equalsIgnoreCase("wP")) {
            this.piece = new Pawn(false);
        } else if (piece.equalsIgnoreCase("bR")) {
            this.piece = new Rook(true);
        } else if (piece.equalsIgnoreCase("bH")) {
            this.piece = new Knight(true);
        } else if (piece.equalsIgnoreCase("bB")) {
            this.piece = new Bishop(true);
        } else if (piece.equalsIgnoreCase("bQ")) {
            this.piece = new Queen(true);
        } else if (piece.equalsIgnoreCase("bK")) {
            this.piece = new King(true);
        } else if (piece.equalsIgnoreCase("bP")) {
            this.piece = new Pawn(true);
        } else {
            this.piece = null;
        }
    }

}