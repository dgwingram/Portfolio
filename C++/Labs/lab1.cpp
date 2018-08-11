#include <iostream>
#include <string>
#include <fstream>
#include <iomanip>
using namespace std;

const int SIZE = 10;

struct ItemRec
{
	int itemCode;
	string description;
	int quantity;
};
int loadArray(ItemRec[]);
void showArray(const int, const  ItemRec[], ofstream&);

int main()
{
	ItemRec products[SIZE];
	int length = 0;

	ofstream fout("inventory.rpt");
	if(!fout.is_open())
	{
		cout << "error opening inventory.rpt";
		system("pause");
		exit(-1);
	}
	length = loadArray(products);
	showArray(length, products, fout);
	system("type inventory.dat");
	cout << endl;
	system("type inventory.rpt");
}

int loadArray(ItemRec product[])
{
	int length=0;
	ifstream fin("inventory.dat");
	if (!fin.is_open())
	{
		cout << "error opening inventory.dat file - contact systems";
		system("pause");
		exit(-1);
	}

	for (; length< SIZE; length++)
	{
		fin >> product[length].itemCode;
		if (fin.eof())
			break;
		fin.ignore();
		getline(fin, product[length].description);
		fin >> product[length].quantity;
		
	}
	return length;
}

void showArray(const int length, const  ItemRec products[], ofstream& fout)
{
	fout << left << setw(15) << "ItemCode" << setw(20) << "Description" << setw(10) <<"Quantity on Hand" <<endl << endl;
	for (int ctr = 0; ctr<length; ctr++)
	{
		fout << setw(15) << products[ctr].itemCode << setw(20) << products[ctr].description << setw(10) << products[ctr].quantity << endl;
	}
	fout << endl;
}
/*					-------OUTPUT----------
		22140
		stove
		100
		54312
		dishwasher
		75
		60000
		freezer
		45
		ItemCode       Description         Quantity on Hand

		22140          stove               100
		54312          dishwasher          75
		60000          freezer             45

		Press any key to continue . . .
*/
/*            ------inventory.dat------
    22140
    stove
    100
    54312
    dishwasher
    75
    60000
    freezer
    45
*/
