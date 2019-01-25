package Q4;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Avarage Run Time Anlaysis Sınıfı
 * 10 farklı size da test edılmıstır her algoritma
 * bazı durumlarda test edılemeyen algorıtmalar stackoverflow aldgı ıcın cıkarılmıstır
 * Cıkan sonuclar mılısecond cınsındendır.
 */
public class RunTimeAnalysis {

    /**
     * Node sınıfı
     */
    static class Node {

        int data;
        Node next, prev;
        Node(int d) {
            data = d;
            next = prev = null;
        }
    }

    /**
     * MergeSortDoubleLinkedList sınıfı
     */
    public static class MergeSortDoubleLinkedList {

        public static Node head;
        static class Node {

            int data;
            Node next, prev;

            // Constructor to create a new node
            Node(int d) {
                data = d;
                next = prev = null;
            }
        }

        void setHead(Node nod) {
            head = nod;
        }

        Node getHead() {
            return head;
        }

        void print(Node node) {
            Node temp = node;
            System.out.println("Forward Traversal:");
            while (node != null) {
                System.out.print(node.data + " ");
                temp = node;
                node = node.next;
            }
        }

        Node ayirma(Node head) {
            Node ilk = head;
            Node iknci = head;
            while (ilk.next != null && ilk.next.next != null) {
                ilk = ilk.next.next;
                iknci = iknci.next;
            }
            Node temp = iknci.next;
            iknci.next = null;
            return temp;
        }

        Node sort(Node node) {
            if (node == null || node.next == null) {
                return node;
            }
            Node second = ayirma(node);
            node = sort(node);
            second = sort(second);
            return merge(node, second);
        }
        Node merge(Node first, Node second) {
            if (first == null)
                return second;
            if (second == null)
                return first;
            if (first.data < second.data) {
                first.next = merge(first.next, second);
                first.next.prev = first;
                first.prev = null;
                return first;
            } else {
                second.next = merge(first, second.next);
                second.next.prev = second;
                second.prev = null;
                return second;
            }
        }
    }

    /**
     * MergeSort sınıfı
     */
    public static class MergeSort {
        // Merges two subarrays of arr[].
        // First subarray is arr[l..m]
        // Second subarray is arr[m+1..r]
        void merge(int arr[], int l, int m, int r) {
            // Find sizes of two subarrays to be merged
            int n1 = m - l + 1;
            int n2 = r - m;

            /* Create temp arrays */
            int L[] = new int[n1];
            int R[] = new int[n2];

            /*Copy data to temp arrays*/
            for (int i = 0; i < n1; ++i)
                L[i] = arr[l + i];
            for (int j = 0; j < n2; ++j)
                R[j] = arr[m + 1 + j];


            /* Merge the temp arrays */

            // Initial indexes of first and second subarrays
            int i = 0, j = 0;

            // Initial index of merged subarry array
            int k = l;
            while (i < n1 && j < n2) {
                if (L[i] <= R[j]) {
                    arr[k] = L[i];
                    i++;
                } else {
                    arr[k] = R[j];
                    j++;
                }
                k++;
            }

            /* Copy remaining elements of L[] if any */
            while (i < n1) {
                arr[k] = L[i];
                i++;
                k++;
            }

            /* Copy remaining elements of R[] if any */
            while (j < n2) {
                arr[k] = R[j];
                j++;
                k++;
            }
        }

        // Main function that sorts arr[l..r] using
        // merge()
        void sort(int arr[], int l, int r) {
            if (l < r) {
                // Find the middle point
                int m = (l + r) / 2;

                // Sort first and second halves
                sort(arr, l, m);
                sort(arr, m + 1, r);

                // Merge the sorted halves
                merge(arr, l, m, r);
            }
        }

        /* A utility function to print array of size n */
        static void printArray(int arr[]) {
            int n = arr.length;
            for (int i = 0; i < n; ++i)
                System.out.print(arr[i] + " ");
            System.out.println();
        }

        // Driver method
        public static void main(String args[]) {
            int arr[] = {12, 11, 13, 5, 6, 7};

            System.out.println("Given Array");
            printArray(arr);

            MergeSort ob = new MergeSort();
            ob.sort(arr, 0, arr.length - 1);

            System.out.println("\nSorted array");
            printArray(arr);
        }
    }

