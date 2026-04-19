public static int subset(int arr[],int range){
range=0;
int n=arr.length;
int min=Integer.MAX_VALUE;
for(int i=0;i<n;i++){
range=range+arr[i];}
boolean t[][]=new boolean[n+1][range+1];
for(int i=0;i<n+1;i++){
t[i][0]=true;}
for(int j=1;j<range+1;j++){
t[0][j]=false;}
for(int i=1;i<n+1;i++){
for(int j=1;j<range+1;j++){
if(arr[i-1]<=j){
t[i][j]=t[i-1][j-arr[i-1]] || t[i-1][j];
}
else{
t[i][j]=t[i-1][j];}
}}
for(int i=0;i<range/2;i++)
{
if(t[n][i]){
int diff=range-2*i;

 min=Math.min(min,diff);

}}
return min;}
