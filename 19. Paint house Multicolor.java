import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int nh = sc.nextInt();
        int nc = sc.nextInt();
        
        int[][] arr = new int[nh][nc];
        
        for(int i =0;i<nh ; i++){
            for(int j =0;j<nc ; j++){
                arr[i][j] = sc.nextInt();
            }
        }
        
        int[][] dp = new int[nh][nc];
        
        // O(n*n*n) formula
        // for(int i =0;i<nc ; i++)  dp[0][i] = arr[0][i];
        
        // for(int i =1; i<nh ; i++){
        //     for(int j =0 ;j<nc ; j++){
        //         int min = 100000;
                
        //       for(int k =0 ; k <nc ; k++){
        //           if( j != k)  min = Math.min( dp[i-1][k] , min );
        //       }
               
        //       dp[i][j] = min+arr[i][j];
              
        //     }
        // }
        
        // int min = 100000;
        // for(int j =0;j<nc ; j++){
        //         min = Math.min( min , dp[nh-1][j] );
        // }
        
        // System.out.println(min);
        
        // O(n*n)  using least and second least form each row 
        
        int least = Integer.MAX_VALUE;
        int sleast = Integer.MAX_VALUE;        
        
        for(int j =0;j<nc ; j++){
                dp[0][i] = arr[0][i];
            
                if(arr[0][j] < least   ){
                    sleast = least;
                    least = arr[0][j];
                }
                else if( arr[0][j] < sleast)  sleast = arr[0][j];
        }
        
        for(int i =1; i<nh ; i++){
            
            int nleast = Integer.MAX_VALUE;
            int nsleast = Integer.MAX_VALUE;
            
            for(int j =0 ;j<nc ; j++){
            
            if( dp[i-1][j] != least )  dp[i][j] = least+arr[i][j];
            else dp[i][j] = sleast+arr[i][j];
            
            if(dp[i][j] < nleast   ){
                nsleast = nleast;
                nleast = dp[i][j];
            }
            else if( dp[i][j] < nsleast)  nsleast = dp[i][j];
            }
            
            least = nleast;
            sleast = nsleast;
    
        }
        System.out.println(least);
    }
}