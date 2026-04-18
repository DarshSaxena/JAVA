public static int Knapsack(int wt[],int val[],int W,int n)
{
if(n==0 || W==0){
return 0;}
if(wt[n-1]<=W){
return Math.max(val[n-1]+Knapsack(wt,val,W-wt[n-1],n-1),Knapsack(wt,val,W,n-1));}
if(wt[n-1]>W){
return Knapsack(wt,val,W,n-1);
}
} 

public static int Knapsack(int wt[],int val[],int W,int n)
{
int t[][]=new int[n+1][W+1];
for(int i=0;i<n+1;i++)
{
for(int j=0;j<W+1;j++)
{
if(i==0 || j==0){
t[i][j]=0;
}}}
for(int i=1;i<n+1;i++)
{
for(int j=1;j<W+1;j++)
{
if(wt[i-1]<=j)
{
t[i][j]=Math.max(val[i-1]+t[i-1][j-wt[i-1]],t[i-1][j]);
}
else{
t[i][j]=t[i-1][j];
}}}
return t[n][W];}
