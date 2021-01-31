package enums;

public enum Rules {
  WIN("WIN"), STILL_PLAYING("STILL_PLAYING"), INVALID_MOVEMENT("INVALID_MOVEMENT"),
  ACCEPTED_MOVIMENT("ACCEPTED_MOVIMENT");

  private String rule;

  Rules(String rule) {
    this.rule = rule;
  }

  public String getRule() {
    return rule;
  }
}
