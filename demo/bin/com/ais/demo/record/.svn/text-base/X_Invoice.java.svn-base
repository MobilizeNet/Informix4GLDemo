package com.ais.demo.record;
// Import declaration block
import com.ais.fc.ix.lang.*;
import com.ais.fc.ix.lang.blob.*;

public  class X_Invoice extends Record{
  //Declaration LIKE orders.order_num
  public int order_num = 0;
  //Declaration LIKE orders.order_date
  public Date order_date = new Date();
  //Declaration LIKE orders.ship_instruct
  public Char ship_instruct = new Char(40);
  //Declaration LIKE orders.backlog
  public Char backlog = new Char(1);
  //Declaration LIKE orders.po_num
  public Char po_num = new Char(10);
  //Declaration LIKE orders.ship_date
  public Date ship_date = new Date();
  //Declaration LIKE orders.ship_weight
  public Decimal ship_weight = new Decimal(8, 2);
  //Declaration LIKE orders.ship_charge
  public Decimal ship_charge = new Money(6, 2);
  //Declaration LIKE items.item_num
  public short item_num = 0;
  //Declaration LIKE items.stock_num
  public short stock_num = 0;
  //Declaration LIKE items.manu_code
  public Char manu_code = new Char(3);
  //Declaration LIKE items.quantity
  public short quantity = 0;
  //Declaration LIKE items.total_price
  public Decimal total_price = new Money(8, 2);
  //Declaration LIKE stock.description
  public Char description = new Char(15);
  //Declaration LIKE stock.unit_price
  public Decimal unit_price = new Money(6, 2);
  //Declaration LIKE stock.unit
  public Char unit = new Char(4);
  //Declaration LIKE stock.unit_descr
  public Char unit_descr = new Char(15);
  //Declaration LIKE manufact.manu_name
  public Char manu_name = new Char(15);
  static final String[] __fields = {"order_num", "order_date", "ship_instruct", "backlog", "po_num", "ship_date", "ship_weight", "ship_charge", "item_num", "stock_num", "manu_code", "quantity", "total_price", "description", "unit_price", "unit", "unit_descr", "manu_name"};

  protected String[] getFieldsOrder(){
    return __fields;
  }

}
