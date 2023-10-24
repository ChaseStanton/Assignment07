package Assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * Contains several methods for solving problems on generic, directed,
 * unweighted,
 * sparse graphs.
 *
 * @author Prof. Parker & ??
 * @version October 19, 2023
 */
public class GraphUtility {
    public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
            throws IllegalArgumentException {
        if (sources.size() != destinations.size()) {
            throw new IllegalArgumentException("The sizes of the sources and destinations lists must be the same.");
        }

        Map<Type, List<Type>> adj = new HashMap<>();
        for (int i = 0; i < sources.size(); i++) {
            adj.computeIfAbsent(sources.get(i), k -> new ArrayList<>()).add(destinations.get(i));
        }
        List<Type> visited = new ArrayList<>();
        Stack<Type> queue = new Stack<>();

        queue.push(srcData);
        while (!queue.isEmpty()) {
            Type vertex = queue.pop();
            visited.add(vertex);

            if (vertex.equals(dstData)) {
                return true;
            }

            List<Type> neighbors = adj.get(vertex);
            if (neighbors != null) {
                for (Type neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        queue.push(neighbor);
                    }
                }
            }
            throw new IllegalArgumentException("No connection between source and desination.");
        }
        return false;
    }

    public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData,
            Type dstData) throws IllegalArgumentException {
        if (!areConnected(sources, destinations, srcData, dstData)) {
            throw new IllegalArgumentException("There is no connection between the two nodes");
        }
        if (sources.size() != destinations.size()) {
            throw new IllegalArgumentException("The sizes of the sources and destinations lists must be the same.");
        }
        if (!sources.contains(srcData) || !destinations.contains(dstData)) {
            throw new IllegalArgumentException("Source node or destination node not in graph");
        }

        Map<Type, List<Type>> graph = new HashMap<>();

        for (int i = 0; i < sources.size(); i++) {
            graph.computeIfAbsent(sources.get(i), k -> new ArrayList<>()).add(destinations.get(i));
        }

        Queue<Type> queue = new LinkedList<>();
        Map<Type, Type> parents = new HashMap<>();
        List<Type> shortestPath = new ArrayList<>();

        queue.add(srcData);
        parents.put(srcData, null);

        while (!queue.isEmpty()) {
            Type vertex = queue.poll();
            if (vertex.equals(dstData)) {
                if (vertex != null) {
                    shortestPath.add(vertex);
                    vertex = parents.get(vertex);
                }
                Collections.reverse(shortestPath);
                return shortestPath;
            }
            List<Type> neighbors = graph.get(vertex);
            if (neighbors != null) {
                for (Type neighbor : neighbors) {
                    if (!parents.containsKey(neighbor)) {
                        queue.add(neighbor);
                        parents.put(neighbor, vertex);
                    }
                }
            }
        }

        throw new IllegalArgumentException("No path found from source to destination.");
    }

    public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
    	Map<Type, Integer> inDegree = new HashMap<>();
    	Map<Type, List<Type>> graph = new HashMap<>();
    	//Populate the graph and inDegree
    	for(Type source: sources) {
    		inDegree.put(source, 0);
    		graph.put(source, new ArrayList<>());
    	}
    	// Add destinations to the graph and update the inDegrees
    	for(int i = 0; i < sources.size(); i++) {
    		Type source = sources.get(i);
    		Type destination = destinations.get(i);
    		List<Type> graphSource = graph.get(source);
    		Integer inDegreeDestination = inDegree.get(destination);
    		graphSource.add(destination);
    		inDegree.put(destination, inDegreeDestination + 1);
    	}
    	Queue<Type> queue = new LinkedList<>();
    	List<Type> result = new ArrayList<>();
    	// If the vertice of the inDegree is 0, enqueue the vertice
    	for(Type vertice: inDegree.keySet()) {
    		if(inDegree.get(vertice) == 0)
    			queue.offer(vertice);
    	}
    	// While the queue isn't empty, dequeue the queue.
    	while(!queue.isEmpty()) {
    		Type vertice = queue.poll();
    		result.add(vertice);
    		for(Type next: graph.get(vertice)) {
    			inDegree.put(vertice, inDegree.get(next) - 1);
    			if(inDegree.get(next) == 0)
    				queue.offer(vertice);
    		}
    	}
    	if (result.size() != inDegree.size()) {
            throw new IllegalArgumentException("The graph contains a cycle.");
    	}
        return result;
    }

    /**
     * Builds "sources" and "destinations" lists according to the edges
     * specified in the given DOT file (e.g., "a -> b"). Assumes that the vertex
     * data type is String.
     *
     * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
     * --accepts \\-style comments
     * --accepts one edge per line or edges terminated with ;
     * --does not accept attributes in [] (e.g., [label = "a label"])
     *
     * @param filename     - name of the DOT file
     * @param sources      - empty ArrayList, when method returns it is a valid
     *                     "sources" list that can be passed to the public methods
     *                     in this
     *                     class
     * @param destinations - empty ArrayList, when method returns it is a valid
     *                     "destinations" list that can be passed to the public
     *                     methods in
     *                     this class
     */
    public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<String> destinations) {
        Scanner scan = null;
        try {
            scan = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        scan.useDelimiter(";|\n");
        // Determine if graph is directed (i.e., look for "digraph id {").
        String line = "", edgeOp = "";
        while (scan.hasNext()) {
            line = scan.next();
            // Skip //-style comments.
            line = line.replaceFirst("//.*", "");
            if (line.indexOf("digraph") >= 0) {
                edgeOp = "->";
                line = line.replaceFirst(".*\\{", "");
                break;
            }
        }

        if (edgeOp.equals("")) {
            System.out.println("DOT graph must be directed (i.e.,digraph).");
            scan.close();
            System.exit(0);
        }
        // Look for edge operator -> and determine the source and destination
        // vertices for each edge.
        while (scan.hasNext()) {
            String[] substring = line.split(edgeOp);
            for (int i = 0; i < substring.length - 1; i += 2) {
                // remove " and trim whitespace from node string on the left
                String vertex1 = substring[0].replace("\"", "").trim();
                // if string is empty, try again
                if (vertex1.equals(""))
                    continue;
                // do the same for the node string on the right
                String vertex2 = substring[1].replace("\"", "").trim();
                if (vertex2.equals(""))
                    continue;
                // indicate edge between vertex1 and vertex2
                sources.add(vertex1);
                destinations.add(vertex2);
            }
            // do until the "}" has been read
            if (substring[substring.length - 1].indexOf("}") >= 0)
                break;
            line = scan.next();
            // Skip //-style comments.
            line = line.replaceFirst("//.*", "");
        }
        scan.close();
    }
}