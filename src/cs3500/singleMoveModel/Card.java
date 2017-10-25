package cs3500.singleMoveModel;

import java.util.Objects;

/**
 * This class represents a card in a standard 52-card deck.
 */
public class Card {
  private final RankType rank;
  private final SuitType suit;

  /**
   * Constructs a card given its rank and suit types.
   *
   * @param rank   A RankType that represents the rank of this Card.
   * @param suit  A SuitType that represents the suit of this Card.
   */
  public Card(RankType rank, SuitType suit) {
    this.rank = rank;
    this.suit = suit;
  }

  /**
   * Gets this card's rank.
   * @return RankType
   */
  public RankType getRank() {
    return this.rank;
  }

  /**
   * Gets this card's suit.
   * @return SuitType
   */
  public SuitType getSuit() {
    return this.suit;
  }

  @Override
  public boolean equals(Object that) {
    if (!(that instanceof Card)) {
      return false;
    }
    Card c = (Card) that;
    return this.rank.equals(c.rank) && this.suit.equals(c.suit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.rank, this.suit);
  }

  /**
   * Formalizes this Card as a string. For example, the 10 of hearts would be "10♥".
   */
  public String toString() {
    return this.rank.getDisplay() + this.suit.getDisplay();
  }
}
