package tetris;

public class Block {
  private char X;

  public Block(char c){
    this.X = c;
  }

  public char getValue(){
    return this.X;
  }
}
