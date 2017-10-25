package cs3500.hw02;

/**
 * An enumeration of the different ranks in a standard deck of playing cards.
 */
public enum RankType {
  ACE(0, "A"),
  TWO(1, "2"),
  THREE(2, "3"),
  FOUR(3, "4"),
  FIVE(4, "5"),
  SIX(5, "6"),
  SEVEN(6, "7"),
  EIGHT(7, "8"),
  NINE(8, "9"),
  TEN(9, "10"),
  JACK(10, "J"),
  QUEEN(11, "Q"),
  KING(12, "K");

  final int index;
  final String display;

  /**
   * Constructs a RankType enum given index and display.
   * @param index   value of rank
   * @param display string representation of rank
   */
  RankType(int index, String display) {
    this.index = index;
    this.display = display;
  }

  /**
   * Gets this rank's index.
   * @return int
   */
  public int getIndex() {
    return this.index;
  }

  /**
   * Gets this rank's display.
   * @return String
   */
  String getDisplay() {
    return this.display;
  }
}
