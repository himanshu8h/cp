#include <bits/stdc++.h>
using namespace std;
int countSetBit(int n) {
	int count = 0;
	while(n) {
		n = n & (n-1);
		count++;
	}
	return count;
}
int find_kth(priority_queue<int> &hammer, int k) {
	stack<int> temp;
	while(k--) {
		temp.push(hammer.top());
		hammer.pop();
	}
	int t = temp.top(); temp.pop();
	while(!temp.empty()) {
		hammer.push(temp.top());
		temp.pop();
	}
	return t;
}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	freopen("oc0.txt", "w", stdout);
	int n; cin >> n;
	int m; cin >> m;
	vector<int> wall;
	for(int i = 0; i < n; i++) {
		int x; cin >> x;
		wall.push_back(countSetBit(x));
	}
	sort(wall.begin(), wall.end());
	priority_queue<int> hammer;
	
	for(int i = 0; i < m; i++) {
		int x; cin >> x;
		hammer.push(x);
	}
	int q; cin >> q;
	while(q--) {
		int k; cin >> k;
		int kth = find_kth(hammer, k);
		cout << (upper_bound(wall.begin(), wall.end(), countSetBit(kth)) - wall.begin()) << endl;
	}
}