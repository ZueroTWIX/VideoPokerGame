import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandEvaluator {

    public HandCombination evaluateHand(Hand hand) {
        List<Card> cards = new ArrayList<>(hand.getCards());
        if (cards.size() != 5) {
            return HandCombination.HIGH_CARD;
        }

        Collections.sort(cards, Comparator.comparing(card -> card.getRank().ordinal()));

        boolean isFlush = checkIfFlush(cards);
        boolean isStraight = checkIfStraight(cards);

        if (isStraight && isFlush) {
            if (cards.get(0).getRank() == Rank.TEN &&
                cards.get(1).getRank() == Rank.JACK &&
                cards.get(2).getRank() == Rank.QUEEN &&
                cards.get(3).getRank() == Rank.KING &&
                cards.get(4).getRank() == Rank.ACE) {
                return HandCombination.ROYAL_FLUSH;
            }
            return HandCombination.STRAIGHT_FLUSH;
        }

        Map<Rank, Integer> rankCounts = getRankCounts(cards);
        Map<Integer, Integer> countCounts = getCountCounts(rankCounts);

        if (countCounts.containsKey(4)) {
            return HandCombination.FOUR_OF_A_KIND;
        }
        if (countCounts.containsKey(3) && countCounts.containsKey(2)) {
            return HandCombination.FULL_HOUSE;
        }
        if (isFlush) {
            return HandCombination.FLUSH;
        }
        if (isStraight) {
            return HandCombination.STRAIGHT;
        }
        if (countCounts.containsKey(3)) {
            return HandCombination.THREE_OF_A_KIND;
        }
        if (countCounts.containsKey(2) && countCounts.get(2) == 2) {
            return HandCombination.TWO_PAIR;
        }
        if (countCounts.containsKey(2)) {
            return HandCombination.ONE_PAIR;
        }

        return HandCombination.HIGH_CARD;
    }

    private boolean checkIfFlush(List<Card> cards) {
        Suit firstSuit = cards.get(0).getSuit();
        for (Card card : cards) {
            if (card.getSuit() != firstSuit) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfStraight(List<Card> cards) {
        boolean regularStraight = true;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i + 1).getRank().ordinal() != cards.get(i).getRank().ordinal() + 1) {
                regularStraight = false;
                break;
            }
        }
        if (regularStraight) {
            return true;
        }

        if (cards.get(0).getRank() == Rank.TWO &&
                cards.get(1).getRank() == Rank.THREE &&
                cards.get(2).getRank() == Rank.FOUR &&
                cards.get(3).getRank() == Rank.FIVE &&
                cards.get(4).getRank() == Rank.ACE) {
            return true;
        }

        return false;
    }

    private Map<Rank, Integer> getRankCounts(List<Card> cards) {
        Map<Rank, Integer> rankCounts = new HashMap<>();
        for (Card card : cards) {
            rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
        }
        return rankCounts;
    }

    private Map<Integer, Integer> getCountCounts(Map<Rank, Integer> rankCounts) {
        Map<Integer, Integer> countCounts = new HashMap<>();
        for (int count : rankCounts.values()) {
            countCounts.put(count, countCounts.getOrDefault(count, 0) + 1);
        }
        return countCounts;
    }
}