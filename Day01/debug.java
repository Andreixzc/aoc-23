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

        int i = 0;
        int j = 0;

        while (i < n) {
            if (padrao.charAt(j) == texto.charAt(i)) {
                i++;
                j++;

                if (j == m) {
                    int[] indices = {i - j, i - 1};
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


public class debug {
    public static void main(String[] args) throws FileNotFoundException {
        int sum = 0;
        File file = new File("Day01/input.txt");
        String[] numbersInWords =
                {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String line = "onetwo";
        int[] arr = getIntVal(line);
        List<List<Integer>> indexSet = buildIndexesSet(line, numbersInWords);
        System.out.println(Arrays.toString(arr));
        System.out.println(indexSet);
        System.out.println(calibrateLine(line, indexSet, arr));

    }


    public static int calibrateLine(String line, List<List<Integer>> indexesSet, int[] intIndex) {
        int n1 = 0;
        int n2 = 0;
        if (indexesSet.isEmpty() && !verifyVet(intIndex)) {
            return 0;
        } else if (indexesSet.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < intIndex.length; i++) {
                sb.append(line.indexOf(intIndex[i]));
            }
            return Integer.parseInt(sb.toString());
        } else {
            if (indexesSet.size() == 1) {
                int begStringIndex = indexesSet.get(0).get(0);
                if (begStringIndex < intIndex[0]) {
                    n1 = getValue(
                            line.substring(indexesSet.get(0).get(0), indexesSet.get(0).get(1) + 1));
                } else {
                    n1 = line.charAt(intIndex[0]);
                }
                if (begStringIndex > intIndex[1]) {
                    n2 = getValue(
                            line.substring(indexesSet.get(0).get(0), indexesSet.get(0).get(1) + 1));
                } else {
                    n2 = line.charAt(intIndex[1]);
                }
            } else {
                int begStringIndex = indexesSet.get(0).get(0);
                int endStringIndex = indexesSet.get(1).get(0);
                if (begStringIndex < intIndex[0]) {
                    n1 = getValue(
                            line.substring(indexesSet.get(0).get(0), indexesSet.get(0).get(1) + 1));
                } else {
                    n1 = line.charAt(intIndex[0]);
                }
                if (endStringIndex > intIndex[1]) {
                    n2 = getValue(
                            line.substring(indexesSet.get(0).get(0), indexesSet.get(0).get(1) + 1));
                } else {
                    n2 = line.charAt(intIndex[1]);
                }
            }

        }
        String concatenatedString = Integer.toString(n1) + Integer.toString(n2);
        int result = Integer.parseInt(concatenatedString);


        return result;
    }

    public static List<List<Integer>> buildIndexesSet(String line, String[] padroes) {
        TreeSet<List<Integer>> indexesSet =
                new TreeSet<>((a, b) -> Integer.compare(a.get(0), b.get(0)));

        for (int i = 0; i < padroes.length; i++) {
            int[] arr = KMPSearch.kmpSearch(line, padroes[i]);
            if (verifyVet(arr)) {
                if (arr.length > 0) {
                    List<Integer> indexes = new ArrayList<>();
                    indexes.add(arr[0]);
                    indexes.add(arr[arr.length - 1]);
                    indexesSet.add(indexes);
                }
            }
        }

        return new ArrayList<>(indexesSet);
    }

    private static class ListComparator implements Comparator<List<Integer>> {
        @Override
        public int compare(List<Integer> o1, List<Integer> o2) {
            return Integer.compare(o1.get(0), o2.get(0));
        }
    }

    public static boolean verifyVet(int[] res) {
        return res != null && res.length > 0;// true se nao for empty
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
        int[] ans = {finalJ, finalk};
        return ans;
    }

}
