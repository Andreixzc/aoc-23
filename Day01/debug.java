package Day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class KMPSearch {
    public static int[] kmpSearch(String texto, String padrao) {
        int n = texto.length();
        int m = padrao.length();

        int[] lps = computeLPSArray(padrao);

        int i = 0; // Índice para 'texto'
        int j = 0; // Índice para 'padrao'

        while (i < n) {
            if (padrao.charAt(j) == texto.charAt(i)) {
                i++;
                j++;

                if (j == m) {
                    // Padrão encontrado, retorna os índices de início e fim
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
        String[] numbersInWords =
                {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        int sum = 0;
        String line = "two1nine";
        HashSet<List<Integer>> indexes = buildIndexesSet(line, numbersInWords);
        int tmp1 = 0;
        int tmp2 = 0;
        int[] firstOcurrency = new int[2];
        int[] lastOcurrency = new int[2];
        int[] intOcorrencys = getIntVal(line);
        getMaxMinIndex(firstOcurrency, lastOcurrency, indexes);
        if (verifyVet(firstOcurrency)) {
            if (firstOcurrency[0] < intOcorrencys[0]) {
                tmp1 = getValue(line.substring(firstOcurrency[0], firstOcurrency[1] + 1));
            } else {
                tmp1 = Character.getNumericValue(line.charAt(intOcorrencys[0]));
            }
            if (lastOcurrency[0] > intOcorrencys[1]) {
                tmp2 = getValue(line.substring(lastOcurrency[0], lastOcurrency[1] + 1));
            } else {
                tmp2 = Character.getNumericValue(line.charAt(intOcorrencys[1]));
            }
            sum += Integer.parseInt(String.valueOf(tmp1) + String.valueOf(tmp2));

        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < intOcorrencys.length; i++) {
                sb.append(intOcorrencys[i]);
            }
            sum += Integer.parseInt(sb.toString());
        }
        System.out.println(sum);

    }



    public static boolean verifyVet(int[] vetor) {
        for (int elemento : vetor) {
            if (elemento != 0) {
                return true;
            }
        }
        return false;
    }

    public static HashSet<List<Integer>> buildIndexesSet(String str, String[] patterns) {
        HashSet<List<Integer>> indexes = new HashSet<>();
        for (int i = 0; i < patterns.length; i++) {
            for (int j = i + 1; j < patterns.length; j++) {
                int[] result = KMPSearch.kmpSearch(str, patterns[i]);
                if (result.length > 0) {
                    List<Integer> indicesList = Arrays.asList(result[0], result[1]);
                    if (!indexes.contains(indicesList)) {
                        indexes.add(indicesList);
                    }
                }
            }
        }
        return indexes;
    }

    public static void getMaxMinIndex(int[] firstOcurrency, int[] lastOcurrency,
            HashSet<List<Integer>> indexes) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (List<Integer> indicesList : indexes) {
            if (indicesList.get(0) < min) {
                min = indicesList.get(0);
                for (int i = 0; i < firstOcurrency.length; i++) {
                    firstOcurrency[i] = indicesList.get(i);
                }

            }
            if (indicesList.get(0) > max) {
                max = indicesList.get(0);
                for (int i = 0; i < lastOcurrency.length; i++) {
                    lastOcurrency[i] = indicesList.get(i);
                }
            }
        }

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
