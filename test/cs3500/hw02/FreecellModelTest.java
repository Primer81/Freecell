package cs3500.hw02;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the FreecellModel class.
 */
public class FreecellModelTest {
  private FreecellModel freecell;
  private List<Card> deck;
  private List<Card> perfectDeck;
  private List<Card> otherDeck;

  /**
   * Initialize test fields.
   */
  @Before
  public void init() {

    this.freecell = new FreecellModel();
    this.deck = freecell.getDeck();
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
    this.otherDeck = new ArrayList<Card>(Arrays.asList(
        new Card(RankType.KING, SuitType.DIAMOND),    new Card(RankType.KING, SuitType.HEART),
        new Card(RankType.TEN, SuitType.CLUB),        new Card(RankType.TEN, SuitType.SPADE),
        new Card(RankType.QUEEN, SuitType.DIAMOND),   new Card(RankType.QUEEN, SuitType.HEART),
        new Card(RankType.NINE, SuitType.CLUB),       new Card(RankType.NINE, SuitType.SPADE),
        new Card(RankType.JACK, SuitType.DIAMOND),    new Card(RankType.JACK, SuitType.HEART),
        new Card(RankType.EIGHT, SuitType.CLUB),      new Card(RankType.EIGHT, SuitType.SPADE),
        new Card(RankType.TEN, SuitType.DIAMOND),     new Card(RankType.TEN, SuitType.HEART),
        new Card(RankType.SEVEN, SuitType.CLUB),      new Card(RankType.SEVEN, SuitType.SPADE),
        new Card(RankType.NINE, SuitType.DIAMOND),    new Card(RankType.NINE, SuitType.HEART),
        new Card(RankType.SIX, SuitType.CLUB),        new Card(RankType.SIX, SuitType.SPADE),
        new Card(RankType.EIGHT, SuitType.DIAMOND),   new Card(RankType.EIGHT, SuitType.HEART),
        new Card(RankType.FIVE, SuitType.CLUB),       new Card(RankType.FIVE, SuitType.SPADE),
        new Card(RankType.SEVEN, SuitType.DIAMOND),   new Card(RankType.SEVEN, SuitType.HEART),
        new Card(RankType.FOUR, SuitType.CLUB),       new Card(RankType.FOUR, SuitType.SPADE),
        new Card(RankType.SIX, SuitType.DIAMOND),     new Card(RankType.SIX, SuitType.HEART),
        new Card(RankType.THREE, SuitType.CLUB),      new Card(RankType.THREE, SuitType.SPADE),
        new Card(RankType.FIVE, SuitType.DIAMOND),    new Card(RankType.FIVE, SuitType.HEART),
        new Card(RankType.TWO, SuitType.CLUB),        new Card(RankType.TWO, SuitType.SPADE),
        new Card(RankType.FOUR, SuitType.DIAMOND),    new Card(RankType.FOUR, SuitType.HEART),
        new Card(RankType.ACE, SuitType.CLUB),        new Card(RankType.ACE, SuitType.SPADE),
        new Card(RankType.THREE, SuitType.DIAMOND),   new Card(RankType.THREE, SuitType.HEART),
        new Card(RankType.KING, SuitType.CLUB),       new Card(RankType.KING, SuitType.SPADE),
        new Card(RankType.TWO, SuitType.DIAMOND),     new Card(RankType.TWO, SuitType.HEART),
        new Card(RankType.QUEEN, SuitType.CLUB),      new Card(RankType.QUEEN, SuitType.SPADE),
        new Card(RankType.ACE, SuitType.DIAMOND),     new Card(RankType.ACE, SuitType.HEART),
        new Card(RankType.JACK, SuitType.CLUB),       new Card(RankType.JACK, SuitType.SPADE)));
  }

  // Sets Rule to expect no exception unless stated otherwise.
  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

  // Automatically empties a specified number of cards from the cascade pile
  // into a specified foundation pile. Helps greatly with testing.
  private void emptyCascade(FreecellModel freecell, int cascadePileNum,
                            int numCards, int foundationPileNum) {
    for (int i = 0; i < numCards; i++) {
      freecell.move(PileType.CASCADE, cascadePileNum, 12 - i,
          PileType.FOUNDATION, foundationPileNum);
    }
  }

