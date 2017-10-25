package cs3500.hw02;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Tests for FoundationPile class.
 */
public class FoundationPileTest {
  private Pile foundation;
  private Card c1;
  private Card c2;
  private Card c3;

  /**
   * Initializes test fields.
   */
  @Before
  public void init() {
    foundation = new FoundationPile();
    this.c1 = new Card(RankType.ACE, SuitType.HEART);
    this.c2 = new Card(RankType.TWO, SuitType.HEART);
    this.c3 = new Card(RankType.TWO, SuitType.CLUB);
  }

  // Sets Rule to expect no exception unless stated otherwise.
  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

  // Tests exception when invalid card is placed in foundation.
  @Test
  public void testPlace01() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Cannot add given card to foundation pile");
    foundation.push(this.c1);
    foundation.place(this.c3);
  }

  // Tests exception when invalid card (non-king) is place in empty foundation.
  @Test
  public void testPlace02() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Only aces can be added to empty foundation piles");
    foundation.place(this.c2);
  }

  // Tests that place method can place kings in empty foundations
  @Test
  public void testPlace03() {
    foundation.place(this.c1);
    assertEquals(this.c1, foundation.pop());
  }

  // Tests that place method can place valid cards in non-empty foundations
  @Test
  public void testPlace04() {
    foundation.push(this.c1);
    foundation.place(this.c2);
    assertEquals(this.c2, foundation.pop());
  }

}