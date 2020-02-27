package com.ais.demo.form;
// Import declaration block
import com.ais.fc.ix.lang.*;
import com.ais.fc.ix.lang.blob.*;
import com.ais.fc.ix.swing.ui.*;
import com.ais.fc.ix.addon.swing.ui.*;
import javax.swing.*;
import java.awt.*;

public  class Ordcur extends Form{
  //*** Form Component Declaration ***
  JLabel label_0 = new JLabel();
  JLabel label_1 = new JLabel();
  TextBox orders__order_num = new TextBox();
  JLabel label_2 = new JLabel();
  TextBox orders__order_date = new TextBox();
  JLabel label_3 = new JLabel();
  TextBox orders__customer_num = new TextBox();
  JLabel label_4 = new JLabel();
  TextBox orders__ship_instruct = new TextBox();
  JLabel label_5 = new JLabel();
  TextBox orders__backlog = new TextBox();
  JLabel label_6 = new JLabel();
  TextBox orders__po_num = new TextBox();
  JLabel label_7 = new JLabel();
  TextBox orders__ship_date = new TextBox();
  JLabel label_8 = new JLabel();
  TextBox orders__ship_weight = new TextBox();
  JLabel label_9 = new JLabel();
  TextBox orders__ship_charge = new TextBox();
  JLabel label_10 = new JLabel();
  TextBox orders__paid_date = new TextBox();
  //*** Table Screen Record Declaration ***
  ScreenRecord customer = new ScreenRecord();
  ScreenRecord orders = new ScreenRecord();
  public Ordcur(){
    try {
      jbInit();
    }
    catch (Exception e){
    }
  }
  

  private void jbInit() throws Exception{
    //*** Screen Specification ***
    this.setName("ordcur");
    GridBagLayout thislayout = new GridBagLayout();
    this.setLayout(thislayout);
    this.setSize(GridBagLayoutHelper.computeSize(thislayout, this, 57, 9));
    //*** Component Initialization ***
    label_0.setName("label_0");
    label_0.setText("ORDER FORM");
    this.add(label_0, new GridBagConstraints(26, 0, 10, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_1.setName("label_1");
    label_1.setText("Order No:");
    this.add(label_1, new GridBagConstraints(4, 2, 9, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    orders__order_num.setName("order_num");
    orders__order_num.setType(new Integer(0));
    orders__order_num.setNotNull(true);
    orders__order_num.setDefaultValue(null);
    orders__order_num.setEnabled(false);
    orders__order_num.setLength(11);
    this.add(orders__order_num, new GridBagConstraints(16, 2, 11, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_2.setName("label_2");
    label_2.setText("Order Date:");
    this.add(label_2, new GridBagConstraints(32, 2, 11, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    orders__order_date.setName("order_date");
    orders__order_date.setType(new Date());
    orders__order_date.setDefaultValue(null);
    orders__order_date.setEnabled(false);
    orders__order_date.setLength(10);
    this.add(orders__order_date, new GridBagConstraints(46, 2, 10, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_3.setName("label_3");
    label_3.setText("Customer No:");
    this.add(label_3, new GridBagConstraints(1, 3, 12, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    orders__customer_num.setName("customer_num");
    orders__customer_num.setType(new Integer(0));
    orders__customer_num.setNotNull(true);
    orders__customer_num.setDefaultValue(null);
    orders__customer_num.setEnabled(false);
    orders__customer_num.setLength(11);
    this.add(orders__customer_num, new GridBagConstraints(16, 3, 11, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_4.setName("label_4");
    label_4.setText("Instructions:");
    this.add(label_4, new GridBagConstraints(0, 4, 13, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    orders__ship_instruct.setName("ship_instruct");
    orders__ship_instruct.setType(new Char(40));
    orders__ship_instruct.setDefaultValue(null);
    orders__ship_instruct.setEnabled(false);
    orders__ship_instruct.setLength(40);
    this.add(orders__ship_instruct, new GridBagConstraints(16, 4, 40, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_5.setName("label_5");
    label_5.setText("Backlog:");
    this.add(label_5, new GridBagConstraints(5, 5, 8, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    orders__backlog.setName("backlog");
    orders__backlog.setType(new Char(1));
    orders__backlog.setDefaultValue(null);
    orders__backlog.setEnabled(false);
    orders__backlog.setLength(1);
    this.add(orders__backlog, new GridBagConstraints(16, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_6.setName("label_6");
    label_6.setText("PO Number:");
    this.add(label_6, new GridBagConstraints(33, 5, 10, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    orders__po_num.setName("po_num");
    orders__po_num.setType(new Char(10));
    orders__po_num.setDefaultValue(null);
    orders__po_num.setEnabled(false);
    orders__po_num.setLength(10);
    this.add(orders__po_num, new GridBagConstraints(46, 5, 10, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_7.setName("label_7");
    label_7.setText("Ship Date:");
    this.add(label_7, new GridBagConstraints(3, 6, 10, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    orders__ship_date.setName("ship_date");
    orders__ship_date.setType(new Date());
    orders__ship_date.setDefaultValue(null);
    orders__ship_date.setEnabled(false);
    orders__ship_date.setLength(10);
    this.add(orders__ship_date, new GridBagConstraints(16, 6, 10, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_8.setName("label_8");
    label_8.setText("Ship Weight:");
    this.add(label_8, new GridBagConstraints(31, 6, 12, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    orders__ship_weight.setName("ship_weight");
    orders__ship_weight.setType(new Decimal(8, 2));
    orders__ship_weight.setDefaultValue(null);
    orders__ship_weight.setEnabled(false);
    orders__ship_weight.setLength(10);
    this.add(orders__ship_weight, new GridBagConstraints(46, 6, 10, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_9.setName("label_9");
    label_9.setText("Ship Charge:");
    this.add(label_9, new GridBagConstraints(1, 7, 12, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    orders__ship_charge.setName("ship_charge");
    orders__ship_charge.setType(new Money(6, 2));
    orders__ship_charge.setDefaultValue(null);
    orders__ship_charge.setEnabled(false);
    orders__ship_charge.setLength(9);
    this.add(orders__ship_charge, new GridBagConstraints(16, 7, 9, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    label_10.setName("label_10");
    label_10.setText("Date Paid:");
    this.add(label_10, new GridBagConstraints(33, 7, 10, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    orders__paid_date.setName("paid_date");
    orders__paid_date.setType(new Date());
    orders__paid_date.setDefaultValue(null);
    orders__paid_date.setEnabled(false);
    orders__paid_date.setLength(10);
    this.add(orders__paid_date, new GridBagConstraints(46, 7, 10, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    //*** Table Screen Record Initialization ***
    customer.setName("customer");
    customer.setFields();
    this.addElement(customer);
    orders.setName("orders");
    orders.setFields(orders__order_num, orders__order_date, orders__customer_num, orders__ship_instruct, orders__backlog, orders__po_num, orders__ship_date, orders__ship_weight, orders__ship_charge, orders__paid_date);
    this.addElement(orders);
  }

}
