import java.util.Scanner;

public class RecursiveFibonacci {

    public static long memoArray[] = new long[52];

    static String path = "";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        System.out.println(fibonacci(n + 1));

    }

    public static long fibonacci(int n) {
        long fiboValue = 0;

        if (n == 0) return 0;

        else if (n <= 2) return 1;

        else if (memoArray[n] != 0) return memoArray[n];

        else {
            fiboValue = fibonacci(n - 1) + fibonacci(n - 2);
            memoArray[n] = fiboValue;
            return fiboValue;
        }
    }
}