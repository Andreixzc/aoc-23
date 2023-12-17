package Day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class M {
    public static void main(String[] args) throws FileNotFoundException {
        int sum = 0;
        File file = new File("day01/input.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            sum += solve(convLine(sc.nextLine()));
        }
        System.out.println(sum);
        sc.close();
    }

    public static String convLine(String str) {
        String[] intPatterns = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String[] patterns = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        for (int i = 0; i < intPatterns.length; i++) {
            str = str.replaceAll(patterns[i], intPatterns[i]);
        }
        return str;
    }

    public static int solve(String line) {
        char[] result = new char[2];
        int j = 0;
        boolean kval = false;
        boolean jval = false;
        int k = line.length() - 1;
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(j)) && !jval) {
                result[0] = line.charAt(j);
                jval = true; // Corrected to set jval to true
            }
            if (Character.isDigit(line.charAt(k)) && !kval) {
                result[1] = line.charAt(k);
                kval = true;
            }

            j++;
            k--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(result[i]);
        }
        System.out.println(sb.toString());
        return Integer.parseInt(sb.toString());
    }
}
