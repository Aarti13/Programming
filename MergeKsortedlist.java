import java.io.*;
import java.util.*;

public class Main {
    
    public static class Pair implements Comparable<Pair>{
        int li ; //list index
        int di ; //data index
        int val; // value 
        
        Pair( int li , int di , int val ){
            this.di = di;
            this.li = li;
            this.val = val;
        }
        
        public int compareTo( Pair o){
            return this.val - o.val;  //+ this is greater  - o is greater  0 same 
        }
    }
    // merge k sorted lists
	Space complextiy = O(k)
	Time complexity = nlogk

   public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> list){
      ArrayList<Integer> rv = new ArrayList<>();
      PriorityQueue<Pair> pq = new PriorityQueue<>();
      
     for( int i =0 ;i<list.size() ; i++ ){
        Pair p = new Pair( i , 0 , list.get(i).get(0) );
        pq.add(p);
     }
     
     while( pq.size() > 0 ){
        Pair p = pq.remove();
        System.out.print( p.val+" ");
        p.di++;
        
        if( p.di < list.get(p.li).size() ){
            p.val = list.get(p.li).get(p.di);
            pq.add(p);
        }
     }

      return rv;
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int k = Integer.parseInt(br.readLine());
      ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
      for(int i = 0; i < k; i++){
         ArrayList<Integer> list = new ArrayList<>();

         int n = Integer.parseInt(br.readLine());
         String[] elements = br.readLine().split(" ");
         for(int j = 0; j < n; j++){
            list.add(Integer.parseInt(elements[j]));
         }

         lists.add(list);
      }

      ArrayList<Integer> mlist = mergeKSortedLists(lists);
      for(int val: mlist){
         System.out.print(val + " ");
      }
      System.out.println();
   }

}