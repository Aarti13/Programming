//          ***************** void type recursion *****************

#include<iostream>
#include<vector>
#include<string>

#define vi vector<int>  //defining a macro of vector of type int so whereever we need to use vector<int> we can write vi only.
#define vvi vector<vi>  // vector<vector<int>>
#define vb vector<bool>  
#define vvb vector<vb>

using namespace std;

// subsequence of abc :   c  b  bc  a  ac  ab  abc
int sub_Sequence( string ques,string ans)
{
    if(ques.length()==0)
    {
        cout<<ans<<"  ";
        return 1;
    }
    int c=0;
    c+= sub_Sequence(ques.substr(1),ans);
    c+=sub_Sequence(ques.substr(1),ans+ques.at(0));
    return c;
}

// ssssHsss from HisssHiHisHssHis ie remove Hi from the string
void removeHi(string ques,string ans)
{
    if(ques.length()==0)
    { cout<<ans;
        return;
    }
    if(ques.length()>1 && ques.at(0)=='H' && ques.at(1)=='i')
        removeHi(ques.substr(2),ans);
    else
    removeHi(ques.substr(1),ans+ques.at(0));
}

// abc from  aaaaaaaabbbbbbbcccccccc ie remove duplicate from the string
void removeDuplicate(string ques,string ans)
{
    if(ques.length() -1 == 0)
    { cout<<ans;
        return;
    }
    if(ques.at(0) == ans.at( ans.length()-1 ) && ques.length()>1 )
        removeDuplicate(ques.substr(1),ans) ;
    else
    removeDuplicate(ques.substr(1) ,ans+ques.at(0) );
}

//  aaaabbccdeff        a4 b2 c2 def2





// print all the combination of the keypad nos ie 3 def 4 ghi 5 jkl
//DGJ  DGK  DGL  DHJ  DHK  DHL  DIJ  DIK  DIL

string word[]={"","ABC" ,"DEF","GHI","JKL","MNO","PQRS","TUV","WXYZ","*","0","#"};
int keyPad( string ques,string ans)
{
    if(ques.length()==0)
    {
        cout<<ans<<"  ";
        return 1;
    }
    int c=0;
    int idx=ques[0]-'0';
    string str=word[idx-1];  // -1 because we re assuming length from 1 ie ABC is at 2 place 
     cout<<endl;
    for(int i=0;i<str.length();i++)
    {
        c+=keyPad(ques.substr(1),ans+str[i]);
    }
    return c;
}
//for 10110 as 
int keyPad_01( string ques,string ans)
{
    if(ques.length()==0)
    {
        cout<<ans<<"  ";
        return 1;
    }
    int c=0;
    int idx=ques[0]-'0';
    string str=word[idx];  
    for(int i=0;i<str.length();i++)
    {
        c+=keyPad_01(ques.substr(1),ans+str[i]);
    }
    if(ques.length() >= 2)   //10110
    {
        int idx1 = idx*10 + ( ques[1]-'0' );
        if(idx1 >= 10 && idx1 <= 11)
        {
            str=word[idx1];
            for(int i=0;i<str.length();i++)
                c+=keyPad_01(ques.substr(2),ans+str[i]);
        }
    }
    return c;
}

// Dictionary ie a=1,b=2,c=3..........
//find all the combination of  1123  aabc  aaw  alc  kbc  kw  5
int Dictionary( string ques,string ans)
{
    if(ques.length()==0)
    {
        cout<<ans<<"  ";
        return 1;
    }
    int c=0;
    int  idx=ques[0]-'0';
    char str=(char)('a'+(idx-1));  

    if( idx==0)      //if we find only 0 ie no element so we just move 1 forward
    {
      c+=Dictionary(ques.substr(1),ans);
      return 1;   
    }

    c+=Dictionary(ques.substr(1),ans+str );

    if(ques.length() >= 2)   // for two element
    {
        int idx1 = idx*10 + ( ques[1]-'0' );
        if( idx1 <= 26 )
        {
            str=(char)('a'+(idx1-1) ); 
            c+=Dictionary(ques.substr(2),ans+str);
        }
    }
    return c;
}
   // flood fill
    public static void floddfill( int sr ,int sc , int [][] maze , String psf)
    {
         if(sr == maze.length - 1 && sc == maze[0].length - 1)
        {
            System.out.println(psf+" ");
            return;
        }
        maze[sr][sc]=2;

        // T L D R
        if(sr>0 &&  maze[sr-1][sc]!=1 &&  maze[sr-1][sc]!=2 ) floddfill( sr-1, sc , maze , psf +"T");
        if(sc>0 &&  maze[sr][sc-1]!=1 &&  maze[sr][sc-1]!=2 ) floddfill( sr, sc-1 , maze , psf +"L");
        if(sr<maze.length-1 &&  maze[sr+1][sc]!=1 &&  maze[sr+1][sc]!=2 )floddfill( sr+1, sc , maze , psf +"D");
        if(sc<maze[0].length-1 &&  maze[sr][sc+1]!=1 &&  maze[sr][sc+1]!=2 ) floddfill( sr, sc+1 , maze , psf +"R");

        maze[sr][sc]=0;
    }
