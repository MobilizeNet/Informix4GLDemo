package com.ais.demo.record.stores7.informix;
// Import declaration block
import com.ais.fc.ix.lang.*;
import com.ais.fc.ix.lang.blob.*;

public  class State extends Record{
  public Char code = new Char(2);
  public Char sname = new Char(15);
  static final String[] __fields = {"code", "sname"};

  protected String[] getFieldsOrder(){
    return __fields;
  }

}
