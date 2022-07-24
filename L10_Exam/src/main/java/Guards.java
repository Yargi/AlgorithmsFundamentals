import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Guards {

    private static Map<Integer, List<Integer>> g = new HashMap<>();
    private static boolean[] connected;

    public static void main(String[] args) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(rd.readLine());
        int m = Integer.parseInt(rd.readLine());

        for (int i = 0; i < n + 1; i++) {
            g.putIfAbsent(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int[] token = Arrays.stream(rd.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int source = token[0];
            int destination = token[1];
            g.get(source).add(destination);
        }

        int s = Integer.parseInt(rd.readLine());

        rd.close();

        connected = new boolean[n + 1];

        searchConnections(s);

        StringBuilder out = new StringBuilder();

        for (int i = 1; i < connected.length; i++) {
            if (!connected[i]) {
                out.append(i).append(" ");
            }
        }

        System.out.println(out);
    }

    private static void searchConnections(int node) {

        connected[node] = true;

        for (Integer child : g.get(node)) {
            if (!connected[child]) {
                searchConnections(child);
            }
        }
    }
}