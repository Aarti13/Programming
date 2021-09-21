import java.io.*;
import java.util.*;

public class Main {

// Maximum Sum Of Three Non-overlapping Subarrays

// left - mid - right 
// left DP  : storage from left side max subarray sum , 0th col value , 1th col subaaray start point in lexigraphically
// right Dp : storage from right side max subarray sum
// mid : storage sum of 0 to ith idx

	public static void solution(int[] arr, int k){
        
        int n = arr.length;
        int[][] leftDP = new int[n][2];
        int[][] rightDP = new int[n][2];
        int[] midDP = new int[n];
        
        int sum = 0 ;
        // left side
        for( int i=0 ; i<n ; i++ ){
            if( i < k ){
                sum += arr[i];
                leftDP[i][0] = sum;
                leftDP[i][1] = 0;
            }
            else{
                sum += arr[i] - arr[i-k];
                if( sum > leftDP[i-1][0]){
                  leftDP[i][0] = sum;
                  leftDP[i][1] = i-k+1;
                }
                else{
                  leftDP[i][0] = leftDP[i-1][0];
                  leftDP[i][1] = leftDP[i-1][1]; 
                }
            }
        }
        
        sum = 0 ;
        // right side
        for( int i=n-1 ; i>=0 ; i-- ){
            if( n-i <= k ){
                sum += arr[i];
                rightDP[i][0] = sum;
                rightDP[i][1] = i;
            }
            else{
                sum += arr[i] - arr[i+k];
                if( sum >= rightDP[i+1][0]){
                  rightDP[i][0] = sum;
                  rightDP[i][1] = i;
                }
                else{
                  rightDP[i][0] = rightDP[i+1][0];
                  rightDP[i][1] = rightDP[i+1][1]; 
                }
            }
           // System.out.println( rightDP[i][0] + " "+ rightDP[i][1]);
        }
        
        sum =0 ;
        // mid
        for( int i =0 ;i<n ; i++ ){
            sum += arr[i];
            midDP[i] = sum ;
        }
        
        // compare left + mid( in range of k length subarray ) + right
        sum = 0;
        int ans = Integer.MIN_VALUE;
        int left = 0 , right = 0 , mid = 0; // for idx
        
        for( int i=k-1 , j=i+k+1 ; j<n-k+1 ; i++ , j++ ){
            sum = leftDP[i][0] + ( midDP[j-1] - midDP[i] ) +rightDP[j][0];
            //System.out.println( leftDP[i][0] +"  "+ ( midDP[j-1] - midDP[i] )+"  " +rightDP[j][0] );
            if( sum > ans ){
                ans = sum;
                left = leftDP[i][1];
                right = rightDP[j][1];
                mid = i+1;
            }
        }
        
        System.out.println( ans +" "+left+" "+mid+" "+right);
	}
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int[] arr = new int[n];
		for(int i = 0 ; i < arr.length; i++){
			arr[i] = scn.nextInt();
		}
		int k = scn.nextInt();
		solution(arr,k);
	}

}