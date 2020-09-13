// Priority Queue : peek() // O(1) add , remove O(logn) , sorting // nlogn
//java by default min PQ

import java.io.*;
import java.util.*;

public class Main {
    
//k largest elelments 
// tc: O(nlogk)  sc : O(n)
    public static void klargE( int[] arr , int k){
        
        PriorityQueue<Integer> pq = new PriorityQueue();
        for( int i=0;i<arr.length ; i++  ){
            if( i< k)
                 pq.add( arr[i] );
            else
            {
                if( arr[i] > pq.peek() ) {
                    pq.remove();
                    pq.add( arr[i] );
                }
            }
        }
        
        while( pq.size() > 0 ){
            int ele = pq.peek();
            System.out.println( ele);
            pq.remove();
        }
    }

        // k sorted Array
    public static void ksortedArray( int[] arr , int k){
        
        PriorityQueue<Integer> pq = new PriorityQueue();
        // because 0 place pe upto k elelment tak aa skte hai
        for( int i = 0 ;i<= k ; i++){
            pq.add( arr[i]);
        }
        
        for( int i=k+1 ;i<arr.length ; i++  ){
           
            int ele = pq.peek();
            System.out.println( ele);
            pq.remove();
            pq.add( arr[i] );
        }
        
        while( pq.size()> 0)
         System.out.println( pq.remove());
    }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      int[] arr = new int[n];

      for (int i = 0; i < n; i++) {
         arr[i] = Integer.parseInt(br.readLine());
      }

      int k = Integer.parseInt(br.readLine());
      klargE( arr , k );
    }

}