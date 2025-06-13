import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private Deck deck;
    private Hand playerHand;
    private Scanner scanner; 
    private HandEvaluator handEvaluator;

    public Game() {
        deck = new Deck();
        playerHand = new Hand();
        scanner = new Scanner(System.in);
        handEvaluator = new HandEvaluator();
    }

    public void startGame() {
        System.out.println("--- Игра началась! ---");

        deck.shuffleDeck();
        System.out.println("Колода перетасована.");

        dealInitialHand();

        System.out.println("\nВаша рука:");
        System.out.println(playerHand.displayHand());

        promptForCardReplacement();

        System.out.println("\nВаша финальная рука:");
        System.out.println(playerHand.displayHand());

        HandCombination finalCombination = handEvaluator.evaluateHand(playerHand);
        System.out.println("Ваша комбинация: " + finalCombination.getName());

        System.out.println("\n--- Игра завершена! ---");
        scanner.close();
    }

    private void dealInitialHand() {
        System.out.println("Раздаем 5 карт...");
        for (int i = 0; i < 5; i++) {
            try {
                Card drawnCard = deck.drawCard();
                playerHand.addCard(drawnCard);
            } catch (IllegalStateException e) {
                System.err.println("Ошибка при раздаче карт: " + e.getMessage());
                break;
            }
        }
    }

    private void promptForCardReplacement() {
        boolean validInput = false;
        while (!validInput) {
            System.out.printf("\n\tВведите номера карт, которые вы хотите заменить (через пробел).\n");
            System.out.printf("\tНапример, \"1 3 4\" заменят 1-ую, 3-ю и 4-ую карты соответственно (позиции начинаются с 1).\n");
            System.out.printf("\tДля пропуска обмена, просто нажмите Enter.\n");
            System.out.print("\tВаш ввод: ");

            String inputLine = scanner.nextLine();

            if (inputLine.trim().isEmpty()) {
                System.out.println("Вы решили не менять карты.");
                validInput = true;
                continue;
            }

            try {
                List<Integer> positionsToReplace = parseNumbersString(inputLine);

                if (positionsToReplace.size() > 3) {
                    System.out.println("Вы не можете заменить более 3 карт за раз. Попробуйте снова.");
                    continue;
                }

                boolean allPositionsValid = true;
                for (Integer pos : positionsToReplace) {
                    if (pos < 1 || pos > playerHand.getCardCount()) {
                        System.out.println("Ошибка: Позиция " + pos + " некорректна. Пожалуйста, введите номера позиций от 1 до " + playerHand.getCardCount() + ".");
                        allPositionsValid = false;
                        break;
                    }
                }

                if (!allPositionsValid) {
                    continue;
                }

                for (Integer position : positionsToReplace) {
                    Card newCard = deck.drawCard();
                    playerHand.replaceCard(position, newCard);
                }
                validInput = true;
            }catch (IllegalStateException e) {
                System.err.println("Ошибка при обмене карт: " + e.getMessage());
                validInput = true;
            }
        }
    }

    private List<Integer> parseNumbersString(String numbersString) {
        List<Integer> integerList = new ArrayList<>();

        if (numbersString == null || numbersString.trim().isEmpty()) {
            return integerList;
        }

        String[] numberStrings = numbersString.trim().split("\\s+");

        for (String numStr : numberStrings) {
            if (!numStr.isEmpty()) {
                try {
                    integerList.add(Integer.parseInt(numStr));
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка: Введенное значение '" + numStr + "' не является корректным числом. Пожалуйста, введите только цифры, разделенные пробелами.");
                    throw new IllegalArgumentException("Некорректный формат ввода: " + numbersString, e);
                }
            }
        }
        return integerList;
    }

    public static void main(String[] args) {
        Game pokerGame = new Game();
        pokerGame.startGame();
    }
}