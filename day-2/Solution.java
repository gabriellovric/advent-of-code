import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        String[] lookup = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

        BufferedReader reader;

        try {

            try {
                if (args.length != 1 || args[0] == null)
                    throw new FileNotFoundException();

                reader = new BufferedReader(new FileReader(args[0]));
            } catch (FileNotFoundException e) {
                System.out.println("Usage: java Solution.java <input file>");
                return;
            }

            String line = reader.readLine();
            int sum = 0;

            while (line != null) {
                var a = line.indexOf(":");
                var id = Integer.parseInt(line.substring(5, a));
                var selections = line.substring(a + 2).split(";");

                boolean possible = true;

                System.out.println("(" + id + ")");

                // 12 red cubes, 13 green cubes, and 14 blue cubes
                for (var s : selections) {
                    var cubes = s.split(",");
                    for (var ss : cubes) {
                        var aaaa = ss.trim();

                        var asd = aaaa.split(" ");
                        String color = asd[1];
                        int quantity = Integer.parseInt(asd[0]);

                        System.out.print("(" + quantity + "|" + color + ") ");

                        if ((color.equals("red") && quantity > 12)
                                || (color.equals("green") && quantity > 13)
                                || (color.equals("blue") && quantity > 14)) {
                            possible = false;
                            break;
                        }
                    }

                    System.out.println("");
                    if (!possible) {
                        break;
                    }
                }

                if (possible) {
                    sum += id;
                }

                System.out.println();
                line = reader.readLine();
            }

            System.out.println("Sum: " + sum);

            reader.close();
        } catch (

        IOException e) {
            e.printStackTrace();
        }
    }
}
