package Q5;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * WorstCasePerformanceAnalysis
 * içerideki algoritmaaların worst case durumunda calısabılmesı ıcın arraylar bu sekılde doldurulmustur
 * Sonuclar mılısecond cınsındendır
 */
public class WorstCasePerformanceAnalysis {
    /**
     * Node sınıfı
     */
    static class Node {

        int data;
        Node next, prev;

        // Constructor to create a new node
        Node(int d) {
            data = d;
            next = prev = null;
        }
    }

    /**
     * MergeSortDoubleLinkedList sınıfı
     */
    public static class MergeSortDoubleLinkedList {

        public static Node head;  // head of list

        /* Node Class */
        static class Node {

            int data;
            Node next, prev;

            // Constructor to create a new node
            Node(int d) {
                data = d;
                next = prev = null;
            }
        }

        void setHead(Node nod){head=nod;}
        Node getHead(){return head;}

        void print(Node node) {
            Node temp = node;
            System.out.println("Forward Traversal using next pointer");
            while (node != null) {
                System.out.print(node.data + " ");
                temp = node;
                node = node.next;
            }
            System.out.println("\nBackward Traversal using prev pointer");
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.prev;
            }
        }

        // Split a doubly linked list (DLL) into 2 DLLs of
        // half sizes
        Node split(Node head) {
            Node fast = head, slow = head;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            Node temp = slow.next;
            slow.next = null;
            return temp;
        }

        Node mergeSort(Node node) {
            if (node == null || node.next == null) {
                return node;
            }
            Node second = split(node);

            // Recur for left and right halves
            node = mergeSort(node);
            second = mergeSort(second);

            // Merge the two sorted halves
            return merge(node, second);
        }

        // Function to merge two linked lists
        Node merge(Node first, Node second) {
            // If first linked list is empty
            if (first == null) {
                return second;
            }

            // If second linked list is empty
            if (second == null) {
                return first;
            }

            // Pick the smaller value
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

        // Driver program to test above functions
        public static void main(String[] args) {

        }
    }

    /**
     * MergeSort sınıfı
     */
    public static class MergeSort
    {
        // Merges two subarrays of arr[].
        // First subarray is arr[l..m]
        // Second subarray is arr[m+1..r]
        void merge(int arr[], int l, int m, int r)
        {
            // Find sizes of two subarrays to be merged
            int n1 = m - l + 1;
            int n2 = r - m;

            /* Create temp arrays */
            int L[] = new int [n1];
            int R[] = new int [n2];

            /*Copy data to temp arrays*/
            for (int i=0; i<n1; ++i)
                L[i] = arr[l + i];
            for (int j=0; j<n2; ++j)
                R[j] = arr[m + 1+ j];


            /* Merge the temp arrays */

            // Initial indexes of first and second subarrays
            int i = 0, j = 0;

            // Initial index of merged subarry array
            int k = l;
            while (i < n1 && j < n2)
            {
                if (L[i] <= R[j])
                {
                    arr[k] = L[i];
                    i++;
                }
                else
                {
                    arr[k] = R[j];
                    j++;
                }
                k++;
            }

            /* Copy remaining elements of L[] if any */
            while (i < n1)
            {
                arr[k] = L[i];
                i++;
                k++;
            }

            /* Copy remaining elements of R[] if any */
            while (j < n2)
            {
                arr[k] = R[j];
                j++;
                k++;
            }
        }

        // Main function that sorts arr[l..r] using
        // merge()
        void sort(int arr[], int l, int r)
        {
            if (l < r)
            {
                // Find the middle point
                int m = (l+r)/2;

                // Sort first and second halves
                sort(arr, l, m);
                sort(arr , m+1, r);

                // Merge the sorted halves
                merge(arr, l, m, r);
            }
        }

        /* A utility function to print array of size n */
        static void printArray(int arr[])
        {
            int n = arr.length;
            for (int i=0; i<n; ++i)
                System.out.print(arr[i] + " ");
            System.out.println();
        }

