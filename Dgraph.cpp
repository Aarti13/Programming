                                  //  ************** DIRECTED GRAPH ************

#include<iostream>
#include<list>
#include<vector>
#include<queue>

using namespace std;

class Edge
{   
    public:
    int v=0;
    int w=0;

    Edge() {}

    Edge(int v, int w)
    {
        this->v=v;
        this->w=w;
    }
};

vector <vector <Edge *>> graph (7,vector< Edge *> ());
vector<int> size(graph.size(), 0);     // for all incoming edge in khans algorithm

// adding edge
void addEdge(int u,int v ,int w)
{
    if(u< 0 || v<0 || u>graph.size() || v>graph.size() )
    return;
    graph[u].push_back(new Edge(v, w));
    size[v]++;   // for incoming edges   khans algorithm
}

//display an graph G(v,e);
void display()
{
    for(int i=0;i<graph.size() ; i++)
    {
        cout<<i<<" ->  ";
        for(int j=0 ;j< graph[i].size() ; j++)
            cout<<"( "<<graph[i][j]->v<<" , "<<graph[i][j]->w<<" )";
        cout<< endl;
    }
}

//**************************   Tpological sort   ************************
// topological sort where in directed a cyclic graph (u,v) u always come prior to v
// DFS for traversal all the vertices
bool DFS(int src, vector<bool> & vis, vector<int> & stack , vector<bool> & cycle )
{
    vis[src]=true;
    cycle[src]=true;
    bool res = false;

    for(Edge *e: graph[src])
    {
        if(! vis[e->v])
          res = res || DFS(e->v, vis, stack , cycle);
        else if( cycle[e->v])
        return true;
    }

    stack.push_back(src);
    cycle[src]=false;
    return res;
}

// topological sort
bool TopologicalSort()
{
    vector<bool> vis(graph.size(), false);
    vector<bool> cycle(graph.size(), false);     // for identifying there is cycle or not in th graph
    vector<int> stack;
    bool res=false;

    for(int i=0 ;i<graph.size() ;i++)
    {
        if( !vis[i])
        {
             if(  DFS(i, vis, stack , cycle) )
             res= true ;       //  cycle present
        }
    }
    // for printing reverse stack 
      for(int i=stack.size() -1   ; i>=0 && ! res ;i--)
         cout<<stack[i] <<" ";
     return res;       // there is no cycle exist in topological graph
}

// topological sort  using khans algorithm 
void Khan_Topological_Sort()
{
    queue <int> que;   // make a queue
    vector<int> ans;   // for storing ans
    bool res= false;   // for identifying if there is any cycle present in the graph
    int count=0;       // cycle counter

    for(int i=0 ;i<graph.size() ;i++)
    {
        if( size[i] == 0 )    // if there is no incoming edge then add it to the queue
            que.push(i); 
    }
    
    while(que.size()>0)   // traverse the queue until it become empty
    {
        int u= que.front();    // extract the first element
        que.pop();             // pop it ie remove it from queue
        ans.push_back(u);      // push it at the back of a vector ie ans

        for(int i=0 ;i<graph.size() ;i++)
            {
                if( -- size[i] == 0 )       // dec the incoming edge of nbr vertices of a vtx v 
                     que.push(i);                // if it become 0 then add it to the queue
             
            }
            count++;                    //cycle counter
     }
     if(count == graph.size())
     {
        res=true;
        cout<<"No Cycle Present:"<<endl <<"Topological Order  : ";
     }
     else
     cout<<"Cycle Present :";

     for(int i=0  ; i<size.size() && res  ;i++)   // print the ans
         cout<<ans[i] <<" ";
}

void solve()
{
    //************** graph1 ***************
        addEdge( 0,3,1);
        addEdge( 0,2,1);
        addEdge( 3,4,1);
        addEdge( 4,6,1);
        addEdge( 1,2,1);
        addEdge( 1,5,1);
        addEdge( 5,6,1);

        // addEdge( 0,1,1);
        // addEdge( 1,2,1);
        // addEdge( 2,3,1);
        // addEdge( 3,0,1);

        //display();
       //cout<<(boolalpha)<< TopologicalSort();
       Khan_Topological_Sort();
}

int main()
{
    solve();
    return 0;
}