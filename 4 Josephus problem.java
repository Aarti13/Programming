import java.io.*;
import java.util.*;

public class Main {
    /*
    You are given an integer N which represents the total number of soldiers standing in a circle 
     having position marked from 1 to N.
    A cruel king wants to kill them in an manner that is each alternate soldier is killed
    You have to find the position of that lucky soldier.
    
    eg  1  2  3  4  5  6  7  8  9 
        -> 2, 4, 6, 8 are killed 1st time
        -> 1 ,5, 9 2nd time
        -> 7 at last 
        /// ans 3 left only LUCKYY 
        
        Approach : n = 2^x (x = heighest power of 2 < n ) + L  // tranform n to 2^d + L 
                   ans = 2 * L + 1  
                   
        WHY?????
        at first time str from 1....n 
        nth bit ..... 3 2 1 0 bit position
        all the no are killed who are even (their rmb is 0 ie 0th bit ) odd survived
        
        den , next time if lastth no bit (n) killed bit is zero den next time 1th bit which represents
        1 are killed and 0 are same 
        dis process is repeated
        
        Basically if last ele ith pos is 0 then at i+1th pos all the bit rep 1 killed
                  if last ele ith pos is 1 then at i+1th pos all the bit rep 0 killed
                  
        eg n = 6  ans = 5
            
            2 1 0 bit position  frst time , second time , third time ( Killed )
        1   0 0 1                                           k
        2   0 1 0                   k        
        3   0 1 1                               k   
        4   1 0 0                   k 
        5   1 0 1                                                       //ans
        6   1 1 0                   k
                                all 0 bit (k)  all 1 bit(k)  all 0 bit(k)
                                
                survived        - - 1           - 0 -       1 - -       1 0 1                 
            6 bits pos tell at next it which bit is survived 
            after killing 0 bit at 0 bit pos  frst it                      - - 1 
            den strt from 0 in 6 -> 1 1 0 
            
            second time 0th bit pos  ie 0 bit tell 0 will survive          - 0 -
            3rd time    1th bit pos ie  1 bit tell 0 will survive          1 - -
            
            // ans 1 0 1
    */
  public static int highestPowerOf2(int n){
      int i = 1;
      while( i* 2<= n) i = i*2;
      
      return i;
  }     
  public static int solution(int n){
    
    int x =highestPowerOf2( n );
    int l = n-x;
    return 2*l + 1;
  }
  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    System.out.println(solution(n));
  }
  

}