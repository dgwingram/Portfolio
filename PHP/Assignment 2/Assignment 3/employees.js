var id="", index=0, maxIndex=0;
var btnPrev = document.getElementById("prev");
var btnNext = document.getElementById("next");
$("document").ready(function(){    
    $(".deptRow td").click(function(){       
        id=this.id;
        index=0;
        $.ajax({
           url: "countEmpl.php",
           data: {"id":id},
           type: "post",
           datatype:"json",
           error:  function(xhr,status,error){
                    alert(status+" "+error);
            },
            success: function(result1){                
                var obj = JSON.parse(result1);
                maxIndex=obj;
                index=0;                
                $.ajax({
                    url: "populateEmpl.php", 
                    data: {"move":0},
                    type: "post",
                    datatype:"json",
                    error:  function(xhr,status,error){
                            alert(status+" "+error);
                    },
                    success: function (result){
                        checkBtns();
                        var obj = JSON.parse(result);
                        dept = document.getElementById(id);
                        $("#emplData").empty();
                        var empTbl=document.getElementById("emplData");
                        empTbl.innerHTML="<table id='emplTable'></table>";
                        rowHTML="<tr id='emplData'></tr><tr><th colspan='2'>"
                        rowHTML+=dept.innerHTML;
                        rowHTML+="</th>";
                        var emplTitle = document.getElementById("emplDept");
                        emplTitle.innerHTML = dept.innerHTML;

                        for(row=0;row < 10;row++){
                            rowHTML="<tr><td class='emplRow'>";
                           rowHTML+=obj[row].first_name;
                           rowHTML+="</td><td class='emplRow'>";
                           rowHTML+=obj[row].last_name;
                           rowHTML+="</td></tr>";
                           //emplTbl.innerHTML = rowHTML;
                            $("#emplData").append(rowHTML);
                        } 
                    }
                });
            }
        });
    });
    btnClickHandlers();
});

function btnClickHandlers(){
    
    btnPrev.disabled=true;
    btnNext.disabled=true;
    $("#prev").click(function(){
        previous = -10;
        
        $.ajax({
            url: "populateEmpl.php", 
            data: {"move":previous},
            type: "post",
            datatype:"json",
            error:  function(xhr,status,error){
                    alert(status+" "+error);
            },
            success: function (result){
                index-=10;
                checkBtns();
                var obj = JSON.parse(result);
               
                dept = document.getElementById(id);
                $("#emplData").empty();
                
                for(row=0;row < 10;row++){
                   rowHTML="<tr><td class='emplRow'>";
                   rowHTML+=obj[row].first_name;
                   rowHTML+="</td><td class='emplRow'>";
                   rowHTML+=obj[row].last_name;
                   rowHTML+="</td></tr>";
                   
                    $("#emplData").append(rowHTML);
                }
            }
        });
    });
    $("#next").click(function(){
        var advance =10;
    
        
        $.ajax({
            url: "populateEmpl.php", 
            data: {"move":advance},
            type: "post",
            datatype:"json",
            error:  function(xhr,status,error){
                    alert(status+" "+error);
            },
            success: function (result){ 
    
                var obj = JSON.parse(result);
                index+=10;
                checkBtns();
                dept = document.getElementById(id);
                $("#emplData").empty();
                
                for(row=0;row < 10;row++){
                   rowHTML="<tr><td class='emplRow'>";
                   rowHTML+=obj[row].first_name;
                   rowHTML+="</td><td class='emplRow'>";
                   rowHTML+=obj[row].last_name;
                   rowHTML+="</td></tr>";
                   
                    $("#emplData").append(rowHTML);
                }         
                
            }
        });
    });
}

function checkBtns(){
    
    document.getElementById("next").disabled=false;
    document.getElementById("prev").disabled=false;
    if (index<=0){
        document.getElementById("prev").disabled=true;
    }
    else if(index>=maxIndex-10){
        document.getElementById("next").disabled=true;
    }   
}
