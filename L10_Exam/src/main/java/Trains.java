import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Trains {

    public static void main(String[] args) throws IOException {

        //String[] arrival = {"2.00", "2.10", "3.00", "3.20", "3.50", "5.00"};
        //String[] departure = {"2.30", "3.40", "3.20", "4.30", "4.00", "5.20"};

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        String [] arrival = rd.readLine().split("\\s+");
        String [] departure = rd.readLine().split("\\s+");

        rd.close();

        double[] a = Arrays.stream(arrival).mapToDouble(Double::parseDouble).toArray();
        double[] d = Arrays.stream(departure).mapToDouble(Double::parseDouble).toArray();

        int[] dp = new int[240];

        double t = 0.0;
        int count = 0;
        while (t < 24.0) {

            for (int i = 0; i < a.length; i++) {
                if (t >= a[i] && t < d[i]) {
                    dp[count] += 1;
                }
            }

            count++;
            t = round(t + 0.1, 1);
        }

        int platforms = 0;
        for (int i : dp) {
            if (i > platforms) {
                platforms = i;
            }
        }

        System.out.println(platforms);
    }

    public static double round(double value, int places) {
        if (places < 0) {
            return value;
        }
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}