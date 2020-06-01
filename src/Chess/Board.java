package Chess;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Contains the major pieces of the game, and deals with game logic.
 * @author Chris Qin
 * @version 1.0
 */
public class Board {

    /**
     * the number of square from in the x and y axis, forming a square grid.
     */
    public static final int GRIDSIZE = 8;
    private static Square[][] squares; //the square on the  board
    private String pickedUpPiece = null; //current picked up piece
    private boolean hasPieceInHand = false; //if player is currently is holding a piece
    private String prevId = ""; //location of last clicked square
    private Piece prevPiece; // last clicked piece
    private Gui gui; 
    private String selectColor = "#cdd26b"; //color to show current selected piece
    private boolean whiteTurn = true;
    private String whiteTurnString = "White TURN";
    private String blackTurnString = "Black TURN";
    private boolean enableTurns = true;
    private FileOutputStream fileOut;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    /**
     * Constructor for Board.
     */
    public Board() {
        resetBoard();
    }

    /**
     * Sets up square's colors.
     * @param startColor true for black, false for white
     */
    public void setUpSquares(boolean startColor) {
        squares[0][0].setColor(startColor);

        for (int i = 0; i < GRIDSIZE; i++) {

            for (int j = 0; j < GRIDSIZE; j++) {
                if (j == 0 && i != 0) { //Sets color based on square above
                    squares[i][j].setColor(!squares[i - 1][j].getColor()); 
                } else if (j != 0) { //Sets color based on square to the left
                    squares[i][j].setColor(!squares[i][j - 1].getColor()); 
                }
            }
        }
    }

    /**
     * Gets squares from board.
     * @return squares on the board
     */
    public static Square[][] getSqaure() {
        return squares;
    }

    /**
     * Returns square on board location.
     * @param id the location to look at
     * @return returns piece on position
     */
    private Piece getSquarePiece(final String id) {
        int[] points = getX_Y(id);
        return squares[points[0]][points[1]].getPiece();
    }

    /**
     * Sets piece on location (Uses code word for the piece).
     * @param id the location to place piece on
     * @param piece the code word of the piece
     */
    private void setPiece(final String id, final String piece) {
        int[] points = getX_Y(id);
        squares[points[0]][points[1]].setPiece(piece);
    }

    /**
     * Gets X and Y from ID, so 16 will be X = 1, Y = 6.
     * @param id concatenated id
     * @return x and y in an array of length 2
     */
    private int[] getX_Y(final String id) {
        int[] points = new int[2];
        points[0] = Integer.parseInt("" + id.charAt(0)) - 1;
        points[1] = Integer.parseInt("" + id.charAt(1)) - 1;
        return points;
    }

    /**
     * Performs game logic.
     * @param id square clicked, will be in xy format. Eg. 56 is x = 5, y = 6.
     */
    public void doGameLogic(final String id) {
        int[] points = getX_Y(id);
        System.out.println("Game: " + id);
        String peiceInSqaure;
        String clickedColor;
        try {
            peiceInSqaure = getSquarePiece(id).getPiece();
            clickedColor = getSquarePiece(id).getColor();
        } catch (Exception exp) {
            peiceInSqaure = "";
            clickedColor = "";
        }

        if (hasPieceInHand) { //has piece in hand
            System.out.println("has piece");
            System.out.println(prevId + " - " + id);
            if (id.equalsIgnoreCase(prevId)) { // clicked on same square
                System.out.println("same clicked");
                hasPieceInHand = false;
                pickedUpPiece = null;
                refresh();
            } else if (peiceInSqaure == "") { // clicked on empty space
                placePiece(id, false);
            } else if (prevPiece.getColor() == clickedColor) { // clicked on same color
                pickUpPeice(points[0], points[1], id);
            } else if (prevPiece.getColor() != clickedColor) { // clicked on enemy color
                placePiece(id, true);
            }
        } else { //has no piece in hand
            if (peiceInSqaure != "") { //and clicked on non-empty space          
                pickUpPeice(points[0], points[1], id);
            }
        }
    }


    private  void placePiece(String clickedLocation, boolean isKill) {
        if (!isKill || !(prevPiece.getPiece().matches(".P"))) {
            if (prevPiece.isValidMove(prevId, clickedLocation)) {
                System.out.println("MOVE!!!!!!!!!!!!!!!!!!!!!!!");
                if (enableTurns) {
                    whiteTurn = !whiteTurn;
                }
                setTurnTitle();
                System.out.println("placed Piece");
                setPiece(clickedLocation, pickedUpPiece);
                setPiece(prevId, "");
                hasPieceInHand = false;
                refresh();
            }
        } else {
            if (PawnKill.isValidKill(prevId, clickedLocation, prevPiece.getColor())) {
                System.out.println("MOVE!!!!!!!!!!!!!!!!!!!!!!!");
                if (enableTurns) {
                    whiteTurn = !whiteTurn;
                }
                setTurnTitle();
                System.out.println("placed Piece");
                setPiece(clickedLocation, pickedUpPiece);
                setPiece(prevId, "");
                hasPieceInHand = false;
                refresh();
            }
        }
    }

