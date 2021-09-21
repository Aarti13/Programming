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

//  Find (Repeating , Missing) One number is present twice in array and one is missing in from (1-n)
  public static void solution(int[] arr){
   
   int n = arr.length  ,  j=1 ;
   int x = 0 , y =0 , ans =0 ; // repaeting no , missing no
   
   for( int i =0;i<n ;i++){
       // By creating scenario every intger XOR twice
       ans = ans ^ arr[i] ^ j ; // getting duplicate XOR Missing 
       j++;
   }
   
   // den same as two non repeating no in an array of repeated no
       int rmsb = ans & -ans; // right most set bit mask 

    // check for each rmsbm position and seprate them in two different sets and find their XOR
    for(int i = 0 ; i < n; i++){
        
        if( (rmsb & arr[i] ) == 0 ) x ^=  arr[i];
        else y ^= arr[i];
    }
    for(int i = 1 ; i <= n; i++){
        
        if( ( i & rmsb ) == 0 ) x ^=  i;
        else y ^= i;
    }
    
    for( int val: arr){
         
         if( val == x ) // x repeat
         {
            System.out.println( "Missing Number -> "+ y );
            System.out.println( "Repeating Number -> "+ x );
            break;
         }
         else if ( val == y ) // y repeat
         {
            System.out.println( "Missing Number -> "+ x );
            System.out.println( "Repeating Number -> "+ y ); 
            break;
         }
    }

  }

}