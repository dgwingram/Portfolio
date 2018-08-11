#include <string>
using namespace std;

struct Date{
	string name;
	int age;
	char gender;
	Date *Link;
};

class DateList{
public:
	DateList(string /*club name*/);
	void showDate(ostream&/*file out screen*/)const;
	void addDate();
	void removeDate(string/*name*/);
	int getNoDates()const{ return numberOfDates; }
	string getDatingServiceName()const{ return datingServiceName; }
private:
	Date *firstPtr;
	string datingServiceName;
	int numberOfDates;
};