//all permutation of abc ie 3!  abc  acb  bac  bca  cab  cba  6
int permu(string ques,string ans)
{
    if(ques.length() ==0)
    {
        cout<<ans<<"  ";
        return 1;
    }
    int c=0;
    for(int i=0;i<ques.length();i++)
    {
        string st=ques.substr(0,i)+ques.substr(i+1);
        c+=permu(st,ans+ques[i]);
    }
    return c;
}

//all permutation of aba with no repetition ie 3!/2!  
int permu_rep(string ques,string ans)
{
    if(ques.length() ==0)
    {
        cout<<ans<<"  ";
        return 1;
    }
    int c=0;
    vector<bool> vis(26,false);
    for(int i=0;i<ques.length();i++)
    {
        if( ! vis[ ques[i] - 'a' ])
        {   vis[ques[i] - 'a']=true;
            string st=ques.substr(0,i)+ques.substr(i+1);
            c+=permu_rep(st,ans+ques[i]);
        }
    }
    return c;
}

//****************************
void basic()
{
    //cout<<sub_Sequence("abc","");
    //cout<<keyPad("345","");
    //cout<<keyPad_01("10110","");
    //cout<<Dictionary("110023","");
    // cout<<permu("abc","");
    // cout<<permu_rep("aba","");
    //removeHi("HisssHiiSShuHi" ,"");
    //removeDuplicate("aaaabbbbccccdddd"," ");
   
   //not performed: compression("aaaabbbbccccdddd"," ","");
}

// ************** MazePath ****************
//pathtype
int mazePath_HVD(int sr, int er,int sc, int ec  , string ans )
{
    if( sr == er && sc == ec)
    {
        cout<<ans<<endl;
        return 1;
    }

    int c=0;
    if (sr +1 <= er )
        c+=mazePath_HVD(sr+1,er,sc,ec,ans+"H");
    if (sc +1 <= ec )
        c+=mazePath_HVD(sr,er,sc+1,ec,ans+"V");
    if(sr+1<=er && sc+1<=ec) 
        c+=mazePath_HVD(sr+1,er,sc+1,ec,ans+"D");

    return c;
}

// height of maze path
 //maximum height of the tree : 2,2 =4 2,6 =10

int mazePath_Height(int sr, int er,int sc, int ec  , string ans )
{
    int height=0;
    if( sr == er && sc == ec)
        return 0;

    int c=0;
    if (sr +1 <= er )
        height =max( height,mazePath_Height(sr+1,er,sc,ec,ans+"H") );
    if (sc +1 <= ec )
         height =max( height,mazePath_Height(sr,er,sc+1,ec,ans+"V") );
    if(sr+1<=er && sc+1<=ec) 
         height =max( height,mazePath_Height(sr+1,er,sc+1,ec,ans+"D") );

    return height+1;
}

//maze path multi move to reach diagonal from how much jumps are reuqired  d1d1 d2
int mazePath_Multimove_HVD(int sr, int er,int sc, int ec  , string ans )
{
    if( sr == er && sc == ec)
    {
        cout<<ans<<endl;
        return 1;
    }

    int c=0;
    for (int i=1; sr +i <= er ; i++)
        c+=mazePath_Multimove_HVD(sr+i,er,sc,ec,ans+"H"+to_string(i));
    for (int i=1; sc +i <= ec ; i++) 
        c+=mazePath_Multimove_HVD(sr,er,sc+i,ec,ans+"V"+to_string(i));
    for (int i=1; sr+i<=er && sc+i<=ec ; i++)  
        c+=mazePath_Multimove_HVD(sr+i,er,sc+i,ec,ans+"D"+to_string(i));

    return c;
}
  
