       IDENTIFICATION DIVISION.
       PROGRAM-ID. SAMPLE.
       AUTHOR. Daniel Ingram.
      ******
      ****** SampleProgram.cbl - Basic Cobol Program
      ******

       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.

       DATA DIVISION.
      * ONLY PLACE YOU CAN DECLARE DATA OR VARIABLES
       FILE SECTION.
       WORKING-STORAGE SECTION.
      *Declare a variable called W01-SWITCH
      *PIC X tells the compiler that the variable is 1 byte/1 character 
       01  W01-SWITCH PIC X.

       PROCEDURE DIVISION.
           DISPLAY 'MAIN STARTING'
      *Go run the paragraph (procedure) 100-INT then come back.    
           PERFORM 100-INIT
      *This is a loop.  Keep running 200 - Process paragraph
      *Stop the loop when W01-SWITCH is N
           PERFORM 200-PROCESS UNTIL W01-SWITCH = 'N'
           PERFORM 300-FINAL
           DISPLAY 'MAIN STOPPING'
           ACCEPT W01-SWITCH
           STOP RUN
           .
      * Notice the paragraph starts with a name then period
      * and ends with a period 
       100-INIT.
           DISPLAY '100-INIT RUNNING'
      *This is an assignment statement.    
           MOVE 'Y' TO W01-SWITCH
           .
      
       200-PROCESS.
           DISPLAY '200-PROCESS RUNNING'
           MOVE 'N' TO W01-SWITCH
           .

       300-FINAL.
           DISPLAY '300-FINAL RUNNING'
           .


