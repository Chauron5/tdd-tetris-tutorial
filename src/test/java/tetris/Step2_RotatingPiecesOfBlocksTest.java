// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Esko Luontola
 */
public class Step2_RotatingPiecesOfBlocksTest {

    // Step 2: Stepping stone for rotation algorithms
    // - Remove the @Ignore annotation from this class
    // - See README for motivation
    // - Next step: RotatingTetrominoesTest

    private Piece piece;

    // @Before for next block of tests
    private void createPiece_3x3() {
        piece = new Piece("" +
                          ".X.\n" +
                          ".X.\n" +
                          "...\n");
    }

    @Test
    public void a_piece_of_3x3_blocks_consists_of_many_blocks() {
        createPiece_3x3();
        assertEquals("" +
                     ".X.\n" +
                     ".X.\n" +
                     "...\n", piece.toString());
    }

    @Test
    public void a_piece_of_3x3_blocks_can_be_rotated_right() {
        createPiece_3x3();
        piece = piece.rotateRight();
        assertEquals("" +
                     "...\n" +
                     ".XX\n" +
                     "...\n", piece.toString());
    }

    @Test
    public void a_piece_of_3x3_blocks_can_be_rotated_left() {
        createPiece_3x3();
        piece = piece.rotateLeft();
        assertEquals("" +
                     "...\n" +
                     "XX.\n" +
                     "...\n", piece.toString());
    }

    // @Before for next block of tests
    private void createPiece_5x5() {
        piece = new Piece("" +
                          "..XXX\n" +
                          "..XX.\n" +
                          "..X..\n" +
                          ".....\n" +
                          ".....\n");
    }

    @Test
    public void a_piece_of_5x5_blocks_consists_of_many_blocks() {
        createPiece_5x5();
        assertEquals("" +
                     "..XXX\n" +
                     "..XX.\n" +
                     "..X..\n" +
                     ".....\n" +
                     ".....\n", piece.toString());
    }

    @Test
    public void a_piece_of_5x5_blocks_can_be_rotated_right() {
        createPiece_5x5();
        piece = piece.rotateRight();
        assertEquals("" +
                     ".....\n" +
                     ".....\n" +
                     "..XXX\n" +
                     "...XX\n" +
                     "....X\n", piece.toString());
    }

    @Test
    public void a_piece_of_5x5_blocks_can_be_rotated_left() {
        createPiece_5x5();
        piece = piece.rotateLeft();
        assertEquals("" +
                     "X....\n" +
                     "XX...\n" +
                     "XXX..\n" +
                     ".....\n" +
                     ".....\n", piece.toString());
    }

}
