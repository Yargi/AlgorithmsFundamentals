import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SuperSet {

    private static StringBuilder out = new StringBuilder();
    private static int[] combinations;

    public static void main(String[] args) throws IOException {

        //int[] intArray = {4, 5, 6};

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        int[] intArray = Arrays.stream(rd.readLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        rd.close();

        int k = intArray.length;

        out.append(System.lineSeparator());

        for (int i = 1; i <= k; i++) {
            combinations = new int[i];
            combine(0, 0, intArray);
        }

        System.out.print(out);
    }

    private static void combine(int index, int start, int[] intArray) {
        if (index >= combinations.length) {
            writeToString(combinations);
        } else {
            for (int i = start; i < intArray.length; i++) {
                combinations[index] = intArray[i];
                combine(index + 1, i + 1, intArray);
            }
        }
    }

    private static void writeToString(int[] combinations) {
        for (int combination : combinations) {
            out.append(combination).append(" ");
        }
        out.append(System.lineSeparator());
    }
}
