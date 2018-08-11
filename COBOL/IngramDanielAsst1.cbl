       IDENTIFICATION DIVISION.
       PROGRAM-ID. IngramDanielAsst1.
       AUTHOR. Daniel Ingram.
       
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
           SELECT F01-EMPLOYEE-IN ASSIGN TO "Asst1.dat"
               ORGANIZATION IS LINE SEQUENTIAL.
           SELECT F02-PRINT-FILE ASSIGN TO "ASST1.OUT"
                ORGANIZATION IS LINE SEQUENTIAL.
                
       DATA DIVISION.
       FILE SECTION.
       FD F01-EMPLOYEE-IN
           RECORD CONTAINS 30 CHARACTERS
           DATA RECORD IS F01-EMPLOYEE-RECORD.
       01  FO1-EMPLOYEE-RECORD.
           05 F01-EMPLOYEE-NAME        PIC X(18).
           05 F01-EMPLOYEE-SIN-NUMBER  PIC X(9).
           05 F01-EMPLOYEE-GROSS-PAY   PIC 9(3).
       
       FD F02-PRINT-FILE
           RECORD CONTAINS 171 CHARACTERS
           DATA RECORD IS F02-PRINT-FILE-RECORD.
       01 F02-PRINT-FILE-RECORD        PIC X(171).
       
       WORKING-STORAGE SECTION.
       
       01  W01-EMPLOYEE-DATA.
           05                      PIC X(2)    VALUE SPACES.
           05 W01-EMPLOYEE-NAME    PIC X(18).
           05                      PIC X(2)    VALUE SPACES.
           05 W01-SIN-NUMBER       PIC 9(9).
           05                      PIC X(7)    VALUE SPACES.
           05 W01-HUNDRED-BILLS    PIC 9.
           05                      PIC X(5)    VALUE SPACES.
           05 W01-FIFTY-BILLS      PIC 9.
           05                      PIC X(4)    VALUE SPACES.
           05 W01-TWENTY-BILLS     PIC 9.
           05                      PIC X(4)    VALUE SPACES.
           05 W01-TEN-BILLS        PIC 9.
           05                      PIC X(4)    VALUE SPACES.
           05 W01-FIVE-BILLS       PIC 9.
           05                      PIC X(4)    VALUE SPACES.
           05 W01-ONE-COIN         PIC 9.
           05                      PIC X(3)    VALUE SPACES.
           05 W01-GROSS-PAY        PIC 9(3).
       
       01 W02-DETAIL-LINE.
           05                      PIC X(2)    VALUE SPACES.
           05                      PIC X(13)   VALUE 'EMPLOYEE NAME'.
           05                      PIC X(20)   VALUE SPACES.
           05                      PIC X(4)    VALUE '$100'.
           05                      PIC X(3)    VALUE SPACES.
           05                      PIC X(3)   VALUE '$50'.
           05                      PIC X(2)    VALUE SPACES.
           05                      PIC X(3)   VALUE '$20'.
           05                      PIC X(2)    VALUE SPACES.
           05                      PIC X(3)   VALUE '$10'.
           05                      PIC X(3)    VALUE SPACES.
           05                      PIC X(2)   VALUE '$5'.
           05                      PIC X(3)    VALUE SPACES.
           05                      PIC X(2)   VALUE '$1'.
           05                      PIC X(3)    VALUE SPACES.
           05                      PIC X(3)   VALUE 'PAY'.
       
       01 W03-HEADER-TITLE.
           05                      PIC X(17)   VALUES SPACES.
           05                      PIC X(30)   VALUE 'DANIEL INGRAM COBOL ASSIGNMENT'.
           05                      PIC X(24)   VALUE SPACES.
      
       01 W04-DATA-REMAINS-SWITCH  PIC XX      VALUE SPACES.
       
       01 W05-COMPUTE-DATA.
           05 W05-REMAINDER        PIC 99.
           05 W05-HUNDRED          PIC 999     VALUE 100.
           05 W05-FIFTY            PIC 99      VALUE 50.
           05 W05-TWENTY           PIC 99      VALUE 20.
           05 W05-TEN              PIC 99      VALUE 10.
           05 W05-FIVE             PIC 9       VALUE 5.
       01 W06-FOOTER.
           05                      PIC X(13)   VALUE 'End of Report'.
           05                      PIC X(58)   VALUE SPACES.
           
       PROCEDURE DIVISION.
      *START PROGRAM AND CALL ON FUNCTIONS FOR CALCULATIONS 
      *AND READ/WRITE TO FILES  
           PERFORM 100-OPEN-FILES
           PERFORM 200-WRITE-HEADING-LINES      
           PERFORM 300-PROCESS-RECORDS 
               UNTIL W04-DATA-REMAINS-SWITCH = 'NO'
           PERFORM 400-WRITE-FOOTER
           PERFORM 500-CLOSE-FILES
           STOP RUN
           .
      
      * OPEN FILES FOR INPUT AND OUTPUT AND PRIME READ DATA     
       100-OPEN-FILES.
           OPEN INPUT F01-EMPLOYEE-IN
                OUTPUT F02-PRINT-FILE
            READ F01-EMPLOYEE-IN
               AT END MOVE 'NO' TO W04-DATA-REMAINS-SWITCH
            END-READ
           .
      
      *WRITE HEADINGS TO OUTPUT FILE 
       200-WRITE-HEADING-LINES.
           MOVE W03-HEADER-TITLE TO F02-PRINT-FILE-RECORD
           WRITE F02-PRINT-FILE-RECORD
           MOVE W02-DETAIL-LINE TO F02-PRINT-FILE-RECORD
           WRITE F02-PRINT-FILE-RECORD
           .
      
      *CALCULATE COLUMNS AND WRITE DATA TO FILE  
       300-PROCESS-RECORDS.
           MOVE F01-EMPLOYEE-NAME TO W01-EMPLOYEE-NAME
           MOVE F01-EMPLOYEE-SIN-NUMBER TO W01-SIN-NUMBER
           MOVE F01-EMPLOYEE-GROSS-PAY TO W01-GROSS-PAY
           PERFORM 310-DO-CALCULATIONS
		   MOVE W01-EMPLOYEE-DATA TO F02-PRINT-FILE-RECORD
		   WRITE F02-PRINT-FILE-RECORD
           READ F01-EMPLOYEE-IN
               AT END MOVE 'NO' TO W04-DATA-REMAINS-SWITCH
            END-READ
           .
      * DETERMINE HOW MANY OF EACH DENOMINATION PER COLUMN
       310-DO-CALCULATIONS.
           MOVE F01-EMPLOYEE-GROSS-PAY TO W01-GROSS-PAY
           DIVIDE W01-GROSS-PAY BY W05-HUNDRED GIVING W01-HUNDRED-BILLS REMAINDER W05-REMAINDER
           DIVIDE W05-REMAINDER BY W05-FIFTY GIVING W01-FIFTY-BILLS REMAINDER W05-REMAINDER
           DIVIDE W05-REMAINDER BY W05-TWENTY GIVING W01-TWENTY-BILLS REMAINDER W05-REMAINDER
           DIVIDE W05-REMAINDER BY W05-TEN GIVING W01-TEN-BILLS REMAINDER W05-REMAINDER
           DIVIDE W05-REMAINDER BY W05-FIVE GIVING W01-FIVE-BILLS REMAINDER W05-REMAINDER
           MOVE W05-REMAINDER TO W01-ONE-COIN
           .
           
      * WRITE FOOTER FOR OUTPUT FILE
       400-WRITE-FOOTER.
           MOVE W06-FOOTER TO F02-PRINT-FILE-RECORD
           WRITE F02-PRINT-FILE-RECORD
           .
           
      *CLOSE INPUT AND OUTPUT FILES
       500-CLOSE-FILES.
           CLOSE F01-EMPLOYEE-IN
                 F02-PRINT-FILE
           .
