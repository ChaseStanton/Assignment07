package Assignment07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<Type> {
private Map<Type, Vertex<Type>> vertices;
private List<Edge<Type>> edges;

public Graph() {
	this.vertices = new HashMap<>();
}
public void addVertex(Type data) {
    if (!vertices.containsKey(data)) {
        vertices.put(data, new Vertex<>(data));
    }
}

public void addEdge(Type sourceData, Type destinationData) {
    Vertex<Type> source = vertices.get(sourceData);
    Vertex<Type> destination = vertices.get(destinationData);

    if (source != null && destination != null) {
        Edge<Type> edge = new Edge<>(source, destination);
        source.addEdge(edge);
    }
}

public List<Type> topologicalSort(){
	List<Type> sources = new ArrayList<>();
    List<Type> destinations = new ArrayList<>();

    for (Vertex<Type> vertex : vertices.values()) {
        Type sourceData = vertex.getData();
        for (Edge<Type> edge : vertex.getEdges()) {
            Type destinationData = edge.getDestination().getData();
            sources.add(sourceData);
            destinations.add(destinationData);
        }}

    return GraphUtility.sort(sources, destinations);
}
}
