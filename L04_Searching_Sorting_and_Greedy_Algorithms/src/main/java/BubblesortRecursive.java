import java.util.Arrays;
import java.util.Random;

public class BubblesortRecursive {

    public static void main(String[] args) {

        Random r = new Random();
        int[] arr = new int[r.nextInt(31) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(98) + 1;
        }

        System.out.println(Arrays.toString(arr));

        sort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static int[] sort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            if (arr[i] <= arr[i + 1]) {
                continue;
            }

            int t = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = t;

            sort(arr);
        }

        return arr;
    }
}