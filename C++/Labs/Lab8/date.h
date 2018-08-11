#include <iostream>
#include <string>
using namespace std;

class Date
{
public:
	Date();
	Date(int /*day*/, int /*month*/, int /*year*/);
	virtual void showDate(ostream & /*file or screen*/)const;
	int getYear() { return year; }
	int getMonth(){ return month; }
	int getDay(){ return day; }

protected:
	int year, month, day;
};

class AmericanDate :public Date
{
public:
	AmericanDate(); // (doesn't show a default constructon) default or theses: string /**/, string/**/, double/**/  
	AmericanDate(int /*year*/, int/*month*/, int /*day*/);
	void showDate(ostream & /*file or screen*/)const;
};

class WeekDate :public Date
{
public:
	WeekDate();
	WeekDate(int/*year*/, int/*month*/, int /*day*/, int/*day of the week*/);
	void showDate(ostream & /*file or screen*/)const;
	string getDayOfWeek()const{ return *dayOfWeek; }
	virtual ~WeekDate();
private:
	string *dayOfWeek;
};
