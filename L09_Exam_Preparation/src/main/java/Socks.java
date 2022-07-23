import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Socks {

    public static void main(String[] args) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        //int[] left = {31, 41, 29, 32, 42, 40, 26};
        //int[] right = {31, 30, 32, 42, 28, 29, 29};

        int[] left = Arrays.stream(rd.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] right = Arrays.stream(rd.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        rd.close();

        int[][] dp = new int[left.length][right.length];

        int result = 0;

        if (left.length == 1) {
            for (int r : right) {
                if (r == left[0]) {
                    result = 1;
                }
            }
        } else if (right.length == 1) {
            for (int l : left) {
                if (l == right[0]) {
                    result = 1;
                }
            }
        } else {
            for (int i = 0; i < left.length; i++) {
                for (int j = 0; j < right.length; j++) {
                    if (i == 0 || j == 0 || left[i] != right[j]) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                        result = Math.max(result, dp[i][j]);
                    }
                }
            }
        }

        System.out.println(result);
    }
}