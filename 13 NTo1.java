import java.io.*;
import java.util.*;

public class Main {

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
    
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		long n = scn.nextInt();
        System.out.println(solution(n));
    }
	
	
}