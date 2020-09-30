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
    
//**************************
    // Print the number produced on setting its i-th bit.
    int a1 = n | ( 1<<i);
    System.out.println(a1);

//**************************
    // Print the number produced on unsetting its j-th bit.
    a1 = n & ~( 1<<j);
    System.out.println(a1);

//**************************
    // Print the number produced on toggling its k-th bit.
    a1 = n ^ ( 1<<k );
    System.out.println(a1);
    // Also, Check if its m-th bit is on or off. Print 'true' if it is on, otherwise print 'false'.

//**************************    
    if((n & ( 1<<m ) )== 0 ) System.out.println("false");
    else System.out.println("true");

//**************************
    // Right Most Set Bit Mask  using O(1)
    // 76 0000...1001100 ans 0000.....0000100 
    // find the right most set bit using 2's compliment
    // method find 2's compliment of n ie (~n + 1) 1's comp + 1
    
    // n & (~n + 1) 2's comp
    int Twocomp = (~n )+ 1; // -n can also be written as 
    int ans = n & Twocomp ;
    System.out.println( Integer.toBinaryString(ans) );

//**************************
     //count the number of set bits in the given number.
    // normal method but not enough smart 
    int c=0;
    while( n!=0){
        if( n%2 == 1)c++;
        n /= 2;
    }
  
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

//*******************************
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
        
        Approach : n = 2^x (x = heighest power of 2 < n ) + l
                   ans = 2 * l + 1  
                   
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

//***********************************
    /*
         The gray code is a binary numeral system where two successive numbers 
         differ in only one bit.
         
         1          2           3
    ->   0          0 0         0 0 0
         1          0 1         0 0 1
                    1 1         0 1 1
                    1 0         0 1 0
                                1 1 0
                                1 1 1
                                1 0 1
                                1 0 0
        using recursion:
        1  =    0     1
        2  =  0 0    0 1  // simply add 0 in 1's arraylist
              1 1    1 0  // add 1 by reversing 1's arraylist
        3  =  000 001 011 010 
              110 111 101 100
                                
    */
     public static ArrayList<String> grayCode(int n) {
 	    
 	    if( n == 1){
 	        ArrayList<String> base = new ArrayList<>();
 	        base.add("0");
 	        base.add("1");
 	        return base;
 	    }
 	    
 	     ArrayList<String> res = grayCode(n-1);
 	     ArrayList<String> mres = new ArrayList<>();
 	     // add 0 
 	     for(int i =0 ; i< res.size()  ; i++){
 	         String a = res.get(i);
 	         mres.add("0"+a);
 	     }
 	    // add 1
 	     for(int i = res.size() -1 ; i>=0 ; i-- ){
 	         String a = res.get(i);
 	         mres.add("1"+a);
 	     }
 	    return mres;
     }

//******************************
// All Repeating except one
    // traversing only once in the array and without using any extra space.
    int ans = 0;
    for(int i = 0 ; i < n; i++){
       ans = ans ^ arr[i] ;
    }

//******************************
// All Repeating Except Two
    // You have to find 2 non-repeating numbers in an array.
    
    /*
    Approach :
     frst find xor of all the array
     we get x XOR y for those two numbers
     
     den find right most set bit den divide the array in two partition
     as both the no doesnot present in same group 
     so XOR for both no 
     */
  public static void solution(int[] arr){
     
   int ans = 0 , n = arr.length ;
    for(int i = 0 ; i < n; i++){
       ans = ans ^ arr[i] ;
    }
    
    int rmsb = ans & -ans; // right most set bit mask 
    int x = 0 , y = 0;
    // check for each rmsbm position and seprate them in two different sets and find their XOR
    for(int i = 0 ; i < n; i++){
        
        if( (rmsb & arr[i] ) == 0 ) x = x ^ arr[i];
        else y = y ^ arr[i];
    }
    
    if( x < y )
    { System.out.println( x );
      System.out.println( y );
    }
    else
    { System.out.println( y );
      System.out.println( x );
    }
  }

//*****************************
//  Find (Repeating , Missing) One number is present twice in array and one is missing in from (1-n)
  public static void solution(int[] arr){
   
   int n = arr.length  ,  j=1 ;
   int x = 0 , y =0 , ans =0 ; // repaeting no , missing no
   
   for( int i =0;i<n ;i++){
       // By creating scenario every intger XOR twice
       ans = ans ^ arr[i] ^ j ; // getting duplicate XOR Missing 
       j++;
   }
   
   // den same as two non repeating no in an array of repeated no
       int rmsb = ans & -ans; // right most set bit mask 

    // check for each rmsbm position and seprate them in two different sets and find their XOR
    for(int i = 0 ; i < n; i++){
        
        if( (rmsb & arr[i] ) == 0 ) x ^=  arr[i];
        else y ^= arr[i];
    }
    for(int i = 1 ; i <= n; i++){
        
        if( ( i & rmsb ) == 0 ) x ^=  i;
        else y ^= i;
    }
    
    for( int val: arr){
         
         if( val == x ) // x repeat
         {
            System.out.println( "Missing Number -> "+ y );
            System.out.println( "Repeating Number -> "+ x );
            break;
         }
         else if ( val == y ) // y repeat
         {
            System.out.println( "Missing Number -> "+ x );
            System.out.println( "Repeating Number -> "+ y ); 
            break;
         }
    }

  }

