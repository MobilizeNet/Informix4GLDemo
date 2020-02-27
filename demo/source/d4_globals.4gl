DATABASE stores7

GLOBALS
    DEFINE
        p_customer RECORD LIKE customer.*,
        p_orders RECORD 
                order_num LIKE orders.order_num,
                order_date LIKE orders.order_date,
                po_num LIKE orders.po_num,
	        ship_instruct LIKE orders.ship_instruct
            END RECORD,
        p_items ARRAY[10] OF RECORD
                item_num LIKE items.item_num,
                stock_num LIKE items.stock_num,
                manu_code LIKE items.manu_code,
                description LIKE stock.description,
                quantity LIKE items.quantity,
                unit_price LIKE stock.unit_price,
                total_price LIKE items.total_price
            END RECORD,
        p_stock ARRAY[30] OF RECORD
	        stock_num LIKE stock.stock_num,
	        manu_code LIKE manufact.manu_code,
	        manu_name LIKE manufact.manu_name,
	        description LIKE stock.description,
	        unit_price LIKE stock.unit_price,
	        unit_descr LIKE stock.unit_descr
	    END RECORD,
        p_state ARRAY[50] OF RECORD LIKE state.*,
        state_cnt, stock_cnt INTEGER,
        print_option CHAR(1)
END GLOBALS

