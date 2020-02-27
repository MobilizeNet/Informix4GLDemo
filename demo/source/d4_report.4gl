GLOBALS
    "d4_globals.4gl"


FUNCTION reports()
    CALL ring_menu()
    MENU "REPORTS"
	COMMAND "Labels" "Print mailing labels from customer list" 
		HELP 501
	    CALL unring_menu()
	    CALL print_labels()
	    CLEAR SCREEN
	    CALL ring_menu()
	COMMAND "Accounts-receivable" "Print current unpaid orders" HELP 502
	    CALL unring_menu()
	    CALL print_ar()
	    CLEAR SCREEN
	    CALL ring_menu()
	COMMAND "Backlog" "Print backlogged orders" HELP 503
	    CALL unring_menu()
	    CALL print_backlog()
	    CALL ring_menu()
	COMMAND "Stock-list" "Print stock available" HELP 504
	    CALL unring_menu()
	    CALL print_stock()
	    CALL ring_menu()
        COMMAND "Options" "Change the report output options" HELP 505
	    CALL unring_menu()
	    CALL update_options()
	    CALL ring_menu()
	COMMAND "Exit" "Return to MAIN Menu" HELP 506
	    CLEAR SCREEN
	    EXIT MENU
    END MENU
END FUNCTION


FUNCTION print_labels()
    DEFINE where_part CHAR(500),
	   query_text CHAR(500),
	   msg CHAR(50),
           file_name CHAR(20)

    OPTIONS
	FORM LINE 7
    OPEN FORM customer FROM "custform"
    DISPLAY FORM customer 
	ATTRIBUTE(MAGENTA)
    CALL fgl_drawbox(3,32,3,42)
    CALL fgl_drawbox(3,61,8,7)
    CALL fgl_drawbox(11,61,8,7)
    CALL clear_menu()
    DISPLAY "CUSTOMER LABELS:" AT 1,1
    MESSAGE "Use query-by-example to select customer list"
    CONSTRUCT BY NAME where_part ON customer.*
    IF int_flag THEN
	LET int_flag = FALSE
	ERROR "Label print request aborted" ATTRIBUTE(RED, REVERSE)
	RETURN
    END IF
    MESSAGE ""
    LET query_text = "select * from customer where ", where_part CLIPPED,
	     " order by zipcode"
    PREPARE label_st FROM query_text
    DECLARE label_list CURSOR FOR label_st 
    CASE (print_option)
	WHEN "f"
            PROMPT " Enter file name for labels >" FOR file_name
	    IF file_name IS NULL THEN
		LET file_name = "labels.out"
	    END IF
            MESSAGE "Printing mailing labels to ", file_name CLIPPED,
		    " -- Please wait"
            START REPORT labels_report TO file_name
	WHEN "p"
	    MESSAGE "Printing mailing labels -- Please wait"
	    START REPORT labels_report TO PRINTER
	WHEN "s"
	    START REPORT labels_report 
            CLEAR SCREEN
    END CASE
    FOREACH label_list INTO p_customer.*
        OUTPUT TO REPORT labels_report (p_customer.*)
	IF int_flag THEN
	    LET int_flag = FALSE
	    EXIT FOREACH
        END IF
    END FOREACH
    FINISH REPORT labels_report
    IF int_flag THEN
	LET int_flag = FALSE
	ERROR "Label printing aborted" ATTRIBUTE (RED, REVERSE)
	RETURN
    END IF
    IF print_option = "f" THEN
        LET msg = "Labels printed to ", file_name CLIPPED
        CALL mess(msg, 23)
    END IF
    CLOSE FORM customer
    OPTIONS
	FORM LINE 3
END FUNCTION


REPORT labels_report (rl)
     DEFINE rl RECORD LIKE customer.*

OUTPUT
    TOP MARGIN 0         
    BOTTOM MARGIN 0     
    PAGE LENGTH 6

FORMAT
    ON EVERY ROW
    SKIP TO TOP OF PAGE
        PRINT rl.fname CLIPPED, 1 SPACE, rl.lname
        PRINT rl.company
        PRINT rl.address1
        IF rl.address2 IS NOT NULL THEN
	    PRINT rl.address2
        END IF
        PRINT rl.city CLIPPED, ", ", rl.state, 2 SPACES, rl.zipcode
    IF print_option = "s" THEN
	PAUSE "Type RETURN to continue"
    END IF
END REPORT


