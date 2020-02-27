package com.ais.demo.form;
// Import declaration block
import com.ais.fc.ix.lang.*;
import com.ais.fc.ix.lang.blob.*;
import com.ais.fc.ix.swing.ui.*;
import com.ais.fc.ix.addon.swing.ui.*;
import javax.swing.*;
import java.awt.*;

public  class Stock_Sel extends Form{
  //*** Form Component Declaration ***
  FieldGroup formonly__stock_num = new FieldGroup();
  TextBox formonly__stock_num__fld = new TextBox();
  FieldGroup formonly__manu_code = new FieldGroup();
  TextBox formonly__manu_code__fld = new TextBox();
  FieldGroup formonly__manu_name = new FieldGroup();
  TextBox formonly__manu_name__fld = new TextBox();
  FieldGroup formonly__description = new FieldGroup();
  TextBox formonly__description__fld = new TextBox();
  FieldGroup formonly__unit_price = new FieldGroup();
  TextBox formonly__unit_price__fld = new TextBox();
  FieldGroup formonly__unit_descr = new FieldGroup();
  TextBox formonly__unit_descr__fld = new TextBox();
  //*** Table Screen Record Declaration ***
  ScreenRecord stock = new ScreenRecord();
  //*** FormOnly Screen Record Declaration ***
  ScreenRecord formonly = new ScreenRecord();
  //*** Screen Record Declaration ***
  ScreenRecord s_stock = new ScreenRecord();
  public Stock_Sel(){
    try {
      jbInit();
    }
    catch (Exception e){
    }
  }
  

  private void jbInit() throws Exception{
    //*** Screen Specification ***
    this.setName("stock_sel");
    GridBagLayout thislayout = new GridBagLayout();
    this.setLayout(thislayout);
    this.setSize(GridBagLayoutHelper.computeSize(thislayout, this, 76, 4));
    //*** Component Initialization ***
    formonly__stock_num.setName("stock_num");
    formonly__stock_num__fld.setName("stock_num");
    formonly__stock_num__fld.setType(new Char(255));
    formonly__stock_num__fld.setNotNull(false);
    formonly__stock_num__fld.setEnabled(false);
    formonly__stock_num__fld.setLength(4);
    formonly__stock_num.add(formonly__stock_num__fld);
    formonly__stock_num.setFieldCount(3);
    this.add(formonly__stock_num, new GridBagConstraints(3, 0, 4, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    formonly__manu_code.setName("manu_code");
    formonly__manu_code__fld.setName("manu_code");
    formonly__manu_code__fld.setType(new Char(255));
    formonly__manu_code__fld.setNotNull(false);
    formonly__manu_code__fld.setEnabled(false);
    formonly__manu_code__fld.setLength(4);
    formonly__manu_code.add(formonly__manu_code__fld);
    formonly__manu_code.setFieldCount(3);
    this.add(formonly__manu_code, new GridBagConstraints(9, 0, 4, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    formonly__manu_name.setName("manu_name");
    formonly__manu_name__fld.setName("manu_name");
    formonly__manu_name__fld.setType(new Char(255));
    formonly__manu_name__fld.setNotNull(false);
    formonly__manu_name__fld.setEnabled(false);
    formonly__manu_name__fld.setLength(15);
    formonly__manu_name.add(formonly__manu_name__fld);
    formonly__manu_name.setFieldCount(3);
    this.add(formonly__manu_name, new GridBagConstraints(15, 0, 15, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    formonly__description.setName("description");
    formonly__description__fld.setName("description");
    formonly__description__fld.setType(new Char(255));
    formonly__description__fld.setNotNull(false);
    formonly__description__fld.setEnabled(false);
    formonly__description__fld.setLength(15);
    formonly__description.add(formonly__description__fld);
    formonly__description.setFieldCount(3);
    this.add(formonly__description, new GridBagConstraints(32, 0, 15, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    formonly__unit_price.setName("unit_price");
    formonly__unit_price__fld.setName("unit_price");
    formonly__unit_price__fld.setType(new Char(255));
    formonly__unit_price__fld.setNotNull(false);
    formonly__unit_price__fld.setEnabled(false);
    formonly__unit_price__fld.setLength(9);
    formonly__unit_price.add(formonly__unit_price__fld);
    formonly__unit_price.setFieldCount(3);
    this.add(formonly__unit_price, new GridBagConstraints(49, 0, 9, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    formonly__unit_descr.setName("unit_descr");
    formonly__unit_descr__fld.setName("unit_descr");
    formonly__unit_descr__fld.setType(new Char(255));
    formonly__unit_descr__fld.setNotNull(false);
    formonly__unit_descr__fld.setEnabled(false);
    formonly__unit_descr__fld.setLength(15);
    formonly__unit_descr.add(formonly__unit_descr__fld);
    formonly__unit_descr.setFieldCount(3);
    this.add(formonly__unit_descr, new GridBagConstraints(60, 0, 15, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    //*** Table Screen Record Initialization ***
    stock.setName("stock");
    stock.setFields();
    this.addElement(stock);
    //*** Formonly Screen Record Initialization ***
    formonly.setName("formonly");
    formonly.setFields(formonly__stock_num, formonly__manu_code, formonly__manu_name, formonly__description, formonly__unit_price, formonly__unit_descr);
    this.addElement(formonly);
    //*** Screen Record Initialization ***
    s_stock.setName("s_stock");
    s_stock.setFields(formonly__stock_num, formonly__manu_code, formonly__manu_name, formonly__description, formonly__unit_price, formonly__unit_descr);
    this.addElement(s_stock);
  }

}
