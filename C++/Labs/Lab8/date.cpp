#include "date.h"
#include <string>
#include <cassert>
using namespace std;

Date::Date(int newYear, int newMonth, int newDay)
{
	year = newYear > 0 ? newYear : 1;
	month = newMonth > 0 && newMonth < 13 ? newMonth : 1;
	day = newDay >0 && newDay < 32 ? newDay : 1;
}

AmericanDate::AmericanDate(int newYear, int newMonth, int newDay) :Date(newYear, newMonth, newDay)
{

}

WeekDate::WeekDate(int newYear, int newMonth, int newDay, int newDayOfWeek) : Date(newYear, newMonth, newDay)
{
	string weekdays[7] = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

	dayOfWeek = new string(newDayOfWeek < 7 && newDayOfWeek >= 0 ? weekdays[newDayOfWeek] : weekdays[0]);
	assert(dayOfWeek != NULL);
}

WeekDate::~WeekDate()
{
	delete dayOfWeek;
}

void Date::showDate(ostream & out)const{
	out << year << "-" << month << "-" << day;
}

void AmericanDate::showDate(ostream & out)const{
	out << month << "/" << day << "/" << year;
}

void WeekDate::showDate(ostream & out)const{
	out << dayOfWeek << ", ";
	Date:showDate(out);
}
