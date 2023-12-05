package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new FileReader("/Users/priyal/Downloads/input.txt"));
        String line;
        String trimmedLine;
        int sum = 0;
        while ((line = read.readLine()) != null) {
            trimmedLine = line.trim();
            sum += getNumber(trimmedLine);
        }
        System.out.println(sum);
    }

    private static int  getNumber(String word) {
        StringBuilder digits = new StringBuilder();

        // Extract digits from the line
        for (char c : word.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.append(c);
            }
        }

        // Combine the first and last digits to form the two-digit number
        int calibrationValue = Integer.parseInt(digits.substring(0, 1) + digits.substring(digits.length() - 1));

        return calibrationValue;
    }

}
