package tetris;

import org.junit.Test;

import static org.junit.Assert.*;

public class Step7_ImprovingCoverageTest {

  @Test
  public void test_to_improve_coverage(){
      Block b = new Block('X');
      assertEquals(b.getValue(), 'X');

      assertFalse(b.isHollowAt(0,0));
      assertFalse(b.isHollowAt(1,0));
      assertFalse(b.isHollowAt(0,1));
      assertTrue(b.isHollowAt(1,1));

      Piece p = new Piece("");
      assertEquals(p.toString(), "");

      Board board = new Board(6, 8);
      Piece piece = new Piece("" +
              ".X.\n" +
              ".X.\n" +
              "...\n");
      board.tick();
      assertEquals("" +
              "........\n" +
              "........\n" +
              "........\n" +
              "........\n" +
              "........\n" +
              "........\n", board.toString());
      board.drop(piece);
      board.tick();
      board.tick();
      board.tick();
      board.tick();
      board.drop(piece);
      assertEquals("" +
              "....X...\n" +
              "....X...\n" +
              "........\n" +
              "........\n" +
              "........\n" +
              "........\n", board.toString());
  }
}
