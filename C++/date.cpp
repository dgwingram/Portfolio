/*
	Programmer:		Daniel Ingram
	Description:	Implementation File that sets up function for dates in metric, American or with week date.
	*/
#include "date.h"
#include <string>
#include <cassert>
using namespace std;

Date::Date(){
	cout <<endl << "Enter year: ";
	cin >> year;
	while (cin.fail()||year < 1000 || year>9999){
		cout << " Enter correct year(4 digits long): ";
		cin >> year;
		cin.ignore(80,'\n');
	}
	cout << "Enter month: ";
	cin >> month;
	while (cin.fail() || month < 1 || month>12){
		cout << " Enter correct month(1-12): ";
		cin >> month;
		cin.ignore(80,'\n');
	}
	cout << "Enter day: ";
	cin >> day;
	while (cin.fail() || day < 1 || day>31){
		cout << " Enter correct month(1-31): ";
		cin >> day;
		cin.ignore(80,'\n');
	}
	cin.ignore(80, '\n');

}

AmericanDate::AmericanDate(){

}
WeekDate::WeekDate(){
	int weekDay;
	const string weekdays[7] = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

	cout << "Enter Day of the Week: ";
	cin >> weekDay;
	while (cin.fail() || weekDay > 7 || weekDay < 1){
		cout << "1 - Sun 2 - Mon 3 - Tue 4 - Wed 5 - Thursday 6 - Friday 7 - Saturday \n Enter Valid Week Day";
		cin >> weekDay;
		cin.ignore(80,'\n');
	}
	
	*dayOfWeek = weekdays[weekDay-1];
}
Date::Date(int newYear, int newMonth, int newDay)
{
	year = newYear > 0 ? newYear : 1900;
	month = newMonth >= 0 && newMonth < 13 ? newMonth : 0;
	day = newDay >= 0 && newDay < 32 ? newDay : 0;
}

AmericanDate::AmericanDate(int newYear, int newMonth, int newDay) :Date(newYear, newMonth, newDay)
{

}

WeekDate::WeekDate(int newYear, int newMonth, int newDay, int newDayOfWeek) : Date(newYear, newMonth, newDay)
{
	const string weekdays[7] = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
	dayOfWeek = new string(newDayOfWeek < 7 && newDayOfWeek >= 0 ? weekdays[newDayOfWeek-1] : weekdays[0]);
	assert(dayOfWeek != NULL);
}

WeekDate::~WeekDate()
{
	delete dayOfWeek;
}

void Date::showDate(ostream & out)const{
	out << year << "-" << (month < 10 ? "0" : "") << month << "-" << (day < 10 ? "0" : "") << day;
}

void AmericanDate::showDate(ostream & out)const{
	out << (month < 10 ? "0" : "") << month << "/" << (day < 10 ? "0" : "") << day << "/" << year;
}

void WeekDate::showDate(ostream & out)const{
	out << *dayOfWeek;
	Date::showDate(out);
}
