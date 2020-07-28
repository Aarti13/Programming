//O(n)


import java.io.*;
import java.util.*;

public class Main{
  

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    for(int i = 0; i < n; i++){
       arr[i] = Integer.parseInt(br.readLine());
    }
    
    int[] l=new int[n];
    int[] r=new int[n];
    
    //for next smaller on right
    Stack<Integer> st = new Stack<>();
    st.push(arr.length-1);
    r[arr.length-1]=arr.length;
    
    for(int i = arr.length-2 ; i>=0; i--)
    {
        while( st.size()> 0 && arr[i] <= arr[st.peek()])
            st.pop();
        
        if(st.size()==0)
            r[i]=arr.length;
        else
            r[i]=st.peek();
            
        st.push(i );
        
    }
    
    //for next smaller on left
    
    st.push(0);
    l[0]=-1;
    
    for(int i = 1 ; i<arr.length; i++)
    {
        while( st.size()> 0 && arr[i] <= arr[st.peek()])
            st.pop();
        
        if(st.size()==0)
            l[i]=-1;
        else
            l[i]=st.peek();
            
        st.push(i );
    }
    //area
    int ar=0;
    for(int i=0;i<arr.length;i++)
    {
        int wt=r[i]-l[i]-1;
      
        ar = Math.max(ar,wt*arr[i]);
    }
    System.out.println(ar);
 }
}
