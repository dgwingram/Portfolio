<?php
    session_start();
    $deptId=$_SESSION["id"] ;
    $index = $_SESSION["index"];
    $move=$_POST["move"];
    if($move==0||$index+$move<0){
        $index=0;
        
    }        
    else{
        $index+=$move;
    }
    $_SESSION["index"]=$index;
        
        
    $connection=  mysqli_connect("localhost", "root", "mysql", "employees");
   
    $sql = "SELECT first_name, last_name FROM dept_emp JOIN employees USING(emp_no) JOIN departments USING(dept_no) WHERE dept_no = '$deptId' LIMIT $index, 10;";
    $result =mysqli_query($connection,$sql);                                
    
    $rows=Array();
    while($row=  mysqli_fetch_assoc($result)){
            $rows[]=$row;
    }
    echo json_encode($rows);
