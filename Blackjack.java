import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Blackjack extends Game {
    //TODO: Add all game logic from main as functions.
    //TODO: Don't reshuffle deck between each game. Need to keep track of how many cards we have left.

    Scanner scan = new Scanner(System.in);
    boolean gameOn = false;

    boolean willHit = true;
    boolean dealerHit = true;
    boolean playerBust = false;
    boolean dealerBust = false;
    int playerValue = 0;

    Game game = new Game();
    Map<Integer, Integer> cardValues = game.getCardValues();


    public boolean playGame(boolean gameOn){
            String initialMessage = "Do you want to play Blackjack";
            String playAgain = initialMessage + "again?";
            if (gameOn){
                System.out.println(playAgain);
            } else {
                System.out.println(initialMessage + "?");
            }
            String result = scan.next();

            if (result.toLowerCase().substring(0, 1).equals("y")) {
                gameOn = true;
            }
            return gameOn;
        }

        public List<List<Card>> initialDeal(Deck deck) {
            List<Card> playerCards = deck.dealCards(2);
            List<Card> dealerCards = deck.dealCards(2);
            List<List<Card>> initialList = new ArrayList<>();
            initialList.add(playerCards);
            initialList.add(dealerCards);

            System.out.println("Dealer:");

            Card visibleCard = dealerCards.get(0);
            System.out.println(Game.getRank(visibleCard.getRank()) + " " + Game.getSuit(visibleCard.getSuit()));
            System.out.println("##");


            System.out.println("Player:");

            for (Card card : playerCards) {
                System.out.println(Game.getRank(card.getRank()) + " " + Game.getSuit(card.getSuit()));
            }
            return initialList;
        }

        public void playerLoop(List<List<Card>> initialList, Deck deck){
            List<Card> playerCards = initialList.get(0);

            while (willHit) {
                playerValue = 0;
                int aceCounter =0;
                for (Card card : playerCards) {
                    playerValue += cardValues.get(card.getRank());
                    if (Game.getRank(card.getRank()).equals("Ace")) {
                        aceCounter++;
                    }
                }
                if (aceCounter > 0 && playerValue <=11){
                    playerValue+=10;
                }
                if (aceCounter > 0 && playerValue > 21){
                    playerValue-=10;
                }
                if (playerValue > 21) {
                    System.out.println(playerValue + ": you bust!");
                    dealerHit = false;
                    playerBust = true;
                    break;
                }
                if (playerValue == 21) {
                    System.out.println("21, that's Blackjack!");
                    break;
                }

                System.out.println("Player hand worth: " + playerValue);

                System.out.println("Do you want to hit or stay?");
                String result = scan.next();
                if (result.toLowerCase().substring(0, 1).equals("h")) {
                    List<Card> newCard = deck.dealCards(1);
                    Card hit = newCard.remove(0);
                    System.out.println(Game.getRank(hit.getRank()) + " " + Game.getSuit(hit.getSuit()));
                    playerCards.add(hit);
                } else {
                    willHit = false;
                }
            }
        }



}
