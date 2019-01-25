package sources;

/**
 * Edge sınıfı
 */
public class Edge {
    /**
     * Data fields
     */
    private int source;
    private int dest;
    private double weight;

    /**
     * Constructor
     * @param source
     * @param dest
     */
    public Edge(int source, int dest) {
        this.source = source;
        this.dest = dest;
        weight = 1.0;
    }

    /**
     * Constructor
     * @param source
     * @param dest
     * @param w
     */
    public Edge(int source, int dest, double w) {
        this.source = source;
        this.dest = dest;
        weight = w;
    }

    /**
     * getter source
     * @return int
     */
    public int getSource() {
        return source;
    }

    /**
     * getter destination
     * @return int
     */
    public int getDest() {
        return dest;
    }

    /**
     * return weight
     * @return double
     */
    public double getWeight() {
        return weight;
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[(");
        sb.append(Integer.toString(source));
        sb.append(", ");
        sb.append(Integer.toString(dest));
        sb.append("): ");
        sb.append(Double.toString(weight));
        sb.append("]");
        return sb.toString();
    }

    /**
     *
     * @param obj
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge edge = (Edge) obj;
            return (source == edge.source && dest == edge.dest);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (source << 16) ^ dest;
    }
}

