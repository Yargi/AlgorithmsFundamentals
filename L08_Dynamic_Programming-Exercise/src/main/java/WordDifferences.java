import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WordDifferences {

    public static void main(String[] args) throws IOException {

        // open reader, get input variables, close reader
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        char[] first = rd.readLine().toCharArray();
        char[] second = rd.readLine().toCharArray();

        rd.close();

        // declare dp table as matrix
        int[][] dp = new int[first.length + 1][second.length + 1];

        // iterative filling dp table with data
        for (int i = 0; i <= first.length; i++) {
            for (int j = 0; j <= second.length; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (first[i - 1] == second[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        // get the solution and print to console
        System.out.println("Deletions and Insertions: " + dp[first.length][second.length]);
    }
}