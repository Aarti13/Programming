import java.io.*;
import java.util.*;

// count set bit no's less than N
// for N = 12(1100) 2set bits no;s are ---------ans 5 
public class Main {
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
    
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
        long n = scn.nextLong();
        int k = csb(n);
        System.out.println(solution(n, k, 63));
    }
	
	
}