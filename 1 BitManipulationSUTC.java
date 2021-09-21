import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int i = scn.nextInt();
    int j = scn.nextInt();
    int k = scn.nextInt();
    int m = scn.nextInt();
    
    //for setting the ith bit
    // using OR
    int mask = 1<<i;
    int ans = n | mask ;
    System.out.println(ans);
    
    //for unsetting the jth bit
    //using AND
    mask = 1<<j ;
    mask = ~mask;
    ans = n & mask;
    System.out.println(ans);

    //for toggling the kth bit
    // using XOR
    mask = 1<<k;
    ans = n ^ mask;
    System.out.println(ans);

    //for checking the mth bit is on or off
    mask = 1<<m;
    ans = n & mask;
    if( ans == 0 )    System.out.println("false");
    else     System.out.println("true");

  }

}