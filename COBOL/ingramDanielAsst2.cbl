       IDENTIFICATION DIVISION.
       PROGRAM-ID. InventoryProgram.
       AUTHOR. Daniel Ingram.
       
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
       
       SELECT F01-INVENTORY-FILE ASSIGN TO 'ASST2.DAT'
                 ORGANIZATION IS LINE SEQUENTIAL.
       
       SELECT F02-PRINT-FILE ASSIGN TO 'Asst2ReportLayout.xlsx'
                 ORGANIZATION IS LINE SEQUENTIAL.
                               
       DATA DIVISION.
       FILE SECTION.
       
       FD F01-INVENTORY-FILE
           RECORD CONTAINS 35 CHARACTERS
           DATA RECORD IS F01-INVENTORY-RECORD.
       
       01 F01-INVENTORY-RECORD.
           05  F01-PART-NAME               PIC X(20).
           05  F01-BEGINNING-QUANTITY       PIC 9(3).
           05  F01-INVENTORY-RECEIVED      PIC 9(3).
           05  F01-INVENTORY-SHIPPED       PIC 9(3).
           05  F01-INVENTORY-PRICE         PIC 9999V99.
       
       FD F02-PRINT-FILE
           RECORD CONTAINS 83 CHARACTERS
           DATA RECORD IS F02-PRINT-RECORD.
       
       01 F02-PRINT-RECORD                 PIC X(83).
           
       
       WORKING-STORAGE SECTION.
       
	   01 W01-DATA-REMAINS-SWITCH                   PIC X(2)  VALUE SPACES
	   
	   01 W02-TITLE-LINE
			05  									PIC X(34) VALUE SPACES
			05										PIC X(26) VALUE "Daniel Ingram Assignment 2"
		    05										PIC x(23) VALUE SPACES
        
		01 W03-HEADING-LINE
			05										PIC X(9) VALUE "Part Name"
			05 										PIC X(14) VALUE Spaces
			05										PIC X(9) VALUE "Beginning"
			05										PIC X(4) VALUE SPACES
			05										PIC X(4) VALUE "Recd"
			05										PIC X(4) VALUE SPACES
			05										PIC X(7) VALUE "Shipped"
			05										PIC X(3) VALUE SPACES
			05										PIC X(6) VALUE "Ending"
			05										PIC X(4) VALUE  SPACES
			05										PIC X(5) VALUE "Price"
			05										PIC X(7) VALUE  SPACES
			05										PIC X(5) VALUE "Total"
			05										PIC X(2) VALUE  SPACES
			
			
	   01 W04-PRINT-UNDERLINE
			05 										PIC X(70) VALUE SPACES
			05 										PIC X(10) VALUE ALL "-"
			05										PIC X(3)  VALUE SPACES
			
	   01 W05-LINE-TOTALS
			05 										PIC X(28) VALUE "Total Value of all inventory"
			05										PIC X(42) VALUE SPACES
			05	W05-INVENTORY-SUM					PIC $$$$,$$9.99
			05	  					                PIC XX
			
	   01 W06-CALCULATED-NUMBERS
			05 W06-ENDING-AMOUNT					PIC 999
			05 W06-LINE-TOTAL						PIC 9999999v99
			05 W06-SUM-OF-INVENTORY					PIC 999999V99
			05 W06-SINGLE-FLAG						PIC XX				VALUE "* "
			05 W06-DOUBLE-FLAG 						PIC XX				VALUE "**"
			05 W06-NO-FLAG 							PIC XX				VALUE "  "
	   
	   01 W07-OUTPUT-LINE
			05 W07-OUTPUT-PART-NAME					PIC X(20)
			05 										PIC X(6)       VALUE SPACES
			05 W07-OUTPUT-BEGINING-QTY				PIC ZZ9
			05 										PIC X(8)       VALUE SPACES 
			05 W07-OUTPUT-RECD-QTY					PIC ZZ9
			05 										PIC X(6)       VALUE SPACES
			05 W07-OUTPUT-SHIPPED-QTY				PIC ZZ9 
			05 										PIC X(7)       VALUE SPACES
            05 W07-OUTPUT-ENDING-QTY                PIC ZZ9
            05                                      PIC X(4)       VALUE SPACES
            05 W07-OUTPUT-PRICE                     PIC ZZ9.99
            05                                      PIC X(4)       VALUE SPACES
            05 W07-OUTPUT-LINE-TOTAL                PIC ZZZZ9.99
            05 W07-OUTPUT-FLAG                      PIC XX         VALUE SPACES

       PROCEDURE DIVISION.
	   PERFORM 100-OPEN-FILES
	   PERFORM 200-WRITE-HEADING-LINES
	   PERFORM 300-PROCESS-RECORDS
           UNTIL W01-DATA-REMAINS-SWITCH = 'NO'
	   PERFORM 400-PRINT-TOTALS
	   PERFORM 500-CLOSE-FILES
	   STOP RUN
	   .
	   
      *Prepare files for read and write 
	   100-OPEN-FILES.
	   OPEN INPUT F01-INVENTORY-FILE
			OUTPUT F02-PRINT-FILE
	   
       READ F01-INVENTORY-FILE
           AT END MOVE 'NO' TO W01-DATA-REMAINS-SWITCH
       END-READ
	   .
	 
      *Write the heading and title lines to the record file  
	   200-WRITE-HEADING-LINES.
	   MOVE W02-TITLE-LINE TO F02-PRINT-RECORD
	   
       WRITE F02-PRINT-RECORD
       
       MOVE W03-HEADING-LINE TO F02-PRINT-RECORD
       
       WRITE F02-PRINT-RECORD
           AFTER ADVANCING 1 LINE
	   .
	   
	  *Retreive data from file, perfrom calculations, then print the record 
       300-PROCESS-RECORDS.
	   PERFORM 310-DO-CALCULATIONS
      
       MOVE F01-PART-NAME TO W07-OUTPUT-PART-NAME
       MOVE F01-BEGINNING-QUANTITY TO W07-OUTPUT-BEGINING-QTY
       MOVE F01-INVENTORY-RECEIVED TO W07-OUTPUT-RECD-QTY
       MOVE F01-INVENTORY-SHIPPED TO W07-OUTPUT-SHIPPED-QTY
       MOVE W06-ENDING-AMOUNT TO W07-OUTPUT-ENDING-QTY
       MOVE F01-INVENTORY-PRICE TO W07-OUTPUT-PRICE
       MOVE W06-LINE-TOTAL TO W07-OUTPUT-LINE-TOTAL
      
       MOVE W07-OUTPUT-LINE TO F02-PRINT-RECORD
       WRITE F02-PRINT-RECORD
       
       READ F01-INVENTORY-FILE
           AT END MOVE 'NO' TO W01-DATA-REMAINS-SWITCH
        END-READ
	   .
	  
      *Calculate each line's inventory totals and add all the totals together 
	   310-DO-CALCULATIONS.
	   
       COMPUTE 
           W06-ENDING-AMOUNT = F01-BEGINNING-QUANTITY + F01-INVENTORY-RECEIVED - F01-INVENTORY-SHIPPED
       END-COMPUTE
       
       COMPUTE
           W06-LINE-TOTAL ROUNDED = W06-ENDING-AMOUNT * F01-INVENTORY-PRICE
       END-COMPUTE
	   
	   IF W06-LINE-TOTAL > 50000
			MOVE W06-DOUBLE-FLAG TO W07-OUTPUT-FLAG
		ELSE
			IF W05-LINE-TOTALS >= 40000
				MOVE W06-SINGLE-FLAG TO W07-OUTPUT-FLAG
			ELSE
				MOVE W06-NO-FLAG TO W07-OUTPUT-FLAG
			END-IF
		END-IF
	   
       COMPUTE
       	   W06-SUM-OF-INVENTORY = W06-SUM-OF-INVENTORY + W06-LINE-TOTAL
       END-COMPUTE
	   .
	   
      *Print overall totals to the record file 
	   400-PRINT-TOTALS.
	   MOVE W04-PRINT-UNDERLINE TO F02-PRINT-RECORD
	   WRITE F02-PRINT-RECORD
       
       MOVE W06-SUM-OF-INVENTORY TO W05-INVENTORY-SUM
	   MOVE W05-LINE-TOTALS TO F02-PRINT-RECORD
	   
       WRITE F02-PRINT-RECORD
	   .
	   
      *Close the input and output files 
	   500-CLOSE-FILES.
	   CLOSE F01-INVENTORY-FILE
			 F02-PRINT-FILE
	   .
