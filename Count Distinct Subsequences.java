#include<bits/stdc++.h>
using namespace std;

int main()
{
    string s;
    cin>>s;
    int n=s.length();
    vector<long> dp(n+1) ;
    dp[0]=1;
    
    vector<int> loc(27,-1);
    for(int i=1;i<=n;i++)
     {
        dp[i]=dp[i-1]*2;
        char c=s[i-1];
        if(loc[s[i-1]-'a']!=-1)
         {
            dp[i] -= dp[loc[c-'a']-1];
         }
        loc[s[i-1]-'a']=i;
     }
    cout<<dp[n]-1<<endl; 
}