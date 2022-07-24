import java.util.Scanner;

public class Stairs {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        //int n = 200;
        sc.close();

        int result = 0;

        long[] dp = new long[n + 3];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;


        for (int i = 3; i <= n; i++) {

            dp[i] = dp[i - 2] + dp[i - 1];
        }

        System.out.println(dp[n]);
    }
}