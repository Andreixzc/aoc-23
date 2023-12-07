package Day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

class KMPSearch {
    public static int[] kmpSearch(String texto, String padrao) {
        int n = texto.length();
        int m = padrao.length();

        int[] lps = computeLPSArray(padrao);

        int i = 0; //
        int j = 0; //

        while (i < n) {
            if (padrao.charAt(j) == texto.charAt(i)) {
                i++;
                j++;

                if (j == m) {
                    // Padrão encontrado, retorna os índices de início e fim
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

        // Padrão não encontrado, retorna um array vazio
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

public class debug {
    public static void main(String[] args) throws FileNotFoundException {
        String[] numbersInWords = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        String line = "sevenine";
        List<List<Integer>> result = buildIndexesSet(line, numbersInWords);
        int[] numberIndexes = getIntVal(line);
        System.out.println( result.get(0));
    }

    public static List<List<Integer>> buildIndexesSet(String line, String[] padroes) {
        List<Integer> lowestIndexes = new ArrayList<>();
        List<Integer> highestIndexes = new ArrayList<>();

        for (int i = 0; i < padroes.length; i++) {
            int[] arr = KMPSearch.kmpSearch(line, padroes[i]);
            if (verifyVet(arr)) {
                // If there are matching indexes, update the lowest and highest indexes
                if (arr.length > 0) {
                    lowestIndexes.add(arr[0]);
                    highestIndexes.add(arr[arr.length - 1]);
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(lowestIndexes);
        result.add(highestIndexes);
        return result;
    }

    private static class ListComparator implements Comparator<List<Integer>> {
        @Override
        public int compare(List<Integer> o1, List<Integer> o2) {
            // Implement your custom comparison logic here
            // For example, compare based on the first element
            return Integer.compare(o1.get(0), o2.get(0));
        }
    }

    public static boolean verifyVet(int[] res) {
        return res != null && res.length > 0;
    }

    public static int getValue(String key) {
        Map<String, Integer> hash = new HashMap<>();
        hash.put("zero", 0);
        hash.put("one", 1);
        hash.put("two", 2);
        hash.put("three", 3);
        hash.put("four", 4);
        hash.put("five", 5);
        hash.put("six", 6);
        hash.put("seven", 7);
        hash.put("eight", 8);
        hash.put("nine", 9);
        return hash.get(key);
    }

    public static int[] getIntVal(String line) {
        char[] result = new char[2];
        int j = 0;
        boolean kval = false;
        boolean jval = false;
        int finalk = 0;
        int finalJ = 0;
        int k = line.length() - 1;
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(j)) && !jval) {
                result[0] = line.charAt(j);
                finalJ = j;
                jval = true; // Corrected to set jval to true
            }
            if (Character.isDigit(line.charAt(k)) && !kval) {
                result[1] = line.charAt(k);
                finalk = k;
                kval = true;
            }

            j++;
            k--;
        }
        int[] ans = { finalJ, finalk };
        return ans;
    }

}
