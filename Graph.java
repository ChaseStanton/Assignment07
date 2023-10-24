package Assignment07;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph<Type> {
    private List<Vertex<Type>> sourceNodes;
    private List<Vertex<Type>> destinationNodes;
    private List<Edge<Type>> edges;

    public Graph() {
        this.sourceNodes = new ArrayList<>();
        this.destinationNodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public void addEdge(Vertex<Type> source, Vertex<Type> destination) {
        sourceNodes.add(source);
        destinationNodes.add(destination);
        Edge<Type> edge = new Edge<>(source, destination);
        edges.add(edge);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Edge<Type> edge : edges) {
            stringBuilder.append(edge.getSRC().getData()).append(" -> ").append(edge.getDST().getData()).append("\n");
        }
        return stringBuilder.toString();
    }

    public boolean isConnected(Vertex<Type> src, Vertex<Type> dst){
        return GraphUtility.areConnected(sourceNodes, destinationNodes, src, dst);
    }
    public static <Type> List<Type> topologicalSort(List<Type> verticesList, List<List<Type>> graph, List<Integer> inDegrees) {
        Queue<Type> queue = new LinkedList<>();
        List<Type> result = new ArrayList<>();

        // Enqueue vertices with in-degree 0
        for (int i = 0; i < inDegrees.size(); i++) {
            if (inDegrees.get(i) == 0) {
                queue.offer(verticesList.get(i));
            }
        }

        // While the queue isn't empty, dequeue the queue.
        while (!queue.isEmpty()) {
            Type vertex = queue.poll();
            result.add(vertex);

            // Update in-degrees of adjacent vertices
            int vertexIndex = verticesList.indexOf(vertex);
            for (Type next : graph.get(vertexIndex)) {
                int nextIndex = verticesList.indexOf(next);
                inDegrees.set(nextIndex, inDegrees.get(nextIndex) - 1);
                if (inDegrees.get(nextIndex) == 0) {
                    queue.offer(next);
                }
            }
        }

        return result;
    }
}
