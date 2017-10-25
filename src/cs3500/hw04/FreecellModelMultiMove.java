package cs3500.hw04;

import cs3500.hw02.Card;
import cs3500.hw02.CascadePile;
import cs3500.hw02.FoundationPile;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.OpenPile;
import cs3500.hw02.Pile;
import cs3500.hw02.PileType;
import cs3500.hw02.RankType;
import cs3500.hw02.SuitType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A freecell model that allows the player to move multiple cards assuming
 * a valid build, valid move, and enough intermediary slots.
 */
public class FreecellModelMultiMove implements FreecellOperations<Card> {

  protected ArrayList<Pile> foundationPiles;
  protected ArrayList<Pile> cascadePiles;
  protected ArrayList<Pile> openPiles;

  /**
   * Constructs a new FreecellModel.
   */
  public FreecellModelMultiMove() {
    this.foundationPiles = new ArrayList<Pile>();
    this.cascadePiles = new ArrayList<Pile>();
    this.openPiles = new ArrayList<Pile>();
  }

  @Override
  public List<Card> getDeck() {
    return new ArrayList<>(Arrays.asList(
        new Card(RankType.ACE, SuitType.DIAMOND),   new Card(RankType.ACE, SuitType.HEART),
        new Card(RankType.ACE, SuitType.CLUB),      new Card(RankType.ACE, SuitType.SPADE),
        new Card(RankType.TWO, SuitType.DIAMOND),   new Card(RankType.TWO, SuitType.HEART),
        new Card(RankType.TWO, SuitType.CLUB),      new Card(RankType.TWO, SuitType.SPADE),
        new Card(RankType.THREE, SuitType.DIAMOND), new Card(RankType.THREE, SuitType.HEART),
        new Card(RankType.THREE, SuitType.CLUB),    new Card(RankType.THREE, SuitType.SPADE),
        new Card(RankType.FOUR, SuitType.DIAMOND),  new Card(RankType.FOUR, SuitType.HEART),
        new Card(RankType.FOUR, SuitType.CLUB),     new Card(RankType.FOUR, SuitType.SPADE),
        new Card(RankType.FIVE, SuitType.DIAMOND),  new Card(RankType.FIVE, SuitType.HEART),
        new Card(RankType.FIVE, SuitType.CLUB),     new Card(RankType.FIVE, SuitType.SPADE),
        new Card(RankType.SIX, SuitType.DIAMOND),   new Card(RankType.SIX, SuitType.HEART),
        new Card(RankType.SIX, SuitType.CLUB),      new Card(RankType.SIX, SuitType.SPADE),
        new Card(RankType.SEVEN, SuitType.DIAMOND), new Card(RankType.SEVEN, SuitType.HEART),
        new Card(RankType.SEVEN, SuitType.CLUB),    new Card(RankType.SEVEN, SuitType.SPADE),
        new Card(RankType.EIGHT, SuitType.DIAMOND), new Card(RankType.EIGHT, SuitType.HEART),
        new Card(RankType.EIGHT, SuitType.CLUB),    new Card(RankType.EIGHT, SuitType.SPADE),
        new Card(RankType.NINE, SuitType.DIAMOND),  new Card(RankType.NINE, SuitType.HEART),
        new Card(RankType.NINE, SuitType.CLUB),     new Card(RankType.NINE, SuitType.SPADE),
        new Card(RankType.TEN, SuitType.DIAMOND),   new Card(RankType.TEN, SuitType.HEART),
        new Card(RankType.TEN, SuitType.CLUB),      new Card(RankType.TEN, SuitType.SPADE),
        new Card(RankType.JACK, SuitType.DIAMOND),  new Card(RankType.JACK, SuitType.HEART),
        new Card(RankType.JACK, SuitType.CLUB),     new Card(RankType.JACK, SuitType.SPADE),
        new Card(RankType.QUEEN, SuitType.DIAMOND), new Card(RankType.QUEEN, SuitType.HEART),
        new Card(RankType.QUEEN, SuitType.CLUB),    new Card(RankType.QUEEN, SuitType.SPADE),
        new Card(RankType.KING, SuitType.DIAMOND),  new Card(RankType.KING, SuitType.HEART),
        new Card(RankType.KING, SuitType.CLUB),     new Card(RankType.KING, SuitType.SPADE)));
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean
      shuffle)
      throws
      IllegalArgumentException {
    // validates parameters
    this.validateDeck(deck);
    if (numCascadePiles < 4) {
      throw new IllegalArgumentException("The number of cascade piles must be at least 4");
    }
    if (numOpenPiles < 1) {
      throw new IllegalArgumentException("The number of open piles must be at least 1");
    }
    // initializes fields
    this.initFields(numCascadePiles, numOpenPiles);
    // shuffles the given deck
    if (shuffle) {
      this.shuffle(deck);
    }
    // deal cards to cascade piles
    this.deal(deck, numCascadePiles);
  }

