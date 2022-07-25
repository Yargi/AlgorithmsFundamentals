import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AlphaDecay {

    public static void main(String[] args) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        int[] n = Arrays.stream(rd.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(rd.readLine());

        rd.close();

        //int[] n = {109, 113, 234, 232};
        //int k = 3;

        boolean[] used = new boolean[n.length];
        int[] variations = new int[k];

        variate(0, n, used, variations);

    }

    private static void variate(int index, int[] elements, boolean[] used, int[] variations) {

        if (index == variations.length) {

            print(variations);
            return;
        }

        for (int i = 0; i < elements.length; i++) {

            if (!used[i]) {
                used[i] = true;
                variations[index] = elements[i];
                variate(index + 1, elements, used, variations);
                used[i] = false;
            }
        }
    }

    private static void print(int[] variations) {

        StringBuilder out = new StringBuilder();

        for (int n : variations) {

            out.append(n).append(" ");
        }

        System.out.println(out);
    }
}