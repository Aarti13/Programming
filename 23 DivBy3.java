import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    String str = scn.nextLine();
    
    // checak divisibility by 3
    //  n(binary no) / 11
    //  abcde binary string 
    //  a*10^4 + b*10^3 + c*10^2 + d*10^1 + e*10^0  / 11 => multiple of 11 then it can be divisible by 3
    //  9999a + a + 1001b -b + 99c + c  + 11d - d + e
    //  9999a + 1001b + 99c + 11d - (b-a-c+d-e)
    //  909a + 91b + 9c + d - ( b+d -(a+c+e) )
    
    
    // sum of all digits is also divisble by 3
    int evenC=0;
    int oddC =0;
    for( int i =0 ; i<str.length() ; i++ ){
        int bit = str.charAt(i) - '0';
		if( i % 2 == 0 ) evenC += bit;
		else oddC += bit;
    }
	int res = (evenC - oddC) % 3;
	if( res == 0 ) System.out.println("true");
	else System.out.println("false");
  }

}