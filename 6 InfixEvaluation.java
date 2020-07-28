import java.io.*;
import java.util.*;

public class Main{
  

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exp = br.readLine();
    
    // Main code:
    Stack<Integer> si= new Stack<>(); // operands stack
    Stack<Character> so= new Stack<>(); // operators Stack
    
    for(int i=0;i<exp.length(); i++)
    {
        char ch=exp.charAt(i) ;
        
         if(ch == '(') so.push(ch);
         else if(ch >= '0' && ch <= '9' ) si.push( ch-'0'); //char to int
         
         else if(ch == ')') 
         {
             while(so.peek() != '(')
             {
                char op=so.pop();
                int v2=si.pop();
                int v1=si.pop();
                
                int val = cal(v1 ,v2, op);
                si.push(val);
             }
             so.pop();
         }
      
         else if(ch == '+' ||ch == '-' || ch == '*' || ch == '/') 
         {
             //first it pocess all the op who are less tahn or equal to iteslf
            while(so.size()>0 && so.peek() != '('  && precd(ch) <=precd(so.peek()) )
             {
                char op=so.pop();
                int v2=si.pop();
                int v1=si.pop();
                
                int val = cal(v1 ,v2, op);
                si.push(val);
             }
             
             // then it push itself 
             so.push(ch);
         }
    }
    
    // at teh end if stack still no remain with 1 op
      while(so.size()>0 )
     {
        char op=so.pop();
        int v2=si.pop();
        int v1=si.pop();
        int val = cal(v1 ,v2, op);
        si.push(val);     
    }
    
    System.out.println(si.peek());
 }
 
    //to find teh precedence of the operator
    public static int precd(char op)
    {
        if(op == '+') return 1;
        else if(op == '-') return 1;
        else if(op == '*') return 2;
        else  return 2;
    }
    
    // to calculate the value of the operands with the op
    public static int cal(int v1, int v2 , char op)
    {
        if(op == '+') return v1+v2;
        else if(op == '-') return v1-v2;
        else if(op == '*') return v1*v2;
        else  return v1/v2;
    }
}