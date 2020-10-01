/*You are given an array of N integers and a sum S. You have to find number of subset of the array
having sum equal to S
Input:
	5 6
	2 3 4 1 2
Output:
	4
Explaination:
	{2, 3, 1}
	{3, 1, 2}
	{2, 4}
	{4, 2}
	Hence count is 4
*/
#include<bits/stdc++.h>
using namespace std;
int dp[1002][1002];
//Buttom-Up DP
int countSubset(vector<int> &arr, int n, int s) {
	for(int i = 0; i <= s; i++) dp[0][i] = 0;
	for(int i = 0; i <= n; i++) dp[i][0] = 1;
	for(int i = 1; i <= n; i++)
		for(int j = 1; j <= s; j++)
			if(arr[i-1] <= j)
				dp[i][j] = dp[i-1][j-arr[i-1]] + dp[i-1][j];
			else
				dp[i][j] = dp[i-1][j];
	return dp[n][s];
}
int main() {
	int n, s;
	cin >> n >> s;
	vector<int> arr(n);
	for(int i = 0; i < n; i++) cin >> arr[i];
	cout << countSubset(arr, n, s) << endl;
}