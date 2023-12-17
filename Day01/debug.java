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

public class debug {
    public static void main(String[] args) throws FileNotFoundException {
       
        String[] numbersInWords = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        // String line = "7pqrstsixteen";
        // List<List<Integer>> indexSet = buildIndexesSet(line, numbersInWords);
        // if (indexSet.size() > 2) {
        //          indexSet = minMax(indexSet);
        //     }
        // System.out.println(calibrateLine(line, indexSet, getIntVal(line)));

        File file = new File("Day01/input.txt");
        Scanner sc = new Scanner(file);
        int sum = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            int[] arr = getIntVal(line);
            List<List<Integer>> indexSet = buildIndexesSet(line, numbersInWords);
            if (indexSet.size() > 2) {
                indexSet = minMax(indexSet);
            }
            System.out.println(sum);
            sum += calibrateLine(line, indexSet, arr);
        }
        System.out.println(sum);
        sc.close();

    }

    public static List<List<Integer>> minMax(List<List<Integer>> indexSet) {
        List<List<Integer>> output = new ArrayList<>();
        output.add(indexSet.get(0));
        output.add(indexSet.get(indexSet.size() - 1));
        return output;
    }

    public static int calibrateLine(String line, List<List<Integer>> indexesSet, int[] intIndex) {
        if (indexesSet.isEmpty()) {
            if (intIndex[1] == 0) {
                return Character.getNumericValue(line.charAt(intIndex[0]));
            } else {
                return concatenarNumeros(Character.getNumericValue(line.charAt(intIndex[0])),
                        Character.getNumericValue(line.charAt(intIndex[1])));
            }
        }
        if (!indexesSet.isEmpty() && indexesSet.size() == 1 && intIndex == null) {
            int n1L1 = indexesSet.get(0).get(0);
            int n2L1 = indexesSet.get(0).get(1);
            int firstStrNumber = getValue(line.substring(n1L1, n2L1 + 1));
            return firstStrNumber;
        } else if (!indexesSet.isEmpty() && indexesSet.size() > 1 && intIndex == null) {
            int n1L1 = indexesSet.get(0).get(0);
            int n2L1 = indexesSet.get(0).get(1);
            int n1L2 = indexesSet.get(1).get(0);
            int n2L2 = indexesSet.get(1).get(1);
            int firstStrNumber = getValue(line.substring(n1L1, n2L1 + 1));
            int secondStrNumber = getValue(line.substring(n1L2, n2L2 + 1));
            return concatenarNumeros(firstStrNumber, secondStrNumber);
        } else if (!indexesSet.isEmpty() && indexesSet.size() == 1) {
            int n1L1 = indexesSet.get(0).get(0);
            int n2L1 = indexesSet.get(0).get(1);
            int firstStrNumber = getValue(line.substring(n1L1, n2L1 + 1));
            if (intIndex[1] == 0) {
                int numero = Character.getNumericValue(line.charAt(intIndex[0]));
                if (intIndex[0] < n1L1) {
                    return concatenarNumeros(numero, firstStrNumber);
                } else {
                    return concatenarNumeros(firstStrNumber, numero);
                }
            } else {
                if (intIndex[1] < n2L1) {
                    int numero1 = Character.getNumericValue(line.charAt(intIndex[0]));
                    return concatenarNumeros(numero1, firstStrNumber);

                } else {
                    int numero1 = Character.getNumericValue(line.charAt(intIndex[0]));
                    int numero2 = Character.getNumericValue(line.charAt(intIndex[1]));
                    return concatenarNumeros(numero1, numero2);
                }
            }

        }
        if (indexesSet.size() > 1 && intIndex == null) {
            int n1L1 = indexesSet.get(0).get(0);
            int n2L1 = indexesSet.get(0).get(1);
            int n1L2 = indexesSet.get(1).get(0);
            int n2L2 = indexesSet.get(1).get(1);
            int firstStrNumber = getValue(line.substring(n1L1, n2L1 + 1));
            int secondStrNumber = getValue(line.substring(n1L2, n2L2 + 1));
            return concatenarNumeros(firstStrNumber, secondStrNumber);
        } else {
            int n1L1 = indexesSet.get(0).get(0);
            int n2L1 = indexesSet.get(0).get(1);
            int n1L2 = indexesSet.get(1).get(0);
            int n2L2 = indexesSet.get(1).get(1);
            int firstStrNumber = getValue(line.substring(n1L1, n2L1 + 1));
            int secondStrNumber = getValue(line.substring(n1L2, n2L2 + 1));
            if (intIndex[0] == intIndex[1]) {
                if (intIndex[0] > n1L1) {
                    return concatenarNumeros(firstStrNumber, secondStrNumber);
                } else if (n1L1 > intIndex[0]) {
                    int numero1 = Character.getNumericValue(line.charAt(intIndex[0]));
                    return concatenarNumeros(numero1, secondStrNumber);
                }
            } else {
                if (intIndex[0] < n1L1 && intIndex[1] > n2L2) {
                    int numero1 = Character.getNumericValue(line.charAt(intIndex[0]));
                    int numero2 = Character.getNumericValue(line.charAt(intIndex[1]));
                    return concatenarNumeros(numero1, numero2);
                }
                else if (n1L1 < intIndex[0] && intIndex[1] > n2L2) {
                    int numero2 = Character.getNumericValue(line.charAt(intIndex[1]));
                    return concatenarNumeros(firstStrNumber, numero2);
                }
            }

        }

        return 0;
    }

    public static int concatenarNumeros(int num1, int num2) {
        // Convertendo o segundo número para uma string
        String num2Str = String.valueOf(num2);

        // Concatenando as strings dos dois números
        String concatenacaoStr = num1 + num2Str;

        // Convertendo a string resultante de volta para um número inteiro
        int resultado = Integer.parseInt(concatenacaoStr);

        return resultado;
    }

    public static List<List<Integer>> buildIndexesSet(String line, String[] padroes) {
        TreeSet<List<Integer>> indexesSet = new TreeSet<>((a, b) -> Integer.compare(a.get(0), b.get(0)));

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
                jval = true;
            }
            if (Character.isDigit(line.charAt(k)) && !kval) {
                result[1] = line.charAt(k);
                finalk = k;
                kval = true;
            }

            j++;
            k--;
        }

        // Verificar se nenhum número foi encontrado
        if (!jval && !kval) {
            return null;
        }

        int[] ans = { finalJ, finalk };
        return ans;
    }

}