//*****************************************
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

//*******************************************
//Triplets - 1
    /*
    //Question:
    1. You are given an array(arr) of N numbers.
    2. You have to select three indices i,j,k following this condition -> 
      0 <= i < j <= k < arr.length
    3. Two numbers X and Y as defined as :
      X = arr[i] ^ arr[i+1] ^ ... ^ arr[j - 1]
      Y = arr[j] ^ arr[j+1] ^ ... ^ arr[k]
    4. You have to print the number of triplets where X is equal to Y.
    
    solution:
    // BF aaproach O(n^3 ) 3 loops of i , j , k and find if x== y c++
    
    //2nd one:
    1 3 2 5 7 2 7 5 6 4
    find pos of i to j where XOR of array ele is 0 den ans = k-i
    eg i = 2 , k = 7 ans = 5 set in which x = y
    */
	public static void solution(int[] arr){
		//O(n^2)
		int c=0, n =arr.length;
		for( int i =0 ; i<n ;i++){
		    int val = arr[i];
		    for( int k =i+1 ; k<n ;k++){
		        val = val ^ arr[k];
		        if( val == 0 ) c += k-i;
		    }
		}
		System.out.println( c);
    }
//***************************************
/*
Ques 1 Reduce n to 1
1. You are given a positive number N.
2. You have to find the minimum number of operations required to convert N into 1.
3. Operations allowed :
     (i)  If n is even, you have to replace n with n/2.
     (ii) If n is odd, you can replace n with either n-1 or n+1.
     
     Approach 
*/
    public static int solution(long n) {
        int c=0;
        
        while( n!= 1){
            if( n% 2 ==0 ) n=n/2;        // even n= n/2
            else if( n == 3 ) { n = n-1;  break ;}//exceptional case
            //odd cases represent odd in 4x+1 ( n-1 efficient approach) 
            //                        or 4x+3( n+1 efficient approach) 
            else if( n%4 == 1 ) n = n-1;
            else if( n%4 == 3 ) n = n+1;
            
            c++;
        }
        return c;
    }
//***************************
/*
        nCr = n! / n-r ! * r! = n*n-1*n-2*n-3*n-4*n-5-----n-r ( n-r)!
                                -------------------------------------
                                1 2 3 4 ..................... ( n-r)!
    */
    public static long ncr(long n, long r){
        if( r > n ) return 0;
        
        long res = 1L;
        for( long i =0L ;i<r ;i++){
            res *=(n-i)  ;
            res = res/(i+1) ;
        }
        return res;
    }
    
    // find out the count of positive integers strictly less than N, 
    // having same number of set bits as that of N.
    
    // n = number   k = no of set bit in n   i = total length ie 63 
    /*
    using recursion
    Approach : calculate the no of bits in rest if 1 comes
    eg 1 0 0 1 0 1 1  no of set bits 4
        = 0( 6C4 ) + 1( 0 0 1 0 1 1)
        =            1 0 1 1
        =            0( 3C3 ) + 1( 0 1 1)
        =                       11 
        // base case as there is no which is less than has 2 set bits
        
        long n = scn.nextLong();
        int k = csb(n);
        System.out.println(solution(n, k, 63));
    */
    public static long solution(long n, int k, int i) {
        
        if( i == 0 ) return 0;
        long res = 0L;
        long mask = 1L<<i ;
        if( (n&mask) == 0 ) res = solution( n , k , i-1 );
        else{
           long res0 = solution( n , k-1 , i-1 );
           long res1 = ncr( i , k);
           res = res0 + res1 ;
        }
        return res;
    }
    
    // count the no of set bits
    public static int csb(long n){
        int res = 0;
        
        while(n > 0){
            long rsb = n & -n; // right most set bit
            n -= rsb;
            res++;
        }
        
        return res;
    }
//*************************************
    //Xor Of Sum Of All Pairs
    public static int solution(int[] arr){
       
       int sum = 0 ;
       for(  int val : arr ){
           sum = sum ^ (2 * val);
       }
       return sum;
    }
//**********************************
// You have to generate all abbrevations of that word
    // I/P pep  O/P pep  pe1  p1p  p2  1ep  1e1  2p  3
    // approach :
    // 000 pep , 001 pe1 , 010 p1p , 011 p2, 100  1ep , 101 1e1 ,110 2p , 111 3
    // 0-> char append ( if count !=0 add count ) 
    // 1 => count++ 
   
    public static void solve(String str){
       
       for( int i=0 ; i< ( 1<<str.length() ) ;i++){
                  int c = 0;
                  StringBuilder sb= new StringBuilder();
                  
           for( int j=0 ; j< str.length() ;j++){
               
               char ch = str.charAt(j);
               int bit = str.length()-1-j ;
                if( ( i & ( 1<<bit)) == 0 ){
                    if( c== 0) sb.append(ch);
                    else{
                        sb.append(c);
                        sb.append(ch);
                        c=0;
                    }
                }
                else c++;
           }
           
           if( c > 0 )  sb.append(c);
           System.out.println(sb);
       }
    }
//*********************************




  }

}