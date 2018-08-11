<?php
require_once 'Asst1Include.php';
function SetUpForm($heading,$title,$Buttons){
    WriteHeaders($heading,$title,"Asst1Style.css");
    echo "<div class=\"top\">
            Music - Daniel Ingram
          </div>";
    echo "<div class=\"bigpiece\">";
        echo "<form action=? method=post>";
        echo "<div class=\"leftpiece\">";
         if(strpbrk($Buttons,"S") ){
             echo"<p>";
             DisplayButton("Save","Save");    
             echo "</p>";
         }
         if(strpbrk($Buttons,"s") ){
             echo"<p>";
             DisplayButton("SaveChanges","Save Changes");    
             echo "</p>";
         }
         if(strpbrk($Buttons,"F") ){
             echo"<p>";
             DisplayButton("find","Find Record");    
             echo "</p>";
         }
         if(strpbrk($Buttons,"H") ){
             echo"<p>";
             DisplayButton("home","Home");    
             echo "</p>";
         }
         if(strpbrk($Buttons,"C") ){
             echo"<p>";
             DisplayButton("create","Create Table");    
             echo "</p>";
         }
         if(strpbrk($Buttons,"A") ){
             echo"<p>";
             DisplayButton("add","Add Record");    
             echo "</p>";
         }
         if(strpbrk($Buttons,"M") ){
             echo"<p>";
             DisplayButton("modify","Modify Record");    
             echo "</p>";
         }
         if(strpbrk($Buttons,"D") ){
             echo"<p>";
             DisplayButton("display","Display Data");    
             echo "</p>";
         }
        echo "</div>";
        echo "<div class=\"middlepiece\">";
}
function FinishForm($portrait){
    echo "</form>";
    echo "</div>";
    echo "<div class=\"rightpiece\">";
    DisplayImage($portrait,"Student Portrait");
    echo "</div>";
    echo "</div>";
    echo "<div class=\"bottom\"";
    WriteFooters();
    echo "</div>";
}
function DisplayMainForm(){
    SetUpForm("","Copy Shop: Home","CAMD");
    echo "<p>Welcome to Copy Shop</p>";
    FinishForm("portrait.jpg");
}
function DataEntryForm(){
    SetUpForm("","Copy Shop: ","SH");
    echo "<p>";
    DisplayLabel("Band Name: ");
    DisplayTextbox("brand","15"); 
    DisplayLabel("Gig Date (yyyy/mm/dd): ");
    DisplayTextbox("date","10");
     echo "</p>";
    echo "<p>";
    DisplayLabel("CD Quantity: ");
    DisplayTextbox("numCDs","4"); 
    DisplayLabel("Selling Price: ");
    DisplayTextbox("price","6"); 
    echo "</p>";
    echo "<p>";
    DisplayLabel("Manager Fees: ");
    DisplayRadioButton("MangerFee",array("0.45","0.55"),array("45%","55%"),"0");
    DisplayLabel("Advance: ");
    DisplayCheckBox("advance","-1");
     echo "</p>";
    echo "<p>";
    DisplayLabel("Recording Studio: ");
    echo "</p>";
    echo "<p>";
    DisplayListBox("studio",array("na","rock","sing","makeMusic"),array("N/A","Rock Rules Recording Studio","Sing To Me Studios","Make Some Noise Studios"));
    echo "</p>";
    echo "<p>";
    DisplayLabel("Distrubuter Fees: ");
    DisplayTextbox("distributerFee","");
    DisplayLabel("Manufacturing Costs: ");
    DisplayTextbox("manufacturingFee","");
    echo "</p>";
    FinishForm("portrait.jpg");
}
function ResultsForm(){
   
    SetUpForm("","","SH");
    echo "<div class=\"whiteSpace\">";
    echo "<span class=\"titles\">Breakdown of Revenue</span>\n";
    $advance=0;
    $studioFee=0;
    $revenue=0.0;
    $expenses=0.0;
    echo "Number of CDs Sold: ".$_POST["numCDs"]."\n";
    echo "CD Purchase Price: ".$_POST["price"]."\n";
    $revenue = $_POST["numCDs"]*$_POST["price"];
    echo "    Total Revenue:  $".number_format($revenue,2)." \n";
    echo"\n";
    echo "<span class=\"titles\">Breakdown of Revenue</span>\n";
    $managerFees =$revenue*$_POST["MangerFee"];
    echo "Managment Fees: $managerFees \n";
    if($_POST["studio"]=="rock") 
        $studioFee = $revenue*0.05;
    if($_POST["studio"]=="sing") 
        $studioFee = $revenue*0.10;
    if($_POST["studio"]=="makeMusic") 
        $studioFee = $revenue*0.15;
    
    echo "Recording Cost: $studioFee\n";
    if(isset($_POST["advance"])){
        $advance=1000;
        echo "Advance: $advance\n";
    }
    echo "Distrubuter Fees: ".$_POST["distributerFee"]."\n";
    echo "Manufacturing Costs: ".$_POST["manufacturingFee"]."\n";
    $expense = $_POST["distributerFee"]+$_POST["manufacturingFee"]+$advance+$studioFee+$managerFees;
    
    echo "    Total Expenses:  $".number_format($expense,2)."\n";
    echo "\n\n";
    $netBalance=$revenue-$expense;
    
    echo "<span class=\"titles\">".$_POST["brand"]."'s";
    if($netBalance==0)
        echo " Broke Even. ";
    else if($netBalance<0){
        $netBalance=$netBalance*-1;
        echo " Net Loss is  ".number_format($netBalance,2).". ";
        
    }
    else{
        echo " Net Income is  $".number_format($netBalance,2).". ";
    }
    echo " Next gig is ".$_POST["date"]."</span>\n";
    echo "</div>";
    FinishForm("portrait.jpg");;
}
function CreateTableForm(){
    SetUpForm("","Copy Shop: Create","H");
    echo "Table has been created ";
   FinishForm("portrait.jpg");
}
function DisplayData(){
    SetUpForm("","Copy Shop: Display Data","H");
    echo "All Data will show here";
    FinishForm("portrait.jpg");
}
function ModifyRecord(){
    SetUpForm("","Copy Shop: Modify Record","FH");
    echo "You can search a record here ";
    FinishForm("portrait.jpg");
}
function ShowFoundRecord(){
    SetUpForm("","Copy Shop: Show Record","sH");
    echo "You can edit a record here. ";
    FinishForm("portrait.jpg");
}
function WriteFoundRecordData(){
    SetUpForm("","Copy Shop: Modify Record","H");
    echo "Record has been saved. ";
    FinishForm("portrait.jpg");
}
if(isset($_POST["create"])){
    CreateTableForm();
}
else if(isset($_POST["add"])){
    DataEntryForm();
}
else if(isset($_POST["modify"])){
    ModifyRecord();
}
else if(isset($_POST["display"])){
    DisplayData();
}
else if(isset($_POST["Save"])){
    ResultsForm();
}
else if(isset($_POST["SaveChanges"])){
    WriteFoundRecordData();
}
else if(isset($_POST["find"])){
    ShowFoundRecord();
}
else{
    DisplayMainForm();
}
?>
