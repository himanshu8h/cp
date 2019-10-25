
// Sample code to perform I/O:

#include <iostream>

using namespace std;

int main()
{
    int n;
	 long int  ans=1;
	cin >> n;										// Reading input from STDIN
	int arr[n],i;
    for(i=0;i<n;i++)
	{
	    cin>>arr[i];
	    ans=(ans*arr[i])%1000000007;
	}
	cout<<ans;		// Writing output to STDOUT
}

