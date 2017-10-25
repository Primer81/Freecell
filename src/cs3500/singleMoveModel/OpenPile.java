package cs3500.singleMoveModel;

/**
 * Represents an open pile in singleMoveModel.
 */
public class OpenPile extends APile {

  @Override
  public void place(Card c) {
    if (this.size() == 0) {
      this.addElement(c);
    }
    else {
      throw new IllegalArgumentException("Open piles can only hold 1 card");
    }
  }
}
