// has Duplicates brackets ie extra brcakets (a+ ((b+(d+e)))) true , (a+b+(i)) false
// frstly add all the char to the stack
// den if comes ) simply pop ele till ( ie there is ele in btw  
// if ( comes at top den it has duplicate brackets 
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
