package sources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Tester sınıf
 */
public class myTesterClass {

    /**
     * Result sınıfı
     * shortest path metodu ıcın result tutan sınıf
     */
    public static class Result{
        public static Vector<Integer> path;
        public static Integer distance;

        public Result(Vector<Integer> p, Integer dist){
            path=p;
            distance=dist;
        }

        public Vector<Integer> getPath(){return path;}
        public Integer getDist(){return distance;}
    }

    /**
     * Main metod
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        BufferedReader bf = new BufferedReader(new FileReader("test1.txt"));
        Scanner scann = new Scanner(bf);
        MatrixGraph objM = (MatrixGraph) AbstractGraph.createGraph(scann,true,"Matrix");
        int []pred=new int[9];
        double []cost=new double[9];
        Result res= shortest_path(objM,9,10);
        System.out.print("path:");
        for (int i = 0; i < res.getPath().size(); i++) {
            System.out.print(res.getPath().elementAt(i)+" ");
        }
        System.out.println();
        System.out.println("dist:"+res.getDist());
        System.out.println(is_connected(objM,9,10));
        plot_graph(objM);

        BufferedReader bf2 = new BufferedReader(new FileReader("test2.txt"));
        Scanner scann2 = new Scanner(bf2);
        MatrixGraph objM2 = (MatrixGraph) AbstractGraph.createGraph(scann2,false,"Matrix");
        System.out.println(is_acyclic_graph(objM2));
        System.out.println(is_undirected(objM2));



    }

    /**
     * Verilen vertexler arası en kısa yolu ve distance ı bulur
     * Shortest path helper
     *  @param g
     * @param start
     * @param end
     * @return Result
     */
    private static Result dijkstra(Graph g,int start,int end) {
        int nVertices = g.getNumV();
        int[] shortestDistances = new int[nVertices];
        boolean[] added = new boolean[nVertices];
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }
        shortestDistances[start] = 0;
        int[] parents = new int[nVertices];
        parents[start] = -1;
        for (int i = 1; i < nVertices; i++) {
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }
            if(nearestVertex==-1){
                break;
            }
            added[nearestVertex] = true;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                int edgeDistance = (int)g.getEdge(nearestVertex,vertexIndex).getWeight();
                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance +
                            edgeDistance;
                }
            }
        }
        if(shortestDistances[end]==Integer.MAX_VALUE){
            shortestDistances[end]=-1;
            return new Result(new Vector<Integer>(),shortestDistances[end]);
        }
        else
            return new Result(toVector(start,end, shortestDistances, parents),shortestDistances[end]);
    }

    /**
     * shortest path helper
     * @param startVertex
     * @param end
     * @param distances
     * @param parents
     * @return Vector
     */
    private static Vector<Integer> toVector(int startVertex, int end, int[] distances, int[] parents) {
        Vector<Integer> res=new Vector<>();
        getDistance(res,end, parents);
        return res;
    }

    /**
     * Shortestpath helper
     * @param vect
     * @param currentVertex
     * @param parents
     */
    private static void getDistance(Vector<Integer> vect, int currentVertex, int[] parents) {
        if (currentVertex == -1)
            return;
        getDistance(vect,parents[currentVertex], parents);
        vect.add(currentVertex);
    }

    /**
     * is_acyclic_graph helper
     * @param graph
     * @param v
     * @param discovered
     * @param departure
     * @param time
     * @return int
     */
    private static int DFS(Graph graph, int v, boolean[] discovered, int[] departure, int time) {
        discovered[v] = true;
        for (double u : graph.getMatrix()[v]) {
            if(u!=-1) {
                if (!discovered[(int)u])
                    time = DFS(graph,(int)u, discovered, departure, time);
            }
        }
        departure[v] = time++;

        return time;
    }

    /**
     * is_acyclic_graph helper
     * @param graph
     * @param N
     * @return true or false
     */
    private static boolean control(Graph graph, int N) {
        boolean[] discovered = new boolean[N];
        int[] departure = new int[N];
        int time = 0;
        for (int i = 0; i < N; i++)
            if (discovered[i] == false)
                time = DFS(graph, i, discovered, departure, time);
        for (int u = 0; u < N; u++) {
            for (double v : graph.getMatrix()[u]) {
                if(v!=-1){
                    if (departure[u] <= departure[(int)v])
                        return false;
                }
            }
        }

        // no back edges
        return true;
    }

    /**
     * graph directed or undirected control
     * @param g
     * @return true or false
     */
    public static boolean is_undirected(Graph g){
        Vector<Edge> vect=new Vector<>();
        for (int i = 0; i < g.getNumV(); i++) {
            for (int j = 0; j < g.getNumV(); j++) {
                if(g.isEdge(i,j)==true){
                    if(g.getEdge(i,j).getWeight()!=-1){
                        vect.add(g.getEdge(i,j));
                    }
                }
            }
        }
        for (int i = 0; i < g.getNumV(); i++) {
            for (int j = 0; j < g.getNumV(); j++) {
                if(g.isEdge(i,j)==true){
                    if(g.getEdge(i,j).getWeight()!=-1){
                        if(!(vect.contains(g.getEdge(j,i)) && g.getEdge(j,i).getWeight()==g.getEdge(i,j).getWeight()))
                            return false;
                        if(!vect.contains(g.getEdge(j,i)))
                            return true;
                    }
                }
            }
        }
        return true;
    }

    /**
     * graph cyclic or acyclic control
     * @param g
     * @return true or false
     */
    public static boolean is_acyclic_graph(Graph g){
        return control(g,g.getNumV());
    }

    /**
     * graph connected or disconnected control
     * @param g
     * @param v1
     * @param v2
     * @return true or false
     * @throws Exception
     */
    public static boolean is_connected(Graph g,int v1,int v2) throws Exception{
        if(shortest_path(g,v1,v2).getPath().size()==0)
            return false;
        else
            return true;
    }

    /**
     * Return shortest path between given vertex
     * @param g
     * @param v1
     * @param v2
     * @return Result
     */
    public static Result shortest_path(Graph g,int v1,int v2){
        if((v1>=0 && v1<g.getNumV()) && (v2>=0 && v2<g.getNumV())){
            return dijkstra(g,v1,v2);
        }else{
            System.out.println("Wrong vertex");
            return new Result(new Vector<Integer>(),new Integer(-1));
        }

    }

    /**
     * print to graph
     * @param g
     */
    public static void plot_graph(Graph g){
        for (int i = 0; i < g.getNumV(); i++) {
            for (int j = 0; j < g.getNumV(); j++) {
                if(g.isEdge(i,j)==true){
                    if(g.getEdge(i,j).getWeight()!=-1){
                        System.out.println(g.getEdge(i,j).toString());
                    }
                }
            }
            System.out.println("-----");
        }
    }

}
