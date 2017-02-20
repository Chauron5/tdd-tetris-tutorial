// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import net.orfjackal.nestedjunit.NestedJUnit;
import org.junit.*;
import org.junit.runner.RunWith;

/**
 * @author Esko Luontola
 */
@RunWith(NestedJUnit.class)
public class Step3_RotatingTetrominoesTest extends Assert {

    // Step 3: The actual rotation algorithms
    // - Remove the @Ignore annotation from this class
    // - See README for how "Tetromino" is different from "Piece"
    // - Next step: FallingPiecesTest


    private Tetromino shape;


    public class All_shape_instances {

        @Before
        public void createAnyShape() {
            shape = Tetromino.T_SHAPE;
        }

        @Test
        public void are_immutable() {
            String original = shape.toString();
            shape.rotateRight();
            assertEquals(original, shape.toString());
            shape.rotateLeft();
            assertEquals(original, shape.toString());
        }
    }



    public class The_T_shape {

        @Before
        public void createTShape() {
            shape = Tetromino.T_SHAPE;
        }

        @Test
        public void is_shaped_like_T() {
            assertEquals("" +
                    ".T.\n" +
                    "TTT\n" +
                    "...\n", shape.toString());
        }

       @Test
       public void can_be_rotated_right_3_times() {
           shape = shape.rotateRight();
           assertEquals("" +
                   ".T.\n" +
                   ".TT\n" +
                   ".T.\n", shape.toString());
           shape = shape.rotateRight();
           assertEquals("" +
                   "...\n" +
                   "TTT\n" +
                   ".T.\n", shape.toString());
           shape = shape.rotateRight();
           assertEquals("" +
                   ".T.\n" +
                   "TT.\n" +
                   ".T.\n", shape.toString());
       }

       @Test
       public void can_be_rotated_left_3_times() {
           shape = shape.rotateLeft();
           assertEquals("" +
                   ".T.\n" +
                   "TT.\n" +
                   ".T.\n", shape.toString());
           shape = shape.rotateLeft();
           assertEquals("" +
                   "...\n" +
                   "TTT\n" +
                   ".T.\n", shape.toString());
           shape = shape.rotateLeft();
           assertEquals("" +
                   ".T.\n" +
                   ".TT\n" +
                   ".T.\n", shape.toString());
       }

       @Test
       public void rotating_it_4_times_will_go_back_to_the_original_shape() {
           String originalShape = shape.toString();
           shape = shape.rotateRight().rotateRight().rotateRight().rotateRight();
           assertEquals(originalShape, shape.toString());
           shape = shape.rotateLeft().rotateLeft().rotateLeft().rotateLeft();
           assertEquals(originalShape, shape.toString());
       }
    }



    public class The_I_shape {

        @Before
        public void createIShape() {
            shape = Tetromino.I_SHAPE;
        }

      @Test
      public void the_I_shape_is_shaped_like_I() {
          createIShape();
          assertEquals("" +
                       "....\n" +
                       "IIII\n" +
                       "....\n" +
                       "....\n", shape.toString());
      }

      @Test
      public void the_I_shape_can_be_rotated_right_four_times() {
          createIShape();
          shape = shape.rotateRight();
          assertEquals("" +
                       "..I.\n" +
                       "..I.\n" +
                       "..I.\n" +
                       "..I.\n", shape.toString());
          shape = shape.rotateRight();
          assertEquals("" +
                       "....\n" +
                       "....\n" +
                       "IIII\n" +
                       "....\n", shape.toString());
          shape = shape.rotateRight();
          assertEquals("" +
                       ".I..\n" +
                       ".I..\n" +
                       ".I..\n" +
                       ".I..\n", shape.toString());
          shape = shape.rotateRight();
          assertEquals("" +
                       "....\n" +
                       "IIII\n" +
                       "....\n" +
                       "....\n", shape.toString());
      }

