import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DividingPresents {

    public static void main(String[] args) throws IOException {

        // read input from console
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        int[] gifts = Arrays.stream(rd.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        rd.close();

        // declare variables
        int totalSum = IntStream.of(gifts).sum();
        int fiftyFifty = totalSum / 2;

        // memoization range (sum of presents + 1)
        int[] sumIndexer = new int[totalSum + 1];
        Arrays.fill(sumIndexer, -1);
        sumIndexer[0] = 0;

        // iteration (gifts size * memo size)
        for (int i = 0; i < gifts.length; i++) {

            int giftValue = gifts[i];
            for (int prevGiftIndex = totalSum - gifts[i]; prevGiftIndex >= 0; prevGiftIndex--) {
                // check for unset value in memo
                if (sumIndexer[prevGiftIndex] != -1 && sumIndexer[prevGiftIndex + giftValue] == -1) {
                    // set the index in memo
                    sumIndexer[prevGiftIndex + giftValue] = i;
                }
            }
        }
        // get the last set index from down half of memo
        while (sumIndexer[fiftyFifty] == -1) {
            fiftyFifty--;
        }

        // build result string
        StringBuilder out = new StringBuilder();
        out.append("Difference: ").append(totalSum - fiftyFifty * 2).append(System.lineSeparator());
        out.append("Alan:").append(fiftyFifty).append(" Bob:").append(totalSum - fiftyFifty).append(System.lineSeparator());
        out.append("Alan takes:");
        // iterate through memo by following previous index and append gift value to output string
        while (fiftyFifty > 0) {
            out.append(" ").append(gifts[sumIndexer[fiftyFifty]]);
            fiftyFifty -= gifts[sumIndexer[fiftyFifty]];
        }
        out.append(System.lineSeparator()).append("Bob takes the rest.").append(System.lineSeparator());
        // print to console
        System.out.print(out);
    }
}