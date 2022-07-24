import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Trains2 {

    public static void main(String[] args) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        String arrivalInput = rd.readLine();
        String departureInput = rd.readLine();

        rd.close();

        double[] arrival = Arrays.stream(arrivalInput.split("\\s+")).mapToDouble(Double::parseDouble).toArray();
        double[] departure = Arrays.stream(departureInput.split("\\s+")).mapToDouble(Double::parseDouble).toArray();

        double[][] dp = new double[arrival.length][2];

        for (int i = 0; i < arrival.length; i++) {

            dp[i][0] = arrival[i];
            dp[i][1] = departure[i];
        }

        Arrays.sort(dp, (a, d) -> {
            return (int) (a[0] - d[0]);
        });

        PriorityQueue<Double> queue = new PriorityQueue<>();

        int platforms = 1;
        queue.add(dp[0][1]);

        for (int i = 1; i < dp.length; i++) {

            if (queue.peek() > dp[i][0]) {

                platforms++;
            } else {

                queue.remove();
            }

            queue.add(dp[i][1]);
        }

        System.out.println(platforms);
    }
}