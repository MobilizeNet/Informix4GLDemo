GLOBALS
    "d4_globals.4gl"


FUNCTION customer()

    OPTIONS 
	FORM LINE 7
    OPEN FORM customer FROM "custform"
    DISPLAY FORM customer 
	ATTRIBUTE(MAGENTA)
    CALL ring_menu()
    CALL fgl_drawbox(3,32,3,42)
    CALL fgl_drawbox(3,61,8,7)
    CALL fgl_drawbox(11,61,8,7)
    LET p_customer.customer_num = NULL
    MENU "CUSTOMER"
	COMMAND "One-add" "Add a new customer to the database" HELP 201
	    CALL unring_menu()
	    CALL add_customer(FALSE)
	    call ring_menu ()
	COMMAND "Many-add" "Add several new customers to database" HELP 202
	    CALL unring_menu()
	    CALL add_customer(TRUE)
	    call ring_menu ()
	COMMAND "Find-cust" "Look up specific customer" HELP 203
	    call unring_menu ()
	    IF query_customer(23) THEN
		call ring_menu ()
		NEXT OPTION "Update-cust"
	    END IF
	    call ring_menu ()
	COMMAND "Update-cust" "Modify current customer information" HELP 204
	    CALL unring_menu()
	    CALL update_customer()
	    call ring_menu ()
    	    NEXT OPTION "Find-cust"
	COMMAND "Delete-cust" "Remove a customer from database" HELP 205
	    CALL unring_menu()
	    CALL delete_customer()
	    call ring_menu ()
    	    NEXT OPTION "Find-cust"
	COMMAND "Exit" "Return to MAIN Menu" HELP 206
	    CLEAR SCREEN
	    EXIT MENU
    END MENU
    OPTIONS 
	FORM LINE 3 
END FUNCTION


FUNCTION add_customer(repeat)
    DEFINE repeat INTEGER

    CALL clear_menu()
    MESSAGE "Press F1 or CTRL-F for field help; ",
	    "F2 or CTRL-Y to return to menu"
    IF repeat THEN
	WHILE input_cust()
	    ERROR "Customer data entered" ATTRIBUTE (GREEN)
	END WHILE
	CALL mess("Multiple insert completed - current screen values ignored", 23)
    ELSE
	IF input_cust() THEN
	    ERROR "Customer data entered" ATTRIBUTE (GREEN)
	ELSE
    	    CLEAR FORM
    	    LET p_customer.customer_num = NULL
            ERROR "Customer addition aborted" ATTRIBUTE (RED, REVERSE)
	END IF
    END IF
END FUNCTION


FUNCTION input_cust()

    DISPLAY "Press ESC to enter new customer data" AT 1,1
    INPUT BY NAME p_customer.*
        AFTER FIELD state
            CALL statehelp() 
    	    DISPLAY "Press ESC to enter new customer data", "" AT 1,1
	ON KEY (F1, CONTROL-F)
            CALL customer_help()
	ON KEY (F2, CONTROL-Y)
	    LET int_flag = TRUE
	    EXIT INPUT
    END INPUT
    IF int_flag THEN
	LET int_flag = FALSE
	RETURN(FALSE)
    END IF
    LET p_customer.customer_num = 0
    INSERT INTO customer VALUES (p_customer.*)
    LET p_customer.customer_num = SQLCA.SQLERRD[2]
    DISPLAY BY NAME p_customer.customer_num ATTRIBUTE(MAGENTA)
    RETURN(TRUE)
END FUNCTION


FUNCTION query_customer(mrow)
    DEFINE where_part CHAR(500),
           query_text CHAR(500),
           answer CHAR(1),
	   mrow, chosen, exist SMALLINT

    CLEAR FORM
    CALL clear_menu()

    MESSAGE "Enter criteria for selection"
    CONSTRUCT where_part ON customer.* FROM customer.*
    MESSAGE ""
    IF int_flag THEN
        LET int_flag = FALSE
	CLEAR FORM
        ERROR "Customer query aborted" ATTRIBUTE(RED, REVERSE)
	LET p_customer.customer_num = NULL
	RETURN (p_customer.customer_num)
    END IF
    LET query_text = "select * from customer where ", where_part CLIPPED,
			" order by lname"
    PREPARE statement_1 FROM query_text
    DECLARE customer_set SCROLL CURSOR FOR statement_1

    OPEN customer_set
    FETCH FIRST customer_set INTO p_customer.*
    IF status = NOTFOUND THEN
	LET exist = FALSE
    ELSE
        LET exist = TRUE
	DISPLAY BY NAME p_customer.* ATTRIBUTE(MAGENTA)
	MENU "BROWSE"
	    COMMAND "Next" "View the next customer in the list"
		FETCH NEXT customer_set INTO p_customer.*
		IF status = NOTFOUND THEN
		    ERROR "No more customers in this direction" ATTRIBUTE(RED, REVERSE)
		    FETCH LAST customer_set INTO p_customer.*
		END IF
		DISPLAY BY NAME p_customer.*  ATTRIBUTE(MAGENTA)
	    COMMAND "Previous" "View the previous customer in the list"
		FETCH PREVIOUS customer_set INTO p_customer.*
		IF status = NOTFOUND THEN
		    ERROR "No more customers in this direction" ATTRIBUTE(RED, REVERSE)
		    FETCH FIRST customer_set INTO p_customer.*
		END IF
		DISPLAY BY NAME p_customer.*  ATTRIBUTE(MAGENTA)
	    COMMAND "First" "View the first customer in the list"
		FETCH FIRST customer_set INTO p_customer.*
		DISPLAY BY NAME p_customer.*  ATTRIBUTE(MAGENTA)
	    COMMAND "Last" "View the last customer in the list"
		FETCH LAST customer_set INTO p_customer.*
		DISPLAY BY NAME p_customer.*  ATTRIBUTE(MAGENTA)
	    COMMAND "Select" "Exit BROWSE selecting the current customer"
		LET chosen = TRUE
		EXIT MENU
	    COMMAND "Quit" "Quit BROWSE without selecting a customer"
		LET chosen = FALSE
		EXIT MENU
	END MENU
    END IF
    CLOSE customer_set

    CALL clear_menu()
    IF NOT exist THEN
	CLEAR FORM
	CALL mess("No customer satisfies query", mrow)
        LET p_customer.customer_num = NULL
	RETURN (FALSE)
    END IF
    IF NOT chosen THEN
	CLEAR FORM
	LET p_customer.customer_num = NULL
	CALL mess("No selection made", mrow)
	RETURN (FALSE)
    END IF
    RETURN (TRUE)
