import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BreakCycles {

    private static Map<String, Set<String>> g = new TreeMap<>();
    private static List<String> removedEdges = new ArrayList<>();

    private static boolean acyclic = true;

    public static void main(String[] args) throws IOException {

        StringBuilder out = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String line = rd.readLine();
        while (line != null && !line.trim().equals("")) {

            String[] token = line.split(" -> ");

            String key = token[0];
            g.putIfAbsent(key, new TreeSet<>());

            List<String> edges = token.length > 1 ? Arrays.stream(token[1].split("\\s+")).collect(Collectors.toList()) : new ArrayList<>();
            for (String edge : edges) {
                boolean duplicate = !g.get(key).add(edge);
                if (duplicate && !removedEdges.contains(String.format("%s - %s", edge, key))) {
                    removedEdges.add(String.format("%s - %s", key, edge));
                }
            }

            line = rd.readLine();
        }
        rd.close();

        Set<String> visited = new HashSet<>();
        for (String key : g.keySet()) {

        checkForCycles(key, visited, new HashSet<>(), null);

        while (!acyclic) {

                breakCycle();
            }
        }





        out.append("Edges to remove: ").append(removedEdges.size()).append(System.lineSeparator());
        out.append(String.join(System.lineSeparator(), removedEdges));

        System.out.println(out);
    }

    private static void breakCycle() {

        for (String node : g.keySet()) {

            Set<String> edges = g.get(node);

            for (String edge : edges) {
                g.get(node).remove(edge);
                acyclic = true;
                checkForCycles(edge, new HashSet<>(), new HashSet<>(), null);
                if (!acyclic) {
                    g.get(node).add(edge);
                } else {
                    removedEdges.add(String.format("%s - %s", node, edge));
                }
            }
        }
    }

    private static void checkForCycles(String node, Set<String> visited, Set<String> cycle, String lastNode) {

        if (cycle.contains(node)) {
            acyclic = true;
            return;
        }
        if (!visited.contains(node)) {
            visited.add(node);
            cycle.add(node);

            for (String child : g.get(node)) {
                if (!child.equals(lastNode)) {
                    checkForCycles(child, visited, cycle, node);
                }
            }
            cycle.remove(node);
        }
        return;
    }

}