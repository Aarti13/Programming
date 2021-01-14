//TOWER OF HANOI
// Aim: transfer all the shells from source to destination using helper 
void TOH(int n , int st, int dt, int ht)
{
    if( n==0)
    return;

    TOH( n-1 , st , ht, dt); // source to helper using destination
    cout<< n<< " "<<"[ "<<st<<" - "<< dt<<" ]"<<endl;
    TOH(n-1, ht, dt, st);
}
//display array
     public static void display(int[] arr)
     {      
             System.out.println("Array elements :");
             for(int i=0;i<arr.length;i++)
             {
                 System.out.print(arr[i]+"  ");
             }     
    }

    //find all the indexes in an array in which the data is found
     public static int[] allIndex(int[] arr,int vidx,int data,int c)
       {
         
         if(vidx==arr.length)
         {
              int[] ans=new int[c];
              return ans;
         }
         if(data== arr[vidx] )
         c++;
         int[] recans=allIndex(arr,vidx+1,data,c);
          if(data== arr[vidx] )
          {
               recans[c-1]=vidx;
         }
        return recans;
       }
// 1. Freeze the expectation PD(n) = n, n-1, n-2, .............  ie. PD(5) = 5 4 3 2 1 
// 2. Assume faith PD(n-1) = n-1,n-2,n-3........
// 3. Expectation = Faith PD(n) = n PD(n-1)
    public static int powera_b(int a ,int b)
    {
        if(b==1)
        return a*b;
        int res=powera_b(a,b-1);
        return a*res;
    }

    public static int powera_b1(int a ,int b)
    {
        if(b==0) return 1;
        int res=powera_b1(a,b/2);
    
        if((b&1 )==0) return res*res;
        else return res*a*res;
    }
int factorial(int n)
{
    if(n<=1)
    return 1;
    int res=factorial(n-1);
    return n*res;
}
///////////////////////////////
RECURSION WITH ARRAY
// Expectation:  print array : 10,20,30,40,50
    // Faith:  print 20,30,40,50 but array cant be shotened so we use index den we can print 
    public static void display(int [] arr , int idx)
    {
        if(arr.length == idx) return ;
        System.out.println(arr[idx]);  // print idx value
        display(arr , idx+1 );         // print idx+1 to arr.length values.
    }

    //print recusive array 50,40,30,20,10
     public static void Recdisplay(int [] arr , int idx)
    {
        if(arr.length == idx) return ;
        Recdisplay(arr , idx+1 );         // print idx+1 to arr.length values.
        System.out.println(arr[idx]);  // print idx value
    }

    // print max element from array
    public static int maxEle( int[] arr, int idx)
    {
        if(idx == arr.length-1 ) return arr[idx];
       
       int max= maxEle(arr, idx+1);
        if(arr[idx] > max)
            max=arr[idx];
        return max;
    }

    //linear search
    public static boolean linearSearch(int [] arr, int idx, int key)
    {
        if(idx == arr.length -1 ) return false;
        
        if( arr[idx] == key) return true;
        return linearSearch(arr , idx+1 , key);
    }

    //Binary Search
    public static boolean BinarySearch(int [] arr, int s, int e, int key)
    {
        while(s<= e)
        {
            int mid= (s+e )/2;
            if(arr[mid] == key) return true;
            else if(arr[mid] > key)  return BinarySearch(arr, s, mid-1 , key);
            else  return  BinarySearch(arr, mid+1, e , key);
        }
        return false ;
    }

    //find first ocuurence of key using linear search
    public static int firstOcurr(int [] arr, int idx, int key)
    {
        if(idx == arr.length -1 ) return -1;
        
        if( arr[idx] == key) return idx;
        return firstOcurr(arr , idx+1 , key);
    }

    //find last ocuurence of key using linear search
    public static int lastOcurr(int [] arr, int idx, int key)
    {
        //Method 1:
        //strt loop from last
        // if(idx == 0 ) return -1;
        // if( arr[idx] == key) return idx;
        // return lastOcurr(arr , idx-1 , key);

        //Method 2:
        // strt from frst
        if(idx == arr.length ) return -1;
        
        int i= lastOcurr(arr, idx+1 , key);
        // check in post order
        if(i != -1 ) return i;   // if return value is not -1 ie we found the ele from last
        else if( arr[idx] == key) return idx ;  // if it matches then return the index value
        else return -1;         // if we dont find the value return -1
    }

    // find all the indexs where the ele coming
    public static int[] AllOcurr(int [] arr, int idx, int key, int fsf)
    {
        if(idx == arr.length -1 ) return new int[fsf]; // return array of size fsf;
        
        if( arr[idx] == key)  
        {
            int[] res= AllOcurr(arr , idx+1 , key , fsf+1);
            res[fsf]=idx;
            return res;
        }
        else
        {
            int[] res= AllOcurr(arr , idx+1 , key , fsf);
            return res;
        }
    }

    //reverse an array
    public static void reverseArray( int[] arr, int l , int r)
    {
        if(l==r) return;
        if(l<r)
        {
            int temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;
        }
        reverseArray(arr, l+1, r-1);
    }

    //palindrome
    public static boolean palindrome( int[] arr, int l , int r)
    {
        if(l==r) return true;
        if(l<r)
        {
            if( arr[l] == arr[r] ) return palindrome(arr, l+1, r-1);
            else return false;
        }
        return false;
    }

     //find first ocuurence of key using binary search
    // public static boolean firstOcurrB(int [] arr, int s, int e, int key)
    // {
    //     while(s<= e)
    //     {
    //         int mid= (s+e )/2;
    //         if(arr[mid] == key) 
    //         {
    //             if( arr[mid-1] == key && mid-1 >=0 )
    //                return BinarySearch(arr, s, mid-1 , key);
    //             else return true;

    //         }
    //         else if(arr[mid] > key)  return BinarySearch(arr, s, mid-1 , key);
    //         else  return  BinarySearch(arr, mid+1, e , key);
    //     }
    //     return false ;
    // }
