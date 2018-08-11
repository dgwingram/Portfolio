<?php
	/*
		Programmers:	Daniel Ingram & Jeremy Robson
		Program:   		Assignment 2 Sprint 2
	*/
	$conn=null;
	function RunSelect($tablename, $wherefield="", $wherevalue="", $orderfield, $sort="ASC") {
		global $conn;
		$sql = "SELECT * FROM $tablename";
		
		if ($wherefield != "" && isset($wherevalue) && $wherevalue != "") {
			if (gettype($wherevalue) == "string" || gettype($wherevalue) == "date")
				$wherevalue = "'" . $wherevalue . "'";
			$sql = $sql . " WHERE $wherefield = $wherevalue ";
		}
		
		if ($orderfield != "")
			$sql = $sql . " ORDER BY $orderfield $sort";

		$result = mysqli_query($conn, $sql);
	
		if (!$result)
			echo mysqli_error($conn);
	
		//echo $sql;

		return $result;
	}
	
	function CreateTable($tablename, $fieldnames, $datatypes, $sizes, $decimal) {
		global $conn;
		mysqli_query($conn, "DROP TABLE IF EXISTS $tablename");
		
		$sql = "CREATE TABLE $tablename (";
		
		for ($i = 0; $i < count($fieldnames); $i++) {
			$sql = $sql . $fieldnames[$i] . " " . $datatypes[$i];
			if ($datatypes[$i] == "varchar")
				$sql = $sql . "(" . $sizes[$i] . ")";
			else if ($datatypes[$i] == "decimal")
				$sql = $sql . "(" . $sizes[$i] . ", " . $decimal[$i] . ")";
			
			if ($i < count($fieldnames) - 1)
				$sql = $sql . ",";
		}
		
		$sql = $sql . ")";
		
		$result = mysqli_query($conn, $sql);
		
		//echo $sql;

		if (!$result)
			$result = mysqli_error($conn);

		return $result != false;
	}
	
	function InsertIntoTable($tablename, $values, $datatypes) {
		global $conn;		
		$sqlRecord = "INSERT INTO $tablename VALUES (";
		
		for($ctr=0;$ctr<count($values);$ctr++) {
			
			if ($datatypes[$ctr]=="varchar" || $datatypes[$ctr]=="date") {
				$sqlRecord = $sqlRecord . "'" . $values[$ctr] . "'";
			}
			else {
				$sqlRecord = $sqlRecord . $values[$ctr];

				/*
				if($datatypes[$ctr]=="int"||$datatypes[$ctr]=="decimal") {
					if($values[$ctr]==null) {
						$sqlRecord = 0;
					}
				}
				*/				
			}
			if($ctr < count($values) - 1)
				$sqlRecord=$sqlRecord . ",";
		}
	
		$sqlRecord = $sqlRecord . ")";

		//echo $sqlRecord;

		$result = mysqli_query($conn, $sqlRecord);

		if (!$result)
			echo mysqli_error($conn);

		return $result != false;
	}

	
	function OpenConnectionandDatabase(){
		global $conn;
		$connAquired = false;
		$databaseExists = false;
		$conn = mysqli_connect("localhost","root","mysql","test");
		if($conn) 
			$connAquired=true;
		else
			print_r(mysqli_connect_error());
		$result=mysqli_query($conn,"use test");
		if($result)
			$databaseExists=true;
		return $connAquired && $databaseExists;
	}
	
	function GetOneRow($Resultset){
		$row = mysqli_fetch_array($Resultset);
		return $row;
	}
	
	function CloseConnection(){
		global $conn;
		mysqli_close($conn);
	}
?>
