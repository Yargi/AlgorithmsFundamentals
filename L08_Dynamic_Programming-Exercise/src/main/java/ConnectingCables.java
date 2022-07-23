import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ConnectingCables {

    public static void main(String[] args) throws IOException {

        // open reader and build array from console input
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        int[] cables = Arrays.stream(rd.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        // close reader
        rd.close();

        // declare helper structures, length array and dp matrix
        int[] destinations = new int[cables.length];

        for (int i = 0; i < destinations.length; i++) {
            destinations[i] = i + 1;
        }

        int[][] dp = new int[cables.length + 1][cables.length + 1];

        // iterative fill dp matrix with data
        for (int i = 1; i <= cables.length; i++) {
            for (int j = 1; j <= cables.length; j++) {
                if (destinations[i - 1] == cables[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // get result from matrix and print to console
        System.out.println("Maximum pairs connected: " + dp[cables.length][cables.length]);
    }
}