/////////////////////////////////////////////////////////
RECURSION WITH ARRAYLIST
//print roattaions
    public static void printRotations(  ArrayList<Integer> list ,int k )
    {
        int i=0;
      while(i!=k+1)
       {
           System.out.print(list);
           System.out.println();
           int t= list.get(list.size()-1);
           list.remove(list.size()-1);
           list.add(i,t);
            i++;
       } 
    }

    //remove duplicacy
    public static void  removeDuplicate(  ArrayList<Integer> list )
    {
        for(int i=1;i<list.size();i++)
        {
            if( list.get(i)== list.get(i-1))
           { list.remove(i);
            i--;
           }
        }
         System.out.print(list);
    }

        //sum of  duplicate value
    public static void  sumDuplicate(  ArrayList<Integer> list )
    {
        int sum=0;
        for(int i=list.size()-2 ;i>=0;i--)
        {
           if( list.get(i)== list.get(i+1))
           {    
               sum += list.remove(i+1);
            }
           else
           {  list.set(i+1,sum+ list.get(i+1));   
              sum=0;
            }

        }
           int val= list.get(0);
           val += sum;
           list.set(0, val);
         System.out.print(list);
    }

//stair Path 1
vector<string> gsp(int src)
{
   if(src == 0)
   {
       vector<string> bp;
       bp.push_back("");
       return bp;
   }

   vector<string> stod;

   for(int step = 1; step <= 3; step++)
    {
        if(src >= step)
        {
            int nbr = src - step;
            vector<string> ntod = gsp(nbr);

            for(int i = 0; i < ntod.size(); i++)
            {
                string ntodpath = ntod[i]; //
                string stodpath = to_string(step) + ntodpath; //
                stod.push_back(stodpath);
            }
        }
    }

    return stod;
}
//print all the sr to dest path where for particular source we can have arr[sr] paths
//stair path 2
vector<string> path( vector<int> arr , int sr, int ds)
{

    if( sr== ds)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }
    vector<string> nbr;
    vector<string> ans;
   for(int i=1 ;i<= arr[sr]  ;i++) 
   {
       if( sr+i <= ds)
       nbr= path(arr, sr+i, ds );
       for(int j=0;j<nbr.size() ;j++)
       {
           string str= to_string(j) +" "+nbr[j];
            ans.push_back(str);
       }
   }
   return ans;
}

// Maze path MULTIMOVE
vector<string> gmp2(int sr, int sc, int dr, int dc)
{
    if(sr == dr && sc == dc)
    {
        vector<string> bp;
        bp.push_back("");
        return bp;
    }

    vector<string> stod;

    // h nbrs
    for(int ms = 1; ms <= dc - sc; ms++)
    {
        vector<string> ntod = gmp2(sr, sc + ms, dr, dc);
        for(int i = 0; i < ntod.size(); i++)
        {
            stod.push_back("h" + to_string(ms) + ntod[i]);
        }
    }

    // v nbrs
    for(int ms = 1; ms <= dr - sr; ms++)
    {
        vector<string> ntod = gmp2(sr + ms, sc, dr, dc);
        for(int i = 0; i < ntod.size(); i++)
        {
            stod.push_back("v" + to_string(ms) + ntod[i]);
        }
    }

    // d nbrs
    for(int ms = 1; ms <= dr - sr && ms <= dc - sc; ms++)
    {
        vector<string> ntod = gmp2(sr + ms, sc + ms, dr, dc);
        for(int i = 0; i < ntod.size(); i++)
        {
            stod.push_back("d" + to_string(ms) + ntod[i]);
        }
    }

    return stod;
}

//recursion questions we are generating answering while going upwards

// stair Path 1
int StairPath(int n , string ans)
{
    if( n == 0) // ground level is slao end of journey but there is a path 
    {
        cout<<ans<<endl;
        return 1;
    }

    //bina soche call lagana REACTIVE approach 
    // basement is also end of journey but there is no path find from here

    // if(n <0 ) // ie - down the stairs so there is no path exist as it is basement 
    // {
    //     return 0;
    // }
    int c=0;
    
    //for productive call we already think for irrevalent calls
    // more effetive method k/a  PRODUCTIVE APPROACH

        if( n>=1) c+= StairPath(n-1, ans+ to_string(1));
        if( n>=2) c+= StairPath(n-2, ans+ to_string(2));
        if( n>=3) c+= StairPath(n-3, ans+ to_string(3));
    return c;
}

// keypad 

// stair Path 2
int StairPath2( vector<int> arr , int src, string ans)
{
    if( src== arr.size()-1)
    {
        cout<<ans<<endl;
        return 1;
    }
    int c=0;
    for(int i=1;i<=arr[src] ; i++)
    {
        if( src+i <= arr.size()-1)
            c+=StairPath2(arr, src+i , ans + to_string(i));
    }
    return c;
}

//maze path
//maze path 1
int mazePath1(int sr, int sc, int er, int ec, string ans)
{
    if( sr== er && sc==ec)
    {
        cout<< ans<<endl;
        return 1;
    }

    int c=0;
    if( sr+1 <= er ) c+=mazePath1( sr+1, sc, er, ec,ans+"V");
    if( sc+1 <= ec ) c+=mazePath1( sr, sc+1, er, ec,ans+"H");
    return c;
}
//maze path 2 multimove paths
int mazePath2(int sr, int sc, int er, int ec, string ans)
{
    if( sr== er && sc==ec)
    {
        cout<< ans<<endl;
        return 1;
    }

    int c=0;
    for( int i=1;i<=er-sr;i++) c+=mazePath2( sr+i, sc, er, ec,ans+"V"+to_string(i));
    for( int i=1;i<=ec-sc;i++)  c+=mazePath2( sr, sc+i, er, ec,ans+"H"+to_string(i));
    for( int i=1;i<=ec-sc;i++)  c+=mazePath2( sr+i, sc+i, er, ec,ans+"D"+to_string(i));
    return c;
}

