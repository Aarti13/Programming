import java.io.*;
import java.util.*;

public class Main {
   static class Edge {
      int src;
      int nbr;
      int wt;

      Edge(int src, int nbr, int wt){
         this.src = src;
         this.nbr = nbr;
         this.wt = wt;
      }
   }
   
   // TC : O(V+E)
   // The time complexity of DFS if the entire tree is traversed is O(V) where V is the number of nodes. If the graph is represented as adjacency list: Here, each node maintains a list of all its adjacent edges. ... So, the time complexity in this case is O(V) + O(E) = O(V + E).

   public static boolean hasPath(  ArrayList<Edge>[] graph , int src , int dest , boolean[] visited ){
       
       if( src == dest ) return true;
       
       visited[src] = true;
       for(  Edge edge : graph[src] ){
           if( visited[edge.nbr] == false ){
               boolean hasNbrPath = hasPath(graph , edge.nbr , dest , visited);
               if( hasNbrPath ) return true;
           }
       }
       
       return false;
   }
   
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for(int i = 0; i < vtces; i++){
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for(int i = 0; i < edges; i++){
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }

      int src = Integer.parseInt(br.readLine());
      int dest = Integer.parseInt(br.readLine());
      
      boolean[] visited = new boolean[edges];
      boolean ans = hasPath(graph , src , dest , visited);
      System.out.println(ans );
    }

}