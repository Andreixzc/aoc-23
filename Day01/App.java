package Day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("Day01/input.txt");
        Scanner sc = new Scanner(file);
        int sum = 0;
        while (sc.hasNext()) {
            sum += solve(sc.nextLine());
            System.out.println(sum);
        }
        System.out.println(sum);

        sc.close();
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
        return Integer.parseInt(sb.toString());
    }
}