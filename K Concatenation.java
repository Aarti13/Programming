import java.io.*;
import java.util.*;

public class Main {

// kadanes algo
    public static int kadanes(int[] arr) {
        
        int n = arr.length;
        int currMax = arr[0];
        int ans = arr[0];
        for( int i=1 ; i< n ; i++ ){
        
        currMax = Math.max( currMax+arr[i] , arr[i] );
        ans = Math.max( ans , currMax );
        
        }
       return ans ; 
      }

  //Maximum Sum Subarray With At Least K Elements
  public static int solution(int[] arr, int k , int sum ) {
    
    int[] a = new int[arr.length * 2 ];
    for( int i =0 ; i< arr.length*2 ; i++ ){
            a[i] = arr[i % arr.length ];
    }
    
    if( k == 1 ) return kadanes(arr);
    else if( sum < 0 ){
        return kadanes(a);
    }
    else{
        
        int res = kadanes(a);
        //System.out.print(res);
        int ans = res + (k-2)* sum ;
        return ans;
    }
   // return 0;
}

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
      sum += arr[i];
    }
    int k = scn.nextInt();
    System.out.println(solution(arr, k , sum));
  }
}











import java.io.*;
import java.util.*;

public class Main {
 
  public static int kadanes( int[] arr , int k , int n ){
    
    int currSum =0 ;
    int ans = Integer.MIN_VALUE;
    int i=0 ;
    
    while( i < n ){    
        if( k == 0 ) break;
        
        currSum += arr[i];
        ans = Math.max(ans , currSum );
        if( currSum < 0 ) currSum = 0 ;
        
        i++;
        if( i % n == 0 ) {
            k--;
            i=0;
        }
    } 
    return ans;
 }
  public static long solution(int[] arr, int k, long sum) {
    
    //O(k*n)
    // traversing whole araay k times 
    
    // int n = arr.length;
    // int currSum =0 ;
    // int ans = Integer.MIN_VALUE;
    // int i=0;
    
    // while( i < n ){    
    //     if( k == 0 ) break;
        
    //     currSum += arr[i];
    //     ans = Math.max(ans , currSum );
    //     if( currSum < 0 ) currSum = 0 ;
        
    //     i++;
    //     if( i % n == 0 ) {
    //         k--;
    //         i=0;
    //     }
    // }
    
    //TC : O(n)
    // only find kadanes of two den implement basic math
    // k == 1 kadanes
    // sum of whole araay is < 0 kadanes of 1st 2 copies only 
    // sum > 0 kadanes of 1st 2 copies + (k-2)* sum
    
    int n = arr.length;
    
    if( k == 1 )  return kadanes(arr , 1 , n );
    else{
       int ans = kadanes(arr , 2 , n); 
      
       if( sum < 0 ) return ans;
       else return ans + (k-2)*sum;
    } 
    
  }

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    long sum = 0;
    for (int i = 0; i < arr.length; i++) {
      arr[i] = scn.nextInt();
      sum += arr[i];
    }
    int k = scn.nextInt();
    System.out.println(solution(arr, k, sum));
  }

}