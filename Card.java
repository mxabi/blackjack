public class Card {
    private int suit, rank;
    private boolean isFaceDown = true;

    public Card(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public boolean isFaceDown() {
        return isFaceDown;
    }

    public void flip () {
        this.isFaceDown = !this.isFaceDown;
    }

    public String cardString(boolean isFaceDown){
        if (isFaceDown) {
            return "##";
        } else {
            return this.getRank() + " " + this.getSuit();
        }
    }
}



