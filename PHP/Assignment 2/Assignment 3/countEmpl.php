<?php
    session_start();
    
     $_SESSION["index"] = 0;
     $_SESSION["id"] = $deptId = $_POST["id"];
    
    $connection=  mysqli_connect("localhost", "root", "mysql", "employees");
    $sql = "SELECT COUNT(*) AS emplCount FROM dept_emp WHERE dept_no = '$deptId';";
    $result =mysqli_query($connection,$sql);                                
    
      $rows=Array();
      $row=  mysqli_fetch_assoc($result);
    
      $rows[]=$row["emplCount"];
      
    
      mysqli_close($connection);
      echo json_encode($rows);
