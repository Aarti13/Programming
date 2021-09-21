import java.io.*;
import java.util.*;

public class Main {
   static class Edge {
      int src;
      int nbr;

      Edge(int src, int nbr) {
         this.src = src;
         this.nbr = nbr;
      }
   }
   
   // order of work opposite to topological sort
   // topological sort : a permutation of vertices in a directed acyclic graph in which for (u, v) u appears before v in a graph
   
   //use DFS
   // travel and add in stack in post order 
   // why not in pre order because we are checking if u depends on v so we have to print u frst den v but in this case u will print after v
   public static void topologicalSort( ArrayList<Edge>[] graph ,int src , boolean[] visited , Stack<Integer> st   ){
      
       visited[src]=true;
       for( Edge edge : graph[src]){
           if( !visited[edge.nbr] ) topologicalSort(graph , edge.nbr , visited , st);
                 
       }
       st.push(src); 
       
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         graph[v1].add(new Edge(v1, v2));
      }

     boolean[] visited = new boolean[vtces];
     Stack<Integer> st = new Stack<>();
     for( int i =0 ; i<graph.length ; i++ ){
         if(!visited[i]) topologicalSort(graph , i , visited , st);
     }
     
     while(st.size()>0 ){
           System.out.println( st.pop());
       }
   }

}