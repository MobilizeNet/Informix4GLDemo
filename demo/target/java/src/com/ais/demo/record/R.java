package com.ais.demo.record;
// Import declaration block
import com.ais.fc.ix.lang.*;
import com.ais.fc.ix.lang.blob.*;

public  class R extends Record{
  //Declaration LIKE customer.customer_num
  public int customer_num = 0;
  //Declaration LIKE customer.fname
  public Char fname = new Char(15);
  //Declaration LIKE customer.lname
  public Char lname = new Char(15);
  //Declaration LIKE customer.company
  public Char company = new Char(20);
  //Declaration LIKE orders.order_num
  public int order_num = 0;
  //Declaration LIKE orders.order_date
  public Date order_date = new Date();
  //Declaration LIKE orders.ship_date
  public Date ship_date = new Date();
  //Declaration LIKE orders.paid_date
  public Date paid_date = new Date();
  //Declaration LIKE items.total_price
  public Decimal total_price = new Money(8, 2);
  static final String[] __fields = {"customer_num", "fname", "lname", "company", "order_num", "order_date", "ship_date", "paid_date", "total_price"};

  protected String[] getFieldsOrder(){
    return __fields;
  }

}
