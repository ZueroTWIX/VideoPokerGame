// HandCombination.java
public enum HandCombination {
    HIGH_CARD("Старшая карта", 0),
    ONE_PAIR("Одна пара", 1),
    TWO_PAIR("Две пары", 2),
    THREE_OF_A_KIND("Сет (Тройка)", 3),
    STRAIGHT("Стрит", 4),
    FLUSH("Флеш", 5),
    FULL_HOUSE("Фулл-хаус", 6),
    FOUR_OF_A_KIND("Каре (Четверка)", 7),
    STRAIGHT_FLUSH("Стрит-флеш", 8),
    ROYAL_FLUSH("Роял-флеш", 9);

    private final String name;
    private final int value; // Для сравнения силы комбинаций

    HandCombination(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}