    /**
     * InsertıonSort sınıf
     */
    public static class InsertionSort {
        /*Function to sort array using insertion sort*/
        void sort(int arr[]) {
            int n = arr.length;
            for (int i = 1; i < n; ++i) {
                int key = arr[i];
                int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
                while (j >= 0 && arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                }
                arr[j + 1] = key;
            }
        }

        /* A utility function to print array of size n*/
        static void printArray(int arr[]) {
            int n = arr.length;
            for (int i = 0; i < n; ++i)
                System.out.print(arr[i] + " ");

            System.out.println();
        }

        // Driver method
        public static void main(String args[]) {
            int arr[] = {12, 11, 13, 5, 6};

            InsertionSort ob = new InsertionSort();
            ob.sort(arr);

            printArray(arr);
        }
    }

    /**
     * QuıckSort sınıfı
     */
    public static class QuickSort {
        /* This function takes last element as pivot,
           places the pivot element at its correct
           position in sorted array, and places all
           smaller (smaller than pivot) to left of
           pivot and all greater elements to right
           of pivot */
        int partition(int arr[], int low, int high) {
            int pivot = arr[high];
            int i = (low - 1); // index of smaller element
            for (int j = low; j < high; j++) {
                // If current element is smaller than or
                // equal to pivot
                if (arr[j] <= pivot) {
                    i++;

                    // swap arr[i] and arr[j]
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            // swap arr[i+1] and arr[high] (or pivot)
            int temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;

            return i + 1;
        }


        /* The main function that implements QuickSort()
          arr[] --> Array to be sorted,
          low  --> Starting index,
          high  --> Ending index */
        void sort(int arr[], int low, int high) {
            if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
                int pi = partition(arr, low, high);

                // Recursively sort elements before
                // partition and after partition
                sort(arr, low, pi - 1);
                sort(arr, pi + 1, high);
            }
        }

        /* A utility function to print array of size n */
        static void printArray(int arr[]) {
            int n = arr.length;
            for (int i = 0; i < n; ++i)
                System.out.print(arr[i] + " ");
            System.out.println();
        }

        // Driver program
        public static void main(String args[]) {
            int arr[] = {10, 7, 8, 9, 1, 5};
            int n = arr.length;

            QuickSort ob = new QuickSort();
            ob.sort(arr, 0, n - 1);

            System.out.println("sorted array");
            printArray(arr);
        }
    }

    /**
     * HeapSort sınıfı
     */
    public static class HeapSort {
        public void sort(int arr[]) {
            int n = arr.length;

            // Build heap (rearrange array)
            for (int i = n / 2 - 1; i >= 0; i--)
                heapify(arr, n, i);

            // One by one extract an element from heap
            for (int i = n - 1; i >= 0; i--) {
                // Move current root to end
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                // call max heapify on the reduced heap
                heapify(arr, i, 0);
            }
        }

        // To heapify a subtree rooted with node i which is
        // an index in arr[]. n is size of heap
        void heapify(int arr[], int n, int i) {
            int largest = i;  // Initialize largest as root
            int l = 2 * i + 1;  // left = 2*i + 1
            int r = 2 * i + 2;  // right = 2*i + 2

            // If left child is larger than root
            if (l < n && arr[l] > arr[largest])
                largest = l;

            // If right child is larger than largest so far
            if (r < n && arr[r] > arr[largest])
                largest = r;

            // If largest is not root
            if (largest != i) {
                int swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;

                // Recursively heapify the affected sub-tree
                heapify(arr, n, largest);
            }
        }

        /* A utility function to print array of size n */
        static void printArray(int arr[]) {
            int n = arr.length;
            for (int i = 0; i < n; ++i)
                System.out.print(arr[i] + " ");
            System.out.println();
        }

        // Driver program
        public static void main(String args[]) {
            int arr[] = {12, 11, 13, 5, 6, 7};
            int n = arr.length;

            HeapSort ob = new HeapSort();
            ob.sort(arr);

            System.out.println("Sorted array is");
            printArray(arr);
        }
    }

