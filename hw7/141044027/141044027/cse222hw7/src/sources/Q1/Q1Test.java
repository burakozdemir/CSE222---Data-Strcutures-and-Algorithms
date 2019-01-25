package sources.Q1;

import sources.AbstractGraph;
import sources.MatrixGraph;
import sources.myTesterClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Q1Test {

    public static void main(String []args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("src/sources/q1Test"));
        Scanner scann = new Scanner(bf);
        MatrixGraph objM = (MatrixGraph) AbstractGraph.createGraph(scann,true,"Matrix");
        System.out.println("plot_graph(objM)");
        myTesterClass.plot_graph(objM);
        System.out.println("is_undirected(objM):"+ myTesterClass.is_undirected(objM));
        System.out.println("is_acyclic_graph(objM):"+ myTesterClass.is_acyclic_graph(objM));
        System.out.println("------------------------");

        myTesterClass.Result res= myTesterClass.shortest_path(objM,0 ,2);
        System.out.print("shortest_path(0,2):");
        for (int i = 0; i < res.getPath().size(); i++) {
            System.out.print(res.getPath().elementAt(i)+" ");
        }
        System.out.println();
        System.out.println("dist:"+res.getDist());

        System.out.println("------------------------");

        res= myTesterClass.shortest_path(objM,0 ,4);
        System.out.print("shortest_path(0,4):");
        for (int i = 0; i < res.getPath().size(); i++) {
            System.out.print(res.getPath().elementAt(i)+" ");
        }
        System.out.println();
        System.out.println("dist:"+res.getDist());

        System.out.println("------------------------");

        res= myTesterClass.shortest_path(objM,0 ,7);
        System.out.print("shortest_path(0,7):");
        for (int i = 0; i < res.getPath().size(); i++) {
            System.out.print(res.getPath().elementAt(i)+" ");
        }
        System.out.println();
        System.out.println("dist:"+res.getDist());

    }

}
