import java.io.*;
import java.util.*;

public class Main{

public static void main(String[] args) throws Exception {
    
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    
    long pz = 1, po = 1 , nz=0, no =0; 
    
    for( int i =2 ;i<= n ;i++){
        
        nz = po ; 
        no = pz+po;
         //System.out.println( nz+"  "+no );
        pz = nz;
        po = no;
    }
    long ans = pz+po;
    System.out.println( ans*ans );
 }

}