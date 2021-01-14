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

