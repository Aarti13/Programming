import java.io.*;
import java.util.*;

public class Main {

  public static void kWordSelectionRep(int idx,String ustr,HashMap<Character, Integer> unique,int cs,int ts,String asf){
    
    //same as combination aaega nhi aaega ki jgh 
    // frst char a uski freq jitn aaaega den 1 kam den 2 kam ... aur ni aaegaa
    
    //if(  ts < cs ) return;
    if( idx == ustr.length() ){
        if( ts== cs ){
            System.out.println(asf);
        }
        return;
    }
    char ch = ustr.charAt(idx);
    for(int i=unique.get(ch) ; i>=0 ; i-- ){
        if( cs<=ts ){ // to eliminate all the calls jo k slots se bade hai in length 
            String str="";
            for( int j=0;j<i ; j++){
                str += ch;
            }
            kWordSelectionRep(idx+1,ustr,unique,cs+i,ts,asf+str);
        }
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    int k = Integer.parseInt(br.readLine());

    HashMap<Character, Integer> unique = new HashMap<>();
    String ustr = "";
    for (char ch : str.toCharArray()) {
      if (unique.containsKey(ch) == false) {
        unique.put(ch, 1);
        ustr += ch;
      } else {
        unique.put(ch, unique.get(ch) + 1);
      }
    }
    kWordSelectionRep(0,ustr,unique,0,k,"");
  }

}