package com.ais.demo;
// Import declaration block
import com.ais.demo.record.R;
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

public  interface ID4_Report {

  void reports();
  

  void print_labels();
  

  void finishReportLabels_Report();
  

  void outputReportLabels_Report(Object... row);
  

  void print_ar();
  

  void finishReportAr_Report();
  

  void outputReportAr_Report(Object... row);
  

  void update_options();
  

  void print_backlog();
  

  void print_stock();

}
