package cs3500.hw02;

/**
 * Represents a cascade pile in hw02.
 */
public class CascadePile extends APile {

  @Override
  public void place(Card c) {
    if (this.size() == 0 && c.getRank().getIndex() == 12) {
      this.addElement(c);
    }
    else if (this.size() != 0) {
      Card top = this.lastElement();
      if (top.getRank().getIndex() - 1 == c.getRank().getIndex()
          && !(top.getSuit().getColor()).equals(c.getSuit().getColor())) {
        this.addElement(c);
      }
      else {
        throw new IllegalArgumentException("Cannot add given card to cascade pile");
      }
    }
    else {
      throw new IllegalArgumentException("Only kings can be added to empty cascade piles");
    }
  }
}