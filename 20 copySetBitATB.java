import java.io.*;
import java.util.*;

public class Main {
	//copy set bits in a range
	
  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int a = scn.nextInt();  // A no
    int b = scn.nextInt();  // Another no B
    int left = scn.nextInt(); // range start point 
    int right = scn.nextInt(); // range end point
	
	//copy set bit of a in b from left to right 
    for( int i=left ; i<= right ; i++ ){
       int mask=1<<(i-1);
       if( (a&mask) == 0) continue;
       else b|=mask;
    }
    System.out.println(b);
  }

}