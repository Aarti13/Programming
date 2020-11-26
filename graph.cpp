#include <vector>
#include <iostream>
#include <list>
#include<queue>

using namespace std;

 class Edge
{
    public:
    int v=0;
    int w=0;

    Edge()
    {}

    Edge(int v,int w)
    {
        this->v=v;
        this->w=w;
    }
};

// class for path which have shortest or heighest weight 
class pair_path
{
    public:
    int w=100000;   //for shortest path w =1000000 and for heighest w=0
    string str="";

    pair_path()
    {}

    pair_path(int w,string str)
    {
        this->w=w;
        this->str=str;
    }
};

class Kruskalpair
{
    public:
    int vtx=-1 , pvtx=-1, wt=-1;

    Kruskalpair() {}

    Kruskalpair(int vtx , int pvtx ,int wt) {
        this-> vtx=vtx;
        this->pvtx=pvtx;
        this->wt= wt;
    }

    bool operator < (const Kruskalpair & o) const
    {
        return this->wt > o.wt;
    }

};

vector <vector <Edge *>> graph(7 ,vector < Edge *> ());
// for 2nd graph  whch shows the spanning tree
vector <vector <Edge *>> graphdij(7 ,vector < Edge *> ());

priority_queue <Kruskalpair> que;   // make a que for kruskal 


void addEdge(int u,int v,int w)
{
    if(u<0 ||v<0 || u>graph.size() || v>graph.size())
       return;
  //  Kruskalpair root(u,v,w);       // initialize the que by adding root for kruskal Graph
  //  que.push(root); 
    graph[u].push_back(new Edge(v,w));
    graph[v].push_back(new Edge(u,w));
}

void display()
{
    for(int i=0;i<graph.size();i++)
    {
        cout<<i<<"->";
        for(int j=0;j<graph[i].size();j++)
        {
            cout<<"("<<graph[i][j]->v<<" , "<<graph[i][j]->w<<")";
        }
        cout<<endl;
    }
}

//for removing an edge
void removeEdge(int u ,int v)
{
    int i,j;
    i=j=-1;

    for(int k=0;k< graph[u].size();k++)
    {
        if(graph[u][k]->v== v)
        {
            i=k;
            break;
        }
    }
    for(int k=0;k< graph[v].size();k++)
    {
        if(graph[v][k]->v== u)
        {
            j=k;
            break;
        }
    }
    graph[u].erase(graph[u].begin()+i);
    graph[v].erase(graph[v].begin()+j);
}

//removing a vertex
void removeVertex(int u)
{
    for(int i=graph[u].size()-1 ;i>= 0;i--)
    {
        removeEdge(graph[u][i]->v,u);
    }
}

// chceking if there is path from src to dest or not if yes what is the path
bool hasPath(int src,int des,vector<bool> & vis,string s)
{
    if(src==des)
        {
            cout<<s+to_string(src);
            return true;
        }
    vis[src]=true; //mark particular vertex through which we traverse 
    bool res=false;

    for(int i=0;i<graph[src].size();i++)
    {
        int nbr=graph[src][i]->v;
        if(!vis[nbr])
            res=res|| hasPath(nbr,des,vis,s+to_string(src)+"->");
    }

    return res;
}

//all the path from src to dest
int  allhasPath(int src,int des,vector<bool> & vis,string s)
{
    if(src==des)
        {
            cout<<s+to_string(src)<<endl;
            return 1;
        }
    vis[src]=true; //mark particular vertex through which we traverse 
    int count=0;

    for(int i=0;i<graph[src].size();i++)
    {
        int nbr=graph[src][i]->v;
        if(!vis[nbr])
            count+= allhasPath(nbr,des,vis,s+to_string(src)+"->");
    }

    vis[src]=false;//unmark vertex
    return count;
}

//all preorderpaths
void  preOrderPath(int src,int wt,vector<bool> & vis,string s)
{
    vis[src]=true; //mark particular vertex through which we traverse 

    cout<<to_string(src)+":  "+s+to_string(src)+" @ "+to_string(wt)<<endl;

    for(int i=0;i<graph[src].size();i++)
    {
        int nbr=graph[src][i]->v;
        int w=graph[src][i]->w;
        if(!vis[nbr])
            preOrderPath(nbr,w+wt,vis,s+to_string(src)+"->");
    }

    vis[src]=false;//unmark vertex
}

