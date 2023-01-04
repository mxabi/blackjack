import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Game game = new Game();
        Blackjack blackjack = new Blackjack();
        Player player = new Player(100);

        boolean gameOn = blackjack.playGame(false);

        Deck deck = blackjack.getDeck();
        deck.shuffleDeck();

        //TODO: Running out of cards?

        while (gameOn) {
            List<List<Card>> initialList = blackjack.initialDeal(deck);
            blackjack.playerLoop(initialList,deck);

            //TODO: Dealer Loop

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
                }
                else if (dealerValue > 21) {
                    System.out.println(dealerValue + ": Dealer busts, you win!");
                    dealerBust = true;
                    break;
                }else {
                    dealerHit = false;
                }
            }
            //TODO: Win-loss check

            if (!playerBust && !dealerBust) {
                if (dealerValue > playerValue) {
                    System.out.println(dealerValue + " is higher than " + playerValue + ". You lose!");
                } else if (playerValue > dealerValue) {
                    System.out.println(playerValue + " is higher than " + dealerValue + ". You win!");
                } else {
                    System.out.println(playerValue + " is the same as " + dealerValue + ". You tie!");
                }
            }


            gameOn = blackjack.playGame(false);

        }


    }

}
