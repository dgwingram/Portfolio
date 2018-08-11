#include <ostream>
#include <iomanip>
#include "datelist.h";
using namespace std;

DateList::DateList(string newName){
	datingServiceName = newName.length == 0 ? "Invalid Name" : newName;
	numberOfDates == 0;
	firstPtr = NULL;
}

void DateList::showDate(ostream  & out)const{
	out << "SLC Online Dating Service"<<endl;
	out << left << setw(25) << "Name" << setw(8) << "Gender" << setw(8) << "Age" << endl << endl;
	
	Date *walker = firstPtr;
	while(walker!=NULL){
		out << setw(25) << walker->name << setw(8) << walker->gender << setw(8) <<walker->age;
		walker = walker->Link;
	
	}
	out << "# of possible dates=" << numberOfDates;
}
