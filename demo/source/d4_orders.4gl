GLOBALS
    "d4_globals.4gl"


FUNCTION orders()

    OPEN FORM order_form FROM "orderform"
    DISPLAY FORM order_form
	ATTRIBUTE(MAGENTA)
    MENU "ORDERS"
	COMMAND "Add-order" "Enter new order to database and print invoice"
	        HELP 301
	    CALL add_order()
	COMMAND "Update-order" "Enter shipping or payment data" HELP 302
	    CALL update_order()
	COMMAND "Find-order" "Look up and display orders" HELP 303
	    CALL get_order()
	COMMAND "Delete-order" "Remove an order from the database" HELP 304
	    CALL delete_order()
	COMMAND "Exit" "Return to MAIN Menu" HELP 305
	    CLEAR SCREEN
	    EXIT MENU
    END MENU
END FUNCTION


FUNCTION add_order()
    DEFINE pa_curr, s_curr, num_stocks INTEGER,
	   file_name CHAR(20),
	   query_stat INTEGER

    LET query_stat = query_customer(2)
    IF query_stat IS NULL THEN
       RETURN 
    END IF
    IF NOT query_stat THEN
	OPEN WINDOW cust_w AT 3,5
	    WITH 19 ROWS, 72 COLUMNS
	    ATTRIBUTE(BORDER, YELLOW) 
	OPEN FORM o_cust FROM "custform"
	DISPLAY FORM o_cust
	    ATTRIBUTE(MAGENTA)
	CALL fgl_drawbox(3,61,4,7)
	CALL fgl_drawbox(11,61,4,7)
	CALL add_customer(FALSE)
	CLOSE FORM o_cust
	CLOSE WINDOW cust_w
	IF p_customer.customer_num IS NULL THEN
	    RETURN
	END IF
    END IF
    DISPLAY by name p_customer.* ATTRIBUTE(CYAN)

    MESSAGE "Enter the order date, PO number and shipping instructions."
    INPUT BY NAME p_orders.order_date, p_orders.po_num, p_orders.ship_instruct
    IF int_flag THEN
	LET int_flag = FALSE
	CLEAR FORM
	ERROR "Order input aborted" ATTRIBUTE (RED, REVERSE)
	RETURN
    END IF
    INPUT ARRAY p_items FROM s_items.* HELP 311
        BEFORE FIELD stock_num
            MESSAGE "Press ESC to write order"
	    DISPLAY "Enter a stock number or press CTRL-B to scan stock list"
		AT 1,1
        BEFORE FIELD manu_code
            MESSAGE "Enter the code for a manufacturer"
        BEFORE FIELD quantity
	    DISPLAY "" AT 1,1
            MESSAGE "Enter the item quantity"
	ON KEY (CONTROL-B)
   	    IF INFIELD(stock_num) OR INFIELD(manu_code) THEN
                LET pa_curr = arr_curr()
                LET s_curr = scr_line()
   	        CALL get_stock() RETURNING 
		    p_items[pa_curr].stock_num, p_items[pa_curr].manu_code, 
           	    p_items[pa_curr].description, p_items[pa_curr].unit_price 
                DISPLAY p_items[pa_curr].stock_num TO s_items[s_curr].stock_num
                DISPLAY p_items[pa_curr].manu_code TO s_items[s_curr].manu_code
                DISPLAY p_items[pa_curr].description TO s_items[s_curr].description
                DISPLAY p_items[pa_curr].unit_price TO s_items[s_curr].unit_price
                NEXT FIELD quantity
            END IF
        AFTER FIELD stock_num, manu_code
            LET pa_curr = arr_curr()
            IF p_items[pa_curr].stock_num IS NOT NULL
                AND p_items[pa_curr].manu_code IS NOT NULL 
            THEN
                CALL get_item()
            END IF
        AFTER FIELD quantity
            MESSAGE ""
            LET pa_curr = arr_curr()
            IF p_items[pa_curr].unit_price IS NOT NULL
                AND p_items[pa_curr].quantity IS NOT NULL
            THEN
                CALL item_total()
	    ELSE
		ERROR "A valid stock code, manufacturer, and quantity must all be entered" ATTRIBUTE (RED, REVERSE)
		NEXT FIELD stock_num
            END IF
        AFTER INSERT, DELETE
            CALL renum_items()
	    CALL order_total()
	AFTER ROW
	    CALL order_total()
    END INPUT 
    IF int_flag THEN
	LET int_flag = FALSE
	CLEAR FORM
	ERROR "Order input aborted" ATTRIBUTE (RED, REVERSE)
	RETURN
    END IF

    WHENEVER ERROR CONTINUE
    BEGIN WORK
    INSERT INTO orders (order_num, order_date, customer_num, 
	    ship_instruct, po_num)
        VALUES (0, p_orders.order_date, p_customer.customer_num,
            p_orders.ship_instruct, p_orders.po_num)
    IF status < 0 THEN
	ROLLBACK WORK
	ERROR "Unable to complete update of orders table" 
	    ATTRIBUTE(RED, REVERSE, BLINK)
	RETURN
    END IF
    LET p_orders.order_num = SQLCA.SQLERRD[2]
    DISPLAY BY NAME p_orders.order_num 
    IF NOT insert_items() THEN
	ROLLBACK WORK
	ERROR "Unable to insert items" ATTRIBUTE(RED, REVERSE, BLINK)
	RETURN
    END IF
    COMMIT WORK
    WHENEVER ERROR STOP
    CALL mess("Order added", 23)
    LET file_name = "inv", p_orders.order_num USING "<<<<&",".out" 
    CALL invoice(file_name)
    CLEAR FORM
