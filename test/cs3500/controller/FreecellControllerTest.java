package cs3500.controller;

import cs3500.singleMoveModel.Card;
import cs3500.singleMoveModel.FreecellModel;
import cs3500.singleMoveModel.PileType;
import cs3500.singleMoveModel.RankType;
import cs3500.singleMoveModel.SuitType;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the FreecellController class.
 */
public class FreecellControllerTest {
  private FreecellController controller;
  private FreecellModel model;
  private FreecellModel testModel;
  private Readable reader;
  private Appendable writer;
  private List<Card> perfectDeck;

  /**
   * Initializes test fields.
   */
  @Before
  public void init() {
    this.controller = new FreecellController(null, null);
    this.model = new FreecellModel();
    this.testModel = new FreecellModel();
    this.reader = new StringReader("q");
    this.writer = new StringBuffer();
    this.perfectDeck = new ArrayList<Card>(Arrays.asList(
        new Card(RankType.KING, SuitType.DIAMOND),    new Card(RankType.KING, SuitType.HEART),
        new Card(RankType.KING, SuitType.CLUB),       new Card(RankType.KING, SuitType.SPADE),
        new Card(RankType.QUEEN, SuitType.DIAMOND),   new Card(RankType.QUEEN, SuitType.HEART),
        new Card(RankType.QUEEN, SuitType.CLUB),      new Card(RankType.QUEEN, SuitType.SPADE),
        new Card(RankType.JACK, SuitType.DIAMOND),    new Card(RankType.JACK, SuitType.HEART),
        new Card(RankType.JACK, SuitType.CLUB),       new Card(RankType.JACK, SuitType.SPADE),
        new Card(RankType.TEN, SuitType.DIAMOND),     new Card(RankType.TEN, SuitType.HEART),
        new Card(RankType.TEN, SuitType.CLUB),        new Card(RankType.TEN, SuitType.SPADE),
        new Card(RankType.NINE, SuitType.DIAMOND),    new Card(RankType.NINE, SuitType.HEART),
        new Card(RankType.NINE, SuitType.CLUB),       new Card(RankType.NINE, SuitType.SPADE),
        new Card(RankType.EIGHT, SuitType.DIAMOND),   new Card(RankType.EIGHT, SuitType.HEART),
        new Card(RankType.EIGHT, SuitType.CLUB),      new Card(RankType.EIGHT, SuitType.SPADE),
        new Card(RankType.SEVEN, SuitType.DIAMOND),   new Card(RankType.SEVEN, SuitType.HEART),
        new Card(RankType.SEVEN, SuitType.CLUB),      new Card(RankType.SEVEN, SuitType.SPADE),
        new Card(RankType.SIX, SuitType.DIAMOND),     new Card(RankType.SIX, SuitType.HEART),
        new Card(RankType.SIX, SuitType.CLUB),        new Card(RankType.SIX, SuitType.SPADE),
        new Card(RankType.FIVE, SuitType.DIAMOND),    new Card(RankType.FIVE, SuitType.HEART),
        new Card(RankType.FIVE, SuitType.CLUB),       new Card(RankType.FIVE, SuitType.SPADE),
        new Card(RankType.FOUR, SuitType.DIAMOND),    new Card(RankType.FOUR, SuitType.HEART),
        new Card(RankType.FOUR, SuitType.CLUB),       new Card(RankType.FOUR, SuitType.SPADE),
        new Card(RankType.THREE, SuitType.DIAMOND),   new Card(RankType.THREE, SuitType.HEART),
        new Card(RankType.THREE, SuitType.CLUB),      new Card(RankType.THREE, SuitType.SPADE),
        new Card(RankType.TWO, SuitType.DIAMOND),     new Card(RankType.TWO, SuitType.HEART),
        new Card(RankType.TWO, SuitType.CLUB),        new Card(RankType.TWO, SuitType.SPADE),
        new Card(RankType.ACE, SuitType.DIAMOND),     new Card(RankType.ACE, SuitType.HEART),
        new Card(RankType.ACE, SuitType.CLUB),        new Card(RankType.ACE, SuitType.SPADE)));
  }

