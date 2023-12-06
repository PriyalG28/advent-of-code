package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {

    public static void main(String[] args) throws IOException {
        try {
            // Read File
            BufferedReader reader = new BufferedReader(new FileReader("/Users/priyal/Downloads/input-day2.txt"));

            // Variable Declaration
            String line;
            String trimmedLine;
            int sum = 0;

            // Read lines of file
            while ((line = reader.readLine()) != null) {
                trimmedLine = line.trim();

                // Check if game is valid; parse game id and add it
                if (isValidGame(trimmedLine)) {
                    sum += getGameId(trimmedLine);
                }
            }

            //Print Output
            System.out.println("Output sum is: " + sum);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static boolean isValidGame(String line) {
        String[] draws = line.split(": ")[1].trim().split(";");

        // Initialize variables
        int maxRed = 12, maxGreen = 13, maxBlue = 14;
        int redCount, greenCount, blueCount;
        int count;
        String[] parts;
        String[] cubes;

        for (String draw : draws) {

            // initialize it with zero
            redCount = greenCount = blueCount = 0;

            cubes = draw.trim().split(", ");
            for (String cube : cubes) {
                parts = cube.trim().split(" ");
                count = Integer.parseInt(parts[0].trim());
                String color = parts[1].trim();

                switch (color) {
                    case "red" -> redCount += count;
                    case "green" -> greenCount += count;
                    case "blue" -> blueCount += count;
                }
            }

            if (redCount > maxRed || greenCount > maxGreen || blueCount > maxBlue) {
                return false;
            }
        }

        return true;
    }

    private static int getGameId(String gameInput) {
        return Integer.parseInt(gameInput.split(":")[0].substring(5).trim());
    }

}
