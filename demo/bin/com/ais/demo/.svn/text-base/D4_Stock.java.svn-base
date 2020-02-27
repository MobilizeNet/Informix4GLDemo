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

public  class D4_Stock implements ID4_Stock {
  /**
   *  D4_Stock constructor
   */
  public D4_Stock(Application owner){
    _app = owner;
    _wm = _app.getUIHelper();
    _eh = _app.getErrorHandler();
  }
  
  /**
   *  Application instances
   */
  protected Application _app;
  protected UIHelper _wm;
  protected ErrorHandler _eh;

  public void stock(){
    final Menu menu = _wm.createMenu("STOCK");
    menu.addCommandListener(_wm.createCommand("Add-stock", "Add new stock items to database", 401), new InputAction() {
      public void actionPerformed(java.util.EventObject _ae){
        add_stock();
      }
    });
    menu.addCommandListener(_wm.createCommand("Find-stock", "Look up specific stock item", 402), new InputAction() {
      public void actionPerformed(java.util.EventObject _ae){
        query_stock();
      }
    });
    menu.addCommandListener(_wm.createCommand("Update-stock", "Modify current stock information", 403), new InputAction() {
      public void actionPerformed(java.util.EventObject _ae){
        update_stock();
      }
    });
    menu.addCommandListener(_wm.createCommand("Delete-stock", "Remove a stock item from database", 404), new InputAction() {
      public void actionPerformed(java.util.EventObject _ae){
        delete_stock();
      }
    });
    menu.addCommandListener(_wm.createCommand("Exit", "Return to MAIN Menu", 405), new InputAction() {
      public void actionPerformed(java.util.EventObject _ae){
        _wm.setCurrent(_wm.getScreen());
        _wm.getScreen().clear();
        menu.stop();
      }
    });
    menu.start();
  }
  

  public void add_stock(){
    /**
     *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
     */
    Warning.warning("** W0146 **: Attribute COLOR(RED) not translated. Used by: Display");
    _wm.error("This option has not been implemented");
  }
  

  public void query_stock(){
    /**
     *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
     */
    Warning.warning("** W0146 **: Attribute COLOR(RED) not translated. Used by: Display");
    _wm.error("This option has not been implemented");
  }
  

  public void update_stock(){
    /**
     *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
     */
    Warning.warning("** W0146 **: Attribute COLOR(RED) not translated. Used by: Display");
    _wm.error("This option has not been implemented");
  }
  

  public void delete_stock(){
    /**
     *  @todo: Please review the following warnings thoroughly, they were generated during the migration process
     */
    Warning.warning("** W0146 **: Attribute COLOR(RED) not translated. Used by: Display");
    _wm.error("This option has not been implemented");
  }

}
