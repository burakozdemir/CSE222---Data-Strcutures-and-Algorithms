package sources.Q3;

import sources.AbstractGraph;
import sources.MatrixGraph;
import sources.myTesterClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Q3Test {
    public static void main(String []args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("src/sources/q3Test"));
        Scanner scann = new Scanner(bf);
        MatrixGraph objM = (MatrixGraph) AbstractGraph.createGraph(scann,false,"Matrix");
        System.out.println("plot_graph(objM)");
        myTesterClass.plot_graph(objM);
        System.out.println("is_undirected(objM):"+myTesterClass.is_undirected(objM));
        System.out.println("is_acyclic_graph(objM):"+myTesterClass.is_acyclic_graph(objM));
        System.out.println("------------------------");

        System.out.println("DFS(objM):");
        //myTesterClass.DFS(objM,0);
    }
}
