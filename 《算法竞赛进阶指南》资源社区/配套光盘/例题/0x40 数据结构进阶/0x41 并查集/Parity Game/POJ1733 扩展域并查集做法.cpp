// POJ1733 ��չ�򲢲鼯����
#include<iostream>
#include<cstdio>
#include<cstring>
#include<algorithm>
#include<queue>
using namespace std;
struct { int l, r, ans; } query[10010];
int a[20010], fa[40010], n, m, t;
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
	return fa[x] = get(fa[x]);
}
int main() {
	read_discrete();
	for (int i = 1; i <= 2 * n; i++) fa[i] = i;
	for (int i = 1; i <= m; i++) {
		// ���l-1��r��ɢ��֮���ֵ
		int x = lower_bound(a + 1, a + n + 1, query[i].l - 1) - a;
		int y = lower_bound(a + 1, a + n + 1, query[i].r) - a;
		int x_odd = x, x_even = x + n;
		int y_odd = y, y_even = y + n;
		if (query[i].ans == 0) { // �ش���ż����ͬ
			if (get(x_odd) == get(y_even)) { // ����֪���ì��
				cout << i - 1 << endl;
				return 0;
			}
			fa[get(x_odd)] = get(y_odd); // �ϲ�
			fa[get(x_even)] = get(y_even);
		}
		else { // �ش���ż�Բ�ͬ
			if (get(x_odd) == get(y_odd)) { // ����֪���ì��
				cout << i - 1 << endl;
				return 0;
			}
			fa[get(x_odd)] = get(y_even); // �ϲ�
			fa[get(x_even)] = get(y_odd);
		}
	}
	cout << m << endl; // û��ì��
}