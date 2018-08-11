<?php 
    // Write Header code
    function WriteHeaders($Heading="Welcome",$Title="MySite",$cssStyle="ZooParameters.css"){
        echo "<!doctype html> 
        <html lang = \"en\">
        <head>
            <meta charset = \"UTF-8\">
            <link rel=\"stylesheet\" type=\"text/css\" href=\"$cssStyle\">
            <title>$Title</title>
        </head>
        <body>
        <h1>$Heading</h1>";
    }

    // Display Label
    function DisplayLabel($Text){
        echo "<label >$Text</label>";
    }

    // Display Textbox
    function DisplayTextbox($txtName,$txtSize,$txtValue=""){
        echo "<Input type = text name = $txtName Size = $txtSize value = $txtValue >";
    }

    // Display Contact info
    function DisplayContactInfo(){
        echo "<footer>Questions? Comments? <a href=\"mailto:dingram20@student.sl.on.ca\"> dingram20@student.sl.on.ca </a></footer> ";
    }

    // Display Image
    function DisplayImage($fileName, $alt, $height=0, $width=0){
        echo "<img src=\"$fileName\" alt=\"$alt\"";
        if($height>0){
            echo "height=\"$height\"";
            if($width>0){
                echo "width=\"$width\"";
            }
        
        }
        echo " />";
    }

    // Create a button 
    function DisplayButton($btnName, $btnText, $btnFileName="na", $btnAlt="na") 
    {
        echo "<button name=\"$btnName\" >";
        if($btnFileName !== "na")
        {
            echo "<img src=\"$btnFileName\" value=\"$btnText\" ";
            if($btnAlt!="na"){
                echo "alt=\"$btnAlt\" ";
            }
            echo "/> <br>";
        }
        else{
            echo "$btnText";
        }
        echo "</button>";
    } 
    // Write Footer at the bottom of the Code
    function WriteFooters(){
        DisplayContactInfo();
        echo "</body> </html>";
    }
    function DisplayRadioButton($radioName,$radioValue,$radioText,$checkedBtn="-1"){
        for($ctr=0;$ctr<(sizeof($radioValue));$ctr++){
            echo "<input type=\"radio\" name=\"$radioName\" value=\"$radioValue[$ctr]\" ";
            if($ctr==$checkedBtn) 
                echo "checked";
            echo ">$radioText[$ctr] ";
        }   
    }
    function DisplayListBox($listName,$ListValues,$ListText){
        $listSize = sizeof($ListValues);
        echo "<select name =\"$listName\" size=\"$listSize\">";
        for($ctr=0;$ctr<$listSize;$ctr++){
            echo "<option value=\"$ListValues[$ctr]\" ";
            if($ctr==0)
                echo "selected";
            echo ">$ListText[$ctr]</option> ";
        }
        echo "</select>";
    }
    function DisplayCheckbox($chkValue,$checkedBox="-1"){
        echo "<input type=checkbox name=\"$chkValue\" ";
        if($checkedBox!=-1)
            echo "checked";
        echo " />";
    }
?>
