import java.io.*;
import java.util.*;

public class Main {

// power of 2 or not 
  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    
    int c=0;
    while(n!=0){
        int rmsb = n& (-n);
        n = n - rmsb ;
        c++;
    }
	// count set bits den if 1 the no can be represented in power of 2
    if( c == 1) System.out.println("true");
    else System.out.println("false");
	
	//another method if( n & (n-1) ) == 0 return true
  }

}