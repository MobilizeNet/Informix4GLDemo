GLOBALS
    "d4_globals.4gl"


MAIN

    DEFER INTERRUPT
    OPTIONS
	HELP FILE "helpdemo"
    LET print_option = "s"
    CALL get_states()
    CALL get_stocks()

    CALL ring_menu()
    MENU "MAIN"
	COMMAND "Customer" "Enter and maintain customer data" HELP 101
	    CALL customer()
	    CALL ring_menu()
	COMMAND "Orders" "Enter and maintain orders" HELP 102
	    CALL orders()
	    CALL ring_menu()
	COMMAND "Stock" "Enter and maintain stock list" HELP 103
	    CALL stock()
	    CALL ring_menu()
	COMMAND "Reports" "Print reports and mailing labels" HELP 104
	    CALL reports()
	    CALL ring_menu()
	COMMAND key("!")
	    CALL bang()
	    CALL ring_menu()
	    NEXT OPTION "Customer"
        COMMAND key("X")
	    CALL demo()
	    CALL ring_menu()
	    NEXT OPTION "Customer"
	COMMAND "Exit" "Exit program and return to operating system" HELP 105
	    CLEAR SCREEN
	    EXIT PROGRAM
    END MENU
END MAIN


FUNCTION bang()
    DEFINE cmd CHAR(80),
	   x CHAR(1)

    CALL clear_menu()
    LET x = "!"
    WHILE x = "!"
       PROMPT "!" FOR cmd
       RUN cmd
       PROMPT "Type RETURN to continue." FOR CHAR x
    END WHILE
END FUNCTION


FUNCTION mess(str, mrow)
    DEFINE str CHAR(80),
	   mrow SMALLINT

    DISPLAY " ", str CLIPPED AT mrow,1
    SLEEP 3
    DISPLAY "" AT mrow,1
END FUNCTION


FUNCTION ring_menu()

    DISPLAY "---------------------------------------   ",
	    "Type Control-W for MENU HELP   -----" AT 4,2 ATTRIBUTE(MAGENTA)
END FUNCTION


FUNCTION unring_menu()

    DISPLAY "---------------------------------------   ",
	    "                               -----" AT 4,2 ATTRIBUTE(MAGENTA)
END FUNCTION


FUNCTION clear_menu()

    DISPLAY "" AT 1,1
    DISPLAY "" AT 2,1
END FUNCTION


FUNCTION get_states()

    DECLARE c_state CURSOR FOR 
	SELECT * FROM state 
	ORDER BY sname
    LET state_cnt = 1
    FOREACH c_state INTO p_state[state_cnt].*
        LET state_cnt = state_cnt + 1
        IF state_cnt > 50 THEN
            EXIT FOREACH
        END IF
    END FOREACH
    LET state_cnt = state_cnt - 1
END FUNCTION


FUNCTION get_stocks()

    DECLARE stock_list CURSOR FOR 
	SELECT stock_num, manufact.manu_code,
		manu_name, description, unit_price, unit_descr
	FROM stock, manufact
	WHERE stock.manu_code = manufact.manu_code
	ORDER BY stock_num
    LET stock_cnt = 1
    FOREACH stock_list INTO p_stock[stock_cnt].*
        LET stock_cnt = stock_cnt + 1
	IF stock_cnt > 30 THEN
	    EXIT FOREACH
	END IF
    END FOREACH
    LET stock_cnt = stock_cnt - 1
END FUNCTION

