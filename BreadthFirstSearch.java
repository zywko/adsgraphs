import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<V> extends Search<V> {

    public BreadthFirstSearch(Vertex<V> start) {
        super(start);
        bfs(start);
    }

    private void bfs(Vertex<V> start) {
        Queue<Vertex<V>> queue = new LinkedList<>();

        marked.add(start);   // mark start as visited
        queue.add(start);    // add to queue

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll(); // take next vertex from queue

            // Visit all neighbors
            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!marked.contains(neighbor)) {
                    marked.add(neighbor);
                    edgeTo.put(neighbor, current); // remember how we got here
                    queue.add(neighbor);
                }
            }
        }
    }
}
