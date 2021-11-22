#include <iostream>
int main()
{
	int a;
	int b;
	int c;
	std::cout << "Entrez deux nombres a et b\n"<<std::endl;
	std::cin >> a >> b;
	std::cout << "a vaut : "<< a << " et a pour adresse " << &a << " b vaut : " << b << " et a pour adresse " << &b<<std::endl;
	c=a;
	a=b;
	b=c;	
	std::cout << "a vaut : "<< a << " et a pour adresse " << &a << " b vaut : " << b << " et a pour adresse " << &b<<std::endl;
	return 0;
}