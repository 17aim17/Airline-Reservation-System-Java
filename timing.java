import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.text.*;


public class timing extends JInternalFrame implements ActionListener , FocusListener,ItemListener{
  Container c =getContentPane();
  Connection con;
  String temp ;
  Statement st;
  int flag =0;
  JLabel l0,l1,l2,l3,l4,l5,l6,l7,l8;
  JTextField t1,t2,t3,t4,t5,t6,t7;
  JButton b1,b2;
  java.util.Date d1,d2;
  SimpleDateFormat sdf =new SimpleDateFormat("H:m");
  JComboBox cb1;
  timing()
  {
    super("Timing Master",true,true,true,true);
     setSize(1366,712);
      setLayout(null);
      c.setBackground(Color.white);

    /**Labels here */
      l1 =new JLabel("Aircraft Code");
      l2 =new JLabel("Route No");
      l3 =new JLabel("Source");
      l4 =new JLabel("Destination");
      l5 =new JLabel("Distance");
      l6 =new JLabel("Dept time");
      l7 =new JLabel("Journy Hours");
      l8 =new JLabel("Arrival time");

      add(l1);
      add(l2);
      add(l3);
      add(l4);
      add(l5);
      add(l6);
      add(l7);
      add(l8);

      l1.setFont(new Font("Arial" ,Font.BOLD ,18));
      l2.setFont(new Font("Arial" ,Font.BOLD ,18));
      l3.setFont(new Font("Arial" ,Font.BOLD ,18));
      l4.setFont(new Font("Arial" ,Font.BOLD ,18));
      l5.setFont(new Font("Arial" ,Font.BOLD ,18));
      l6.setFont(new Font("Arial" ,Font.BOLD ,18));
      l7.setFont(new Font("Arial" ,Font.BOLD ,18));
      l8.setFont(new Font("Arial" ,Font.BOLD ,18));



    // Buttons here
      b1 =new JButton("New");
      b2 =new JButton("Create");

      add(b1);
      add(b2);

      b1.setFont(new Font("Arial" ,Font.BOLD ,17));
      b1.setBackground(Color.white);
      b2.setFont(new Font("Arial" ,Font.BOLD ,17));
      b2.setBackground(Color.white);

 /**combo box*/
      cb1 =new JComboBox();
      add(cb1);
      cb1.addItem(" Availabe Crafts ");

      cb1.setFont(new Font("Arial" ,Font.BOLD ,15));
      cb1.setBackground(Color.white);
  /**TextField*/
    t1 =new JTextField(30);
    t2 =new JTextField(30);
    t3 =new JTextField(30);
    t4 =new JTextField(30);
    t5 =new JTextField(30);
    t6 =new JTextField(30);
    t7 =new JTextField(30);

    t1.setFont(new Font("Arial" ,Font.BOLD ,15));
    t2.setFont(new Font("Arial" ,Font.BOLD ,15));
    t3.setFont(new Font("Arial" ,Font.BOLD ,15));
    t4.setFont(new Font("Arial" ,Font.BOLD ,15));
    t5.setFont(new Font("Arial" ,Font.BOLD ,15));
    t6.setFont(new Font("Arial" ,Font.BOLD ,15));
    t7.setFont(new Font("Arial" ,Font.BOLD ,15));


    add(t1);
    add(t2);
    add(t3);
    add(t4);
    add(t5);
    add(t6);
    add(t7);


      b1.setBounds(550,550,100,30);
      b2.setBounds(680,550,100,30);

      l1.setBounds(440,100,250,30);
      l2.setBounds(440,150,250,30);
      l3.setBounds(440,200,250,30);
      l4.setBounds(440,250,250,30);
      l5.setBounds(440,300,250,30);
      l6.setBounds(440,350,250,30);
      l7.setBounds(440,400,250,30);
      l8.setBounds(440,450,250,30);


      cb1.setBounds(680,100,150,30);
      t1.setBounds(680,150,150,30);
      t2.setBounds(680,200,150,30);
      t3.setBounds(680,250,150,30);
      t4.setBounds(680,300,150,30);
      t5.setBounds(680,350,150,30);
      t6.setBounds(680,400,150,30);
      t7.setBounds(680,450,150,30);


      //connection WHERE
      try{
        Class.forName("com.mysql.jdbc.Driver");
        con =DriverManager.getConnection("jdbc:mysql://localhost/FlightReservation","root","");
        st = con.createStatement();
        String get ="SELECT a_code FROM route";
        ResultSet rs = st.executeQuery(get);
        while(rs.next()){
          cb1.addItem(rs.getString(1));
        }


      }catch(Exception e){
           JOptionPane.showMessageDialog(this,"Server Not Connected");
      }

      t1.setEditable(false);
      t2.setEditable(false);
      t3.setEditable(false);
      t4.setEditable(false);
      t5.setEditable(false);
      t6.setEditable(false);
      t7.setEditable(false);

      b1.addActionListener(this);
      b2.addActionListener(this);
      t6.addFocusListener(this);
      cb1.addItemListener(this);
  }
/**focus listeners*/
  public void focusGained(FocusEvent f1)
  {

  }
  public void focusLost(FocusEvent f2)
  {
      try{
         d1 =sdf.parse(t5.getText());
         d2 =sdf.parse(t6.getText());
         int ah =d1.getHours() +d2.getHours();
         int am =d1.getMinutes() +d2.getMinutes();
      
        if(ah==23){
          if(am>=60){
            am =am-60;
            ah =0;
          }
        }

        while(ah>=24)
        {
          ah =ah-24;
          if(am>=60) {
            am =am-60;
            ah =ah+1;
          }
        }
        if(ah<23 && am>=60)
        {
          ah =ah+1;
          am =am-60;
        }


        t7.setText(format(String.valueOf(ah))+":"+format(String.valueOf(am)));
      }
      catch(Exception e) {
        JOptionPane.showMessageDialog(this,"Enter Correct Format hh:mm");
      }
  }

public void clear()
{
  t1.setText("");
  t2.setText("");
  t3.setText("");
  t4.setText("");
  t5.setText("");
  t6.setText("");
  t7.setText("");
}

/**action listeners*/
public void actionPerformed(ActionEvent e)
{
  try{
    if(e.getSource()==b1)
    {

      clear();
      t5.setEditable(true);
      t6.setEditable(true);
      cb1.setSelectedIndex(0);
      b2.setEnabled(true);
      b2.setText("Create");
    }

    if(e.getSource()==b2)
    {
      // System.out.println("inside create");
      if (cb1.getSelectedIndex()==0 || t1.getText().trim().length() == 0 || t2.getText().trim().length() == 0  || t3.getText().trim().length() == 0  || t4.getText().trim().length() == 0 || t5.getText().trim().length() == 0 || t6.getText().trim().length() == 0 || t7.getText().trim().length() == 0)
       {
         JOptionPane.showMessageDialog(this ,"Fields cannot be empty");
       }
     else{
          if(flag ==0){
             String insert  ="INSERT INTO timing VALUES ('"+cb1.getSelectedItem()+"','"+t1.getText()+"','"+t5.getText()+"','"+t6.getText()+"','" +t7.getText()+"')";
             st.executeUpdate(insert);
             JOptionPane.showMessageDialog(this ,"Creatated Successfully");
             b2.setText("Modify");
           }
           else if( flag==1)
           {
             String update  ="UPDATE  timing SET  d_time = '"+t5.getText()+"',hours = '"+t6.getText()+"',a_time ='"+t7.getText()+"' WHERE a_code ='"+cb1.getSelectedItem()+"'AND r_code ='"+t1.getText()+"'" ;
             st.executeUpdate(update);
             JOptionPane.showMessageDialog(this ,"Updated Successfully");
             b2.setText("Modify");
           }
      }
    }
  }
   catch(Exception ex) {
      JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");
   }
}

/**item listener*/
public void itemStateChanged(ItemEvent ie){
  if(ie.getSource()==cb1 && ie.getStateChange()==ItemEvent.SELECTED){
      if(cb1.getSelectedIndex() ==0)
      {
        clear();
        cb1.setSelectedItem(0);
      }
      else{
      if(ie.getSource()==cb1){
        try{
          String detail ="SELECT * FROM route WHERE a_code='"+cb1.getSelectedItem()+"'";
          ResultSet rs =st.executeQuery(detail);
          if(rs.next()){
            t1.setText(rs.getString(1));
            t2.setText(rs.getString(3));
            t3.setText(rs.getString(4));
            t4.setText(rs.getString(5));
          }
          String detail2 ="SELECT * FROM timing WHERE a_code ='"+cb1.getSelectedItem()+"'";
          ResultSet rs2 =st.executeQuery(detail2);
          if(!rs2.next()) {
            t5.setText("");
            t6.setText("");
            t7.setText("");
            flag=0;
            b2.setText("Create");
          }
          else{
            t5.setText(rs2.getString(3));
            t6.setText(rs2.getString(4));
            t7.setText(rs2.getString(5));
            flag =1;
            b2.setText("Modify");
            t5.setEditable(true);
            t6.setEditable(true);
          }

        } catch(Exception exc){
            JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");
          }
      }
    }
}
}

public String format(String x)
{
  if(x.length()>1)
  {
    return x;
  }
  else{
    return "0"+x;
  }
}

}
