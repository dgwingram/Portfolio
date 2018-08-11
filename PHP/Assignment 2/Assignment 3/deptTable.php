<?php

$connection=  mysqli_connect("localhost", "root", "mysql", "employees");
$sql="SELECT * FROM departments;";
$departments =mysqli_query($connection,$sql);

echo "<table id='deptTable'>";
echo "<tr><th>Departments</th></tr>";
while($row=  mysqli_fetch_assoc($departments)){

    $id=$row["dept_no"];
    $dept=$row["dept_name"];
    echo "<tr class='deptRow' ><td id='$id'>$dept</td></tr>";
}
echo"</table>";
mysqli_close($connection);
