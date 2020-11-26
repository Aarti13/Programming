// ***************** Strongly Connected Component ***************

#include<iostream>
#include<vector>
#include<list>

using namespace std;

class Edge
{
    public:
    int v=0;

    Edge() {}

    Edge(int v)
    {
        this->v=v;
    }
};

 vector < vector <Edge *>> graph ;
 vector < vector <Edge *>> graphT ;

void addEdge(int u ,int v)
{
   graph[u].push_back(new Edge(v)) ;
}

void addEdge2(int u ,int v)
{
   graphT[v].push_back(new Edge(u)) ;
}

void DFS(int src, vector<bool> & vis, vector<int> & stack)
{
    vis[src]=true;
    for(Edge * e:graph[src])
    {
        if( ! vis[e->v]) 
            DFS(e->v,vis ,stack);
    }
      stack.push_back(src);
}

void DFS2(int src, vector<bool> & vis)
{
    vis[src]=false;
    int count=0;
    for(Edge * e:graphT[src])
    {
        if(  vis[e->v]) 
        {
            DFS2(e->v,vis );
        }
    }
    
}

void solve()
{
    int m=6;
    //cin>>m;
    for(int i=0;i<m;i++)
    {
        vector<Edge *> ar;
        graph.push_back(ar);

        vector<Edge *> arr;
        graphT.push_back(arr);
    }
    
        //  addEdge(1,0);
        //  addEdge(2,1);
        //  addEdge(0,2);
        //  addEdge(0,3);
        //  addEdge(3,4);

      //  ******graph 2 ******
        //  addEdge(0,1);
        //  addEdge(1,2);
        //  addEdge(2,0);
        //  addEdge(2,3);
        //  addEdge(3,5);
        //  addEdge(5,4);
        //  addEdge(4,3);

         
       
     vector<bool> vis(graph.size(), false);
     vector<int> stack;

//    for(int i=0;i<graph.size() ;i++)
//    {
//        if(! vis[i])
//        {
//             DFS(i , vis , stack);
//        }
//    }
    
        //  addEdge2(1,0);
        //  addEdge2(2,1);
        //  addEdge2(0,2);
        //  addEdge2(0,3);
        //  addEdge2(3,4);

        //  addEdge2(0,1);
        //  addEdge2(1,2);
        //  addEdge2(2,0);
        //  addEdge2(2,3);
        //  addEdge2(3,5);
        //  addEdge2(5,4);
        //  addEdge2(4,3);


        int count=0;
        while( !stack.empty())
        {
             int a=stack.back();
             stack.pop_back();
             for(int i=0;i<graphT.size() ;i++)
              {
                if( vis[a])
                 {
                     DFS2(a , vis );
                     count++;
                 }
              }
  
        }
         cout<<count;
        
}
int main()
{
    solve();
    return 0;
}