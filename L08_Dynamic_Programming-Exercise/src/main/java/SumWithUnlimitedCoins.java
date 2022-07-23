import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SumWithUnlimitedCoins {

    public static void main(String[] args) throws IOException {

        // open reader, declare variables and read input from console, close reader
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        int[] coins = Arrays.stream(rd.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int targetSum = Integer.parseInt(rd.readLine());

        rd.close();

        // declare array for dynamic programming
        int[] dp = new int[targetSum + 1];

        dp[0] = 1;

        // calculate dp values iterative
        for (int coin : coins) {

            for (int j = coin; j <= targetSum; j++) {

                dp[j] += dp[j - coin];
            }
        }

        // get result out of dp table and print to console
        System.out.println(dp[targetSum]);
    }
}