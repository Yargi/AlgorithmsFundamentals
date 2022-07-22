import java.util.*;
import java.util.stream.Collectors;

public class PathFinder {

    public static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] args) {

        /*int n = 7;
        int [][] nodes = new int[n][];
        nodes[0] = new int[] {3, 6};
        nodes[1] = new int[] {4, 5};
        nodes[2] = new int[] {};
        nodes[3] = new int[] {1};
        nodes[4] = new int[] {};
        nodes[5] = new int[] {};
        nodes[6] = new int[] {1, 2};
        int p = 3;
        int[][] paths = new int[p][];
        paths[0] = new int[] {0, 3, 1, 5};
        paths[1] = new int[] {0, 3, 1, 5, 6};
        paths[2] = new int[] {0, 6, 2};*/

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            graph.putIfAbsent(i, new ArrayList<>());
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                graph.get(i).addAll(Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
            }
        }

        int p = Integer.parseInt(sc.nextLine());

        int[][] paths = new int[p][];

        for (int i = 0; i < p; i++) {
            paths[i] = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }

        for (int[] path : paths) {
            boolean brokenPath = false;
            int start = path[0];
            for (int i = 1; i < path.length; i++) {
                if (!graph.get(start).contains(path[i])) {
                    brokenPath = true;
                }
                start = path[i];
            }
            if (brokenPath) {
                System.out.println("no");
            } else {
                System.out.println("yes");
            }
        }
    }
}
