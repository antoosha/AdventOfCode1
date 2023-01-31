package korolov.project.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Worker {

    private final String path = "input.txt";

    private int maxCalories = 0;

    public void work() {

        List<Integer> calories = new ArrayList<>();
        Scanner scanner = null;

        try {
            File file = new File(path);
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                if (data.isBlank()) {
                    findMax(calories);
                    calories = new ArrayList<>();
                } else {
                    calories.add(Integer.parseInt(data));
                }
            }

            System.out.println("MaxCallories: " + maxCalories);

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

    private void findMax(List<Integer> calories) {
        var currentSum = calories.stream()
                .mapToInt(Integer::intValue)
                .sum();

        if (currentSum > maxCalories) {
            maxCalories = currentSum;
        }
    }
}
