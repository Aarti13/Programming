import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int a = scn.nextInt();
    int b = scn.nextInt();
    
    // no fo bits should be flipped to make b from a 
    int c=0;
    int ans= a^b ; //taek xor of a and b if both same rep 0 otherwise one 
    // then count the 1's in the ans
    
    while(ans != 0){
        int rmsb = ans & (-ans);
        ans = ans - rmsb;
        c++;
    }
    System.out.println(c);
  }

}