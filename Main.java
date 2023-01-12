import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Game game = new Game();
        Blackjack blackjack = new Blackjack();
        Player player = new Player(100);

        boolean gameOn = blackjack.playGame(false, player);

        Deck deck = blackjack.getDeck();
        deck.shuffleDeck();

        //TODO: Running out of cards?

        while (gameOn) {
            int bet = blackjack.makeBet(player);
            List<List<Card>> initialList = blackjack.initialDeal(deck);
            boolean playerBust = blackjack.playerLoop(initialList, deck, true, player);
            boolean dealerBust = blackjack.dealerLoop(initialList, deck, true, player, bet);
            int dealerValue = blackjack.getDealerValue();
            int playerValue = blackjack.getPlayerValue();


            blackjack.winLossCheck(playerBust, dealerBust, playerValue, dealerValue, player, bet);


            gameOn = blackjack.playGame(gameOn, player);
            int deckCount = deck.getListOfCards().size();
            if (deckCount <= 26){
                Game game = new Game();
                deck = game.getDeck();
                deck.shuffleDeck();
            }
         

        }


    }

}
