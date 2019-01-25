package Q3;

/**
 * MergeSortDoubleLinkedList sınıf
 */
public class MergeSortDoubleLinkedList {

    /**
     * Linkedlist ozellıgı ıcın node sınıfı
     */
    public static class Node {

        int data;
        Node next, prev;
        Node(int d) {
            data = d;
            next = prev = null;
        }
        public void setNext(Node a){
            next=a;
        }
        public Node getNext(){return next;}
    }

    /**
     * Nodeların baslangıc data fieldı
     */
    public static Node head;

    /**
     * Verilen nodedan ıtıbaren ileri dogru traverse eder
     * @param node
     */
    void print(Node node) {
        Node temp = node;
        while (node != null) {
            System.out.print(node.data + " ");
            temp = node;
            node = node.next;
        }
    }

    /**
     * Verilen nodedan ıtıbaren lısteyı ıkıye ayırır
     * @param head
     * @return Node
     */
    Node ayirma(Node head) {
        Node ilk = head, iknci = head;
        while (ilk.next != null && ilk.next.next != null) {
            ilk = ilk.next.next;
            iknci = iknci.next;
        }
        Node temp = iknci.next;
        iknci.next = null;
        return temp;
    }

    /**
     * Verilen Nodedan ıtıbaren kucukten buyuge sıralama yapar
     * @param node
     * @return Node
     */
    public Node sort(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node iknci = ayirma(node);
        node = sort(node);
        iknci = sort(iknci);
        return birlestir(node, iknci);
    }

    /**
     * Parametrede verılen lıste bası nodeları sıralı sekılde tek bır lıste olarak return eder
     * @param ilk
     * @param ikinci
     * @return Node
     */
    Node birlestir(Node ilk, Node ikinci) {
        if (ilk == null)
            return ikinci;

        if (ikinci == null)
            return ilk;
        if (ilk.data < ikinci.data) {
            ilk.next = birlestir(ilk.next, ikinci);
            ilk.next.prev = ilk;
            ilk.prev = null;
            return ilk;
        } else {
            ikinci.next = birlestir(ilk, ikinci.next);
            ikinci.next.prev = ikinci;
            ikinci.prev = null;
            return ikinci;
        }
    }
}