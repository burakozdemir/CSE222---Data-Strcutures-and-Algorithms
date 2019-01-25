package sources.Q2;

import sources.AbstractGraph;
import sources.MatrixGraph;
import sources.myTesterClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Q2Test {
    public static void main(String []args) throws Exception {
        BufferedReader bf = new BufferedReader(new FileReader("src/sources/q2Test"));
        Scanner scann = new Scanner(bf);
        MatrixGraph objM = (MatrixGraph) AbstractGraph.createGraph(scann,false,"Matrix");
        System.out.println("plot_graph(objM)");
        myTesterClass.plot_graph(objM);
        System.out.println("is_undirected(objM):"+myTesterClass.is_undirected(objM));
        System.out.println("is_acyclic_graph(objM):"+myTesterClass.is_acyclic_graph(objM));
        System.out.println("------------------------");

        System.out.println("is_connected(objM,0,4):"+myTesterClass.is_connected(objM,0,4));
        System.out.println("------------------------");

        System.out.println("is_connected(objM,4,12):"+myTesterClass.is_connected(objM,4,12));
        System.out.println("------------------------");

        System.out.println("is_connected(objM,9,11):"+myTesterClass.is_connected(objM,9,11));
        System.out.println("------------------------");

    }

}
