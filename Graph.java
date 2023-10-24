package Assignment07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<Type> {
private Map<Type, Vertex<Type>> vertices;

public Graph() {
	this.vertices = new HashMap<Type, Vertex<Type>>();
}


public void addEdge(Type obj1, Type obj2) {
	Vertex<Type> vertex1;
    // if vertex already exists in graph, get its object
    if (vertices.containsKey(obj1))
        vertex1 = vertices.get(obj1);
    // else, create a new object and add to graph
    else {
        vertex1 = new Vertex<Type>(obj1);
        vertices.put(obj1, vertex1);
    }

    Vertex<Type> vertex2;
    if (vertices.containsKey(obj2))
        vertex2 = vertices.get(obj2);
    else {
        vertex2 = new Vertex<Type>(obj2);
        vertices.put(obj2, vertex2);
    }

    // add a new directed edge from vertex1 to vertex2
    vertex1.addEdge(vertex2);
}


}
