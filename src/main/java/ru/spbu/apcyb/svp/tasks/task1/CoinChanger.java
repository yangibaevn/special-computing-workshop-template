package ru.spbu.apcyb.svp.tasks.task1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoinChanger {
    private final int sum;
    private final int[] coins;
    private static final Logger LOGGER = Logger.getLogger(CoinChanger.class.getName());

    public CoinChanger() {
        try (Scanner input = new Scanner(System.in)) {
            LOGGER.info("Enter amount of money for change:");
            this.sum = getAmountForChangeFromInput(input);

            LOGGER.info("Enter available denominations for change:");
            this.coins = getChangeOptionsFromInput(input);
        }
    }

    private static int getAmountForChangeFromInput(Scanner input) {
        try {
            String sumAsString = input.nextLine();
            if (sumAsString.isBlank()) throw new RuntimeException("Input cannot be empty. Please enter a valid positive integer");

            int sum = Integer.parseInt(sumAsString);
            if (sum < 0) throw new RuntimeException("Amount for change should be a positive integer.");

            return sum;

        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid input format or value exceeds the limits of integer type. Please enter a valid positive integer.");
        }
    }

    private static int[] getChangeOptionsFromInput(Scanner input) {

        String[] coinsAsStrings = input.nextLine().split(" +");

        if (coinsAsStrings.length == 0) {
            throw new RuntimeException("There should be at least 1 available denomination for change.");
        }

        int[] coins = new int[coinsAsStrings.length];

        for (int i = 0; i < coinsAsStrings.length; i++) {
            try {
                int coinValue = Integer.parseInt(coinsAsStrings[i]);

                if (coinValue <= 0) {
                    throw new RuntimeException("Change options should be positive integers.");
                }

                coins[i] = coinValue;
            } catch (NumberFormatException e) {
                throw new RuntimeException("Change options should be valid positive integers.");
            }
        }

        return coins;
    }

    public void countChangeOptions() {
        List<int[]> changeOptions = getChangeOptions(sum);
        Set<List<Integer>> uniqueOptions = convertOptionsToSet(changeOptions);

        displayChangeCombinations(uniqueOptions);
    }

    private Set<List<Integer>> convertOptionsToSet(List<int[]> options) {
        Set<List<Integer>> result = new HashSet<>();

        for (int[] option : options) {
            List<Integer> optionAsList = new ArrayList<>();
            for (int i = 0; i < option.length; i++) {
                for (int j = 0; j < option[i]; j++) {
                    optionAsList.add(coins[i]);
                }
            }
            result.add(optionAsList);
        }

        return result;
    }

    private void displayChangeCombinations(Set<List<Integer>> options) {
        LOGGER.log(Level.INFO, "Total amount of combinations: {0}\nCombinations:", options.size());
        for (List<Integer> option : options) {
            System.out.println(option);
        }
    }

    private List<int[]> getChangeOptions(int sum) {
        List<int[]> combinations = new ArrayList<>();

        if (sum > 0) {
            for (int i = 0; i < coins.length; i++) {
                if (sum == coins[i]) {
                    int[] newCombination = new int[coins.length];
                    newCombination[i] += 1;
                    combinations.add(newCombination);
                } else if (sum > coins[i]) {
                    List<int[]> previousCombinations = getChangeOptions(sum - coins[i]);
                    for (int[] combination : previousCombinations) {
                        combination[i]++;
                        combinations.add(combination.clone());
                    }
                }
            }
        }

        return combinations;
    }

    public static void main(String[] args) {
        CoinChanger changeMachine = new CoinChanger();
        changeMachine.countChangeOptions();
    }
}
