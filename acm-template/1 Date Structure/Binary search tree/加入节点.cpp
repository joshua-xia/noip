
const int maxn = 10000;
int l[manx],r[maxn],v[maxn];
int dfs(int node,int vv)
{
    if(vv < v[node])
    {
        if(l[node] == 0)
        {
            l[node] = sz;
            v[sz] = vv;
            l[sz] = r[sz] = 0;
            return sz++;
        }
        else
            return dfs(l[node],vv);
    }
    else
    {
        if(r[node] == 0)
        {
            r[node] = sz;
            v[sz] = vv;
            l[sz] = r[sz] =0;
            return sz++;
        }
        else
            return dfs(r[node],vv);
    }
}
