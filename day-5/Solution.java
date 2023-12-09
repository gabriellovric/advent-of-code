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

            var seeds = mapToLongArray(parts[0].substring(7).split("\s+"));
            var seedToSoil = parseMap(parts[1]);
            var soilToFertilizer = parseMap(parts[2]);
            var fertilizerToWater = parseMap(parts[3]);
            var waterToLight = parseMap(parts[4]);
            var lightToTemperature = parseMap(parts[5]);
            var temperatureToHumidity = parseMap(parts[6]);
            var humidityToLocation = parseMap(parts[7]);

            long[] soils = mapValues(seeds, seedToSoil);
            long[] fertilizers = mapValues(soils, soilToFertilizer);
            long[] waters = mapValues(fertilizers, fertilizerToWater);
            long[] lights = mapValues(waters, waterToLight);
            long[] temperatures = mapValues(lights, lightToTemperature);
            long[] humidities = mapValues(temperatures, temperatureToHumidity);
            long[] locations = mapValues(humidities, humidityToLocation);

            long min = Arrays.stream(locations).min().getAsLong();

            System.out.println(min);

        } catch (FileNotFoundException e) {
            System.out.println("Usage: java Solution.java <input file>");
            return;
        } catch (IOException e) {
            System.out.println("Usage: java Solution.java <input file>");
            return;
        }

    }

    private static long[] mapValues(long[] values, long[][] map) {
        long[] mapped = new long[values.length];

        for (int i = 0; i < values.length; i++) {

            mapped[i] = -1;

            for (long[] mapping : map) {
                if (values[i] >= mapping[1] && values[i] < mapping[1] + mapping[2]) {
                    mapped[i] = mapping[0] + values[i] - mapping[1];
                    break;
                }
            }

            if (mapped[i] == -1) {
                mapped[i] = values[i];
            }

        }

        return mapped;
    }

    private static long[] mapToLongArray(String[] source) {
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
                .map(x -> mapToLongArray(x.trim().split("\s+")))
                .toArray(long[][]::new);
    }
}
