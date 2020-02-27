package com.ais.demo.record;
// Import declaration block
import com.ais.fc.ix.lang.*;
import com.ais.fc.ix.lang.blob.*;

public  class P_Orders extends Record{
  //Declaration LIKE orders.order_num
  public int order_num = 0;
  //Declaration LIKE orders.order_date
  public Date order_date = new Date();
  //Declaration LIKE orders.po_num
  public Char po_num = new Char(10);
  //Declaration LIKE orders.ship_instruct
  public Char ship_instruct = new Char(40);
  static final String[] __fields = {"order_num", "order_date", "po_num", "ship_instruct"};

  protected String[] getFieldsOrder(){
    return __fields;
  }

}
