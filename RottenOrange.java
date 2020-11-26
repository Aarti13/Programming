// ******************** Rotten Oranges ***********************

import java.util.LinkedList; 
import java.util.Scanner; 

class Rotten_Oranges
{
    // class for rotten oranges which contain row and col and the time
    public static class ROPair
    {
        int r=0;
        int c=0;
        int time=0;

        ROPair() {}
        ROPair(int r,int c ,int time)
        {
            this.r=r;
            this.c=c;
            this.time=time;
        }
    }

    public static void Rotten_Oranges(long [][] oranges)
    {
        LinkedList <ROPair> que= new LinkedList<>(); // linked list which act as a queue for storing all the r and c which oranges are rotten

        for( int r=0; r<oranges.length ; r++)
        {
            for(int c =0; c< oranges[r].length ; c++)
            {
                if(oranges[r][c] == 2)
                {
                    ROPair root= new ROPair(r,c ,0) ;
                    que.addLast(root);
                }
            }
        }

        int time=0;
        int [][] dir={{0,1} ,{1,0}, {0,-1}, {-1,0} };
        while( !que.isEmpty())
        {
        ROPair rpair= que.removeFirst();

        for(int d=0 ; d<dir.length ; d++)
        {
            int r= dir[d][0] + rpair.r ;
            int c= dir[d][1] + rpair.c ;

            if(r >= 0 && c >= 0 && r< oranges.length && c< oranges[0].length && oranges[r][c]== 1)
            {
                oranges[r][c]=2;
                ROPair npair= new ROPair(r, c, rpair.time+1 );
                que.addLast(npair);
            }
        }

        time=rpair.time;
        }

         for( int r=0; r<oranges.length ; r++)
        {
            for(int c =0; c< oranges[r].length ; c++)
            {
                if(oranges[r][c] == 1)
                time=-1;
             }
        }
                System.out.print( time);
     
    }

    public static void solve()
    {
         Scanner sc =new Scanner(System.in );
        int m= sc.nextInt() ;      // no of test cases
        int row= sc.nextInt();       // no of rows
        int col= sc.nextInt();        // no of col

        for(int i=0;i<m;i++)
        {
            long [][] oranges=new long[row][col];
            for( int r=0; r< row; r++)
            {
                 for(int c =0; c< col ; c++)
                 {
                    oranges[r][c]=sc.nextInt();
                }
            }        
        Rotten_Oranges(oranges);
        }
    }

    public static void main(String [] args)
    {
        solve();
    }

}



// ******************** we are on fire ***********************
#include<iostream>
#include<vector>
using namespace std;

int m , n , q;
int ln=0;


void we_are_on_fire(int r, int c,vector<vector<int> >& arr)
{
    arr[r][c]=2;
    ln--;

    if( r-1>=0 && arr[r-1][c]==1)
        we_are_on_fire(r-1,c,arr);
    if( c-1>=0 && arr[r][c-1]==1)
        we_are_on_fire(r,c-1,arr);
    if( r+1<m && arr[r+1][c]==1)
        we_are_on_fire(r+1,c,arr);
    if( c+1<n && arr[r][c+1]==1)
        we_are_on_fire(r,c+1,arr);
}

int main()
{
    cin>>m>>n>>q;
    vector<vector<int>> arr(m , vector<int> (n ,0));
    for(int i =0;i<m;i++)
    {
        for(int j=0;j<n;j++)
        {
            int val;
            cin>>val;
            arr[i][j]=val;
            if(arr[i][j]==1)
                 ln++;
        }
    }
  
    for(int i =0;i<q;i++)
    {
        int a,b;
        cin>>a>>b;
        if( a-1>=0 && b-1>=0 && a-1<m && b-1<n){
        if(arr[a-1][b-1] == 1)
            we_are_on_fire(a-1,b-1, arr);
        cout<<ln<<endl;
        }
    }
    return 0;
}
