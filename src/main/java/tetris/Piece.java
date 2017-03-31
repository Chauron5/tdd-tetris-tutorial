package tetris;

/**
 * Class that represents a Piece of the tetris game.
 *
 * @author javi
 *
 */
public class Piece implements BoardPiece {

    /**
     * Array containing the shape of the piece.
     */
    private Block[][] blocks;

    /**
     * Constructor.
     *
     * @param piece
     *            String representing the shape of the piece
     */
    public Piece(final String piece) {
        String[] rows = piece.split("\n");
        for (int i = 0; i < rows.length; i++) {
            char[] column = rows[i].toCharArray();
            for (int j = 0; j < column.length; j++) {
                if ((i == 0) && (j == 0)) {
                    blocks = new Block[rows.length][column.length];
                }
                blocks[i][j] = new Block(column[j]);
            }
        }
    }

    /**
     * Returns the shape of the piece as string.
     *
     * @return String
     */
    public final String toString() {
        String s = "";
        if (blocks != null) {
            for (int i = 0; i < blocks.length; i++) {
                for (int j = 0; j < blocks[i].length; j++) {
                    s += blocks[i][j].toString();
                }
                s += "\n";
            }
        }
        return s;
    }

    /**
     * Returns the with of the piece.
     *
     * @return int with of the piece
     */
    public final int width() {
        return blocks[0].length;
    }

    /**
     * Returns the height of the piece.
     *
     * @return int height of the piece
     */
    public final int height() {
        return blocks.length;
    }

    /**
     * Indicates if the piece is empty at position(i,j).
     *
     * @param i
     *            row
     * @param j
     *            column
     * @return true if is not empty
     */
    public final boolean isHollowAt(final int i, final int j) {
        return blocks[i][j].toString().equals(String.valueOf(BoardPiece.EMPTY));
    }

    /**
     * Rotates piece to right.
     *
     * @return Piece rotated right
     */
    public final Piece rotateRight() { // transpose + reverse each row
        return new Piece(reverseRows(transpose(blocks)));
    }

    /**
     * Rotates piece to left.
     *
     * @return Piece rotated left
     */
    public final Piece rotateLeft() { // reverse each row + transpose
        return new Piece(transpose(reverseRows(blocks)));
    }

    /**
     * Transposes piece.
     *
     * @param recivedBlocks
     *            blocks to transpose
     * @return Block[][] representing the piece
     */
    private Block[][] transpose(final Block[][] recivedBlocks) {
        Block[][] transposedBlocks =
            new Block[recivedBlocks.length][recivedBlocks[0].length];
        for (int i = 0; i < recivedBlocks.length; i++) {
            for (int j = 0; j < recivedBlocks[i].length; j++) {
                transposedBlocks[j][i] = recivedBlocks[i][j];
            }
        }
        return transposedBlocks;
    }

    /**
     * Reverses the piece.
     *
     * @param recivedBlocks
     *            blocks to reverse
     * @return Block[][] Piece representation reversed
     */
    private Block[][] reverseRows(
        final Block[][] recivedBlocks) {
        Block[][] reversedBlocks = new Block
          [recivedBlocks.length][recivedBlocks[0].length];
        for (int i = 0; i < recivedBlocks.length; i++) {
            for (int j = 0; j < recivedBlocks[i].length; j++) {
                reversedBlocks[i][j] =
                    recivedBlocks[i][recivedBlocks[i]
                    .length - j - 1];
            }
        }
        return reversedBlocks;
    }

    /**
     *
     * @param recivedBlocks
     *            reviced blocks
     */
    private Piece(final Block[][] recivedBlocks) {
        this.blocks = recivedBlocks;
    }

}
