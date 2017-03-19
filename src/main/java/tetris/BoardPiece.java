package tetris;

public interface BoardPiece {

    final char EMPTY = '.';

    String toString();

    int width();

    int height();

    boolean is_hollow_at(int i, int j);

}
