import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Climbing {

    private static long[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        int rows = Integer.parseInt(rd.readLine());
        int cols = Integer.parseInt(rd.readLine());

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            matrix[row] = Arrays.stream(rd.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }

        rd.close();

        int[][] dp = new int[rows][cols];

        dp[rows - 1][cols - 1] = matrix[rows - 1][cols - 1];

        for (int row = rows - 1; row >= 0; row--) {
            for (int col = cols - 1; col >= 0; col--) {
                if (row == rows - 1 && col == cols - 1) {
                    continue;
                } else if (row == rows - 1) {
                    dp[row][col] = dp[row][col + 1] + matrix[row][col];
                } else if (col == cols - 1) {
                    dp[row][col] = dp[row + 1][col] + matrix[row][col];
                } else {
                    if (dp[row + 1][col] > dp[row][col + 1]) {
                        dp[row][col] = dp[row + 1][col] + matrix[row][col];
                    } else {
                        dp[row][col] = dp[row][col + 1] + matrix[row][col];
                    }
                }
            }
        }

        List<String> path = new ArrayList<>();
        int row = 0;
        int col = 0;


        while (row < rows && col < cols) {
            path.add("" + matrix[row][col]);
            if (row == rows - 1) {
                col++;
            } else if (col == cols - 1) {
                row++;
            } else if (dp[row + 1][col] <= dp[row][col + 1]) {
                col++;
            } else {
                row++;
            }
        }

        System.out.println(dp[0][0]);
        Collections.reverse(path);
        System.out.println(String.join(" ", path));
    }
}