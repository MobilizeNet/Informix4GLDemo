GLOBALS
    "d4_globals.4gl"


FUNCTION stock()
    MENU "STOCK"
	COMMAND "Add-stock" "Add new stock items to database" HELP 401
	    CALL add_stock()
	COMMAND "Find-stock" "Look up specific stock item" HELP 402
	    CALL query_stock()
	COMMAND "Update-stock" "Modify current stock information" HELP 403
	    CALL update_stock()
	COMMAND "Delete-stock" "Remove a stock item from database" HELP 404
	    CALL delete_stock()
	COMMAND "Exit" "Return to MAIN Menu" HELP 405
	    CLEAR SCREEN
	    EXIT MENU
    END MENU
END FUNCTION


FUNCTION add_stock()
    ERROR "This option has not been implemented" ATTRIBUTE (RED)
END FUNCTION


FUNCTION query_stock()
    ERROR "This option has not been implemented" ATTRIBUTE (RED)
END FUNCTION


FUNCTION update_stock()
    ERROR "This option has not been implemented" ATTRIBUTE (RED)
END FUNCTION


FUNCTION delete_stock()
    ERROR "This option has not been implemented" ATTRIBUTE (RED)
END FUNCTION

