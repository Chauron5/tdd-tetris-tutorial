package tetris;

/**
 * Most simple element of tetris.
 */
public class Block implements BoardPiece {
  /**
   * Char thar defines the block.
   */
  private final char value;

  /**
  * Constructor.
  * @param c char of the block.
  */
  public Block(final char c) {
    this.value = c;
  }

  /**
  * Returns the char of the block.
  * @return char of the blovk.
  */
  public final char getValue() {
    return this.value;
  }

  /**
  * returns the char of the block as String.
  * @return char of the block as String.
  */
  public final String toString() {
    String newString = "" + this.value;
    return newString;
  }

  /**
   * Get the with of the block.
   * @return int with of the block.
   */
  public final int width() {
        return 1;
    }

    /**
     * Get the height of the block.
     * @return int height of the block.
     */
    public final int height() {
        return 1;
    }

    /**
     * Returns if the block is at a defined position.
     * @param i int that represents the row.
     * @param j int that represents the column.
     * @return int height of the block.
     */
    public final boolean is_hollow_at(final int i, final int j) {
        return ((i != 0) && (j != 0));

    }

}
