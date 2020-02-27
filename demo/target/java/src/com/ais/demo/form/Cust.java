package com.ais.demo.form;
// Import declaration block
import com.ais.fc.ix.lang.*;
import com.ais.fc.ix.lang.blob.*;
import com.ais.fc.ix.swing.ui.*;
import com.ais.fc.ix.addon.swing.ui.*;
import javax.swing.*;
import java.awt.*;

public  class Cust extends Form{
  //*** Form Component Declaration ***
  JLabel label_0 = new JLabel();
  JLabel label_1 = new JLabel();
  JLabel label_2 = new JLabel();
  FieldGroup customer__fname = new FieldGroup();
  TextBox customer__fname__fld = new TextBox();
  FieldGroup customer__lname = new FieldGroup();
  TextBox customer__lname__fld = new TextBox();
  FieldGroup customer__company = new FieldGroup();
  TextBox customer__company__fld = new TextBox();
  //*** Table Screen Record Declaration ***
  ScreenRecord customer = new ScreenRecord();
  //*** Screen Record Declaration ***
  ScreenRecord s_cust = new ScreenRecord();
  public Cust(){
    try {
      jbInit();
    }
    catch (Exception e){
    }
  }
  

  private void jbInit() throws Exception{
    //*** Screen Specification ***
    this.setName("cust");
    GridBagLayout thislayout = new GridBagLayout();
    this.setLayout(thislayout);
    this.setSize(GridBagLayoutHelper.computeSize(thislayout, this, 58, 7));
    //*** Component Initialization ***
    label_0.setName("label_0");
    label_0.setText("First Name");
    this.add(label_0, new GridBagConstraints(5, 0, 10, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_1.setName("label_1");
    label_1.setText("Last Name");
    this.add(label_1, new GridBagConstraints(24, 0, 9, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_2.setName("label_2");
    label_2.setText("Company");
    this.add(label_2, new GridBagConstraints(43, 0, 7, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    customer__fname.setName("fname");
    customer__fname__fld.setName("fname");
    customer__fname__fld.setType(new Char(15));
    customer__fname__fld.setDefaultValue(null);
    customer__fname__fld.setEnabled(false);
    customer__fname__fld.setLength(15);
    customer__fname.add(customer__fname__fld);
    customer__fname.setFieldCount(4);
    this.add(customer__fname, new GridBagConstraints(1, 2, 15, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    customer__lname.setName("lname");
    customer__lname__fld.setName("lname");
    customer__lname__fld.setType(new Char(15));
    customer__lname__fld.setDefaultValue(null);
    customer__lname__fld.setEnabled(false);
    customer__lname__fld.setLength(15);
    customer__lname.add(customer__lname__fld);
    customer__lname.setFieldCount(4);
    this.add(customer__lname, new GridBagConstraints(19, 2, 15, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    customer__company.setName("company");
    customer__company__fld.setName("company");
    customer__company__fld.setType(new Char(20));
    customer__company__fld.setDefaultValue(null);
    customer__company__fld.setEnabled(false);
    customer__company__fld.setLength(20);
    customer__company.add(customer__company__fld);
    customer__company.setFieldCount(4);
    this.add(customer__company, new GridBagConstraints(37, 2, 20, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    //*** Table Screen Record Initialization ***
    customer.setName("customer");
    customer.setFields(customer__fname, customer__lname, customer__company);
    this.addElement(customer);
    //*** Screen Record Initialization ***
    s_cust.setName("s_cust");
    s_cust.setFields(customer__fname, customer__lname, customer__company);
    this.addElement(s_cust);
  }

}
