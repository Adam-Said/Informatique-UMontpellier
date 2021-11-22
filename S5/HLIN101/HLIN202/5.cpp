#include <iostream>
int main()
{	
	int x;
	std::cin >> x;
	if (x<0)
	{
		x=x-(2*x);
	}
	std::cout << x<< std::endl;
	return 0;
}