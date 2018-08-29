// 注意修改maxn 的值，要不然容易T

#include<iostream>
#include<cmath>
#include<cstring>
#define mem(ar,num) memset(ar,num,sizeof(ar))
#define me(ar) memset(ar,0,sizeof(ar))
#define lowbit(x) (x&(-x))
using namespace std;
typedef long long LL;
typedef unsigned long long ULL;
const int    prime = 999983;
const int    INF = 0x7FFFFFFF;
const LL     INFF =0x7FFFFFFFFFFFFFFF;
const double pi = acos(-1.0);
const double inf = 1e18;
const double eps = 1e-6;
const LL     mod = 1e4;
int dr[2][4] = {1,-1,0,0,0,0,-1,1};

const int maxn = 100;
int n;
struct Matrix{
	Matrix(){ init();};
	long long a[maxn][maxn];
	void init(void)
	{
		memset(a,0,sizeof(a));
	 } 
};
void print(const Matrix &a)
{
	for(int i = 1;i <= n; ++i,cout<<endl)
	 for(int j= 1;j <=n; ++j)
	    cout<<a.a[i][j]<<" ";
}
Matrix operator*(Matrix a,Matrix b)
{
	Matrix c;
	c.init();
	for(int i = 1;i <= n; ++i)
	{
		for(int j = 1;j <= n; ++j)
		{
			for(int k = 1;k <= n; ++k)
			{
				c.a[i][j] += a.a[i][k] * b.a[k][j];
				c.a[i][j] %= mod;
			}
		}
	}
//	print(c);
	return c;
}
Matrix qpow(Matrix a,long long b,long long mod)
{
	Matrix ans;
	for(int i = 1;i <= n;++i)
	   ans.a[i][i] = 1;
//	for(int j = )
    while(b > 0)
    {
    	if(b & 1)
    	  ans = ans*a;
    	a = a*a;
    	b >>= 1;
	}
	return ans;
}

int main(void)
{
//   cout<<"Strive for excellence"<<endl;
   
   n = 2;
   int t;
   for(int t = 1;t <= 100; ++t)
   while(cin>>t&& t >= 0)
   {
   	 if(t == 0)
   	   {
   	   	cout<<0<<endl;
   	   	continue;
		  }
   	 Matrix M;
   	
     
	     M.a[1][1] = M.a[1][2] =  M.a[2][1] = 1;
//	 print(M);
	 M = qpow(M,t,10000);
	 //print(M);
	 printf("%d\n",M.a[1][2]);
   }
   return 0;
}
