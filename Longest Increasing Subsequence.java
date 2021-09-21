import java.io.*;
import java.util.*;

public class Main {
    
	// aaega (compare with all previous one den cal Max one ), nhi aaega (khudse length banaegaa)
    public static int LongestIncreasingSubsequence(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int max = dp[0];
        
        for( int i=1 ; i<n  ; i++){
            for(int j=i-1 ; j>=0 ; j--){
               if( arr[i] > arr[j] ) {
                   dp[i] = Math.max(dp[i], dp[j] );
               }
            }
            dp[i] = dp[i]+1;
            max = Math.max(max , dp[i]);
        }
        
       return max;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for( int i =0 ; i<n ; i++){
            arr[i] = sc.nextInt();
        }
        
        int ans = LongestIncreasingSubsequence(arr);
        System.out.println(ans);
    }

}