//all postorderpaths
void  postOrderPath(int src,int wt,vector<bool> & vis,string s)
{
    vis[src]=true; //mark particular vertex through which we traverse 

    for(int i=0;i<graph[src].size();i++)
    {
        int nbr=graph[src][i]->v;
        int w=graph[src][i]->w;
        if(!vis[nbr])
            preOrderPath(nbr,w+wt,vis,s+to_string(src)+"->");
    }
    cout<<to_string(src)+":  "+s+to_string(src)+" @ "+to_string(wt)<<endl;

    vis[src]=false;//unmark vertex
}

int maxwt=0;
int minwt=10000000;
string ans="";

//maximum wt path
void maxwtPath(int src,int des,int wt,vector<bool> & vis,string s)
{
    if(src==des)
        {
            if(maxwt<wt)
            {
                maxwt=wt;
                ans= s+to_string(src)+" @ "+to_string(maxwt);

            }
        }
    vis[src]=true; //mark particular vertex through which we traverse 

    for(int i=0;i<graph[src].size();i++)
    {
        int nbr=graph[src][i]->v;
        int w=graph[src][i]->w;
        if(!vis[nbr])
             maxwtPath(nbr,des,w+wt,vis,s+to_string(src)+"->");
    }

    vis[src]=false;//unmark vertex
}

//minimum wt path
void minwtPath(int src,int des,int wt,vector<bool> & vis,string s)
{
    if(src==des)
        {
            if(minwt>wt)
            {
                minwt=wt;
                ans= s+to_string(src)+" @ "+to_string(minwt);

            }
        }
    vis[src]=true; //mark particular vertex through which we traverse 

    for(int i=0;i<graph[src].size();i++)
    {
        int nbr=graph[src][i]->v;
        int w=graph[src][i]->w;
        if(!vis[nbr])
             minwtPath(nbr,des,w+wt,vis,s+to_string(src)+"->");
    }

    vis[src]=false;//unmark vertex
}

// Ceil , Floor  Path 
int cd = 1e7;
int fd = -1e7;
string cp ="";
string fp = "";

void Ceil_Floor_Path( int src , int des , int wt , vector<bool> & vis , string s , int cf )
{
    if( src == des )
    {
        if( wt > cf && wt < cd )
        {
            cd = wt ;
            cp= s+to_string(src)+" @ "+to_string(cd);
        }
        if( wt < cf && wt > fd )
        {
            fd = wt ;
            fp= s+to_string(src)+" @ "+to_string(fd);
        }
    }

    vis[src]=true;

    for( int i = 0 ; i<graph[src].size() ; i++){
        int nbr = graph[src][i]->v;
        int w = graph[src][i]->w;
        if(!vis[ nbr ])
            Ceil_Floor_Path( nbr , des , w+wt , vis , s+to_string(src)+"->" , cf);
    }
    vis[src]= false;
}


//minimum wt path for class pair_path.... where we define the wt and string in class
// pair_path *  minwtPath_01(int src,int des,vector<bool> & vis)
// {
//     if(src==des)
//         {
//             pair_path * obj=new pair_path(0,to_string(src)+"->");
//             return obj;
//         }
//     vis[src]=true; //mark particular vertex through which we traverse 

//     for(int i=0;i<graph[src].size();i++)
//     {
//         int nbr=graph[src][i]->v;
//         int w=graph[src][i]->w;
//         if(!vis[nbr])
//             pair_path *recRes= minwtPath_01(nbr,des,vis);
//     }

//     vis[src]=false;//unmark vertex
//     if(recRes->wt+w)
//     return myAns;

// }


// hamiltinoan path ie we should traverse through all vertices only once 
// and find n the min wt path
void hamiltonianPathCycle(int src,int osrc,int count,int wt,vector<bool> & vis,string s)
{
    if(count== graph.size()-1)   //hamiltonian Path
    {
         cout<<endl<<s+to_string(src)+" @ "+to_string(wt);
         for(Edge * e: graph[src])       //hamiltonian Cycle
         {
             if(e->v == osrc)
             cout<<"  CYCLE  ";
         }
         return ; 
    }
    vis[src]=true; //mark particular vertex through which we traverse 

    for(int i=0;i<graph[src].size();i++)
    {
        int nbr=graph[src][i]->v;
        int w=graph[src][i]->w;
        if(!vis[nbr])
             hamiltonianPathCycle(nbr,osrc,count+1,w+wt,vis,s+to_string(src)+"->");
    }

    vis[src]=false;//unmark vertex

}

