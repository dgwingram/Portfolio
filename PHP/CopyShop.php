<?php //Daniel Ingram
    include_once 'DanielIngramInclude.php';
  
    function GetInfoForm  (){
        WriteHeaders("Copy Shop","Order Form");
        echo "<form action=? method=post class=\"\">";
            DisplayLabel("Customer Type: ");
                echo "<select name = \"rate\" size = \"3\">
                        <option value = \"Regular\" selected>Regular</option>
                        <option value = \"Corporate\">Corporate</option>
                        <option value = \"Government\">Government</option>
                      </select> ";
            DisplayLabel("Number of Copies: ");
                DisplayTextbox("copiesQty",3,1);
            DisplayButton("submitOrder","Submit");
            echo "<input type=hidden name=\"showResults\" value=\"OrderResults\">";
            
        echo "</form>";
        WriteFooters();
            
      }
      function ShowResultsForm(){
              WriteHeaders("Copy Shop","Review Order");
              $orderResults=CalculateTotal(GetCostofPage($_POST["rate"]),$_POST["copiesQty"]);
              echo "<h2>Subtotal: </h2> <h3> ".number_format ($orderResults[0],2)." </h3>";
                echo "<h2>Total: </h2> <h3> ".number_format ($orderResults[1],2)." </h3>";
              WriteFooters();   
              //TODO Title, Data from Form 1
      }
  function GetCostofPage($CustomerType){
        $rate=0;
        if($CustomerType=="Regular")
           $rate=0.2;
        else
           if($CustomerType=="Corporate")
                $rate=0.15;
        else
           if($CustomerType=="Government")
               $rate=0.1;
        return $rate;
        
      }
    function CalculateTotal($rate,$qty){
        $subtotal=$rate*$qty;
        $total = $subtotal*1.13;        
        return array($subtotal,$total);
    }
    if(!isset($_POST["submitOrder"]))
        GetInfoForm();
    else
       ShowResultsForm();       
?>
