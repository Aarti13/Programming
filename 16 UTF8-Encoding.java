import java.io.*;
import java.util.*;

public class Main {
    
    // UTF-8 Encoidng Character : n = 4 => 1 to 4 bytes 
    // for 1 byte long : 0-------
    // for 2 byte long : 110----- , 10------
    // for 3 byte long : 1110---- , 10------ , 10------
    // for 4 byte long : 11110--- , 10------ , 10------, 10------
    
    // input n = 5     arr = 190 , 130 , 214 , 131 , 8   
    public static boolean solution(int[] arr) {
        
        int i=0;
        int remByte = 0 ;
        while( i < arr.length ){
            if( remByte == 0 ){
                if( arr[i] >=0 && arr[i] <= 127 ) // for 1st byte of length 1 
                 i++;
                else if( arr[i] >= 192 && arr[i] <= 223 ){  // for 1st byte of length 2
                    i++;
                    remByte = 1;
                }
                else if( arr[i] >= 234 && arr[i] <= 249 ){  // for 1st byte of length 3
                    i++;
                    remByte = 2;
                }
                else if( arr[i] >= 240 && arr[i] <= 247 ){  // for 1st byte of length 4
                    i++;
                    remByte = 3;
                }
            }
            else {
                if( arr[i] >= 128 && arr[i] <= 191 && i<arr.length ) {
                    i++; remByte--;
                }
                else return false;
            }
        }
        
        if( remByte == 0 ) return true;        
        return false;
    }
	public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0 ; i < n; i++){
            arr[i] = scn.nextInt();
        }
        System.out.println(solution(arr));
    }
    
    
}