int counter=0;
// Knight Tour  Path:
void tour(vector<vector <int>> & box ,int r , int c , int csf)
{
    if( r<0 || c<0 || r<=box.size() || c<=box.size() || box[r][c] != 0) return ;

    else if( csf == box.size()* box.size()- 1)
    {
           counter++;
            cout<<"*******" <<counter<< "*******" ;
            box[r][c] = csf;
            for(int i = 0; i < box.size(); i++){
                for(int j = 0; j < box.size(); j++){
                    cout<<box[i][j] <<endl;
                }
                cout<<endl;
            }
            box[r][c] = 0;
            cout<<"*******" <<counter << "*******" ;
            return;
    }
     box[r][c] = csf;
    tour(box,r-2, c-1, csf+1);
    tour(box,r-1, c-2 , csf+1);
    tour(box,r+1, c-2 , csf+1);
    tour(box,r+2, c-1 , csf+1);
    tour(box,r+2, c+1 , csf+1);
    tour(box,r+1, c+2 , csf+1);
    tour(box,r-1, c+2 , csf+1);
    tour(box,r-2, c+1 , csf+1);
     box[r][c] = 0;
}

//*********************************************

//BFS: (Breadth First Search )
bool bfs(int s, int d){
    vector<bool> visited (graph.size(), false);
    queue<int> q;

    q.push(s);
    while(q.size() > 0){
        // grmpa
        int rem = q.front();
        q.pop();

        if(visited[rem] == true){
            continue;
        }
        visited[rem] = true;
        
        if(rem == d){
            return true;
        }

        for(int n = 0; n < graph[rem].size(); n++){

            if(visited[graph[rem][n]->v] == false){
                q.push(graph[rem][n]->v);
            }
        }
    }

    return false;
}

// get Connected components

string gscc(int r, vector<bool>& visited)
{
    string comp = "";
    
    queue<int> q;
    q.push(r);
    while(q.size() > 0){
        int rem = q.front();
        q.pop();

        if(visited[rem] == true){
            continue;
        }
        visited[rem] = true;

        comp += to_string(rem);

        for(int n = 0; n < graph[rem].size(); n++)
        {
            if(visited[graph[rem][n]->v] == false)
            {
                q.push(graph[rem][n]->v);
            }
        }
    }
    return comp;
}

vector<string> gcc(){
    vector<string> comps;
    vector<bool> visited(graph.size(), false);

    for(int v = 0; v < graph.size(); v++){
        if(visited[v] == false)
        {
            string comp = gscc(v, visited);
            comps.push_back(comp);
        }
    }
    return comps;
}

// isconnected  (if gcc_count = 1)
// iscyclic      (if if(vis[s])  then return true)
// tree ( if there exist no cycle )
// forest ( if there is no cycle and gcc > 1 )

//*********************************************

// Bipartiate graph ? true or false:
// Bipartite graph if a graph can be divided into two sets in which there is no edge between vertecies of one set den its is a bipartiate graph for particular k
 
// ********* github isBipartite using numbers ******************

 class BPair    // Bipartite class
    {
        public:
        int vtx= -1;
        string colour="";

        BPair(){}

        BPair(int vtx,string colour)
        {
            this->vtx=vtx;
            this->colour=colour;
        }

    };

 bool BipartiteGraph(int src , vector <string> & vis)
{
     list <BPair> que;
     BPair root(src,"R");
     que.push_back(root);

     bool res=true;

     while(que.size() > 0)
     {
         BPair rpair= que.front();
         que.pop_front();

         if(vis[rpair.vtx] != "" )
         {
             if( ! vis[rpair.vtx] .compare(rpair.colour))
                {
                    cout<<"Not a Bipartitate Graph "<<rpair.vtx<<endl;
                   res= false;
                } 
                else
                {
                    cout<<"Bipartitate Graph "<<rpair.vtx<<endl;
                }
                 continue;  
         }

         vis[rpair.vtx]=rpair.colour;

        for(Edge * e: graph[rpair.vtx])
        {
            if(  vis[e->v] == "")
                {
                    BPair npair(e->v , rpair.colour.compare("R" ) ? "G" : "R");
                    que.push_back(npair);
                }
        }
     }
     return res;
}

//*********************************************
//Dijikstra Algorithm

class Dijikpair
{
    public:
    int vtx=-1, pvtx=-1 , wt=-1, wsf=-1 ;
    string psf="";

    Dijikpair() {}

