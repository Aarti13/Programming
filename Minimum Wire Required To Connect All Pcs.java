import java.io.*;
import java.util.*;

public class Main {
   static class Edge {
      int src;
      int nbr;
      int wt;

      Edge(int src, int nbr, int wt) {
         this.src = src;
         this.nbr = nbr;
         this.wt = wt;
      }
   }
    
    static class Pair implements Comparable<Pair>{
        int vtx;
        int wt;
        int pvtx;
        
        Pair( int vtx , int wt , int pvtx){
            this.vtx = vtx;
            this.wt = wt;
            this.pvtx = pvtx;
        }
        
        public int compareTo(Pair o){
            return this.wt - o.wt ;
        }
    }
    
    public static void prims(ArrayList<Edge>[] graph  , int src , boolean[] visited ){
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add( new Pair(src , 0 , -1 ));
        
        while(pq.size()> 0 ){
            Pair np = pq.remove();
             
            if( visited[np.vtx] ) continue;
            visited[np.vtx] = true;
        
            if(np.pvtx != -1 ) System.out.println("["+np.vtx +"-"+np.pvtx + "@"+np.wt+"]");
            for(Edge edge : graph[np.vtx]){
                if( !visited[edge.nbr]){
                    pq.add(new Pair( edge.nbr , edge.wt  , np.vtx )) ;
                }
            }
        }
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
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }

      
      boolean[] visited = new boolean[vtces];
      
      prims( graph , 0 , visited );
      
   }
}