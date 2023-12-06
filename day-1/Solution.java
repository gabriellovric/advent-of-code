import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        String[] lookup = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            int sum = 0;

            while (line != null) {
                int firstDigit = -1;
                int lastDigit = -1;

                for (char c : line.toCharArray()) {
                    if (c >= '0' && c <= '9') {
                        if (firstDigit == -1) {
                            firstDigit = c - '0';
                            lastDigit = firstDigit;
                        } else {
                            lastDigit = c - '0';
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
