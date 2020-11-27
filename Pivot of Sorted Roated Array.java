import java.io.*;
import java.util.*;

public class Main {

  public static int findPivot(int[] arr) {
     //O(n) if value dec den print it
   //O(logn) using binary search 
   int st=0 , end=arr.length-1 ;
   while( st<end){
       int mid = ( st + end )/2;
       //mid --- high if inc fall in prev 
       // if dec fall in next
       if( arr[mid] < arr[end] ) end =  mid;
       else st = mid +1;
   }
   return arr[end];
  }

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = scn.nextInt();
    }
    int pivot = findPivot(arr);
    System.out.println(pivot);
  }

}