    Dijikpair(int vtx,int pvtx, int wt ,int wsf, string psf)
    {
        this->vtx=vtx;
        this->pvtx=pvtx;
        this->wt=wt;
        this->wsf=wsf;
        this->psf=psf;
    }

    // bool return value true or false in terms of whose wsf is strong 
    // operator for comparision 
    //const so when it is calculating the value no on ecan change it
    //function is also const
    //& for pass by reference
    bool operator < (const Dijikpair  & o ) const   // for max PQ > opertor is used
    {
        // this for our wsf    o for other wsf 
        // for min PQ
        return this->wsf > o.wsf ;   // mein strong -> weak  -> strong
                                     // when strong it store at neeche priority queue mein
                                     //ie it become min PQ

    // ***************for max PQ  *************************
      //  return this->wsf < o.wsf ;   // mein  weak  -> strong ->waek
                                     // when i am become weak it store at upper priority queue mein
                                     //ie it become max PQ
    }
};


void addEdge2(int u,int v,int w)
{
    if(u<0 ||v<0 || u>graphdij.size() || v>graphdij.size())
       return;
    graphdij[u].push_back(new Edge(v,w));
    graphdij[v].push_back(new Edge(u,w));
}

void display2()
{
    for(int i=0;i<graphdij.size();i++)
    {
        cout<<i<<"->";
        for(int j=0;j<graphdij[i].size();j++)
        {
            cout<<"("<<graphdij[i][j]->v<<" , "<<graphdij[i][j]->w<<")";
        }
        cout<<endl;
    }
}


void DijikstraGraph(int src ,int des, vector <bool> & vis)
{
    priority_queue <Dijikpair> que;   // make a que for dijikpair 
    Dijikpair root(src,-1,0,0,to_string(src)+"");       // initialize the que by adding root
    que.push(root);                                      

    while(que.size()>0)
    {
        Dijikpair rpair= que.top();   // extract top elemnt in rpair
        que.pop();                    // remove the element

        if(vis[rpair.vtx])            // if vtx is already visited
        continue;

        if(vis[rpair.vtx] != -1)        // for making an spanning tree graph
        addEdge2(rpair.vtx , rpair.pvtx , rpair.wsf);
 
        if(rpair.vtx == des)                 // if we reach at dest
        cout<<"MINIMUM wt Path:   "<< rpair.psf<<" @ wt  "<< rpair.wsf<<endl;

        vis[rpair.vtx]=true;           //mark that vtx 

        for(Edge * e: graph[rpair.vtx])
        {
            if( ! vis[e->v] )
            {
                Dijikpair npair(e->v , rpair.vtx , e->w , rpair.wsf + e->w , rpair.psf+" ->"+ to_string(e->v));
                que.push(npair);
            }
        }
    }
     display2();
}

//*********************************************
//Prims Algorithm 

class Primpair
{
    public:
    int vtx=-1, pvtx=-1 , wt=-1, wsf=-1 ;
    string psf="";

    Primpair() {}

    Primpair(int vtx,int pvtx, int wt ,int wsf, string psf)
    {
        this->vtx=vtx;
        this->pvtx=pvtx;
        this->wt=wt;
        this->wsf=wsf;
        this->psf=psf;
    }

    bool operator < (const Primpair  & o ) const   
    {
        return this->wt > o.wt ;  
    }
};

void PrimGraph(int src ,int des, vector <bool> & vis)
{
    priority_queue <Primpair> que;   // make a que for dijikpair 
    Primpair root(src,-1,0,0,to_string(src)+"");       // initialize the que by adding root
    que.push(root);                                      

    while(que.size()>0)
    {
        Primpair rpair= que.top();   // extract top elemnt in rpair
        que.pop();                    // remove the element

        if(vis[rpair.vtx])            // if vtx is already visited   ie cycle
        continue;

        if(vis[rpair.vtx] != -1)        // for making an spanning tree graph
        addEdge2(rpair.vtx , rpair.pvtx , rpair.wt);
 
        if(rpair.vtx == des)                 // if we reach at dest
        cout<<"MINIMUM wt Path:   "<< rpair.psf<<" @ wt  "<< rpair.wsf<<endl;

        vis[rpair.vtx]=true;           //mark that vtx 

        for(Edge * e: graph[rpair.vtx])
        {
            if( ! vis[e->v] )
            {
                Primpair npair(e->v , rpair.vtx , e->w , rpair.wsf + e->w , rpair.psf+" ->"+ to_string(e->v));
                que.push(npair);
            }
        }
    }
     display2();
}

