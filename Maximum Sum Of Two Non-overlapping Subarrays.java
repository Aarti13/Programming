import java.io.*;
import java.util.*;

public class Main {

// Maximum Sum Of Two Non-overlapping Subarrays

//          0   1   2   3   4   5   6   7   8
//   arr   3 , 8 , 1 , 3 , 2 , 1 , 8  , 9 , 0  x =2  y = 3  op= 35 , 
//  
//         0    6   5   2   2   5   1   9   4  x=1 y=2  op = 20

// 1  x length subarray (left)- y length subarray( right)
// 2  y length subarray (left)- x length subarray( right)
//  ans can be in form 1 and 2

// 1st method
//  dp1[i] stores max sum of subarray with x length elements
//         0   6   6    6   6   6   6   9   9     

//  dp2[i] stores max sum of subarray with y length elements
//        13  13  13  13   13  13   13  13  4   
// max compare start hoga dp1 ((x-1) , n ) to dp2( y-1 , (n-y)) tak : 19

//        0   6   11    11  11    11  11    11 13
//        9   9    9    9    9    9    9    9  4
// max compare : 20
// total comparision : max( 20 , 19) 20

// TC:O(n^2)
	public static int solution(int[] arr, int x, int y){
        
        int n = arr.length ;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        
        int sum =0 ;
        
        // 1st way x(left) - y(right)
        // for x ele subarray max sum
        for( int i=0 ; i< n ; i++){
            if( i < x ){
                sum += arr[i];
                dp1[i] = sum;
            }
            else {
                sum += arr[i] - arr[i-x];
                dp1[i] = Math.max( dp1[i-1] , sum );
            }
        }
        
        sum=0;
        // for y ele subarray max sum 
        for( int i=n-1 ; i>=0 ; i--) {
            if( y >= n-i){
                sum += arr[i];
                dp2[i] = sum;
            }
            else{
                sum += arr[i] - arr[i+y];
                dp2[i] = Math.max( dp2[i+1] , sum ); 
            }
        }
        
        // compare both dp1 and dp2 
        int max1 = Integer.MIN_VALUE;
        for( int i=x-1 , j=i+1 ; j<n-y ; i++ , j++ ){
            max1 = Math.max( dp1[i]+dp2[j] , max1 );
        }
        
        // 2nd way  y(left) - x(right) 
        sum=0;
        // for y ele subarray max sum
        for( int i=0 ; i< n ; i++){
            if( i < y ){
                sum += arr[i];
                dp1[i] = sum;
            }
            else {
                sum += arr[i] - arr[i-y];
                dp1[i] = Math.max( dp1[i-1] , sum );
            }
        }
        
        sum=0;
        // for x ele subarray max sum 
        for( int i=n-1 ; i>=0 ; i--) {
            if( x >= n-i){
                sum += arr[i];
                dp2[i] = sum;
            }
            else{
                sum += arr[i] - arr[i+x];
                dp2[i] = Math.max( dp2[i+1] , sum ); 
            }
        }
        
        // compare both dp1 and dp2 
        int max2 = Integer.MIN_VALUE;
        for( int i=y-1 , j=i+1 ; j<n-x ; i++ , j++ ){
            max2 = Math.max( dp1[i]+dp2[j] , max2 );
        }
        
        return Math.max(max1, max2);
	}
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int[] arr = new int[n];
		for(int i = 0 ; i < arr.length; i++){
			arr[i] = scn.nextInt();
		}
		int x = scn.nextInt();
		int y = scn.nextInt();
		System.out.println(solution(arr,x,y));
	}

}