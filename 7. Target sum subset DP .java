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
        
        boolean[][] dp= new boolean[m+1][ tar+1]; 
        
        for( int i=0; i<dp.length ; i++ )
        {
         for( int j=0 ; j<dp[0].length ; j++ )   
           {
                    
            if( i==0 && j==0 )  dp[i][j] = true;
            else if( i ==0 )  dp[i][j] = false ;
            else if( j == 0 )  dp[i][j]  = true;
            else 
            {
                if( dp[i-1][j]  ) dp[i][j]=true;
                else{
                    int val = arr[i-1];
                    if( j >= val ){
                        if( dp[i-1][ j- val] )  dp[i][j]=true;
                    }
                }
            }
                      
            }
        }
       
        System.out.println( dp[dp.length-1][dp[0].length-1] );
    }

}