package enums;

public enum Player {
  PLAYER_ONE("|X|"), PLAYER_TWO("|O|");

  private String character;

  Player(String character) {
    this.character = character;
  }

  public String getCharacter() {
    return character;
  }
}