//**************************************

void path()
{
//    cout<< mazePath_HVD(0,2,0,2,"");
    //cout<< mazePath_Height(0,2,0,2,"");
 //  cout<< mazePath_Multimove_HVD(0,2,0,2,"");
}

//************************  all permutation or combination ***************************
// coin change 2 3 5 7 coins to achieve the target of 10

//permutation with repition allowded 
int permu_01(vector<int> & ques,int idx,int tar,string ans)
{
    if(tar == 0)
    {
        cout<<ans<<endl;
        return 1;
    }
    int c=0;
    for(int i=0;i<ques.size();i++)
    {
        if((tar-ques[i] ) >= 0)
            c+=permu_01(ques ,0,tar-ques[i],ans+" "+to_string(ques[i]));
    }
    return c;
}
//combination  with repition allowded 
int comb_01(vector<int> & ques,int idx,int tar,string ans)
{
    if(tar == 0)
    {
        cout<<ans<<endl;
        return 1;
    }
    int c=0;
    for(int i=idx;i<ques.size();i++)
    {
        if((tar-ques[i] ) >= 0)
            c+=comb_01(ques ,i,tar-ques[i],ans+to_string(ques[i]));
    }
    return c;
}

//permutation without repition allowded 
int permu_02(vector<int> & ques,int idx,int tar,string ans ,vector<bool> & vis)
{
    if(tar == 0)
    {
        cout<<ans<<endl;
        return 1;
    }
    int c=0;
    for(int i=0;i<ques.size();i++)
    {
        if((tar-ques[i] ) >= 0 && !vis[i])
        {   
            vis[i]=true;
            c+=permu_02(ques ,0,tar-ques[i],ans+" "+to_string(ques[i]) ,vis);
            vis[i]=false;
        }
    }
    return c;
}
//combination  without repition allowded 

// int comb_02(vector<int> & ques,int idx,int tar,string ans)
// {
  
// }

//***************************************
//using substring.......
//permutation with repition allowded 
int permusub_01(vector<int> & ques,int idx,int tar,string ans)
{
    if(tar == 0 || ques.size()==idx )
    {
        if(tar==0)
        {
            cout<<ans<<endl;
            return 1;
        }
        return 0;
    }
    int c=0;
        
        if((tar-ques[idx] ) >= 0)
            c+=permusub_01(ques ,0,tar-ques[idx],ans+" "+to_string(ques[idx]));
        c+=permusub_01(ques ,idx+1,tar,ans);
    return c;
}

//combination with repition allowded 
int combsub_01(vector<int> & ques,int idx,int tar,string ans)
{
    if(tar == 0 || ques.size()==idx )
    {
        if(tar==0)
        {
            cout<<ans<<endl;
            return 1;
        }
        return 0;
    }
    int c=0;
        
        if((tar-ques[idx] ) >= 0)
            c+=combsub_01(ques ,idx,tar-ques[idx],ans+" "+to_string(ques[idx]));
        c+=combsub_01(ques ,idx+1,tar,ans);
    return c;
}

//permutation with repition allowded 
int permusub_02(vector<int> & ques,int idx,int tar,string ans,vector<bool> & vis)
{
    if(tar == 0 || ques.size()==idx )
    {
        if(tar==0)
        {
            cout<<ans<<endl;
            return 1;
        }
        return 0;
    }
    int c=0;
        
        if((tar-ques[idx] ) >= 0 && !vis[idx])
        {
            vis[idx]=true;
            c+=permusub_02(ques ,0,tar-ques[idx],ans+" "+to_string(ques[idx]) , vis);
            vis[idx]=false;
        }
        c+=permusub_02(ques ,idx+1,tar,ans ,vis);
    return c;
}

