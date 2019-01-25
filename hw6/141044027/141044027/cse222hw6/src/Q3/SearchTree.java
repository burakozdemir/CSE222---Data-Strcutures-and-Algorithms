package Q3;

public interface SearchTree <E extends Comparable <E>> {
    boolean add(E item);
    boolean contains(E target);
    E find(E target);
    E delete(E target);
    boolean remove(E target);
}