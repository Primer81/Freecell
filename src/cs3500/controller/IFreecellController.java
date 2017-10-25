package cs3500.controller;

import cs3500.singleMoveModel.FreecellOperations;

import java.util.List;

/**
 * Interface to represent a controller for freecell.
 */
public interface IFreecellController<T> {
  /**
   * Will start a new game of freecell provided that the controller is prepared to do so.
   * The given deck will be shuffled if shuffle is set to true, otherwise it will be used
   * as is.
   * @param deck the deck to be used in the game of freecell
   * @param model the model that will be used to play freecell
   * @param numCascades the number of cascade piles
   * @param numOpens the number of open piles
   * @param shuffle will the deck be shuffled or not
   *
   * @throws IllegalStateException if the controller has not been
   *     initialized to receive input and transmit output
   * @throws IllegalArgumentException if startGame is given a null deck or model
   */
  void playGame(List<T> deck, FreecellOperations<T> model, int numCascades,
                int numOpens, boolean shuffle);
}
