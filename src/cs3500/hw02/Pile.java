package cs3500.hw02;

import cs3500.hw02.Card;

/**
 * An interface for all piles in hw02.
 */
public interface Pile extends Iterable<Card> {
  /**
   * Appends a Card to the end of this Pile and returns the given card.
   */
  Card push(Card c);

  /**
   * Same as push, but throws an exception if this placement
   * would break the movement rules.
   */
  void place(Card c);

  /**
   * Removes the last Card from this pile and returns it. Throws an
   * exception if this Pile is empty.
   */
  Card pop();

  /**
   * Returns the size of this pile.
   */
  int size();
}
