package sources;

import java.util.Iterator;

/**
 * Graph interface
 */
public interface Graph {

    /**
     * getter adjency matrix
     * @return double[][]
     */
    public double[][] getMatrix();

    /**
     * getter number vertex
     * @return int
     */
    int getNumV();

    /**
     * control graph is directed or undirected
     * @return true or false
     */
    boolean isDirected();

    /**
     * insert to graph edge
     * @param edge
     */
    void insert(Edge edge);

    /**
     * is there edge like that
     * @param source
     * @param dest
     * @return true or false
     */
    boolean isEdge(int source, int dest);

    /**
     * getter edge
     * @param source
     * @param dest
     * @return Edge
     */
    Edge getEdge(int source, int dest);

    /**
     * iterator edge
     * @param source
     * @return Iterator
     */
    Iterator<Edge> edgeIterator(int source);
}

