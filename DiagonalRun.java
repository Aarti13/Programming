#include<bits/stdc++.h>

using namespace std;

string check(vector<vector<char>> formation)
 {
  
    int n = formation.size();
    int m = formation[0].size();
	
	//upper triangle
    for( int gap=0 ; gap<m ; gap++){
        for( int i=0 , j=gap ; j<m-1 ; i++ , j++ ){
            char ch = formation[i][j];
            if( ch != formation[i+1][j+1]) return "No";
        }
    }
    
	//lower triangle
    for( int gap=0 ; gap<n ; gap++){
        for( int j=0 , i=gap ; i<n-1 ; i++ , j++ ){
            char ch = formation[i][j]
			if( ch != formation[i+1][j+1]) return "No";
        }
    }
        return "Yes"; 
 }
