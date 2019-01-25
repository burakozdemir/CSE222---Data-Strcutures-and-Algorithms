package Q3;

/**
 * BinarySearchTree sınıfı
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements SearchTree<E> {

    /**
     * Data fields
     */
    protected boolean addReturn;
    protected E deleteReturn;

    /**
     * Tree icinde parametre varsa degerı yoksa null return eder.
     * @param target
     * @return E
     */
    @Override
    public E find(E target) {
        return find(root, target);
    }

    /**
     * Public metodu icin helper
     * @param localRoot
     * @param target
     * @return E
     */
    private E find(Node<E> localRoot, E target) {
        if (localRoot == null) {
            return null;
        }
        int compResult = target.compareTo(localRoot.data);
        if (compResult == 0) {
            return localRoot.data;
        } else if (compResult < 0) {
            return find(localRoot.left, target);
        } else {
            return find(localRoot.right, target);
        }
    }

    /**
     * Treeye eleman ekler
     * @param item
     * @return true or false
     */
    @Override
    public boolean add(E item) {
        root = add(root, item);
        return addReturn;
    }

    /**
     * Public metodu ıcın helper
     * @param localRoot
     * @param item
     * @return Node
     */
    private Node<E> add(Node<E> localRoot, E item) {
        if (localRoot == null) {
            addReturn = true;
            return new Node<E>(item);
        } else if (item.compareTo(localRoot.data) == 0) {
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        } else {
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    /**
     * Treeden eleman siler . Aksi halde null return eder.
     * @param target
     * @return E
     */
    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    /**
     * Public metodu icin helper
     * @param localRoot
     * @param item
     * @return Node
     */
    private Node<E> delete(Node<E> localRoot, E item) {
        if (localRoot == null) {
            deleteReturn = null;
            return localRoot;
        }
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        } else if (compResult > 0) {
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        } else {
            deleteReturn = localRoot.data;
            if (localRoot.left == null) {
                return localRoot.right;
            } else if (localRoot.right == null) {
                return localRoot.left;
            } else {
                if (localRoot.left.right == null) {
                    localRoot.data = localRoot.left.data;
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                } else {
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }

    /**
     *
     * @param parent
     * @return E
     */
    private E findLargestChild(Node<E> parent) {
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLargestChild(parent.right);
        }
    }

    /**
     * delete from tree specific element
     * @param target
     * @return true or false
     */
    public boolean remove(E target) {
        return delete(target) != null;
    }

    /**
     * tree icinde varmı yokmu kontrol
     * @param target
     * @return true or false
     */
    public boolean contains(E target) {
        return find(target) != null;
    }
}
