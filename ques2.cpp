#include<bits/stdc++.h>
using namespace std;
void solve() {
	int n, k;
	cin >> n >> k;
	vector<int> Query(n);
	for (auto &a : Query) cin >> a;

	int prevRemaining = 0, currTotal;
	for (int i = 0; i < n; i++) {
		currTotal = prevRemaining + Query[i];
		if (currTotal < k) {
			cout << i + 1 << '\n';
			return;
		}
		prevRemaining = currTotal - k;
	}
	int day = (currTotal - 1) / k + 1;
	cout << day + n << '\n';

}
int main() {
	int t;
	cin >> t;
	while (t--) {
		solve();
	}
}