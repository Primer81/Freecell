package cs3500.singleMoveModel;

import java.util.Iterator;
import java.util.Stack;

/**
 * Abstraction class over different singleMoveModel piles.
 */
abstract class APile extends Stack<Card> implements Pile {
  /**
   * Gives a String representation of each element in this Pile.
   * @return String
   */
  public String toString() {
    StringBuilder state = new StringBuilder();
    Card c;
    Iterator<Card> it = this.iterator();
    if (it.hasNext()) {
      c = it.next();
      state.append(c.toString());
    }
    while (it.hasNext()) {
      c = it.next();
      state.append(", ").append(c.toString());
    }
    return state.toString();
  }
}