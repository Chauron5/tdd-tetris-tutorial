package tetris;
/**
 * This class implements a tetris board.
 */
public class Board {

    /**
     * atribute that contains a String to throw with the exception:
     * AlreadyFallingException.
     */
    public  static final String ALREADY_FALLING = "already falling";

    /**
    * Number of rows of board.
    */
    private final int rows;
    /**
    * Number of columns of board.
    */
    private final int columns;
    /**
    * Piece falling.
    */
    private BoardPiece fallingBlock;
    /**
    * Current block row.
    */
    private int currentBlockRow;
    /**
    * Current block column.
    */
    private int currentBlockColumn;
    /**
    * MAtrix representing the board.
    */
    private char[][] board;
    /**
    * Indicates if piece reached bottom.
    */
    private boolean lastTick;

    /**
    * Constructor.
    * @param numRows number of rows of the board.
    * @param numColumns number of columns of the board.
    */
    public Board(final int numRows, final int numColumns) {
        this.rows = numRows;
        this.columns = numColumns;
        this.fallingBlock = null;
        this.board = new char[numRows][numColumns];
        fillWith(board, BoardPiece.EMPTY);
        this.lastTick = false;
    }

    /**
    * Returns the board as a String.
    * @return board as String.
    */
    public final String toString() {
        String s = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (fallingBlockIsAt(i, j)) {
                    char[][] block =
                      new char[fallingBlock.width()][fallingBlock.height()];
                    fillWith(block, fallingBlock.toString());
                    s += block [(i - currentBlockRow)]
                               [(j - currentBlockColumn)];
                } else {
                    char[] c = {board[i][j] };
                    s += new String(c);
                }
            }
            s += "\n";
        }
        return s;
    }

    /**
    * Indicates if a piece is falling.
    * @return true if a piece is falling.
    */
    public final boolean hasFalling() {
        return (fallingBlock != null);
    }


    /**
     * Drops a piece into board.
     * @param b BoarPiece
     * @throws IllegalStateException exception
     */
    public final void drop(final BoardPiece b) throws IllegalStateException {
        if ((fallingBlock == null) || (lastTick)) {
            fallingBlock = b;
            currentBlockRow = 0;
            currentBlockColumn = (this.columns / 2) - (b.width() / 2);
        } else {
            throw new IllegalStateException(Board.ALREADY_FALLING);
        }
    }

    /**
    * Moves falling piece down.
    */
    public final void tick() {
        if (fallingBlock != null) {
            if (!lastTick) {
                currentBlockRow++;
                if (reachedBottom() || touchedAnotherBlock()) {
                    lastTick = true;
                }
            } else {
                fillWith(board, toString());
                fallingBlock = null;
                lastTick = false;
            }
        }
    }

    /**
    * fills all matrix with char c.
    * @param matrix char[][] to fill.
    * @param c char to fill with.
    */
    private void fillWith(final char[][] matrix, final char c) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = c;
            }
        }
    }

    /**
    * Fills matrix (Board) with a string representing the board.
    * @param matrix char[][] to fill.
    * @param s String to fill with.
    */
    private void fillWith(final char[][] matrix, final String s) {
        String[] mrows = s.split("\n");
        for (int i = 0; i < mrows.length; i++) {
            char[] column = mrows[i].toCharArray();
            for (int j = 0; j < column.length; j++) {
                matrix[i][j] = column[j];
            }
        }
    }

    /**
    * Indicates if a piece reached the bottom.
    * @return true if piece reached bottom, false otherwise.
    */
    private boolean reachedBottom() {
        int reachedRow = currentBlockRow;
        String[] s = fallingBlock.toString().split("\n");

        for (int i = 0; i < s.length; i++) {
            if (s[i].replace(BoardPiece.EMPTY, ' ').trim().length() != 0) {
                reachedRow++;
            }
        }

        return (reachedRow == rows);
    }

    /**
    * Indicates if a piece touched another block.
    * @return true if piece touched another block, false otherwise.
    */
    private boolean touchedAnotherBlock() {
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < columns; j++) {
                if ((board[i + 1][j] != BoardPiece.EMPTY)
                  && fallingBlockIsAt(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
    * indicates if the piece can be moved left.
    * @return true if the piece can be moved left.
    */
    public final boolean canMoveLeft() {
      if (fallingBlockIsAtColumn(0)) {
        return false;
      }
      for (int i = 0; i < rows - 1; i++) {
        for (int j = 0; j < columns; j++) {
          if (fallingBlockIsAt(i, j)) {
            if (board[i][j - 1] != BoardPiece.EMPTY) {
              return false;
            }
          }
        }
      }
      return true;
    }

    /**
    * indicates if the piece can be moved rigth.
    * @return true if the piece can be moved rigth.
    */
    public final boolean canMoveRigth() {
      if (fallingBlockIsAtColumn(columns - 1)) {
        return false;
      }
      for (int i = 0; i < rows - 1; i++) {
        for (int j = columns - 1; j >= 0; j--) {
          if (fallingBlockIsAt(i, j)) {
            if (board[i][j + 1] != BoardPiece.EMPTY) {
              return false;
            }
          }
        }
      }
      return true;
    }

    /**
    * Indicates if falling block is at the position (i, j).
    * @param row row.
    * @param column column.
    * @return true if block is at that position, false otherwise.
    */
    private boolean fallingBlockIsAt(final int row, final int column) {
        if (fallingBlock != null) {
            return ((currentBlockRow <= row)
                    && (row < currentBlockRow + fallingBlock.height())
                    && (currentBlockColumn <= column)
                    && (column < currentBlockColumn + fallingBlock.width())
                    && (!fallingBlock.isHollowAt(row - currentBlockRow,
                      column - currentBlockColumn
                      + fallingBlock.width() / 2 - 1)));
        } else {
            return false;
        }
    }

    /**
    * Indicates if the block is at the column.
    * @param column column
    * @return true if falling block is at column (column)
    */
    private boolean fallingBlockIsAtColumn(final int column) {
      for (int i = 0; i < this.rows; i++) {
        if (fallingBlockIsAt(i, column)) {
          return true;
        }
      }
      return false;
    }

    /**
    * Moves piece left.
    */
    public final void moveLeft() {
      if (canMoveLeft()) {
        this.currentBlockColumn--;
      }
    }

    /**
    * Moves piece rigth.
    */
    public final void moveRigth() {
      if (canMoveRigth()) {
        this.currentBlockColumn++;
      }
    }

    /**
    * iMoves piece down.
    */
    public final void moveDown() {
      this.tick();
    }
}
