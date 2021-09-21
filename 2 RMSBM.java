import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    
    // Right Most Set Bit Mask 
    // 76 0000...1001100 ans 0000.....0000100 
    // find the right most set bit using 2's compliment
    // method find 2's compliment of n ie (~n + 1) 1's comp + 1
    
    // n & (~n + 1) 2's comp
    int Twocomp = (~n )+ 1; // -n can also be written as 
    int ans = n & Twocomp ;
    System.out.println( Integer.toBinaryString(ans) );
  }

}