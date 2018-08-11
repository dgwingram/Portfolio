#include "warehouse.h"
#include <iostream>
#include <fstream>
using namespace std;

Warehouse::Warehouse(string fileName,int newMaxItems)
{
	if (newMaxItems > SIZE)
		maxItems = SIZE;
	else
		maxItems = newMaxItems;

	ifstream fin(fileName);
	if (!fin.is_open())
	{
		cout << "error opening inventory.dat file - contact systems";
		system("pause");
		exit(-1);
	}

	
	for (noItems=0; noItems< SIZE; noItems++)
	{
		fin >> items[noItems].itemCode;
		if (fin.eof())
			break;
		fin.ignore(80,'\n');
		getline(fin, items[noItems].description);
		fin >> items[noItems].quantity;

	}
}
