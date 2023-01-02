import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> listOfCards = new ArrayList<>();

    public Deck(int[] suits, int[] ranks) {
        for (int suit : suits) {
            for (int rank : ranks) {
                listOfCards.add(new Card(suit, rank));
            }
        }
    }

    // TODO: Come back to shuffling function?
    public void shuffleDeck() {
        Collections.shuffle(listOfCards);
    }

    public String DeckString() {
        String deck = "";
        for (Card card : listOfCards) {
            deck += card.cardString(false) + "\n";
        }
        return deck;
    }

    public List<Card> dealCards(int numberOfCards) {
        List<Card> cardsDealt = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {

            if (listOfCards.size() > 0) {
                cardsDealt.add(listOfCards.remove(0));
            } else {
                return null;
            }
        }
        return cardsDealt;
    }
}
