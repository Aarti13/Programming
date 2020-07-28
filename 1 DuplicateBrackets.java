// has Duplicates brackets ie extra brcakets (a+ ((b+(d+e)))) true , (a+b+(i)) false
bool hasDuplicateBrackets(string & str)     //O(n)
{
    stack<char> st;
    for(int i=0 ; i<str.length(); i++)
    {
        char ch=str[i];
        if(ch == ')' )
        {
            if(st.top() == '(')
                return true;
            while(st.top()  != '(')
                st.pop();
            st.pop();
        }
        else
        {
            st.push(ch);
        }
    }
    return false;
}
