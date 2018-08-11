#include <iostream>
#include <string>
#include "warehouse.h"
using namespace std;

int main()
{
	Warehouse firstDepot( "inventory.dat", 10 );
	cout << firstDepot.getNoItems()<<endl;
	system("pause");//temporary
	
}
