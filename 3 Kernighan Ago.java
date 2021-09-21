import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    
    //count the number of set bits in the given number.
    // normal method but not enough smart 
    // int c=0;
    // while( n!=0){
    //     if( n%2 == 1)c++;
    //     n /= 2;
    // }
  
    //Kernighans Algorithm
    // as it jumps to every next 1 bit location directly
    // as , using right most set bit
    // x - rmsb until x == 0 and inc counter each time
    
    int c=0;
    while( n !=0 ){
        int rmsb = n & -n ;
        n = n - rmsb;
        c++;
    }
    System.out.println(c);
  }

}