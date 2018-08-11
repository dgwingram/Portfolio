<?php
	/*
		Programmers:	Daniel Ingram & Jeremy Robson
		Program:   		Assignment 2 Sprint 2
	*/
	echo "
	<!doctype html>
	<html lang = \"en\">
	   <head>
		 <meta charset = \"UTF-8\">
		 <title>Testing DB Functions</title>
	   </head>
	   <body>";

	require_once("Asst2DatabaseFunctions.php");
	error_reporting(0);
	//ini_set("display_errors", "On");
	
		
	function test() {
		OpenConnectionandDatabase();
		
		$tableName="testing";
		
		$fieldnames=array(
			0=>"Item", 
			1=>"City",
			2=>"DateReceived",
			3=>"Quantity",
			4=>"Price"
		);

		$dataTypes=array(
			0=>"varchar", 
			1=>"varchar",
			2=>"date",
			3=>"int",
			4=>"decimal"
		);

		$sizes=array(
			0=>25, 
			1=>10,
			2=>0,
			3=>0,
			4=>4
		);

		$decimal=array(
			0=>0, 
			1=>0,
			2=>0,
			3=>0,
			4=>2
		);
		
		if (CreateTable($tableName,$fieldnames,$dataTypes,$sizes,$decimal))
			echo "<p>Table Created Successfully</p>";
		else
			echo mysqli_error($conn);

		$value=array(
			0=>"Tea", 
			1=>"Toronto",
			2=>"2015-05-31",
			3=>15,
			4=>6.45
		);

		$dataTypes=array(
			0=>"varchar", 
			1=>"varchar",
			2=>"date",
			3=>"int",
			4=>"decimal"
		);	

		if (InsertIntoTable($tableName, $value, $dataTypes))
			echo "<p>Data Inserted Successfully</p>";
		else
			echo mysqli_error($conn);

		$value=array(
			0=>"Milk", 
			1=>"Kingston",
			2=>"2014-06-17",
			3=>100,
			4=>0.99
		);

		$dataTypes=array(
			0=>"varchar", 
			1=>"varchar",
			2=>"date",
			3=>"int",
			4=>"decimal"
		);
		
		if(InsertIntoTable($tableName, $value, $dataTypes)) 
			echo "<p>Data Inserted Successfully</p>";
		else
			echo mysqli_error($conn);
		
		$result = RunSelect($tableName, "Item","","Item");

		if ($result) {
			echo "<ul>";
			while($row = GetOneRow($result)) {
				echo "<li>";
				print_r($row);
				echo "</li>";
			}
			echo "</ul>";
		}
		else
			echo mysqli_error($conn);
		CloseConnection();
	}
		
  	test();
    
	echo "</body>
         </html>";
?>
