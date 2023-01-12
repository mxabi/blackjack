public class Player {

    private int chipStack;
    private int bet;

    public Player(int chipStack) {
        this.chipStack = chipStack;
        this.bet = 0;
    }

    public int getBet() {
        return bet;
    }

    public int getChipStack() {
        return chipStack;
    }

    public boolean makeBet(int bet) {
        boolean madeBet = false;
        if (chipStack >= bet) {
            this.bet = bet;
            this.chipStack -= bet;
            madeBet = true;
        } else {
            System.out.println("You don't have enough chips to make this bet!");
        }
        return madeBet;
    }

    public void winBet(int bet) {
        this.chipStack += bet * 2;
        resetBet();
    }

    public void loseBet() {
        if (chipStack == 0) {
            System.out.println("You are out of chips. The house always wins >:)");
        }
        resetBet();
    }

    public void pushBet(int bet) {
        this.chipStack += bet;
        resetBet();
    }

    public void blackjack(int bet) {
        this.chipStack += bet + (bet * 1.5);
        resetBet();
    }

    public void resetBet() {
        this.bet = 0;
    }
}