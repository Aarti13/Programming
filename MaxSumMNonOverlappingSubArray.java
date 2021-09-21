import java.io.*;
import java.util.*;

public class Main {

// Maximum Sum Of M Non-overlapping Subarrays
// 2 10 7 18 5 33 0
// 12 17 25 23 33

// recursion

// memoization TC : n*(m+1)
	public static int solution(int[] arr, int m, int k, int[] psum, int idx , int[][] dp){
        
        if( m == 0 ) return 0;
        if( idx >= arr.length ) return 0;
        if( dp[idx][m] != 0) return dp[idx][m];
        
        int include = 0 + psum[idx] + solution( arr, m-1 , k , psum , idx+k ,dp);
        int exclude = 0 + solution( arr, m , k , psum , idx+1 , dp );
        
        int ans = Math.max(include , exclude );
        dp[idx][m] = ans;
		return ans;
	}
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int[] arr = new int[n];
		for(int i = 0 ; i < arr.length; i++){
			arr[i] = scn.nextInt();
		}
        int m = scn.nextInt();
        int k = scn.nextInt();
        
        // find the sum of frst k elelments
        int sum = 0;
        for( int i=0 ; i<k ; i++ ){
            sum += arr[i];
        }
        
        int[] psum = new int[n];
        psum[0] = sum;
        for( int i=1 , j=i+k-1 ; i<=n-k ; i++ , j++ ){
            psum[i] = psum[i-1]+arr[j]-arr[i-1] ;
            //System.out.print(psum[i]+" ");
        }
        
    //   for( int i=k , j=0 ; i<n ; i++ , j++ ){
    //       sum += arr[i]-arr[j];
    //       psum[i-k+1] = sum;
    //       System.out.print(psum[i-k+1]+" ");
    //   }
        
        int[][] dp = new int[n][m+1];
        System.out.println(solution(arr, m , k, psum , 0 , dp));
	}

}