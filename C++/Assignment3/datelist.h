#include <string>
using namespace std;

struct Date{
	string name;
	int age;
	char gender;
	Date *link;
};

class DateList{
public:
	DateList(string /*club name*/);
	~DateList();
	void showDate(ostream&/*file out screen*/)const;
	void addDate();
	void removeDate(string/*name*/);
	int getNoDates()const{ return numberOfDates; }
	string getDatingServiceName()const{ return datingServiceName; }
	friend iostream& operator<<(iostream& /*output or file*/, DateList /*DateList object*/);
private:
	Date *firstPtr;
	string datingServiceName;
	int numberOfDates;
};
