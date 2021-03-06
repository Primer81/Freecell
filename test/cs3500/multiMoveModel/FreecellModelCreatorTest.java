package cs3500.multiMoveModel;

import cs3500.singleMoveModel.Card;
import cs3500.singleMoveModel.FreecellModel;
import cs3500.singleMoveModel.FreecellOperations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for FreecellModelCreator.
 */
public class FreecellModelCreatorTest {

  // Tests that FreecelModelCreator returns the correct model
  // when given the corresponding gameTypes.
  @Test
  public void create() {
    FreecellModelCreator create = new FreecellModelCreator();
    FreecellOperations<Card> model = create.create(FreecellModelCreator.GameType.SINGLEMOVE);
    assertEquals(true, model instanceof FreecellModel);
    model = create.create(FreecellModelCreator.GameType.MULTIMOVE);
    assertEquals(true, model instanceof FreecellModelMultiMove);
  }
}