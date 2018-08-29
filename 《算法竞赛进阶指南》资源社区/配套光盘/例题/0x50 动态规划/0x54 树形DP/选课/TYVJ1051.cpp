#include<iostream>
#include<cstdio>
#include<cstring>
#include<algorithm>
#include<vector>
using namespace std;
vector<int> son[310];
int f[310][310], s[310], n, m;

void dp(int x) {
	f[x][0] = 0;
	for (int i = 0; i < son[x].size(); i++) { // ѭ���ӽڵ㣨��Ʒ��
		int y = son[x][i];
		dp(y);
		for (int t = m; t >= 0; t--) // ����ѭ����ǰѡ������������ǰ���������
			for (int j = t; j >= 0; j--) // ѭ�����������ϵ�ѡ��������������Ʒ��
				if (t - j >= 0)
                    f[x][t] = max(f[x][t], f[x][t-j] + f[y][j]);
	}
	if (x != 0) // x��Ϊ0ʱ��ѡ��x������Ҫռ��1�ſΣ��������Ӧѧ��
		for(int t = m; t > 0; t--)
			f[x][t] = f[x][t-1] + s[x];
}

int main()
{
	cin >> n >> m;
	for(int i = 1; i <= n; i++)
	{
		int x;
		cin >> x >> s[i];
		son[x].push_back(i);
		
	}
	memset(f, 0xcf, sizeof(f)); // -INF
	dp(0);
	cout << f[0][m] << endl;
}