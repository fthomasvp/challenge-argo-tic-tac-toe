package models;

import enums.Player;

public class Movement {
  private int line;
  private int column;
  public Player player;

  public Movement(int line, int column, Player player) {
    this.setLine(line);
    this.setColumn(column);
    this.player = player;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public Movement() {

  }
}
