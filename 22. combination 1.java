import java.io.*;
import java.util.*;

public class Main {
    
    //combinations : chpoosing boxes for items they are identical so same not allwded 
    // nCr = n!/r!(n-r)!
      public static void combinations(int cb, int tb, int ssf, int ts, String asf){
         
         if(cb>tb){
           if(ssf==ts) System.out.println(asf);
            return;
         } 
        combinations(cb+1,tb,ssf+1,ts,asf+"i");   //aaega
        combinations(cb+1,tb,ssf,ts,asf+"-");     // ni aaega

  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int nboxes = Integer.parseInt(br.readLine());
    int ritems = Integer.parseInt(br.readLine());
    combinations(1, nboxes, 0, ritems, "");
  }

}