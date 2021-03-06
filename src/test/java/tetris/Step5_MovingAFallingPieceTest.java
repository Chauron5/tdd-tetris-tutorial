// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import net.orfjackal.nestedjunit.NestedJUnit;
import org.junit.*;
import org.junit.runner.RunWith;

@RunWith(NestedJUnit.class)
public class Step5_MovingAFallingPieceTest extends Assert {

    // Step 5: It's your turn now
    // - Remove the @Ignore annotation from this class
    // - The test names have been provided, you just need to fill in the test body
    // - Next step: RotatingAFallingPieceTest

    private final Board board = new Board(6, 8);

    @Before
    public void dropPiece() {
        board.drop(Tetromino.T_SHAPE);
    }

    @Test
    public void a_falling_piece_can_be_moved_left(){
      board.moveLeft();
      assertEquals("" +
              "...T....\n" +
              "..TTT...\n" +
              "........\n" +
              "........\n" +
              "........\n" +
              "........\n", board.toString());
    }

    @Test
    public void a_falling_piece_can_be_moved_right() {
      board.moveRigth();
      assertEquals("" +
              ".....T..\n" +
              "....TTT.\n" +
              "........\n" +
              "........\n" +
              "........\n" +
              "........\n", board.toString());
    }

    @Test
    public void a_falling_piece_can_be_moved_down() {
      board.moveDown();
      assertEquals("" +
              "........\n" +
              "....T...\n" +
              "...TTT..\n" +
              "........\n" +
              "........\n" +
              "........\n" , board.toString());
    }

    @Test
    public void it_will_not_move_left_over_the_board() {
      board.moveLeft();
      board.moveLeft();
      board.moveLeft();
      assertEquals("" +
              ".T......\n" +
              "TTT.....\n" +
              "........\n" +
              "........\n" +
              "........\n" +
              "........\n", board.toString());
      board.moveLeft();
      assertEquals("" +
              ".T......\n" +
              "TTT.....\n" +
              "........\n" +
              "........\n" +
              "........\n" +
              "........\n", board.toString());
    }

  @Test
  public void it_will_not_move_right_over_the_board() {
    board.moveRigth();
    board.moveRigth();
    assertEquals("" +
            "......T.\n" +
            ".....TTT\n" +
            "........\n" +
            "........\n" +
            "........\n" +
            "........\n", board.toString());
    board.moveRigth();
    assertEquals("" +
            "......T.\n" +
            ".....TTT\n" +
            "........\n" +
            "........\n" +
            "........\n" +
            "........\n", board.toString());
  }

  @Test
  public void it_will_not_move_down_over_the_board() {
    board.moveDown();
    board.moveDown();
    board.moveDown();
    board.moveDown();
    assertEquals("" +
            "........\n" +
            "........\n" +
            "........\n" +
            "........\n" +
            "....T...\n" +
            "...TTT..\n" , board.toString());
    board.moveDown();
    assertEquals("" +
            "........\n" +
            "........\n" +
            "........\n" +
            "........\n" +
            "....T...\n" +
            "...TTT..\n" , board.toString());
  }

  @Test
  public void it_cannot_be_moved_left_if_another_piece_is_in_the_way() {
    board.moveLeft();
    board.tick();
    board.tick();
    board.tick();
    board.tick();
    board.tick();
    board.drop(Tetromino.O_SHAPE);
    board.tick();
    board.tick();
    board.tick();
    assertEquals("" +
            "........\n" +
            "........\n" +
            "........\n" +
            "....OO..\n" +
            "...TOO..\n" +
            "..TTT...\n" ,board.toString());
    board.moveLeft();
    assertEquals("" +
            "........\n" +
            "........\n" +
            "........\n" +
            "....OO..\n" +
            "...TOO..\n" +
            "..TTT...\n" ,board.toString());
  }

 @Test
 public void it_cannot_be_moved_right_if_another_piece_is_in_the_way(){
   board.moveRigth();
   board.moveRigth();
   board.tick();
   board.tick();
   board.tick();
   board.tick();
   board.tick();
   board.drop(Tetromino.O_SHAPE);
   board.tick();
   board.tick();
   board.tick();
   assertEquals("" +
           "........\n" +
           "........\n" +
           "........\n" +
           "....OO..\n" +
           "....OOT.\n" +
           ".....TTT\n" ,board.toString());
   board.moveRigth();
   assertEquals("" +
           "........\n" +
           "........\n" +
           "........\n" +
           "....OO..\n" +
           "....OOT.\n" +
           ".....TTT\n" ,board.toString());
 }

@Test
public void it_cannot_be_moved_down_if_another_piece_is_in_the_way() {
    board.tick();
    board.tick();
    board.tick();
    board.tick();
    board.tick();
    board.drop(Tetromino.O_SHAPE);
    board.moveDown();
    board.moveDown();
    assertEquals("" +
            "........\n" +
            "........\n" +
            "....OO..\n" +
            "....OO..\n" +
            "....T...\n" +
            "...TTT..\n" ,board.toString());
    board.moveDown();
    assertEquals("" +
            "........\n" +
            "........\n" +
            "....OO..\n" +
            "....OO..\n" +
            "....T...\n" +
            "...TTT..\n" ,board.toString());
 }
}
