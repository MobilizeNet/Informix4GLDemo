package com.ais.demo.record;
// Import declaration block
import com.ais.fc.ix.lang.*;
import com.ais.fc.ix.lang.blob.*;

public  class SQLCA extends Record{
  public int sqlcode = 0;
  public Char sqlerrm = new Char(71);
  public Char sqlerrp = new Char(8);
  public int[] sqlerrd = new int[6];
  public Char sqlawarn = new Char(8);
  static final String[] __fields = {"sqlcode", "sqlerrm", "sqlerrp", "sqlerrd", "sqlawarn"};

  protected String[] getFieldsOrder(){
    return __fields;
  }

}
