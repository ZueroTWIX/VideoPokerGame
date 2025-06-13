import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        if (cards.size() < 5) {
            cards.add(card);
            System.out.println("Карта '" + card.toString().trim() + "' добавлена в руку.");
        } else {
            System.out.println("Не удалось добавить карту: рука уже полная (максимум 5 карт).");
        }
    }

    public void replaceCard(int position, Card newCard) {
        int listIndex = position - 1;

        if (listIndex >= 0 && listIndex < cards.size()) {
            Card oldCard = cards.set(listIndex, newCard);
            System.out.println("Карта на позиции " + position + " ('" + oldCard.toString().trim() + "') заменена на '" + newCard.toString().trim() + "'.");
        } else {
            System.out.println("Некорректная позиция для замены: " + position + ". Доступные позиции: от 1 до " + cards.size() + ".");
        }
    }

    public String displayHand() {
        if (cards.isEmpty()) {
            return "Рука пуста.";
        }

        StringBuilder indexRow = new StringBuilder("Позиции: ");
        StringBuilder cardRow = new StringBuilder("Карты:   ");

        for (int i = 0; i < cards.size(); i++) {
            indexRow.append(String.format(" %-7d", i + 1));
            cardRow.append(String.format(" %-7s", cards.get(i).toString().trim()));
        }
        return indexRow.toString() + "\n" + cardRow.toString();
    }

    public int getCardCount() {
        return cards.size();
    }

    public Card getCard(int position) {
        int listIndex = position - 1;
        if (listIndex >= 0 && listIndex < cards.size()) {
            return cards.get(listIndex);
        }
        return null;
    }

    public void clearHand() {
        cards.clear();
        System.out.println("Рука очищена.");
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}