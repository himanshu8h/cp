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
	int n; cin >> n;
	int m; cin >> m;
	vector<int> wall;
	for(int i = 0; i < n; i++) {
		int x; cin >> x;
		wall.push_back(countSetBit(x));
	}
	sort(wall.begin(), wall.end());
	vector<int> hammer;
	for(int i = 0; i < m; i++) {
		int x; cin >> x;
		hammer.push_back(x);
	}
	sort(hammer.begin(), hammer.end(), greater<int>());
	vector<int> ham;
	for(int i = 0; i < m && i < 200; i++) ham.push_back(hammer[i]);
	int q; cin >> q;
	while(q--) {
		int k; cin >> k;
		int kth = ham[k-1];
		int count = (upper_bound(wall.begin(), wall.end(), countSetBit(kth)) - wall.begin());
		cout << count << endl;
		ham[k-1] -= count;
		sort(ham.begin(), ham.end(), greater<int>());
	}
}