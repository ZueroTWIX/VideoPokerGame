public enum Rank{
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),  // Валет
    QUEEN("Q"), // Дама
    KING("K"),  // Король
    ACE("A");    // Туз

    private String rank;

    private Rank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return rank;
    }
}