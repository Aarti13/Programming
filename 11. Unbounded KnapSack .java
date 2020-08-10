import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        
        int[] val = new int[m];
        
        for( int i=0;i<m ; i++){
                val[i] = sc.nextInt();
        }
        
        int[] wt = new int[m];
        
        for( int i=0;i<m ; i++){
                wt[i] = sc.nextInt();
        }
        int cap = sc.nextInt();
        
        int[]dp= new int[cap+1]; 
        
        dp[0] = 0;
        for( int i = 1; i < dp.length ; i++ ){
           for(int j=0 ; j<wt.length ; j++ ){
               
               if( i >= wt[j] ) dp[i] = Math.max( dp[i - wt[j] ] + val[j] , dp[i] );
           }
        }
                      
        System.out.println( dp[dp.length-1] );
    }
}