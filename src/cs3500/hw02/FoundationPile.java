package cs3500.hw02;

/**
 * Represents a foundation pile in hw02.
 */
public class FoundationPile extends APile {

  @Override
  public void place(Card c) {
    if (this.size() == 0 && c.getRank().getIndex() == 0) {
      this.addElement(c);
    }
    else if (this.size() != 0) {
      Card top = this.lastElement();
      if (top.getRank().getIndex() + 1 == c.getRank().getIndex()
          && top.getSuit().equals(c.getSuit())) {
        this.addElement(c);
      }
      else {
        throw new IllegalArgumentException("Cannot add given card to foundation pile");
      }
    }
    else {
      throw new IllegalArgumentException("Only aces can be added to empty foundation piles");
    }
  }
}
