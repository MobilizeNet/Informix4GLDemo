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

public  class D4_Demo implements ID4_Demo {
  /**
   *  D4_Demo constructor
   */
  public D4_Demo(Application owner){
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

  public void demo(){
    getD4_Main().ring_menu();
    {
      final Menu menu = _wm.createMenu("DEMO");
      menu.addCommandListener(_wm.createCommand("Menus", "Source code for MAIN Menu"), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          _wm.showHelp(2001);
        }
      });
      menu.addCommandListener(_wm.createCommand("Windows", "Source code for STATE CODE Window"), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          _wm.showHelp(2007);
        }
      });
      menu.addCommandListener(_wm.createCommand("Forms", "Source code for new CUSTOMER data entry"), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          _wm.showHelp(2006);
        }
      });
      menu.addCommandListener(_wm.createCommand("Detail-Scrolling", "Source code for scrolling of new ORDER line-items"), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          _wm.showHelp(2003);
        }
      });
      menu.addCommandListener(_wm.createCommand("Scroll-Cursor", "Source code for customer record BROWSE/SCROLL"), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          _wm.showHelp(2008);
        }
      });
      menu.addCommandListener(_wm.createCommand("Query_language", "Source code for new order insertion using SQL"), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          _wm.showHelp(2004);
        }
      });
      menu.addCommandListener(_wm.createCommand("Construct_query", "Source code for QUERY-BY-EXAMPLE selection and reporting"), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          _wm.showHelp(2002);
        }
      });
      menu.addCommandListener(_wm.createCommand("Reports", "Source code for MAILING LABEL report"), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          _wm.showHelp(2005);
        }
      });
      menu.addCommandListener(_wm.createCommand("Exit", "Return to MAIN MENU"), new InputAction() {
        public void actionPerformed(java.util.EventObject _ae){
          _wm.setCurrent(_wm.getScreen());
          _wm.getScreen().clear();
          menu.stop();
        }
      });
      menu.start();
    }
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
