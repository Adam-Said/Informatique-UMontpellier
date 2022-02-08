#include <iostream>
int main()
{
	int som=0;
	int prod=1;
	for (int i = 1; i <= 100; ++i)
	{
		som=som+i;
		prod=prod*i;
	}
	std::cout << som << "\n" << prod;
	return 0;
}