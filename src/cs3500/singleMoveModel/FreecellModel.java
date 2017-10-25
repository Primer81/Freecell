package cs3500.singleMoveModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * This is the class which implements FreecellOperations interface with the Card type.
 */
public class FreecellModel implements FreecellOperations<Card> {

  private ArrayList<Pile> foundationPiles;
  private ArrayList<Pile> cascadePiles;
  private ArrayList<Pile> openPiles;

  /**
   * Constructs a new FreecellModel.
   */
  public FreecellModel() {
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
  private void validateDeck(List<Card> deck) {
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
  private void initFields(int numCascadePiles, int numOpenPiles) {
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
  private void shuffle(List<Card> deck) {
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
  private void deal(List<Card> deck, int numCascadePiles) {
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
    Card c;
    // removes 1 card from source pile and stores it in c
    switch (source) {
      case OPEN:
        pile = this.openPiles.get(pileNumber);
        break;
      case CASCADE:
        pile = this.cascadePiles.get(pileNumber);
        break;
      case FOUNDATION:
        pile = this.foundationPiles.get(pileNumber);
        break;
      default:
        throw new IllegalArgumentException("PileType source not found");
    }
    if (cardIndex != pile.size() - 1) {
      throw new IllegalArgumentException("Only 1 card can be moved at a time");
    }
    c = pile.pop();
    // adds card to the specified destination pile
    switch (destination) {
      case OPEN:
        pile = this.openPiles.get(destPileNumber);
        break;
      case CASCADE:
        pile = this.cascadePiles.get(destPileNumber);
        break;
      case FOUNDATION:
        pile = this.foundationPiles.get(destPileNumber);
        break;
      default:
        throw new IllegalArgumentException("PileType destination not found");
    }
    pile.place(c); // place throws exception if move is invalid.
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
