package ru.spbu.apcyb.svp.tasks.task1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.*;

public class CoinChanger {

    private int sum;
    private int[] coins;
    private static final Logger LOGGER = Logger.getLogger(CoinChanger.class.getName());

    public CoinChanger() {
        try (Scanner input = new Scanner(System.in)) {

            LOGGER.info("Enter amount of money for change: ");
            sum = input.nextInt();

            LOGGER.info("Enter number of available denominations for change: ");
            int numCoins = input.nextInt();
            coins = new int[numCoins];

            LOGGER.info("Enter available denominations: ");
            for (int i = 0; i < numCoins; i++) {
                coins[i] = input.nextInt();
            }

        } catch (InputMismatchException e) {
            LOGGER.log(Level.WARNING, "Invalid input. Please enter a valid integer.", e);
        }
    }

    // Метод для нахождения количества комбинаций сдачи
    public int countChangeOptions() {
        List<List<Integer>> combinations = getChangeOptions(sum, coins, 0);
        displayCombinations(combinations); // Отображение комбинаций
        return combinations.size();
    }

    // Метод для отображения комбинаций
    private void displayCombinations(List<List<Integer>> combinations) {
        System.out.println("\nCombinations: ");
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }

    // Метод для нахождения всех возможных комбинаций сдачи
    private List<List<Integer>> getChangeOptions(int amount, int[] coins, int index) {
        List<List<Integer>> combinations = new ArrayList<>();
        if (amount == 0) {
            combinations.add(new ArrayList<>()); // Добавляем пустую комбинацию (нет сдачи)
            return combinations;
        }

        if (amount < 0 || index == coins.length) {
            return combinations;
        }

        int currentCoin = coins[index];

        // Находим комбинации с учетом текущей монеты
        for (int i = 0; i * currentCoin <= amount; i++) {
            int remainingAmount = amount - i * currentCoin;
            List<List<Integer>> subCombinations = getChangeOptions(remainingAmount, coins, index + 1);

            // Добавляем комбинации с учетом текущей монеты
            for (List<Integer> subCombination : subCombinations) {
                for (int j = 0; j < i; j++) {
                    subCombination.add(currentCoin);
                }
                combinations.add(new ArrayList<>(subCombination));
            }
        }

        return combinations;
    }

    public static void main(String[] args) {
        CoinChanger changeMachine = new CoinChanger();
        int count = changeMachine.countChangeOptions();
        LOGGER.log(Level.INFO, "Total combinations: {0}", count);
    }
}