//WORDBREAK 
void wordbreak(string ques, string ans, unordered_set<string>& dictionary)
{
    if(ques.length() == 0)
    {
        cout << ans << endl;
        return;
    }

    for(int i = 1; i <= ques.length(); i++)
    {
        string prefix = ques.substr(0, i);
        if(dictionary.count(prefix) == 1)
        {
            string ros = ques.substr(i, ques.length() - prefix.length());
            wordbreak(ros, ans + prefix + "-" , dictionary);
        }
    }
}
////////////////////////////////////////////////////////////
 public static ArrayList<String> subsequence( String s)
    {
        if( s.length()== 0  )
        {
            ArrayList<String> rbase = new ArrayList<>()  ;
            rbase.add("");
            return rbase;
        }

        char ch=s.charAt(0); // 'a'
        String ros = s.substring( 1 , s.length() );  // rest of string "bcd"

        ArrayList<String> ans = subsequence( ros) ;  // []
        ArrayList<String> myans =  new ArrayList<>() ;

        for(String e : ans)
        {
            myans.add('-' + e);
            myans.add(ch + e);
        }
        return myans;

    }

    //10 july
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

    //Queen
    //Method 1 objects at level 
    //find all the permutation of two objects in 4 boxes 

    public static void printPerm(int ci, int ti, String asf, boolean[] boxes){
        if(ci > ti){
            System.out.println(asf);
            return;
        }

        for(int i = 0; i < boxes.length; i++){
            if(boxes[i] == false){
                boxes[i] = true;
                printPerm(ci + 1, ti, asf + ci + "[" + i + "] ", boxes);
                boxes[i] = false;
            }
        }        
    }
    //find all the combination of two objects in 4 boxes 
    public static void printComb(int ci, int ti, int lib,  String asf, boolean[] boxes){
        if(ci > ti){
            System.out.println(asf);
            return;
        }

        for(int i = lib + 1; i < boxes.length; i++){
            if(boxes[i] == false){
                boxes[i] = true;
                printComb(ci + 1, ti, i, asf + ci + "[" + i + "] ", boxes);
                boxes[i] = false;
            }
        }        
    }

    //Method 2 boxes at level 
    //more efficient Method

    // cb=current box
    // n= total no of box / tb
    // bssf = box select so far 
    // r= total no of objects 

    //print combinations of selected boxes for items
    public static void printComb2( int cb , int n , int bssf , int r , String asf)
    {
        if( cb == n)
        {
         if( bssf == r)
         {
             System.out.println( asf);
         }
         return ;
        }

        // if item is selected by the box
        printComb2( cb+1, n, bssf+1 , r, asf+ cb);

        // if item is not selected by the box
        printComb2( cb+1, n, bssf , r, asf);
    }

    //print permutations of selected boxes for items
    public static void printPermu2( int cb , int n , int issf , int r , boolean[] item, String asf)
    {
        if( cb == n)
        {
         if( issf == r)
         {
             System.out.println( asf);
         }
         return ;
        }

        // if no item is selected by the box
        printPermu2( cb+1, n, issf , r, item, asf);

        // if item is 1 or 2  selected by the box
        for(int i=0;i<item.length; i++)
        {
            if( item[i] == false){
                item[i]=true;
                printPermu2( cb+1, n, issf+1 , r, item,asf+i+" B "+ cb+"  ");
                item[i]=false;
            }
        }
        
    }

    // COIN CHNAGE 

    // FOR INFINITE COINS:
    // permutation
    public static void coinPermutation( int [] coin , int tar ,String asf)
    {
        if( tar == 0 )
        {
            System.out.println(asf);
            return ;
        }

        for(int i=0;i<coin.length ;i++)
        {
            int a=tar - coin[i] ;
            if( a >= 0)
                coinPermutation(coin, tar-coin[i], asf+ coin[i]);
        }
    }
     // Combination
    public static void coinCombination( int [] coin ,int idx, int tar ,String asf)
    {
        if( tar == 0 )
        {
            System.out.println(asf);
            return ;
        }

        for(int i=idx;i<coin.length ;i++)
        {
            int a=tar - coin[i] ;
            if( a >= 0)
                coinCombination(coin,i, tar-coin[i], asf+ coin[i]);
        }
    }

    // N-Queen in Matrix
    // Permutation
    static int counter =0;
    public static void NQueenPermu( boolean [][] chess, int qpsf,String asf )
    {
        if( qpsf == chess.length){
            System.out.println( ++counter + " :" + asf+"  ");
            return;
        }

        for(int i=0;i<chess.length ;i++)
        {
            for(int j=0;j< chess[0].length ; j++)
            {
                if( chess[i][j] == false){
                    chess[i][j]=true;
                    NQueenPermu(chess, qpsf +1, asf +i+","+j+"  ");
                    chess[i][j]=false;
                }
            }
        }

    }
    // Combination

     public static void NQueenComb( boolean [][] chess,int isf, int jsf, int qpsf,String asf )
    {
        if( qpsf == chess.length){
            System.out.println( ++counter + " :" + asf+"  ");
            return;
        }

        for(int i= isf ;i< chess.length ;i++)
        {
            for(int j= ( isf == i? jsf+1 : 0  ); j< chess[0].length ; j++)
            {
                if( chess[i][j] == false){
                    chess[i][j]=true;
                    NQueenComb(chess,  i , j, qpsf+1 , asf +i+","+j+"  ");
                    chess[i][j]=false;
                }
            }
        }

    }

    // valid n-Queens placed in chess of n*n board

    static int[][] dir={{0,-1} , {-1,-1}, {-1,0}   ,{-1,1}};
    public static boolean isSafeToPlace( boolean[][] chess, int i , int j)
    {
        for(int k= 0  ; k < dir.length ; k++)
        {
            for(int d=1 ; d<= Math.max( chess.length , chess[0].length ) ; d++){

                int r = i+ d* dir[k][0];
                int c = j+ d* dir[k][1];
                if( r>=0 && c>=0 && r<chess.length && c< chess[0].length && chess[r][c] == true)
                    return false;
            }
       
        }
        return true;
    }

    public static void NQueen( boolean [][] chess,int isf, int jsf, int qpsf,String asf )
    {
        if( qpsf == chess.length){
            System.out.println( ++counter + " :" + asf+"  ");
            return;
        }

        for(int i= isf ;i< chess.length ;i++)
        {
            for(int j= ( isf == i? jsf+1 : 0  ); j< chess[0].length ; j++)
            {
                if( chess[i][j] == false && isSafeToPlace( chess , i , j) == true ){
                    chess[i][j]=true;
                    NQueen(chess,  i , j, qpsf+1 , asf +i+","+j+"  ");
                    chess[i][j]=false;
                }
            }
        }

    }

    public static boolean isSafeKnightToPlace( boolean[][] chess, int r , int c)
    {
        if( r >= 1 && c >= 2 && chess[r-1][c-2] == true ) return false;
        else if( r >= 2 && c >= 1 &&  chess[r-2][c-1] == true) return false;
        else if( r >= 2 && c < chess.length - 1 &&  chess[r-2][c+1] == true) return false;
        else if( r >= 1 && c < chess.length -2 &&  chess[r-1][c+2] == true) return false;

        return true;
    }

     // knight when queen at level and choice for box
    public static void knight( boolean [][] chess,int isf, int jsf, int qpsf,String asf )
    {
        if( qpsf == chess.length){
            System.out.println( ++counter + " :" + asf+"  ");
            return;
        }

        for(int i= isf ;i< chess.length ;i++)
        {
            for(int j= ( isf == i? jsf+1 : 0  ); j< chess[0].length ; j++)
            {
                if( chess[i][j] == false && isSafeKnightToPlace( chess , i , j) == true ){
                    chess[i][j]=true;
                    knight(chess,  i , j, qpsf+1 , asf +i+","+j+"  ");
                    chess[i][j]=false;
                }
            }
        }

    }

    // knight when box at level and choice for queen
    
    // public static void knight1( boolean [][] chess,int i, int j, int ksf,String asf )
    // {
    //     if( i == chess.length && j== 0)
    //     {
    //         System.out.println(asf);
    //         return;
    //     }
    //     else if( j== chess[0].length )
    //     {
    //         //no
    //         knight1( chess , i+)
    //     }
    // }

    //********************************************* */
    // encoding 
    // 1123 : aabc , aaw , kbc , kw , alc 
    // testcases : 1321 (4) , 1023 (2) 

    public static int encoding( String s, String asf )
    {
        int c = 0 ;
        if( s.length() == 0) {

            System.out.println(asf);
            return 1;
        }

        else if( s.length() == 1){

            char ch = s.charAt(0);
            String ros = s.substring(1, s.length() );
            if( ch != '0'){
                int num = ch -48 ;
                char csf = (char) ('a' + num - 1);
                 c += encoding( ros, asf + csf);
            }

        }

        else{
            
            char ch0 = s.charAt(0);
            String ros0 = s.substring(1, s.length() );
            if( ch0 != '0'){
                 int num = ch0 - 48 ;
                char csf = (char) ('a' + num - 1 );
                 c += encoding( ros0 , asf + csf);
            }

            char ch1 = s.charAt(1);
            String ros1 = s.substring( 2 , s.length()  );
            if( ch0 != '0'){
                int num1 = (ch0 - 48) * 10 + (ch1 - 48 ) ;
                if( num1 >= 10 && num1 <= 26)
                {
                        char csf = (char) ('a' + num1 - 1);
                        c += encoding( ros1, asf + csf);
                }
                   
            }

        }
        return c;
    }


    // WORDBREAK 
    public static List<String > wordBreak(String s ,  List<String> wordDict){
    if(s.length()==0)
      {
        List<String>bres=new ArrayList<String>();
        bres.add("");
        return bres;
      }
       List<String>mres=new ArrayList<String>();
      for(int i=0;i<s.length();i++)
      {
           String prefix=s.substring(0,i);
          if(wordDict.contains(prefix))
          {
              String roq=s.substring(i);
              List<String>rres=wordBreak(roq,wordDict);
              for(String ch:rres)
              {
                mres.add(prefix+""+ch);
              }
          }
      }
       return mres;
    }

    // In c++

    // CRYPTO ARITHMETIC
    // SEND + MORE = MONEY

    static String s1= "send";
    static String s2= "more";
    static String s3= "money";
    static boolean[] takenNo = new boolean[10];
    static int[] map = new int[26]; 


    // to get the numbers from string and mapped aaray
    public static int getNumber( String s )
    {
        int ans = 0;

        int mul = 1;
        for( int i=s.length()-1 ; i>=0 ;i--){
           ans += mul * map[s.charAt(i) - 97 ];
           mul *= 10;
        }
        return ans;
    }

    public static void crypto(String unique )
    {
        if( unique.length() == 0 )
        {
            int n1 = getNumber( s1);
            int n2 = getNumber( s2);
            int n3 = getNumber( s3);

            if( n1+n2 == n3 )
            {
                for(int i = 0; i < 26; i++){
                    if(map[i] != -1){
                        System.out.print((char)(i + 97) + "=" + map[i] + 
                        ", ");
                    }
                 }
                     System.out.println();
            }

            return ;
        }

        char s= unique.charAt(0);
        String ros = unique.substring( 1 , unique.length());
        for( int i = 0 ;i<=9 ; i++){
            if( takenNo[ i ] == false)
            {
                map[ s- 97 ] = i;
                takenNo[ i ] = true;
                crypto( ros  );
                map[ s- 97 ] = -1;
                takenNo[ i ] = false;
                
            }
        }
    }
    
    // for finding the unqiue variable and their frequency
    public static String Unique_Map( )
    {
        String ans= "";
         boolean[] duplicates = new boolean[26];
        for( char ele : s1.toCharArray())
        {
            if( duplicates[ ele - 97 ] == false){
                ans += ele;
                duplicates[ ele - 97 ] = true;
            }
        }
        
        for( char ele : s2.toCharArray())
        {
            if( duplicates[ ele - 97 ] == false){
                ans += ele;
                duplicates[ ele - 97 ] = true;
            }
        }
        
        for( char ele : s3.toCharArray())
        {
            if( duplicates[ ele - 97 ] == false){
                ans += ele;
                duplicates[ ele - 97 ] = true;
            }
        }

        return ans;
    }

    // SUDUKO
