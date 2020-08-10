#include<iostream>
#include<vector>
using namespace std;

int climstairs( int n , vector<int> & qb){
    
    qb[0] = 1 ;
    
    for( int i=1 ; i< qb.size() ; i++ ){
        
        if( i == 1 ) qb[i] = qb[i-1];
        else if( i == 2 ) qb[i] = qb[i-1]+qb[i-2];
        else qb[i] = qb[i-1]+qb[i-2]+qb[i-3]; 
       
    }
    return qb[n];
}

int main(){
    int n;
    cin>>n ;
    vector<int > qb(n+1 , 0);
    int a = climstairs(n , qb);
    cout<<a;
    return 0;
}