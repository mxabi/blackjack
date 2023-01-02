import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game blackjack = new Game();
        Scanner scan = new Scanner(System.in);
        boolean gameOn = false;

        System.out.println("Do you want to play Blackjack?");
        String result = scan.next();

        if (result.toLowerCase().substring(0, 1).equals("y")) {
            gameOn = true;
        }

        while (gameOn) {

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
            //TODO: Implement entire game logic

            System.out.println("Do you want to play Blackjack again?");
            result = scan.next();

            if (!result.toLowerCase().substring(0, 1).equals("y")) {
                gameOn = false;
            }
        }


    }

}
