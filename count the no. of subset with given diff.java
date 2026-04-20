public static int subset(int arr[],int diff){
int n=arr.length;
int sum=0;
for(int i=0;i<n;i++)
{ 
sum=sum+arr[i];
}
int target=(sum+diff)/2;
int t[][]=new int[n+1][target+1];
for(int i=0;i<n+1;i++){
t[i][0]=1;}
for(int j=1;j<target+1;j++){
t[0][j]=0;}
for(int i=1;i<n+1;i++){
for(int j=1;j<target+1;j++){
if(arr[i-1]<=j){
t[i][j]=t[i-1][j-arr[i-1]] + t[i-1][j];
}
else{
t[i][j]=t[i-1][j];}
}}
return t[n][target];}
