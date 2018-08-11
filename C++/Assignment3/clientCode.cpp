#include <iostream>
#include <iomanip>
#include <cassert>
#include "datelist.h"
using namespace std;

int main(){
	DateList *datingKingston = new DateList("SLC Online Dating Service");
	assert(datingKingston);
	cout << "Dating Service: " << datingKingston->getDatingServiceName() <<endl
	     << " # of Dates: " << datingKingston->getNoDates() << endl;

	for (int ctr = 0; ctr < 3; ctr++)
		datingKingston->addDate();

	datingKingston->showDate(cout);

	datingKingston->removeDate("Torrie"); //middle
	datingKingston->removeDate("Fred"); //beginning
	datingKingston->removeDate("Jonathan"); //end
	datingKingston->removeDate("Torrie"); //delete twice

	datingKingston->showDate(cout);

	

	cout << datingKingston;
	cout << endl;
	system("pause");
}
iostream& operator<<(iostream& out, DateList dateListObj){
	if (dateListObj.firstPtr == NULL)
		out << "no Dates"<<endl;
	else
		out << "Dating Service: " << right << setw(50) << dateListObj.datingServiceName << endl
		<< "Number of Dates: " << right << setw(50) << dateListObj.numberOfDates<<endl;
	return out;
}

/*
Test Data:
	Fred			30				m
	George			20				m
	George			--				---
	Susan			59				f
	Torrie			29				f
	Hosea			80				m
	Sarah			35				f
	Michael			64				m
	Esther			17,-1,18		f				
	Jonathan		18				m			

*/
