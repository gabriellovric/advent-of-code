import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.management.InvalidApplicationException;

public class Solution {
    public static void main(String[] args) {

        try {
            if (args.length != 1 || args[0] == null)
                throw new FileNotFoundException();

            var scanner = new Scanner(new File(args[0]));

            var seeds = readSeeds(scanner);
            var seedToSoil = readSeedToSoil(scanner);

        } catch (Exception e) {
            System.out.println("Usage: java Solution.java <input file>");
            return;
        }
    }

    private static int[] readSeeds(Scanner scanner) throws Exception {
        if (!scanner.hasNextLine()) {
            throw new Exception("Invalid input file");
        }

        var line = scanner.nextLine();
        if (!line.startsWith("seeds: ")) {
            throw new Exception("Invalid input file");
        }

        var seeds = line.substring(7).split(" ");
        return Arrays.stream(seeds).mapToInt(Integer::parseInt).toArray();
    }

    private static int[] readSeedToSoil(Scanner scanner) throws Exception {
        if (!scanner.hasNextLine()) {
            throw new Exception("Invalid input file");
        }

        var line = scanner.nextLine();
        if (!line.equals("seed-to-soil map:")) {
            throw new Exception("Invalid input file");
        }

        while (!line.isEmpty()) {
            line = scanner.nextLine();
        }

        var seeds = line.substring(7).split(" ");
        return Arrays.stream(seeds).mapToInt(Integer::parseInt).toArray();
    }
}
