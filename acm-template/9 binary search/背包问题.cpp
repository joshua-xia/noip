//https://vjudge.net/problem/51Nod-1257

#include<bits/stdc++.h>
using namespace std;
const int LEN  = 50000+50;
int w[LEN];
int p[LEN];
struct T{
    int ID;
    double v;
}d[LEN];
int gcd(int a,int b)
{
    return b == 0?a:gcd(b,a%b);
}
bool cmp(const T &a,const T &b)
{
    return a.v>b.v;
}
int main(void)
{
    int N,K;
    cin>>N>>K;
    for(int i = 1; i <= N;++i)
        scanf("%d %d",&w[i],&p[i]);
    double l = 0,r = 50000.0*50000;
    while(r-l>1e-6)
    {
        double mid = l + (r-l)/2;
        for(int i = 1; i <= N; ++i)
        {
              d[i].v = p[i] - mid * w[i];//这是本题的关键
              d[i].ID = i;
        }
        sort(d+1,d+N+1,cmp);
        double sum = 0;
        for(int i = 1; i <= K; ++i)
            sum += d[i].v;
        if(sum>0)
            l = mid;//sum大于零, 说明mid的值取得过小
        else
            r = mid;
    }
    long long V = 0,H = 0;
    for(int  i = 1; i <= K; ++i)
    {
          V += w[d[i].ID];
          H += p[d[i].ID];
    }
    int tmp = gcd(V,H);
    cout<<H/tmp<<"/"<<V/tmp<<endl;


    return 0;
}
