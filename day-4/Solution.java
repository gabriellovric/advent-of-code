import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {

        try {
            if (args.length != 1 || args[0] == null)
                throw new FileNotFoundException();

            var file = new File(args[0]);
            var scanner = new Scanner(file);
            var sumOfPoints = 0;

            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();

                var numbers = line.substring(line.indexOf(":") + 1);
                var lists = numbers.split("\\|");

                var winningNumbers = lists[0].trim().split("\s+");
                var ownNumbers = lists[1].trim().split("\s+");

                var points = 0;

                for (var winningNumber : winningNumbers) {
                    for (var ownNumber : ownNumbers) {
                        if (ownNumber.equals(winningNumber)) {
                            points = points == 0 ? 1 : points * 2;
                        }
                    }
                }

                sumOfPoints += points;

                System.out.println("Points: " + points);
            }

            System.out.println("Sum of Points: " + sumOfPoints);

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Usage: java Solution.java <input file>");
            return;
        }
    }
}
