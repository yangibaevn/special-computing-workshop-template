package ru.spbu.apcyb.svp.tasks.task1;

import java.util.*;
import java.util.logging.*;

public class CoinChanger {
    private int sum;
    private int[] coins;
    private static final Logger LOGGER = Logger.getLogger(CoinChanger.class.getName());

    public CoinChanger() {
        try (Scanner input = new Scanner(System.in)) {

            LOGGER.info("Enter amount of money for change: ");
            if (input.hasNextLine()) {
                String userInput = input.nextLine();

                if (!userInput.isEmpty()) {
                    sum = Integer.parseInt(userInput);

                    if (sum < 0) {
                        LOGGER.info("Invalid amount. Please enter a non-negative integer.");
                        input.nextInt();
                    }

                    LOGGER.info("Enter number of available denominations for change: ");
                    int numCoins = input.nextInt();
                    coins = new int[numCoins];

                    LOGGER.info("Enter available denominations: ");
                    for (int i = 0; i < numCoins; i++) {
                        coins[i] = input.nextInt();
                    }
                } else {
                    LOGGER.warning("Input cannot be empty.");
                }
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
        LOGGER.log(Level.INFO, "\nCombinations: ");
        for (List<Integer> combination : combinations) {
            LOGGER.log(Level.INFO, combination.toString());
        }
    }

    // Метод для нахождения всех возможных комбинаций сдачи
    private List<List<Integer>> getChangeOptions(int amount, int[] coins, int index) {
        List<List<Integer>> combinations = new ArrayList<>();
        if (amount == 0) {
            combinations.add(new ArrayList<>());
            return combinations;
        }

        if (amount < 0 || index == coins.length) {
            return combinations;
        }

        int currentCoin = coins[index];

        for (int i = 0; i * currentCoin <= amount; i++) {
            int remainingAmount = amount - i * currentCoin;
            List<List<Integer>> subCombinations = getChangeOptions(remainingAmount, coins, index + 1);

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
