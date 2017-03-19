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
    private BoardPiece falling_block;
    /**
    * Current block row.
    */
    private int current_block_row;
    /**
    * Current block column.
    */
    private int current_block_column;
    /**
    * MAtrix representing the board.
    */
    private char[][] board;
    /**
    * Indicates if piece reached bottom.
    */
    private boolean last_tick;

    /**
    * Constructor.
    * @param numRows number of rows of the board.
    * @param numColumns number of columns of the board.
    */
    public Board(final int numRows, final int numColumns) {
        this.rows = numRows;
        this.columns = numColumns;
        this.falling_block = null;
        this.board = new char[numRows][numColumns];
        fill_with(board, BoardPiece.EMPTY);
        this.last_tick = false;
    }

    /**
    * Returns the board as a String.
    * @return board as String.
    */
    public final String toString() {
        String s = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (falling_block_is_at(i, j)) {
                    char[][] block =
                      new char[falling_block.width()][falling_block.height()];
                    fill_with(block, falling_block.toString());
                    s += block [(i - current_block_row)]
                               [(j - current_block_column)];
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
        return (falling_block != null);
    }

    /**
    * Drops a piece into board.
    * @param b BoardPiece to drop into board.
    * @throws IllegalStateException
    */
    public final void drop(final BoardPiece b) throws IllegalStateException {
        if ((falling_block == null) || (last_tick)) {
            falling_block = b;
            current_block_row = 0;
            current_block_column = (this.columns / 2) - (b.width() / 2);
        } else {
            throw new IllegalStateException(Board.ALREADY_FALLING);
        }
    }

    /**
    * Moves falling piece down.
    */
    public final void tick() {
        if (falling_block != null) {
            if (!last_tick) {
                current_block_row++;
                if (reached_bottom() || touched_another_block()) {
                    last_tick = true;
                }
            } else {
                fill_with(board, toString());
                falling_block = null;
                last_tick = false;
            }
        }
    }

    /**
    * fills all matrix with char c.
    * @param matrix char[][] to fill.
    * @param c char to fill with.
    */
    private void fill_with(final char[][] matrix, final char c) {
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
    private void fill_with(final char[][] matrix, final String s) {
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
    private boolean reached_bottom() {
        int reached_row = current_block_row;
        String[] s = falling_block.toString().split("\n");

        for (int i = 0; i < s.length; i++) {
            if (s[i].replace(BoardPiece.EMPTY, ' ').trim().length() != 0) {
                reached_row++;
            }
        }

        return (reached_row == rows);
    }

    /**
    * Indicates if a piece touched another block.
    * @return true if piece touched another block, false otherwise.
    */
    private boolean touched_another_block() {
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < columns; j++) {
                if ((board[i + 1][j] != BoardPiece.EMPTY)
                  && falling_block_is_at(i, j)) {
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
    public final boolean can_move_left() {
      if (falling_block_is_at_column(0)) {
        return false;
      }
      for (int i = 0; i < rows - 1; i++) {
        for (int j = 0; j < columns; j++) {
          if (falling_block_is_at(i, j)) {
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
    public final boolean can_move_rigth() {
      if (falling_block_is_at_column(columns - 1)) {
        return false;
      }
      for (int i = 0; i < rows - 1; i++) {
        for (int j = columns - 1; j >= 0; j--) {
          if (falling_block_is_at(i, j)) {
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
    private boolean falling_block_is_at(final int row, final int column) {
        if (falling_block != null) {
            return ((current_block_row <= row)
                    && (row < current_block_row + falling_block.height())
                    && (current_block_column <= column)
                    && (column < current_block_column + falling_block.width())
                    && (!falling_block.is_hollow_at(row - current_block_row,
                      column - current_block_column
                      + falling_block.width() / 2 - 1)));
        } else {
            return false;
        }
    }

    /**
    * Indicates if the block is at the column.
    * @param column column
    */
    private boolean falling_block_is_at_column(final int column) {
      for (int i = 0; i < this.rows; i++) {
        if (falling_block_is_at(i, column)) {
          return true;
        }
      }
      return false;
    }

    /**
    * Moves piece left.
    */
    public void moveLeft() {
      if (can_move_left()) {
        this.current_block_column--;
      }
    }

    /**
    * Moves piece rigth.
    */
    public final void moveRigth() {
      if (can_move_rigth()) {
        this.current_block_column++;
      }
    }

    /**
    * iMoves piece down.
    */
    public void moveDown() {
      this.tick();
    }
}
