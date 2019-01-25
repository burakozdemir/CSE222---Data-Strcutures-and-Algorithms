package main;

import java.util.Vector;

/**
 * MultiDimensionelTree icin arayüz saglanıyor
 * @param <E>
 */
public interface SearchTree<E> {
    boolean add(Vector<E> item);
    boolean contains(Vector<E> target);
    Vector<E> find (Vector<E> target);
    Vector<E> delete(Vector<E> target);
    boolean remove(Vector<E> target);
}
