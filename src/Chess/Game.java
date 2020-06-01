package Chess;

/**
* Main driver class for the chess game, creates board and start game.
* @author Chris Qin
* @version 1.0
*/
public class Game {
   private static Board board;

   /**
    * Main method, run the program.
    * @param args command line arguments
    */
   public static void main(String[] args) {
       startGame();
   }

   /**
    * Starts game.
    */
   public static void startGame() {
       board = new Board();
       board.setUpSquares(false); //draws initial square starting with white (false)
       board.refresh(); //updates screen
       
   }
 
}
