#include<iostream>
#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;
int v[1010], prime[1010], phi[1010], sum[1010], m, t, n;

void euler(int n) {
	memset(v, 0, sizeof(v)); // ��С������
	m = 0; // ��������
	for (int i = 2; i <= n; i++) {
		if (v[i] == 0) { // i������
			v[i] = i, prime[++m] = i;
			phi[i] = i - 1;
		}
		// ����ǰ����i����һ��������
		for (int j = 1; j <= m; j++) {
			// i�б�prime[j]��С�������ӣ����߳���n�ķ�Χ
			if (prime[j] > v[i] || prime[j] > n / i) break;
			// prime[j]�Ǻ���i*prime[j]����С������
			v[i*prime[j]] = prime[j];
			phi[i*prime[j]] = phi[i] * (i%prime[j] ? prime[j]-1 : prime[j]);
		}
	}
}

int main() {
	euler(1000);
	for (int i = 2; i <= 1000; i++)
		sum[i] = sum[i - 1] + phi[i];
	cin >> t;
	for (int i = 1; i <= t; i++)
	{
		scanf("%d", &n);
		printf("%d %d %d\n", i, n, sum[n] * 2 + 3);
	}
}