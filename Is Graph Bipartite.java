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
   
   static class Pair{
       int vtx;
       String psf;
       int level;
       
       Pair(int vtx ,String psf , int level ){
           this.vtx = vtx;
           this.psf = psf;
           this.level = level;
       }
   }
    
    public static boolean isBipartite(  ArrayList<Edge>[] graph , int src , int[] visited ){
        
        Queue<Pair> que = new ArrayDeque<>();
        Pair p = new Pair( src , src+"", 0);
        que.add(p);
        
        while( que.size()>0 ){
            
            Pair np = que.remove();
            
            if(visited[np.vtx] != -1  ){ 
                if(visited[np.vtx] != np.level ) return false;
            }
            else visited[np.vtx] = np.level;
        
            for(Edge edge : graph[np.vtx]){
                if(visited[edge.nbr] == -1 ){ 
                   
                    que.add(new Pair(edge.nbr , np.psf+ edge.nbr , np.level+1 ));
                }
            }
            
        }
        return true;
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
        
        int[] visited = new int[vtces];
        for( int i =0 ; i<vtces ; i++ ){
            visited[i] = -1;
        }
       
       for( int i =0 ; i<vtces ; i++ ){
           if(visited[i] == -1 ){
               boolean ans = isBipartite( graph , i , visited);
               if(!ans) {
                   System.out.println("false");
                   return;
               }
           }
       }
       
      System.out.println("true");
   }
}