
bool isBalancedBracket(string & str)   //O(n)
{ 
    stack<char> st;
    for(int i=0 ; i<str.length(); i++)
    {
        char ch=str[i];
        if(ch == '(' || ch == '{' || ch == '[' )
        {
            st.push(ch);
        }
        else if(ch == '}' ) 
        {
            if(st.empty() || st.top()!= '{')
                return false;
            else
                st.pop();
        }
        else if( ch == ')' )
        {
            if(st.empty() || st.top()!= '(')
                return false;
            else
                st.pop();
        }
        else if( ch == ']' )
        {
            if(st.empty() || st.top()!= '[')
                return false;
            else
                st.pop();
        }
    }
        return true;
}