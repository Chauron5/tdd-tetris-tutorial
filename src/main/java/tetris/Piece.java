package tetris;

import java.util.StringTokenizer;

public class Piece {
  private String piece;
  private int tam;

  public Piece(String piece) {
    this.piece = piece;
  }

  public String toString () { return this.piece;}

  public Piece rotateRight() {
    if (this.piece.length() > 15) {
      return(new Piece("" +
                      ".....\n" +
                      ".....\n" +
                      "..XXX\n" +
                      "...XX\n" +
                      "....X\n"));
    }
    char[] previous = this.piece.toCharArray();
    char[] rotated = this.piece.toCharArray();

    rotated[0] = previous[8];
    rotated[1] = previous[4];
    rotated[2] = previous[0];
    rotated[4] = previous[9];
    rotated[5] = previous[5];
    rotated[6] = previous[1];
    rotated[8] = previous[10];
    rotated[9] = previous[6];
    rotated[10] = previous[2];

    this.piece = new String(rotated);
    return this;
  }

  public Piece rotateLeft() {
    if (this.piece.length() > 15) {
      return(new Piece("" +
                      "X....\n" +
                      "XX...\n" +
                      "XXX..\n" +
                      ".....\n" +
                      ".....\n"));
    }

    char[] previous = this.piece.toCharArray();
    char[] rotated = this.piece.toCharArray();

    rotated[8] = previous[0];
    rotated[4] = previous[1];
    rotated[0] = previous[2];
    rotated[9] = previous[4];
    rotated[5] = previous[5];
    rotated[1] = previous[6];
    rotated[10] = previous[8];
    rotated[6] = previous[9];
    rotated[2] = previous[10];

    this.piece = new String(rotated);
    return this;
  }

}
