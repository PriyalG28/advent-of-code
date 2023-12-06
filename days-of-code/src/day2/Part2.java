package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {


    public static void main(String[] args) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/Users/priyal/Downloads/input-day2-part-2.txt"));

            String line;
            int sum = 0;
            String trimmedLine = "";

            while ((line = reader.readLine()) != null) {
                trimmedLine = line.trim();
                sum += minimumCubeCount(trimmedLine);
            }
            System.out.println(sum);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int minimumCubeCount(String line) {
        String[] games = line.split(": ")[1].split(";");
        int countBlue = 0;
        int countRed = 0;
        int countGreen = 0;
        String num;
        String color;
        for (String game : games) {
            String[] draws = game.split(",");
            for (String draw : draws) {
                num = draw.trim().split(" ")[0].trim();
                color = draw.trim().split(" ")[1].trim();
                if (color.equalsIgnoreCase("blue")) {
                    if (Integer.parseInt(num) > countBlue) {
                        countBlue = Integer.parseInt(num);
                    }
                } else if (color.equalsIgnoreCase("green")) {
                    if (Integer.parseInt(num) > countGreen) {
                        countGreen = Integer.parseInt(num);
                    }
                } else if (color.equalsIgnoreCase("red")) {
                    if (Integer.parseInt(num) > countRed) {
                        countRed = Integer.parseInt(num);
                    }
                }

            }

        }

        return countBlue * countGreen * countRed;

    }

}