        // Driver method
        public static void main(String args[])
        {
            int arr[] = {12, 11, 13, 5, 6, 7};

            System.out.println("Given Array");
            printArray(arr);

            MergeSort ob = new MergeSort();
            ob.sort(arr, 0, arr.length-1);

            System.out.println("\nSorted array");
            printArray(arr);
        }
    }

    /**
     * InsertıonSort sınıfı
     */
    public static class InsertionSort
    {
        /*Function to sort array using insertion sort*/
        void sort(int arr[])
        {
            int n = arr.length;
            for (int i=1; i<n; ++i)
            {
                int key = arr[i];
                int j = i-1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
                while (j>=0 && arr[j] > key)
                {
                    arr[j+1] = arr[j];
                    j = j-1;
                }
                arr[j+1] = key;
            }
        }

        /* A utility function to print array of size n*/
        static void printArray(int arr[])
        {
            int n = arr.length;
            for (int i=0; i<n; ++i)
                System.out.print(arr[i] + " ");

            System.out.println();
        }

        // Driver method
        public static void main(String args[])
        {
            int arr[] = {12, 11, 13, 5, 6};

            InsertionSort ob = new InsertionSort();
            ob.sort(arr);

            printArray(arr);
        }
    }

    /**
     * QuıckSort sınıfı
     */
    public static class QuickSort
    {
        /* This function takes last element as pivot,
           places the pivot element at its correct
           position in sorted array, and places all
           smaller (smaller than pivot) to left of
           pivot and all greater elements to right
           of pivot */
        int partition(int arr[], int low, int high)
        {
            int pivot = arr[high];
            int i = (low-1); // index of smaller element
            for (int j=low; j<high; j++)
            {
                // If current element is smaller than or
                // equal to pivot
                if (arr[j] <= pivot)
                {
                    i++;

                    // swap arr[i] and arr[j]
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            // swap arr[i+1] and arr[high] (or pivot)
            int temp = arr[i+1];
            arr[i+1] = arr[high];
            arr[high] = temp;

            return i+1;
        }


        /* The main function that implements QuickSort()
          arr[] --> Array to be sorted,
          low  --> Starting index,
          high  --> Ending index */
        void sort(int arr[], int low, int high)
        {
            if (low < high)
            {
            /* pi is partitioning index, arr[pi] is
              now at right place */
                int pi = partition(arr, low, high);

                // Recursively sort elements before
                // partition and after partition
                sort(arr, low, pi-1);
                sort(arr, pi+1, high);
            }
        }

        /* A utility function to print array of size n */
        static void printArray(int arr[])
        {
            int n = arr.length;
            for (int i=0; i<n; ++i)
                System.out.print(arr[i]+" ");
            System.out.println();
        }

        // Driver program
        public static void main(String args[])
        {
            int arr[] = {10, 7, 8, 9, 1, 5};
            int n = arr.length;

            QuickSort ob = new QuickSort();
            ob.sort(arr, 0, n-1);

            System.out.println("sorted array");
            printArray(arr);
        }
    }

    /**
     * HeapSort sınıfı
     */
    public static class HeapSort
    {
        public void sort(int arr[])
        {
            int n = arr.length;

            // Build heap (rearrange array)
            for (int i = n / 2 - 1; i >= 0; i--)
                heapify(arr, n, i);

            // One by one extract an element from heap
            for (int i=n-1; i>=0; i--)
            {
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
        void heapify(int arr[], int n, int i)
        {
            int largest = i,l = 2*i + 1,r = 2*i + 2;

            // If left child is larger than root
            if (l < n && arr[l] > arr[largest])
                largest = l;

            // If right child is larger than largest so far
            if (r < n && arr[r] > arr[largest])
                largest = r;

            // If largest is not root
            if (largest != i)
            {
                int swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;

                // Recursively heapify the affected sub-tree
                heapify(arr, n, largest);
            }
        }

        /* A utility function to print array of size n */
        static void printArray(int arr[])
        {
            int n = arr.length;
            for (int i=0; i<n; ++i)
                System.out.print(arr[i]+" ");
            System.out.println();
        }

        // Driver program
        public static void main(String args[])
        {
            int arr[] = {12, 11, 13, 5, 6, 7};
            int n = arr.length;

            HeapSort ob = new HeapSort();
            ob.sort(arr);

            System.out.println("Sorted array is");
            printArray(arr);
        }
    }

    /**
     * MergeSort ıcın worst durumu olusturan sınıf
     */
    public static class GFG
    {
        // Function to join left and right subarray
        static void join(int arr[], int left[], int right[],
                         int l, int m, int r)
        {
            int i;
            for (i = 0; i <= m - l; i++)
                arr[i] = left[i];

            for (int j = 0; j < r - m; j++)
                arr[i + j] = right[j];
        }

        // Function to store alternate elemets in left
        // and right subarray
        static void split(int arr[], int left[], int right[],
                          int l, int m, int r)
        {
            for (int i = 0; i <= m - l; i++)
                left[i] = arr[i * 2];

            for (int i = 0; i < r - m; i++)
                right[i] = arr[i * 2 + 1];
        }

        // Function to generate Worst Case of Merge Sort
        public void generateWorstCase(int arr[], int l, int r)
        {
            if (l < r)
            {
                int m = l + (r - l) / 2;

                // create two auxillary arrays
                int[] left = new int[m - l + 1];
                int[] right = new int[r - m];

                // Store alternate array elements in left
                // and right subarray
                split(arr, left, right, l, m, r);

                // Recurse first and second halves
                generateWorstCase(left, l, m);
                generateWorstCase(right, m + 1, r);

                // join left and right subarray
                join(arr, left, right, l, m, r);
            }
        }

        // driver program
        public static void main (String[] args)
        {
            // sorted array
            int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9,
                    10, 11, 12, 13, 14, 15, 16 };
            int n = arr.length;
            System.out.println("Sorted array is");
            System.out.println(Arrays.toString(arr));

            // generate Worst Case of Merge Sort
            //generateWorstCase(arr, 0, n - 1);

            System.out.println("\nInput array that will result in \n"+
                    "worst case of merge sort is \n");

            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * Main
     * @param args
     */
    public static void main(String args[]){
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
        int size3 = 5000;
        int size4 = 10000;

        int array1[] = new int [size1];
        int array2[] = new int [size2];
        int array3[] = new int [size3];
        int array4[] = new int [size4];

        /////////////////////////////////////////SIZE:10
        //////////////////////////////////////////////
        System.out.println("::::::::::::::::::::::::::::::SIZE:10 ...");
        ///MergeSortDoublyLinkedList
        MergeSortDoubleLinkedList list = new MergeSortDoubleLinkedList();
        MergeSortDoubleLinkedList.Node temp=new MergeSortDoubleLinkedList.Node((int)(Math.random()*100));
        list.setHead(temp);
        for (int i = 0; i <10 ; i++) {
            temp.next=new MergeSortDoubleLinkedList.Node((int)(Math.random()*100));
            temp=temp.next;
        }
        startTime=System.nanoTime();
        list.mergeSort(list.getHead());
        endTime = System.nanoTime();
        running_time_MergeSortDoubleLinkedList = endTime - startTime;
        int seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSortDoubleLinkedList, TimeUnit.NANOSECONDS);
        System.out.println("MergeSortDoublLinkedList(10): "+seconds);

        ////MergeSort
        for (int i = 0; i <size1 ; i++) {
            array1[i]=i;
        }
        GFG genareting=new GFG();
        genareting.generateWorstCase(array1,0,array1.length-1);
        MergeSort merge = new MergeSort();
        startTime = System.nanoTime();
        merge.sort(array1, 0, array1.length-1);
        endTime = System.nanoTime();
        running_time_MergeSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSort, TimeUnit.NANOSECONDS);
        System.out.println("MergeSort(10): "+seconds);

        ///InsertionSort
        int count=0;
        for (int i = size1-1; i >=0 ; i--) {
            array1[count]=i;
            count++;
        }
        InsertionSort insertion = new InsertionSort();
        startTime = System.nanoTime();
        insertion.sort(array1);
        endTime = System.nanoTime();
        running_time_InsertionSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_InsertionSort, TimeUnit.NANOSECONDS);
        System.out.println("InsertionSort(10): "+seconds);

        ///QuickSort
        count=0;
        for (int i = size1-1; i >=0 ; i--) {
            array1[count]=i;
            count++;
        }
        QuickSort quick = new QuickSort();
        startTime = System.nanoTime();
        quick.sort(array1, 0, array1.length-1);
        endTime = System.nanoTime();
        running_time_QuickSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_QuickSort, TimeUnit.NANOSECONDS);
        System.out.println("QuickSort(10): "+seconds);

        ///HeapSort
        count=0;
        for (int i = size1-1; i >=0 ; i--) {
            array1[count]=i;
            count++;
        }
        HeapSort heap = new HeapSort();
        startTime = System.nanoTime();
        heap.sort(array1);
        endTime = System.nanoTime();
        running_time_HeapSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_HeapSort, TimeUnit.NANOSECONDS);
        System.out.println("HeapSort(10): "+seconds);

