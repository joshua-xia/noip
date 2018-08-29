#include<iostream>
#include<cstdio>
#include<cstring>
#include<algorithm>
#include<queue>
#include<map>
using namespace std;
// state���������״̬(3*3�Ź���ѹ��Ϊһ������)
// dist����ǰ���� + ����
struct rec{int state,dist;
	rec(){}
	rec(int s,int d){state=s,dist=d;}
};
int a[3][3];
// map��priority_queue���÷��μ�0x71��C++ STL
map<int,int> d,f,go;
priority_queue<rec> q;
const int dx[4]={-1,0,0,1},dy[4]={0,-1,1,0};
char dir[4]={'u','l','r','d'};

bool operator <(rec a,rec b) {
	return a.dist>b.dist;
}

// ��3*3�ľŹ���ѹ��Ϊһ��������9���ƣ�
int calc(int a[3][3]) {
	int val=0;
	for(int i=0;i<3;i++)
		for(int j=0;j<3;j++) {
			val=val*9+a[i][j];
		}
	return val;
}

// ��һ��9��������ԭ��3*3�ľŹ����Լ��ո�λ��
pair<int,int> recover(int val,int a[3][3]) {
	int x,y;
	for(int i=2;i>=0;i--)
		for(int j=2;j>=0;j--) {
			a[i][j]=val%9;
			val/=9;
			if(a[i][j]==0) x=i,y=j;
		}
	return make_pair(x,y);
}

// ������ۺ���
int value(int a[3][3]) {
	int val=0;
	for(int i=0;i<3;i++)
		for(int j=0;j<3;j++) {
			if(a[i][j]==0) continue;
			int x=(a[i][j]-1)/3;
			int y=(a[i][j]-1)%3;
			val+=abs(i-x)+abs(j-y);
		}
	return val;
}

// A*�㷨
int astar(int sx,int sy,int e) {
	d.clear(); f.clear(); go.clear();
	while(q.size()) q.pop();
	int start=calc(a);
	d[start]=0;
	q.push(rec(start,0+value(a)));
	while(q.size()) {
		// ȡ���Ѷ�
        int now=q.top().state; q.pop();
        // ��һ��ȡ��Ŀ��״̬ʱ���õ���
		if(now==e) return d[now];
		int a[3][3];
        // ��ԭ�Ź���
		pair<int,int> space=recover(now,a);
		int x=space.first,y=space.second;
        // ö�ٿո���ƶ������������ң�
		for(int i=0;i<4;i++) {
			int nx=x+dx[i], ny=y+dy[i];
			if (nx<0||nx>2||ny<0||ny>2) continue;
			swap(a[x][y],a[nx][ny]);
			int next=calc(a);
            // next״̬û�з��ʹ��������ܱ�����
			if(d.find(next)==d.end()||d[next]>d[now]+1) {
				d[next]=d[now]+1;
                // f��go��¼�ƶ���·�ߣ��Ա��������
				f[next]=now;
				go[next]=i;
                // ���
				q.push(rec(next,d[next]+value(a)));
			}
			swap(a[x][y],a[nx][ny]);
		}
	}
	return -1;
}

void print(int e) {
	if(f.find(e)==f.end()) return;
	print(f[e]);
	putchar(dir[go[e]]);
}

int main() {
	int end=0;
	for(int i=1;i<=8;i++) end=end*9+i;
	end*=9;
	int x,y;
	for(int i=0;i<3;i++)
		for(int j=0;j<3;j++) {
			char str[2];
			scanf("%s",str);
			if(str[0]=='x') a[i][j]=0,x=i,y=j;
			else a[i][j]=str[0]-'0';
		}
	int ans=astar(x,y,end);
	if(ans==-1) puts("unsolvable"); else print(end);
}