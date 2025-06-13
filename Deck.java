import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

    private final int DECK_SIZE = 52;

    private List<Card> cards;

    private static Random RND = new Random();

    public Deck() {
        this.cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                this.cards.add(new Card(suit, rank));
            }
        }
        if(cards.size() != DECK_SIZE) {
            throw new IllegalStateException("Критическая ошибка! Обратитесь к создателю.");
        }
    }

    public Card drawCard() {
        if(cards.isEmpty()) {
            throw new IllegalStateException("Колода пуста, невозможно вытянуть карту.");
        }
        int randomIndex = RND.nextInt(cards.size());
        Card card = cards.remove(randomIndex);
        return card;
    }

    public void returnCard(Card card) {
        if (!cards.contains(card)) {
            cards.add(card);
        } else {
            System.out.println("Ошибка! Такая карта есть в колоде.");
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(String.format("%s%n", card.toString()));
        }
        return sb.toString();
    }
}