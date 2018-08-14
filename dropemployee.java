import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

public class dropemployee extends JInternalFrame implements ActionListener ,ItemListener{
  SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");
  SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd");

  Container c =getContentPane();
  JLabel l0,l1,l2,l3,l4,l5,l6;
  JTextField t1,t2,t3,t4,t5,t6;
  JComboBox cb;
  JButton b1 ,b2;
  Connection con;
  Statement st;

  dropemployee()
  {
      super("Drop a User",true,true,true,true);
      setLayout(null);
       setSize(1366,712);
      c.setBackground(Color.white);

      l0 =new JLabel("User List");
      l1 =new JLabel("Full Name");
      l2 =new JLabel("Contact No");
      l3 =new JLabel("Email ID");
      l4 =new JLabel("Gender");
      l5 =new JLabel("Date");

      add(l0);
      add(l1);
      add(l2);
      add(l3);
      add(l4);
      add(l5);

      l0.setFont(new Font("Arial" ,Font.BOLD ,18));
      l1.setFont(new Font("Arial" ,Font.BOLD ,18));
      l2.setFont(new Font("Arial" ,Font.BOLD ,18));
      l3.setFont(new Font("Arial" ,Font.BOLD ,18));
      l4.setFont(new Font("Arial" ,Font.BOLD ,18));
      l5.setFont(new Font("Arial" ,Font.BOLD ,18));

      // buttons
      b1 =new JButton("Drop");
      b2 =new JButton("Cancel");

      add(b1);
      add(b2);

      b1.setFont(new Font("Arial" ,Font.BOLD ,17));
      b1.setBackground(Color.white);
      b2.setFont(new Font("Arial" ,Font.BOLD ,17));
      b2.setBackground(Color.white);


      /**Combobox*/
      cb =new JComboBox();
      add(cb);
      cb.addItem("Select Users ");

      cb.setFont(new Font("Arial" ,Font.BOLD ,15));
      cb.setBackground(Color.white);
      // TextField
      t1 =new JTextField(30);
      t2 =new JTextField(30);
      t3 =new JTextField(30);
      t4 =new JTextField(30);
      t5 =new JTextField(30);
      t1.setFont(new Font("Arial" ,Font.BOLD ,15));
      t2.setFont(new Font("Arial" ,Font.BOLD ,15));
      t3.setFont(new Font("Arial" ,Font.BOLD ,15));
      t4.setFont(new Font("Arial" ,Font.BOLD ,15));
      t5.setFont(new Font("Arial" ,Font.BOLD ,15));

      add(t1);
      add(t2);
      add(t3);
      add(t4);
      add(t5);

      /**set bounds*/

      l0.setBounds(440,100,250,30);
      l1.setBounds(440,150,250,30);
      l2.setBounds(440,200,250,30);
      l3.setBounds(440,250,250,30);
      l4.setBounds(440,300,250,30);
      l5.setBounds(440,350,250,30);

      cb.setBounds(680,100,200,30);
      t1.setBounds(680,150,200,30);
      t2.setBounds(680,200,200,30);
      t3.setBounds(680,250,200,30);
      t4.setBounds(680,300,200,30);
      t5.setBounds(680,350,200,30);

      b1.setBounds(550,450,100,30);
      b2.setBounds(680,450,100,30);

      try{
        Class.forName("com.mysql.jdbc.Driver");
        con =DriverManager.getConnection("jdbc:mysql://localhost/FlightReservation","root","");
        st = con.createStatement();
        String get ="SELECT * FROM employee";
        ResultSet rs = st.executeQuery(get);
        while(rs.next()){
          cb.addItem(rs.getString(6));
        }

      }catch(Exception e){
        JOptionPane.showMessageDialog(this,"Server Not Connected");
      }
      t1.setEditable(false);
      t2.setEditable(false);
      t3.setEditable(false);
      t4.setEditable(false);
      t5.setEditable(false);
      b1.addActionListener(this);
      b2.addActionListener(this);
      b1.setEnabled(false);
      cb.addItemListener(this);

  }

  public  void clear()
  {
    t1.setText("");
    t2.setText("");
    t3.setText("");
    t4.setText("");
    t5.setText("");
  }

  public void actionPerformed (ActionEvent ae)
  {
    try
    {
        if(ae.getSource()==b1)
        {
          int n =  JOptionPane.showConfirmDialog(this ,"Do you really want to kick out this employee??");
           if(n==0){
             try{
               String drop ="DELETE FROM employee WHERE user_id ='"+cb.getSelectedItem()+"'";
               st.executeUpdate(drop);
               JOptionPane.showMessageDialog(this ,"Live long employee :"+cb.getSelectedItem() );
               clear();
               cb.setSelectedIndex(0);
             } catch(Exception e) {
               JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");
             }
           }


        }

        if(ae.getSource()==b2)
        {
          clear();
          cb.setSelectedIndex(0);
        }
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");
    }
  }


  public void itemStateChanged(ItemEvent ie){
    if(ie.getSource()==cb && ie.getStateChange()==ItemEvent.SELECTED){
        if(cb.getSelectedIndex() ==0)
        {
          clear();
          cb.setSelectedItem(0);
        }
        else{
        if(ie.getSource()==cb){
          try{
            String detail ="SELECT * FROM employee WHERE user_id='"+cb.getSelectedItem()+"'";
            String d="";
            ResultSet rs =st.executeQuery(detail);
            if(rs.next()){
              t1.setText(rs.getString(1));
              t2.setText(rs.getString(3));
              t3.setText(rs.getString(4));
              t4.setText(rs.getString(5));
              d =rs.getString(8);
            }
            java.util.Date d2 =sdf1.parse(d);
            String doj =sdf.format(d2);
            t5.setText(doj);
            b1.setEnabled(true);
          } catch(Exception exc){
             JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");

          }
        }
      }
}
}

}