END FUNCTION


FUNCTION update_order()

    ERROR "This option has not been implemented" ATTRIBUTE (RED)
END FUNCTION


FUNCTION delete_order()

    ERROR "This option has not been implemented" ATTRIBUTE (RED)
END FUNCTION


FUNCTION order_total()
    DEFINE order_total MONEY(8),
	   i INTEGER

    LET order_total = 0.00
    FOR i = 1 TO ARR_COUNT()
	IF p_items[i].total_price IS NOT NULL THEN
	    LET order_total = order_total + p_items[i].total_price
	END IF
    END FOR
    LET order_total = 1.1 * order_total
    DISPLAY order_total TO t_price
	ATTRIBUTE(GREEN)
END FUNCTION


FUNCTION item_total()
    DEFINE pa_curr, sc_curr INTEGER

    LET pa_curr = arr_curr()
    LET sc_curr = scr_line()
    LET p_items[pa_curr].total_price =
        p_items[pa_curr].quantity * p_items[pa_curr].unit_price
    DISPLAY p_items[pa_curr].total_price TO s_items[sc_curr].total_price
END FUNCTION


FUNCTION renum_items()
    DEFINE pa_curr, pa_total, sc_curr, sc_total, k INTEGER

    LET pa_curr = arr_curr()
    LET pa_total = arr_count()
    LET sc_curr = scr_line()
    LET sc_total = 4
    FOR k = pa_curr TO pa_total
        LET p_items[k].item_num = k
        IF sc_curr <= sc_total THEN
            DISPLAY k TO s_items[sc_curr].item_num
            LET sc_curr = sc_curr + 1
        END IF
    END FOR
END FUNCTION


FUNCTION insert_items()
    DEFINE idx INTEGER

    FOR idx = 1 TO arr_count()
	IF p_items[idx].quantity != 0 THEN
            INSERT INTO items 
                VALUES (p_items[idx].item_num, p_orders.order_num,
                    p_items[idx].stock_num, p_items[idx].manu_code,
                    p_items[idx].quantity, p_items[idx].total_price)
            IF status < 0 THEN
	        RETURN (FALSE)
            END IF
        END IF
    END FOR
    RETURN (TRUE)
END FUNCTION


FUNCTION get_stock()
    DEFINE idx integer

    OPEN WINDOW stock_w AT 7, 3
	WITH FORM "stock_sel"
       	ATTRIBUTE(BORDER, YELLOW)
    CALL set_count(stock_cnt)
    DISPLAY " Use cursor using F3, F4, and arrow keys; press ESC to select a stock item" AT 1,1
    DISPLAY ARRAY p_stock TO s_stock.* 
    LET idx = arr_curr()
    CLOSE WINDOW stock_w
    RETURN p_stock[idx].stock_num, p_stock[idx].manu_code,
	   p_stock[idx].description, p_stock[idx].unit_price
END FUNCTION


