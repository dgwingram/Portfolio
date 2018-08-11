#include <ostream>
#include <iostream>
#include <iomanip>
#include <string>
#include <cassert>
#include "datelist.h";
using namespace std;

DateList::DateList(string newName){
	datingServiceName = newName.length() == 0 ? "Invalid Name" : newName;
	numberOfDates = 0;
	firstPtr = NULL;
}

void DateList::showDate(ostream  & out)const{
	out <<endl<<endl<< datingServiceName << endl;
	out << left << setw(25) << "Name" << setw(8) << "Gender" << setw(8) << "Age" << endl << endl;

	Date *walker = firstPtr;
	while (walker != NULL){
		out << setw(25) << walker->name << setw(8) << walker->gender << setw(8) << walker->age << endl;
		walker = walker->link;

	}
	out <<endl<< endl << "# of possible dates=" << numberOfDates << endl;
}

void DateList::addDate(){
	string newName;
	cout << "Enter Name: ";
	getline(cin, newName);
	while (newName == ""){
		cout << "No name entered.  Please re-enter: ";
		getline(cin, newName);
		cin.ignore(80, '\n');
	}
	Date *walker = firstPtr, *stalker = NULL;

	while (walker != NULL){

		if (newName == walker->name)
			break;
		stalker = walker;
		walker = walker->link;
	}
	if (walker == NULL || walker->name != newName){
		char gender;
		int age;
		Date *builder = new Date;
		assert(builder != NULL);

		cout << "Enter Age: ";
		cin >> age;
		while (age < 18){
			cout << "Must be 18 or older. Please re-enter age: ";
			cin >> age;
		}
		builder->age = age;
		cout << "Enter gender (M/F): ";
		cin >> gender;
		gender = toupper(gender);
		while (!(gender == 'M' || gender == 'F')){
			cout << "Invlaid gender.  Enter 'M' for Male or 'F' for Female. \n Please Re-Enter: ";
			cin >> gender;
			gender = toupper(gender);
		}
		builder->gender = gender;
		builder->name = newName;
		builder->link = walker;
		if (firstPtr != NULL)
			stalker->link = builder;
		else
			firstPtr = builder;
		numberOfDates++;
	}
	else
		cout << endl << newName << " already exists."<<endl
			 << "Press enter to continue: " << endl;
	cin.ignore(80, '\n');
}

void DateList::removeDate(string name){
	if (firstPtr != NULL){
		Date *walker = firstPtr, *stalker = NULL;
		string message = " was deleted.";
		while (walker != NULL){
			if (walker->name == name)
				break;
			stalker = walker;
			walker = walker->link;
		}
		if (walker == NULL){
			message = " not found.";
		}
		else if (stalker == NULL)
		{
			walker = walker->link;
			delete firstPtr;
			firstPtr = walker;
		}
		else{
			stalker->link = walker->link;
			delete walker;
		}
		cout << endl << name << message;
	}
	else{
		cout << endl << "There are no Dates.";
	}
}

DateList::~DateList(){
	Date *walker = firstPtr;
	while (firstPtr != NULL){
		walker = walker->link;
		delete firstPtr;
		firstPtr = walker;
	}
}