/*
    bool isValid( vector<vector<char>>& board , int r , int c , char option)
    {
        for( int i = 0 ; i<board.size() ;i++)
        {
            if( board[i][c] == option) return false;
        }
        
         for( int i = 0 ; i<board.size() ;i++)
        {
            if( board[r][i] == option) return false;
        }
        
         for(int ii = 0; ii < 3; ii++){
            for(int jj = 0; jj < 3; jj++){
                int row = r / 3 * 3 + ii;
                int col = c / 3 * 3 + jj;

                if(board[row][col] == option) return false;
            }
        }
        return true;
    }
    void solve(vector<vector<char>> & board , int i , int j)
    {
        if( i== board.size() && j == 0 ) return ;
        
        else if( j == board.size()- 1 )
        {
            if(board[i][j] != '.')
                solve(board , i+1 , 0 );
            else{
                
                for( int op =1; op<=9 ; op++)
                {
                    if( isValid( board , i , j , op ) == true ){
                        board[i][j] = op;
                        solve(board, i+1 , 0);
                        board[i][j] = '.';
                    }
                }
            }
        }
        
        else
        {
            if(board[i][j] != '.')
                solve(board , i , j+1 );
            else{
                
                for( int op =1; op<=9 ; op++)
                {
                    if( isValid( board , i , j , op ) == true ){
                        board[i][j] = op;
                        solve(board, i , j+1);
                        board[i][j] = '.';
                    }
                }
            }
        }
    }
    
    void solveSudoku(vector<vector<char>>& board) {
        
        solve(board , 0 , 0);
    }
*/


















