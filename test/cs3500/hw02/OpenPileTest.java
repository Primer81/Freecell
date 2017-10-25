package cs3500.hw02;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Tests for OpenPile class.
 */
public class OpenPileTest {
  private Pile open;
  private Card c1;
  private Card c2;

  /**
   * Initializes test fields.
   */
  @Before
  public void init() {
    open = new OpenPile();
    this.c1 = new Card(RankType.KING, SuitType.HEART);
    this.c2 = new Card(RankType.QUEEN, SuitType.CLUB);
  }

  // Sets Rule to expect no exception unless stated otherwise.
  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

  // Tests exception when open pile already contains a card
  @Test
  public void place01() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Open piles can only hold 1 card");
    open.push(this.c1);
    open.place(this.c2);
  }

  // Tests that cards can be placed in open piles
  @Test
  public void place02() {
    open.place(this.c2);
    assertEquals(this.c2, open.pop());
  }
}