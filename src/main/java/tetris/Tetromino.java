package tetris;

public final class Tetromino implements BoardPiece {
  public static final Tetromino T_SHAPE =
    new Tetromino(new Piece(".T.\nTTT\n..."));
  public static final Tetromino I_SHAPE =
    new Tetromino(new Piece("....\nIIII\n....\n....\n"));
  public static final Tetromino O_SHAPE =
    new Tetromino(new Piece(".OO\n.OO\n..."));

  private Piece piece;

    private Tetromino(final Piece newPiece) {
        this.piece = newPiece;
    }

    public String toString() {
        return piece.toString();
    }

    public Tetromino rotateRight() {
       return new Tetromino(piece.rotateRight());
   }

   public Tetromino rotateLeft() {
       return new Tetromino(piece.rotateLeft());
   }

   public int width() {
        return piece.width();
    }

    public int height() {
        return piece.height();
    }

    public boolean is_hollow_at(final int i, final int j) {
        return piece.is_hollow_at(i, j);
    }

}
