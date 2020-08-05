//26 august Sumit Sir 

#include<iostream>
using namespace std;

class Node
 {
    public:
    int data;
    Node* next;
    
    Node()
    {
        this->data=0;
        this->next=NULL;
    }  
    Node(int data ,Node* next)
    {
        this->data=data;
        this->next=next;
    }     
  };

//add at first in linked list
void addFirst( Node *& head, Node *& tail,int d)
{
    if(head==NULL)
    {
        head=tail= new Node(d,NULL);
    }
    else
    {
        Node * nn= new Node(d,NULL);
        nn->next=head;
        head=nn;
    }
}

void addLast( Node *& head, Node *& tail,int d)
{
    if(head==NULL)
    {
        head=tail= new Node(d,NULL);
    }
    else
    {
        Node * nn= new Node(d,NULL);
        tail->next=nn;
        tail=nn;
    }
}


Node* getNodeAt(Node * head,int idx)
{
if(head==NULL)
    return NULL;
    int j=0;
     for( Node* i=head;i !=NULL ;i=i->next)
    {
        if(j== idx)
        {
           return i;
        }
        j++;
        
    }
    return NULL;
}

int getSize(Node *head)
{
    int s=0;
    for( Node* i=head;i !=NULL ;i=i->next)
    {
       s++;  
    }
    return s;
}

int getFirst(Node * head)
{
    return head->data;
}
int getLast(Node * tail)
{
    return tail->data;
}
int getAt(Node * head,int idx)
{
    if(head==NULL)
      return 0;
    int j=0;
     for( Node* i=head;i !=NULL ;i=i->next)
    {
        if(j== idx)
        {
            return i->data;
        }
        j++;
        
    }
    return 0;
}


//add at particular position
void addAt(Node *& head,Node *& tail,int idx,int data)
{
    if(head==NULL)
     { addFirst(head,tail,data); }

    else if(head->next==NULL)
    {
        addLast(head,tail,data);
    }

    else
    {
        Node * nn=getNodeAt(head,idx-1);
        nn->next=new Node(data,nn->next);   
    }
   
}

void removeFirst(Node *& head,Node *& tail)
{
    if(head==NULL){}

    else if(head->next==NULL)
    {
        Node * temp=NULL;
        temp=head;
        head=tail=NULL;
        delete temp;           //it would leak the memory
    }

    else
    {
       Node * temp=head;
       head=head->next;
       delete temp;  
    }
   
}

void removeLast(Node *& head,Node *& tail)
{
    if(head==NULL)
    return ;

    else if(head->next==NULL)
    {
        Node * temp=head;
        head=tail=NULL;
        delete temp;           //it would leak the memory
    }

    else
    {
        int sz=getSize(head);
        Node * nn=getNodeAt(head,sz-2);
       nn->next=NULL;
       delete tail;
       tail=nn;  
    }
   
}

void display( Node * head)
{
    for( Node* i=head;i !=NULL ;i=i->next)
    {
        cout<<i->data<<"   ";
    }
    cout<<endl;
}

//using iteration *********************
//recursion using data property ie reverse data using iterartion  n^2
void reverseDI(Node* head,Node * tail)
{
    int si=0, li=getSize(head)-1;

    while(si<li)
    {
        Node* left=getNodeAt(head,si);
        Node* right=getNodeAt(head,li);

        int temp=left->data;
        left->data=right->data;
        right->data=temp;

        si++;
        li--;
    }
    display(head);
}

//recursion using pointer property ie reverse data pointer using iterartion  n
void reversePI(Node*& head,Node *& tail)
{
    Node* curr =head;
    Node *prev=NULL;

    while(curr!=NULL)
    {
    Node*  next=curr->next;
    curr->next=prev;
    prev=curr;
    curr=next;
    }
    Node * temp=head;
    head=tail;
    tail=temp;

    display(head);
}

//using recursion *****************************
//reverse the array using recursion without changing the data or pointer property  
void recDisplay(Node * head)
{
    if(head == NULL)
        return;

    recDisplay(head->next);
    cout<<head->data<<"   ";
}

//reverse the array data using recursion without changing the pointer property
void recDR(Node *& left,Node * right , int floor,int & size)
{
    if(right == NULL)
       { return;}

    size++;
    recDR(left,right->next,floor+1,size);

    if(floor >= size/2)
    {
    int temp=left->data;
    left->data=right->data;
    right->data=temp;

    left=left->next;   
    }

}

//madam is palindrome
bool isPalindrome(Node *& left,Node* right)
{
      
    if(right == NULL)
       { return true ;}

    bool res=isPalindrome(left,right->next);

    int t1=left->data;
    int t2=right->data;
    res=res && t1==t2;
    left=left->next;   
   return res;
}

// a->b->c->d->e->f = a-f-b-e-d-c
// a-b-c-d-e = a-e-b-d-c
void fold(Node*& left,Node * right , int floor,int & size,Node *&tail)
{
     if(right == NULL)
       { return;}

    size++;
    fold(left,right->next,floor+1,size,tail);

    if(floor > size/2)
    {
    Node* temp=left->next;
    left->next=right;
    right->next=temp;

    left=temp;   
    }
    else if( size/2 == floor )
    {
        tail=right;
        tail->next=NULL;
    }

}


//31 August
//find the mid element from the list ie 10 20 30-40-50-60   = 30
// 10-20-30-40-50-60-70  = 40

int mid(Node* head)
{
    Node* slow=head;
    Node* fast=head;

    while(fast->next!=NULL && fast->next->next!=NULL)
    {
        slow=slow->next;
        fast=fast->next->next;
    }
    return slow->data;
}