class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        
       vector<vector<int>> ans;
        int sum=0;
        
        sort(nums.begin() , nums.end());  // firstly sort the array
         
        for( int i=0 ; i<nums.size() ; i++ ){
            
            // for duplicacy
            if( i >0 && nums[i]== nums[i-1]) continue;
            
            int l = i+1;  // left 
            int r = nums.size()-1;  //right
            int x = i;     // fixed value
            
            while( l<r ){
                
                sum=nums[x] +nums[l] +nums[r];
                if( sum==0){
                    ans.push_back(vector<int>{nums[x] ,nums[l] ,nums[r]}) ;
                    
                    //for duplicacy
                    while( l <r && nums[l] == nums[l+1]) l++;
                    while(l < r && nums[r]== nums[r-1]) r--;
                    
                    l++;
                    r--;
                }
                else if( sum < 0 ) l++;
                else r--;
                
            }
        }
        return ans;
    }
};












vector<int> Solution::plusOne(vector<int> &A) {
    
    vector<int> ans;
    int sum = 0;
    int carry = 1;
    int c=0;
    // for shifting the elelments if we encounter 0 
    
    while(A.front() == 0 && A.size()>0){
        A.erase(A.begin());
    }

    for( int i =A.size()-1 ; i>=0 ; i-- ){
        sum = carry + A[i];
        int r = sum % 10;
        carry = sum/10;
        ans.push_back(r);
    }
    if( carry == 1) ans.push_back(1);
    reverse( ans.begin() , ans.end());
    
    return ans;
}

// Maximum absolute difference

int Solution::maxArr(vector<int> &A) {
    
    int m = 0;
    // basic Approach O(n^2)
    // for( int i =0 ; i<A.size() ; i++){
    //     for( int j =0 ; j<A.size() ; j++){
    //       m = max(m , abs(A[i]-A[j]) + abs( i-j) );
    //     }
    // }
    
    // abs(A[i]-A[j]) + abs( i-j)
    // can be written as if A[i]  > A[j]   i >  j   ie A[i]+ i - (A[j] + j)
    // A[i]  < A[j]   i >  j   ie -A[i] + i + A[j] - j 
    // A[i]  > A[j]   i <  j   ie A[i] - i + j - A[j] 
    // A[i]  < A[j]   i <  j   ie - ( A[i]+ i ) + (A[j] + j)
    
    int max1 = -1e7 ;
    int min1 = 1e7 ;
    int max2 = -1e7 ;
    int min2 = 1e7 ;
    
    for( int i =0 ; i<A.size() ; i++){
        
        max1 = max( max1 , A[i] + i );
        min1 = min( min1 , A[i] + i );
        
        max2 = max( max2 , A[i] - i  );
        min2 = min( min2 , A[i] - i  );
       
    }
     m = max( max1 - min1 , max2 - min2 );
    return m;
}

// 3 , 30 if ab 330 if ba 303 teh array not be sorted in ascending order 
// as for 3 39  asc: 339 desc : 393  // so we use own compareTo ()
// calculate the largest no
//O( length of largest string * nlogn // sorting TC )

//[3, 30, 34, 5, 9], the largest formed number is 9534330.

bool criterea(int& x, int& y)
{
    long long int xy=stoll(to_string(x)+to_string(y)); // string to long long
    long long int yx=stoll(to_string(y)+to_string(x));
    return xy>=yx;
}
string Solution::largestNumber(const vector<int>& A)
{
    string ans="";
    vector<int>B(A);
    sort(B.begin(),B.end(),criterea);
    for(auto val:B)
        ans+=to_string(val);
    return (ans[0]=='0')?"0":ans;
}

// roatate a 2D matrix in clockwise direction
void Solution::rotate(vector<vector<int> > &A) {
    
    // tranpose of an array 
    for( int i =0;i<A.size() ; i++){
        for( int j =i; j<A.size() ; j++){
            int temp= A[i][j];
            A[i][j] = A[j][i];
            A[j][i] = temp ;
        }
    }
    
    int i =0, j=A.size()-1;
    while( i< j){
    for( int k=0;k<A.size() ; k++){
  
        int temp= A[k][i];
        A[k][i] = A[k][j];
        A[k][j] = temp ;
       
    }
     i++;  j--;
    }
}

int Solution::solve(vector<string> &A) {
    int n=A.size();
    vector<double> arr(n);
    for(int i=0;i<n;i++)
    {
        arr[i]=stod(A[i]);
    }
    sort(arr.begin(),arr.end());
    //initially i point to first,j points to second and k point to last element.
    int i=0,j=1,k=n-1;
    while(true)
    {
        
        double sum=arr[i]+arr[j]+arr[k];
        //if sum is in range return true
        if(sum>1&&sum<2) return true;
        // if sum<=1 then we need to increase the j index (j index can be increased till k-1)
        // if j gets to k-1 then another option is to increase ith index (till j-1)
        if(sum<=1)
        {
            if(j<k-1) j++;
            else if(i<j-1) i++;
        }
        
        //if sum is greater than 2 then decrease k till  j+1 so that all three numbers remain unique
        //since we are never reducing j we dont need to track whether j becomes less than i+1 or not
        if(sum>=2)
        {
            if(k>j+1) k--;
        }
        sum=arr[i]+arr[j]+arr[k];
        //finally if the three numbers comes to consecutive index and even after this the sum is 
        //not in the given range then we need to return false
        if(i==j-1&&j==k-1&&(sum>2||sum<1)) return false;
    }
    return false;
    
}


void  reverse(int i , int j ,vector<int> &A) {
    
    while( i< j){
        swap(A[i], A[j]);
        i++; j--;
    }
}