FUNCTION print_ar()
    DEFINE r RECORD
	    customer_num	LIKE customer.customer_num,
	    fname 		LIKE customer.fname,
	    lname 		LIKE customer.lname,
	    company 		LIKE customer.company,
	    order_num 		LIKE orders.order_num,
	    order_date 		LIKE orders.order_date,
	    ship_date 		LIKE orders.ship_date,
	    paid_date 		LIKE orders.paid_date,
	    total_price 	LIKE items.total_price
        END RECORD,
    file_name CHAR(20),
    msg CHAR(50)

    DECLARE ar_list CURSOR FOR
	SELECT customer.customer_num,fname,lname,company,
	       orders.order_num,order_date,ship_date,paid_date,
               total_price
	    FROM customer,orders,items
	    WHERE customer.customer_num=orders.customer_num AND
		  paid_date IS NULL AND
		  orders.order_num=items.order_num
            ORDER BY 1,5

    CALL clear_menu()
    CASE (print_option)
	WHEN "f"
            PROMPT " Enter file name for AR Report >" FOR file_name
	    IF file_name IS NULL THEN
		LET file_name = "ar.out"
	    END IF
            MESSAGE "Printing AR REPORT to ", file_name CLIPPED,
		    " -- Please wait"
            START REPORT ar_report TO file_name
	WHEN "p"
	    MESSAGE "Printing AR REPORT -- Please wait"
	    START REPORT ar_report TO PRINTER
	WHEN "s"
	    START REPORT ar_report 
            CLEAR SCREEN
	    MESSAGE "Printing AR REPORT -- Please wait"
    END CASE
    FOREACH ar_list INTO r.*
        OUTPUT TO REPORT ar_report (r.*)
	IF int_flag THEN
	    LET int_flag = FALSE
	    EXIT FOREACH
        END IF
    END FOREACH
    FINISH REPORT ar_report
    IF int_flag THEN
	LET int_flag = FALSE
	ERROR "AR REPORT printing aborted" ATTRIBUTE (RED, REVERSE)
	RETURN
    END IF
    IF print_option = "f" THEN
        LET msg = "AR REPORT printed to ", file_name CLIPPED
        CALL mess(msg, 23)
    END IF
END FUNCTION


REPORT ar_report (r)
    DEFINE r RECORD 
	    customer_num	LIKE customer.customer_num,
	    fname 		LIKE customer.fname,
	    lname 		LIKE customer.lname,
	    company 		LIKE customer.company,
	    order_num 		LIKE orders.order_num,
	    order_date 		LIKE orders.order_date,
	    ship_date 		LIKE orders.ship_date,
	    paid_date 		LIKE orders.paid_date,
	    total_price 	LIKE items.total_price
        END RECORD,
    name_text CHAR(80)

OUTPUT 
    PAGE LENGTH 22
    LEFT MARGIN 0

FORMAT
    PAGE HEADER
	PRINT 15 SPACES,"West Coast Wholesalers, Inc."
	PRINT 6 SPACES,
	      "Statement of ACCOUNTS RECEIVABLE - ",
	      TODAY USING "mmm dd, yyyy"
	SKIP 1 LINES
        LET name_text = r.fname CLIPPED," ",r.lname CLIPPED,"/",
			r.company CLIPPED
        PRINT 29 - length(name_text)/2 SPACES, name_text
	SKIP 1 LINES
	PRINT " Order Date   Order Number    Ship Date            Amount"
	PRINT "----------------------------------------------------------"

    BEFORE GROUP OF r.customer_num
	SKIP TO TOP OF PAGE

    AFTER GROUP OF r.order_num
	NEED 3 LINES
	PRINT " ",r.order_date,7 SPACES,r.order_num USING "###&",8 SPACES,
	      r.ship_date,"  ", 
	      GROUP SUM(r.total_price) USING "$$$$,$$$,$$$.&&"

    AFTER GROUP OF r.customer_num
	PRINT 42 SPACES,"----------------"
	PRINT 42 SPACES,GROUP SUM(r.total_price) USING "$$$$,$$$,$$$.&&"

    PAGE TRAILER
        IF print_option = "s" THEN
	    PAUSE "Type RETURN to continue"
        END IF
END REPORT


FUNCTION update_options()
    DEFINE po CHAR(2)

    DISPLAY "Current print option:" AT 8,25
    LET po = " ", print_option
    DISPLAY po AT 8,46 ATTRIBUTE(CYAN)
    MENU "REPORT OPTIONS" 
	COMMAND "File" "Send all reports to a file"
	    LET print_option = "f"
	    EXIT MENU
	COMMAND "Printer" "Send all reports to the printer"
	    LET print_option = "p"
	    EXIT MENU
	COMMAND "Screen" "Send all reports to the terminal screen"
	    LET print_option = "s"
	    EXIT MENU
	COMMAND "Exit" "Return to REPORT Menu" 
	    EXIT MENU
    END MENU
    DISPLAY "" AT 8,1
END FUNCTION


FUNCTION print_backlog()
    ERROR "This option has not been implemented" ATTRIBUTE (RED)
END FUNCTION


FUNCTION print_stock()
    ERROR "This option has not been implemented" ATTRIBUTE (RED)
END FUNCTION

