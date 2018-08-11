<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" type="text/css" href="employees.css">
    </head>
    <body>
        <div id="dept">
        <?php
            session_start();
            $_SESSION["id"]="";
            $_SESSION["max"]=$_SESSION["index"]=0;
            include_once 'deptTable.php';
        ?>
        </div>
        <div id="empl">
            <table id="emplTable">
                <tr><th id="emplDept" colspan="2">Department Employees</th></tr>
                <tr id="dataRow">
                    <td id="emplData" colspan="2"></td>
                </tr>
                <tr>
                    <td><button type='button' id='prev'>Previous</button></td>
                    <td><button type='button' id='next'>Next</button></td>
                </tr>
            </table>
        </div>
        <script type="text/javascript" src="js/libs/jquery/jquery.js"></script>
        <script src="employeeTable.js"></script>
    </body>
</html>
