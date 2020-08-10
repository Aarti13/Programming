//Infinite Coins: 
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        
        int[] arr = new int[m];
        
        for( int i=0;i<m ; i++){
                arr[i] = sc.nextInt();
        }
        
        int tar = sc.nextInt();
        
        int[] dp= new int[ tar+1]; 
        
        dp[0] = 1;
        
        
        for( int i = 1; i <dp.length ; i++ ){
            for( int j =0 ; j < arr.length ; j++ ){
                
               if(arr[j] <= i ) dp[i] += dp[ i - arr[j] ] ;  
            }
        }
                      
        System.out.println( dp[dp.length-1] );
    }
}