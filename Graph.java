package Assignment07;

import java.util.ArrayList;
import java.util.List;

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
            stringBuilder.append(edge.getSRC().getData()).append(" -> ")
                         .append(edge.getDST().getData()).append("\n");
        }
        return stringBuilder.toString();
    }

    public boolean isConnected(Vertex<Type> src, Vertex<Type> dst){
        return GraphUtility.areConnected(sourceNodes, destinationNodes, src, dst);
    }

    public List<Vertex<Type>> shortestPath(Vertex<Type> src, Vertex<Type> dst){
        return GraphUtility.shortestPath(sourceNodes, destinationNodes, src, dst);
    }

    public List<Vertex<Type>> TopologicalSort(Vertex<Type> src, Vertex<Type> dst){
        return GraphUtility.sort(sourceNodes, destinationNodes);
    }
}
