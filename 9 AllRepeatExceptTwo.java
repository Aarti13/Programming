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
    solution(arr);
  }

    // All Repeating Except Two
    // You have to find 2 non-repeating numbers in an array.
    
    /*
    Approach :
     frst find xor of all the array
     we get x XOR y for those two numbers
     */
  public static void solution(int[] arr){
     
   int ans = 0 , n = arr.length ;
    for(int i = 0 ; i < n; i++){
       ans = ans ^ arr[i] ;
    }
    
    int rmsb = ans & -ans; // right most set bit mask 
    int x = 0 , y = 0;
    // check for each rmsbm position and seprate them in two different sets and find their XOR
    for(int i = 0 ; i < n; i++){
        
        if( (rmsb & arr[i] ) == 0 ) x = x ^ arr[i];
        else y = y ^ arr[i];
    }
    
    if( x < y )
    { System.out.println( x );
      System.out.println( y );
    }
    else
    { System.out.println( y );
      System.out.println( x );
    }
  }

}