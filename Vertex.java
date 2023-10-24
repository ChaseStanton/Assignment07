package Assignment07;

import java.util.Iterator;
import java.util.LinkedList;

public class Vertex<Type> {
	private Type vertice;
	private LinkedList<Edge<Type>> adj;

	public Vertex(Type vertice) {
		this.vertice = vertice;
		this.adj = new LinkedList<Edge<Type>>();
	}

	public Type getVertice() {
		return this.vertice;
	}

	public void addEdge(Vertex<Type> otherVertex) {
		adj.add(new Edge<Type>(otherVertex));
	}

	public Iterator<Edge<Type>> edges() {
		return adj.iterator();
	}
}
