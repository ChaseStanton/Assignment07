package Assignment07;

import java.util.ArrayList;
import java.util.List;

public class Vertex<Type> {
	private Type data;
	private List<Edge<Type>> edges;
	
	public Vertex(Type data) {
		this.data = data;
		this.edges = new ArrayList<>();
	}
	public void addEdge(Edge<Type> edge) {
		edges.add(edge);
	}
	
	public Type getData() {
		return this.data;
	}
	public List<Edge<Type>> getEdges(){
		return edges;
	}
}
