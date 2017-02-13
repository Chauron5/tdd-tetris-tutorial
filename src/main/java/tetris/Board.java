// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class Board {

    private final int rows;
    private final int columns;
    private boolean falling = false;
    private Block block = null;
    private int tickNum = 1;
    private int numBlocks = 0;
    private HashMap<Integer, Character> blocks;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        blocks = new HashMap();
    }

    public String toString() {
        String s = "";
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                s += ".";
            }
            s += "\n";
        }
        char[] ca = s.toCharArray();
        if (block != null){
          ca[this.tickNum] = block.getValue();
          if (this.blocks.isEmpty() == false && this.numBlocks > 1) {
            Set set = blocks.entrySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()) {
              Map.Entry mentry = (Map.Entry)iterator.next();
              ca[(int) mentry.getKey()] = (char) mentry.getValue();
            }
          }
        }
        return new String(ca);
    }

    public boolean hasFalling(){
      return this.falling;
    }

    public void drop (Block block) throws IllegalStateException {
      if (this.falling) {
        throw new IllegalStateException("already falling");
      }
      this.numBlocks++;
      this.tickNum = 1;
      this.block = block;
      this.falling = true;
    }

    public void tick(){
      if (this.tickNum == 9){
        this.falling = false;
        this.blocks.put(this.tickNum, block.getValue());
      } else {
        this.tickNum+=4;
      }
    }
}
