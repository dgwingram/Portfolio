#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

/*
	Programmer:		Daniel Ingram
	Description:	A Program to used to calculate the volume of either a box, cylinder, or cone.
*/

void showMenu();
char validateChoice(char);
int getValidNumber();
double computeVolume(char, int, int);
void showVolume(char, int, int, double);


const double PI = 3.14159;


int main()
{
	char choice;
	int lengthOrRadius, height;
	double volume;

	do
	{
		showMenu();
		cin >> choice;
		choice = validateChoice(choice);
		if (cin.eof())
			break;

		//Ask for required input
		switch (choice)
		{
		case 'b':
			cout << "Please input the length: ";
			break;
		default:
			cout << "Please input the Radius: ";
		}
		lengthOrRadius = getValidNumber();
		cout << "Please input the height: ";
		height = getValidNumber();

		//calculate and display the volume of choice
		volume = computeVolume(choice, lengthOrRadius, height);
		showVolume(choice, lengthOrRadius, height, volume);

	} while (!cin.eof());
	system("pause");
}

void showMenu()
{
	cout << endl << "Compute the volume for a: " << endl;
	cout << setw(3) << "" << "(b)ox" << endl;
	cout << setw(3) << "" << "(c)ylinder" << endl;
	cout << setw(3) << "" << "C(o)ne" << endl << endl;
	cout << "Enter a letter from above or ^z to end ==>";
}

char validateChoice(char choice)
{

	choice = tolower(choice);

	while (choice != 'b' && choice != 'c' && choice != 'o'&&!cin.eof())
	{
		cin.ignore(80, '\n');
		cout << "Choice is incorrect.  Type 'b', 'c', or 'o' or ^Z to exit: ";
		cin >> choice;

		choice = tolower(choice);
	}
	return choice;
}

int getValidNumber()
{
	int number;

	cin >> number;

	while (number < 1 || number>100 || cin.fail())
	{
		cin.clear();
		cin.ignore(80, '\n');
		cout << "Can input numbers 1-100 only. Please Re-enter: ";
		cin >> number;
	}
	return number;
}

void showVolume(char menu, int lengthOrRadius, int height, double volume)
{
	cout << fixed << setprecision(1);
	switch (menu)
	{
	case 'b':
		cout << left << setw(15) << "Box:" << "Length=" << lengthOrRadius << endl;
		cout << setw(15) << "" << "Height=" << height << endl;
		cout << setw(15) << "" << "Volume=" << volume << endl;
		break;
	case 'c':
		cout << left << setw(15) << "Cylinder:" << "Radius=" << lengthOrRadius << endl;
		cout << setw(15) << "" << "Height=" << height << endl;
		cout << setw(15) << "" << "Volume=" << volume << endl;
		break;
	case 'o':
		cout << left << setw(15) << "Cone:" << "Radius=" << lengthOrRadius << endl;
		cout << setw(15) << "" << "Height=" << height << endl;
		cout << setw(15) << "" << "Volume=" << volume << endl;
	}
}

double computeVolume(char menuOption, int lengthOrRadius, int height)
{
	double volume;
	switch (menuOption)
	{
	case 'b':
		volume = lengthOrRadius*lengthOrRadius*height;
		break;
	case 'c':
		volume = PI*lengthOrRadius*lengthOrRadius*height;
		break;
	case 'o':
		volume = (PI*lengthOrRadius*lengthOrRadius*height) / 3;
	}
	return volume;
}
/*

Compute the volume for a:
(b)ox
(c)ylinder
C(o)ne

Enter a letter from above or ^z to end ==>b
Please input the length: 40
Please input the height: 10
Box:           Length=40
Height=10
Volume=16000.0

Compute the volume for a:
(b)ox
(c)ylinder
C(o)ne

Enter a letter from above or ^z to end ==>c
Please input the Radius: 2
Please input the height: 4
Cylinder:      Radius=2
Height=4
Volume=50.3

Compute the volume for a:
(b)ox
(c)ylinder
C(o)ne

Enter a letter from above or ^z to end ==>o
Please input the Radius: 10
Please input the height: 10
Cone:          Radius=10
Height=10
Volume=1047.2

Compute the volume for a:
(b)ox
(c)ylinder
C(o)ne

Enter a letter from above or ^z to end ==>^Z
Press any key to continue . . .
*/