    private void setTurnTitle() {
        if (whiteTurn) {
            gui.updateTitle(" - " + whiteTurnString);
        } else {
            gui.updateTitle(" - " + blackTurnString);
        }
    }

    private void pickUpPeice(int xaxis, int yaxis, String clickedLocation) {
        try {
            if ((whiteTurn && getSquarePiece(clickedLocation).getColor() == "white")
                    || (!whiteTurn && getSquarePiece(clickedLocation).getColor() == "black")) {
                hasPieceInHand = true;
                prevId = clickedLocation;
                prevPiece = getSquarePiece(clickedLocation);
                pickedUpPiece = prevPiece.getPiece();
                prevPiece.getColor();
                System.out.println("picked up" + pickedUpPiece);
                refresh();
                gui.buttonColorChange(selectColor, xaxis, yaxis);
            }
        } catch (Exception exp) {
            System.out.println("nothing in square");
        }
    }


    /**
     * Refreshed Window, with peice's new locations.
     */
    public void refresh() {
        setUpSquares(false);
        gui.drawSquares();
        gui.refreshWindow();
        setTurnTitle();
    }

    /**
     * Resets Board.
     */
    public void resetBoard() {
        squares = new Square[GRIDSIZE][GRIDSIZE];

        //fills square array
        for (int i = 0; i < GRIDSIZE; i++) {
            for (int j = 0; j < GRIDSIZE; j++) {
                squares[i][j] = new Square();
            }
        }

        /*
         * b = black
         * w = white
         * R = Rook
         * H = Knight (Horse)
         * B = Bishop
         * K = King
         * Q = Queen
         * P = Pawn
         *
         * 
         *BLACK PIECEs DEFAULT LOCATIONS
         */
        squares[0][0].setPiece("bR");
        squares[0][1].setPiece("bH");
        squares[0][2].setPiece("bB");
        squares[0][3].setPiece("bK");
        squares[0][4].setPiece("bQ");
        squares[0][5].setPiece("bB");
        squares[0][6].setPiece("bH");
        squares[0][7].setPiece("bR");

        squares[1][0].setPiece("bP");
        squares[1][1].setPiece("bP");
        squares[1][2].setPiece("bP");
        squares[1][3].setPiece("bP");
        squares[1][4].setPiece("bP");
        squares[1][5].setPiece("bP");
        squares[1][6].setPiece("bP");
        squares[1][7].setPiece("bP");

        //WHITE PIECE's DEFAULT LOCATIONS
        squares[6][0].setPiece("wP");
        squares[6][1].setPiece("wP");
        squares[6][2].setPiece("wP");
        squares[6][3].setPiece("wP");
        squares[6][4].setPiece("wP");
        squares[6][5].setPiece("wP");
        squares[6][6].setPiece("wP");
        squares[6][7].setPiece("wP");

        squares[7][0].setPiece("wR");
        squares[7][1].setPiece("wH");
        squares[7][2].setPiece("wB");
        squares[7][3].setPiece("wK");
        squares[7][4].setPiece("wQ");
        squares[7][5].setPiece("wB");
        squares[7][6].setPiece("wH");
        squares[7][7].setPiece("wR");

        gui = new Gui("Chess Game - Chris Qin A00944299", 800, 800, this);
        setTurnTitle();
        refresh();
    }

    /**
     * Saves Game.
     * @throws IOException If the is a IOException
     */
    public void saveGame(String fileName) throws IOException {
        fileOut = new FileOutputStream(fileName);
        out = new ObjectOutputStream(fileOut);
        for (int i = 0; i < GRIDSIZE; i++) {
            for (int j = 0; j < GRIDSIZE; j++) {
                out.writeObject(squares[i][j]);
            }
        }
        out.writeBoolean(whiteTurn);
        out.close();
        fileOut.close();
        System.out.println("saved Game");
    }

    /**
     * Loads game.
     * @throws IOException an exception
     * @throws ClassNotFoundException an exception
     */
    public void loadGame(String fileName) throws IOException, ClassNotFoundException {
        in = new ObjectInputStream(new FileInputStream(fileName));  

        for (int i = 0; i < GRIDSIZE; i++) {
            for (int j = 0; j < GRIDSIZE; j++) {
                squares[i][j] = (Square) in.readObject();
            }
        }
        whiteTurn = in.readBoolean();
        System.out.println(squares[0][0].getPiece());
        in.close();
        System.out.println("loaded Game");
        refresh();

    }
}