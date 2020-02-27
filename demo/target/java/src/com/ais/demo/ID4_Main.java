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

public  interface ID4_Main extends Runnable{

  void bang();
  

  void mess(String str_, short mrow);
  

  void ring_menu();
  

  void unring_menu();
  

  void clear_menu();
  

  void get_states();
  

  void get_stocks();

}