    /**
     * verilen arrayı ekrana prınt eder
     * @param arr
     */
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    /**
     * Main
     * @param args
     */
    public static void main(String args[]) {
        Random rand = new Random();
        int m;
        long startTime;
        long endTime;

        long running_time_MergeSort;
        long running_time_InsertionSort;
        long running_time_QuickSort;
        long running_time_HeapSort;
        long running_time_MergeSortDoubleLinkedList;

        int size1 = 10;
        int size2 = 100;
        int size3 = 1000;
        int size4 = 5000;
        int size5 = 10000;
        int size6 = 30000;
        int size7 = 60000;
        int size8 = 90000;
        int size9 = 100000;
        int size10 = 200000;

        int array1[] = new int[size1];
        int array2[] = new int[size2];
        int array3[] = new int[size3];
        int array4[] = new int[size4];
        int array5[] = new int[size5];
        int array6[] = new int[size6];
        int array7[] = new int[size7];
        int array8[] = new int[size8];
        int array9[] = new int[size9];
        int array10[] = new int[size10];

        /////////////////////////////////////////SIZE:10
        //////////////////////////////////////////////
        System.out.println("::::::::::::::::::::::::::::::SIZE:10 ");
        MergeSortDoubleLinkedList list = new MergeSortDoubleLinkedList();
        MergeSortDoubleLinkedList.Node temp = new MergeSortDoubleLinkedList.Node((int) (Math.random() * 100));
        list.setHead(temp);
        for (int i = 0; i < 10; i++) {
            temp.next = new MergeSortDoubleLinkedList.Node((int) (Math.random() * 100));
            temp = temp.next;
        }
        list.print(list.getHead());
        startTime = System.nanoTime();
        list.setHead(list.sort(list.getHead()));
        endTime = System.nanoTime();
        running_time_MergeSortDoubleLinkedList = endTime - startTime;
        int seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSortDoubleLinkedList, TimeUnit.NANOSECONDS);
        ;
        list.print(list.getHead());
        System.out.println("MergeSortDoublLinkedList(10): " + seconds);

        System.out.println("Before:");
        for (m = 0; m < array1.length; m++) {
            array1[m] = (int) (Math.random() * 100);
        }
        printArray(array1);
        MergeSort merge = new MergeSort();
        startTime = System.nanoTime();
        merge.sort(array1, 0, array1.length - 1);
        endTime = System.nanoTime();
        running_time_MergeSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSort, TimeUnit.NANOSECONDS);
        ;
        System.out.println("After:");
        printArray(array1);
        System.out.println("MergeSort(10): " + seconds);