  /**
   * Takes in a list of cards and throws an exception if this list is not a valid deck.
   * Used by startGame method.
   * @param deck an arrayList of Card
   */
  protected void validateDeck(List<Card> deck) {
    if (deck.size() != 52) {
      throw new IllegalArgumentException("Deck given to startGame method has incorrect size");
    }
    List<String> cardsLeft = new ArrayList<>(Arrays.asList(
        "A♥", "A♦", "A♣", "A♠", "2♥", "2♦", "2♣", "2♠", "3♥", "3♦", "3♣", "3♠",
        "4♥", "4♦", "4♣", "4♠", "5♥", "5♦", "5♣", "5♠", "6♥", "6♦", "6♣", "6♠", "7♥",
        "7♦", "7♣", "7♠", "8♥", "8♦", "8♣", "8♠", "9♥", "9♦", "9♣", "9♠", "10♥", "10♦",
        "10♣", "10♠", "J♥", "J♦", "J♣", "J♠", "Q♥", "Q♦", "Q♣", "Q♠", "K♥", "K♦", "K♣", "K♠"));
    for (Card c: deck) {
      if (!cardsLeft.contains(c.toString())) {
        throw new IllegalArgumentException("Deck given to startGame method is invalid");
      }
      cardsLeft.remove(c.toString());
    }
  }

  /**
   * Initializes fields in FreecellModel. Used by startGame method.
   * @param numCascadePiles number of cascade piles
   * @param numOpenPiles number of open piles
   */
  protected void initFields(int numCascadePiles, int numOpenPiles) {
    this.foundationPiles = new ArrayList<Pile>();
    for (int i = 0; i < 4; i++) {
      this.foundationPiles.add(new FoundationPile());
    }
    this.cascadePiles = new ArrayList<Pile>();
    for (int i = 0; i < numCascadePiles; i++) {
      this.cascadePiles.add(new CascadePile());
    }
    this.openPiles = new ArrayList<Pile>();
    for (int i = 0; i < numOpenPiles; i++) {
      this.openPiles.add(new OpenPile());
    }
  }

  /**
   * Shuffles the given valid deck. Used by startGame method
   * @param deck valid deck
   */
  protected void shuffle(List<Card> deck) {
    int  newIndex;
    Card temp;
    Random r = new Random();
    for (int i = 0; i < 52; i++) {
      newIndex = r.nextInt(52 - i) + i;
      temp = deck.get(i);
      deck.set(i, deck.get(newIndex));
      deck.set(newIndex, temp);
    }
  }

  /**
   * Deals the given valid deck to cascade piles. Used by startGame method.
   * @param deck valid deck
   */
  protected void deal(List<Card> deck, int numCascadePiles) {
    int cascadeIndex = 0;
    for (Card c: deck) {
      this.cascadePiles.get(cascadeIndex % numCascadePiles).push(c);
      cascadeIndex++;
    }
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber) throws IllegalArgumentException {
    Pile pile;
    ArrayList<Card> cards = new ArrayList<Card>();
    pile = this.getPile(source, pileNumber);
    // Checks to see if moving more than 1 card to a cascade pile
    // and if enough intermediates exist to do so.
    int openInter = this.getNumEmpty(this.openPiles);
    int cascadeInter = this.getNumEmpty(this.cascadePiles);
    int numMovable = (int) ((openInter + 1) * Math.pow(2.0, (double) cascadeInter));
    int numMoving = pile.size() - cardIndex;
    if (numMoving >= 2 && destination.equals(PileType.CASCADE) && numMoving <= numMovable) {
      for (int i = 0; i < numMoving; i++) {
        cards.add(pile.pop());
      }
      pile = this.getPile(destination, destPileNumber);
      // place method will throw an exception if first card added to empty cascade is not a king.
      // push will not.
      if (pile.size() == 0) {
        pile.push(cards.remove(cards.size() - 1));
      }
      int size = cards.size();
      for (int i = 0; i <= size - 1; i++) {
        pile.place(cards.remove(cards.size() - 1));
        // place throws exception if move is invalid.
      }
    }
    // Checks to see if moving only 1 card
    else if (numMoving == 1) {
      cards.add(pile.pop());
      // adds card to the specified destination pile
      pile = this.getPile(destination, destPileNumber);
      if (destination == PileType.CASCADE && pile.size() == 0) {
        pile.push(cards.get(0)); // allows any card to be added to empty cascade piles.
      }
      else {
        pile.place(cards.get(0)); // place throws exception if move is invalid.
      }
    }
    // Throws exception if move is not possible.
    else {
      throw new IllegalArgumentException("Cannot move that many cards.");
    }
  }

