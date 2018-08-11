#include <iostream>
#include <cassert>
#include "datelist.h"
using namespace std;

int main(){
	DateList *datingKingston = new DateList("SLC Online Dating Service");
	assert(datingKingston);

	datingKingston->addDate();
	datingKingston->addDate();
	datingKingston->addDate();
	datingKingston->addDate();

	cout << datingKingston->getDatingServiceName() << endl;
	datingKingston->showDate(cout);
	

	cout << endl << datingKingston->getNoDates();

	system("pause");
}
