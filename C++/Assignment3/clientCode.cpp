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
	datingKingston->removeDate("Jonathan"); //end
	datingKingston->removeDate("Torrie"); //delete twice
	datingKingston->removeDate("Esther"); //beginning
	datingKingston->removeDate("Daniel"); //delete non existance name

	datingKingston->showDate(cout);

	

	cout << datingKingston;
	cout << endl;
	system("pause");
}
iostream & operator<<(ostream & out, DateList dateListObj){
	if (dateListObj.firstPtr == NULL)
		out << "no Dates"<<endl;
	else
		out << "Dating Service: " << right << setw(50) << dateListObj.datingServiceName << endl
		<< "Number of Dates: " << right << setw(50) << dateListObj.numberOfDates<<endl;
	return out;
}

/*
Test Data:
	Fred			30				M
	George			20				m
	George			--				---
	Susan			59				F
	Torrie			29				f
	Hosea			80				K, l, m
	Sarah			35				f
	Michael			64				m
	Esther			1, -1, 17, 18			f				
	Jonathan		18				m			

*/
/*
--------------------------------------------------------
---------------       Output          ------------------
--------------------------------------------------------
Dating Service: SLC Online Dating Service
# of Dates: 0
Enter Name: Fred
Enter Age: 30
Enter gender (M/F): M
Fred was added.
Enter Name: George
Enter Age: 20
Enter gender (M/F): m
George was added.
Enter Name: George

George already exists.
Press enter to continue:

Enter Name: Susan
Enter Age: 59
Enter gender (M/F): F
Susan was added.
Enter Name: Sarah
Enter Age: 35
Enter gender (M/F): f
Sarah was added.
Enter Name: Micheal
Enter Age: 64
Enter gender (M/F): m
Micheal was added.
Enter Name: Esther
Enter Age: 1
Must be 18 or older. Please re-enter age: -1
Must be 18 or older. Please re-enter age: 17
Must be 18 or older. Please re-enter age: 18
Enter gender (M/F): f
Esther was added.
Enter Name: Jonathan
Enter Age: 18
Enter gender (M/F): m
Jonathan was added.
Enter Name: Torrie
Enter Age: 29
Enter gender (M/F): f
Torrie was added.
Enter Name: Hosea
Enter Age: 80
Enter gender (M/F): k
Invlaid gender.  Enter 'M' for Male or 'F' for Female.
Please Re-Enter: L
Invlaid gender.  Enter 'M' for Male or 'F' for Female.
Please Re-Enter: m
Hosea was added.


SLC Online Dating Service
Name                     Gender  Age

Esther                   F       18
Fred                     M       30
George                   M       20
Hosea                    M       80
Jonathan                 M       18
Micheal                  M       64
Sarah                    F       35
Susan                    F       59
Torrie                   F       29


# of possible dates=9

Torrie was deleted.
Jonathan was deleted.
Esther was deleted.

SLC Online Dating Service
Name                     Gender  Age

Fred                     M       30
George                   M       20
Hosea                    M       80
Micheal                  M       64
Sarah                    F       35
Susan                    F       59


# of possible dates=6
Dating Service:                          SLC Online Dating Service
Number of Dates:                                                  6

Press any key to continue . . .
*/