vector<int> Solution::nextPermutation(vector<int> &A) {
    // next permutation means :
    // just next graeter value from given value in its all permuatations
    // Brute Force : O(n!) ie find all teh permutation of given array and den find the just greter value
    
    //O(n) 
    // 6 , 2, 1, 5 , 4, 3, 0  IP  find the ele from last ie not in inc order ie 1
    // if we find all the ele in inc order just return sorted array
    // 6 , 2, 3, 5, 4, 1, 0 // swap 1 and 3
    // 6, 2, 3, 0, 1, 4, 5 // OP   reverse all the elements from 3 ie 5410 -> 0145
    
    int k = A.size() - 2;
    while( k >=0 && A[k] >= A[k+1]) --k;
    
    if( k == -1 )// last permutation so print in sorted manner ie reverse it
    { reverse(0 , A.size()-1, A);
      return A;
    }
     
    // else find the next greter ele A[k] and swap it
    
    for( int i = A.size() -1; i>k; i--){
        if(A[i] > A[k]) {
            swap(A[i], A[k]);
            break;
        }
    }
    //den reverse the rest from k+1, A.size()-1
    reverse(k+1 ,A.size() -1, A);
    
return A;
}


// find permutation ie IDDDIID -> 15432687
// DIIDDDDI -> 213876549
vector<int> Solution::findPerm(const string A, int B) {
    
    vector<int> ans ;
    stack<int> st;
    int j=1;
    for(int i=0;i< B; i++)
    {
        st.push(j++);
        if( A.size() == i || A[i] == 'I')
        {
            while(st.size() > 0){
               ans.push_back(st.top());
               st.pop();
            }
        }
    }
    return ans;
}


//Merge Overlapping Intervals
/**
 * Definition for an interval.
 * struct Interval {
 *     int start;
 *     int end;
 *     Interval() : start(0), end(0) {}
 *     Interval(int s, int e) : start(s), end(e) {}
 * };
 */
 
 bool compare( Interval a, Interval b){
     if(a.start != b.start ) return a.start < b.start;
     else return a.end < b.end;
 }
vector<Interval> Solution::merge(vector<Interval> &A) {
  
    vector<Interval> ans;
    stack<Interval > st ;
    int n = A.size();
    // sort the intervals
    sort( A.begin() , A.end() , compare);
    
    
    for( int i =0 ;i<n ; i++ ){
        
        if( i == 0) st.push( A[i] ); // pushing frst interval
        else
        {
            Interval val = st.top();
            if( A[i].start <= val.end ){
                val.start= val.start;
                val.end = max( A[i].end , val.end );
                st.pop();
                st.push(val);
            }
            else st.push(A[i]);
        }
            
    }
    
    while( st.size() != 0 ){
        Interval a = st.top();
        ans.push_back(st.top());
        st.pop();
    }
    reverse(ans.begin() , ans.end());
  
    return ans; 
}

// Prime sum
bool isPrime( int n ){
    
    if( n<= 1)return false;
    for( int i =2 ; i<= sqrt(n) ; i++){
        if( n% i == 0) return false;
    }
    return true;
}

vector<int> Solution::primesum(int A) {
    
    vector<int> ans ;
    for( int i =2 ; i<A ; i++ ){
            if( isPrime(i) && isPrime(A-i)){
                    ans.push_back(i);
                    ans.push_back(A-i);
                    return ans;
            }
    }
    return ans;
}


int Solution::hammingDistance(const vector<int> &A) {
    
    // Hamming Distance :
    //   1 2 3
    // 2 0 1 0   different pairs ( 2, 4) ( 2, 6 )     (4 , 2) ( 2, 6)
    // 4 1 0 0   ( 2, 4) ( 4, 6)         opposite  ( 6 , 4 )     (4 , 2) 
    // 6 1 1 0
    // ans 8 pairs with different bits 
    
    // brute force 
    // int setBits = 0; 
    // for( int i=0;i<A.size() ; i++ ){
    //     for( int j=0;j<A.size() ;j++){
            
    //     int x = A[i]^A[j];
    //     while (x > 0)  
    //     { 
    //          setBits += x & 1; 
    //          x >>= 1; 
    //      } 
    //     }
    // }
    // return setBits;
    
    // trick   for each index count no of 1 and 0 and (no of 1 * no of 0) * 2 // as we also needed opposite
    long long n = A.size();
    long long res = 0;
    
    for(int i=0;i<32;i++){
        int countone=0;
        for(int j=0;j<n;j++){
            if(A[j] & 1<<i)
            countone++;
        }
        res += countone * (n-countone) *2;
    }
    
    return res % 1000000007;
}


// A can be expressed in A^P A>0 , P>1
// 4 2^2 
// Basic Idea A^P = n log both side
//            Plog(A) = log(n)
//            P = log(n) /log(A)
int Solution::isPower(int A) {
    
    int p;
    for( int i =1;i<= sqrt(A) ; i++){
       p = log(A)/log(i);
       if( pow(i,p) == A) return 1;
    }
    return 0;
}


/*
    Input:
	A  0 1 5  
	B  1  
	C  2   All combinations of 1 digit nos from A is { 1, 5 }
	Output:  2 (0 and 1 are possible)  

	Input:
	  0 1 2 5  
	  2  
	  21    2 digit no { 11,12, 15 ,20,21,25,51,51 , 52... } 
	Output:  5 (10, 11, 12, 15, 20 are possible)
	
	// All Cases:
	case1 : B > C ie 4 digits no > 33 // No solution
	case2 : B < C ie 2 digits no <  999 // all nos are smaller ie all possible combinations
	        A.size() c B 
	        // using power function
	         - -  4* 4 = pow(A.size() , B)
	         - -  3* 4 = A.size()-1 * pow(A.size() , B-1)
	case3 : B == C ie 2 == 23
	        // find all combination of A with 2 digits no 
	        den find all no less than 23
*/
int Solution::solve(vector<int> &A, int B, int C) {
    
    if( A.size() == 0 )return 0 ; // no values in A
    int temp =C , count =0 , ans =0 ;// c no of digits in C
    
    while( temp!=0){
        count++;
        temp = temp/10;
    }
    
    if( B > count  ) return 0; // case 1
    
    else if( B < count ) {    // case 2
    
        if( A[0] == 0 ) ans = (A.size()-1 )* pow(A.size() , B-1);
        else ans = pow(A.size() , B);
        
        if( B == 1 && A[0] == 0 ) ans++ ;  // 0 also included in ans 
        return ans;
    }
    
    else{  // case 3
    
        if( B == 1 )// for 1 digit values all < C comes
        {
            for( int i =0 ; i< A.size() ; i++ ){
                if( A[i] < C ) ans++;
            }
        }
        
        // for greater than 1 digit values 
        else{
            
            // storing C no digit values in an array
            vector<int> Carr( B );
            for( int i = B-1 ; i>=0 ; i--){
                Carr[i] = C % 10 ;
                C = C/10;
            }
            
            // counting all the digits starting from the C[0] index in A
            count = 0;
            for( int i =0 ; i< A.size() ; i++ ){
                
                if( A[i] == 0 ) continue; // 0 not counted
                if( A[i] >  Carr[0] ) break; // value exceed break
                count++ ; 
            }
            ans += (count ) * pow( A.size() , B-1 );
            
            // if it conatins some extra value ie C 23 ans contains 22 24 25 27 ..
            
            // assuing that it contains the no starting from C[0]
            int flag = 0 , j=1  ;
            for( int i=0;i<A.size() ; i++ ){
                if( Carr[0] == A[i] ) flag=1;
            }
            
            // counting all the nos > 23 
            
            while( flag == 1 && j<= B-1 ){
                flag = 0;
                int countx=0;
                for( int i=0;i<A.size() ; i++ ){
                    if( Carr[j] < A[i] ) countx++;
                    if( Carr[j] == A[i] )  flag=1;
                }
                
                // if it contains 23 we have to exclude it also
                
                ans -=( (countx ) * ( pow( A.size() , B-j-1 ) ) );
                j++;
            }
            if( j==B && flag == 1 ) ans--;
        }
        
    }
 return ans;
}

