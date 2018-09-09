import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;


public class createemployee extends JInternalFrame implements ActionListener , FocusListener{
  Container c =getContentPane();
  Connection con;
  String temp ;
  Statement st;
  JLabel l0,l1,l2,l3,l4,l5,l6,l7,l8;
  JTextField t1,t2,t3,t4,t5,t6,t7;
  JRadioButton  rb1 ,rb2;
  JPanel pn ,pn1;
  JButton b1 ,b2;
  ButtonGroup bg;
  SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");
  SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd");
  Random x = new Random();
  createemployee()
  {
      super("Create New Employee",true,true,true,true);
        setSize(1366,712);
      setLayout(null);
      c.setBackground(Color.white);
      // setExtendedState(JInternalFrame.MAXIMIZED_BOTH);

    /**Labels here */
      l1 =new JLabel("Full Name");
      l2 =new JLabel("Address");
      l3 =new JLabel("Contact No");
      l4 =new JLabel("Email ID");
      l5 =new JLabel("Gender");
      l6 =new JLabel("User ID");
      l7 =new JLabel("Password");
      l8 =new JLabel("Date");

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
    //   b3 =new JButton("Modify");
    //   b4 =new JButton("Delete");
    //   b5 =new JButton("Search");
      add(b1);
      add(b2);
      // add(b3);
      // add(b4);
      // add(b5);
      b1.setFont(new Font("Arial" ,Font.BOLD ,17));
      b1.setBackground(Color.white);
      b2.setFont(new Font("Arial" ,Font.BOLD ,17));
      b2.setBackground(Color.white);

    // radio buttons
      rb1 =new JRadioButton("Male" ,true);
      rb2 =new JRadioButton("Female");
      add(rb1);
      add(rb2);
      bg =new ButtonGroup();
      bg.add(rb1);
      bg.add(rb2);


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


      t1.setBounds(680,100,200,30);
      t2.setBounds(680,150,200,30);
      t3.setBounds(680,200,200,30);
      t4.setBounds(680,250,200,30);
      rb1.setBounds(680,300,90,30);
      rb2.setBounds(770,300,100,30);
      t5.setBounds(680,350,200,30);
      t6.setBounds(680,400,200,30);
      t7.setBounds(680,450,200,30);


      //connection WHERE
      try{
        Class.forName("com.mysql.jdbc.Driver");
        con =DriverManager.getConnection("jdbc:mysql://localhost/FlightReservation","root","");
        st = con.createStatement();
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
      b2.setEnabled(false);
      t4.addFocusListener(this);
      //setVisible(true);
  }
/**focus listeners*/
  public void focusGained(FocusEvent f1)
  {

  }
  public void focusLost(FocusEvent f2)
  {
    String email = t4.getText();
    int index =email.indexOf('@');
    String uid =email.substring(0,index);
    t5.setText(uid);
    t6.setText(temp);
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
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        rb1.setSelected(true);
        t1.requestFocus(true);
        b2.setEnabled(true);
        Calendar d =Calendar.getInstance();
        String month =String.valueOf(d.get(Calendar.MONTH)+1);
        String date =String.valueOf(d.get(Calendar.DATE));
        String currentdate = format(date)+"-"+format(month)+"-"+String.valueOf(d.get(Calendar.YEAR));
        t7.setText(currentdate);
        // password generation
        String  p ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
         temp ="";
        for(int i=0;i<5;i++)
        {
          int j = x.nextInt(61);
          temp+=p.charAt(j);
        }
    }

    if(e.getSource()==b2)
    {
      // System.out.println("inside create");
      if (t1.getText().trim().length() == 0 || t2.getText().trim().length() == 0  || t3.getText().trim().length() == 0  || t4.getText().trim().length() == 0 || t5.getText().trim().length() == 0 || t6.getText().trim().length() == 0)
       {
         JOptionPane.showMessageDialog(this ,"Fields cannot be empty");
       }
     else{
        if(!t1.getText().matches("^[a-zA-Z]+$"))
        {
          JOptionPane.showMessageDialog(this ,"Name Must include only a-z or A-Z");
        }
        else{
          if(!validation.checkContact(t3.getText())){
            JOptionPane.showMessageDialog(this ,"Phone Number Must be 10 digits ");
          }else{
          if(!t4.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
              JOptionPane.showMessageDialog(this ,"Emter a Valid Email");
          }else{
          java.util.Date jd =sdf.parse(t7.getText());
          String jod =sdf1.format(jd);

          //gender
          String gender ="";
          if(rb1.isSelected()){ gender = "Male" ;}
          else { gender ="Female";}

          String insert  ="INSERT INTO employee VALUES ('"+t1.getText()+"','"+t2.getText()+"','"+t3.getText()+"','"+t4.getText()+"','"+gender+"','"+t5.getText()+"','"+t6.getText()+"','"+jod+"')";
          st.executeUpdate(insert);
          JOptionPane.showMessageDialog(this ,"New User is added to System");
          b2.setEnabled(false);
        }
        }
      }
       }
    }
  }
   catch(Exception ex) {
     JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");
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


// public static boolean isValidEmailAddress(String email) {
//    boolean result = true;
//    try {
//       InternetAddress emailAddr = new InternetAddress(email);
//       emailAddr.validate();
//    } catch (AddressException ex) {
//       result = false;
//    }
//    return result;
// }

}
