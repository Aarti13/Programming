import java.io.*;
import java.util.*;

public class Main {
    
    // bitonic subsequence begins with elements in increasing order, followed by elements in decreasing order 
    public static void LongestBitonicSubsequence(int[] arr){
        // input : 10,22,9,33,21,50,41,60,80,1
        // LIS :   1, 2, 1, 3, 2, 4, 4, 5, 6,1
        // LDS :   3, 3, 2, 3, 2, 3, 2, 2, 2,1 
        // ans : LIS+LDS -1 
        //         1, 3, 2, 5, 3, 6, 4, 6, 7,1 : ans will of max of arr : 7 
        
        int n = arr.length ;
        int[] LISDp = new int[n];
        LISDp[0] = 1;
        int[] LDSDp = new int[n];
        LDSDp[n-1] = 1;

        //Longest inc subsequence
        for( int i=1; i<n ; i++){
            for( int j=i-1 ; j>=0 ; j--){
                if( arr[i] > arr[j] ) LISDp[i]= Math.max( LISDp[i] , LISDp[j]);
            }
            LISDp[i] += 1;
        }

        //Longest dec subsequence
        for( int i=n-2; i>=0 ; i--){
            for( int j=i+1 ; j<n ; j++){
                if( arr[i] > arr[j] ) LDSDp[i]= Math.max( LDSDp[i] , LDSDp[j]);
            }
            LDSDp[i] += 1;
        }
        
        //ans 
        int maxC = Integer.MIN_VALUE;
        for( int i=0 ; i<n ; i++){
            maxC = Math.max(maxC ,LDSDp[i] + LISDp[i]-1 );
        }
        
        System.out.println(maxC);
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for( int i=0 ; i<n ; i++){
            arr[i] = sc.nextInt();
        }
        LongestBitonicSubsequence(arr);
    }

}