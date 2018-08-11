<?php
	error_reporting(0);
	require_once('clsVehicle.php');
	//Method1:declare the object and call the constructor
	$objSpyder= new clsVehicle("918 Spyder",845000);
	//Method 2 declare object with no parameters
	$objEmpty = new clsVehicle();
	// To call a method $ObjName->methodName(parameter list)
	
	// cannot access object without setter. Ex: '$obj->VaribalName = data' wont work.  Setter must be called
	
	if(!$objEmpty->setModel("Vroom"))
		echo "wrong Data inputted";
	
	echo "<p> Method 1: shows " . $objSpyder->GetMeData() . "</p>";
	echo "<p> Method 2: shows " . $objEmpty->GetMeData() . "</p>";
	echo "<p> Model: " . $objSpyder->getModel() . " Price: $" . $objSpyder->getPrice() . " Markup: " . $objEmpty->getMarkupPercent() . " TaxRate: " . getTaxRate;
?>
