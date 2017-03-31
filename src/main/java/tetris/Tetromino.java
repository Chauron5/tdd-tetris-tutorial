package tetris;

/**
 * Implementation of a complete piece.
 * @author javi
 *
 */
public final class Tetromino implements BoardPiece {
    /**
     * T shaped piece.
     */
  public static final Tetromino T_SHAPE =
    new Tetromino(new Piece(".T.\nTTT\n..."));
   /**
    * I shaped piece.
    */
  public static final Tetromino I_SHAPE =
    new Tetromino(new Piece("....\nIIII\n....\n....\n"));
  /**
   * O shaped piece.
   */
  public static final Tetromino O_SHAPE =
    new Tetromino(new Piece(".OO\n.OO\n..."));

  /**
   * Piece.
   */
  private Piece piece;

  /**
   * Constructor.
   * @param newPiece Piece of tetromino
   */
    private Tetromino(final Piece newPiece) {
        this.piece = newPiece;
    }

    /**
     * Returns string representing the piece.
     * @return String representing the piece
     */
    public String toString() {
        return piece.toString();
    }

    /**
     * Rotates tetromino right.
     * @return Tetromino rotated
     */
    public Tetromino rotateRight() {
       return new Tetromino(piece.rotateRight());
   }

    /**
     * Rotates tetromino left.
     * @return Tetromino rotated
     */
   public Tetromino rotateLeft() {
       return new Tetromino(piece.rotateLeft());
   }

   /**
    * @return int with of the tetromino
    */
   public int width() {
        return piece.width();
    }

   /**
    * @return int height of the tetromino
    */
    public int height() {
        return piece.height();
    }

    /**
     * @param i row
     * @param j column
     * @return true if tetromino is not empty at (i,j)
     */
    public boolean isHollowAt(final int i, final int j) {
        return piece.isHollowAt(i, j);
    }

}
