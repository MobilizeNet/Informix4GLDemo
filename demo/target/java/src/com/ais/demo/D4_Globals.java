package com.ais.demo;
// Import declaration block
import com.ais.demo.record.stores7.informix.State;
import com.ais.demo.record.P_Stock;
import com.ais.demo.record.P_Items;
import com.ais.demo.record.P_Orders;
import com.ais.demo.record.stores7.informix.Customer;
import com.ais.fc.ix.ui.*;
import com.ais.fc.ix.input.*;
import com.ais.fc.ix.input.event.*;
import com.ais.fc.ix.lang.*;
import com.ais.fc.ix.lang.blob.*;
import com.ais.fc.ix.application.*;
import com.ais.fc.ix.ui.UIHelper;
import com.ais.fc.ix.ui.Key;
import com.ais.fc.ix.report.*;
import com.ais.fc.ix.sql.*;
import java.awt.event.KeyEvent;

public  class D4_Globals implements ID4_Globals {
  /**
   *  D4_Globals constructor
   */
  public D4_Globals(Application owner){
    _app = owner;
    _wm = _app.getUIHelper();
    _eh = _app.getErrorHandler();
    //Global variables initialization
    java.util.HashMap _global = (java.util.HashMap)_app.getValue(Application.GLOBALS_KEY);
    if ((p_customer = (Customer)_global.get("p_customer")) == null){
      _global.put("p_customer", p_customer = new Customer());
    }
    if ((p_orders = (P_Orders)_global.get("p_orders")) == null){
      _global.put("p_orders", p_orders = new P_Orders());
    }
    if ((p_items = (P_Items[])_global.get("p_items")) == null){
      _global.put("p_items", p_items = (P_Items[])Record.allocate(new P_Items[10]));
    }
    if ((p_stock = (P_Stock[])_global.get("p_stock")) == null){
      _global.put("p_stock", p_stock = (P_Stock[])Record.allocate(new P_Stock[30]));
    }
    if ((p_state = (State[])_global.get("p_state")) == null){
      _global.put("p_state", p_state = (State[])Record.allocate(new State[50]));
    }
    if ((state_cnt = (Int)_global.get("state_cnt")) == null){
      _global.put("state_cnt", state_cnt = new Int(0));
    }
    if ((stock_cnt = (Int)_global.get("stock_cnt")) == null){
      _global.put("stock_cnt", stock_cnt = new Int(0));
    }
    if ((print_option = (Char)_global.get("print_option")) == null){
      _global.put("print_option", print_option = new Char(1));
    }
  }
  
  /**
   *  Application instances
   */
  protected Application _app;
  protected UIHelper _wm;
  protected ErrorHandler _eh;
  //Declaration LIKE customer
  public Customer p_customer;
  public P_Orders p_orders;
  public P_Items[] p_items;
  public P_Stock[] p_stock;
  public State[] p_state;
  public Int state_cnt, stock_cnt;
  public Char print_option;
}
