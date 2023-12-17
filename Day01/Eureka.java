package Day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

class KMPSearch {
    public static int[] kmpSearch(String texto, String padrao) {
        int n = texto.length();
        int m = padrao.length();

        int[] lps = computeLPSArray(padrao);

        int i = 0;
        int j = 0;

        while (i < n) {
            if (padrao.charAt(j) == texto.charAt(i)) {
                i++;
                j++;

                if (j == m) {
                    int[] indices = { i - j, i - 1 };
                    return indices;
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return new int[0];
    }

    private static int[] computeLPSArray(String padrao) {
        int m = padrao.length();
        int[] lps = new int[m];
        int length = 0;
        int i = 1;

        while (i < m) {
            if (padrao.charAt(i) == padrao.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

}

public class Eureka {
    public static void main(String[] args) throws FileNotFoundException {
        int sum = 0;
        File file = new File("day01/input.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] patterns = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
            String strNew = strConverter(str);
            sum += calibrateLine(buildIndexesSet(strConverter(strNew), patterns), strNew);
        }
        sc.close();
        System.out.println(sum);

    }

    public static String getValue(char key) {
        Map<Character, String> hash = new HashMap<>();
        hash.put('1', "one");
        hash.put('2', "two");
        hash.put('3', "three");
        hash.put('4', "four");
        hash.put('5', "five");
        hash.put('6', "six");
        hash.put('7', "seven");
        hash.put('8', "eight");
        hash.put('9', "nine");

        return hash.get(key);
    }

    public static Integer getNumber(String word) {
        Map<String, Integer> wordToNumber = new HashMap<>();
        wordToNumber.put("one", 1);
        wordToNumber.put("two", 2);
        wordToNumber.put("three", 3);
        wordToNumber.put("four", 4);
        wordToNumber.put("five", 5);
        wordToNumber.put("six", 6);
        wordToNumber.put("seven", 7);
        wordToNumber.put("eight", 8);
        wordToNumber.put("nine", 9);

        return wordToNumber.get(word);
    }

    public static String strConverter(String str) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                sb.append(getValue(str.charAt(i)));
            } else {
                sb.append(str.charAt(i));
            }

        }
        return sb.toString();
    }

    public static List<List<Integer>> buildIndexesSet(String line, String[] padroes) {
        TreeSet<List<Integer>> indexesSet = new TreeSet<>((a, b) -> Integer.compare(a.get(0), b.get(0)));

        for (int i = 0; i < padroes.length; i++) {
            int[] arr = KMPSearch.kmpSearch(line, padroes[i]);
            if (arr.length > 0) {
                List<Integer> indexes = new ArrayList<>();
                indexes.add(arr[0]);
                indexes.add(arr[arr.length - 1]);
                indexesSet.add(indexes);
            }
        }
        return extractMinMaxIndexes(new ArrayList<>(indexesSet));
    }

    private static List<List<Integer>> extractMinMaxIndexes(List<List<Integer>> indexesList) {
        List<List<Integer>> result = new ArrayList<>();

        if (!indexesList.isEmpty()) {
            List<Integer> minIndexes = indexesList.get(0);
            List<Integer> maxIndexes = indexesList.get(indexesList.size() - 1);

            result.add(minIndexes);
            result.add(maxIndexes);
        }

        return result;
    }

    public static int concatenateAndConvertToInt(int n1, int n2) {
        String concatenatedString = String.valueOf(n1) + String.valueOf(n2);
        return Integer.parseInt(concatenatedString);
    }

    public static int calibrateLine(List<List<Integer>> indexList, String line) {
        if (indexList.get(0) == indexList.get(1)) {
            int firstIndexFirstList = indexList.get(0).get(0);
            int secondIndexFirstList = indexList.get(0).get(1);
            String firstDigit = line.substring(firstIndexFirstList, secondIndexFirstList + 1);
            return getNumber(firstDigit);
        }
        if (indexList.size() > 1) {
            int firstIndexFirstList = indexList.get(0).get(0);
            int secondIndexFirstList = indexList.get(0).get(1);
            int firstIndexSecondList = indexList.get(1).get(0);
            int secondIndexSecondList = indexList.get(1).get(1);

            String firstDigit = line.substring(firstIndexFirstList, secondIndexFirstList + 1);
            String secondDigit = line.substring(firstIndexSecondList, secondIndexSecondList + 1);

            int n1 = getNumber(firstDigit);
            int n2 = getNumber(secondDigit);

            return concatenateAndConvertToInt(n1, n2);
        } else {
            int firstIndexFirstList = indexList.get(0).get(0);
            int secondIndexFirstList = indexList.get(0).get(1);
            String firstDigit = line.substring(firstIndexFirstList, secondIndexFirstList + 1);
            int n1 = getNumber(firstDigit);
            return concatenateAndConvertToInt(n1, n1);
        }

    }

}
