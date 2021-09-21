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
  
//All Repeating Three Times Except One
/*  
    1. HashMap : O(n logn ) O( n )
    2. sort and linear search : )( nlogn + n ) and search with i+3 if A[i] == A[i-1]
        better as logn <= 32 (INT_MAX)
    3. Counting set bits : O( 32 * n)
        check for all the set bits and if its multiple of 3 do nothing 
        otherwise left shift it ie ans*2 each time
    4. Bit manipulation : O(n) as XOR and AND = O(1)
*/
  public static void solution(int[] arr){
    
    int ones=0,twos=0;
    for(int x: arr){
        ones=(x^ones)&(~twos);  //tracks elements that appear one time
        twos=(x^twos)&(~ones);  // tracks element that appear two times
    }
    System.out.println(ones);
  }
  
  ///////////////////another sol 
  public static void solution(int[] arr){
        
        int tn= Integer.MAX_VALUE ;
        int tnp1 = 0 , tnp2 = 0;
        
        for( int i =0 ; i<arr.length ; i++){
            int cwtn = arr[i] & tn ;// common with three 3n 
            int cwtnp1 = arr[i] & tnp1 ;// common with 3n+1 
            int cwtnp2 = arr[i] & tnp2 ;// common with 3n+2 
            
            // change them 
            
            tn = tn & (~cwtn);// off bits that is set on cwtn
            tnp1 = tnp1 | cwtn ; // set the bits which are set in cwtn
            
            tnp1 = tnp1 & (~cwtnp1);// off bits that is set on cwtnp1
            tnp2 = tnp2 | cwtnp1 ; // set the bits which are set in cwtnp1
            
            tnp2 = tnp2 & (~cwtnp2);// off bits that is set on cwtnp2
            tn = tn | cwtnp2 ; // set the bits which are set in cwtnp2
            
        }
        System.out.println(tnp1);
  }


}