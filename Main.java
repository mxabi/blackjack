import java.util.List;
import java.util.Map;
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

            Map<Integer, Integer> cardValues = blackjack.getCardValues();

            // TODO figure out the stuff with ACES and the whole 1 and 11 thing in both dealerHit and whileHit loops

            boolean willHit = true;
            boolean dealerHit = true;
            boolean bust = false;
            int playerValue = 0;


            while (willHit) {
                playerValue = 0;
                for (Card card : playerCards) {
                    playerValue += cardValues.get(card.getRank());
                    if (Game.getRank(card.getRank()).equals("Ace")) {
                        // TODO we are here
                    }
                }
                if (playerValue > 21) {
                    System.out.println(playerValue + ": you bust!");
                    dealerHit = false;
                    bust = true;
                    break;
                }
                if (playerValue == 21) {
                    System.out.println("21, that's Blackjack!");
                    break;
                }

                System.out.println("Player hand worth: " + playerValue);

                System.out.println("Do you want to hit or stay?");
                result = scan.next();
                if (result.toLowerCase().substring(0, 1).equals("h")) {
                    List<Card> newCard = deck.dealCards(1);
                    Card hit = newCard.remove(0);
                    System.out.println(Game.getRank(hit.getRank()) + " " + Game.getSuit(hit.getSuit()));
                    playerCards.add(hit);
                } else {
                    willHit = false;
                }
            }

            int dealerValue = 0;

            while (dealerHit) {
                System.out.println("Dealer:");

                for (Card card : dealerCards) {
                    System.out.println(Game.getRank(card.getRank()) + " " + Game.getSuit(card.getSuit()));
                }
                ;

                dealerValue = 0;
                for (Card card : dealerCards) {
                    dealerValue += cardValues.get(card.getRank());
                }
                System.out.println("Dealer Value: " + dealerValue);

                if (dealerValue < 17) {
                    List<Card> newCard = deck.dealCards(1);
                    Card hit = newCard.remove(0);
                    System.out.println(Game.getRank(hit.getRank()) + " " + Game.getSuit(hit.getSuit()));
                    dealerCards.add(hit);
                } else {
                    dealerHit = false;
                }
            }


            if (!bust) {
                if (dealerValue > playerValue) {
                    System.out.println(dealerValue + " is higher than " + playerValue + ". You lose!");
                } else if (playerValue > dealerValue) {
                    System.out.println(playerValue + " is higher than " + dealerValue + ". You win!");
                } else {
                    System.out.println(playerValue + " is the same as " + dealerValue + ". You tie!");
                }
            }


            System.out.println("Do you want to play Blackjack again?");
            result = scan.next();

            if (!result.toLowerCase().substring(0, 1).equals("y")) {
                gameOn = false;
            }
        }


    }

}
