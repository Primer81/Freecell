package cs3500.hw03;

import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * A controller for a game of freecell.
 */
public class FreecellController<T> implements IFreecellController<T> {

  private Readable rd;
  private Appendable ap;

  /**
   * Constructor for FreecellController.
   * @param rd used to read input
   * @param ap used to send output
   */
  public FreecellController(Readable rd, Appendable ap) {
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public void playGame(List<T> deck, FreecellOperations<T> model,
                       int numCascades, int numOpens, boolean shuffle) {
    // Checks to see if input or output are null
    if (this.rd == null || this.ap == null) {
      throw new IllegalStateException("Input/Output have not been initialized.");
    }
    // Checks to see if null values passed as args
    if (deck == null || model == null) {
      throw new IllegalArgumentException("Null value given to playGame method.");
    }
    // Try to start game
    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    }
    catch (IllegalArgumentException badArg) {
      this.output("Could not start game.");
      return;
    }
    // Output game state
    this.output(model.getGameState() + "\n");
    // Wait for user input
    this.waitForInput(model);
  }

  /**
   * Transmit the given string to ap instance variable. Used by playGame method.
   * @param out string to be transmitted
   */
  private void output(String out) {
    try {
      for (char c: out.toCharArray()) {
        ap.append(c);
      }
    }
    catch (IOException badOut) {
      return;
    }
  }

  /**
   * Waits for input from the user until the game has ended. Used by playGame method.
   * @param model model being used by controller
   */
  private void waitForInput(FreecellOperations<T> model) {
    // Objects to store movement data
    PileType source = null;
    Integer pileNumSource = null;
    Integer cardIndex = null;
    PileType destination = null;
    Integer pileNumDestination = null;
    Scanner sc = new Scanner(this.rd);
    // Wait for input while game is not over
    while (!model.isGameOver()) {
      try {
        // Notify user of required input
        this.notifyUser(source, pileNumSource, cardIndex, destination, pileNumDestination);
        // Checks to see if game should quit
        if (this.hasQuit(sc)) {
          return;
        }
        // Retrieve source pile from input
        if (source == null || pileNumSource == null) {
          String token = sc.next();
          source = this.parsePileCommand(token.charAt(0));
          pileNumSource = Integer.parseInt(token.substring(1)) - 1;
        }
        // Checks to see if game should quit
        if (this.hasQuit(sc)) {
          return;
        }
        // Retrieve cardIndex from input
        if (cardIndex == null) {
          String token = sc.next();
          cardIndex = Integer.parseInt(token.substring(0)) - 1;
        }
        // Checks to see if game should quit
        if (this.hasQuit(sc)) {
          return;
        }
        // Retrieve destination pile from input
        if (destination == null || pileNumDestination == null) {
          String token = sc.next();
          destination = this.parsePileCommand(token.charAt(0));
          pileNumDestination = Integer.parseInt(token.substring(1)) - 1;
        }
      }
      catch (IllegalArgumentException bagArg) {
        this.output("Input invalid. Try again.\n");
        continue; // Skips the following code if input was bad (does not attempt to move)
      }
      // Move based on given parameters and output game state
      this.move(model, source, pileNumSource, cardIndex, destination, pileNumDestination);
      // Reset movement data objects
      source = null;
      pileNumSource = null;
      cardIndex = null;
      destination = null;
      pileNumDestination = null;
    }
    // End game
    this.output("Game over.\n");
  }

  /**
   * Scans input to see if game has been quit returns true if so. Used by waitForInput method.
   * @param sc scanner containing input
   * @return whether or not the game has ended
   */
  private boolean hasQuit(Scanner sc) {
    if (sc.hasNext("q") || sc.hasNext("Q")) {
      this.output("Game quit prematurely.\n");
      return true;
    }
    return false;
  }

  /**
   * Notifies the user of the required input. Used by waitForInput method.
   * @param source source pileType
   * @param pileNumSource source pile number
   * @param cardIndex index of card being used
   * @param destination destination pileType
   * @param pileNumDestination destination pile number
   */
  private void notifyUser(PileType source, Integer pileNumSource, Integer cardIndex,
                          PileType destination, Integer pileNumDestination) {
    if (source == null || pileNumSource == null) {
      this.output("Enter next move (eg. 'C1 3 F2'):\n");
    }
    else if (cardIndex == null) {
      this.output("Enter a card number and destination (eg. '3 F2'):\n");
    }
    else if (destination == null || pileNumDestination == null) {
      this.output("Enter a destination (eg. 'F2'):\n");
    }
  }

  /**
   * Parses a pile command and returns the pileType.
   * Used by waitForInput method
   * @param pile character being parsed
   */
  private PileType parsePileCommand(char pile) {
    switch (pile) {
      case 'F':
        return PileType.FOUNDATION;
      case 'C':
        return PileType.CASCADE;
      case 'O':
        return PileType.OPEN;
      default:
        throw new IllegalArgumentException("PileType does not exist");
    }
  }

  /**
   * Moves a card within the given model based on the given parameters.
   * Used by the waitForInput method.
   * @param model the FreecellModel being used
   * @param source source pileType
   * @param pileNumSource source pile number
   * @param cardIndex index of card being used
   * @param destination destination pileType
   * @param pileNumDestination destination pile number
   */
  private void move(FreecellOperations<T> model, PileType source, Integer pileNumSource,
                    Integer cardIndex, PileType destination, Integer pileNumDestination) {
    try {
      model.move(source, pileNumSource, cardIndex, destination, pileNumDestination);
      this.output(model.getGameState() + "\n");
    }
    catch (IllegalArgumentException | IndexOutOfBoundsException badMove) {
      this.output("Invalid move. Try again.\n");
    }
  }
}