// Rearrange array ie A[i] = A[A[i]]  4 0 2 1 3 -> 3 4 2 0 1
void Solution::arrange(vector<int> &A) {
    
    int n = A.size();
    for (int i=0; i < n; i++) 
        A[i] += (A[A[i]]%n)*n; 
  
    for (int i=0; i<n; i++) 
        A[i] /= n;
  
}
/////////////////
    public static ArrayList<String> subSequence(String str)
    {
        if(str.length()==0) 
        {
            ArrayList<String> base=new  ArrayList<String>();
            base.add(" ");
            return base;
        }
        char ch=str.charAt(0);
        String ros=str.substring(1);
        ArrayList<String> recAns=subSequence( ros);

         ArrayList<String> myAns=new  ArrayList<String>();
        
        myAns.addAll(recAns);
        for(String s:recAns)
              myAns.add(ch+s);
     return myAns;
    }
    
    // hihitiihihitiihi  tiitii
    public static String removeHi(String ques)
    {
       if(ques.length()==0)
       return "";
        if(ques.length()>1 && ques.charAt(0)=='H'&& ques.charAt(1)=='i') 
           return removeHi(  ques.substring(2));
        else
        {
          char ch1=ques.charAt(0);
           return ch1+removeHi(ques.substring(1));
        }
      
    }

    // hihitiihihitiihi     hitiihiti
    public static String removeHiBNHIT(String ques)
    {
       if(ques.length()==0)
       return "";
        if(ques.length()>1 && ques.substring(0,2).equals("hi")) 
        {
            if( ques.length()>2 && ques.substring(0,3).equals("hit"))
            
                return "hit"+removeHiBNHIT(ques.substring(3));
            else
             return removeHiBNHIT(  ques.substring(2));
        }
           
        else
        {
          char ch1=ques.charAt(0);
           return ch1+removeHiBNHIT(ques.substring(1));
        }
      
    }
//  aaaabbccdeff        a4 b2 c2 def2
    public static String compression(String ques,int idx,int count)
    {
       if(ques.length()==idx)
       return  ques.charAt(idx-1)+(count>1?count+" " : "");
       if(ques.charAt(idx-1)==ques.charAt(idx))
       return compression(ques,idx+1,count+1);
       else 
       {
          String str=ques.charAt(idx-1)+(count>1?count+" " : "");
          return str+compression(ques,idx+1,1);
      
       }

    }
