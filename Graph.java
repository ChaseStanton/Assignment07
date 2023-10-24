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

   
}
