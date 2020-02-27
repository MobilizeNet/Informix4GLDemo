package com.ais.demo.record;
// Import declaration block
import com.ais.fc.ix.lang.*;
import com.ais.fc.ix.lang.blob.*;

public  class P_Stock extends Record{
  //Declaration LIKE stock.stock_num
  public short stock_num = 0;
  //Declaration LIKE manufact.manu_code
  public Char manu_code = new Char(3);
  //Declaration LIKE manufact.manu_name
  public Char manu_name = new Char(15);
  //Declaration LIKE stock.description
  public Char description = new Char(15);
  //Declaration LIKE stock.unit_price
  public Decimal unit_price = new Money(6, 2);
  //Declaration LIKE stock.unit_descr
  public Char unit_descr = new Char(15);
  static final String[] __fields = {"stock_num", "manu_code", "manu_name", "description", "unit_price", "unit_descr"};

  protected String[] getFieldsOrder(){
    return __fields;
  }

}
