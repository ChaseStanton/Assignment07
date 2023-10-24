package Assignment07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
    public static <Type> List<Type> topologicalSort(List<Type> vertices, List<List<Type>> graph, List<Integer> inDegrees){
    	 Queue<Type> queue = new LinkedList<>();
         List<Type> result = new ArrayList<>();
         
      // Enqueue vertices with in-degree 0
         for (int i = 0; i < inDegrees.size(); i++) {
             if (inDegrees.get(i) == 0) {
                 queue.offer(vertices.get(i));
             }
         }

         // While the queue isn't empty, dequeue the queue.
         while (!queue.isEmpty()) {
             Type vertex = queue.poll();
             result.add(vertex);

             // Update in-degrees of adjacent vertices
             int vertexIndex = vertices.indexOf(vertex);
             for (Type next : graph.get(vertexIndex)) {
                 int nextIndex = vertices.indexOf(next);
                 inDegrees.set(nextIndex, inDegrees.get(nextIndex) - 1);
                 if (inDegrees.get(nextIndex) == 0) {
                     queue.offer(next);
                 }
             }
         }

         return result;
    }

}
