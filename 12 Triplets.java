import java.io.*;
import java.util.*;

public class Main {
    
    //Triplets - 1
    /*
    //Question:
    1. You are given an array(arr) of N numbers.
    2. You have to select three indices i,j,k following this condition -> 
      0 <= i < j <= k < arr.length
    3. Two numbers X and Y as defined as :
      X = arr[i] ^ arr[i+1] ^ ... ^ arr[j - 1]
      Y = arr[j] ^ arr[j+1] ^ ... ^ arr[k]
    4. You have to print the number of triplets where X is equal to Y.
    
    solution:
    // BF aaproach O(n^3 ) 3 loops of i , j , k and find if x == y c++
    
    //2nd one:
    1 3 2 5 7 2 7 5 6 4
    find pos of i to j where XOR of array ele is 0 den ans = k-i
    eg i = 2 , k = 7 ans = 5 set in which x = y
    */
	public static void solution(int[] arr){
		//O(n^2)
		int c=0, n =arr.length;
		for( int i =0 ; i<n ;i++){
		    int val = arr[i];
		    for( int k =i+1 ; k<n ;k++){
		        val = val ^ arr[k];
		        if( val == 0 ) c += k-i;
		    }
		}
		System.out.println( c);
    }
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0 ; i < arr.length; i++){
            arr[i] = scn.nextInt();
        }
        solution(arr);
    }
	
	
}