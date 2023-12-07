import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        String[] lookup = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

        BufferedReader reader;

        try {
            // reader = new BufferedReader(new FileReader("testing.txt"));
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            int sum = 0;

            while (line != null) {
                int firstDigit = -1;
                int lastDigit = -1;

                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    int match = -1;

                    if (c >= '0' && c <= '9') {
                        match = c - '0';
                    } else {
                        for (int j = 0; j < lookup.length; j++) {
                            String rest = line.substring(i);
                            if (rest.startsWith(lookup[j])) {
                                match = j;
                            }
                        }
                    }

                    if (match != -1) {
                        if (firstDigit == -1) {
                            firstDigit = match;
                            lastDigit = match;
                        } else {
                            lastDigit = match;
                        }
                    }

                }
                sum += firstDigit * 10 + lastDigit;

                System.out.println(line + " -> " + firstDigit + lastDigit);

                line = reader.readLine();
            }

            System.out.println("Sum: " + sum);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
