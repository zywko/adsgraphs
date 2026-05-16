public class Main {
    public static void main(String[] args) {

        // Create an undirected weighted graph
        WeightedGraph<String> graph = new WeightedGraph<>(false);

        // Add edges (vertices are created automatically)
        graph.addEdge("A", "B", 1.5);
        graph.addEdge("A", "C", 3.0);
        graph.addEdge("B", "D", 2.0);
        graph.addEdge("C", "D", 1.0);
        graph.addEdge("D", "E", 4.0);
        graph.addEdge("B", "E", 7.0);

        // The graph looks like:
        //   A -1.5- B -2.0- D -4.0- E
        //   |               |       |
        //  3.0             1.0     7.0
        //   |               |       |
        //   C ------1.0-----+       B

        Vertex<String> startVertex = graph.getVertex("A");
        Vertex<String> endVertex   = graph.getVertex("E");

        // ---- BFS ----
        System.out.println("=== Breadth First Search (BFS) ===");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(startVertex);

        if (bfs.hasPathTo(endVertex)) {
            System.out.println("Path found from A to E: " + bfs.pathTo(endVertex));
        } else {
            System.out.println("No path found from A to E");
        }

        // ---- Dijkstra ----
        System.out.println("\n=== Dijkstra Shortest Path ===");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(startVertex);

        if (dijkstra.hasPathTo(endVertex)) {
            System.out.println("Shortest path from A to E: " + dijkstra.pathTo(endVertex));
            System.out.println("Total distance: " + dijkstra.distanceTo(endVertex));
        } else {
            System.out.println("No path found from A to E");
        }
    }
}