//*********************************************
// //Kruskal Algorithm

 vector<int> par(graph.size());
 vector<int> size(graph.size(),1);

int Find(int  vtx)           //find function to find the par+ent of the vertex
{
    if(par[vtx] != vtx)
      par[vtx]= Find(par[vtx]);      // perform recusion to find the super parent it also perform compression

     return par[vtx];
} 

void Union(int v1 , int v2 )        
{
    if( size[v1 ] <= size[ v2 ] )
    {
         par[v1] = v2 ;
         size[v2] += size[v1] ;
    }
    else
    {
        par[v2] = v1 ;
         size[v1] += size[v2] ;
    }
}

void KruskalGraph()
{
    for(int i=0 ; i< graph.size() ;i++)
    {
        par[i]=i;
    }

    Kpair root();       // initialize the que by adding root
    que.push(root);  

    while(que.size()>0)
    {
        Kruskalpair rpair= que.top();
        que.pop();

        int a=Find(rpair.vtx);
        int b=Find(rpair.pvtx);
        if(a != b)
        {
            addEdge2(rpair.vtx, rpair.pvtx , rpair.wt );
            Union(a,b);
        }
    }
    display2();
    
}

//*********************************************
//  Articulation Point....  pt if we remove that the graph divided into more components

    int time=0;
    int root=0;  
    vector<int> disT(graph.size(), -1);
    vector<int> lowT(graph.size());
    vector<bool> point(graph.size(),false);

    void ArticulationPoints(int vtx , int parent )
    {
        disT[vtx]=lowT[vtx]=time++;

        for(Edge * e: graph[vtx])
        {
            if(disT[e->v] == -1){
                par[e->v]=vtx;

            if(parent == -1)
                root++;

            ArticulationPoints(e->v, vtx);

            if( lowT[vtx] <= lowT[e->v] )
                point[vtx]=true;
            lowT[vtx]= min(lowT[vtx] , lowT[e->v] );
            }

            else if(par[vtx] != e->v )
                lowT[vtx]= min(lowT[vtx] , disT[e->v] );
        }
    }

//*********************************************

void solve()
{
        //************** graph1 ***************
        // addEdge( 0,1,10);
        // addEdge( 0,2,10);
        // addEdge( 2,3,40);
        // addEdge( 1,3,10);
        // addEdge( 2,4,2);
        // addEdge( 4,5,2);
        // addEdge( 5,6,8);
        // addEdge( 4,6,3);
      
        //display();
        //removeEdge(3,2);
        //removeVertex(2);
        //display();
      //  vector<bool> visited(graph.size(),false);
       // bool t=hasPath(0,6,visited,"");
        //cout<<t;
    //    int a= allhasPath(0,6,visited,"");
    //    cout<<a;
       //preOrderPath(0,0,visited,"");
       //postOrderPath(0,0,visited,"");
       //maxwtPath(0,6,0,visited,"");
    //    minwtPath(0,6,0,visited,"");
    //    cout<<ans;

    //    Ceil_Floor_Path(0,6,0,visited,"", 30 );
    //    cout<<cp<<endl;
    //    cout<<fp;

       //hamiltonianPathCycle(0,0,0,0,visited,"");

        // knight Tour
        // vector<vector<int>> chess (5 ,vector<int> (5, 0) );
        //  tour(chess, 1, 3, 1);

        //  vector<string> vis(graph.size(),"");

        //  for(int i=0;i<vis.size() ;i++)
        //  {
        //      if(! vis[i].compare(""))
        //        cout<<(boolalpha)<<BipartiteGraph(i,vis)<<endl;
        //  }
       

        //**************** graph 2 **************

        addEdge( 0,1,5);
        addEdge( 0,2,1);
        addEdge( 1,3,4);
        addEdge( 1,4,2);
        addEdge( 1,2,8);
        addEdge( 2,4,7);
        addEdge( 4,5,1);
        addEdge( 3,5,3);
        addEdge( 3,4,6);
    //     DijikstraGraph(0,5,visited);
    //    PrimGraph(0,6,visited);
       KruskalGraph();
        
    //     ArticulationPoints(0 , -1);
    //    ( point[0] = root >1) ? true: false;

    //     cout<<"Articulation points" ;
    //    for(int i =0 ;i<graph.size(); i++)
    //    {
    //        if(point[i])
    //        cout<<" " <<i<<" ,";
    //    }
}
int main()
{
    solve();
    return 0;
}