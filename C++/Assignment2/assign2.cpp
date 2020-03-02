/*
	Programmer:		Daniel Ingram
	Description:	Program prints dates in metric, American or with day of weeek
	*/

#include "date.h"
#include <cassert>
using namespace std;


void displayDate(const Date *);
int main() {
	
	//Declare and show date for Haloween in metric format
	Date halloween(2015, 10, 31);
	cout << "Halloween (metric format) is: ";
	halloween.showDate(cout);
	cout << endl << endl;

	//declare and show date for Thanksgiving in American format
	AmericanDate thanksgiving(12, 10, 2015);
	cout << endl << "Thanksgiving (American format) is: ";
	
	thanksgiving.showDate(cout);
	cout << endl << endl;

	// Declare and show date in the weekDate format
	WeekDate  lastDay(2015, 12, 18, 6);
	cout << endl << "Last day of classes is: ";
	lastDay.showDate(cout);
	cout << endl << endl;


	// Declare non-default constructor date in the American format dynamically
	AmericanDate *christmasDay = new AmericanDate(2015, 12, 25);
	assert(christmasDay != NULL);
	
	// Display the date 
	christmasDay->showDate(cout);
	cout << endl;
	// Declare non-default constructor date in the weekDate format dynamically
	WeekDate *christmasParade = new WeekDate(2015, 12, 5, 7);
	assert(christmasParade != 0);
	// Display the Date
	christmasParade->showDate(cout);
	cout << endl;

	// Declare default constructor date in the American format dynamically
	AmericanDate * defaultDate = new AmericanDate;
	assert(defaultDate!=NULL);
		
	// Call polymorphic function with the American Date format
	displayDate(defaultDate);
	cout << endl;

	//call friend function with the American Date format
	printDate(defaultDate);

	// Declare default constructor date for the weekdate format
	WeekDate *defaultWeekDate = new WeekDate;
	assert(defaultWeekDate != NULL);

	// Call polymorphic function with the weekDate format
	displayDate(defaultWeekDate);
	cout << endl;

	//call friend function with the weekDate format
	printDate(defaultWeekDate);
	cout << endl;


	// delete all date objects on the heap
	delete defaultDate;
	delete defaultWeekDate;
	delete christmasDay;
	delete christmasParade;

	system("pause");
}

// Polymorphic Function
void displayDate(const Date * datePtr){
	datePtr->showDate(cout);
}

// Friend Function
void printDate(const Date * datePtr){
	
	const string monthName[12] = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

	// Output Month
	cout << monthName[datePtr->month - 1] << " " << datePtr->day << ", " << datePtr->year;
}

