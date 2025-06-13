public enum Suit {
    CLUBS("C"),    // Трефы
    DIAMOND("D"), // Бубны
    HEARTS("H"),   // Червы
    SPADES("S");    // Пики 

    private String suit;

    private Suit (String suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return suit;
    }
}