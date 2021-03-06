package com.ais.demo.form;
// Import declaration block
import com.ais.fc.ix.lang.*;
import com.ais.fc.ix.lang.blob.*;
import com.ais.fc.ix.swing.ui.*;
import com.ais.fc.ix.addon.swing.ui.*;
import javax.swing.*;
import java.awt.*;

public  class Customer extends Form{
  //*** Form Component Declaration ***
  JLabel label_0 = new JLabel();
  JLabel label_1 = new JLabel();
  JLabel label_2 = new JLabel();
  JLabel label_3 = new JLabel();
  TextBox customer__customer_num = new TextBox();
  JLabel label_4 = new JLabel();
  TextBox customer__fname = new TextBox();
  JLabel label_5 = new JLabel();
  TextBox customer__lname = new TextBox();
  JLabel label_6 = new JLabel();
  TextBox customer__company = new TextBox();
  JLabel label_7 = new JLabel();
  TextBox customer__address1 = new TextBox();
  TextBox customer__address2 = new TextBox();
  JLabel label_8 = new JLabel();
  TextBox customer__city = new TextBox();
  JLabel label_9 = new JLabel();
  TextBox customer__state = new TextBox();
  JLabel label_10 = new JLabel();
  TextBox customer__zipcode = new TextBox();
  JLabel label_11 = new JLabel();
  TextBox customer__phone = new TextBox();
  JLabel label_12 = new JLabel();
  //*** Table Screen Record Declaration ***
  ScreenRecord customer = new ScreenRecord();
  //*** Screen Record Declaration ***
  ScreenRecord sc_cust = new ScreenRecord();
  public Customer(){
    try {
      jbInit();
    }
    catch (Exception e){
    }
  }
  

  private void jbInit() throws Exception{
    //*** Screen Specification ***
    this.setName("customer");
    GridBagLayout thislayout = new GridBagLayout();
    this.setLayout(thislayout);
    this.setSize(GridBagLayoutHelper.computeSize(thislayout, this, 69, 19));
    //*** Component Initialization ***
    label_0.setName("label_0");
    label_0.setText("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    this.add(label_0, new GridBagConstraints(0, 0, 69, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_1.setName("label_1");
    label_1.setText("CUSTOMER");
    this.add(label_1, new GridBagConstraints(26, 1, 8, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_2.setName("label_2");
    label_2.setText("FORM");
    this.add(label_2, new GridBagConstraints(36, 1, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_3.setName("label_3");
    label_3.setText("Number:");
    this.add(label_3, new GridBagConstraints(7, 3, 7, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    customer__customer_num.setName("customer_num");
    customer__customer_num.setType(new Integer(0));
    customer__customer_num.setNotNull(true);
    customer__customer_num.setDefaultValue(null);
    customer__customer_num.setEnabled(false);
    customer__customer_num.setLength(11);
    this.add(customer__customer_num, new GridBagConstraints(17, 3, 11, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_4.setName("label_4");
    label_4.setText("First Name:");
    this.add(label_4, new GridBagConstraints(3, 5, 11, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    customer__fname.setName("fname");
    customer__fname.setType(new Char(15));
    customer__fname.setDefaultValue(null);
    customer__fname.setEnabled(false);
    customer__fname.setLength(15);
    this.add(customer__fname, new GridBagConstraints(17, 5, 15, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_5.setName("label_5");
    label_5.setText("Last Name:");
    this.add(label_5, new GridBagConstraints(38, 5, 10, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    customer__lname.setName("lname");
    customer__lname.setType(new Char(15));
    customer__lname.setDefaultValue(null);
    customer__lname.setEnabled(false);
    customer__lname.setLength(15);
    this.add(customer__lname, new GridBagConstraints(51, 5, 15, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_6.setName("label_6");
    label_6.setText("Company:");
    this.add(label_6, new GridBagConstraints(6, 7, 8, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    customer__company.setName("company");
    customer__company.setType(new Char(20));
    customer__company.setDefaultValue(null);
    customer__company.setEnabled(false);
    customer__company.setLength(20);
    this.add(customer__company, new GridBagConstraints(17, 7, 20, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_7.setName("label_7");
    label_7.setText("Address:");
    this.add(label_7, new GridBagConstraints(6, 9, 8, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    customer__address1.setName("address1");
    customer__address1.setType(new Char(20));
    customer__address1.setDefaultValue(null);
    customer__address1.setEnabled(false);
    customer__address1.setLength(20);
    this.add(customer__address1, new GridBagConstraints(17, 9, 20, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    customer__address2.setName("address2");
    customer__address2.setType(new Char(20));
    customer__address2.setDefaultValue(null);
    customer__address2.setEnabled(false);
    customer__address2.setLength(20);
    this.add(customer__address2, new GridBagConstraints(17, 10, 20, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_8.setName("label_8");
    label_8.setText("City:");
    this.add(label_8, new GridBagConstraints(9, 12, 5, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    customer__city.setName("city");
    customer__city.setType(new Char(15));
    customer__city.setDefaultValue(null);
    customer__city.setEnabled(false);
    customer__city.setLength(15);
    this.add(customer__city, new GridBagConstraints(17, 12, 15, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_9.setName("label_9");
    label_9.setText("State:");
    this.add(label_9, new GridBagConstraints(8, 14, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    customer__state.setName("state");
    customer__state.setType(new Char(2));
    customer__state.setDefaultValue(null);
    customer__state.setEnabled(false);
    customer__state.setLength(2);
    this.add(customer__state, new GridBagConstraints(17, 14, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_10.setName("label_10");
    label_10.setText("Zipcode:");
    this.add(label_10, new GridBagConstraints(23, 14, 8, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    customer__zipcode.setName("zipcode");
    customer__zipcode.setType(new Char(5));
    customer__zipcode.setDefaultValue(null);
    customer__zipcode.setEnabled(false);
    customer__zipcode.setLength(5);
    this.add(customer__zipcode, new GridBagConstraints(34, 14, 5, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_11.setName("label_11");
    label_11.setText("Telephone:");
    this.add(label_11, new GridBagConstraints(4, 16, 10, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    customer__phone.setName("phone");
    customer__phone.setType(new Char(18));
    customer__phone.setDefaultValue(null);
    customer__phone.setEnabled(false);
    customer__phone.setLength(18);
    this.add(customer__phone, new GridBagConstraints(17, 16, 18, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_12.setName("label_12");
    label_12.setText("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
    this.add(label_12, new GridBagConstraints(0, 17, 69, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    //*** Table Screen Record Initialization ***
    customer.setName("customer");
    customer.setFields(customer__customer_num, customer__fname, customer__lname, customer__company, customer__address1, customer__address2, customer__city, customer__state, customer__zipcode, customer__phone);
    this.addElement(customer);
    //*** Screen Record Initialization ***
    sc_cust.setName("sc_cust");
    sc_cust.setFields(customer__fname, customer__lname, customer__company, customer__address1, customer__address2, customer__city, customer__state, customer__zipcode, customer__phone);
    this.addElement(sc_cust);
  }

}
