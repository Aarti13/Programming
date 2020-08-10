import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int[] arr = new int[n];
        for( int i=0;i<arr.length ; i++){
            arr[i]= sc.nextInt();
        }
        
        Integer[] dp = new Integer[n+1];
        dp[n]=0;
        
        for( int i = n-1 ; i >= 0 ; i-- ){
            
            int m = Integer.MAX_VALUE;
            
            if(arr[i] != 0){
                for( int j =1 ; j<= arr[i] &&  i+j < dp.length ; j++){
                    
                   if( dp[i+j] != null ) m = Math.min( m , dp[i+j] );
                }
                
                if( m != Integer.MAX_VALUE ) dp[i] = m+1;
            }
        }
        System.out.println( dp[0] );
    }
}