  /**
   * Returns the number of empty piles in the given list of piles.
   * @param piles the list of piles
   * @return the number of empty piles in the given list of piles
   */
  protected int getNumEmpty(ArrayList<Pile> piles) {
    int count = 0;
    for (Pile p: piles) {
      if (p.size() == 0) {
        count += 1;
      }
    }
    return count;
  }

  /**
   * Returns the pile from the specified type and number.
   * @param type type of piles being looked at
   * @param pileNum pile number to be selected
   * @return a pile
   */
  protected Pile getPile(PileType type, int pileNum) {
    switch (type) {
      case OPEN:
        return this.openPiles.get(pileNum);
      case CASCADE:
        return this.cascadePiles.get(pileNum);
      case FOUNDATION:
        return this.foundationPiles.get(pileNum);
      default:
        throw new IllegalArgumentException("PileType destination not found");
    }
  }

  @Override
  public boolean isGameOver() {
    if (this.openPiles.size() == 0
        && this.cascadePiles.size() == 0
        && this.foundationPiles.size() == 0) {
      return true;
    }
    else {
      Pile pile;
      for (int i = 0; i < 4; i++) {
        pile = this.foundationPiles.get(i);
        if (!isSolvedFoundationPile(pile)) {
          return false;
        }
      }
      return true;
    }
  }

  /**
   * Checks to see if the given foundation pile is solved. Used in isGameOver method.
   * @param pile will be checked to see whether it is a complete foundation pile
   */
  private boolean isSolvedFoundationPile(Pile pile) {
    if (pile.size() == 13) {
      Iterator<Card> iter = pile.iterator();
      Card c = iter.next();
      while (iter.hasNext()) {
        Card next = iter.next();
        if (c.getRank().getIndex() != next.getRank().getIndex() - 1
            && c.getSuit().equals(next.getSuit())) {
          return false;
        }
        c = next;
      }
      return true;
    }
    else {
      return false;
    }
  }

  @Override
  public String getGameState() {
    if (this.openPiles.size() == 0
        && this.cascadePiles.size() == 0
        && this.foundationPiles.size() == 0) {
      return "";
    }
    else {
      String state = "";
      state += this.getPilesState(this.foundationPiles, "F");
      state += "\n";
      state += this.getPilesState(this.openPiles, "O");
      state += "\n";
      state += this.getPilesState(this.cascadePiles, "C");
      return state;
    }
  }

  /**
   * Returns the current state of a set of Piles. Used by getGameState method.
   * @param allPiles allPiles of a certain pileType
   * @return String prefix for pile type
   */
  private String getPilesState(ArrayList<Pile> allPiles, String prefix) {
    StringBuilder state = new StringBuilder();
    Pile pile;
    int size = allPiles.size();
    for (int i = 0; i < size - 1; i++) {
      state.append(prefix).append(i + 1).append(":");
      pile = allPiles.get(i);
      if (pile.size() > 0) {
        state.append(" ").append(pile.toString());
      }
      state.append("\n");
    }
    state.append(prefix).append(size).append(":");
    pile = allPiles.get(size - 1);
    if (pile.size() > 0) {
      state.append(" ").append(pile.toString());
    }
    return state.toString();
  }
}
