<?php
    $host="localhost";
    $user = "root";
    $pwd = "mysql";
    $database = "comp74a1ingram";
    
    $con = mysqli_connect($host, $user, $pwd, $database);
    if($con){
        $result = mysqli_query($con, "SELECT * FROM messages WHERE messageId = 1");
            //$row = GetOneRow($result);
            $row=GetOneRow($result);
		while($row!=false){
			
			echo "<tr>";
			for($ctr=0;$ctr<9;$ctr++){
				echo "<td>";
				echo $row[$ctr];
				echo "</td>";
			}
			
			echo "</tr>";

			$row=GetOneRow($result);
		}
        if(isset($_GET['id'])){
            $result = mysqli_query($link, "SELECT * FROM messages WHERE id =".$_GET['id']);
            $row = GetOneRow($result);
            echo $row;
        }
    }
    /*
    if($_Server["REQUEST_METHOD"]=="GET"){
        
    }*/