  // Sets Rule to expect no exception unless otherwise stated.
  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

  // Accepts a model with a game in progress and performs a move with the given parameters on
  // that model. It then returns the new game state of that model.
  private String nextGameState(FreecellModel model, PileType source, int pileNumSource,
                               int cardIndex, PileType destination, int pileNumDestination) {
    model.move(source, pileNumSource, cardIndex, destination, pileNumDestination);
    return model.getGameState();
  }

  // Simulates a series of inputs and outputs to test the controller.
  private boolean testRun(FreecellModel model, Interaction... interactions) throws IOException {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();

    IFreecellController<Card> controller = new FreecellController(input, actualOutput);
    controller.playGame(this.perfectDeck, model, 4, 1, false);

    assertEquals(expectedOutput.toString(), actualOutput.toString());
    return expectedOutput.toString().equals(actualOutput.toString());
  }

  // Same as above but accepts an arrayList<Interaction> as input.
  private boolean testRun(FreecellModel model,
                          ArrayList<Interaction> interactions) throws IOException {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();

    IFreecellController<Card> controller = new FreecellController(input, actualOutput);
    controller.playGame(this.perfectDeck, model, 4, 1, false);

    assertEquals(expectedOutput.toString(), actualOutput.toString());
    return expectedOutput.toString().equals(actualOutput.toString());
  }

