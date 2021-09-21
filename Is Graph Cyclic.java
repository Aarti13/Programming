import java.io.*;
import java.util.*;

public class Main {
   static class Edge {
      int src;
      int nbr;
      int wt;

      Edge(int src, int nbr,int wt) {
         this.src = src;
         this.nbr = nbr;
         this.wt=wt;
      }
   }
    public static class Pair{
        int vt;
        String psf ;
        
        Pair(int vt , String psf){
            this.vt = vt;
            this.psf = psf;
        }
    }
    
    public static boolean isCyclic(ArrayList<Edge>[] graph , int src , boolean[] visited ){
       
        Queue<Pair> que = new ArrayDeque<>();
        Pair mp = new Pair(src , src+"");
        que.add(mp);
        
        while(que.size() > 0 ){
            // remove mark* work add*
            Pair p = que.remove();
            if( visited[p.vt] == true ) return true;
          
            visited[p.vt] = true;
            //System.out.println( p.vt +"@" +p.psf );
            
            for(Edge edge : graph[p.vt] ){
                if( !visited[edge.nbr] )  que.add(new Pair( edge.nbr , p.psf+edge.nbr ));
            }
        }
        return false;
        
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
         graph[v1].add(new Edge(v1, v2,wt));
         graph[v2].add(new Edge(v2, v1,wt));
      }

      boolean[] visited = new boolean[vtces];
      boolean ans=false;
      for(int i=0;i<vtces;i++){
        if(!visited[i]){  
          ans = isCyclic(graph , i , visited);
          if(ans) break;
        }
      }
      System.out.println(ans);
   }
}