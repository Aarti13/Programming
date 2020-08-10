import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
         
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
         
        int[] val = new int[m];
        
        for( int i=0;i<m ; i++){
                val[i] = sc.nextInt();
        }
        
        int inc= val[0], exe=0 , ninc=0 , nexe=0 ; //include that val // exclude that val
        for( int i =1;i<m ; i++){
            
            ninc = val[i] + exe ;
            nexe = Math.max( inc , exe );
            
            inc = ninc;
            exe = nexe;
        }
        int ans= Math.max( ninc , nexe );
        System.out.println( ans );
    }
}