FUNCTION get_order()
    DEFINE idx, exist, chosen INTEGER,
           answer CHAR(1)

    CALL clear_menu()
    CLEAR FORM
    IF NOT query_customer(2) THEN
       RETURN
    END IF

    DECLARE order_list CURSOR FOR
        SELECT order_num, order_date, po_num, ship_instruct
            FROM orders
            WHERE customer_num = p_customer.customer_num    
    LET exist = FALSE
    LET chosen = FALSE
    FOREACH order_list INTO p_orders.*
        LET exist = TRUE
        CLEAR orders.*
        FOR idx = 1 TO 4
            CLEAR s_items[idx].*
        END FOR
        DISPLAY p_orders.* TO orders.*
        DECLARE item_list CURSOR FOR
            SELECT item_num, items.stock_num, items.manu_code, 
		   description, quantity, unit_price, total_price
                FROM items, stock
                WHERE order_num = p_orders.order_num
                    AND items.stock_num = stock.stock_num 
                    AND items.manu_code = stock.manu_code
                ORDER BY item_num
        LET idx = 1
        FOREACH item_list INTO p_items[idx].*
            LET idx = idx + 1
            IF idx > 10 THEN
                ERROR "More than 10 items; only 10 items displayed"
		    ATTRIBUTE (RED, REVERSE)
                EXIT FOREACH
            END IF
        END FOREACH
        CALL set_count(idx - 1)
	CALL order_total()
        MESSAGE "Press ESC when you finish viewing the items"
        DISPLAY ARRAY p_items TO s_items.*
	    ATTRIBUTE(CYAN)
        MESSAGE ""
	IF int_flag THEN
	    LET int_flag = FALSE
	    EXIT FOREACH
	END IF
        PROMPT " Enter 'y' to select this order ", 
               "or RETURN to view next order: " FOR CHAR answer
        IF answer MATCHES "[yY]" THEN
            LET chosen = TRUE
            EXIT FOREACH
        END IF
    END FOREACH

    IF NOT exist THEN
        ERROR "No orders found for this customer" ATTRIBUTE (RED)
    ELSE
        IF NOT chosen THEN
            CLEAR FORM
            ERROR "No order selected for this customer" ATTRIBUTE (RED)
        END IF
    END IF
END FUNCTION


FUNCTION get_item()
    DEFINE pa_curr, sc_curr INTEGER

    LET pa_curr = arr_curr()
    LET sc_curr = scr_line()
    SELECT description, unit_price
        INTO p_items[pa_curr].description,
             p_items[pa_curr].unit_price
        FROM stock
        WHERE stock.stock_num = p_items[pa_curr].stock_num
            AND stock.manu_code = p_items[pa_curr].manu_code
    IF status THEN
	LET p_items[pa_curr].description = NULL
	LET p_items[pa_curr].unit_price = NULL
    END IF
    DISPLAY p_items[pa_curr].description, p_items[pa_curr].unit_price
        TO s_items[sc_curr].description, s_items[sc_curr].unit_price
    IF p_items[pa_curr].quantity IS NOT NULL THEN
        CALL item_total()
    END IF
END FUNCTION


FUNCTION invoice(file_name)
    DEFINE x_invoice RECORD
            order_num		LIKE orders.order_num,
            order_date		LIKE orders.order_date,
            ship_instruct	LIKE orders.ship_instruct,
            backlog		LIKE orders.backlog,
            po_num		LIKE orders.po_num,
            ship_date		LIKE orders.ship_date,
            ship_weight		LIKE orders.ship_weight,
            ship_charge		LIKE orders.ship_charge,
            item_num		LIKE items.item_num,
            stock_num		LIKE items.stock_num,
            manu_code		LIKE items.manu_code,
            quantity		LIKE items.quantity,
            total_price		LIKE items.total_price,
            description		LIKE stock.description,
            unit_price		LIKE stock.unit_price,
            unit		LIKE stock.unit,
            unit_descr		LIKE stock.unit_descr,
            manu_name		LIKE manufact.manu_name
        END RECORD,
    file_name CHAR(20),
    msg CHAR(40)

    DECLARE invoice_data CURSOR FOR 
	SELECT o.order_num,order_date,ship_instruct,backlog,po_num,ship_date,
	       ship_weight,ship_charge,
               item_num,i.stock_num,i.manu_code,quantity,total_price,
               s.description,unit_price,unit,unit_descr,
               manu_name
        FROM orders o,items i,stock s,manufact m
        WHERE 
	    ((o.order_num=p_orders.order_num) AND
	     (i.order_num=p_orders.order_num) AND
             (i.stock_num=s.stock_num AND
              i.manu_code=s.manu_code) AND
             (i.manu_code=m.manu_code))
        ORDER BY 9
    CASE (print_option)
	WHEN "f"
            START REPORT r_invoice TO file_name
            CALL clear_menu()
            MESSAGE "Writing invoice -- please wait"
	WHEN "p"
	    START REPORT r_invoice TO PRINTER
            CALL clear_menu()
            MESSAGE "Writing invoice -- please wait"
	WHEN "s"
	    START REPORT r_invoice 
    END CASE
    FOREACH invoice_data INTO x_invoice.* 
        OUTPUT TO REPORT r_invoice (p_customer.*, x_invoice.*)
    END FOREACH
    FINISH REPORT r_invoice
    IF print_option = "f" THEN
        LET msg = "Invoice written to file ", file_name CLIPPED
        CALL mess(msg, 23)
    END IF
