import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Paths {

    private static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {


        // open reader and build graph with vertices
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(rd.readLine());

        for (int i = 0; i < n; i++) {

            graph.putIfAbsent(i, new ArrayList<>());
            String line = rd.readLine();
            if (line.trim().equals("")) {

                continue;
            }
            int[] children = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();

            for (int child : children) {

                graph.get(i).add(child);
            }
        }

        rd.close();

        // for every node search the paths by dfs
        for (int i = 0; i < n - 1; i++) {

            boolean[] visited = new boolean[n];
            List<String> path = new ArrayList<>();
            path.add(String.valueOf(i));

            dfs(i, n - 1, visited, path);
        }
    }

    // recursive method - depth first search algorithm DFS
    private static void dfs(int node, int target, boolean[] visited, List<String> path) {

        // if target is reached print the path
        if (node == target) {

            System.out.println(String.join(" ", path));
            return;
        }

        // mark node as visited
        visited[node] = true;

        // for every not visited children of node recall dfs
        for (Integer child : graph.get(node)) {

            if (!visited[child]) {

                path.add(String.valueOf(child));
                dfs(child, target, visited, path);
                // after recursion remove from path-list
                path.remove(String.valueOf(child));
            }
        }
        // also remove the visited mark after all children where checked else you get only the first possible path
        visited[node] = false;
    }
}