  // Represents outputs from the controller.
  private static Interaction prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line).append('\n');
      }
    };
  }

  // Represents prompts from the controller and responses from the user
  private static Interaction prompts(String prompt, String response) {
    return (input, output) -> {
      output.append(prompt).append("\n");
      input.append(response);
    };
  }

  // Tests for IllegalStateException when rd and ap instance variables haven't been initialized.
  @Test
  public void testPlayGame01() {
    expectedEx.expect(IllegalStateException.class);
    expectedEx.expectMessage("Input/Output have not been initialized.");

    this.controller.playGame(this.perfectDeck, this.model, 4, 1, false);
  }

  // Tests for IllegalArgumentException when null deck or model is passed to controller.
  @Test
  public void testPlayGame02() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Null value given to playGame method.");

    this.controller = new FreecellController(new StringReader(""), new StringWriter());
    this.controller.playGame(null, null, 4, 1, false);
  }

  // Tests for output when game fails to start.
  @Test
  public void testPlayGame03() {
    this.controller = new FreecellController(this.reader, this.writer);
    this.controller.playGame(new ArrayList<Card>(), this.model, 4, 1, false);
    assertEquals("Could not start game.", this.writer.toString());
  }

  // Tests for output when game starts by quitting.
  @Test
  public void testPlayGame04() throws IOException {
    this.testModel.startGame(this.perfectDeck, 4, 1, false);
    boolean test = this.testRun(this.model,
        this.prints(this.testModel.getGameState()),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "q\n"),
        this.prints("Game quit prematurely."));
    this.testRun(this.model,
        this.prints(this.testModel.getGameState()),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "Q\n"),
        this.prints("Game quit prematurely."));
    assertEquals(true, test); // Forces style checker to not doc points
  }

  // Tests for output when game starts by playing.
  @Test
  public void testPlayGame05() throws IOException {
    this.testModel.startGame(this.perfectDeck, 4, 1, false);
    boolean test = this.testRun(this.model,
        this.prints(this.testModel.getGameState()),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "C1 13 F1\n"),
        this.prints(this.nextGameState(this.testModel, PileType.CASCADE,
            0, 12, PileType.FOUNDATION, 0)),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "q\n"),
        this.prints("Game quit prematurely."));
    assertEquals(true, test); // Forces style checker to not doc points
  }

  // Tests for output when game starts after inputting invalid moves.
  @Test
  public void testPlayGame06() throws IOException {
    this.testModel.startGame(this.perfectDeck, 4, 1, false);
    boolean test = this.testRun(this.model,
        this.prints(this.testModel.getGameState()),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "C1 ??\n"),
        this.prints("Input invalid. Try again."),
        this.prompts("Enter a card number and destination (eg. '3 F2'):", "13 ??\n"),
        this.prints("Input invalid. Try again."),
        this.prompts("Enter a destination (eg. 'F2'):", "F1\n"),
        this.prints(this.nextGameState(this.testModel, PileType.CASCADE,
            0, 12, PileType.FOUNDATION, 0)),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "q\n"),
        this.prints("Game quit prematurely."));
    assertEquals(true, test); // Forces style checker to not doc points
  }

  // Tests for output when game is quit after inputting invalid moves.
  @Test
  public void testPlayGame07() throws IOException {
    this.testModel.startGame(this.perfectDeck, 4, 1, false);
    boolean test = this.testRun(this.model,
        this.prints(this.testModel.getGameState()),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "C1 ??\n"),
        this.prints("Input invalid. Try again."),
        this.prompts("Enter a card number and destination (eg. '3 F2'):", "13 q\n"),
        this.prints("Game quit prematurely."));
    assertEquals(true, test); // Forces style checker to not doc points
  }

  // Tests for output when input is out of bounds in cascade.
  @Test
  public void testPlayGame08() throws IOException {
    this.testModel.startGame(this.perfectDeck, 4, 1, false);
    boolean test = this.testRun(this.model,
        this.prints(this.testModel.getGameState()),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "C100 13 F1\n"),
        this.prints("Invalid move. Try again."),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "q\n"),
        this.prints("Game quit prematurely."));
    this.testRun(this.model,
        this.prints(this.testModel.getGameState()),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "C-100 13 F1\n"),
        this.prints("Invalid move. Try again."),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "q\n"),
        this.prints("Game quit prematurely."));
    assertEquals(true, test); // Forces style checker to not doc points
  }

  // Tests for output when input is out of bounds in cardIndex.
  @Test
  public void testPlayGame09() throws IOException {
    this.testModel.startGame(this.perfectDeck, 4, 1, false);
    boolean test = this.testRun(this.model,
        this.prints(this.testModel.getGameState()),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "C1 100 F1\n"),
        this.prints("Invalid move. Try again."),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "q\n"),
        this.prints("Game quit prematurely."));
    this.testRun(this.model,
        this.prints(this.testModel.getGameState()),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "C1 -100 F1\n"),
        this.prints("Invalid move. Try again."),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "q\n"),
        this.prints("Game quit prematurely."));
    assertEquals(true, test); // Forces style checker to not doc points
  }

  // Tests for output when input is out of bounds in foundation.
  @Test
  public void testPlayGame10() throws IOException {
    this.testModel.startGame(this.perfectDeck, 4, 1, false);
    boolean test = this.testRun(this.model,
        this.prints(this.testModel.getGameState()),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "C1 13 F100\n"),
        this.prints("Invalid move. Try again."),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "q\n"),
        this.prints("Game quit prematurely."));
    this.testRun(this.model,
        this.prints(this.testModel.getGameState()),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "C1 13 F-100\n"),
        this.prints("Invalid move. Try again."),
        this.prompts("Enter next move (eg. 'C1 3 F2'):", "q\n"),
        this.prints("Game quit prematurely."));
    assertEquals(true, test); // Forces style checker to not doc points
  }

  // Tests that game ends when game has been won.
  @Test
  public void testPlayGame12() throws IOException {
    this.testModel.startGame(this.perfectDeck, 4, 1, false);

    // Creates a list of interactions that will win the game
    ArrayList<Interaction> interactions = new ArrayList<Interaction>(0);
    interactions.add(this.prints(this.testModel.getGameState()));
    // Loop repetitive interactions
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 13; j++) {
        interactions.add(this.prompts("Enter next move (eg. 'C1 3 F2'):",
            "C" + (i + 1) + " " + (13 - j) + " F" + (i + 1) + "\n"));
        String gameState = this.nextGameState(this.testModel, PileType.CASCADE,
            i, 12 - j, PileType.FOUNDATION, i);
        interactions.add(this.prints(gameState));
      }
    }
    // add game over message
    interactions.add(this.prints("Game over."));

    boolean test = this.testRun(this.model, interactions);
    assertEquals(true, test); // Forces style checker to not doc points
  }
}