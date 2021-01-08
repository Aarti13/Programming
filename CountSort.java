import java.io.*;
import java.util.*;

public class Main {

// we perform count sort for those elements woh doesnot vary too much like jeemains marks 
  public static void countSort(int[] arr, int min, int max) {
   
   int n = max - min + 1 ; //size of frequency matrix ie 
    // frst find smallest and largest value from array
   int[] freq = new int[n]; // frequency of array element
   
   for( int i =0 ; i< arr.length ; i++){
       int val= arr[i] - min ;
       freq[val]++;
   }
   
   // WHY ???? because we have to make our count sort stable
    // ie to preserve their order
    //Prefix sum array for frequency matrix
   for(int i =1 ; i< n ; i++){
       freq[i] += freq[i-1];
   }
   
    int[] ans = new int[arr.length]; // Ans array
  
    // aakhri value kha tk aaega ie aakhri 3 konse index pe aaega
   for(int i =arr.length-1 ; i>=0 ; i--){
       int val = arr[i]-min ;
       ans[ freq[val ]-1 ] = arr[i];
       freq[val]--;
   }
   for(int i =0 ; i<ans.length ; i++){
      
      arr[i] =  ans[ i ] ;
   }
  }

  public static void print(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }
  }

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      arr[i] = scn.nextInt();
      max = Math.max(max, arr[i]);
      min = Math.min(min, arr[i]);
    }
    countSort(arr,min,max);
    print(arr);
  }

}
