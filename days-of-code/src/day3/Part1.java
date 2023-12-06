package day3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) {

        try {
            String filePath = "/Users/priyal/Downloads/input-day3.txt";
            int sum = calculatePartNumberSum(filePath);
            System.out.println("Sum: " + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculatePartNumberSum(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        Path path = Paths.get(filePath);
        List<String> allLines = Files.lines(path).collect(Collectors.toList());

        int sum = 0;
        int lineNumber = 0;
        while ((line = reader.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                char currentChar = line.charAt(i);

                if (Character.isDigit(currentChar)) {
                    // Check horizontally, vertically, and diagonally
                    StringBuilder numberStr = new StringBuilder();
                    int k = i;
                    while (k < line.length() && Character.isDigit(line.charAt(k))) {
                        numberStr.append(line.charAt(k));
                        k++;
                    }
                    boolean condition;
                    if (lineNumber == 0) {
                        condition = isValidPosition(line, i - 1) ||
                                isValidPosition(line, k) ||
                                isValidPosition(getLineBelow(filePath, lineNumber), i) ||
                                isValidPositionInBetween(getLineBelow(filePath, lineNumber), i, k) ||
                                isValidPosition(getLineBelow(filePath, lineNumber), k - 1) ||
                                isValidPosition(getLineBelow(filePath, lineNumber), i - 1) ||
                                isValidPosition(getLineBelow(filePath, lineNumber), k);

                    } else if (lineNumber == allLines.size() - 1) {
                        condition = isValidPosition(line, i - 1) ||
                                isValidPosition(line, k) ||
                                isValidPosition(getLineAbove(filePath, lineNumber), i) ||
                                isValidPositionInBetween(getLineAbove(filePath, lineNumber), i, k) ||
                                isValidPosition(getLineAbove(filePath, lineNumber), k - 1) ||
                                isValidPosition(getLineAbove(filePath, lineNumber), i - 1) ||
                                isValidPosition(getLineAbove(filePath, lineNumber), k);
                    } else {
                        condition = isValidPosition(line, i - 1) ||
                                isValidPosition(line, k) ||
                                isValidPosition(getLineAbove(filePath, lineNumber), i) ||
                                isValidPosition(getLineBelow(filePath, lineNumber), i) ||
                                isValidPositionInBetween(getLineAbove(filePath, lineNumber), i, k) ||
                                isValidPositionInBetween(getLineBelow(filePath, lineNumber), i, k) ||
                                isValidPosition(getLineAbove(filePath, lineNumber), k - 1) ||
                                isValidPosition(getLineBelow(filePath, lineNumber), k - 1) ||
                                isValidPosition(getLineAbove(filePath, lineNumber), i - 1) ||
                                isValidPosition(getLineBelow(filePath, lineNumber), i - 1) ||
                                isValidPosition(getLineAbove(filePath, lineNumber), k) ||
                                isValidPosition(getLineBelow(filePath, lineNumber), k);

                    }

                    if (condition) {

                        System.out.println("i = " + i + ";k = " + k + "; " + numberStr);
                        System.out.println("line number: " + lineNumber);


                        // Extracted the whole number and add to the sum
                        sum += Integer.parseInt(numberStr.toString());


                    }
                    i = k;
                    // Decrement i to process the current character in the next iteration
                    i--;
                }
            }
            lineNumber++;
        }

        reader.close();
        return sum;
    }

    private static boolean isValidPosition(String line, int position) {
        return position >= 0 && position < line.length() && line.charAt(position) != '.'  && !Character.isDigit(line.charAt(position));
    }

    private static boolean isValidPositionInBetween(String line, int start, int end) {
        if( start >= 0 && end < line.length()){
           for (int i = start; i <end; i++){
               if(line.charAt(i) != '.' && !Character.isDigit(line.charAt(i))){
                   return true;
               }
           }
        }
        return false;
    }

    private static String getLineAbove(String filePath, int currentLineNumber) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = "";
        for (int i = 0; i < currentLineNumber; i++) {
            line = reader.readLine();
        }
        reader.close();
        return line;
    }

    private static String getLineBelow(String filePath, int currentLineNumber) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        for (int i = 0; i <= currentLineNumber; i++) {
            reader.readLine();
        }
        String line = reader.readLine();
        reader.close();
        return line;
    }
}