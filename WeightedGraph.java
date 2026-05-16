import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {

    private Map<V, Vertex<V>> vertices;
    private boolean directed;

    public WeightedGraph(boolean directed) {
        this.vertices = new HashMap<>();
        this.directed = directed;
    }

    // Add a single vertex to the graph
    public void addVertex(V data) {
        vertices.put(data, new Vertex<>(data));
    }

    // Add an edge between two vertices (creates vertices if they don't exist)
    public void addEdge(V sourceData, V destData, double weight) {
        if (!vertices.containsKey(sourceData)) {
            addVertex(sourceData);
        }
        if (!vertices.containsKey(destData)) {
            addVertex(destData);
        }

        Vertex<V> source = vertices.get(sourceData);
        Vertex<V> dest = vertices.get(destData);

        source.addAdjacentVertex(dest, weight);

        // If undirected, add edge in both directions
        if (!directed) {
            dest.addAdjacentVertex(source, weight);
        }
    }

    // Get a vertex by its data value
    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }
}
