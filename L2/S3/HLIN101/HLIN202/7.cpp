#include <iostream>
int main()
{
	int som=0;
	int prod=1;
	for (int i = 1; i <= 100; ++i)
	{
		if (i%2==0)
		{
			som=som+i;
			prod=prod*i;		
		}
		
	}
	std::cout << som << "\n" << prod;
	return 0;
}