END FUNCTION


REPORT r_invoice (c, x)
    DEFINE c RECORD LIKE customer.*,
        x RECORD
                order_num	LIKE orders.order_num,
                order_date	LIKE orders.order_date,
                ship_instruct	LIKE orders.ship_instruct,
                backlog		LIKE orders.backlog,
                po_num		LIKE orders.po_num,
                ship_date	LIKE orders.ship_date,
                ship_weight	LIKE orders.ship_weight,
                ship_charge	LIKE orders.ship_charge,
                item_num	LIKE items.item_num,
                stock_num	LIKE items.stock_num,
                manu_code	LIKE items.manu_code,
                quantity	LIKE items.quantity,
                total_price	LIKE items.total_price,
                description	LIKE stock.description,
                unit_price	LIKE stock.unit_price,
                unit		LIKE stock.unit,
                unit_descr	LIKE stock.unit_descr,
                manu_name	LIKE manufact.manu_name
            END RECORD,
        sales_tax, calc_total MONEY(8,2)

OUTPUT
    LEFT MARGIN 0
    RIGHT MARGIN 0
    TOP MARGIN 1
    BOTTOM MARGIN 1
    PAGE LENGTH 48

FORMAT
    BEFORE GROUP OF x.order_num
	SKIP TO TOP OF PAGE
	SKIP 1 LINE
	PRINT 10 SPACES,
              "   W E S T   C O A S T   W H O L E S A L E R S ,   I N C ."
	PRINT 30 SPACES," 1400 Hanbonon Drive"
	PRINT 30 SPACES,"Menlo Park, CA  94025"
	SKIP 1 LINES
	PRINT "Bill To:", COLUMN 10,c.fname CLIPPED, " ", c.lname CLIPPED;
	PRINT COLUMN 56,"Invoice No.        ",x.order_num USING "&&&&&"
	PRINT COLUMN 10,c.company
	PRINT COLUMN 10,c.address1 CLIPPED;
	PRINT COLUMN 56,"Invoice Date: ", x.order_date 
	PRINT COLUMN 10,c.address2 CLIPPED;
	PRINT COLUMN 56,"Customer No.       ", c.customer_num USING "####&"
	PRINT COLUMN 10,c.city CLIPPED,", ",c.state CLIPPED,"   ",
	      c.zipcode CLIPPED;
	PRINT COLUMN 56,"PO No. ",x.po_num
	PRINT COLUMN 10,c.phone CLIPPED;
	PRINT COLUMN 56,"Backlog Status: ",x.backlog
	SKIP 1 LINES
	PRINT COLUMN 10,"Shipping Instructions: ", x.ship_instruct
	PRINT COLUMN 10,"Ship Date: ",x.ship_date USING "ddd. mmm dd, yyyy";
	PRINT "   Weight: ", x.ship_weight USING "#####&.&&"
	SKIP 1 LINES
	PRINT "----------------------------------------";
	PRINT "---------------------------------------"
	PRINT "   Stock                              Unit        ";
	PRINT "                       Item "
	PRINT " #  Num Man    Description      Qty   Cost   Unit ";
	PRINT " Unit Description      Total"
	SKIP 1 LINES
	LET calc_total = 0.00

    ON EVERY ROW
	PRINT x.item_num USING "#&","  ",
	      x.stock_num USING "&&", "  ",x.manu_code;
	PRINT "  ",x.description,"  ",x.quantity USING "###&", "  ";
	PRINT x.unit_price USING "$$$&.&&","  ",x.unit, "  ",x.unit_descr,"  ";
	PRINT x.total_price USING "$$$$$$$&.&&"
	LET calc_total = calc_total + x.total_price

    AFTER GROUP OF x.order_num
	SKIP 1 LINES
	PRINT "----------------------------------------";
	PRINT "---------------------------------------"
	PRINT COLUMN 50, "        Sub-total: ",calc_total USING "$$$$$$$&.&&"
	LET sales_tax = 0.065 * calc_total
	LET x.ship_charge = 0.035 * calc_total
	PRINT COLUMN 45, "Shipping Charge (3.5%): ",
	      x.ship_charge USING "$$$$$$$&.&&"
	PRINT COLUMN 50, " Sales Tax (6.5%): ",sales_tax USING "$$$$$$$&.&&"
	PRINT COLUMN 50, "                   -----------"
	LET calc_total = calc_total + x.ship_charge + sales_tax
	PRINT COLUMN 50, "            Total: ",calc_total USING "$$$$$$$&.&&"
	IF print_option = "s" THEN
	    PAUSE "Type RETURN to continue"
        END IF
END REPORT

