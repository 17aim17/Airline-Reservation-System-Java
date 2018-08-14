import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;


public class flightmaster extends JInternalFrame implements ActionListener ,ItemListener {
  Container c =getContentPane();
  Connection con;
  Statement st;
  JLabel l0,l1,l2,l3,l4,l5,l6,l7;
  JTextField t1,t2,t3,t4,t5,t6;
  JPanel pn ,pn1;
  JComboBox cb;

  JButton b1 ,b2;
  flightmaster()
  {
    super("Flight Master",true,true,true,true);
     setSize(1366,712);
      setLayout(null);
      c.setBackground(Color.white);


      cb =new JComboBox();
      add(cb);
      cb.addItem("get Info ");

      cb.setFont(new Font("Arial" ,Font.BOLD ,15));
      cb.setBackground(Color.white);

    /**Labels here */
      l1 =new JLabel("Aircraft Code");
      l2 =new JLabel("Aircraft Name");
      l3 =new JLabel("Class E Seats");
      l4 =new JLabel("Class B Seats");
      l5 =new JLabel("Economy Weigth Cap");
      l6 =new JLabel("Buisness Weigth Cap");
      l7 =new JLabel("Search Craft");

      add(l1);
      add(l2);
      add(l3);
      add(l4);
      add(l5);
      add(l6);
      add(l7);

      l1.setFont(new Font("Arial" ,Font.BOLD ,18));
      l2.setFont(new Font("Arial" ,Font.BOLD ,18));
      l3.setFont(new Font("Arial" ,Font.BOLD ,18));
      l4.setFont(new Font("Arial" ,Font.BOLD ,18));
      l5.setFont(new Font("Arial" ,Font.BOLD ,18));
      l6.setFont(new Font("Arial" ,Font.BOLD ,18));
      l7.setFont(new Font("Arial" ,Font.BOLD ,18));




    // Buttons here
      b1 =new JButton("New");
      b2 =new JButton("Create");
      add(b1);
      add(b2);

      b1.setFont(new Font("Arial" ,Font.BOLD ,17));
      b1.setBackground(Color.white);
      b2.setFont(new Font("Arial" ,Font.BOLD ,17));
      b2.setBackground(Color.white);



    /**TextField*/
    t1 =new JTextField(30);
    t2 =new JTextField(30);
    t3 =new JTextField(30);
    t4 =new JTextField(30);
    t5 =new JTextField(30);
    t6 =new JTextField(30);


    t1.setFont(new Font("Arial" ,Font.BOLD ,15));
    t2.setFont(new Font("Arial" ,Font.BOLD ,15));
    t3.setFont(new Font("Arial" ,Font.BOLD ,15));
    t4.setFont(new Font("Arial" ,Font.BOLD ,15));
    t5.setFont(new Font("Arial" ,Font.BOLD ,15));
    t6.setFont(new Font("Arial" ,Font.BOLD ,15));


    add(t1);
    add(t2);
    add(t3);
    add(t4);
    add(t5);
    add(t6);


      b1.setBounds(450,450,100,30);
      b2.setBounds(680,450,100,30);
      l1.setBounds(440,100,250,30);
      l2.setBounds(440,150,250,30);
      l3.setBounds(440,200,250,30);
      l4.setBounds(440,250,250,30);
      l5.setBounds(440,300,250,30);
      l6.setBounds(440,350,250,30);
      l7.setBounds(440,550,250,30);


      t1.setBounds(680,100,150,30);
      t2.setBounds(680,150,150,30);
      t3.setBounds(680,200,150,30);
      t4.setBounds(680,250,150,30);
      t5.setBounds(680,300,150,30);
      t6.setBounds(680,350,150,30);
      cb.setBounds(680,550,150,30);

      //connection WHERE
      try{
        Class.forName("com.mysql.jdbc.Driver");
        con =DriverManager.getConnection("jdbc:mysql://localhost/FlightReservation","root","");
        st = con.createStatement();
        String get ="SELECT * FROM fleetinfo";
        ResultSet rs = st.executeQuery(get);
        while(rs.next()){
          cb.addItem(rs.getString(1));
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

      b1.addActionListener(this);
      b2.addActionListener(this);
      b2.setEnabled(false);
      cb.addItemListener(this);

  }

public void clear()
{
  t1.setText("");
  t2.setText("");
  t3.setText("");
  t4.setText("");
  t5.setText("");
  t6.setText("");
}
/**action listeners*/
public void actionPerformed(ActionEvent e)
{
  try{
    if(e.getSource()==b1)
    {
      t1.setEditable(true);
      t2.setEditable(true);
      t3.setEditable(true);
      t4.setEditable(true);
      t5.setEditable(true);
      t6.setEditable(true);
        clear();
        t1.requestFocus(true);
        b2.setEnabled(true);

    }

    if(e.getSource()==b2)
    {
      // System.out.println("inside create");
      if (t1.getText().trim().length() == 0 || t2.getText().trim().length() == 0  || t3.getText().trim().length() == 0  || t4.getText().trim().length() == 0 || t5.getText().trim().length() == 0 || t6.getText().trim().length() == 0)
       {
         JOptionPane.showMessageDialog(this ,"Fields cannot be empty");
       }
     else{
       
         String check ="SELECT * FROM fleetinfo WHERE a_code ='"+t1.getText()+"'";
         ResultSet rb =st.executeQuery(check);
         if(!rb.next())
         {
         String insert  ="INSERT INTO fleetinfo VALUES ('"+t1.getText()+"','"+t2.getText()+"','"+t3.getText()+"','"+t4.getText()+"','"+t5.getText()+"','"+t6.getText()+"','Available')";
         st.executeUpdate(insert);
         cb.addItem(t1.getText());
         JOptionPane.showMessageDialog(this ,"Creatated Successfully");
         b2.setEnabled(false);
       } else {
         JOptionPane.showMessageDialog(this ,"Flight Code already exists");
       }
       }
    }
  }
   catch(Exception ex) {
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
          String detail ="SELECT * FROM fleetinfo WHERE a_code='"+cb.getSelectedItem()+"'";
          String d="";
          t1.setEditable(false);
          t2.setEditable(false);
          t3.setEditable(false);
          t4.setEditable(false);
          t5.setEditable(false);
          t6.setEditable(false);
          ResultSet rs =st.executeQuery(detail);
          if(rs.next()){
            t1.setText(rs.getString(1));
            t2.setText(rs.getString(2));
            t3.setText(rs.getString(3));
            t4.setText(rs.getString(4));
            t5.setText(rs.getString(5));
            t6.setText(rs.getString(6));
          }
          b1.setEnabled(true);
          b2.setEnabled(false);
        } catch(Exception exc){
          JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");
          }
      }
    }
}
}

}
