#include <iostream>
#include "datelist.h"
using namespace std;

int main(){
	DateList *datingKingston = new DateList("Kingston Dating");
	cout << datingKingston->getDatingServiceName<<endl;
	datingKingston->showDate(cout);
}
