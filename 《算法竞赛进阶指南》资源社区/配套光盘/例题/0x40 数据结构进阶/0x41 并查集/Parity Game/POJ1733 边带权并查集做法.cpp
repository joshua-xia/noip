#include<iostream>
#include<cstdio>
#include<cstring>
#include<algorithm>
#include<queue>
using namespace std;
struct { int l, r, ans; } query[10010];
int a[20010], fa[20010], d[20010], n, m, t;
void read_discrete() { // ���롢��ɢ��
	cin >> n >> m;
	for (int i = 1; i <= m; i++) {
		char str[5];
		scanf("%d%d%s", &query[i].l, &query[i].r, str);
		query[i].ans = (str[0] == 'o' ? 1 : 0);
		a[++t] = query[i].l - 1;
		a[++t] = query[i].r;
	}
	sort(a + 1, a + t + 1);
	n = unique(a + 1, a + t + 1) - a - 1;
}
int get(int x) {
	if (x == fa[x]) return x;
	int root = get(fa[x]);
	d[x] ^= d[fa[x]];
	return fa[x] = root;
}
int main() {
	read_discrete();
	for (int i = 1; i <= n; i++) fa[i] = i;
	for (int i = 1; i <= m; i++) {
		// ���l-1��r��ɢ��֮���ֵ
		int x = lower_bound(a + 1, a + n + 1, query[i].l - 1) - a;
		int y = lower_bound(a + 1, a + n + 1, query[i].r) - a;
		// ִ��get�������õ�������������·��ѹ��
		int p = get(x), q = get(y);
		if (p == q) { // �Ѿ���ͬһ������
			if ((d[x] ^ d[y]) != query[i].ans) { // ì�ܣ����
				cout << i - 1 << endl;
				return 0;
			}
		}
		else { // ����ͬһ���ϣ��ϲ�
			fa[p] = q;
			d[p] = d[x] ^ d[y] ^ query[i].ans;
		}
	}
	cout << m << endl; // û��ì��
}