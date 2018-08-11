       IDENTIFICATION DIVISION.
       PROGRAM-ID.  Asst3.
       AUTHOR. Daniel Ingram.

       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
       SELECT F01-EMPLOYEE-FILE ASSIGN TO 'Asst3.dat'
               ORGANIZATION IS LINE SEQUENTIAL.
						
       SELECT F02-VALID-FILE ASSIGN TO 'ValidRecords.dat'                
               ORGANIZATION IS LINE SEQUENTIAL.
						
       SELECT F03-ERROR-FILE ASSIGN TO 'ErrorFile.dat'
                    ORGANIZATION IS LINE SEQUENTIAL.

       DATA DIVISION.
       FILE SECTION.
       FD 	F01-EMPLOYEE-FILE
            RECORD CONTAINS 78 CHARACTERS
            DATA RECORD IS F01-EMPLOYEE-RECORD.
       01  F01-EMPLOYEE-RECORD.
       
           05	F01-EMPLOYEE-SSN				PIC 9(9).
           05	F01-EMPLOYEE-SSN-X
                   REDEFINES F01-EMPLOYEE-SSN 	PIC X(9).
                    
           05	F01-FULL-NAME.
                10	F01-LAST-NAME				PIC X(14).
                10	F01-FIRST-NAME				PIC X(12).
                10	F01-INITIAL					PIC X.
                
           05	F01-HOURLY-RATE					PIC 9(3)V99.
           05	F01-HOURLY-RATE-X
                    REDEFINES F01-HOURLY-RATE 	PIC X(5).
                    
           05	F01-HOURS-WORKED				PIC	9(3)V99.
                88 	F01-OVERTIME-CHECK			VALUES 0 THRU 40.
           05  F01-HOURS-WORKED-X
                   REDEFINES F01-HOURS-WORKED 	PIC X(5).
                    
           05  F01-SALARY-TYPE					PIC X.
           05	F01-DEPENDANTS					PIC 99.
                88	F01-DEPENDANTS-VALID		VALUES 0 THRU 10  .      
           05  F01-DEPENDANTS-X
                    REDEFINES F01-DEPENDANTS    PIC XX.
                    
           05	F01-TAX-STATUS					PIC 9.
           05 	F01-INSURANCE 					PIC X.
                88	F01-INSURANCE-CHECK	 		VALUES "A" "B" "C" "Z".
           05	F01-YTD-INFO.
                10	YTD-EARNINGS				PIC 9(6)V99.
                10	YTD-TAXES					PIC 9(5)V99.
                10	YTD-FICA					PIC 9(4)V99.
                10	YTD-INSURANCE				PIC 9(4)V99.

       FD	F02-VALID-FILE
            RECORD CONTAINS 78 CHARACTERS
            DATA RECORD IS F02-VALID-RECORD.
       01	F02-VALID-RECORD					PIC X(78).

       FD 	F03-ERROR-FILE
            RECORD CONTAINS 94 CHARACTERS
            DATA RECORD IS F03-ERROR-RECORD.
       01	F03-ERROR-RECORD					PIC X(94).

       WORKING-STORAGE SECTION.
       01 W01-HEADINGS.
            05	W01-PAGE-HEADING.
                10     PIC X(28) VALUE SPACES.
                10     PIC X(18) VALUE "Payroll Error File".
            05 W01-COLUMN-HEADINGS.
                10     PIC XXX	VALUE "SSN".
                10     PIC X(12) VALUE SPACES.
                10     PIC X(9)  VALUE "Last Name".
                10     PIC X(7) VALUE SPACES.
                10     PIC X(20) VALUE "Offending Field Data".
                10	   PIC X(5) VALUE SPACES.
                10	   PIC X(5) VALUE "Error".
            05	W01-FOOTER.		
                10     PIC X(19) VALUE  "ReportProduced By".
                10     PIC X(13) VALUE  "Daniel Ingram".
				
       01 W02-ERR-MSGS.
           05 W02-SSN-ERR-MSG   					PIC X(38)
                    VALUE 'Social Security Number cannot be blank'.
	       05 W02-DEPEND-NOT-NUMERIC-ERR 		PIC X(26)
				        VALUE 'Dependents must be numeric'.
	       05 W02-DEPEND-OVER10-ERR 				PIC X(29)
				        VALUE 'Dependents must be 10 or less'.
	       05 W02-HOURS-NOT-NUMERIC-ERR 			PIC X(28)
				        VALUE 'Hours worked must be numeric'.
	       05 W02-SALARY-OVER-40-ERR-MSG     	PIC X(34)
				        VALUE 'No overtime for salaried employees'.
	       05 W02-INSURANCE-ERR 					PIC X(30)
				        VALUE 'Insurance must be A, B, C or Z'.
		
       01 W03-CONTROLS.
           05   W03-EOF-SWITCH 					PIC X VALUE SPACES.
	       05   W03-ERRORS						PIC X.
                   88 W03-ERROR-FOUND                 VALUE "T".
                   88 W03-ERROR-NOT-FOUND             VALUE "F".
           05  W03-TRUE                         PIC X VALUE "T".
           
	        
       01 W04-ERROR-INFO.
           05  W04-ERROR-SSN                   PIC X(9).
           05                                  PIC X(6) VALUE SPACES.
           05  W04-ERROR-LAST-NAME             PIC X(14)
           05                                  PIC XX VALUE SPACES.
           05  W04-ERROR-DATA                  PIC X(15).
           05                                  PIC X(10) VALUE SPACES.
           05  W04-ERROR-MSG                   PIC X(38).       
				
       PROCEDURE DIVISION.
       
	        PERFORM 100-OPEN-FILES
	        PERFORM 200-WRITE-HEADINGS
	        PERFORM 300-READ-RECORD
				
	        PERFORM 400-PROCESS-RECORD
		        UNTIL W03-EOF-SWITCH = 'T'
			PERFORM 500-WRITE-FOOTER	
	        PERFORM 600-CLOSE-FILES
            STOP RUN
	        .
            
       100-OPEN-FILES.
	        OPEN INPUT F01-EMPLOYEE-FILE
			        OUTPUT F02-VALID-FILE
			        OUTPUT F03-ERROR-FILE
       .
       200-WRITE-HEADINGS.
	       WRITE F03-ERROR-RECORD FROM W01-PAGE-HEADING
		        BEFORE ADVANCING 1 LINE
           WRITE F03-ERROR-RECORD FROM W01-COLUMN-HEADINGS
       .   
       300-READ-RECORD.
	        READ F01-EMPLOYEE-FILE
		        AT END MOVE W03-TRUE TO W03-EOF-SWITCH
	        END-READ
       .

       400-PROCESS-RECORD.
           SET W03-ERROR-NOT-FOUND TO TRUE
           PERFORM 410-VALIDATE-SSN
           
           PERFORM 420-VALIDATE-HOURS-WORKED
           PERFORM 430-VALIDATE-DEPENDENTS
           PERFORM 440-VALIDATE-INSURANCE
           IF W03-ERROR-NOT-FOUND
               PERFORM 401-WRITE-RECORD
           END-IF
           
           
            
           PERFORM 300-READ-RECORD
           
       .
       401-WRITE-RECORD.
             MOVE F01-EMPLOYEE-RECORD TO F02-VALID-RECORD
	         WRITE F02-VALID-RECORD 
             DISPLAY F02-VALID-RECORD
             
       .
       402-WRITE-ERROR.
            MOVE SPACES TO F03-ERROR-RECORD
            IF W03-ERROR-FOUND
               CONTINUE
            ELSE 
                WRITE F03-ERROR-RECORD
                MOVE W03-TRUE TO W03-ERRORS
            END-IF
            MOVE F01-LAST-NAME TO W04-ERROR-LAST-NAME
            MOVE F01-EMPLOYEE-SSN-X TO W04-ERROR-SSN
            MOVE W04-ERROR-INFO TO F03-ERROR-RECORD
            WRITE F03-ERROR-RECORD 
            
       .
            
       410-VALIDATE-SSN.
	        MOVE SPACES TO W04-ERROR-DATA
            
            MOVE F01-EMPLOYEE-SSN TO W04-ERROR-DATA
            
                
            IF F01-EMPLOYEE-SSN IS NUMERIC
               CONTINUE
            ELSE
               
               IF F01-EMPLOYEE-SSN-X = SPACES
                    MOVE SPACES TO W04-ERROR-MSG
                    MOVE W02-SSN-ERR-MSG TO W04-ERROR-MSG
                
                    PERFORM 402-WRITE-ERROR
                END-IF
            END-IF
       .
       420-VALIDATE-HOURS-WORKED.
	      	MOVE SPACES TO W04-ERROR-MSG         
            MOVE SPACES TO W04-ERROR-DATA
            MOVE F01-HOURS-WORKED TO W04-ERROR-DATA
            
            IF F01-HOURS-WORKED IS NUMERIC
               INSPECT F01-HOURS-WORKED REPLACING LEADING SPACES BY "0"    
            END-IF
            
            IF F01-HOURS-WORKED NOT NUMERIC 
                
                MOVE W02-HOURS-NOT-NUMERIC-ERR TO W04-ERROR-MSG
                PERFORM 402-WRITE-ERROR                    
            ELSE
                IF   F01-SALARY-TYPE = "S" 
                   IF F01-OVERTIME-CHECK
                       CONTINUE
                   ELSE
                       MOVE W02-SALARY-OVER-40-ERR-MSG TO W04-ERROR-MSG
                       PERFORM 402-WRITE-ERROR
                   END-IF
                   
                END-IF
            END-IF
        .
        430-VALIDATE-DEPENDENTS.
           MOVE SPACES TO W04-ERROR-DATA
           MOVE SPACES TO W04-ERROR-MSG
          
           IF F01-DEPENDANTS NOT NUMERIC
               INSPECT F01-DEPENDANTS-X  REPLACING LEADING SPACES BY "0"
               MOVE F01-DEPENDANTS-X TO F01-DEPENDANTS
           END-IF
           
           IF F01-DEPENDANTS IS NUMERIC 
               IF NOT F01-DEPENDANTS-VALID
                   MOVE W02-DEPEND-OVER10-ERR TO W04-ERROR-MSG
                   MOVE F01-DEPENDANTS TO W04-ERROR-DATA
                   
                   PERFORM 402-WRITE-ERROR
               END-IF
           ELSE
                MOVE W02-DEPEND-NOT-NUMERIC-ERR TO W04-ERROR-MSG
                MOVE F01-DEPENDANTS-X TO W04-ERROR-DATA
                
                PERFORM 402-WRITE-ERROR
           END-IF
        .
        440-VALIDATE-INSURANCE.
            IF NOT F01-INSURANCE-CHECK 
               MOVE SPACES TO W04-ERROR-DATA
               MOVE SPACES TO W04-ERROR-MSG
                
               MOVE W02-INSURANCE-ERR TO W04-ERROR-MSG
               MOVE F01-INSURANCE TO W04-ERROR-DATA
               
               PERFORM 402-WRITE-ERROR
            END-IF
        .
        500-WRITE-FOOTER.
		    
             WRITE F03-ERROR-RECORD FROM W01-FOOTER
		        AFTER ADVANCING 2 LINES
        .
        600-CLOSE-FILES.
	        
	        CLOSE F01-EMPLOYEE-FILE
			        F02-VALID-FILE
			        F03-ERROR-FILE
        .
          