//Find the kth element from the ll 

int KthFromLast(Node* head,int k)
{
    
}

//1 sept

// merge two sorted LL 
void mergeSL(Node* h1,Node* h2,Node*& oh,Node*& ot)
{
    Node* t1=h1;
    Node* t2=h2;

    while(t1!= NULL && t2!=NULL)
    {
        if(t1->data > t2->data )
        {
            addLast(oh,ot,t2->data);
            t2=t2->next;
        }
        else
        {
            addLast(oh,ot,t1->data);
            t1=t1->next;
        }
    }
    while(t1!= NULL )
    {
            addLast(oh,ot,t1->data);
            t1=t1->next;
    }
    while(t2!= NULL )
    {
            addLast(oh,ot,t2->data);
            t2=t2->next;
    }
}

//find mid node each time at the time of merge sort
Node* midNode(Node* head ,Node* tail)
{
    Node* slow=head;
    Node* fast=head;

    while(fast !=tail && fast->next !=tail )
    {
        slow=slow->next;
        fast=fast->next->next;
    }
    return slow;
}

//mergesort
void mergeSort(Node*& head,Node*& tail)
{
    if(head == tail)
    {
        Node* nn=new Node(head->data,NULL);
        head=tail=nn;
        return;
    }

    Node* mid=midNode(head,tail);

    Node* lh=head;
    Node* lt=mid;
    Node* rh=mid->next;
    Node* rt=tail;

    mergeSort(lh,lt);
    mergeSort(rh,rt);

    Node* fh=NULL;
    Node* ft=NULL;    
    mergeSL(lh,rh,fh,ft);

    head=fh;
    tail=ft;
}

//InPlace Merge two sorted list
void INP_mergeSL(Node* lh,Node* lt,Node* rh,Node*rt,Node*& oh,Node*& ot)
{
    Node * t1=lh;
    Node* t2=rh;

    if(t1!=NULL && t2!=NULL)
    {
        Node* rm=NULL;
        if(t1->data < t2->data)
        {
            rm=t1;
            t1=t1->next;
            rm->next=NULL;
        }
        else
        {
            rm=t2;
            t2=t2->next;
            rm->next=NULL;
        }

        if(oh==NULL)
         { oh=ot=rm;}
        else
        {
            ot->next=rm;
            ot=rm;
        }
    }

    if(t1!=NULL)
    {
        ot->next=t1;
        ot=lt;
    }
    else
    {
        ot->next=t2;
        ot=rt;
    }
}

// InPlace MergeSort
void INP_MergeSort(Node* & head,Node* & tail)
{
    if(head == tail)
    {
        return;
    }

    Node* mid=midNode(head,tail);

    Node* lh=head;
    Node* lt=mid;
    Node* rh=mid->next;
    Node* rt=tail;

    mid->next=NULL;

    INP_MergeSort(lh,lt);
    INP_MergeSort(rh,rt);

    Node* fh=NULL;
    Node* ft=NULL;    
    INP_mergeSL(lh,lt,rh,rt,fh,ft);

    head=fh;
    tail=ft;
    
}


//**************main***************

int main()
{
    Node* head=NULL;
    Node* tail=NULL;
    
    // addAt(head,tail,0,100);
    // removeFirst(head,tail);
    // addFirst(head,tail,70);
    // addLast(head,tail,10);
    // removeLast(head,tail);
    // addLast(head,tail,20);
    // addLast(head,tail,30);
    // addLast(head,tail,40);
    // addLast(head,tail,50);
    // addFirst(head,tail,60);
    // addAt(head,tail,4,1000);
    // addAt(head,tail,3,200);
    // removeFirst(head,tail);
    // // cout<<getAt(head,3)<<endl;
    // cout<<getFirst(head)<<endl;
    // cout<<getLast(tail)<<endl;
    // cout<<getNodeAt(head,3)<<endl;
   //  cout<<getSize(head);
    // display(head);
  //  recDisplay(head);
   //reversePI(head,tail);
//     Node* left=head;
//     Node* right=head;
     int size=0;
     int floor=0;
//    // recDR(left,right,floor,size);
   

//    addLast(head,tail,10);
//    addLast(head,tail,20);
//    addLast(head,tail,30);
//    addLast(head,tail,40);
//    addLast(head,tail,50);
//     addLast(head,tail,60);
//    display(head);

//    //find mid elelment
//     cout<<mid(head)<<endl;

//    Node* left=head;
//    Node* right=head;
  // cout<<boolalpha<<isPalindrome(left,right);
  //  fold(left,right,floor,size,tail);

    //merging two sorted LL
   Node* h1=NULL;
   Node* t1=NULL;
  
   addLast(h1,t1,11);
   addLast(h1,t1,12);
   addLast(h1,t1,13);
   addLast(h1,t1,14);
   addLast(h1,t1,15);
   addLast(h1,t1,19);
   display(h1);
   
   Node* h2=NULL;
   Node* t2=NULL; 
   addLast(h2,t2,1);
   addLast(h2,t2,2);
   addLast(h2,t2,3);
   addLast(h2,t2,4);
   addLast(h2,t2,5);
   addLast(h2,t2,9);
   display(h2);
    
    Node* oh=NULL;
    Node* ot=NULL; 

   //mergeSL(head,h1,h2,t2);
  //mergeSort(h2,t2);

    INP_mergeSL(h2,t2,h1,t1,oh,ot);
  //INP_MergeSort(h1,t1);
    display(oh);
    return 0;
}