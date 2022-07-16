import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DividingPresents {

    private static int[] gifts;
    private static List<Integer> alansGifts = new ArrayList<>();
    private static int sumAlan = 0;
    private static List<Integer> bobsGifts = new ArrayList<>();
    private static int sumBob = 0;
    private static int fiftyFifty;
    private static int difference = 0;

    public static void main(String[] args) throws IOException {

        // read input from console
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        gifts = Arrays.stream(rd.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        rd.close();
        // declare variables
        fiftyFifty = Arrays.stream(gifts).sum() / 2;
        // calculations
        divideGifts();



        // build result string
        StringBuilder out = new StringBuilder();
        out.append("Difference: ").append(difference).append(System.lineSeparator());
        out.append("Alan:").append(sumAlan).append(" Bob:").append(sumBob).append(System.lineSeparator());
        out.append("Alan takes: ").append(alansGifts.stream().map(String::valueOf).collect(Collectors.joining(" "))).append(System.lineSeparator());
        out.append("Bob takes the rest.").append(System.lineSeparator());
        // print
        System.out.print(out);
    }

    private static void divideGifts() {

        for (int i = 0; i < gifts.length; i++) {
            int a = gifts[i];
            if (sumBob <= sumAlan) {
                bobsGifts.add(a);
                sumBob += a;
            } else {
                alansGifts.add(a);
                sumAlan += a;
            }
        }

        difference = Math.abs(sumBob - sumAlan) ;

        while (!(sumAlan == fiftyFifty)
                && (bobsGifts.stream().anyMatch(g -> g <= difference)
                || alansGifts.stream().anyMatch(g -> g <= difference))) {

            int a = 0;
            if (sumBob <= sumAlan) {
                a = alansGifts.stream().filter(g -> g <= difference).findAny().get();
                alansGifts.remove(alansGifts.indexOf(a));
                bobsGifts.add(a);
                sumAlan -= a;
                sumBob += a;
            } else {
                a = bobsGifts.stream().filter(g -> g <= difference).findAny().get();
                bobsGifts.remove(bobsGifts.indexOf(a));
                alansGifts.add(a);
                sumBob -= a;
                sumAlan += a;
            }

            difference = Math.abs(sumBob - sumAlan);
        }
    }

}
