package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader read = new BufferedReader(new FileReader("/Users/priyal/Downloads/input.txt"));) {
            String line;
            int sum = 0;
            String trimmedLine = "";

            while ((line = read.readLine()) != null) {
                trimmedLine = line.trim();
                trimmedLine = replaceWordNums(trimmedLine);
                //replace all non-digit chars
                String allInts = trimmedLine.replaceAll("[^0-9]", "");

                char[] numArray = allInts.toCharArray();
                String firstAndLast = "" + numArray[0] + numArray[numArray.length - 1];
                sum += Integer.parseInt(firstAndLast);
            }

            System.out.println("SUM: " + sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String replaceWordNums(String line) {
        //TODO: Try with map approach
        String[] searchList = new String[]{"oneight", "twone", "eightwo","zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] replacementList = new String[]{"18", "21", "82", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        return replaceEach(line, searchList, replacementList);
    }

    public static String replaceEach(String line, String[] searchList, String[] replacementList) {
        if (searchList.length != replacementList.length) {
            throw new IllegalArgumentException("Search and replacement lists must have the same length");
        }

        for (int i = 0; i < searchList.length; i++) {
            line = line.replace(searchList[i], replacementList[i]);
        }

        return line;
    }
}