        System.out.println("Before:");
        for (m = 0; m < array1.length; m++) {
            array1[m] = (int) (Math.random() * 100);
        }
        printArray(array1);
        InsertionSort insertion = new InsertionSort();
        startTime = System.nanoTime();
        insertion.sort(array1);
        endTime = System.nanoTime();
        running_time_InsertionSort = endTime - startTime;
        System.out.println("After:");
        printArray(array1);
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_InsertionSort, TimeUnit.NANOSECONDS);
        ;
        System.out.println("InsertionSort(10): " + seconds);

        System.out.println("Before:");
        for (m = 0; m < array1.length; m++) {
            array1[m] = (int) (Math.random() * 100);
        }
        printArray(array1);
        QuickSort quick = new QuickSort();
        startTime = System.nanoTime();
        quick.sort(array1, 0, array1.length - 1);
        endTime = System.nanoTime();
        running_time_QuickSort = endTime - startTime;
        System.out.println("After:");
        printArray(array1);
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_QuickSort, TimeUnit.NANOSECONDS);
        ;
        System.out.println("QuickSort(10): " + seconds);

        System.out.println("Before:");
        for (m = 0; m < array1.length; m++) {
            array1[m] = (int) (Math.random() * 100);
        }
        printArray(array1);
        HeapSort heap = new HeapSort();
        startTime = System.nanoTime();
        heap.sort(array1);
        endTime = System.nanoTime();
        running_time_HeapSort = endTime - startTime;
        System.out.println("After:");
        printArray(array1);
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_HeapSort, TimeUnit.NANOSECONDS);
        ;
        System.out.println("HeapSort(10): " + seconds);

        //////////////////////////////////////SIZE:100
        //////////////////////////////////////////
        System.out.println("::::::::::::::::::::::::::::::SIZE:100 ...");
        temp = new MergeSortDoubleLinkedList.Node((int) (Math.random() * 100));
        list.setHead(temp);
        for (int i = 0; i < 100; i++) {
            temp.next = new MergeSortDoubleLinkedList.Node((int) (Math.random() * 100));
            temp = temp.next;
        }
        startTime = System.nanoTime();
        list.sort(list.getHead());
        endTime = System.nanoTime();
        running_time_MergeSortDoubleLinkedList = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSortDoubleLinkedList, TimeUnit.NANOSECONDS);
        ;
        System.out.println("MergeSortDoublLinkedList(100): " + seconds);


        for (m = 0; m < array2.length; m++) {
            array2[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        merge.sort(array2, 0, array2.length - 1);
        endTime = System.nanoTime();
        running_time_MergeSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSort, TimeUnit.NANOSECONDS);
        ;
        System.out.println("MergeSort(100): " + seconds);

        for (m = 0; m < array2.length; m++) {
            array2[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        insertion.sort(array2);
        endTime = System.nanoTime();
        running_time_InsertionSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_InsertionSort, TimeUnit.NANOSECONDS);
        ;
        System.out.println("InsertionSort(100): " + seconds);

        for (m = 0; m < array2.length; m++) {
            array2[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        quick.sort(array2, 0, array2.length - 1);
        endTime = System.nanoTime();
        running_time_QuickSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_QuickSort, TimeUnit.NANOSECONDS);
        ;
        System.out.println("QuickSort(100): " + seconds);

        for (m = 0; m < array2.length; m++) {
            array2[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        heap.sort(array2);
        endTime = System.nanoTime();
        running_time_HeapSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_HeapSort, TimeUnit.NANOSECONDS);
        ;
        System.out.println("HeapSort(100): " + seconds);


        //////////////////////////////////////SIZE:1000
        /////////////////////////////////////////
        System.out.println("::::::::::::::::::::::::::::::SIZE:1000 ...");
        temp = new MergeSortDoubleLinkedList.Node((int) (Math.random() * 100));
        list.setHead(temp);
        for (int i = 0; i < array3.length; i++) {
            temp.next = new MergeSortDoubleLinkedList.Node((int) (Math.random() * 100));
            temp = temp.next;
        }
        startTime = System.nanoTime();
        list.sort(list.getHead());
        endTime = System.nanoTime();
        running_time_MergeSortDoubleLinkedList = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSortDoubleLinkedList, TimeUnit.NANOSECONDS);
        System.out.println("MergeSortDoublLinkedList(1000): " + seconds);

        for (m = 0; m < array3.length; m++) {
            array3[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        merge.sort(array3, 0, array3.length - 1);
        endTime = System.nanoTime();
        running_time_MergeSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSort, TimeUnit.NANOSECONDS);
        ;
        System.out.println("MergeSort(1000): " + seconds);

        for (m = 0; m < array3.length; m++) {
            array3[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        insertion.sort(array3);
        endTime = System.nanoTime();
        running_time_InsertionSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_InsertionSort, TimeUnit.NANOSECONDS);
        ;
        System.out.println("InsertionSort(1000): " + seconds);

        for (m = 0; m < array3.length; m++) {
            array3[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        quick.sort(array3, 0, array3.length - 1);
        endTime = System.nanoTime();
        running_time_QuickSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_QuickSort, TimeUnit.NANOSECONDS);
        ;
        System.out.println("QuickSort(1000): " + seconds);

        for (m = 0; m < array3.length; m++) {
            array3[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        heap.sort(array3);
        endTime = System.nanoTime();
        running_time_HeapSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_HeapSort, TimeUnit.NANOSECONDS);
        ;
        System.out.println("HeapSort(1000): " + seconds);

        //////////////////////////////////////SIZE:5000
        /////////////////////////////////////////
        System.out.println("::::::::::::::::::::::::::::::::::SIZE:5000 ...");
        temp = new MergeSortDoubleLinkedList.Node((int) (Math.random() * 100));
        list.setHead(temp);
        for (int i = 0; i < array4.length; i++) {
            temp.next = new MergeSortDoubleLinkedList.Node((int) (Math.random() * 100));
            temp = temp.next;
        }
        startTime = System.nanoTime();
        list.sort(list.getHead());
        endTime = System.nanoTime();
        running_time_MergeSortDoubleLinkedList = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSortDoubleLinkedList, TimeUnit.NANOSECONDS);
        System.out.println("MergeSortDoublLinkedList(5000): " + seconds);

        for (m = 0; m < array4.length; m++) {
            array4[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        merge.sort(array4, 0, array4.length - 1);
        endTime = System.nanoTime();
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSort, TimeUnit.NANOSECONDS);
        System.out.println("MergeSort(5000): " + seconds);

        for (m = 0; m < array4.length; m++) {
            array4[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        insertion.sort(array4);
        endTime = System.nanoTime();
        running_time_InsertionSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_InsertionSort, TimeUnit.NANOSECONDS);
        System.out.println("InsertionSort(5000): " + seconds);

        for (m = 0; m < array4.length; m++) {
            array4[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        quick.sort(array4, 0, array4.length - 1);
        endTime = System.nanoTime();
        running_time_QuickSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_QuickSort, TimeUnit.NANOSECONDS);
        System.out.println("QuickSort(5000): " + seconds);

        for (m = 0; m < array4.length; m++) {
            array4[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        heap.sort(array4);
        endTime = System.nanoTime();
        running_time_HeapSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_HeapSort, TimeUnit.NANOSECONDS);
        System.out.println("HeapSort(5000): " + seconds);

        //////////////////////////////////////SIZE:10000
        /////////////////////////////////////////
        System.out.println(":::::::::::::::::::::::::::::::::::::::::SIZE:10000 ...");
        for (m = 0; m < array5.length; m++) {
            array5[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        merge.sort(array5, 0, array5.length - 1);
        endTime = System.nanoTime();
        running_time_MergeSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSort, TimeUnit.NANOSECONDS);
        System.out.println("MergeSort(10000): " + seconds);

        for (m = 0; m < array5.length; m++) {
            array5[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        insertion.sort(array5);
        endTime = System.nanoTime();
        running_time_InsertionSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_InsertionSort, TimeUnit.NANOSECONDS);
        System.out.println("InsertionSort(10000): " + seconds);

        for (m = 0; m < array5.length; m++) {
            array5[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        quick.sort(array5, 0, array3.length - 1);
        endTime = System.nanoTime();
        running_time_QuickSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_QuickSort, TimeUnit.NANOSECONDS);
        System.out.println("QuickSort(10000): " + seconds);

        for (m = 0; m < array5.length; m++) {
            array5[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        heap.sort(array5);
        endTime = System.nanoTime();
        running_time_HeapSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_HeapSort, TimeUnit.NANOSECONDS);
        System.out.println("HeapSort(10000): " + seconds);

        //////////////////////////////////////SIZE:30000
        /////////////////////////////////////////
        System.out.println(":::::::::::::::::::::::::::::::::::SIZE:30000 ...");
        for (m = 0; m < array6.length; m++) {
            array6[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        merge.sort(array6, 0, array6.length - 1);
        endTime = System.nanoTime();
        running_time_MergeSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSort, TimeUnit.NANOSECONDS);
        System.out.println("MergeSort(30000): " + seconds);

        for (m = 0; m < array6.length; m++) {
            array6[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        insertion.sort(array6);
        endTime = System.nanoTime();
        running_time_InsertionSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_InsertionSort, TimeUnit.NANOSECONDS);
        System.out.println("InsertionSort(30000): " + seconds);

        for (m = 0; m < array6.length; m++) {
            array6[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        quick.sort(array6, 0, array6.length - 1);
        endTime = System.nanoTime();
        running_time_QuickSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_QuickSort, TimeUnit.NANOSECONDS);
        System.out.println("QuickSort(30000): " + seconds);

        for (m = 0; m < array6.length; m++) {
            array6[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        heap.sort(array6);
        endTime = System.nanoTime();
        running_time_HeapSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_HeapSort, TimeUnit.NANOSECONDS);
        System.out.println("HeapSort(30000): " + seconds);

        //////////////////////////////////////SIZE:60000
        /////////////////////////////////////////
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::SIZE:60000 ...");
        for (m = 0; m < array7.length; m++) {
            array7[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        merge.sort(array7, 0, array7.length - 1);
        endTime = System.nanoTime();
        running_time_MergeSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSort, TimeUnit.NANOSECONDS);
        System.out.println("MergeSort(60000): " + seconds);

        for (m = 0; m < array7.length; m++) {
            array7[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        insertion.sort(array7);
        endTime = System.nanoTime();
        running_time_InsertionSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_InsertionSort, TimeUnit.NANOSECONDS);
        System.out.println("InsertionSort(60000): " + seconds);

        for (m = 0; m < array7.length; m++) {
            array7[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        quick.sort(array7, 0, array7.length - 1);
        endTime = System.nanoTime();
        running_time_QuickSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_QuickSort, TimeUnit.NANOSECONDS);
        System.out.println("QuickSort(60000): " + seconds);

        for (m = 0; m < array7.length; m++) {
            array7[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        heap.sort(array7);
        endTime = System.nanoTime();
        running_time_HeapSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_HeapSort, TimeUnit.NANOSECONDS);
        System.out.println("HeapSort(60000): " + seconds);

        //////////////////////////////////////SIZE:90000
        /////////////////////////////////////////
        System.out.println(":::::::::::::::::::::::::::::::::::::SIZE:90000 ...");
        for (m = 0; m < array8.length; m++) {
            array8[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        merge.sort(array8, 0, array8.length - 1);
        endTime = System.nanoTime();
        running_time_MergeSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSort, TimeUnit.NANOSECONDS);
        System.out.println("MergeSort(90000): " + seconds);

        for (m = 0; m < array8.length; m++) {
            array8[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        insertion.sort(array8);
        endTime = System.nanoTime();
        running_time_InsertionSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_InsertionSort, TimeUnit.NANOSECONDS);
        System.out.println("InsertionSort(90000): " + seconds);

        for (m = 0; m < array8.length; m++) {
            array8[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        quick.sort(array8, 0, array8.length - 1);
        endTime = System.nanoTime();
        running_time_QuickSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_QuickSort, TimeUnit.NANOSECONDS);
        System.out.println("QuickSort(90000): " + seconds);

        for (m = 0; m < array8.length; m++) {
            array8[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        heap.sort(array8);
        endTime = System.nanoTime();
        running_time_HeapSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_HeapSort, TimeUnit.NANOSECONDS);
        System.out.println("HeapSort(90000): " + seconds);

        //////////////////////////////////////SIZE:100000
        /////////////////////////////////////////
        System.out.println(":::::::::::::::::::::::::::::::::::::SIZE:100000 ...");
        for (m = 0; m < array9.length; m++) {
            array9[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        merge.sort(array9, 0, array9.length - 1);
        endTime = System.nanoTime();
        running_time_MergeSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSort, TimeUnit.NANOSECONDS);
        System.out.println("MergeSort(100000): " + seconds);

        for (m = 0; m < array9.length; m++) {
            array9[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        insertion.sort(array9);
        endTime = System.nanoTime();
        running_time_InsertionSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_InsertionSort, TimeUnit.NANOSECONDS);
        System.out.println("InsertionSort(100000): " + seconds);

        for (m = 0; m < array9.length; m++) {
            array9[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        quick.sort(array9, 0, array9.length - 1);
        endTime = System.nanoTime();
        running_time_QuickSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_QuickSort, TimeUnit.NANOSECONDS);
        System.out.println("QuickSort(100000): " + seconds);

        for (m = 0; m < array9.length; m++) {
            array9[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        heap.sort(array9);
        endTime = System.nanoTime();
        running_time_HeapSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_HeapSort, TimeUnit.NANOSECONDS);
        System.out.println("HeapSort(100000): " + seconds);


        //////////////////////////////////////SIZE:200000
        /////////////////////////////////////////
        System.out.println(":::::::::::::::::::::::::::::SIZE:200000 ...");
        for (m = 0; m < array10.length; m++) {
            array10[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        merge.sort(array10, 0, array10.length - 1);
        endTime = System.nanoTime();
        running_time_MergeSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSort, TimeUnit.NANOSECONDS);
        System.out.println("MergeSort(200000): " + seconds);

        for (m = 0; m < array10.length; m++) {
            array10[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        insertion.sort(array10);
        endTime = System.nanoTime();
        running_time_InsertionSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_InsertionSort, TimeUnit.NANOSECONDS);
        System.out.println("InsertionSort(200000): " + seconds);

        for (m = 0; m < array10.length; m++) {
            array10[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        quick.sort(array10, 0, array10.length - 1);
        endTime = System.nanoTime();
        running_time_QuickSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_QuickSort, TimeUnit.NANOSECONDS);
        System.out.println("QuickSort(200000): " + seconds);

        for (m = 0; m < array10.length; m++) {
            array10[m] = (int) (Math.random() * 100);
        }
        startTime = System.nanoTime();
        heap.sort(array10);
        endTime = System.nanoTime();
        running_time_HeapSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_HeapSort, TimeUnit.NANOSECONDS);
        System.out.println("HeapSort(200000): " + seconds);

    }

}

