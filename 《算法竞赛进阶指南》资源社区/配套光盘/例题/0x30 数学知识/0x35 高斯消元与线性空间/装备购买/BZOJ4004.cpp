#include <iostream>
#include <cstdio>
#include <cstring>
#include <algorithm>
#include <cmath>
using namespace std;
long double a[510][510], eps = 1e-8;
int c[510], n, m, dim, ans;

int main() {
	cin >> n >> m;
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= m; j++) {
			double temp; scanf("%lf", &temp); a[i][j] = temp;
		}
	for (int i = 1; i <= n; i++) {
		double temp; scanf("%lf", &temp); c[i] = temp;
	} 
	int dim = 0;
	// ��ÿ��δ֪��xi����һ����Ԫ 
	for (int i = 1; i <= m; i++) {
		// �ҵ�xiϵ����Ϊ0���۸���͵�һ��
		int now = 0;
		for (int j = dim + 1; j <= n; j++) {
			if (fabs(a[j][i]) > eps && (now == 0 || c[j] < c[now]))
				now = j;
		}
		// xi������Ԫ 
		if (now == 0) continue;
		// �����������һ������ 
		dim++;
		ans += c[now];
		for (int j = 1; j <= m; j++)
			swap(a[now][j], a[dim][j]);
		swap(c[now], c[dim]);
		// ��ȥ�������е�i�е�ֵ 
		for (int j = 1; j <= n; j++)
			if (dim != j && fabs(a[j][i]) > eps) {
				long double rate = a[j][i] / a[dim][i];
				for (int k = i; k <= m; k++)
					a[j][k] -= a[dim][k] * rate;
			}
	}
	cout << dim << ' ' << ans << endl;
}