package com.ais.demo.record;
// Import declaration block
import com.ais.fc.ix.lang.*;
import com.ais.fc.ix.lang.blob.*;

public  class P_Items extends Record{
  //Declaration LIKE items.item_num
  public short item_num = 0;
  //Declaration LIKE items.stock_num
  public short stock_num = 0;
  //Declaration LIKE items.manu_code
  public Char manu_code = new Char(3);
  //Declaration LIKE stock.description
  public Char description = new Char(15);
  //Declaration LIKE items.quantity
  public short quantity = 0;
  //Declaration LIKE stock.unit_price
  public Decimal unit_price = new Money(6, 2);
  //Declaration LIKE items.total_price
  public Decimal total_price = new Money(8, 2);
  static final String[] __fields = {"item_num", "stock_num", "manu_code", "description", "quantity", "unit_price", "total_price"};

  protected String[] getFieldsOrder(){
    return __fields;
  }

}
