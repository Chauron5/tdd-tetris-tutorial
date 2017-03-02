package tetris;

public class Board {

    public  static final String ALREADY_FALLING = "already falling";

    private final int rows;
    private final int columns;
    private BoardPiece falling_block;
    private int current_block_row;
    private int current_block_column;
    private char board[][];
    private boolean last_tick;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.falling_block = null;
        this.board = new char[rows][columns];
        fill_with(board, BoardPiece.EMPTY);
        this.last_tick = false;
    }

    public String toString() {
        String s = "";
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (falling_block_is_at(i,j)) {
                    char block[][] = new char[falling_block.width()][falling_block.height()];
                    fill_with(block, falling_block.toString());
                    s += block[(i-current_block_row)][(j-current_block_column)];
                } else {
                    char c[] = { board[i][j] };
                    s += new String(c);
                }
            }
            s += "\n";
        }
        return s;
    }

    public boolean hasFalling() {
        return (falling_block != null);
    }

    public void drop(BoardPiece b) throws IllegalStateException {
        if ((falling_block == null) || (last_tick)) {
            falling_block = b;
            current_block_row = 0;
            current_block_column = (this.columns / 2) - (b.width() / 2);
        } else {
            throw new IllegalStateException(Board.ALREADY_FALLING);
        }
    }

    public void tick() {
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

    private void fill_with(char matrix[][], char c) {
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                matrix[i][j] = c;
            }
        }
    }

    private void fill_with(char matrix[][], String s) {
        String[] rows = s.split("\n");
        for (int i=0; i<rows.length; i++) {
            char[] column = rows[i].toCharArray();
            for (int j=0; j<column.length; j++) {
                matrix[i][j] = column[j];
            }
        }
    }

    private boolean reached_bottom() {
        int reached_row = current_block_row;
        String[] s = falling_block.toString().split("\n");

        for (int i=0; i<s.length; i++) {
            if (s[i].replace(BoardPiece.EMPTY, ' ').trim().length() != 0) {
                reached_row++;
            }
        }

        return (reached_row == rows);
    }

    private boolean touched_another_block() {
        for (int i=0; i<rows-1; i++) {
            for (int j=0; j<columns; j++) {
                if ((board[i+1][j] != BoardPiece.EMPTY) &&
                    falling_block_is_at(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean can_move_left() {
      if (falling_block_is_at_column(0)) {
        return false;
      }
      for(int i = 0; i < rows-1; i++){
        for(int j = 0; j < columns; j++){
          if(falling_block_is_at_column(j)){
            if(falling_block_is_at(i,j)){
              if (board[i][j-1] != BoardPiece.EMPTY){
                return false;
              }
            }
          }
        }
      }
      return true;
    }

    private boolean falling_block_is_at(int row, int column) {
        if (falling_block != null) {
            return ((current_block_row <= row) &&
                    (row < current_block_row + falling_block.height()) &&
                    (current_block_column <= column) &&
                    (column < current_block_column + falling_block.width()) &&
                    (!falling_block.is_hollow_at(row - current_block_row,
                                                 column - current_block_column + falling_block.width()/2 - 1)));
        } else {
            return false;
        }
    }

    private char[] getColumn(int index) {
      char[] column = new char[this.rows];

      for (int i = 0 ; i < this.rows ; i++) {
          for (int j = 0 ; j < this.columns; j++) {
            if (j == index) {
              column[i]= board[i][j];
            }
          }
      }

      return column;
    }

    private boolean falling_block_is_at_column(int column){
      for (int i=0; i < this.rows; i++){
        if(falling_block_is_at(i,column)){
          return true;
        }
      }
      return false;
    }

    public void moveLeft() {
      if (can_move_left()) {
        this.current_block_column--;
      }
    }

    public void moveRigth() {
      if (!falling_block_is_at_column(this.columns-1)){
        this.current_block_column++;
      }
    }

    public void moveDown() {
      this.tick();
    }
}
