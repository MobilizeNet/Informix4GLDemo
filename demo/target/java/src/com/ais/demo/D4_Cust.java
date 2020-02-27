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

public  class D4_Cust implements ID4_Cust {
  /**
   *  D4_Cust constructor
   */
  public D4_Cust(Application owner){
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
  public java.sql.ResultSet customer_set = null;
  protected D4_Globals globals_0 = null;

  public void customer(){
    _wm.setFormLine(6);
    _app.putProperty("form.customer", "com.ais.demo.form.Custform");
    /**
     *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
     */
    Warning.warning("** W0146 **: Attribute COLOR(MAGENTA) not translated. Used by: Display form");
    _wm.getCurrentWindow().add(_app.getProperty("form.customer"), com.ais.fc.ix.ui.Window.FORM_CONSTRAINT);
    getD4_Main().ring_menu();
    /**
     *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
     */
    Warning.warning("** W0148 **: Draw box statement not supported. fgl_drawbox(3,32,3,42)");
    /**
     *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
     */
    Warning.warning("** W0148 **: Draw box statement not supported. fgl_drawbox(3,61,8,7)");
    /**
     *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
     */
    Warning.warning("** W0148 **: Draw box statement not supported. fgl_drawbox(11,61,8,7)");
    globals_0.p_customer.customer_num = Int.getNull();
    {
      final Menu menu = _wm.createMenu("CUSTOMER");
      menu.addCommandListener(_wm.createCommand("One-add", "Add a new customer to the database", 201), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          getD4_Main().unring_menu();
          add_customer(0);
          getD4_Main().ring_menu();
        }
      });
      menu.addCommandListener(_wm.createCommand("Many-add", "Add several new customers to database", 202), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          getD4_Main().unring_menu();
          add_customer(1);
          getD4_Main().ring_menu();
        }
      });
      menu.addCommandListener(_wm.createCommand("Find-cust", "Look up specific customer", 203), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          getD4_Main().unring_menu();
          if (Bool.valueOf(query_customer((short)23))){
            getD4_Main().ring_menu();
            menu.nextOption("Update-cust");
          }
          getD4_Main().ring_menu();
        }
      });
      menu.addCommandListener(_wm.createCommand("Update-cust", "Modify current customer information", 204), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          getD4_Main().unring_menu();
          update_customer();
          getD4_Main().ring_menu();
          menu.nextOption("Find-cust");
        }
      });
      menu.addCommandListener(_wm.createCommand("Delete-cust", "Remove a customer from database", 205), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          getD4_Main().unring_menu();
          delete_customer();
          getD4_Main().ring_menu();
          menu.nextOption("Find-cust");
        }
      });
      menu.addCommandListener(_wm.createCommand("Exit", "Return to MAIN Menu", 206), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          _wm.setCurrent(_wm.getScreen());
          _wm.getScreen().clear();
          menu.stop();
        }
      });
      menu.start();
    }
    _wm.setFormLine(2);
  }
  

  public void add_customer(int repeat){
    getD4_Main().clear_menu();
    _wm.message("Press F1 or CTRL-F for field help; ", "F2 or CTRL-Y to return to menu");
    if (Bool.valueOf(repeat)){
      while (input_cust()){
        /**
         *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
         */
        Warning.warning("** W0146 **: Attribute COLOR(GREEN) not translated. Used by: Display");
        _wm.error("Customer data entered");
      }
      getD4_Main().mess("Multiple insert completed - current screen values ignored", (short)23);
    }
    else if (input_cust()){
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0146 **: Attribute COLOR(GREEN) not translated. Used by: Display");
      _wm.error("Customer data entered");
    }
    else {
      _wm.getCurrentWindow().getCurrentForm().clear();
      globals_0.p_customer.customer_num = Int.getNull();
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0146 **: Attribute COLOR(RED), REVERSE not translated. Used by: Display");
      _wm.error("Customer addition aborted");
    }
  }
  

  public boolean input_cust(){
    _wm.displayAt(0, 0, "Press ESC to enter new customer data");
    {
      final Input input = _wm.createInput(false, new Object[]{globals_0.p_customer}, _wm.getFldRef("customer_num"), _wm.getFldRef("fname"), _wm.getFldRef("lname"), _wm.getFldRef("company"), _wm.getFldRef("address1"), _wm.getFldRef("address2"), _wm.getFldRef("city"), _wm.getFldRef("state"), _wm.getFldRef("zipcode"), _wm.getFldRef("phone"));
      input.addFieldListener(new FieldAdapter() {
        public void afterField(java.util.EventObject _ev){
          statehelp();
          _wm.displayAt(0, 0, "Press ESC to enter new customer data", "");
        }
      }, _wm.getFldRef("state"));
      input.addKeyAction(new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          customer_help();
        }
      }, _wm.getKey(KeyEvent.VK_F1), _wm.getKey(KeyEvent.VK_F, KeyEvent.CTRL_MASK));
      input.addKeyAction(new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          _wm.setInterruptFlag(true);
          input.stop();
        }
      }, _wm.getKey(KeyEvent.VK_F2), _wm.getKey(KeyEvent.VK_Y, KeyEvent.CTRL_MASK));
      input.start();
    }
    if (_wm.isInterruptFlag()){
      _wm.setInterruptFlag(false);
      return false;
    }
    globals_0.p_customer.customer_num = (int)0;
    _app.getDB().executeUpdate("INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", globals_0.p_customer);
    globals_0.p_customer.customer_num = _app.getDB().getLastSerialNumber();
    /**
     *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
     */
    Warning.warning("** W0146 **: Attribute COLOR(MAGENTA) not translated. Used by: Display");
    _wm.displayTo(new Object[]{globals_0.p_customer.customer_num}, _wm.getFldRef("customer_num"));
    return true;
  }
  

  public int query_customer(short mrow){
    //The following variable acts as a holder for all the variables taken out to the class Query_CustomerVariables
    final Query_CustomerVariables _v = new Query_CustomerVariables();
    Char where_part = new Char(500);
    Char query_text = new Char(500);
    Char answer = new Char(1);
    boolean exist = false;
    java.sql.PreparedStatement statement_1 = null;
    _wm.getCurrentWindow().getCurrentForm().clear();
    getD4_Main().clear_menu();
    _wm.message("Enter criteria for selection");
    {
      final Construct construct = _wm.createConstruct(new String[]{"customer.customer_num", "customer.fname", "customer.lname", "customer.company", "customer.address1", "customer.address2", "customer.city", "customer.state", "customer.zipcode", "customer.phone"}, _wm.getRecRef("customer"));
      construct.start();
      where_part.set(construct.getQuery());
    }
    _wm.message("");
    if (_wm.isInterruptFlag()){
      _wm.setInterruptFlag(false);
      _wm.getCurrentWindow().getCurrentForm().clear();
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0146 **: Attribute COLOR(RED), REVERSE not translated. Used by: Display");
      _wm.error("Customer query aborted");
      globals_0.p_customer.customer_num = Int.getNull();
      return globals_0.p_customer.customer_num;
    }
    query_text.set("select * from customer where " + where_part.rTrim() + " order by lname");
    statement_1 = _app.getDB().prepareStatement(Str.valueOf(query_text), java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY);
    if (customer_set != null){
      _app.getDB().close(customer_set);
    }
    customer_set = _app.getDB().executeQuery(statement_1);
    if (_app.getDB().moveFirst(customer_set)){
      Record _rec = new Record(customer_set);
      try {
        globals_0.p_customer.set(_rec);
      }
      catch (NoMoreValuesException _e){
      }
    }
    if (_eh.getStatus() == 100){
      exist = false;
    }
    else {
      exist = true;
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0146 **: Attribute COLOR(MAGENTA) not translated. Used by: Display");
      _wm.displayTo(new Object[]{globals_0.p_customer}, _wm.getFldRef("customer_num"), _wm.getFldRef("fname"), _wm.getFldRef("lname"), _wm.getFldRef("company"), _wm.getFldRef("address1"), _wm.getFldRef("address2"), _wm.getFldRef("city"), _wm.getFldRef("state"), _wm.getFldRef("zipcode"), _wm.getFldRef("phone"));
      {
        final Menu menu = _wm.createMenu("BROWSE");
        menu.addCommandListener(_wm.createCommand("Next", "View the next customer in the list"), new InputAction() {
          public void actionPerformed(java.util.EventObject _ae){
            if (_app.getDB().moveNext(customer_set)){
              Record _rec = new Record(customer_set);
              try {
                globals_0.p_customer.set(_rec);
              }
              catch (NoMoreValuesException _e){
              }
            }
            if (_eh.getStatus() == 100){
              /**
               *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
               */
              Warning.warning("** W0146 **: Attribute COLOR(RED), REVERSE not translated. Used by: Display");
              _wm.error("No more customers in this direction");
              if (_app.getDB().moveLast(customer_set)){
                Record _rec = new Record(customer_set);
                try {
                  globals_0.p_customer.set(_rec);
                }
                catch (NoMoreValuesException _e){
                }
              }
            }
            /**
             *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
             */
            Warning.warning("** W0146 **: Attribute COLOR(MAGENTA) not translated. Used by: Display");
            _wm.displayTo(new Object[]{globals_0.p_customer}, _wm.getFldRef("customer_num"), _wm.getFldRef("fname"), _wm.getFldRef("lname"), _wm.getFldRef("company"), _wm.getFldRef("address1"), _wm.getFldRef("address2"), _wm.getFldRef("city"), _wm.getFldRef("state"), _wm.getFldRef("zipcode"), _wm.getFldRef("phone"));
          }
        });
        menu.addCommandListener(_wm.createCommand("Previous", "View the previous customer in the list"), new InputAction() {
          public void actionPerformed(java.util.EventObject _ae){
            if (_app.getDB().movePrior(customer_set)){
              Record _rec = new Record(customer_set);
              try {
                globals_0.p_customer.set(_rec);
              }
              catch (NoMoreValuesException _e){
              }
            }
            if (_eh.getStatus() == 100){
              /**
               *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
               */
              Warning.warning("** W0146 **: Attribute COLOR(RED), REVERSE not translated. Used by: Display");
              _wm.error("No more customers in this direction");
              if (_app.getDB().moveFirst(customer_set)){
                Record _rec = new Record(customer_set);
                try {
                  globals_0.p_customer.set(_rec);
                }
                catch (NoMoreValuesException _e){
                }
              }
            }
            /**
             *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
             */
            Warning.warning("** W0146 **: Attribute COLOR(MAGENTA) not translated. Used by: Display");
            _wm.displayTo(new Object[]{globals_0.p_customer}, _wm.getFldRef("customer_num"), _wm.getFldRef("fname"), _wm.getFldRef("lname"), _wm.getFldRef("company"), _wm.getFldRef("address1"), _wm.getFldRef("address2"), _wm.getFldRef("city"), _wm.getFldRef("state"), _wm.getFldRef("zipcode"), _wm.getFldRef("phone"));
          }
        });
        menu.addCommandListener(_wm.createCommand("First", "View the first customer in the list"), new InputAction() {
          public void actionPerformed(java.util.EventObject _ae){
            if (_app.getDB().moveFirst(customer_set)){
              Record _rec = new Record(customer_set);
              try {
                globals_0.p_customer.set(_rec);
              }
              catch (NoMoreValuesException _e){
              }
            }
            /**
             *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
             */
            Warning.warning("** W0146 **: Attribute COLOR(MAGENTA) not translated. Used by: Display");
            _wm.displayTo(new Object[]{globals_0.p_customer}, _wm.getFldRef("customer_num"), _wm.getFldRef("fname"), _wm.getFldRef("lname"), _wm.getFldRef("company"), _wm.getFldRef("address1"), _wm.getFldRef("address2"), _wm.getFldRef("city"), _wm.getFldRef("state"), _wm.getFldRef("zipcode"), _wm.getFldRef("phone"));
          }
        });
        menu.addCommandListener(_wm.createCommand("Last", "View the last customer in the list"), new InputAction() {
          public void actionPerformed(java.util.EventObject _ae){
            if (_app.getDB().moveLast(customer_set)){
              Record _rec = new Record(customer_set);
              try {
                globals_0.p_customer.set(_rec);
              }
              catch (NoMoreValuesException _e){
              }
            }
            /**
             *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
             */
            Warning.warning("** W0146 **: Attribute COLOR(MAGENTA) not translated. Used by: Display");
            _wm.displayTo(new Object[]{globals_0.p_customer}, _wm.getFldRef("customer_num"), _wm.getFldRef("fname"), _wm.getFldRef("lname"), _wm.getFldRef("company"), _wm.getFldRef("address1"), _wm.getFldRef("address2"), _wm.getFldRef("city"), _wm.getFldRef("state"), _wm.getFldRef("zipcode"), _wm.getFldRef("phone"));
          }
        });
        menu.addCommandListener(_wm.createCommand("Select", "Exit BROWSE selecting the current customer"), new InputAction() {
          public void actionPerformed(java.util.EventObject _ae){
            _v.chosen = true;
            menu.stop();
          }
        });
        menu.addCommandListener(_wm.createCommand("Quit", "Quit BROWSE without selecting a customer"), new InputAction() {
          public void actionPerformed(java.util.EventObject _ae){
            _v.chosen = false;
            menu.stop();
          }
        });
        menu.start();
      }
    }
    _app.getDB().close(customer_set);
    customer_set = null;
    getD4_Main().clear_menu();
    if (!exist){
      _wm.getCurrentWindow().getCurrentForm().clear();
      getD4_Main().mess("No customer satisfies query", mrow);
      globals_0.p_customer.customer_num = Int.getNull();
      return 0;
    }
    if (!_v.chosen){
      _wm.getCurrentWindow().getCurrentForm().clear();
      globals_0.p_customer.customer_num = Int.getNull();
      getD4_Main().mess("No selection made", mrow);
      return 0;
    }
    return 1;
  }
  

  public void update_customer(){
    getD4_Main().clear_menu();
    if (Int.isNull(globals_0.p_customer.customer_num)){
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0146 **: Attribute COLOR(RED), REVERSE not translated. Used by: Display");
      _wm.error("No customer has been selected; use the Find-cust option");
      return ;
    }
    _wm.message("Press F1 or CTRL-F for field-level help");
    _wm.displayAt(0, 0, "Press ESC to update customer data; DEL to abort");
    {
      final Input input = _wm.createInput(true, new Object[]{globals_0.p_customer}, _wm.getFldRef("customer_num"), _wm.getFldRef("fname"), _wm.getFldRef("lname"), _wm.getFldRef("company"), _wm.getFldRef("address1"), _wm.getFldRef("address2"), _wm.getFldRef("city"), _wm.getFldRef("state"), _wm.getFldRef("zipcode"), _wm.getFldRef("phone"));
      input.addFieldListener(new FieldAdapter() {
        public void afterField(java.util.EventObject _ev){
          statehelp();
          _wm.displayAt(0, 0, "Press ESC to update customer data; DEL to abort", "");
        }
      }, _wm.getFldRef("state"));
      input.addKeyAction(new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          customer_help();
        }
      }, _wm.getKey(KeyEvent.VK_F1), _wm.getKey(KeyEvent.VK_F, KeyEvent.CTRL_MASK));
      input.start();
    }
    if (!_wm.isInterruptFlag()){
      _app.getDB().executeUpdate("UPDATE customer SET (fname, lname, company, address1, address2, city, state, zipcode, phone) = (?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE customer_num = ?", globals_0.p_customer.fname, globals_0.p_customer.lname, globals_0.p_customer.company, globals_0.p_customer.address1, globals_0.p_customer.address2, globals_0.p_customer.city, globals_0.p_customer.state, globals_0.p_customer.zipcode, globals_0.p_customer.phone, globals_0.p_customer.customer_num);
      getD4_Main().mess("Customer data modified", (short)23);
    }
    else {
      _wm.setInterruptFlag(false);
      {
        Record _rec = _app.getDB().selectInto("SELECT * FROM customer WHERE customer_num = ?", globals_0.p_customer.customer_num);
        if (_rec != null){
          try {
            globals_0.p_customer.set(_rec);
          }
          catch (NoMoreValuesException _e){
          }
        }
      }
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0146 **: Attribute COLOR(MAGENTA) not translated. Used by: Display");
      _wm.displayTo(new Object[]{globals_0.p_customer}, _wm.getFldRef("customer_num"), _wm.getFldRef("fname"), _wm.getFldRef("lname"), _wm.getFldRef("company"), _wm.getFldRef("address1"), _wm.getFldRef("address2"), _wm.getFldRef("city"), _wm.getFldRef("state"), _wm.getFldRef("zipcode"), _wm.getFldRef("phone"));
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0146 **: Attribute COLOR(RED), REVERSE not translated. Used by: Display");
      _wm.error("Customer update aborted");
    }
  }
  

  public void delete_customer(){
    Char answer = new Char(1);
    int num_orders = 0;
    getD4_Main().clear_menu();
    if (Int.isNull(globals_0.p_customer.customer_num)){
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0146 **: Attribute COLOR(RED), REVERSE not translated. Used by: Display");
      _wm.error("No customer has been selected; use the Find-cust option");
      return ;
    }
    {
      Record _rec = _app.getDB().selectInto("SELECT COUNT(*) FROM orders WHERE customer_num = ?", globals_0.p_customer.customer_num);
      if (_rec != null){
        try {
          num_orders = _rec.getInt();
        }
        catch (NoMoreValuesException _e){
        }
      }
    }
    if (Bool.valueOf(num_orders)){
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0146 **: Attribute COLOR(RED), REVERSE not translated. Used by: Display");
      _wm.error("This customer has active orders and can not be removed");
      return ;
    }
    {
      final Prompt prompt = _wm.createPrompt(" Are you sure you want to delete this customer row? ", true);
      prompt.start();
      answer.set(prompt.getPrompt());
    }
    if (answer.matches("[yY]")){
      _app.getDB().executeUpdate("DELETE FROM customer WHERE customer_num = ?", globals_0.p_customer.customer_num);
      _wm.getCurrentWindow().getCurrentForm().clear();
      getD4_Main().mess("Customer entry deleted", (short)23);
      globals_0.p_customer.customer_num = Int.getNull();
    }
    else {
      /**
       *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
       */
      Warning.warning("** W0146 **: Attribute COLOR(RED), REVERSE not translated. Used by: Display");
      _wm.error("Deletion aborted");
    }
  }
  

  public void customer_help(){
    if (_wm.inField(_wm.getFldRef("customer_num"))){
      _wm.showHelp(1001);
    }
    else if (_wm.inField(_wm.getFldRef("fname"))){
      _wm.showHelp(1002);
    }
    else if (_wm.inField(_wm.getFldRef("lname"))){
      _wm.showHelp(1003);
    }
    else if (_wm.inField(_wm.getFldRef("company"))){
      _wm.showHelp(1004);
    }
    else if (_wm.inField(_wm.getFldRef("address1"))){
      _wm.showHelp(1005);
    }
    else if (_wm.inField(_wm.getFldRef("address2"))){
      _wm.showHelp(1006);
    }
    else if (_wm.inField(_wm.getFldRef("city"))){
      _wm.showHelp(1007);
    }
    else if (_wm.inField(_wm.getFldRef("state"))){
      _wm.showHelp(1008);
    }
    else if (_wm.inField(_wm.getFldRef("zipcode"))){
      _wm.showHelp(1009);
    }
    else if (_wm.inField(_wm.getFldRef("phone"))){
      _wm.showHelp(1010);
    }
  }
  

  public void statehelp(){
    int idx = 0;
    //Window declarations
    Window w_state = null;
    {
      Record _rec = _app.getDB().selectInto("SELECT COUNT(*) FROM state WHERE code = ?", globals_0.p_customer.state);
      if (_rec != null){
        try {
          idx = _rec.getInt();
        }
        catch (NoMoreValuesException _e){
        }
      }
    }
    if (idx == 1){
      return ;
    }
    _wm.displayAt(0, 0, "Move cursor using F3, F4, and arrow keys; press ESC to select state");
    /**
     *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
     */
    Warning.warning("** W0146 **: Attribute COLOR(RED) not translated. Used by: Window");
    w_state = _wm.createWindow("w_state", _wm.getForm("com.ais.demo.form.State_List"));
    w_state.setFormLine(1);
    _wm.add(w_state, 36, 7);
    _wm.setArrayCount(globals_0.state_cnt.value);
    {
      final DisplayArray displayArray = _wm.createDisplayArray(globals_0.p_state, _wm.getArrRef("s_state"));
      displayArray.start();
    }
    idx = _wm.getArrayIndex() + 1;
    _wm.remove(w_state);
    globals_0.p_customer.state.set(globals_0.p_state[idx - 1].code);
    /**
     *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
     */
    Warning.warning("** W0146 **: Attribute COLOR(MAGENTA) not translated. Used by: Display");
    _wm.displayTo(new Object[]{globals_0.p_customer.state}, _wm.getFldRef("state"));
    return ;
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
