import java.io.*;
import java.util.*;

public class Main{
  

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exp = br.readLine();

    Stack<String> pre= new Stack<>(); // pre
    Stack<Character> so= new Stack<>(); // operators
    Stack<String> post= new Stack<>(); // post
    
    for(int i=0;i<exp.length(); i++)
    {
        char ch=exp.charAt(i) ;
        
         if(ch == '(' ) so.push(ch);
         
         else if((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') ){
           pre.push( ch+""); //char to int
           post.push( ch+""); //char to int
         } 
         
         else if(ch == ')') 
         {
             while(so.peek() != '(')
             {
                char op=so.pop();
                
                String prev2=pre.pop();
                String prev1=pre.pop();
                String v1= op+prev1 + prev2 ;
                pre.push(v1);
                
                String postv2=post.pop();
                String postv1=post.pop();
                String v2= postv1 + postv2 + op ;
                post.push(v2);
                
             }
             so.pop();
         }
      
         else if(ch == '+' ||ch == '-' || ch == '*' || ch == '/') 
         {
             //first it pocess all the op who are less tahn or equal to iteslf
            while(so.size()>0 && so.peek() != '('  && precd(ch) <=precd(so.peek()) )
             {
                char op=so.pop();
                
                String prev2=pre.pop();
                String prev1=pre.pop();
                String v1= op+prev1 + prev2 ;
                pre.push(v1);
                
                String postv2=post.pop();
                String postv1=post.pop();
                String v2= postv1 + postv2 + op ;
                post.push(v2);
             }
             
             // then it push itself 
             so.push(ch);
         }
    }
    
    // at teh end if stack still no remain with 1 op
      while(so.size()>0 )
     {
        char op=so.pop();
        
        String prev2=pre.pop();
        String prev1=pre.pop();
        String v1= op+prev1 + prev2 ;
        pre.push(v1);
                
        String postv2=post.pop();
        String postv1=post.pop();
        String v2= postv1 + postv2 + op ;
        post.push(v2);
    }
    
    System.out.println(post.peek());
    System.out.println(pre.peek());
 
 }
 
    //to find teh precedence of the operator
    public static int precd(char op)
    {
        if(op == '+') return 1;
        else if(op == '-') return 1;
        else if(op == '*') return 2;
        else  return 2;
    }

}