package tetris;

public class Block implements BoardPiece{
  private final char X;

  public Block(char c){
    this.X = c;
  }

  public char getValue(){
    return this.X;
  }

  public String toString(){
    return new String(""+this.X);
  }

  public int width() {
        return 1;
    }

    public int height() {
        return 1;
    }

    public boolean is_hollow_at(int i, int j) {
        return ((i!=0) && (j!=0));

    }

}