  // Test that getDeck method returns a complete 52 card deck
  @Test
  public void testGetDeck() {
    List<String> cardsLeft = new ArrayList<String>(Arrays.asList(
        "A♥", "A♦", "A♣", "A♠", "2♥", "2♦", "2♣", "2♠", "3♥", "3♦", "3♣", "3♠", "4♥", "4♦", "4♣",
        "4♠", "5♥", "5♦", "5♣", "5♠", "6♥", "6♦", "6♣", "6♠", "7♥", "7♦", "7♣", "7♠", "8♥",
        "8♦", "8♣", "8♠", "9♥", "9♦", "9♣", "9♠", "10♥", "10♦", "10♣", "10♠", "J♥", "J♦", "J♣",
        "J♠", "Q♥", "Q♦", "Q♣", "Q♠", "K♥", "K♦", "K♣", "K♠"));
    for (Card c: deck) {
      assertEquals(true, cardsLeft.contains(c.toString()));
      cardsLeft.remove(c.toString());
    }
  }

  // Test invalid deck size exception in startGame method.
  @Test
  public void testStartGame01() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Deck given to startGame method has incorrect size");

    deck.remove(0);
    freecell.startGame(deck, 5, 5, true);
  }

  // Test invalid deck elements exception in startGame method.
  @Test
  public void testStartGame02() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Deck given to startGame method is invalid");

    deck.set(0, new Card(RankType.KING, SuitType.SPADE));
    freecell.startGame(deck, 5, 5, true);
  }

  // Test invalid numCascadePiles exception in startGame method.
  @Test
  public void testStartGame03() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("The number of cascade piles must be at least 4");

    freecell.startGame(deck, 3, 5, true);
  }

  // Test invalid numOpenPiles exception in startGame method.
  @Test
  public void testStartGame04() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("The number of open piles must be at least 1");

    freecell.startGame(deck, 4, 0, true);
  }

  // Test that startGame method shuffles deck when shuffle is true.
  @Test
  public void testStartGame05() {
    List<Card> unshuffled = freecell.getDeck();
    freecell.startGame(deck, 4, 1, true);
    boolean shuffled = false;
    for (Card c1 : deck) {
      for (Card c2 : unshuffled) {
        if (!c1.equals(c2)) {
          shuffled = true;
        }
      }
    }
    assertEquals(true, shuffled);
  }

  // Tests that startGame method distributes the deck as expected.
  @Test
  public void testStartGame06() {
    freecell.startGame(this.deck, 4, 1, false);
    assertEquals(
        "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "C1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "C2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "C3: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "C4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠",
        freecell.getGameState());
  }

  // Tests that startGame method can correctly start a game after one has already started
  @Test
  public void testStartGame07() {
    freecell.startGame(this.perfectDeck, 4, 1, false);
    this.emptyCascade(freecell, 0, 13, 0);
    assertEquals(
        "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "C1:\n" +
            "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n" +
            "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
            "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠",
        freecell.getGameState());
    freecell.startGame(this.perfectDeck, 4, 1, false);
    assertEquals(
        "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
            "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n" +
            "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
            "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠",
        freecell.getGameState());
  }

  // Tests that move method throws exception when trying to move multiple cards.
  @Test
  public void testMove01() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Only 1 card can be moved at a time");

    freecell.startGame(deck, 4, 1, false);
    freecell.move(PileType.CASCADE, 0, 11, PileType.OPEN, 0);
  }

  // Tests exception when making invalid move to cascade pile.
  @Test
  public void testMove02() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Cannot add given card to cascade pile");

    freecell.startGame(this.deck, 4, 1, false);
    freecell.move(PileType.CASCADE, 0, 12, PileType.CASCADE, 1);
  }

  // Tests exception when moving an invalid card (not king) to empty cascade pile.
  @Test
  public void testMove03() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Only kings can be added to empty cascade piles");

    freecell.startGame(this.perfectDeck, 4, 1, false);
    this.emptyCascade(freecell, 0, 13, 0);
    freecell.move(PileType.CASCADE, 1, 12, PileType.CASCADE, 0);
  }

  // Tests exception making an invalid move to cascade due to rank and suit.
  @Test
  public void testMove04() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Cannot add given card to cascade pile");

    freecell.startGame(this.perfectDeck, 4, 1, false);
    freecell.move(PileType.CASCADE, 2, 12, PileType.CASCADE, 3);
  }

  // Tests exception making an invalid move to cascade due to rank.
  @Test
  public void testMove05() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Cannot add given card to cascade pile");

    freecell.startGame(this.perfectDeck, 4, 1, false);
    freecell.move(PileType.CASCADE, 1, 12, PileType.CASCADE, 3);
  }

  // Tests exception making an invalid move to cascade due to suit.
  @Test
  public void testMove06() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Cannot add given card to cascade pile");

    freecell.startGame(this.perfectDeck, 4, 1, false);
    this.emptyCascade(freecell, 0, 1, 0);
    freecell.move(PileType.CASCADE, 1, 12, PileType.CASCADE, 0);
  }

  // Tests exception when making an invalid move to an empty foundation pile.
  @Test
  public void testMove11() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Only aces can be added to empty foundation piles");

    freecell.startGame(deck, 4, 1, false);
    freecell.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
  }

  // Tests exception making an invalid move to foundation pile due to rank and suit.
  @Test
  public void testMove12() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Cannot add given card to foundation pile");

    freecell.startGame(this.perfectDeck, 4, 1, false);
    this.emptyCascade(freecell, 0, 1, 0);
    freecell.move(PileType.CASCADE, 1, 12, PileType.FOUNDATION, 0);
  }

  // Tests exception making an invalid move to foundation pile due to suit.
  @Test
  public void testMove13() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Cannot add given card to foundation pile");

    freecell.startGame(this.perfectDeck, 4, 1, false);
    this.emptyCascade(freecell, 0, 1, 0);
    this.emptyCascade(freecell, 1, 1, 1);
    freecell.move(PileType.CASCADE, 0, 11, PileType.FOUNDATION, 1);
  }

  // Tests exception when making an invalid move to foundation pile due to rank.
  @Test
  public void testMove14() {
    freecell.startGame(this.otherDeck, 4, 1, false);
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Cannot add given card to foundation pile");

    this.emptyCascade(freecell, 0, 9, 0);
    freecell.move(PileType.CASCADE, 0, 3, PileType.CASCADE, 3);
    freecell.move(PileType.CASCADE, 0, 2, PileType.FOUNDATION, 0);
  }

  // Tests that move method can move card to non-empty cascade piles.
  @Test
  public void testMove19() {
    freecell.startGame(this.otherDeck, 4, 1, false);
    this.emptyCascade(freecell, 0, 9, 0);
    freecell.move(PileType.CASCADE, 0, 3, PileType.CASCADE, 3);
    assertEquals(
        "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "C1: K♦, Q♦, J♦\n" +
            "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n" +
            "C3: 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣, K♣, Q♣, J♣\n" +
            "C4: 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠, K♠, Q♠, J♠, 10♦",
        freecell.getGameState());
  }

  // Tests that move method can move card to non-empty foundation piles.
  @Test
  public void testMove21() {
    freecell.startGame(this.otherDeck, 4, 1, false);
    freecell.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    freecell.move(PileType.CASCADE, 0, 11, PileType.FOUNDATION, 0);
    assertEquals(
        "F1: A♦, 2♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦\n" +
            "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n" +
            "C3: 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣, K♣, Q♣, J♣\n" +
            "C4: 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠, K♠, Q♠, J♠",
        freecell.getGameState());
  }

  // Tests that move method can move card to empty open piles.
  @Test
  public void testMove18() {
    freecell.startGame(this.otherDeck, 4, 1, false);
    freecell.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    assertEquals(
        "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: A♦\n" +
            "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦\n" +
            "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n" +
            "C3: 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣, K♣, Q♣, J♣\n" +
            "C4: 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠, K♠, Q♠, J♠",
        freecell.getGameState());
  }

  // Tests that move method can move card to empty foundation piles.
  @Test
  public void testMove22() {
    freecell.startGame(this.otherDeck, 4, 1, false);
    freecell.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    assertEquals(
        "F1: A♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦\n" +
            "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n" +
            "C3: 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣, K♣, Q♣, J♣\n" +
            "C4: 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠, K♠, Q♠, J♠",
        freecell.getGameState());
  }

  // Tests exception when moving card from open to foundation pile due to rank.
  @Test
  public void testMove24() {
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("Cannot add given card to foundation pile");

    freecell.startGame(this.otherDeck, 4, 1, false);
    freecell.move(PileType.CASCADE, 2, 12, PileType.OPEN, 0);
    freecell.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    freecell.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
  }

  // Tests that move method can move card to non-empty foundation piles from open pile.
  @Test
  public void testMove25() {
    freecell.startGame(this.otherDeck, 4, 1, false);
    freecell.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    freecell.move(PileType.CASCADE, 0, 11, PileType.OPEN, 0);
    assertEquals(
        "F1: A♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 2♦\n" +
            "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦\n" +
            "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n" +
            "C3: 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣, K♣, Q♣, J♣\n" +
            "C4: 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠, K♠, Q♠, J♠",
        freecell.getGameState());
    freecell.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
    assertEquals(
        "F1: A♦, 2♦\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦\n" +
            "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n" +
            "C3: 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣, K♣, Q♣, J♣\n" +
            "C4: 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠, K♠, Q♠, J♠",
        freecell.getGameState());
  }

  // Tests that isGameOver method returns true when game has not started.
  @Test
  public void testIsGameOver01() {
    assertEquals(true, freecell.isGameOver());
  }

  // Tests that isGameOver method returns false is game has just started.
  @Test
  public void testIsGameOver02() {
    freecell.startGame(deck, 4, 1, false);
    assertEquals(false, freecell.isGameOver());
  }

  // Tests that isGameOver method returns true when game has been won.
  @Test
  public void testIsGameOver03() {

    freecell.startGame(this.perfectDeck, 4, 1, false);
    for (int i = 0; i < 4; i++) {
      this.emptyCascade(freecell, i, 13, i);
    }
    assertEquals(true, freecell.isGameOver());
  }

  // Tests that isGameOver method returns false when game has been played to a point.
  @Test
  public void testIsGameOver04() {

    freecell.startGame(this.perfectDeck, 4, 1, false);
    for (int i = 0; i < 3; i++) {
      this.emptyCascade(freecell, i, 13, i);
    }
    // leaves game 1 card away from victory
    this.emptyCascade(freecell, 3, 12, 3);
    assertEquals(false, freecell.isGameOver());
  }

  // Tests that getGameState method returns empty string when game is over
  @Test
  public void testGetGameState01() {
    assertEquals("", freecell.getGameState());
  }

  // Tests that getGameState method returns expected string after game start
  @Test
  public void testGetGameState02() {
    freecell.startGame(deck, 4, 1, false);
    assertEquals("" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "C1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "C2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "C3: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "C4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠",
        freecell.getGameState());
  }

  // Tests that getGameState method returns expected string after game start
  // and card is moved to open pile
  @Test
  public void testGetGameState03() {
    freecell.startGame(deck, 4, 1, false);
    freecell.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    assertEquals("" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: K♦\n" +
            "C1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n" +
            "C2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "C3: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "C4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠",
        freecell.getGameState());
  }

  // Tests that getGameState method returns expected string after game start
  // and cards are moved to foundation pile
  @Test
  public void testGetGameState04() {

    freecell.startGame(this.perfectDeck, 4, 1, false);
    for (int i = 0; i < 4; i++) {
      this.emptyCascade(this.freecell, i, 13, i);
    }
    assertEquals("" +
            "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F3: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "O1:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:",
        freecell.getGameState());
  }
}