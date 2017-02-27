package tetris;

public class Piece implements BoardPiece{

    private Block[][] blocks;

    public Piece(String piece) {
        String[] rows = piece.split("\n");
        for (int i=0; i<rows.length; i++) {
            char[] column = rows[i].toCharArray();
            for (int j=0; j<column.length; j++) {
                if ((i==0) && (j==0)) {
                    blocks = new Block[rows.length][column.length];
                }
                blocks[i][j] = new Block(column[j]);
            }
        }
    }

    public String toString() {
        String s = "";
        if (blocks != null) {
            for (int i=0; i<blocks.length; i++) {
                for (int j=0; j<blocks[i].length; j++) {
                    s += blocks[i][j].toString();
                }
            s += "\n";
            }
        }
        return s;
    }

    public int width() {
       return blocks[0].length;
   }

   public int height() {
       return blocks.length;
   }

   public boolean is_hollow_at(int i, int j) {
       return blocks[i][j].toString().equals(String.valueOf(BoardPiece.EMPTY));
   }

    public Piece rotateRight() { // transpose + reverse each row
        return new Piece(reverse_rows(transpose(blocks)));
    }

    public Piece rotateLeft() { // reverse each row + transpose
        return new Piece(transpose(reverse_rows(blocks)));
    }

    private Block[][] transpose(Block blocks[][]) {
        Block[][] transposedBlocks = new Block[blocks.length][blocks[0].length];
        for (int i=0; i<blocks.length; i++) {
            for (int j=0; j<blocks[i].length; j++) {
                transposedBlocks[j][i] = blocks[i][j];
            }
        }
        return transposedBlocks;
    }

    private Block[][] reverse_rows(Block blocks[][]) {
        Block[][] reversedBlocks = new Block[blocks.length][blocks[0].length];
        for (int i=0; i<blocks.length; i++) {
            for (int j=0; j<blocks[i].length; j++) {
                reversedBlocks[i][j] = blocks[i][blocks[i].length-j-1];
            }
        }
        return reversedBlocks;
    }

    private Piece(Block blocks[][]) {
        this.blocks = blocks;
    }

}
