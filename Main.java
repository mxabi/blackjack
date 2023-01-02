import java.util.List;

public class Main {

    public static void main(String[] args) {
        Game blackjack = new Game();
        Deck deck = blackjack.getDeck();
        deck.shuffleDeck();

        List<Card> playerCards = deck.dealCards(2);
        List<Card> dealerCards = deck.dealCards(2);

        System.out.println("Dealer:");

        Card visibleCard = dealerCards.get(0);
        System.out.println(Game.getRank(visibleCard.getRank()) + " " + Game.getSuit(visibleCard.getSuit()));
        System.out.println("##");


        System.out.println("Player:");

        for (Card card : playerCards) {
            System.out.println(Game.getRank(card.getRank()) + " " + Game.getSuit(card.getSuit()));
        }

    }


}
