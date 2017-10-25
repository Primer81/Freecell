package cs3500.singleMoveModel;

/**
 * An enumeration of the different suits in a standard deck of playing cards.
 */
public enum SuitType {
  HEART("red", "♥"),
  DIAMOND("red", "♦"),
  SPADE("black", "♠"),
  CLUB("black", "♣");

  final String color;
  final String display;

  /**
   * Constructs a SuitType enum given its color and display.
   * @param color   the color of this suit written as a string
   * @param display string representation of suit
   */
  SuitType(String color, String display) {
    this.color = color;
    this.display = display;
  }

  /**
   * Gets the color of this suit.
   * @return String
   */
  public String getColor() {
    return this.color;
  }

  /**
   * Gets the display of this suit.
   * @return String
   */
  String getDisplay() {
    return this.display;
  }
}
