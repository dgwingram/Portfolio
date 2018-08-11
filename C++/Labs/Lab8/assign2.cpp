#include "date.h"
#include <cassert>
using namespace std;

int main()
{
	Date *theDate = new Date(1867, 7, 1);
	assert(theDate != 0);
	AmericanDate *usDate=new AmericanDate(1987, 01, 20);
	assert(usDate != NULL);
	WeekDate *halloween=new WeekDate(2015, 10, 31, 7);
	assert(halloween != NULL);

	cout << "Date: ";
	theDate->showDate(cout);
	cout << "My Date: ";
	usDate->showDate(cout);
	cout << "WeekDate: ";
	halloween->showDate(cout);

  delete theDate, usDate, halloween;
}
