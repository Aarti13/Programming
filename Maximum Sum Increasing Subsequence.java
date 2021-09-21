import java.io.*;
import java.util.*;

public class Main {

    public static void MaximumSumIncreasingSubsequence(int[] arr){
        int n = arr.length ;
        int[] dp = new int[n];
        dp[0]=arr[0];
        
        int maxSum = dp[0];
        for( int i=1; i<n ; i++){
            int sum = Integer.MIN_VALUE;
            for( int j=i-1 ; j>=0 ; j--){
                if( arr[i] >= arr[j] ){
                    dp[i] = Math.max(dp[i] , dp[j] );
                }
            }
            dp[i] += arr[i];
            maxSum = Math.max(maxSum,dp[i]);
        }
        System.out.println(maxSum);
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for( int i=0 ; i<n ; i++){
            arr[i] = sc.nextInt();
        }
        
        MaximumSumIncreasingSubsequence(arr);   
    }

}