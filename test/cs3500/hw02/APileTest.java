package cs3500.hw02;

import org.junit.Test;

import javax.swing.text.DefaultEditorKit;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;

/**
 * Tests for APile abstract class.
 */
public class APileTest {

  // Tests the toString method.
  @Test
  public void testToString01() {
    Pile cascade = new CascadePile();
    cascade.push(new Card(RankType.KING, SuitType.SPADE));
    assertEquals("K♠", cascade.toString());
    cascade.push(new Card(RankType.KING, SuitType.SPADE));
    assertEquals("K♠, K♠", cascade.toString());
  }
}