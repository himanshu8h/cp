#include<bits/stdc++.h>
using namespace std;
void solve() {
	int n;
	cin >> n;
	int no_of_digits = log10(n) + 1;
	int value_of_digit = n % 10;
	int ans = 0;
	ans = (value_of_digit - 1) * 10;
	ans += (no_of_digits * (no_of_digits + 1) / 2);
	cout << ans << "\n";
}
int main() {
	int t;
	cin >> t;
	while (t--)
		solve();
}