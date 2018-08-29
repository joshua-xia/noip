#include<iostream>
#include<cstdio>
#include<cstring>
#include<algorithm>
#include<queue>
#include<string>
using namespace std;
struct rec{ int L, P, S; } a[110];
int n, m;
int f[110][16010], q[16010];

bool operator <(rec a, rec b) {
	return a.S < b.S;
}

int calc(int i, int k) {
	return f[i - 1][k] - a[i].P * k;
}

int main()  {
	cin >> n >> m;
	for (int i = 1; i <= m; i++)
		scanf("%d%d%d", &a[i].L, &a[i].P, &a[i].S);
	sort(a + 1, a + m + 1);
	for (int i = 1; i <= m; i++) {
		// ��ʼ����������
		int l = 1, r = 0;
		// ������ĺ�ѡ���ϲ������
		for (int k = max(0, a[i].S - a[i].L); k <= a[i].S - 1; k++) {
			// �����¾��ߣ�ά����β������
			while (l <= r && calc(i, q[r]) <= calc(i, k)) r--;
			q[++r] = k;
		}
		for (int j = 1; j <= n; j++) {
			// ����ˢʱ��ת��
			f[i][j] = max(f[i - 1][j], f[i][j - 1]);
			// ��ˢ��k+1~j��ľ��ʱ��ת��
			if (j >= a[i].S) {
				// �ų���ͷ���Ϸ�����
				while (l <= r && q[l] < j - a[i].L) l++;
				// ���зǿ�ʱ��ȡ��ͷ����״̬ת��
				if (l <= r) f[i][j] = max(f[i][j], calc(i, q[l]) + a[i].P * j);
			}
		}
	}
	cout << f[m][n] << endl;
}