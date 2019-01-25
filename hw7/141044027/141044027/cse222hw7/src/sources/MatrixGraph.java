package sources;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Matrix sınıfı
 */
public class MatrixGraph extends AbstractGraph {

    /**
     * Datafields
     */
    private double[][] edges;

    /**
     * Constructor
     * @param numV
     * @param directed
     */
    public MatrixGraph(int numV, boolean directed) {
        super(numV, directed);
        edges = new double[numV][];
        if (directed) {
            for (int i = 0; i != numV; ++i) {
                edges[i] = new double[numV];
                for (int j = 0; j != numV; ++j) {
                    edges[i][j] = -1;
                }
            }
        } else {
            for (int i = 0; i != numV; ++i) {
                edges[i] = new double[i + 1];
                for (int j = 0; j != i + 1; ++j) {
                    edges[i][j] = -1;
                }
            }
        }
    }

    /**
     * Get mtarixes
     * @return double[][]
     */
    @Override
    public double[][] getMatrix() {
        return edges;
    }

    /**
     * graph a edge insert eder
     * @param edge
     */
    @Override
    public void insert(sources.Edge edge) {
        setEdgeValue(edge.getSource(), edge.getDest(),
                edge.getWeight());
    }

    /**
     * parametlerın edge controlunu yapar
     * @param source
     * @param dest
     * @return true or false
     */
    @Override
    public boolean isEdge(int source, int dest) {
        return Double.POSITIVE_INFINITY != getEdgeValue(source, dest);
    }

    /**
     * parametlere gore edge return eder
     * @param source
     * @param dest
     * @return Edge
     */
    @Override
    public sources.Edge getEdge(int source, int dest) {
        return new sources.Edge(source, dest,
                getEdgeValue(source, dest));
    }


    /**
     * verilen parametrede edge return eder
     * @param source
     * @return Iterator
     */
    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return new Iter(source);
    }

    /**
     * setter for edge
     * @param source
     * @param dest
     * @param wt
     */
    private void setEdgeValue(int source, int dest, double wt) {
        if (isDirected() || source >= dest) {
            edges[source][dest] = wt;
        } else {
            edges[dest][source] = wt;
        }
    }

    /**
     * getter edge val
     * @param source
     * @param dest
     * @return double
     */
    private double getEdgeValue(int source, int dest) {
        if (isDirected() || source >= dest) {
            return edges[source][dest];
        } else {
            return edges[dest][source];
        }
    }

    /**
     * iterator sınıfı
     */
    private class Iter implements Iterator<Edge> {
        private int source;
        private int index;
        public Iter(int source) {
            this.source = source;
            index = -1;
            advanceIndex();
        }

        @Override
        public boolean hasNext() {
            return index != getNumV();
        }

        @Override
        public Edge next() throws NoSuchElementException {
            if (index == getNumV()) {
                throw new java.util.NoSuchElementException();
            }
            Edge returnValue = new Edge(source, index,
                    getEdgeValue(source, index));
            advanceIndex();
            return returnValue;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void advanceIndex() {
            do {
                index++;
            } while (index != getNumV() && Double.POSITIVE_INFINITY == getEdgeValue(source, index));
        }
    }
}

