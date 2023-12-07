import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {

        try {
            if (args.length != 1 || args[0] == null)
                throw new FileNotFoundException();

            var file = new File(args[0]);
            var scanner = new Scanner(file);
            var pattern = Pattern.compile("(\\d+|[^\\.0-9])");


            var lines = new ArrayList<String>();
            var numbers = new ArrayList<int[]>();
            var symbols = new ArrayList<int[]>();
            var ratioSum = 0;
            var sum = 0;

            for (int i = 0; scanner.hasNextLine(); i++) {
                var line = scanner.nextLine();
                lines.add(line);

                System.out.print(line + " | ");

                var matcher = pattern.matcher(line);
                while (matcher.find()) {
                    for (int j = 0; j < matcher.groupCount(); j++) {
                        var start = matcher.start(j);
                        var end = matcher.end(j);
                        var match = matcher.group(j);

                        if (match.length() == 1 && !Character.isDigit(match.charAt(0))) {
                            int[] pair = { i, start };
                            symbols.add(pair);
                        } else {
                            int[] triple = { i, start, end, Integer.parseInt(match), 0 };
                            numbers.add(triple);
                        }

                        System.out.print("[" + start + "-" + end + "]: " + match + " ");
                    }
                }

                System.out.println();
            }

            scanner.close();

            for (var symbol : symbols) {
                var i = symbol[0];
                var j = symbol[1];

                var line = lines.get(i);

                var lineStart = i == 0 ? 0 : i - 1;
                var lineEnd = i == lines.size() - 1 ? i : i + 1;

                var positionStart = j == 0 ? 0 : j - 1;
                var positionEnd = j == line.length() - 1 ? j : j + 1;

                System.out.println("[" + i + "," + j + "]" + "[" + lineStart + "," + lineEnd + "][" + positionStart
                        + "," + positionEnd + "]");

                var currentPartials = new ArrayList<Integer>();

                for (int ii = lineStart; ii <= lineEnd; ii++) {
                    for (int jj = positionStart; jj <= positionEnd; jj++) {

                        if (Character.isDigit(lines.get(ii).charAt(jj))) {
                            System.out.println("Partial number found at line: " + ii + " position: " + jj);

                            for (var num : numbers) {
                                var ln = num[0];
                                var pos = num[1];
                                var end = num[2];
                                var number = num[3];
                                var isPartial = num[4];

                                if (isPartial == 0 && ln == ii && jj >= pos && jj < end) {
                                    num[4] = 1;
                                    sum += number;
                                    currentPartials.add(number);
                                }
                            }

                        }
                    }
                }

                if (line.charAt(j) == '*' && currentPartials.size() == 2) {
                    System.out.println("---------------------------gear found");

                    var ratio = currentPartials.get(0) * currentPartials.get(1);

                    ratioSum += ratio;
                }

            }

            System.out.println("Sum: " + sum);
            System.out.println("Ratio sum: " + ratioSum);

        } catch (FileNotFoundException e) {
            System.out.println("Usage: java Solution.java <input file>");
            return;
        }
    }
}
