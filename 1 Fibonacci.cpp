#include<iostream>
#include<vector>
using namespace std;

// fib using recursion
int fib( int n)
{
    if( n == 0 ) return 0;
    if( n == 1 ) return 1;
    int ans = 0;
    ans = fib(n-1) + fib(n-2);
    return ans;
}

//fib using memoization / DP
int fibRec( int n , vector<int> & qb )
{
    if( n == 0 || n == 1) return n;
    if( qb[n] != 0 ) return qb[n];
    
    int ans = fibRec(n-1 , qb) + fibRec(n-2 , qb) ;
    qb[n] = ans;
    return ans;
}

int main(){
    
    int n;
    cin>>n;
    //int a = fib(n);
    vector<int > qb (n+1 , 0);
    int a= fibRec(n , qb);
    cout<<a;
    return 0;
}