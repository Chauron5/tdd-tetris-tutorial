// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Esko Luontola
 */
public class Step1_FallingBlocksTest {

    // Step 1: Starting small
    // - See the README for motivation
    // - Next step: RotatingPiecesOfBlocksTest

    private final Board board = new Board(3, 3);

    @Test
    public void a_new_board_is_empty() {
        assertEquals("" +
                     "...\n" +
                     "...\n" +
                     "...\n", board.toString());
    }

    @Test
    public void a_new_board_has_no_falling_blocks() {
        assertFalse(board.hasFalling());
    }

    // @Before for next block of tests
    private void dropBlock() {
        board.drop(new Block('X'));
    }

    @Test
    public void when_a_block_is_dropped_the_block_is_falling() {
        dropBlock();
        assertTrue(board.hasFalling());
    }

    @Test
    public void when_a_block_is_dropped_it_starts_from_the_top_middle() {
        dropBlock();
        assertEquals("" +
                     ".X.\n" +
                     "...\n" +
                     "...\n", board.toString());
    }

    @Test
    public void when_a_block_is_dropped_it_moves_down_one_row_per_tick() {
        dropBlock();
        board.tick();
        assertEquals("" +
                     "...\n" +
                     ".X.\n" +
                     "...\n", board.toString());
    }

    @Test(expected = IllegalStateException.class)
    public void when_a_block_is_dropped_at_most_one_block_may_be_falling_at_a_time() {
        dropBlock();
        board.drop(new Block('Y'));
        assertEquals("" +
                     ".X.\n" +
                     "...\n" +
                     "...\n", board.toString());
    }

    // @Before for next block of tests
    private void fallToLastRow() {
        board.drop(new Block('X'));
        board.tick();
        board.tick();
    }

    @Test
    public void when_a_block_reaches_the_bottom_it_is_still_falling_on_the_last_row() {
        fallToLastRow();
        assertEquals("" +
                     "...\n" +
                     "...\n" +
                     ".X.\n", board.toString());
        assertTrue("the player should still be able to move the block", board.hasFalling());
    }

    @Test
    public void when_a_block_reaches_the_bottom_it_stops_when_it_hits_the_bottom() {
        fallToLastRow();
        board.tick();
        assertEquals("" +
                     "...\n" +
                     "...\n" +
                     ".X.\n", board.toString());
        assertFalse("the block should stop moving", board.hasFalling());
    }

    // @Before for next block of tests
    private void landOnAnother() {
        board.drop(new Block('X'));
        board.tick();
        board.tick();
        board.tick();
        assertEquals("" +
                     "...\n" +
                     "...\n" +
                     ".X.\n", board.toString());
        assertFalse(board.hasFalling());

        board.drop(new Block('Y'));
        board.tick();
    }

    @Test
    public void when_a_block_lands_on_another_block_it_is_still_falling_right_above_the_other_block() {
        landOnAnother();
        assertEquals("" +
                     "...\n" +
                     ".Y.\n" +
                     ".X.\n", board.toString());
        assertTrue("the player should still be able to avoid landing on the other block", board.hasFalling());
    }

    @Test
    public void when_a_block_lands_on_another_block_it_stops_when_it_hits_the_other_block() {
        landOnAnother();
        board.tick();
        assertEquals("" +
                     "...\n" +
                     ".Y.\n" +
                     ".X.\n", board.toString());
        assertFalse("the block should stop moving when it lands on the other block", board.hasFalling());
    }

}
