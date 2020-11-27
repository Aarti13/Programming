import java.io.*;
import java.util.*;

public class Main {

  public static void radixSort(int[] arr) {
    
    int m =0;
    for( int i =0;i<arr.length ; i++){
        m = Math.max(m , arr[i] );
    }  
    
    int exp = 1;
    while( exp <= m){
        countSort(arr , exp);
        exp = exp*10;
    }
  }

  public static void countSort(int[] arr, int exp) {
    
    int[] freq = new int[10];
    
    for( int i =0 ;i< arr.length ; i++){
        freq[arr[i] / exp % 10 ]++;
    }
    for( int i =1 ;i<10 ; i++){
        freq[i] += freq[i-1];
    }
      
    int[] ans = new int[arr.length];
    for( int i =arr.length-1 ; i>=0 ; i-- ){
        
        int pos = freq[arr[i] / exp % 10 ]-1;
        ans[pos] = arr[i];
        freq[arr[i] / exp % 10 ]--;
    }
    for( int i =0 ;i<arr.length ; i++){
        arr[i] = ans[i];
    }
    System.out.print("After sorting on " + exp + " place -> ");
    print(arr);
  }

  public static void print(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = scn.nextInt();
    }
    radixSort(arr);
    print(arr);
  }

}