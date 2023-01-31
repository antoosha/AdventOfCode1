package korolov.project.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Class to process task to find 3 the most total calories throw all elves
 */
public class Worker {

    private final String path = "input.txt";

    private List<Integer> maxCalories = new ArrayList<>();

    public void work() {

        List<Integer> calories = new ArrayList<>();
        Scanner scanner = null;

        try {
            File file = new File(path);
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                if (data.isBlank()) {
                    findThreeMax(calories);
                    calories = new ArrayList<>();
                } else {
                    calories.add(Integer.parseInt(data));
                }
            }
            findThreeMax(calories); // to include last calories

            for (int i = 0; i < 3 && i < maxCalories.size(); i++) {
                System.out.println("MaxCallories: " + maxCalories.get(i));
            }
            System.out.println("Sum: " + maxCalories.stream().mapToInt(Integer::intValue).sum());


        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("An error while reading file occurred.");
            fileNotFoundException.printStackTrace();
        } catch (NumberFormatException numberFormatException) {
            System.out.println("An error while reading data occurred. Data have wrong format.");
            numberFormatException.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Finds max between old max and new value counted from input list.
     *
     * @param calories list of calories of one Elf
     */
    private void findOneMax(List<Integer> calories) {

        var currentSum = calories.stream()
                .mapToInt(Integer::intValue)
                .sum();

        if (maxCalories.isEmpty()) {
            maxCalories.add(currentSum);
        } else {
            if (currentSum > maxCalories.get(0)) {
                maxCalories.set(0, currentSum);
            }
        }
    }

    private void findThreeMax(List<Integer> calories) {

        var currentSum = calories.stream()
                .mapToInt(Integer::intValue)
                .sum();


        if (maxCalories.isEmpty() || maxCalories.size() < 3) {
            maxCalories.add(currentSum);
        } else {
            maxCalories.add(currentSum);
            maxCalories.sort(Comparator.reverseOrder());
            maxCalories = maxCalories.stream().limit(3).collect(Collectors.toList());
        }
    }
}
