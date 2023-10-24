package Assignment07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<Type> {
private Map<Type, Vertex<Type>> vertices;
private Map<Type, List<Type>> graph;

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
	return GraphUtility.sort(new ArrayList<>(graph.keySet()), new ArrayList<>());
}
}