END FUNCTION


FUNCTION update_customer()

    CALL clear_menu()
    IF p_customer.customer_num IS NULL THEN
	ERROR "No customer has been selected; use the Find-cust option"
	    ATTRIBUTE (RED, REVERSE)
	RETURN
    END IF
    MESSAGE "Press F1 or CTRL-F for field-level help"
    DISPLAY "Press ESC to update customer data; DEL to abort" AT 1,1
    INPUT BY NAME p_customer.* WITHOUT DEFAULTS
	AFTER FIELD state
	    CALL statehelp()
    	    DISPLAY "Press ESC to update customer data; DEL to abort", "" AT 1,1
	ON KEY (F1, CONTROL-F)
	    CALL customer_help()
        END INPUT
    IF NOT int_flag THEN
        UPDATE customer SET customer.* = p_customer.*
	    WHERE customer_num = p_customer.customer_num
        CALL mess("Customer data modified", 23)
    ELSE
	LET int_flag = FALSE
	SELECT * INTO p_customer.* FROM customer
	    WHERE customer_num = p_customer.customer_num
	DISPLAY BY NAME p_customer.* ATTRIBUTE(MAGENTA)
	ERROR "Customer update aborted" ATTRIBUTE (RED, REVERSE)
    END IF
END FUNCTION


FUNCTION delete_customer()
    DEFINE answer CHAR(1),
	   num_orders INTEGER

    CALL clear_menu()
    IF p_customer.customer_num IS NULL THEN
	ERROR "No customer has been selected; use the Find-cust option"
	    ATTRIBUTE (RED, REVERSE)
	RETURN
    END IF

    SELECT COUNT(*) INTO num_orders
	FROM orders
	WHERE customer_num = p_customer.customer_num
    IF num_orders THEN
	ERROR "This customer has active orders and can not be removed"
	    ATTRIBUTE (RED, REVERSE)
	RETURN
    END IF

    PROMPT " Are you sure you want to delete this customer row? "
	FOR CHAR answer
    IF answer MATCHES "[yY]" THEN
	DELETE FROM customer 
	    WHERE customer_num = p_customer.customer_num
	CLEAR FORM
	CALL mess("Customer entry deleted", 23)
	LET p_customer.customer_num = NULL
    ELSE
	ERROR "Deletion aborted" ATTRIBUTE (RED, REVERSE)
    END IF
END FUNCTION


FUNCTION customer_help()
    CASE
	WHEN infield(customer_num) CALL showhelp(1001)
	WHEN infield(fname) CALL showhelp(1002)
	WHEN infield(lname) CALL showhelp(1003)
	WHEN infield(company) CALL showhelp(1004)
	WHEN infield(address1) CALL showhelp(1005)
	WHEN infield(address2) CALL showhelp(1006)
	WHEN infield(city) CALL showhelp(1007)
	WHEN infield(state) CALL showhelp(1008)
	WHEN infield(zipcode) CALL showhelp(1009)
	WHEN infield(phone) CALL showhelp(1010)
    END CASE
END FUNCTION


FUNCTION statehelp()
    DEFINE idx INTEGER

    SELECT COUNT(*) INTO idx
        FROM state
        WHERE code = p_customer.state
    IF idx = 1 THEN
	RETURN
    END IF

    DISPLAY "Move cursor using F3, F4, and arrow keys; press ESC to select state" AT 1,1
    OPEN WINDOW w_state AT 8,37
        WITH FORM "state_list"
        ATTRIBUTE (BORDER, RED, FORM LINE 2)

    CALL set_count(state_cnt)
    DISPLAY ARRAY p_state TO s_state.*
    LET idx = arr_curr()

    CLOSE WINDOW w_state
    LET p_customer.state = p_state[idx].code
    DISPLAY BY NAME p_customer.state ATTRIBUTE(MAGENTA)
    RETURN
END FUNCTION

