import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraSearch<V> extends Search<V> {

    private Map<Vertex<V>, Double> distTo; // shortest distance from start to each vertex

    public DijkstraSearch(Vertex<V> start) {
        super(start);
        distTo = new HashMap<>();
        dijkstra(start);
    }

    private void dijkstra(Vertex<V> start) {
        // Priority queue: always process the vertex with smallest known distance first
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(
            Comparator.comparingDouble(v -> distTo.getOrDefault(v, Double.MAX_VALUE))
        );

        distTo.put(start, 0.0); // distance to start is 0
        marked.add(start);
        pq.add(start);

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();

                double currentDist = distTo.getOrDefault(current, Double.MAX_VALUE);
                double newDist = currentDist + weight;

                // If we found a shorter path to this neighbor, update it
                if (newDist < distTo.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, current);
                    marked.add(neighbor);
                    pq.add(neighbor);
                }
            }
        }
    }

    // Returns the shortest distance from start to vertex v
    public double distanceTo(Vertex<V> v) {
        return distTo.getOrDefault(v, Double.MAX_VALUE);
    }
}
