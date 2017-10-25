package cs3500.multiMoveModel;

import cs3500.singleMoveModel.Card;
import cs3500.singleMoveModel.FreecellModel;
import cs3500.singleMoveModel.FreecellOperations;

/**
 * Factory class to create a freecell model.
 */
public class FreecellModelCreator {

  /**
   * Enumerated game types.
   */
  public enum GameType {
    SINGLEMOVE, MULTIMOVE;
  }

  /**
   * Creates a model of the specified gameType.
   * @param gameType type of model wanted
   * @return the model with the specified game type
   */
  public static FreecellOperations<Card> create(GameType gameType) {
    if (gameType.equals(GameType.SINGLEMOVE)) {
      return new FreecellModel();
    }
    else if (gameType.equals(GameType.MULTIMOVE)) {
      return new FreecellModelMultiMove();
    }
    else {
      throw new IllegalArgumentException("Given GameType not recognized.");
    }
  }
}
