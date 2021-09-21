import java.io.*;
import java.util.*;

public class Main {
   
    public static class Edge{
        int vtx ;
        int nbr ;
        
        Edge( int vtx , int nbr ){
            this.vtx = vtx ;
            this. nbr = nbr ;
        }
    }
    
    public static void GCC( ArrayList<Edge>[] graph , boolean[] visited , int src,ArrayList<Integer> al ){
        
        visited[src] = true;
        al.add(src);
        for(Edge edge : graph[src] ){
            if( !visited[edge.nbr] ){
                GCC(graph , visited , edge.nbr , al );
            }
        }
     
    }
    
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int n = Integer.parseInt(br.readLine());
      int k = Integer.parseInt(br.readLine());
   
    ArrayList<Edge>[] graph = new ArrayList[n]; 
    for( int i = 0 ; i< n ; i++){
        graph[i] = new ArrayList<>();
    }
    
    for( int i =0 ; i<k ; i++){
          String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            graph[v1].add(new Edge(v1, v2));
            graph[v2].add(new Edge(v2, v1));
    }
    
    ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
    boolean visited[] = new boolean[n];
    
    for( int i = 0 ; i<n ; i++ ){
        if(!visited[i] ){
            ArrayList<Integer> al = new ArrayList<>();
            GCC(graph,visited,i , al );
            ans.add(al);
        } 
    }
   
    int sum = 0 ;
    for( int i = 0 ; i<ans.size() ; i++ ){
       for( int j = i+1 ; j<ans.size()  ; j++ ){
            int is = ans.get(i).size()  ;
            int js = ans.get(j).size() ;
            sum = sum+( is * js );
        }
    }
    
    System.out.println(sum);
   }

}