//combination with repition allowded 
int combsub_02(vector<int> & ques,int idx,int tar,string ans)
{
    if(tar == 0 || ques.size()==idx )
    {
        if(tar==0)
        {
            cout<<ans<<endl;
            return 1;
        }
        return 0;
    }
    int c=0;
        
        if((tar-ques[idx] ) >= 0)
            c+=combsub_02(ques ,idx+1,tar-ques[idx],ans+" "+to_string(ques[idx]));
        c+=combsub_02(ques ,idx+1,tar,ans);
    return c;
}

// subset 
int Sub_set(vector<int> & ques,int idx,string ans)
{
    cout<<ans<<endl;
    if(idx == ques.size())
        return 1;
    int c=0;
    for(int i=idx;i<ques.size();i++)
    {
            c+=Sub_set(ques ,i+1,ans+to_string(ques[i]));
    }
    return c;
}

//**************************** 
void percomb()
{
    vector<int> ques={2,3,5,7};
    vector<int> arr={1,2,3};
    vector<bool> vis(ques.size(),false);
   // cout<<permu_01(ques,0,10,"");
    //  cout<<comb_01(ques,0,10,"");
   //  cout<<comb_02(ques,0,10,"");
   // cout<<permu_02(ques,0,10,"",vis);
    // cout<<permusub_01(ques,0,10,"");
    //  cout<<combsub_01(ques,0,10,"");
    // cout<<combsub_02(ques,0,10,"");
   // cout<<permu_02(ques,0,10,"",vis);
   cout<<Sub_set(arr,0,"");
}

//***************************** Queen ***************************
//there are 3 queens q1,q2,q3 we have to place them in a 5 box
// find permutation and combination of Queens can sit on which box 
 int queen_permu_01(vector<bool> & box, int tnq,int qpsf,int lqpl,string ans)  //tnq total no of queens, qpsf queen placed so far ,qpl last queen placed location
 {
     if(tnq == qpsf)
     {
         cout<<ans<<endl;
         return 1;
     }
     int c=0;
     for(int i=lqpl ; i< box.size();i++)
     {  
         if(!box[i])
         {
             box[i]=true;
             c+=queen_permu_01(box,tnq,qpsf+1,0,ans+"B "+to_string(i)+"  Q"+to_string(qpsf)+" ");
             box[i]=false;
         }
     }
     return c;
 }

 int queen_comb_01(int box, int tnq,int qpsf,int lqpl,string ans)  
  {
     if(tnq == qpsf)
     {
         cout<<ans<<endl;
         return 1;
     }
     int c=0;
     for(int i=lqpl ; i< box;i++)
     {
         c+=queen_comb_01(box,tnq,qpsf+1,i+1,ans+"B "+to_string(i)+"  Q"+to_string(qpsf)+" ");
     }
     return c;
 }

// Queen Problem using subsequence:
//there are 3 queens q1,q2,q3 we have to place them in a 5 box nd find all permuatations and combinations:
//using subsequence in which the level represent boxes not queen.

int queen_permu_02(vector<bool> & box, int tnq,int qpsf,int lqpl,string ans)  //tnq total no of queens, qpsf queen placed so far ,qpl last queen placed location
 {
    if(tnq==qpsf || lqpl == box.size() )
    {
     if(tnq == qpsf )
     {
         cout<<ans<<endl;
         return 1;
     }
     return 0;
    }
     int c=0; 
         if(!box[lqpl])
         {
             box[lqpl]=true;
             c+=queen_permu_02(box,tnq,qpsf+1,0,ans+"B "+to_string(lqpl)+"  Q"+to_string(qpsf)+" ");
             box[lqpl]=false;
         }
          c+=queen_permu_02(box,tnq,qpsf,lqpl+1,ans);
     return c;
 }

 int queen_comb_02(int box, int tnq,int qpsf,int lqpl,string ans)  
  {
    if(tnq==qpsf || lqpl == box )
    {
     if(tnq == qpsf )
     {
         cout<<ans<<endl;
         return 1;
     }
     return 0;
    }

     int c=0;
         c+=queen_comb_02(box,tnq,qpsf+1,lqpl+1,ans+"B "+to_string(lqpl)+"  Q"+to_string(qpsf)+" ");
        c+=queen_comb_02(box,tnq,qpsf,lqpl+1,ans);
     return c;
 }

