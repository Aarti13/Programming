import java.io.*;
import java.util.*;

public class Main{
  

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[] a = new int[n];
    for(int i = 0; i < n; i++){
       a[i] = Integer.parseInt(br.readLine());
    }
    int k = Integer.parseInt(br.readLine());

    //nearest right max ele
    int[] nre = new int[n];
    Stack<Integer> st = new Stack<>();
    st.push(a.length-1);
    nre[a.length-1]=a.length;
    
    for(int i = a.length-2 ; i>=0 ; i--)
    {
        while(st.size()>0 && a[i] >= a[st.peek()] )
            st.pop();
            
        if(st.size()== 0 )
            nre[i]=a.length;
        else
            nre[i]=st.peek();
            
        st.push(i);
    }
    
    for(int i =0;i<nre.length-k+1;i++)
    {
        // while nre[i] is with in the window is its right max ele
        //otherwise switch to that i value;
        int j=i;
        while(nre[j] < i+k )
            j=nre[j];
            
        System.out.println(a[j]);
        
    }
 }
}