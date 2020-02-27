package com.ais.demo;
// Import declaration block
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

public  class D4_Main implements ID4_Main {
  /**
   *  main entry point
   */

  static public void main(String[] args){
    final Application app = AppFactory.create("D4_Main", args);
    app.start((Runnable)app.getModule("com.ais.demo.D4_Main"));
  }
  
  /**
   *  D4_Main constructor
   */
  public D4_Main(Application owner){
    _app = owner;
    _wm = _app.getUIHelper();
    _eh = _app.getErrorHandler();
    //Initialization for the global instances
    globals_0 = (D4_Globals)_app.getModule("com.ais.demo.D4_Globals");
  }
  
  /**
   *  Application instances
   */
  protected Application _app;
  protected UIHelper _wm;
  protected ErrorHandler _eh;
  protected D4_Globals globals_0 = null;

  public void run(){
    _app.setDB(_app.getDB("stores7"));
    _wm.setDeferInterrupt(true);
    _wm.setHelpFile(this.getClass(), "com.ais.demo.helpdemo");
    globals_0.print_option.set("s");
    get_states();
    get_stocks();
    ring_menu();
    {
      final Menu menu = _wm.createMenu("MAIN");
      menu.addCommandListener(_wm.createCommand("Customer", "Enter and maintain customer data", 101), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          getD4_Cust().customer();
          ring_menu();
        }
      });
      menu.addCommandListener(_wm.createCommand("Orders", "Enter and maintain orders", 102), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          getD4_Orders().orders();
          ring_menu();
        }
      });
      menu.addCommandListener(_wm.createCommand("Stock", "Enter and maintain stock list", 103), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          getD4_Stock().stock();
          ring_menu();
        }
      });
      menu.addCommandListener(_wm.createCommand("Reports", "Print reports and mailing labels", 104), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          getD4_Report().reports();
          ring_menu();
        }
      });
      menu.addCommandListener(_wm.createCommand(), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          bang();
          ring_menu();
          menu.nextOption("Customer");
        }
      }, _wm.getKey( KeyEvent.VK_EXCLAMATION_MARK));
      menu.addCommandListener(_wm.createCommand(), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          getD4_Demo().demo();
          ring_menu();
          menu.nextOption("Customer");
        }
      }, _wm.getKey(KeyEvent.VK_X));
      menu.addCommandListener(_wm.createCommand("Exit", "Exit program and return to operating system", 105), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          _wm.setCurrent(_wm.getScreen());
          _wm.getScreen().clear();
          _app.exit(0);
        }
      });
      menu.start();
    }
  }
  

  public void bang(){
    Char cmd = new Char(80);
    Char x = new Char(1);
    clear_menu();
    x.set("!");
    while (Str.equals(Str.valueOf(x), "!")){
      {
        final Prompt prompt = _wm.createPrompt("!", false);
        prompt.start();
        cmd.set(prompt.getPrompt());
      }
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0107 **: Using Run instruction. Possible error while executing the command.");
      _app.execute(true, Str.valueOf(cmd));
      {
        final Prompt prompt = _wm.createPrompt("Type RETURN to continue.", true);
        prompt.start();
        x.set(prompt.getPrompt());
      }
    }
  }
  

  public void mess(String str_, short mrow){
    Char str = new Char(80).set(str_);
    _wm.displayAt(0, Int.valueOf(mrow) - 1, " ", str.rTrim());
    _app.sleep(3);
    _wm.displayAt(0, Int.valueOf(mrow) - 1, "");
  }
  

  public void ring_menu(){
    /**
     *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
     */
    Warning.warning("** W0146 **: Attribute COLOR(MAGENTA) not translated. Used by: Display");
    _wm.displayAt(1, 3, "---------------------------------------   ", "Type Control-W for MENU HELP   -----");
  }
  

  public void unring_menu(){
    /**
     *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
     */
    Warning.warning("** W0146 **: Attribute COLOR(MAGENTA) not translated. Used by: Display");
    _wm.displayAt(1, 3, "---------------------------------------   ", "                               -----");
  }
  

  public void clear_menu(){
    _wm.displayAt(0, 0, "");
    _wm.displayAt(0, 1, "");
  }
  

  public void get_states(){
    java.sql.ResultSet c_state = null;
    java.sql.PreparedStatement c_state__p = null;
    try {
      c_state__p = _app.getDB().prepareStatement("SELECT * FROM state ORDER BY sname");
      globals_0.state_cnt.value = 1;
      c_state = _app.getDB().executeQuery(c_state__p);
      foreach_ : 
      while (_app.getDB().moveNext(c_state)){
        {
          Record _rec = new Record(c_state);
          try {
            globals_0.p_state[globals_0.state_cnt.value - 1].set(_rec);
          }
          catch (NoMoreValuesException _e){
          }
        }
        globals_0.state_cnt.value += 1;
        if (Int.greater(globals_0.state_cnt.value, 50)){
          _eh.setStatus(0);
          break foreach_;
        }
      }
      _app.getDB().close(c_state);
      c_state = null;
      globals_0.state_cnt.value -= 1;
    }
    finally {
      DBHelper _db = _app.getDB();
      if (_db != null){
        _db.close(c_state);
        c_state = null;
        _db.close(c_state__p);
        c_state__p = null;
      }
    }
  }
  

  public void get_stocks(){
    java.sql.ResultSet stock_list = null;
    java.sql.PreparedStatement stock_list__p = null;
    try {
      stock_list__p = _app.getDB().prepareStatement("SELECT stock_num, manufact.manu_code, manu_name, description, unit_price, unit_descr FROM stock, manufact WHERE stock.manu_code = manufact.manu_code ORDER BY stock_num");
      globals_0.stock_cnt.value = 1;
      stock_list = _app.getDB().executeQuery(stock_list__p);
      foreach_ : 
      while (_app.getDB().moveNext(stock_list)){
        {
          Record _rec = new Record(stock_list);
          try {
            globals_0.p_stock[globals_0.stock_cnt.value - 1].set(_rec);
          }
          catch (NoMoreValuesException _e){
          }
        }
        globals_0.stock_cnt.value += 1;
        if (Int.greater(globals_0.stock_cnt.value, 30)){
          _eh.setStatus(0);
          break foreach_;
        }
      }
      _app.getDB().close(stock_list);
      stock_list = null;
      globals_0.stock_cnt.value -= 1;
    }
    finally {
      DBHelper _db = _app.getDB();
      if (_db != null){
        _db.close(stock_list);
        stock_list = null;
        _db.close(stock_list__p);
        stock_list__p = null;
      }
    }
  }
  
  com.ais.demo.ID4_Cust d4_cust;

  public com.ais.demo.ID4_Cust getD4_Cust(){
    if (this.d4_cust == null){
      return this.d4_cust = (com.ais.demo.ID4_Cust)_app.getModule("com.ais.demo.D4_Cust");
    }
    else {
      return this.d4_cust;
    }
  }
  

  public void setD4_Cust(com.ais.demo.ID4_Cust _D4_Cust){
    this.d4_cust = _D4_Cust;
  }
  
  com.ais.demo.ID4_Demo d4_demo;

  public com.ais.demo.ID4_Demo getD4_Demo(){
    if (this.d4_demo == null){
      return this.d4_demo = (com.ais.demo.ID4_Demo)_app.getModule("com.ais.demo.D4_Demo");
    }
    else {
      return this.d4_demo;
    }
  }
  

  public void setD4_Demo(com.ais.demo.ID4_Demo _D4_Demo){
    this.d4_demo = _D4_Demo;
  }
  
  com.ais.demo.ID4_Orders d4_orders;

  public com.ais.demo.ID4_Orders getD4_Orders(){
    if (this.d4_orders == null){
      return this.d4_orders = (com.ais.demo.ID4_Orders)_app.getModule("com.ais.demo.D4_Orders");
    }
    else {
      return this.d4_orders;
    }
  }
  

  public void setD4_Orders(com.ais.demo.ID4_Orders _D4_Orders){
    this.d4_orders = _D4_Orders;
  }
  
  com.ais.demo.ID4_Report d4_report;

  public com.ais.demo.ID4_Report getD4_Report(){
    if (this.d4_report == null){
      return this.d4_report = (com.ais.demo.ID4_Report)_app.getModule("com.ais.demo.D4_Report");
    }
    else {
      return this.d4_report;
    }
  }
  

  public void setD4_Report(com.ais.demo.ID4_Report _D4_Report){
    this.d4_report = _D4_Report;
  }
  
  com.ais.demo.ID4_Stock d4_stock;

  public com.ais.demo.ID4_Stock getD4_Stock(){
    if (this.d4_stock == null){
      return this.d4_stock = (com.ais.demo.ID4_Stock)_app.getModule("com.ais.demo.D4_Stock");
    }
    else {
      return this.d4_stock;
    }
  }
  

  public void setD4_Stock(com.ais.demo.ID4_Stock _D4_Stock){
    this.d4_stock = _D4_Stock;
  }

}
