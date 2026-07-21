import java.util.*;
class Node{
int val;
Node next;
Node(int val,Node next){
this.val=val;
this.next=next;}}
class NodeLL{
public static void main(String args[])
{
Scanner sc=new Scanner(System.in);
Node e=new Node(10,null);
Node d=new Node(100,e);
Node c=new Node(1000,d);
Node b=new Node(10000,c);
Node a=new Node(1000000,b);

Node tail=e;
Node head=a;
tail=addtail(tail,500);
head=addhead(head,654);
head=delhead(head);
head=insert(head,2,50009);
display(head);
}

public static void display(Node head){
Node temp=head;
while(temp!=null){
System.out.print(temp.val+" ");
temp=temp.next;}}

public static Node addtail(Node tail,int val){
Node temp=new Node(val,null);

if(tail==null){
tail=temp;}
else{
tail.next=temp;
tail=temp;}
return tail;}

public static Node addhead(Node head,int val){
Node temp=new Node(val,head);
head=temp;
return head;}

public static Node delhead(Node head){
if(head==null){
return null;}
head=head.next;
return head;}

public static Node insert(Node head,int idx,int val){
if(idx<0){
System.out.print("invalid");
return head;
}
if(idx==0){
return addhead(head,val);}
Node temp=head;
for(int i=0;i<idx-1;i++){
temp=temp.next;}
Node t=new Node(val,null);
t.next=temp.next;
temp.next=t;
return head;

}}
