package com.ais.demo.record.stores7.informix;
// Import declaration block
import com.ais.fc.ix.lang.*;
import com.ais.fc.ix.lang.blob.*;

public  class Customer extends Record{
  public int customer_num = 0;
  public Char fname = new Char(15);
  public Char lname = new Char(15);
  public Char company = new Char(20);
  public Char address1 = new Char(20);
  public Char address2 = new Char(20);
  public Char city = new Char(15);
  public Char state = new Char(2);
  public Char zipcode = new Char(5);
  public Char phone = new Char(18);
  static final String[] __fields = {"customer_num", "fname", "lname", "company", "address1", 
    "address2", "city", "state", "zipcode", "phone"};

  protected String[] getFieldsOrder(){
    return __fields;
  }

}
