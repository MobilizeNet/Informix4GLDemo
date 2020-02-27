FUNCTION demo()

    CALL ring_menu()
    MENU "DEMO" 
	COMMAND "Menus" "Source code for MAIN Menu"
	    CALL showhelp(2001)
	COMMAND "Windows" "Source code for STATE CODE Window"
	    CALL showhelp(2007)
        COMMAND "Forms" "Source code for new CUSTOMER data entry"
	    CALL showhelp(2006)
	COMMAND "Detail-Scrolling" "Source code for scrolling of new ORDER line-items"
	    CALL showhelp(2003)
	COMMAND "Scroll-Cursor" "Source code for customer record BROWSE/SCROLL"
	    CALL showhelp(2008)
	COMMAND "Query_language" "Source code for new order insertion using SQL"
	    CALL showhelp(2004)
	COMMAND "Construct_query" 
	  "Source code for QUERY-BY-EXAMPLE selection and reporting"
	    CALL showhelp(2002)
	COMMAND "Reports" "Source code for MAILING LABEL report"
	    CALL showhelp(2005)
	COMMAND "Exit" "Return to MAIN MENU" 
	    CLEAR SCREEN
	    EXIT MENU
    END MENU
END FUNCTION
