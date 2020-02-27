package com.ais.demo;
// Import declaration block
import com.ais.demo.record.stores7.informix.Customer;
import com.ais.demo.record.X_Invoice;
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

public  class D4_Orders implements ID4_Orders {
  /**
   *  D4_Orders constructor
   */
  public D4_Orders(Application owner){
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
  ExceptionListener D4_Orders_0 = new ExceptionListener() {
    public void onException(Exception ex){
      if (ex != null){
        ex.printStackTrace();
        _app.exit((_eh.getStatus() == 0) ? -1 : _eh.getStatus());
      }
    }
  };

  public void orders(){
    _app.putProperty("form.order_form", "com.ais.demo.form.Orderform");
    /**
     *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
     */
    Warning.warning("** W0146 **: Attribute COLOR(MAGENTA) not translated. Used by: Display form");
    _wm.getCurrentWindow().add(_app.getProperty("form.order_form"), com.ais.fc.ix.ui.Window.FORM_CONSTRAINT);
    {
      final Menu menu = _wm.createMenu("ORDERS");
      menu.addCommandListener(_wm.createCommand("Add-order", "Enter new order to database and print invoice", 301), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          add_order();
        }
      });
      menu.addCommandListener(_wm.createCommand("Update-order", "Enter shipping or payment data", 302), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          update_order();
        }
      });
      menu.addCommandListener(_wm.createCommand("Find-order", "Look up and display orders", 303), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          get_order();
        }
      });
      menu.addCommandListener(_wm.createCommand("Delete-order", "Remove an order from the database", 304), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          delete_order();
        }
      });
      menu.addCommandListener(_wm.createCommand("Exit", "Return to MAIN Menu", 305), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          _wm.setCurrent(_wm.getScreen());
          _wm.getScreen().clear();
          menu.stop();
        }
      });
      menu.start();
    }
  }
  

  public void add_order(){
    //The following variable acts as a holder for all the variables taken out to the class Add_OrderVariables
    final Add_OrderVariables _v = new Add_OrderVariables();
    boolean num_stocks = false;
    Char file_name = new Char(20);
    int query_stat = 0;
    //Window declarations
    Window cust_w = null;
    ExceptionListener previousListener = _eh.getExceptionListener();
    try {
      query_stat = getD4_Cust().query_customer((short)2);
      if (Int.isNull(query_stat)){
        return ;
      }
      if (!Bool.valueOf(query_stat)){
        /**
         *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
         */
        Warning.warning("** W0146 **: Attribute COLOR(YELLOW) not translated. Used by: Window");
        cust_w = _wm.createWindow("cust_w");
        _wm.add(cust_w, 4, 2, 72, 19);
        _app.putProperty("form.o_cust", "com.ais.demo.form.Custform");
        /**
         *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
         */
        Warning.warning("** W0146 **: Attribute COLOR(MAGENTA) not translated. Used by: Display form");
        _wm.getCurrentWindow().add(_app.getProperty("form.o_cust"), com.ais.fc.ix.ui.Window.FORM_CONSTRAINT);
        /**
         *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
         */
        Warning.warning("** W0148 **: Draw box statement not supported. fgl_drawbox(3,61,4,7)");
        /**
         *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
         */
        Warning.warning("** W0148 **: Draw box statement not supported. fgl_drawbox(11,61,4,7)");
        getD4_Cust().add_customer(0);
        _app.putProperty("form.o_cust", null);
        _wm.remove(cust_w);
        if (Int.isNull(globals_0.p_customer.customer_num)){
          return ;
        }
      }
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0146 **: Attribute COLOR(CYAN) not translated. Used by: Display");
      _wm.displayTo(new Object[]{globals_0.p_customer}, _wm.getFldRef("customer_num"), _wm.getFldRef("fname"), _wm.getFldRef("lname"), _wm.getFldRef("company"), _wm.getFldRef("address1"), _wm.getFldRef("address2"), _wm.getFldRef("city"), _wm.getFldRef("state"), _wm.getFldRef("zipcode"), _wm.getFldRef("phone"));
      _wm.message("Enter the order date, PO number and shipping instructions.");
      {
        final Input input = _wm.createInput(false, new Object[]{globals_0.p_orders.order_date, globals_0.p_orders.po_num, globals_0.p_orders.ship_instruct}, _wm.getFldRef("order_date"), _wm.getFldRef("po_num"), _wm.getFldRef("ship_instruct"));
        input.start();
      }
      if (_wm.isInterruptFlag()){
        _wm.setInterruptFlag(false);
        _wm.getCurrentWindow().getCurrentForm().clear();
        /**
         *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
         */
        Warning.warning("** W0146 **: Attribute COLOR(RED), REVERSE not translated. Used by: Display");
        _wm.error("Order input aborted");
        return ;
      }
      {
        final InputArray inputArray = _wm.createInputArray(false, globals_0.p_items, _wm.getArrRef("s_items"));
        inputArray.setHelp(311);
        inputArray.addFieldListener(new FieldAdapter() {
          public void beforeField(java.util.EventObject _ev){
            _wm.message("Press ESC to write order");
            _wm.displayAt(0, 0, "Enter a stock number or press CTRL-B to scan stock list");
          }
        }, _wm.getFldRef("s_items", "stock_num"));
        inputArray.addFieldListener(new FieldAdapter() {
          public void beforeField(java.util.EventObject _ev){
            _wm.message("Enter the code for a manufacturer");
          }
        }, _wm.getFldRef("s_items", "manu_code"));
        inputArray.addFieldListener(new FieldAdapter() {
          public void beforeField(java.util.EventObject _ev){
            _wm.displayAt(0, 0, "");
            _wm.message("Enter the item quantity");
          }

          public void afterField(java.util.EventObject _ev){
            _wm.message("");
            _v.pa_curr = _wm.getArrayIndex() + 1;
            if (!globals_0.p_items[_v.pa_curr - 1].unit_price.isNull() && !Shrt.isNull(globals_0.p_items[_v.pa_curr - 1].quantity)){
              item_total();
            }
            else {
              /**
               *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
               */
              Warning.warning("** W0146 **: Attribute COLOR(RED), REVERSE not translated. Used by: Display");
              _wm.error("A valid stock code, manufacturer, and quantity must all be entered");
              inputArray.nextField(_wm.getFldRef("stock_num"));
            }
          }
        }, _wm.getFldRef("s_items", "quantity"));
        inputArray.addKeyAction(new InputAction() {
          public void actionPerformed(java.util.EventObject _ae){
            if (_wm.inField(_wm.getFldRef("stock_num")) || _wm.inField(_wm.getFldRef("manu_code"))){
              _v.pa_curr = _wm.getArrayIndex() + 1;
              _v.s_curr = _wm.getArrayLine() + 1;
              {
                //Generate the intermediate record variable, and assign the result of the function call to it
                Record add_orderHolder = new Record(get_stock());
                //Store the results in the original variables
                globals_0.p_items[_v.pa_curr - 1].stock_num = add_orderHolder.getShort();
                globals_0.p_items[_v.pa_curr - 1].manu_code.set(add_orderHolder.getChar());
                globals_0.p_items[_v.pa_curr - 1].description.set(add_orderHolder.getChar());
                globals_0.p_items[_v.pa_curr - 1].unit_price.set(add_orderHolder.getDecimal());
              }
              _wm.displayTo(new Object[]{globals_0.p_items[_v.pa_curr - 1].stock_num}, _wm.getFldRef("s_items", "stock_num", _v.s_curr - 1));
              _wm.displayTo(new Object[]{globals_0.p_items[_v.pa_curr - 1].manu_code}, _wm.getFldRef("s_items", "manu_code", _v.s_curr - 1));
              _wm.displayTo(new Object[]{globals_0.p_items[_v.pa_curr - 1].description}, _wm.getFldRef("s_items", "description", _v.s_curr - 1));
              _wm.displayTo(new Object[]{globals_0.p_items[_v.pa_curr - 1].unit_price}, _wm.getFldRef("s_items", "unit_price", _v.s_curr - 1));
              inputArray.nextField(_wm.getFldRef("quantity"));
            }
          }
        }, _wm.getKey(KeyEvent.VK_B, KeyEvent.CTRL_MASK));
        inputArray.addFieldListener(new FieldAdapter() {
          public void afterField(java.util.EventObject _ev){
            _v.pa_curr = _wm.getArrayIndex() + 1;
            if (!Shrt.isNull(globals_0.p_items[_v.pa_curr - 1].stock_num) && !globals_0.p_items[_v.pa_curr - 1].manu_code.isNull()){
              get_item();
            }
          }
        }, _wm.getFldRef("s_items", "stock_num"), _wm.getFldRef("s_items", "manu_code"));
        inputArray.addInputListener(new InputArrayAdapter() {
          public void afterInsert(InputArrayEvent _ev){
            renum_items();
            order_total();
          }

          public void afterDelete(InputArrayEvent _ev){
            renum_items();
            order_total();
          }

          public void afterRow(InputArrayEvent _ev){
            order_total();
          }
        });
        inputArray.start();
      }
      if (_wm.isInterruptFlag()){
        _wm.setInterruptFlag(false);
        _wm.getCurrentWindow().getCurrentForm().clear();
        /**
         *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
         */
        Warning.warning("** W0146 **: Attribute COLOR(RED), REVERSE not translated. Used by: Display");
        _wm.error("Order input aborted");
        return ;
      }
      _eh.setExceptionListener(null);
      _app.getDB().beginTransaction();
      _app.getDB().executeUpdate("INSERT INTO orders (order_num, order_date, customer_num, ship_instruct, po_num) VALUES (0, ?, ?, ?, ?)", globals_0.p_orders.order_date, globals_0.p_customer.customer_num, globals_0.p_orders.ship_instruct, globals_0.p_orders.po_num);
      if (Int.less(_eh.getStatus(), 0)){
        _app.getDB().rollbackTransaction();
        /**
         *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
         */
        Warning.warning("** W0146 **: Attribute COLOR(RED), REVERSE, BLINK not translated. Used by: Display");
        _wm.error("Unable to complete update of orders table");
        return ;
      }
      globals_0.p_orders.order_num = _app.getDB().getLastSerialNumber();
      _wm.displayTo(new Object[]{globals_0.p_orders.order_num}, _wm.getFldRef("order_num"));
      if (!insert_items()){
        _app.getDB().rollbackTransaction();
        /**
         *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
         */
        Warning.warning("** W0146 **: Attribute COLOR(RED), REVERSE, BLINK not translated. Used by: Display");
        _wm.error("Unable to insert items");
        return ;
      }
      _app.getDB().commitTransaction();
      _eh.setExceptionListener(D4_Orders_0);
      getD4_Main().mess("Order added", (short)23);
      file_name.set("inv" + Int.format(globals_0.p_orders.order_num, "<<<<&") + ".out");
      invoice(file_name.stringValue());
      _wm.getCurrentWindow().getCurrentForm().clear();
    }
    catch (Exception _e){
      _eh.handleException(_e);
    }
    finally {
      _eh.setExceptionListener(previousListener);
    }
  }
  

  public void update_order(){
    ExceptionListener previousListener = _eh.getExceptionListener();
    try {
      _eh.setExceptionListener(D4_Orders_0);
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0146 **: Attribute COLOR(RED) not translated. Used by: Display");
      _wm.error("This option has not been implemented");
    }
    catch (Exception _e){
      _eh.handleException(_e);
    }
    finally {
      _eh.setExceptionListener(previousListener);
    }
  }
  

  public void delete_order(){
    ExceptionListener previousListener = _eh.getExceptionListener();
    try {
      _eh.setExceptionListener(D4_Orders_0);
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0146 **: Attribute COLOR(RED) not translated. Used by: Display");
      _wm.error("This option has not been implemented");
    }
    catch (Exception _e){
      _eh.handleException(_e);
    }
    finally {
      _eh.setExceptionListener(previousListener);
    }
  }
  

  public void order_total(){
    Decimal order_total = new Money(8, 2);
    int i = 0;
    ExceptionListener previousListener = _eh.getExceptionListener();
    try {
      _eh.setExceptionListener(D4_Orders_0);
      order_total.set(Decimal.ZERO);
      for (i = 1; i <= _wm.getArrayCount(); i += 1){
        if (!globals_0.p_items[i - 1].total_price.isNull()){
          order_total.set(order_total.add(globals_0.p_items[i - 1].total_price));
        }
      }
      order_total.set(Money.valueOf(1.1d).multiply(order_total));
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0146 **: Attribute COLOR(GREEN) not translated. Used by: Display");
      _wm.displayTo(new Object[]{order_total}, _wm.getFldRef("t_price"));
    }
    catch (Exception _e){
      _eh.handleException(_e);
    }
    finally {
      _eh.setExceptionListener(previousListener);
    }
  }
  

  public void item_total(){
    int pa_curr = 0, sc_curr = 0;
    ExceptionListener previousListener = _eh.getExceptionListener();
    try {
      _eh.setExceptionListener(D4_Orders_0);
      pa_curr = _wm.getArrayIndex() + 1;
      sc_curr = _wm.getArrayLine() + 1;
      globals_0.p_items[pa_curr - 1].total_price.set(Money.valueOf(globals_0.p_items[pa_curr - 1].quantity).multiply(globals_0.p_items[pa_curr - 1].unit_price));
      _wm.displayTo(new Object[]{globals_0.p_items[pa_curr - 1].total_price}, _wm.getFldRef("s_items", "total_price", sc_curr - 1));
    }
    catch (Exception _e){
      _eh.handleException(_e);
    }
    finally {
      _eh.setExceptionListener(previousListener);
    }
  }
  

  public void renum_items(){
    int pa_curr = 0, pa_total = 0, sc_curr = 0, sc_total = 0, k = 0;
    ExceptionListener previousListener = _eh.getExceptionListener();
    try {
      _eh.setExceptionListener(D4_Orders_0);
      pa_curr = _wm.getArrayIndex() + 1;
      pa_total = _wm.getArrayCount();
      sc_curr = _wm.getArrayLine() + 1;
      sc_total = 4;
      for (k = pa_curr; k <= pa_total; k += 1){
        globals_0.p_items[k - 1].item_num = Shrt.valueOf(k);
        if (Int.lessOrEqual(sc_curr, sc_total)){
          _wm.displayTo(new Object[]{k}, _wm.getFldRef("s_items", "item_num", sc_curr - 1));
          sc_curr += 1;
        }
      }
    }
    catch (Exception _e){
      _eh.handleException(_e);
    }
    finally {
      _eh.setExceptionListener(previousListener);
    }
  }
  

  public boolean insert_items(){
    int idx = 0;
    ExceptionListener previousListener = _eh.getExceptionListener();
    try {
      _eh.setExceptionListener(D4_Orders_0);
      for (idx = 1; idx <= _wm.getArrayCount(); idx += 1){
        if (Int.notEquals(Int.valueOf(globals_0.p_items[idx - 1].quantity), 0)){
          _app.getDB().executeUpdate("INSERT INTO items VALUES (?, ?, ?, ?, ?, ?)", globals_0.p_items[idx - 1].item_num, globals_0.p_orders.order_num, globals_0.p_items[idx - 1].stock_num, globals_0.p_items[idx - 1].manu_code, globals_0.p_items[idx - 1].quantity, globals_0.p_items[idx - 1].total_price);
          if (Int.less(_eh.getStatus(), 0)){
            return false;
          }
        }
      }
      return true;
    }
    catch (Exception _e){
      _eh.handleException(_e);
      return false;
    }
    finally {
      _eh.setExceptionListener(previousListener);
    }
  }
  

  public Object[] get_stock(){
    int idx = 0;
    //Window declarations
    Window stock_w = null;
    ExceptionListener previousListener = _eh.getExceptionListener();
    try {
      _eh.setExceptionListener(D4_Orders_0);
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0146 **: Attribute COLOR(YELLOW) not translated. Used by: Window");
      stock_w = _wm.createWindow("stock_w", _wm.getForm("com.ais.demo.form.Stock_Sel"));
      _wm.add(stock_w, 2, 6);
      _wm.setArrayCount(globals_0.stock_cnt.value);
      _wm.displayAt(0, 0, " Use cursor using F3, F4, and arrow keys; press ESC to select a stock item");
      {
        final DisplayArray displayArray = _wm.createDisplayArray(globals_0.p_stock, _wm.getArrRef("s_stock"));
        displayArray.start();
      }
      idx = _wm.getArrayIndex() + 1;
      _wm.remove(stock_w);
      return new Object[]{globals_0.p_stock[idx - 1].stock_num, globals_0.p_stock[idx - 1].manu_code.stringValue(), globals_0.p_stock[idx - 1].description.stringValue(), globals_0.p_stock[idx - 1].unit_price.toBigDecimal()};
    }
    catch (Exception _e){
      _eh.handleException(_e);
      return null;
    }
    finally {
      _eh.setExceptionListener(previousListener);
    }
  }
  

  public void get_order(){
    int idx = 0;
    boolean exist = false, chosen = false;
    Char answer = new Char(1);
    java.sql.ResultSet item_list = null;
    java.sql.PreparedStatement item_list__p = null;
    java.sql.ResultSet order_list = null;
    java.sql.PreparedStatement order_list__p = null;
    ExceptionListener previousListener = _eh.getExceptionListener();
    try {
      _eh.setExceptionListener(D4_Orders_0);
      getD4_Main().clear_menu();
      _wm.getCurrentWindow().getCurrentForm().clear();
      if (!Bool.valueOf(getD4_Cust().query_customer((short)2))){
        return ;
      }
      order_list__p = _app.getDB().prepareStatement("SELECT order_num, order_date, po_num, ship_instruct FROM orders WHERE customer_num = ?");
      exist = false;
      chosen = false;
      order_list = _app.getDB().executeQuery(order_list__p, globals_0.p_customer.customer_num);
      foreach_ : 
      while (_app.getDB().moveNext(order_list)){
        {
          Record _rec = new Record(order_list);
          try {
            globals_0.p_orders.set(_rec);
          }
          catch (NoMoreValuesException _e){
          }
        }
        exist = true;
        _wm.clearField(_wm.getRecRef("orders"));
        for (idx = 1; idx <= 4; idx += 1){
          _wm.clearField(_wm.getRecRef("s_items", idx - 1));
        }
        _wm.displayTo(new Object[]{globals_0.p_orders}, _wm.getRecRef("orders"));
        if (item_list__p == null){
          item_list__p = _app.getDB().prepareStatement("SELECT item_num, items.stock_num, items.manu_code, description, quantity, unit_price, total_price FROM items, stock WHERE (order_num = ? AND items.stock_num = stock.stock_num AND items.manu_code = stock.manu_code) ORDER BY item_num");
        }
        idx = 1;
        item_list = _app.getDB().executeQuery(item_list__p, globals_0.p_orders.order_num);
        foreach__ : 
        while (_app.getDB().moveNext(item_list)){
          {
            Record _rec = new Record(item_list);
            try {
              globals_0.p_items[idx - 1].set(_rec);
            }
            catch (NoMoreValuesException _e){
            }
          }
          idx += 1;
          if (Int.greater(idx, 10)){
            /**
             *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
             */
            Warning.warning("** W0146 **: Attribute COLOR(RED), REVERSE not translated. Used by: Display");
            _wm.error("More than 10 items; only 10 items displayed");
            _eh.setStatus(0);
            break foreach__;
          }
        }
        _app.getDB().close(item_list);
        item_list = null;
        _wm.setArrayCount(idx - 1);
        order_total();
        _wm.message("Press ESC when you finish viewing the items");
        /**
         *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
         */
        Warning.warning("** W0146 **: Attribute COLOR(CYAN) not translated. Used by: Display array statement");
        {
          final DisplayArray displayArray = _wm.createDisplayArray(globals_0.p_items, _wm.getArrRef("s_items"));
          displayArray.start();
        }
        _wm.message("");
        if (_wm.isInterruptFlag()){
          _wm.setInterruptFlag(false);
          _eh.setStatus(0);
          break foreach_;
        }
        {
          final Prompt prompt = _wm.createPrompt(" Enter 'y' to select this order or RETURN to view next order: ", true);
          prompt.start();
          answer.set(prompt.getPrompt());
        }
        if (answer.matches("[yY]")){
          chosen = true;
          _eh.setStatus(0);
          break foreach_;
        }
      }
      _app.getDB().close(order_list);
      order_list = null;
      if (!exist){
        /**
         *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
         */
        Warning.warning("** W0146 **: Attribute COLOR(RED) not translated. Used by: Display");
        _wm.error("No orders found for this customer");
      }
      else if (!chosen){
        _wm.getCurrentWindow().getCurrentForm().clear();
        /**
         *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
         */
        Warning.warning("** W0146 **: Attribute COLOR(RED) not translated. Used by: Display");
        _wm.error("No order selected for this customer");
      }
    }
    catch (Exception _e){
      _eh.handleException(_e);
    }
    finally {
      DBHelper _db = _app.getDB();
      if (_db != null){
        _db.close(order_list);
        order_list = null;
        _db.close(order_list__p);
        order_list__p = null;
        _db.close(item_list);
        item_list = null;
        _db.close(item_list__p);
        item_list__p = null;
      }
      _eh.setExceptionListener(previousListener);
    }
  }
  

  public void get_item(){
    int pa_curr = 0, sc_curr = 0;
    ExceptionListener previousListener = _eh.getExceptionListener();
    try {
      _eh.setExceptionListener(D4_Orders_0);
      pa_curr = _wm.getArrayIndex() + 1;
      sc_curr = _wm.getArrayLine() + 1;
      {
        Record _rec = _app.getDB().selectInto("SELECT description, unit_price FROM stock WHERE (stock.stock_num = ? AND stock.manu_code = ?)", globals_0.p_items[pa_curr - 1].stock_num, globals_0.p_items[pa_curr - 1].manu_code);
        if (_rec != null){
          try {
            globals_0.p_items[pa_curr - 1].description.set(_rec.getChar());
            globals_0.p_items[pa_curr - 1].unit_price.set(_rec.getDecimal());
          }
          catch (NoMoreValuesException _e){
          }
        }
      }
      if (Bool.valueOf(_eh.getStatus())){
        globals_0.p_items[pa_curr - 1].description.setNull();
        globals_0.p_items[pa_curr - 1].unit_price.setNull();
      }
      _wm.displayTo(new Object[]{globals_0.p_items[pa_curr - 1].description, globals_0.p_items[pa_curr - 1].unit_price}, _wm.getFldRef("s_items", "description", sc_curr - 1), _wm.getFldRef("s_items", "unit_price", sc_curr - 1));
      if (!Shrt.isNull(globals_0.p_items[pa_curr - 1].quantity)){
        item_total();
      }
    }
    catch (Exception _e){
      _eh.handleException(_e);
    }
    finally {
      _eh.setExceptionListener(previousListener);
    }
  }
  

  public void invoice(String file_name_){
    X_Invoice x_invoice = new X_Invoice();
    Char file_name = new Char(20).set(file_name_);
    Char msg = new Char(40);
    java.sql.ResultSet invoice_data = null;
    java.sql.PreparedStatement invoice_data__p = null;
    ExceptionListener previousListener = _eh.getExceptionListener();
    try {
      _eh.setExceptionListener(D4_Orders_0);
      invoice_data__p = _app.getDB().prepareStatement("SELECT o.order_num, order_date, ship_instruct, backlog, po_num, ship_date, ship_weight, ship_charge, item_num, i.stock_num, i.manu_code, quantity, total_price, s.description, unit_price, unit, unit_descr, manu_name FROM orders o, items i, stock s, manufact m WHERE (o.order_num = ? AND i.order_num = ? AND i.stock_num = s.stock_num AND i.manu_code = s.manu_code AND i.manu_code = m.manu_code) ORDER BY 9");
      if (Str.equals(Str.valueOf(globals_0.print_option), "f")){
        /**
         *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
         */
        Warning.warning("** W0108 **: The use of report-to-file must be reviewed.");
        {
          Report _rep = (Report)createReportR_Invoice();
          _rep.setOutputToFile(Str.valueOf(file_name));
          _rep.start();
        }
        getD4_Main().clear_menu();
        _wm.message("Writing invoice -- please wait");
      }
      else if (Str.equals(Str.valueOf(globals_0.print_option), "p")){
        {
          Report _rep = (Report)createReportR_Invoice();
          _rep.setOutputToPrinter();
          _rep.start();
        }
        getD4_Main().clear_menu();
        _wm.message("Writing invoice -- please wait");
      }
      else if (Str.equals(Str.valueOf(globals_0.print_option), "s")){
        ((Report)createReportR_Invoice()).start();
      }
      invoice_data = _app.getDB().executeQuery(invoice_data__p, globals_0.p_orders.order_num, globals_0.p_orders.order_num);
      while (_app.getDB().moveNext(invoice_data)){
        {
          Record _rec = new Record(invoice_data);
          try {
            x_invoice.set(_rec);
          }
          catch (NoMoreValuesException _e){
          }
        }
        outputReportR_Invoice(new Object[]{globals_0.p_customer, x_invoice});
      }
      _app.getDB().close(invoice_data);
      invoice_data = null;
      finishReportR_Invoice();
      if (Str.equals(Str.valueOf(globals_0.print_option), "f")){
        msg.set("Invoice written to file " + file_name.rTrim());
        getD4_Main().mess(msg.stringValue(), (short)23);
      }
    }
    catch (Exception _e){
      _eh.handleException(_e);
    }
    finally {
      DBHelper _db = _app.getDB();
      if (_db != null){
        _db.close(invoice_data);
        invoice_data = null;
        _db.close(invoice_data__p);
        invoice_data__p = null;
      }
      _eh.setExceptionListener(previousListener);
    }
  }
  
  private ReportR_Invoice r_invoice;

  public Report createReportR_Invoice(){
    if (r_invoice == null){
      r_invoice = new ReportR_Invoice();
    }
    return r_invoice;
  }
  

  public void finishReportR_Invoice(){
    r_invoice.execute();
    r_invoice = null;
  }
  

  public void outputReportR_Invoice(Object... row){
    r_invoice.outputTo(new Record(row));
  }
  
  class ReportR_Invoice extends Report{
    //Declaration LIKE customer
    Customer c = new Customer();
    X_Invoice x = new X_Invoice();
    Decimal sales_tax = new Money(8, 2), calc_total = new Money(8, 2);
    public ReportR_Invoice(){
      super(_eh, _app.getReportOutputFactory());
      setReportTabSize(Int.valueOf(_app.getProperty("application.report.tabsize")));
      setFieldNames("c_customer_num", "c_fname", "c_lname", "c_company", "c_address1", "c_address2", "c_city", "c_state", "c_zipcode", "c_phone", "x_order_num", "x_order_date", "x_ship_instruct", "x_backlog", "x_po_num", "x_ship_date", "x_ship_weight", "x_ship_charge", "x_item_num", "x_stock_num", "x_manu_code", "x_quantity", "x_total_price", "x_description", "x_unit_price", "x_unit", "x_unit_descr", "x_manu_name");
      setGroupHierarchy(new String[]{"x_order_num"}, new boolean[]{false});
      setUsedGroups("x_order_num", "x_order_num");
      setLeftMargin(0);
      setRightMargin(0);
      setTopMargin(1);
      setBottomMargin(1);
      setPageLength(48);
    }
    

    public void onParamLoad(Record row){
      c.set(row);
      x.set(row);
    }
    

    public void onEveryRow(){
      print(Shrt.format(x.item_num, "#&"), "  ", Shrt.format(x.stock_num, "&&"), "  ", x.manu_code);
      print("  ", x.description, "  ", Shrt.format(x.quantity, "###&"), "  ");
      print(x.unit_price.format("$$$&.&&"), "  ", x.unit, "  ", x.unit_descr, "  ");
      println(x.total_price.format("$$$$$$$&.&&"));
      calc_total.set(calc_total.add(x.total_price));
    }
    

    public void onBeforeGroup(String groupName){
      if (groupName.equals("x_order_num")){
        skipToNewPage();
        skipLines(1);
        println(Str.spaces(10), "   W E S T   C O A S T   W H O L E S A L E R S ,   I N C .");
        println(Str.spaces(30), " 1400 Hanbonon Drive");
        println(Str.spaces(30), "Menlo Park, CA  94025");
        skipLines(1);
        print("Bill To:");
        goToColumn(10);
        print(c.fname.rTrim(), " ", c.lname.rTrim());
        goToColumn(56);
        println("Invoice No.        ", Int.format(x.order_num, "&&&&&"));
        goToColumn(10);
        println(c.company);
        goToColumn(10);
        print(c.address1.rTrim());
        goToColumn(56);
        println("Invoice Date: ", x.order_date);
        goToColumn(10);
        print(c.address2.rTrim());
        goToColumn(56);
        println("Customer No.       ", Int.format(c.customer_num, "####&"));
        goToColumn(10);
        print(c.city.rTrim(), ", ", c.state.rTrim(), "   ", c.zipcode.rTrim());
        goToColumn(56);
        println("PO No. ", x.po_num);
        goToColumn(10);
        print(c.phone.rTrim());
        goToColumn(56);
        println("Backlog Status: ", x.backlog);
        skipLines(1);
        goToColumn(10);
        println("Shipping Instructions: ", x.ship_instruct);
        goToColumn(10);
        print("Ship Date: ", x.ship_date.format("ddd. mmm dd, yyyy"));
        println("   Weight: ", x.ship_weight.format("#####&.&&"));
        skipLines(1);
        print("----------------------------------------");
        println("---------------------------------------");
        print("   Stock                              Unit        ");
        println("                       Item ");
        print(" #  Num Man    Description      Qty   Cost   Unit ");
        println(" Unit Description      Total");
        skipLines(1);
        calc_total.set(Decimal.ZERO);
      }
    }
    

    public void onAfterGroup(String groupName){
      if (groupName.equals("x_order_num")){
        skipLines(1);
        print("----------------------------------------");
        println("---------------------------------------");
        goToColumn(50);
        println("        Sub-total: ", calc_total.format("$$$$$$$&.&&"));
        sales_tax.set(Money.valueOf(0.065d).multiply(calc_total));
        x.ship_charge.set(Money.valueOf(0.035d).multiply(calc_total));
        goToColumn(45);
        println("Shipping Charge (3.5%): ", x.ship_charge.format("$$$$$$$&.&&"));
        goToColumn(50);
        println(" Sales Tax (6.5%): ", sales_tax.format("$$$$$$$&.&&"));
        goToColumn(50);
        println("                   -----------");
        calc_total.set(calc_total.add(x.ship_charge).add(sales_tax));
        goToColumn(50);
        println("            Total: ", calc_total.format("$$$$$$$&.&&"));
        if (Str.equals(Str.valueOf(globals_0.print_option), "s")){
          /**
           *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
           */
          Warning.warning("** W0123 **: PAUSE statement not translated.");
        }
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
  
  com.ais.demo.ID4_Main d4_main;

  public com.ais.demo.ID4_Main getD4_Main(){
    if (this.d4_main == null){
      return this.d4_main = (com.ais.demo.ID4_Main)_app.getModule("com.ais.demo.D4_Main");
    }
    else {
      return this.d4_main;
    }
  }
  

  public void setD4_Main(com.ais.demo.ID4_Main _D4_Main){
    this.d4_main = _D4_Main;
  }

}