// fill queens in 2d matrix

 int queen_permu_mat(vector<vector<bool>> & box, int tnq,int qpsf,int lqpl,string ans)  
  {
     if(tnq == qpsf)
     {
         cout<<ans<<endl;
         return 1;
     }
     int c=0;
     for(int i=lqpl ; i<box.size() * box[0].size();i++)
     {
         int x=i/box[0].size();
         int y=i% box[0].size();
          if(!box[x][y])
         {
             box[x][y]=true;
            c+=queen_permu_mat(box,tnq,qpsf+1,0,ans+"("+to_string(x)+" , "+to_string(y)+") " +" Q"+to_string(qpsf)+" ");
             box[x][y]=false;
         }
     }
     return c;
 }

 int queen_comb_mat(vector<vector<bool>> & box, int tnq,int qpsf,int lqpl,string ans)  
  {
     if(tnq == qpsf)
     {
         cout<<ans<<endl;
         return 1;
     }
     int c=0;
     for(int i=lqpl ; i<box.size() * box[0].size();i++)
     {
         int x=i/box[0].size();
         int y=i% box[0].size();
         c+=queen_comb_mat(box,tnq,qpsf+1,i+1,ans+"("+to_string(x)+" , "+to_string(y)+") " +" Q"+to_string(qpsf)+" ");
     }
     return c;
 }

//******************************
void queen()
{
    vector<bool> box(5,false);
    vector<vector<bool>> boxes(4, vector<bool> (4,false));
    // cout<<queen_comb_01(5,3,0,0,"");
    // cout<<queen_permu_01(box,3,0,0,"");
     // cout<<queen_comb_02(5,3,0,0,"");
    // cout<<queen_permu_02(box,3,0,0,"");
    // cout<< queen_comb_mat(boxes,4,0,0,"");
   // cout<< queen_permu_mat(boxes,4,0,0,"");
}


//***************************** NQueen ***************************
// how many queens you can place in a 2d matrix so they don not kill each other
//find its permuatation and combination 

//  **************  this may approxaly take O(n*n) time   ***************

// Combinations of Queens
//queen is safe to place that particular block ie. they don not kill each other
bool isSafeToPlace(vector<vector<bool>> & box, int x,int y)
{
    vector<vector<int>> dir ={{0,-1} , {-1,-1} ,{-1,0} ,{-1,1} };
    for(int i=0 ; i< dir.size() ;i++)
    {
        for(int d=1 ; d<= max( box.size() , box[0].size() ) ; d++)
        {
            int row= x + d * dir[i][0];
            int col= y + d * dir[i][1];
            if( row>=0 && col>=0 && row<box.size() && col<box[0].size() && box[row][col] )
                return false;
        }
    }
    return true;
}

int NQueen_comb(vector<vector<bool>> & box, int tnq,int qpsf,int lqpl,string ans)  
  {
     if(tnq == qpsf)
     {
         cout<<ans<<endl;
         return 1;
     }
     int c=0;
     for(int i=lqpl ; i<box.size() * box[0].size();i++)
     {
         int x=i/box[0].size();
         int y=i% box[0].size();
         if(isSafeToPlace(box,x ,y))
          {
             box[x][y]=true;
            c+=NQueen_comb(box,tnq,qpsf+1,i+1,ans+"("+to_string(x)+" , "+to_string(y)+") " +" Q"+to_string(qpsf)+" ");
             box[x][y]=false;
         }
     }
     return c;
 }

// Permutation of Queens
//queen is safe to place that particular block ie. they don not kill each other
bool isSafeToPlaceper(vector<vector<bool>> & box, int x,int y)
{
    vector<vector<int>> dir ={{0,-1} , {-1,-1} ,{-1,0} ,{-1,1}, {0,1} , {1,1} ,{1,0} ,{1,-1} };
    for(int i=0 ; i< dir.size() ;i++)
    {
        for(int d=1 ; d<= max( box.size() , box[0].size() ) ; d++)
        {
            int row= x + d * dir[i][0];
            int col= y + d * dir[i][1];
            if( row>=0 && col>=0 && row<box.size() && col<box[0].size() && box[row][col] )
                return false;
        }
    }
    return true;
}

