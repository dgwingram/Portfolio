       IDENTIFICTION DIVISION.
       PROGRAM-ID. StringEx.
       AUTHOR. Daniel Ingram ish.

       ENVIORNMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
         SELECT F01-INPUT-FILE ASSIGN TO 'looneyTunes.dat'
                     ORGANIZATION IS LINE SEQUENTIAL
         SELECT F02-PRINT-FILE ASSIGN TO 'looney.out'
                     ORGANIZATION IS LINE SEQUENTIAL

       DATA DIVISION.
       FILE SECTION.
       FD F01-INPUT-FILE
           RECORD CONTAINS 78 CHARACTERS.

       01 F01-ADR-RECORD.
           05 F01-FULL-NAME.
               10 F01-FIRST-NAME PIC X(10).
               10 F01-LAST-NAME PIC X(20).
           05 F01-STREET PIC X(30).
           05 F01-CITY PIC X(10).
           05 F01-PROV PIC X(2).
           05 F01-POSTCD PIC X(6).

       FD F02-PRINT-FILE
           RECORD CONTAINS 30 CHARACTERS.
       01 F02-PRINT-RECORD PIC X(31).

       WORKING-STORAGE SECTION.
    
       01 W01-DATA-REMAINS-SWITCH PIC XXX VALUE 'YES'.
      * CREATE NEEDED VARIABLES NOT ALREADY GIVEN
       01  W02-OUTPUT-LINE
           05  W02-NAME-LINE       PIC X(31)
           05  W02-LAST-LINE       PIC X(21)


       PROCEDURE DIVISION.
            OPEN INPUT F01-INPUT-FILE
                 OUTPUT F02-PRINT-FILE
     
            READ F01-INPUT-FILE
                 AT END MOVE 'NO' TO W01-DATA-REMAINS-SWITCH
            END-READ

            PERFORM 100-PROCESS-RECORDS
                   UNTIL W01-DATA-REMAINS-SWITCH='NO' 
     
            CLOSE F01-INPUT-FILE
                  F02-PRINT-FILE

            STOP RUN
            .
       100-PROCESS-RECORDS.
      * COMPLETE EXERCISE CODE HERE:
           MOVE SPACES TO W02-OUTPUT-LINE
    
           STRING F01-FIRST-NAME DELIMITED BY "  "
                  " "            DELIMITED BY SIZE
                  F01-LAST-NAME  DELIMITED BY "  "
               INTO W02-NAME-LINE
           END-STRING
    
           STRING F01-CITY       DELIMITED BY "  "
                  ", "            DELIMITED BY SIZE
                  F01-LAST-NAME  DELIMITED BY "  "
               INTO W02-LAST-LINE
           END-STRING
    
           MOVE W02-NAME-LINE TO F02-PRINT-RECORD 
           WRITE F02-PRINT-RECORD
    
           MOVE F01-STREET TO FO2-PRINT-RECORD
           WRITE F02-PRINT-RECORD
    
           MOVE W02-LAST-LINE TO F02-PRINT-RECORD
           WRITE F02-PRINT-RECORD
               BEFORE ADVANCING 2 LINES
               
      *EXERCISE CODE FINISHED
      
            READ F01-INPUT-FILE
                 AT END MOVE 'NO' TO W01-DATA-REMAINS-SWITCH
            END-READ
            . 
