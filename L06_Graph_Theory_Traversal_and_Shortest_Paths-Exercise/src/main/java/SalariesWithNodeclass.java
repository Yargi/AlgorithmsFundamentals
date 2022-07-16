import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalariesWithNodeclass {

    public static void main(String[] args) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        int v = Integer.parseInt(rd.readLine());
        Map<Integer, Node> graph = new HashMap<>();
        for (int i = 0; i < v; i++) {
            graph.putIfAbsent(i, new Node(i));
            Node node = graph.get(i);
            char[] rel = rd.readLine().toCharArray();
            for (int j = 0; j < v; j++) {
                if (rel[j] == 'Y') {
                    graph.putIfAbsent(j, new Node(j));
                    Node child = graph.get(j);
                    node.addChild(child);
                    child.addParent(node);
                }
            }
        }

        int[] salaries = new int[v];
        boolean[] visited = new boolean[v];
        for (Node node : graph.values()) {
            if (node.getParents().isEmpty())
                //node.updateSalary();
                dfs(node, visited, salaries);
        }

        long sumSalary = 0;
        for (Node node : graph.values()) {
            sumSalary += node.getSalary();
        }

        System.out.print(sumSalary);

    }

    private static void dfs(Node node, boolean[] visited, int[] salaries) {
        if (visited[node.getValue()]) {
            return;
        }
        visited[node.getValue()] = true;

        for (Node child : node.children) {
            if (!visited[child.getValue()]) {

                dfs(child, visited, salaries);
                child.setSalary();
            }
        }
        node.setSalary();
    }

    private static class Node {

        private List<Node> parents;
        private List<Node> children;
        private  int value;
        private long salary;


        public Node(int value) {
            this.value = value;
            this.parents = new ArrayList<>();
            this.children = new ArrayList<>();
            this.salary = 1;
        }


        public int getValue() {
            return value;
        }

        public Node setValue(int value) {
            this.value = value;
            return this;
        }

        public long getSalary() {
            return this.salary;
        }

        public Node setSalary() {
            if (this.children.isEmpty()) {

                this.salary = 1;
            } else {

                this.salary = this.children.stream().mapToLong(Node::getSalary).sum();
            }
            return this;
        }

        public Node setSalary(long salary) {
            this.salary = salary;
            return this;
        }

        public List<Node> getParents() {
            return parents;
        }

        public void addParent(Node parent) {
            this.parents.add(parent);
        }

        public List<Node> getChildren() {
            return children;
        }

        public void addChild(Node child) {
            this.children.add(child);
        }

        public long updateSalary() {
            if (this.children.isEmpty())
                this.setSalary(1);
            else {
                int childSalary = 0;
                for (Node child : this.children) {
                    childSalary += child.updateSalary();
                }
                this.setSalary(childSalary);
            }
            return this.salary;
        }
    }
}