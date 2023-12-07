import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {

        try {
            if (args.length != 1 || args[0] == null)
                throw new FileNotFoundException();

            var file = new File(args[0]);
            var scanner = new Scanner(file);
            var sumOfPoints = 0;
            var listOfMatches = new ArrayList<Integer>();
            var instances = new ArrayList<Integer>();

            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                instances.add(1);

                var numbers = line.substring(line.indexOf(":") + 1);
                var lists = numbers.split("\\|");

                var winningNumbers = lists[0].trim().split("\s+");
                var ownNumbers = lists[1].trim().split("\s+");

                var points = 0;
                var count = 0;

                for (var winningNumber : winningNumbers) {
                    for (var ownNumber : ownNumbers) {
                        if (ownNumber.equals(winningNumber)) {
                            count++;
                            points = points == 0 ? 1 : points * 2;
                        }
                    }
                }

                listOfMatches.add(count);
                sumOfPoints += points;

                System.out.println("Points: " + points);
                System.out.println("Count: " + count);
            }

            for (int i = 0; i < listOfMatches.size(); i++) {
                var match = listOfMatches.get(i);
                var amount = instances.get(i);

                for (int j = 0; j < match; j++) {
                    var count = instances.get(i + j + 1);
                    instances.set(i + j + 1, count + amount);
                }
            }

            
            var sumOfInstances = instances.stream().mapToInt(Integer::intValue).sum();
            for (int i = 0; i < listOfMatches.size(); i++) {
                System.out.println(listOfMatches.get(i) + "," + instances.get(i));
            }

            System.out.println("Sum of Points: " + sumOfPoints);
            System.out.println("Sum of Instances: " + sumOfInstances);


            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Usage: java Solution.java <input file>");
            return;
        }
    }
}
