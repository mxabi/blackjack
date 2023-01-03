import java.util.HashMap;
import java.util.Map;

public class Game {

    private Deck deck;
    private Map<Integer, Integer> cardValues = new HashMap<>();

    //TODO: Add chips in the future?
    public static final int NOTHING =0;
    public static final int SPADES = 1;
    public static final int DIAMONDS = 2;
    public static final int CLUBS = 3;
    public static final int HEARTS = 4;
    public static final int JOKER = 0;
    public static final int ACE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    public static final int NINE = 9;
    public static final int TEN = 10;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;

    public Game() {
        int[] suits = new int[]{SPADES, DIAMONDS, CLUBS, HEARTS};
        int[] ranks = new int[]{ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING};
        deck = new Deck(suits, ranks);


        cardValues.put(ACE, 11);   //TODO in order to have it be 1 or 11 we need to add 10 to the value if the total card value is <= 10
        cardValues.put(TWO, 2);
        cardValues.put(THREE, 3);
        cardValues.put(FOUR, 4);
        cardValues.put(FIVE, 5);
        cardValues.put(SIX, 6);
        cardValues.put(SEVEN, 7);
        cardValues.put(EIGHT, 8);
        cardValues.put(NINE, 9);
        cardValues.put(TEN, 10);
        cardValues.put(JACK, 10);
        cardValues.put(QUEEN, 10);
        cardValues.put(KING, 10);

    }

    public Deck getDeck() {
        return deck;
    }

    private static String[] suitNames = new String[]{"Nothing","Spades", "Diamonds", "Clubs", "Hearts"};
    private static String[] rankNames = new String[]{"Joker", "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

    private static char[] suitSymbols = new char[]{'\u0000','\u2660', '\u2666', '\u2663', '\u2665'};

    public static String getSuit(int suit) {
        return suitNames[suit] + " " + suitSymbols[suit];
    }

    public static String getRank(int rank) {
        return rankNames[rank];
    }
    public Map<Integer, Integer> getCardValues(){
        return cardValues;
    }





}


