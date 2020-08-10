import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // no of fences
        int k = sc.nextInt(); // no of colours
        
        int last_two_same = k ;
        int last_two_diff = k*(k-1) ;
        int total = last_two_same  + last_two_diff ;
        
        for( int i =2 ; i<n ; i++){
           
            int ns = last_two_diff ; // new same 
            int nd = total * (k-1) ; // new diff
            int nt = ns + nd;        // new total
            
            last_two_same = ns ;
            last_two_diff = nd ;
            total = nt; 
            
        }
        
        System.out.println( total);
    }
}