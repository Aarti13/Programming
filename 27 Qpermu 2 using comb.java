import java.io.*;
import java.util.*;

public class Main {

    public static void queensPermutations(int qpsf, int tq, int row, int col, String asf, boolean[] queens) {
        //level : Box
        //choices mein queen konse placed hogi 1,2...
        // use combination method here ie nPr = nCr * r! means print comb den print their arrangements
        
        if( row == tq ){
            if(qpsf==tq){
                System.out.println(asf);
                System.out.println();
            }
            return;
        }
        
        int nr = 0 ;//new row
        int nc = 0 ; // new col
        String sep ="" ;// yes queen placed asf
        if( col == tq-1 ){
            nr=row+1;
            nc=0;
            sep = "
";
        }
        else{
            nr=row;
            nc=col+1;
            sep = "	";
        }
        for( int i=0 ; i< tq ; i++){
            if(!queens[i] ){
                queens[i] = true;
                queensPermutations(qpsf+1,tq,nr,nc,asf+"q"+(i+1)+sep , queens);
                queens[i]  = false;
            }
        }
        queensPermutations(qpsf,tq,nr,nc,asf+"-"+sep , queens);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[] queens = new boolean[n];

        queensPermutations(0, n, 0, 0, "", queens);
    }
}