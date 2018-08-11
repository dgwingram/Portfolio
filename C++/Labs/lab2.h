#include <ostream>
#include <string>
using namespace std;

struct ItemRec
{
	int itemCode;
	string description;
	int quantity;
};

const int SIZE = 25;

class Warehouse
{
	public:
		void showItemRec(int/*item code*/);
		void writeWarehouse(ostream&/*file or cout*/);
		int getNoItems() const { return noItems; }
		void addItem(int/*item code*/,string/*description*/,int/*quantity on hand*/);
	
	private:
		int maxitems;
		int noItems;
		ItemRec items[SIZE];
		int findItem(int/*itemCode*/)const;// is this suppose to be public,instead of private.
		
};
