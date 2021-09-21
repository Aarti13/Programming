import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    
    // swap all odd and even bits 
    // n : 10011101 => 01101110
    //  restore even places using n & (01010101) by unset odd places
    // make this using 0x55555555 ans: 10011101 & 01010101 = 00010101
    
    //  restore odd places using n &  (10101010) by set odd places 
    // make this using 0xAAAAAAAA ans : 10011101  & 10101010 = 10001000
    
    //  even << 1 : 00101010
    //  odd  >> 1 : 01000100
    // even | odd : 01101110
    
    int evenMask = 0x55555555;
    int oddMask =  0xAAAAAAAA;
    
    int even = evenMask & n ;//restore even bits
    int odd =  oddMask & n ;//restore odd bits
    
    int res = ( even << 1 ) | ( odd >> 1 );
    System.out.println(res);
  }

}