        /////////////////////////////////////////SIZE:100
        //////////////////////////////////////////////
        System.out.println("::::::::::::::::::::::::::::::SIZE:100 ...");
        //MergeSortDoublyLinkedList
        temp=new MergeSortDoubleLinkedList.Node((int)(Math.random()*100));
        list.setHead(temp);
        for (int i = 0; i <100 ; i++) {
            temp.next=new MergeSortDoubleLinkedList.Node((int)(Math.random()*100));
            temp=temp.next;
        }
        startTime=System.nanoTime();
        list.mergeSort(list.getHead());
        endTime = System.nanoTime();
        running_time_MergeSortDoubleLinkedList = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSortDoubleLinkedList, TimeUnit.NANOSECONDS);
        System.out.println("MergeSortDoublLinkedList(100): "+seconds);


        ////MergeSort
        for (int i = 0; i <size2 ; i++) {
            array2[i]=i;
        }
        genareting.generateWorstCase(array2,0,array2.length-1);
        startTime = System.nanoTime();
        merge.sort(array2, 0, array2.length-1);
        endTime = System.nanoTime();
        running_time_MergeSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSort, TimeUnit.NANOSECONDS);
        System.out.println("MergeSort(100): "+seconds);

        ///InsertionSort
        count=0;
        for (int i = size2-1; i >=0 ; i--) {
            array2[count]=i;
            count++;
        }
        startTime = System.nanoTime();
        insertion.sort(array2);
        endTime = System.nanoTime();
        running_time_InsertionSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_InsertionSort, TimeUnit.NANOSECONDS);
        System.out.println("InsertionSort(100): "+seconds);

        ///QuickSort
        count=0;
        for (int i = size2-1; i >=0 ; i--) {
            array2[count]=i;
            count++;
        }
        startTime = System.nanoTime();
        quick.sort(array2, 0, array2.length-1);
        endTime = System.nanoTime();
        running_time_QuickSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_QuickSort, TimeUnit.NANOSECONDS);
        System.out.println("QuickSort(100): "+seconds);

        ///HeapSort
        count=0;
        for (int i = size2-1; i >=0 ; i--) {
            array2[count]=i;
            count++;
        }
        startTime = System.nanoTime();
        heap.sort(array2);
        endTime = System.nanoTime();
        running_time_HeapSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_HeapSort, TimeUnit.NANOSECONDS);
        System.out.println("HeapSort(100): "+seconds);
        /////////////////////////////////////////SIZE:10
        //////////////////////////////////////////////
        System.out.println("::::::::::::::::::::::::::::::SIZE:5000 ...");
        ////
        temp=new MergeSortDoubleLinkedList.Node((int)(Math.random()*100));
        list.setHead(temp);
        for (int i = 0; i <array3.length ; i++) {
            temp.next=new MergeSortDoubleLinkedList.Node((int)(Math.random()*100));
            temp=temp.next;
        }
        startTime=System.nanoTime();
        list.mergeSort(list.getHead());
        endTime = System.nanoTime();
        running_time_MergeSortDoubleLinkedList = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSortDoubleLinkedList, TimeUnit.NANOSECONDS);
        System.out.println("MergeSortDoublLinkedList(5000): "+seconds);


        ////MergeSort
        for (int i = 0; i <size3 ; i++) {
            array3[i]=i;
        }
        genareting.generateWorstCase(array3,0,array3.length-1);
        startTime = System.nanoTime();
        merge.sort(array3, 0, array3.length-1);
        endTime = System.nanoTime();
        running_time_MergeSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSort, TimeUnit.NANOSECONDS);
        System.out.println("MergeSort(5000): "+seconds);

        ///InsertionSort
        count=0;
        for (int i = size3-1; i >=0 ; i--) {
            array3[count]=i;
            count++;
        }
        startTime = System.nanoTime();
        insertion.sort(array3);
        endTime = System.nanoTime();
        running_time_InsertionSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_InsertionSort, TimeUnit.NANOSECONDS);
        System.out.println("InsertionSort(5000): "+seconds);

        ///QuickSort
        count=0;
        for (int i = size3-1; i >=0 ; i--) {
            array3[count]=i;
            count++;
        }
        startTime = System.nanoTime();
        quick.sort(array3, 0, array3.length-1);
        endTime = System.nanoTime();
        running_time_QuickSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_QuickSort, TimeUnit.NANOSECONDS);
        System.out.println("QuickSort(5000): "+seconds);

        ///HeapSort
        count=0;
        for (int i = size3-1; i >=0 ; i--) {
            array3[count]=i;
            count++;
        }
        startTime = System.nanoTime();
        heap.sort(array3);
        endTime = System.nanoTime();
        running_time_HeapSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_HeapSort, TimeUnit.NANOSECONDS);
        System.out.println("HeapSort(5000): "+seconds);

        /////////////////////////////////////////SIZE:10
        //////////////////////////////////////////////
        System.out.println("::::::::::::::::::::::::::::::SIZE:10000 ...");
        /*
        temp=new MergeSortDoubleLinkedList.Node((int)(Math.random()*100));
        list.setHead(temp);
        for (int i = 0; i <array4.length ; i++) {
            temp.next=new MergeSortDoubleLinkedList.Node((int)(Math.random()*100));
            temp=temp.next;
        }
        startTime=System.nanoTime();
        list.mergeSort(list.getHead());
        endTime = System.nanoTime();
        running_time_MergeSortDoubleLinkedList = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSortDoubleLinkedList, TimeUnit.NANOSECONDS);
        System.out.println("MergeSortDoublLinkedList(10000): "+seconds);
*/
        ////MergeSort
        for (int i = 0; i <size4 ; i++) {
            array4[i]=i;
        }
        genareting.generateWorstCase(array4,0,array4.length-1);
        startTime = System.nanoTime();
        merge.sort(array4, 0, array4.length-1);
        endTime = System.nanoTime();
        running_time_MergeSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_MergeSort, TimeUnit.NANOSECONDS);
        System.out.println("MergeSort(10000): "+seconds);

        ///InsertionSort
        count=0;
        for (int i = size4-1; i >=0 ; i--) {
            array4[count]=i;
            count++;
        }
        startTime = System.nanoTime();
        insertion.sort(array4);
        endTime = System.nanoTime();
        running_time_InsertionSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_InsertionSort, TimeUnit.NANOSECONDS);
        System.out.println("InsertionSort(10000): "+seconds);

        /*
        ///QuickSort
        count=0;
        for (int i = size4-1; i >=0 ; i--) {
            array4[count]=i;
            count++;
        }
        startTime = System.nanoTime();
        quick.sort(array4, 0, array4.length-1);
        endTime = System.nanoTime();
        running_time_QuickSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_QuickSort, TimeUnit.NANOSECONDS);
        System.out.println("QuickSort(10000): "+seconds);
*/
        ///HeapSort
        count=0;
        for (int i = size4-1; i >=0 ; i--) {
            array4[count]=i;
            count++;
        }
        startTime = System.nanoTime();
        heap.sort(array4);
        endTime = System.nanoTime();
        running_time_HeapSort = endTime - startTime;
        seconds = (int) TimeUnit.MILLISECONDS.convert(running_time_HeapSort, TimeUnit.NANOSECONDS);
        System.out.println("HeapSort(10000): "+seconds);

    }
}
