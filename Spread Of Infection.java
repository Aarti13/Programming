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
    static class Pair{
        int vtx;
        int level ;
        
        Pair( int vtx , int level ){
         this.vtx = vtx;
         this.level = level;
        }
    }
    
    public static int count = 0 ;
    public static void spreadInjection( ArrayList<Edge>[] graph , int src , int time , boolean[] visited ){
        
        Queue<Pair> que= new ArrayDeque<>();
        que.add(new Pair(src , 1)) ;
        
        while(que.size() > 0 ){
            Pair np = que.remove();
            
            if( visited[np.vtx] ) continue;
            if(np.level > time )
             break;
            count ++;;
            visited[np.vtx] = true;
            for(Edge edge : graph[np.vtx]){
                if( !visited[edge.nbr] ){
                    
                    que.add(new Pair(edge.nbr , np.level+1 ));
                    
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
         graph[v1].add(new Edge(v1, v2));
         graph[v2].add(new Edge(v2, v1));
      }

      int src = Integer.parseInt(br.readLine());
      int t = Integer.parseInt(br.readLine());
      
      boolean[] visited = new boolean[vtces];
      
    spreadInjection(graph , src , t ,visited );
    System.out.println(count);
   }

}