package tetris;

/**
 * A class representing a piece of
 * the board
 */
public interface BoardPiece {
    /**
     * Char thar represents the value of the piece
     */
    final char EMPTY = '.';

    /**
    * @return String of the piece
    */
    String toString();

    /**
    * @return with of the piece.
    */
    int width();

    /**
    * @return height of the piece.
    */
    int height();

    /**
    * @param i position(row) at the piece.
    * @param j position(column) at the piece.
    * @return true if the piece is not empty at  point (i,j).
    */
    boolean is_hollow_at(int i, int j);

}
