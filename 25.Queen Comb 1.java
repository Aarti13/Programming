import java.io.*;
import java.util.*;

public class Main {

    public static void queensCombinations(int qpsf, int tq, int row, int col, String asf){
        //level pe boxes aaenge 00,01,10,11
        //choices mein queen aaege ki nhi
        
         //Base case 
        if(row==tq ){
            if(qpsf==tq){
                System.out.println(asf);
            }
            return;
        }
       
        int nr=0;
        int nc=0;
        String nqp = "";//queen is not placed to box
        String yqp = ""; //queen is placed to box
        if( col == tq-1 ){
            nc=0;
            nr=row+1;
            yqp=asf+"q
";
            nqp=asf+"-
";
        }
        else{
            nc=col+1;
            nr=row;
            yqp=asf+"q";
            nqp=asf+"-";
        }
        queensCombinations(qpsf+1,tq,nr,nc,yqp);
        queensCombinations(qpsf,tq,nr,nc, nqp);
       
    
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        queensCombinations(0, n, 0, 0, "");
    }
}