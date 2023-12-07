package Day01;

import java.util.Arrays;

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


public class Reborn {

    public static void main(String[] args) {
        String[] numbersInWords =
                {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        String line = "nineone";
        String last = "";
        String first = "";

        for (int i = 0; i < numbersInWords.length; i++) {
            int lastaux = -1;
            lastaux = line.lastIndexOf(numbersInWords[i]);
        }

    }


    public static boolean verifyVet(int[] res) {
        return res != null && res.length > 0;// true se nao for empty
    }
}
