import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
         
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
         
        int[][] arr = new int[m][3];
        
        for( int i=0;i<m ; i++){
            for( int j=0; j<3 ; j++ )
                arr[i][j] = sc.nextInt();
        }
        
        int rp = arr[0][0] , bp = arr[0][1] , gp = arr[0][2]; // red , blue green paint
        
        for( int i =1;i<m ; i++){
            
            int nrp = arr[i][0] + Math.min(bp , gp );
            int nbp = arr[i][1] + Math.min(rp , gp );
            int ngp = arr[i][2] + Math.min(bp , rp );
            
            rp = nrp ; bp = nbp ; gp = ngp ;
        }
        
        int ans= Math.min(Math.min( rp , bp ) , Math.min( gp , bp ) ) ;
        System.out.println( ans );
    }
}