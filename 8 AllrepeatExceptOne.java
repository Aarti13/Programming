import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    for(int i = 0 ; i < n; i++){
      arr[i] = scn.nextInt();
    }
    
    // All Repeating except one
    // traversing only once in the array and without using any extra space.
    int ans = 0;
    for(int i = 0 ; i < n; i++){
       ans = ans ^ arr[i] ;
    }
    System.out.println( ans );
  }

}