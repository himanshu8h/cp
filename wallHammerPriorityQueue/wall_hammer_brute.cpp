#include<bits/stdc++.h>
using namespace std;
int countSetBit(int n) {
	int count = 0;
	while(n) {
		n = n & (n-1);
		count++;
	}
	return count;
}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	freopen("ob00.txt", "w", stdout);
	int n; cin >> n;
	int m; cin >> m;
	vector<int> wall;
	for(int i = 0; i < n; i++) {
		int x; cin >> x;
		wall.push_back(countSetBit(x));
	}
	vector<int> hammer;
	
	for(int i = 0; i < m; i++) {
		int x; cin >> x;
		hammer.push_back(x);
	}
	int q; cin >> q;
	while(q--) {
		int k; cin >> k;
		sort(hammer.begin(), hammer.end(), greater<int>());
		int kthSetBit = countSetBit(hammer[k-1]);
		auto it = hammer.begin() + (k - 1);
		hammer.erase(it);
		int count = 0;
		for(auto i:wall) if(i <= kthSetBit) count++;
		cout << count << endl;
	}
}