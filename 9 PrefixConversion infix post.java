import java.io.*;
import java.util.*;

public class Main{
  

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exp = br.readLine();

    Stack<Integer> ans = new Stack<>();
    Stack<String> in = new Stack<>();
    Stack<String> post = new Stack<>();
    
    for(int i=exp.length()-1 ; i>=0 ; i--)
    {
        char ch=exp.charAt(i);
    
        if( ch>='0' && ch<='9'){
            in.push(ch+"");
            post.push(ch+"");
            ans.push(ch - '0');
        }
        else if( ch=='+' || ch=='-' || ch=='*' || ch=='/' ){
            
            int v1=ans.pop();
            int v2= ans.pop();
            int val=cal(v1,v2,ch);
            ans.push(val);
            
            String postv1=post.pop();
            String postv2=post.pop();
            String postv=  postv1 + postv2 + ch ;
            post.push(postv);
            
            String inv1=in.pop();
            String inv2=in.pop();
            String inv=  "("+inv1 +ch + inv2 +")" ;
            in.push(inv);
        }
    }
    System.out.println(ans.peek());
    System.out.println(in.peek());
    System.out.println(post.peek());
 }
 
 
 public static int cal(int v1 , int v2 , char op)
 {
    if( op == '+'  ) return v1 +v2;
    else if( op == '-'  ) return v1 -v2;
    else if( op == '*'  ) return v1 *v2;
    else if( op == '/'  ) return v1 /v2;
    else return 0;
 }
}