int NQueen_permu(vector<vector<bool>> & box, int tnq,int qpsf,int lqpl,string ans)  
  {
     if(tnq == qpsf)
     {
         cout<<ans<<endl;
         return 1;
     }
     int c=0;
     for(int i=0 ; i<box.size() * box[0].size();i++)
     {
         int x=i/box[0].size();
         int y=i% box[0].size();
         if(isSafeToPlaceper(box,x ,y)  && !box[x][y])
          {
             box[x][y]=true;
            c+=NQueen_permu(box,tnq,qpsf+1,i,ans+"("+to_string(x)+" , "+to_string(y)+") " +" Q"+to_string(qpsf)+" ");
             box[x][y]=false;
         }
     }
     return c;
 }

//  **************  this may approxaly take O(1) time   ***************

//combination
int nQueen(int r, int tnq ,vb &col , vb &diag ,vb &adiag, string ans)
{
    int n=col.size();
    if( r==n || tnq==0 )
    {
      if( tnq==0 )
      {
          cout<<ans<<endl;
          return 1;
      }
       return 0; 
    }

    int c=0;
    for(int i=0 ; i<col.size() ;i++)
    {
        if( !col[i] && !diag[r+i] && !adiag[r -i +n -1])
        {
            col[i] =true; 
            diag[r+i] =true; 
            adiag[r -i +n -1]=true;
           c+= nQueen(r+1,tnq-1,col,diag,adiag,ans+"B "+"("+to_string(r)+" , "+to_string(i)+") " +" Q"+to_string(r)+" ");
            col[i] =false; 
            diag[r+i] =false; 
            adiag[r -i +n -1]=false;
        }
    }
    return c;
}

// using bit 
int nQueen_bit(int r,int co, int tnq ,int &col , int &diag ,int &adiag, string ans)
{
    int n=co;
    if( r==n || tnq==0 )
    {
      if( tnq==0 )
      {
          cout<<ans<<endl;
          return 1;
      }
       return 0; 
    }

    int c=0;
    for(int i=0 ; i<n ;i++)
    {
        int x= 1<<i;
        int y = 1<<(r+i);
        int z = 1<<(r -i +n -1) ;
        if( !(col & x) && !(diag & y) && !(adiag & z))
        {
            col ^= x; 
            diag ^= y; 
            adiag ^= z;
           c+= nQueen_bit(r+1,co,tnq-1,col,diag,adiag,ans+"B "+"("+to_string(r)+" , "+to_string(i)+") " +" Q"+to_string(r)+" ");
            col ^= x; 
            diag ^= y; 
            adiag ^= z;
        }
    }
    return c;
}

void Nqueen()
{
    vector<vector<bool>> boxes(4, vector<bool> (4,false));
//    cout<< NQueen_comb(boxes,4,0,0,"");
//    cout<< NQueen_permu(boxes,4,0,0,"");

     int r=4,c=4;
    // vb col(c,false);
    // vb diag(r+c-1 ,false);
    // vb adiag(r+c-1 ,false);
    // cout<<nQueen(0,4,col,diag,adiag,"");

    int col=0;
    int diag=0;
    int adiag=0;
    cout<<nQueen_bit(0,c,4,col,diag,adiag,"");
}

//************************** SUDUKO  ***************
// suduko we have to fill box such a way the num doesnt come in the row ,col and 3*3 matrix


void display(vvi &board)
{
    for (vi ar : board)
    {
        for (int ele : ar)
        {
            cout << ele << " ";
        }
        cout << endl;
    }
    cout << endl;
}
bool isSafe( vvi &box,int x,int y,int num)
{
    for(int i=0;i<box[0].size();i++)   // check for col
    { if(box[i][y] == num) return false; }
    for(int i=0;i<box.size();i++)    // check for row
    { if(box[x][i] == num)  return false; }
    int row=(x/3)*3; int col=(y/3)*3;
    for(int i=0;i<3;i++)    //check for 3*3 matrix
    { for(int j=0;j<3;j++) 
        { if(box[row+i][col+j] == num)  return false; } }
    return true;
}
int Suduko(vvi & box,int idx)
{
    if(idx == 81)
    { display(box); return 1;  }
    int c=0;  int x=idx / 9;  int y=idx % 9;
    if(box[x][y] == 0)
    { for(int i=1 ;i<=9 ; i++)
        {  if(isSafe(box , x, y, i))
            {
                box[x][y]=i;   //place the num
                c+=Suduko(box , idx+1);
                box[x][y]=0;    //unplace the num 
            } } }
    else c+=Suduko(box , idx+1);
    return c;
}

