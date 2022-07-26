import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NuclearWaste {

    public static void main(String[] args) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        int[] prices = Arrays.stream(rd.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int n = Integer.parseInt(rd.readLine());

        rd.close();

        //int[] prices = {12, 21, 31, 40, 49, 58, 69, 101};
        //int n = 15;

        int[] dp = new int[n + 1];
        int[] previous = new int[n + 1];

        String result = calculatePrice(n, prices, dp, previous);

        System.out.println(result);
    }

    private static String calculatePrice(int n, int[] prices, int[] dp, int[] previous) {

        for (int i = 1; i <= n; i++) {

            dp[i] = Integer.MAX_VALUE;

            for (int j = 1; j <= i; j++) {

                if (j > prices.length) {

                    break;
                }

                int current = dp[i - j] + prices[j - 1];
                if (current < dp[i]) {

                    dp[i] = current;
                    previous[i] = j;
                }
            }
        }

        String result = BuildResultText(dp, previous, prices, n);

        return result;
    }

    private static String BuildResultText(int[] dp, int[] previous, int[] prices, int n) {

        StringBuilder out = new StringBuilder();

        out.append("Cost: ").append(dp[n]).append(System.lineSeparator());

        while (n > 0) {
            out.append(previous[n]).append(" => ").append(prices[previous[n] - 1]).append(System.lineSeparator());
            n -= previous[n];
        }

        return out.toString();
    }
}
