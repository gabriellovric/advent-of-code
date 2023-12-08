import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {

        try {
            if (args.length != 1 || args[0] == null)
                throw new FileNotFoundException();

            var contents = Files.readString(Paths.get(args[0]));

            var parts = contents.split("\\n\\s*\\n");
            if (parts.length != 8) {
                throw new IOException("Invalid input file");
            }

            var seeds = parts[0].substring(7).split("\s+");
            var seedToSoil = parseMap(parts[1]);
            var soilToFertilizer = parseMap(parts[2]);
            var fertilizerToWater = parseMap(parts[3]);
            var waterToLight = parseMap(parts[4]);
            var lightToTemperature = parseMap(parts[5]);
            var temperatureToHumidity = parseMap(parts[6]);
            var humidityToLocation = parseMap(parts[7]);

            System.out.println(seeds.length);
            System.out.println(seedToSoil.length);
            System.out.println(soilToFertilizer.length);
            System.out.println(fertilizerToWater.length);
            System.out.println(waterToLight.length);
            System.out.println(lightToTemperature.length);
            System.out.println(temperatureToHumidity.length);
            System.out.println(humidityToLocation.length);

        } catch (FileNotFoundException e) {
            System.out.println("Usage: java Solution.java <input file>");
            return;
        } catch (IOException e) {
            System.out.println("Usage: java Solution.java <input file>");
            return;
        }

    }

    private static long[] mapToIntArray(String[] source) {
        return Arrays
                .stream(source)
                .map(String::trim)
                .mapToLong(Long::parseLong)
                .toArray();
    }

    private static long[][] parseMap(String text) {
        return Arrays
                .stream(text.split("\r?\n"))
                .skip(1)
                .map(x -> mapToIntArray(x.trim().split("\s+")))
                .toArray(long[][]::new);
    }
}