      @Test
      public void the_I_shape_can_be_rotated_left_four_times() {
          createIShape();
          shape = shape.rotateLeft();
          assertEquals("" +
                       ".I..\n" +
                       ".I..\n" +
                       ".I..\n" +
                       ".I..\n", shape.toString());
          shape = shape.rotateLeft();
          assertEquals("" +
                       "....\n" +
                       "....\n" +
                       "IIII\n" +
                       "....\n", shape.toString());
          shape = shape.rotateLeft();
          assertEquals("" +
                       "..I.\n" +
                       "..I.\n" +
                       "..I.\n" +
                       "..I.\n", shape.toString());
          shape = shape.rotateLeft();
          assertEquals("" +
                       "....\n" +
                       "IIII\n" +
                       "....\n" +
                       "....\n", shape.toString());
      }

      @Test
      public void the_I_shape_rotating_it_four_times_will_get_back_to_the_original_shape() {
          createIShape();
          String originalShape = shape.toString();
          shape = shape.rotateRight().rotateRight().rotateRight().rotateRight();
          assertEquals(originalShape, shape.toString());
          shape = shape.rotateLeft().rotateLeft().rotateLeft().rotateLeft();
          assertEquals(originalShape, shape.toString());
      }

      @Test
      public void the_I_shape_rotating_it_twice_right_or_left_is_equivalent() {
          createIShape();
          assertEquals(shape.rotateRight().rotateRight().toString(),
                       shape.rotateLeft().rotateLeft().toString());
      }
}

  public class The_O_shape {
      @Before //for next block of tests
      public void createOShape() {
          shape = Tetromino.O_SHAPE;
      }

      @Test
      public void the_O_shape_is_shaped_like_O() {
          createOShape();
          assertEquals("" +
                       ".OO\n" +
                       ".OO\n" +
                       "...\n", shape.toString());
      }

      @Test
      public void the_O_shape_can_be_rotated_right_four_times() {
          createOShape();
          shape = shape.rotateRight();
          assertEquals("" +
                       "...\n" +
                       ".OO\n" +
                       ".OO\n", shape.toString());
          shape = shape.rotateRight();
          assertEquals("" +
                       "...\n" +
                       "OO.\n" +
                       "OO.\n", shape.toString());
          shape = shape.rotateRight();
          assertEquals("" +
                       "OO.\n" +
                       "OO.\n" +
                       "...\n", shape.toString());
          shape = shape.rotateRight();
          assertEquals("" +
                       ".OO\n" +
                       ".OO\n" +
                       "...\n", shape.toString());
      }

      @Test
      public void the_O_shape_can_be_rotated_left_four_times() {
          createOShape();
          shape = shape.rotateLeft();
          assertEquals("" +
                       "OO.\n" +
                       "OO.\n" +
                       "...\n", shape.toString());
          shape = shape.rotateLeft();
          assertEquals("" +
                       "...\n" +
                       "OO.\n" +
                       "OO.\n", shape.toString());
          shape = shape.rotateLeft();
          assertEquals("" +
                       "...\n" +
                       ".OO\n" +
                       ".OO\n", shape.toString());
          shape = shape.rotateLeft();
          assertEquals("" +
                       ".OO\n" +
                       ".OO\n" +
                       "...\n", shape.toString());
      }

      @Test
      public void the_O_shape_rotating_it_four_times_will_get_back_to_the_original_shape() {
          createOShape();
          String originalShape = shape.toString();
          shape = shape.rotateRight().rotateRight().rotateRight().rotateRight();
          assertEquals(originalShape, shape.toString());
          shape = shape.rotateLeft().rotateLeft().rotateLeft().rotateLeft();
          assertEquals(originalShape, shape.toString());
      }

      @Test
      public void the_O_shape_rotating_it_twice_right_or_left_is_equivalent() {
          createOShape();
          assertEquals(shape.rotateRight().rotateRight().toString(),
                       shape.rotateLeft().rotateLeft().toString());
      }
    }
 }
