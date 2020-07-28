// Time:O(n)
// Space:O(n*n)

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            for (int k = 0; k < n; k++) {
                arr[j][k] = line.charAt(k) - '0';
            }
        }

        findCelebrity(arr);

    }

    public static void findCelebrity(int[][] arr) {
        // if a celebrity is there print it''s index (not position), if there is not then
        // print "none"
        
        Stack<Integer> st = new Stack<>();
        for(int i =0;i<arr.length ; i++)
        {
            st.push(i);
        }
        
        while(st.size() >= 2 )
        {
            int v1 = st.pop();
            int v2 = st.pop();
            
            if( arr[v1][v2] == 1 ) st.push(v2); // if v1 knows v2 den v1 can't be a celeb
            else st.push(v1);
        }
        
        int pot=st.pop();
        
        for(int i =0;i<arr.length ; i++)
        {
            if(i != pot ) {
                if( arr[pot][i] == 1 || arr[i][pot] == 0 )
                    {
                    System.out.println("none");
                    return ;
                    }
            }
        }
       System.out.println(pot); 
       
    }

}