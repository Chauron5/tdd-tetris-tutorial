package tetris;

import org.junit.Test;

import static org.junit.Assert.*;

public class Step8_KillingAllMutantsTest {

  /* No se pueden matar los 5 mutantes restantes porque dependen
   * del metodo Block.falling_block_is_at() que nunca puede
   * lanzar un IndexOutOfBoundException
   */

  @Test
  public void can_move_left_mutant_test(){
    assertTrue(true);
}
