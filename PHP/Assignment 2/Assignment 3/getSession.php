<?php
    session_start();

    $_SESSION["emplIndex"] = 0;
    $_SESSION["deptId"] = $deptId = $_POST["deptId"];
    
    $connection=  mysqli_connect("localhost", "root", "mysql", "employees");
    $sql = "SELECT COUNT(*) AS emplIndex FROM dept_emp WHERE dept_no = '$deptId';";
    $result =mysqli_query($connection,$sql);                                
    
    $rows=Array();
    $row=  mysqli_fetch_assoc($result);
    $rows[]=$row;
    $_SESSION["emplMax"]=$row.emplIndex;
    mysqli_close($connection);
    echo json_encode($_SESSION["emplIndex"]);
