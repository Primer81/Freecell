package cs3500.hw02;

import cs3500.hw02.Card;
import cs3500.hw02.RankType;
import cs3500.hw02.SuitType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Card class.
 */
public class CardTest {
  private Card c1;
  private Card c2;
  private Card c3;
  private Card c4;

  /**
   * Initializes test fields.
   */
  @Before
  public void init() {
    this.c1 = new Card(RankType.KING, SuitType.HEART);
    this.c2 = new Card(RankType.ACE, SuitType.HEART);
    this.c3 = new Card(RankType.KING, SuitType.CLUB);
    this.c4 = new Card(RankType.KING, SuitType.CLUB);
  }

  // Test the to String method.
  @Test
  public void testToString01() {
    assertEquals("K♥", c1.toString());
    assertEquals("A♥", c2.toString());
    assertEquals("K♣", c3.toString());
  }

  // Test the equals method.
  @Test
  public void testEquals02() {
    assertEquals(true, c3.equals(c4));
    assertEquals(false, c1.equals(c2));
    assertEquals(false, c2.equals(c3));
  }
}