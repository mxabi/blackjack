import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Blackjack extends Game {

    //TODO: Don't reshuffle deck between each game. Need to keep track of how many cards we have left.

    Scanner scan = new Scanner(System.in);
    boolean gameOn = false;

    boolean willHit = true;
    boolean dealerHit = true;
    boolean playerBust = false;
    boolean dealerBust = false;
    int playerValue = 0;
    int dealerValue = 0;

    Game game = new Game();

    Map<Integer, Integer> cardValues = game.getCardValues();

    public int getPlayerValue() {
        return playerValue;
    }

    public int getDealerValue() {
        return dealerValue;
    }


    public boolean playGame(boolean gameOn, Player player) {

        if (player.getChipStack() == 0) {
            gameOn = false;
        } else {
            String initialMessage = "Do you want to play Blackjack";
            String playAgain = initialMessage + " again?";
            if (gameOn) {
                System.out.println(playAgain);
            } else {
                System.out.println(initialMessage + "?");
            }
            String result = scan.next();

            if (result.toLowerCase().charAt(0) == 'y') {
                gameOn = true;
            }
        }

        return gameOn;
    }

    public int makeBet(Player player) {
        System.out.println("You have " + player.getChipStack() + " chips in your stack.");
        System.out.println("Place your bet!");
        int result = scan.nextInt();
        boolean madeBet = player.makeBet(result);
        if (madeBet) {
            System.out.println("You have bet " + result + " chips.");
        }
        return result;
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

    public boolean playerLoop(List<List<Card>> initialList, Deck deck, boolean willHit, Player player) {
        List<Card> playerCards = initialList.get(0);
        boolean playerBust = false;
        while (willHit) {
            playerValue = 0;
            int aceCounter = 0;
            for (Card card : playerCards) {
                playerValue += cardValues.get(card.getRank());
                if (Game.getRank(card.getRank()).equals("Ace")) {
                    aceCounter++;
                }
            }
            if (aceCounter > 0 && playerValue <= 11) {
                playerValue += 10;
            }
            if (aceCounter > 0 && playerValue > 21) {
                playerValue -= 10;
            }
            if (playerValue > 21) {
                System.out.println(playerValue + ": you bust!");
                dealerHit = false;
                playerBust = true;
                player.loseBet();
                break;
            }
            if (playerValue == 21) {
                System.out.println("21, that's Blackjack!");
                break;
            }

            System.out.println("Player hand worth: " + playerValue);

            System.out.println("Do you want to hit or stay?");
            String result = scan.next();
            if (result.toLowerCase().charAt(0) == 'h') {
                List<Card> newCard = deck.dealCards(1);
                Card hit = newCard.remove(0);
                System.out.println(Game.getRank(hit.getRank()) + " " + Game.getSuit(hit.getSuit()));
                playerCards.add(hit);
            } else {
                willHit = false;
            }
        }
        return playerBust;
    }


    public boolean dealerLoop(List<List<Card>> initialList, Deck deck, boolean dealerHit, Player player, int bet) {
        List<Card> dealerCards = initialList.get(1);
        boolean dealerBust = false;


        while (dealerHit) {
            System.out.println("Dealer:");

            for (Card card : dealerCards) {
                System.out.println(Game.getRank(card.getRank()) + " " + Game.getSuit(card.getSuit()));
            }

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
            } else if (dealerValue > 21) {
                System.out.println(dealerValue + ": Dealer busts, you win!");
                dealerBust = true;
                player.winBet(bet);
                break;
            } else {
                dealerHit = false;
            }
        }
        return dealerBust;
    }


    public void winLossCheck(boolean playerBust, boolean dealerBust, int playerValue, int dealerValue, Player player, int bet) {
        if (!playerBust && !dealerBust) {
            if (dealerValue > playerValue) {
                System.out.println(dealerValue + " is higher than " + playerValue + ". You lose!");
                player.loseBet();
            } else if (playerValue == 21 && playerValue > dealerValue) {
                System.out.println("You win!");
                player.blackjack(bet);
            } else if (playerValue > dealerValue) {
                System.out.println(playerValue + " is higher than " + dealerValue + ". You win!");
                player.winBet(bet);
            } else {
                System.out.println(playerValue + " is the same as " + dealerValue + ". You push!");
                player.pushBet(bet);
            }
        }
    }

}