//using bit and storing all the zero values previously  so that it consume less time 
vi row(9,0);
vi col(9,0);
vvi mat(3, vi (3 ,0));

int Suduko_1(vvi & box, vi &call, int idx)
{
    if(idx == call.size())
    {
        display(box);
        return 1;
    }
    int c=0;
    int x=call[idx] / 9;
    int y=call[idx] % 9;

    for(int i=1 ;i<=9 ; i++)
    {
        int mask = 1<<i;
        if( !(row[x] & mask) && !(col[y] & mask) && !(mat[x/3][y/3] & mask))
         {
                box[x][y]=i;  //place the num
                row[x] ^= mask; //set true;
                col[y] ^= mask;
                mat[x/3][y/3] ^= mask;
                c+=Suduko_1(box ,call, idx+1);
                box[x][y]=0;  //unplace the num
                row[x] ^= mask; //set false;
                col[y] ^= mask;
                mat[x/3][y/3] ^= mask; 
         }
     }
    
    return c;
}

//****************************

void suduko()
{
    
    vvi box = {{0, 0, 6, 0, 0, 8, 0, 0, 0},
                 {5, 2, 0, 0, 0, 0, 0, 0, 0},
                 {0, 8, 7, 0, 0, 0, 0, 3, 1},
                 {0, 0, 3, 0, 1, 0, 0, 8, 0},
                 {9, 0, 0, 8, 6, 3, 0, 0, 5},
                 {0, 5, 0, 0, 9, 0, 6, 0, 0},
                 {1, 3, 0, 0, 0, 0, 2, 5, 0},
                 {0, 0, 0, 0, 0, 0, 0, 7, 4},
                 {0, 0, 5, 2, 0, 6, 3, 0, 0}};
    
   //cout<< Suduko(box,0);

   //using bit and store 0 values prev

   vi call;    //1 D matrix
    for(int i=0;i<box.size();i++)    
    {
        for(int j=0;j<box.size();j++) 
        {
            if(box[i][j] == 0)
                call.push_back( i*9 + j );   // 2D to 1D
            else
            {
                int mask= 1<< box[i][j];
                row[i] |= mask;   //set true;
                col[j] |= mask; 
                mat[i/3][j/3] |= mask; 
            }
        }
    }
    cout<< Suduko_1(box,call,0);
}

