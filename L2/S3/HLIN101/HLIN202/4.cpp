#include <iostream>
int main()
{
	int c;
	float f;
	std::cout << "Entrez une température en degré Celsius : "<<std::endl;
	std::cin >> c;
	f = c*(9.0/5.0)+32;
	std::cout << f <<std::endl;
	return 0;
}