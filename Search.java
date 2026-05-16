import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Search<V> {

    protected Vertex<V> start;
    protected Set<Vertex<V>> marked;       // visited vertices
    protected Map<Vertex<V>, Vertex<V>> edgeTo;  // tracks the path (who came from where)

    public Search(Vertex<V> start) {
        this.start = start;
        this.marked = new HashSet<>();
        this.edgeTo = new HashMap<>();
    }

    // Returns true if we found a path to this vertex
    public boolean hasPathTo(Vertex<V> v) {
        return marked.contains(v);
    }

    // Returns the path from start to v as a list
    public List<V> pathTo(Vertex<V> v) {
        if (!hasPathTo(v)) {
            return null;
        }

        LinkedList<V> path = new LinkedList<>();
        Vertex<V> current = v;

        // Walk backwards from destination to start using edgeTo map
        while (current != start) {
            path.addFirst(current.getData());
            current = edgeTo.get(current);
        }
        path.addFirst(start.getData()); // add the start vertex

        return path;
    }
}
