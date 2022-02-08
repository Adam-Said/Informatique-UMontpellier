#include <iostream>
int main()
{
	float c;
	int f;
	std::cout << "Entrez une température en degré Farenheit : "<<std::endl;
	std::cin >> f;
	c = (5.0/9.0)*(f-32);
	std::cout << c <<std::endl;
}