import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Trains3 {

    public static void main(String[] args) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        double[] arrival = Arrays.stream(rd.readLine().split("\\s+")).mapToDouble(Double::parseDouble).toArray();
        double[] departure = Arrays.stream(rd.readLine().split("\\s+")).mapToDouble(Double::parseDouble).toArray();

        rd.close();

        int[] dp = new int[arrival.length];

        for (int i = 0; i < arrival.length; i++) {
            for (int j = 0; j < arrival.length; j++) {
                if (i == j) {
                    dp[i]++;
                } else if (arrival[i] >= arrival[j] && arrival[i] < departure[j]) {
                    dp[i]++;
                }
            }
        }

        int p = 0;
        for (int i : dp) {
            if (i > p) {
                p = i;
            }
        }

        System.out.println(p);
    }
}