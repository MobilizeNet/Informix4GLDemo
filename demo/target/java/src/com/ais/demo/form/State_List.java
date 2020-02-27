package com.ais.demo.form;
// Import declaration block
import com.ais.fc.ix.lang.*;
import com.ais.fc.ix.lang.blob.*;
import com.ais.fc.ix.swing.ui.*;
import com.ais.fc.ix.addon.swing.ui.*;
import javax.swing.*;
import java.awt.*;

public  class State_List extends Form{
  //*** Form Component Declaration ***
  JLabel label_0 = new JLabel();
  FieldGroup state__code = new FieldGroup();
  TextBox state__code__fld = new TextBox();
  FieldGroup state__sname = new FieldGroup();
  TextBox state__sname__fld = new TextBox();
  //*** Table Screen Record Declaration ***
  ScreenRecord state = new ScreenRecord();
  //*** Screen Record Declaration ***
  ScreenRecord s_state = new ScreenRecord();
  public State_List(){
    try {
      jbInit();
    }
    catch (Exception e){
    }
  }
  

  private void jbInit() throws Exception{
    //*** Screen Specification ***
    this.setName("state_list");
    GridBagLayout thislayout = new GridBagLayout();
    this.setLayout(thislayout);
    this.setSize(GridBagLayoutHelper.computeSize(thislayout, this, 22, 10));
    //*** Component Initialization ***
    label_0.setName("label_0");
    label_0.setText("State Selection");
    this.add(label_0, new GridBagConstraints(3, 0, 15, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    state__code.setName("code");
    state__code__fld.setName("code");
    state__code__fld.setType(new Char(2));
    state__code__fld.setNotNull(true);
    state__code__fld.setDefaultValue(null);
    state__code__fld.setEnabled(false);
    state__code__fld.setLength(2);
    state__code.add(state__code__fld);
    state__code.setFieldCount(7);
    this.add(state__code, new GridBagConstraints(1, 2, 2, 7, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    state__sname.setName("sname");
    state__sname__fld.setName("sname");
    state__sname__fld.setType(new Char(15));
    state__sname__fld.setDefaultValue(null);
    state__sname__fld.setEnabled(false);
    state__sname__fld.setLength(15);
    state__sname.add(state__sname__fld);
    state__sname.setFieldCount(7);
    this.add(state__sname, new GridBagConstraints(6, 2, 15, 7, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    //*** Table Screen Record Initialization ***
    state.setName("state");
    state.setFields(state__code, state__sname);
    this.addElement(state);
    //*** Screen Record Initialization ***
    s_state.setName("s_state");
    s_state.setFields(state__code, state__sname);
    this.addElement(s_state);
  }

}
