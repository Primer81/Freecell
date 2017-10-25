package cs3500.hw02;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Tests for CascadePile class.
 */
public class CascadePileTest {
  private Pile cascade;
  private Card c1;
  private Card c2;
  private Card c3;

  /**
   * Initializes test fields.
   */
  @Before
  public void init() {
    cascade = new CascadePile();
    this.c1 = new Card(RankType.KING, SuitType.HEART);
    this.c2 = new Card(RankType.QUEEN, SuitType.CLUB);
    this.c3 = new Card(RankType.KING, SuitType.CLUB);
  }

  // Sets Rule to expect no exception unless stated otherwise.
  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

  // Tests exception when invalid card is placed in cascade.
  @Test
  public void testPlace01() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Cannot add given card to cascade pile");
    cascade.push(this.c1);
    cascade.place(this.c3);
  }

  // Tests exception when invalid card (non-king) is place in empty cascade.
  @Test
  public void testPlace02() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Only kings can be added to empty cascade piles");
    cascade.place(this.c2);
  }

  // Tests that place method can place kings in empty cascades.
  @Test
  public void testPlace03() {
    cascade.place(this.c1);
    assertEquals(this.c1, cascade.pop());
  }

  // Tests that place method can place valid cards in non-empty cascades
  @Test
  public void testPlace04() {
    cascade.push(this.c1);
    cascade.place(this.c2);
    assertEquals(this.c2, cascade.pop());
  }
}