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

////////////////////////////////////////////////////////////
// 2^n aaega , nhi aaegaa
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


////////////////////////////////////////////////////////////////
Queen

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
///////////////////////////////////////////////////
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

////////////////////////////////////////////////////

COIN CHNAGE 

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
///////////////////////////////////////////////////////////////////

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

/////////////////
    
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