// aabbccddddeee   abcde
    public static String removeDup(String ques)
    {
       if(ques.length()==0)
       return "";
       if(ques.length()==1)
       return ques;
       String recAns=removeDup(ques.substring(1));
        if(ques.charAt(0)==recAns.charAt(0)) 
           return recAns;
        else
        {
          char ch1=ques.charAt(0);
           return ch1+recAns;
        }
     }

   // (abcdef)cc(aabe(())imp(pqr))    (abcdef)(pqr)
  //  public static String printVIBraces(String ques,int c)
  //  {
      
  //  }
    /*   0       1      2
    0  |-------------------|                  
    1  | ------------------|
       |-------------------|
    2  |------------------ |
    */
      public static ArrayList <String> mazePath(int sr,int sc,int er,int ec)
      {
           ArrayList<String> ans=new  ArrayList<String>();
          if(sr==er&& sc==ec)
          {
               ArrayList<String> base=new  ArrayList<>();
               base.add(" ");
               return base;
          }
          if( sc+1<=ec)
          {
              ArrayList<String> horizontal= mazePath( sr, sc+1, er, ec); 
             for(String s:horizontal){
                 ans.add("H"+s);
             }
          }
        
           if( sr+1<=er)
          {
              ArrayList<String> verticle=mazePath( sr+1, sc, er, ec); 
             for(String s:verticle) {
                ans.add("V"+s); 
             } 
         }
        return ans; 
          
      }

      //for diagonal maze:
       public static ArrayList <String> mazePath_diag(int sr,int sc,int er,int ec)
      {
           ArrayList<String> ans=new  ArrayList<String>();
          if(sr==er&& sc==ec)
          {
               ArrayList<String> base=new  ArrayList<>();
               base.add(" ");
               return base;
          }
          if( sc+1<=ec)
          {
              ArrayList<String> horizontal= mazePath_diag( sr, sc+1, er, ec); 
             for(String s:horizontal){
                 ans.add("H"+s);
             }
          }
           if(sr+1<=er && sc+1<=ec)
          {
              ArrayList<String> diagonal= mazePath_diag( sr+1, sc+1, er, ec); 
             for(String s:diagonal){
                 ans.add("D"+s);
             }
          }
           if( sr+1<=er)
          {
              ArrayList<String> verticle=mazePath_diag( sr+1, sc, er, ec); 
             for(String s:verticle) {
                ans.add("V"+s); 
             } 
         }
        return ans; 
          
      }
      //maximum height of the tree : 2,2 =4 2,6 =10
       public static int mazePath_diag_Height(int sr,int sc,int er,int ec)
      {
          int maxHeight=0;
          if(sr==er&& sc==ec)
               return 0;
          if( sc+1<=ec)
              maxHeight=Math.max( maxHeight,mazePath_diag_Height( sr, sc+1, er, ec)) ; 
           if(sr+1<=er && sc+1<=ec)
              maxHeight=Math.max(maxHeight,mazePath_diag_Height( sr+1, sc+1, er, ec)); 
           if( sr+1<=er)
              maxHeight=Math.max(maxHeight,mazePath_diag_Height( sr+1, sc, er, ec)); 
         return maxHeight+1; 
      }

      //maze path multi move to reach diagonal from how much jumps are reuqired  d1d1 d2
      public static ArrayList <String> mazePath_diag_multimove(int sr,int sc,int er,int ec)
      {
           ArrayList<String> ans=new  ArrayList<String>();
          if(sr==er&& sc==ec)
          {
               ArrayList<String> base=new  ArrayList<>();
               base.add(" ");
               return base;
          }
          for(int i=1; sc+i<=ec;i++)
          {
              ArrayList<String> horizontal= mazePath_diag_multimove( sr, sc+i, er, ec); 
             for(String s:horizontal){
                 ans.add("H"+i+s);
             }
          }
           for( int i=1;sr+i<=er;i++)
          {
              ArrayList<String> verticle=mazePath_diag_multimove( sr+i, sc, er, ec); 
             for(String s:verticle) {
                ans.add("V"+i+s); 
             } 
         }
           for(int i=1; sc+i<=ec ;i++)
          {
              ArrayList<String> diagonal= mazePath_diag_multimove( sr+i, sc+i, er, ec); 
             for(String s:diagonal){
                 ans.add("D"+i+s);
             }
          }
          
        return ans; 
          
      }
      public static ArrayList<String> floodFill(int sr,int sc,int er,int ec,boolean isdone[][])
      {
           ArrayList<String> ans=new  ArrayList<String>();
          if(sr==er && sc==ec)
          {
               ArrayList<String> base=new  ArrayList<>();
               base.add(" ");
               return base;
          } 
          isdone [sr][sc]=true;
          if(sr-1>=0 && isdone[sr-1][sc]== false)
          {
              ArrayList<String> upward=floodFill( sr-1, sc, er, ec,isdone); 
             for(String s:upward) {
                ans.add("U"+s); 
             } 
         }
            if( sc+1<=ec && isdone [sr][sc+1]==false)
          {
              ArrayList<String> right= floodFill( sr, sc+1, er, ec,isdone); 
             for(String s:right){
                 ans.add("R"+s);
             }
          }
          if( sr+1<=er && isdone [sr+1][sc]==false)
          {
              ArrayList<String> downward=floodFill( sr+1, sc, er, ec,isdone); 
             for(String s:downward) {
                ans.add("D"+s); 
             } 
         }
         if(sc-1>=0 && isdone [sr][sc-1]==false)
          {
              ArrayList<String> left= floodFill( sr, sc-1, er, ec,isdone); 
             for(String s:left){
                 ans.add("L"+s);
             }
          }
           isdone [sr][sc]=false;
           
          
        return ans; 
          

      }
       // check whether the block is blocked or not the path is valid:
      public static boolean isValid(int x,int y,boolean [][] isdone,boolean [][] path)
      {
          if(x>=0 && y>=0 && x<isdone.length && y<isdone[0].length && !path[x][y] && !isdone[x][y]){
          return true;
          }else{
              return false;
          }
      }
      // no of path for printing all the 8 directions :
       public static ArrayList<String> floodFillDiagonal(int sr,int sc,int er,int ec,boolean isdone[][], boolean [][] path)
      {
          int [][] dir={{1,0},{0,1},{-1,0},{0,-1},{1,1},{-1,-1},{-1,1},{1,-1}};
          String [] dirName={"D","R","U","L","1","3","4","2"};
           ArrayList<String> ans=new  ArrayList<String>();
          if(sr==er && sc==ec)
          {
               ArrayList<String> base=new  ArrayList<>();
               base.add(" ");
               return base;
          } 
          isdone [sr][sc]=true;
         for(int d=0;d<dir.length;d++)
         {
             int x=sr+dir[d][0];
              int y=sc+dir[d][1];

               if(isValid(x,y,isdone,path))
         {
             
             ArrayList<String> calls=floodFillDiagonal(x,y,er,ec,isdone,path);
               for(String s:calls){
                 ans.add(dirName[d]+s);
             }

         }
         }
        
           isdone [sr][sc]=false;
           
          
        return ans; 
          

      }
  
      public static boolean isValid2(int x,int y,boolean [][] isdone)
      {
          if(x>=0 && y>=0 && x<isdone.length && y<isdone[0].length &&  !isdone[x][y]){
          return true;
          }else{
              return false;
          }
      }
        // no of path for horse moves in a chess game:
       public static int knightPath(int sr,int sc,int er,int ec,boolean isdone[][])
      {
          int [][] dir={{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-2},{2,-1}};

          if(sr==er && sc==ec)
          {
               return 1;
          } 
          isdone [sr][sc]=true;
          int count=0;
         for(int d=0;d<dir.length;d++)
         {
             int x=sr+dir[d][0];
              int y=sc+dir[d][1];

               if(isValid2(x,y,isdone))
            count +=knightPath(x,y,er,ec,isdone);
         }
        isdone [sr][sc]=false;
         return count; 
        }

         // one  path for horse moves in a chess game in matrix form:
       public static boolean knightOnePath(int sr,int sc,int count,int boxSize,boolean isdone[][],int ans[][])
      {
          int [][] dir={{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-2},{2,-1}};
           isdone [sr][sc]=true;
          ans[sr][sc]=count;
          if(count==boxSize-1)
          {
              for(int [] ar:ans)
         {
             for(int ele:ar)
             {
                System.out.print(ele+" ");
             }
             System.out.println();
         }
               return true;
          } 

         
          boolean res=false;
        
         for(int d=0;d<dir.length && !res;d++)
         {
             int x=sr+dir[d][0];
              int y=sc+dir[d][1];

               if(isValid2(x,y,isdone))
               {
                    res=res || knightOnePath(x,y,count+1,boxSize,isdone,ans);
               }
               
         }
        isdone [sr][sc]=false;
         ans[sr][sc]=0;
         return res; 
      }
//////////////////////
