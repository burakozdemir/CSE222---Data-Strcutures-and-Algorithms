package sources;

import java.io.IOException;
import java.util.Scanner;

/**
 * AbstractGraph sınıfı(abstract)
 */
public abstract class AbstractGraph implements Graph {

    /**
     * Data fields
     */
    private int numV;
    private boolean directed;

    /**
     * Constructor
     * @param numV
     * @param directed
     */
    public AbstractGraph(int numV, boolean directed) {
        this.numV = numV;
        this.directed = directed;
    }

    /**
     * getter number of vertex
     * @return int
     */
    @Override
    public int getNumV() {
        return numV;
    }

    /**
     * graph is directed or undirected
     * @return true or false
     */
    @Override
    public boolean isDirected() {
        return directed;
    }

    /**
     * file dan graph yapısını alır
     * @param scan
     * @throws IOException
     */
    public void loadEdgesFromFile(Scanner scan) throws IOException {
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            String[] tokens = line.split("\\s+");
            int source = Integer.parseInt(tokens[0]);
            int dest = Integer.parseInt(tokens[1]);
            double weight = 1.0;
            if (tokens.length == 3) {
                weight = Double.parseDouble(tokens[2]);

            }
            insert(new Edge(source, dest, weight));
        }
    }

    /**
     * Graph sınıfı olusturur
     * @param scan
     * @param isDirected
     * @param type
     * @return Graph
     * @throws IOException
     */
    public static Graph createGraph(Scanner scan,
            boolean isDirected,
            String type) throws IOException {
        int numV = scan.nextInt();
        scan.nextLine();
        AbstractGraph returnValue = null;
        if (type.equalsIgnoreCase("Matrix")) {
            returnValue = new MatrixGraph(numV, isDirected);
        }  else {
            throw new IllegalArgumentException();
        }
        returnValue.loadEdgesFromFile(scan);
        return returnValue;
    }
}