//****************** CROSSWORD *************
vector<vector<char>> board = {
    {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
    {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
    {'+', '-', '-', '-', '-', '-', '-', '-', '+', '+'},
    {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
    {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
    {'+', '-', '-', '-', '-', '-', '-', '+', '+', '+'},
    {'+', '-', '+', '+', '+', '-', '+', '+', '+', '+'},
    {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
    {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
    {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}};

bool STPH(string w ,int i ,int j )
{
    for( int d=0 ; d< w.length() ; d++)
    {
        if( board[i][j+d] !='-' && board[i][j+d] != w[d])
        return false;
    }
    return true;
}

vb PWH( string w ,int i ,int j )
{
    vb loc ( w.length() , false);
    for( int d=0 ; d< w.length() ; d++)
    {
        if( board[i][j+d] =='-')
        {
            board[i][j+d]=w[d];
            loc[d]=true;
        }
    }
    return loc;
}

void UPWH( vb & pos ,int i ,int j )
{
     for( int d=0 ; d< pos.size() ; d++)
    {
        if( pos[d] ){
            board[i][j+d]= '-';
        }
    }
}

bool STPV(string w ,int i ,int j )
{
    for( int d=0 ; d< w.length() ; d++)
    {
        if( board[i+d][j] !='-' && board[i+d][j] != w[d])
        return false;
    }
    return true;
}

vb PWV( string w ,int i ,int j )
{
    vb loc ( w.length() , false);
    for( int d=0 ; d< w.length() ; d++)
    {
        if( board[i+d][j] =='-')
        {
            board[i+d][j]=w[d];
            loc[d]=true;
        }
    }
    return loc;
}

void UPWV( vb &pos ,int i ,int j )
{
     for( int d=0 ; d< pos.size() ; d++)
    {
        if( pos[d] ){
            board[i+d][j]= '-';
        }
    }
}

int crossWordUtil(vector <string> &words,int idx )
{
    if( idx == words.size())           //base case
    {
        for (vector<char> ar : board)      // for display only
         {
          for (char ele : ar)
          {
            cout << ele << " ";
          }
        cout << endl;
         }

        return 1;
    }

    int c=0;
    string w=words[idx];
    for(int i=0 ; i < board.size() ; i++)
    {
        for(int j=0 ; j < board[0].size() ; j++)
        {  
            if( board[i][j] == '-' || w[0] == board[i][j] )
            { 
                 if(STPV(w,i,j))             //STPV safe to place vertically
                {
                    vb pos= PWV(w,i,j);      // PWV place word vertically
                    c += crossWordUtil(words,idx+1);
                    UPWV(pos,i,j);             // UPWV unplace word vertically
                }
                if(STPH(w,i,j))          //STPH safe to place horizonatally
                {
                    vb pos= PWH(w,i,j);    // PWH place word horizontally
                    c += crossWordUtil(words ,idx+1);
                    UPWH(pos,i,j);             // UPWH unplace word horizontally
                }
               
            }
        }
    }

    return c;
}

void crossWord()
{
    vector<string> words = {"agra", "norway", "england", "gwalior"};
    cout << crossWordUtil(words, 0) << endl;
}

//************** misc *************

// word break according to dictionary 
vector<string> dict={"i","like","ilike","man","go","mango","and","sam","sung","samsung","ili"};

bool isPresent(string word)
{
    for( int i=0 ; i< dict.size() ; i++ )
    {
        if( word.compare( dict[i] )==0)
        return true;
    }
    return false;
}

int wordBreak(string ques, string ans)
{
    if(ques.length() == 0)
    {
        cout<<ans<<endl;
        return 1;
    }
    int c=0;
    string w="";
    for( int i=0;i< ques.length() ; i++ )
    {
         w += ques[i];
        if(isPresent(w))
        {
            c += wordBreak(ques.substr(i+1) , ans+w+" ");
        }
    }
    return c;
}

// for decoding an dencoding of strings like s1 send s2 more s3 money ie s1+s2=s3
string s1="send";
string s2="more";
string s3="money";
vi mapping(26,-1);
vb numset(10,false);

int decode( string s)
{
    int res=0;
    for(int i=0;i< s.length() ;i++)
    {
        int n= mapping[ s[i] - 'a'];
        res =res*10+ n;
    }
    return res;
}

// for decode the string into no then encode them if they satisfies s1+s2=s3
int dec_encode(int idx , string ans)
{
    if( ans.length() == idx )      //base case
    {
        if(mapping[s1[0]-'a']==0 || mapping[s2[0]-'a']==0 || mapping[s3[0]-'a']==0 )
        {
            return 0;
        }
        int n1=decode(s1);
        int n2=decode(s2);
        int n3=decode(s3);
        if( n1 + n2 == n3 )
        {
            cout<<n1<<" + "<< n2<<"  =  "<<n3<<endl;
             return 1;
        }
        return 0;
    }
    int c=0;
    for(int i=0;i<10 ;i++)
    {
        if( !numset[i] )
        {
            numset[i]=true;
            mapping[ans[idx] - 'a']=i;
            c+=dec_encode(idx+1 , ans);
            numset[i]=false;
            mapping[ans[idx] - 'a']=-1;
        }
    }
    return c;
}

// for finding the unqiue variable and their frequency
void map()
{
    vi freqmap(26,0);
    string s =s1+s2+s3;
    string ans="";

    for(int i=0 ; i<s.length() ; i++)
    {
        int idx =s[i]-'a';
        freqmap[idx]++ ;
    }

    for(int i=0 ; i<26 ;i++)
    {
        if( freqmap[i] != 0 )
        ans += (char) i+'a' ;
    }
    cout<<ans<<endl;
    cout<<dec_encode(0,ans);
}

void misc()
{
    // cout<<wordBreak( "ilikemangoandsamsung","");
   // map();
}
//*****************************
void solve()
{
    //basic();
    //path();
    //percomb();    
    // queen();
   // Nqueen();
  // suduko();
  //crossWord();
  misc();
  
}

int main()
{
    solve();
    return 0;
}
