import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class login extends JFrame implements ActionListener{
    Container c1 = getContentPane();
    JButton bt;
    Connection con;
    Statement st;
    JTextField tf1 ;
    JPanel pn ,pn1;
    JPasswordField tf2;
    JLabel lb1 ,lb2 ,lb3,lx;
    String user="";
  login(String uname){
    user=uname;
    setSize(1366,768);
    setTitle(user+" Login");
    setVisible(true);
    pn =new JPanel();
    lb3 =new JLabel( user.toUpperCase() +" LOGIN  AREA HERE");
    lb3.setFont(new Font("Arial" ,Font.BOLD ,20));
    lb3.setForeground(Color.white);
    pn.setBackground(Color.black);
    pn.add(lb3);
    add(pn ,"North");

    pn1 =new JPanel();
    pn1.setLayout(null);
    bt = new JButton("Log In");
    tf1 =new JTextField(40);
    tf2 =new JPasswordField(40);
    lb1 =new JLabel("USERNAME");
    lb2 =new JLabel("PASSWORD");
    pn1.add(bt);
    pn1.add(tf1);
    pn1.add(tf2);
    pn1.add(lb1);
    pn1.add(lb2);
    ImageIcon mg =new ImageIcon("plane.jpeg");
    lx =new JLabel(mg);
      pn1.add(lx);
    // add(lx);
    lx.setBounds(0,0,1920,1280);
    pn1.setBackground(Color.white);
    add(pn1,"Center");


    c1.setBackground(Color.white);
    c1.setForeground(Color.black);
    bt.setFont(new Font("Arial" ,Font.BOLD ,23));
    bt.setBackground(Color.red);
    bt.setForeground(Color.white);
     lb1.setForeground(Color.white);
     lb2.setForeground(Color.white);
    lb1.setFont(new Font("Arial" ,Font.BOLD ,23));
    lb2.setFont(new Font("Arial" ,Font.BOLD ,23));
    tf1.setFont(new Font("Arial" ,Font.BOLD ,20));
    tf2.setFont(new Font("Arial" ,Font.BOLD ,20));
// bounds       x   y w h
    lb1.setBounds(420 ,150 ,200 , 40);
    tf1.setBounds(680 ,150 ,200 , 40);
    lb2.setBounds(420 ,250 ,200 , 40);
    tf2.setBounds(680 ,250 ,200 , 40);
    bt.setBounds(580,350 ,140 ,40);
    setExtendedState(JFrame.MAXIMIZED_BOTH);

    bt.addActionListener(this);

    //connection WHERE
    try{
      Class.forName("com.mysql.jdbc.Driver");
      con =DriverManager.getConnection("jdbc:mysql://localhost/FlightReservation","root","");
      st = con.createStatement();
    }catch(Exception e){
       JOptionPane.showMessageDialog(this,"Server Not Connected");
    }
  }

public void actionPerformed(ActionEvent e){
  try{
    if(e.getSource()==bt){
          if(user.equals("User")){
           if(tf1.getText().trim().length()==0 || tf2.getText().trim().length()==0){
             JOptionPane.showMessageDialog(this , "Enter Username  or Password please");
           }else{
           String check ="SELECT * FROM employee WHERE user_id = '"+tf1.getText()+"' AND password ='"+tf2.getText()+"'";
           ResultSet rs =st.executeQuery(check);
           if(rs.next()){
            userpanel obj= new userpanel(tf1.getText());
            this.dispose();

           }else{
             JOptionPane.showMessageDialog(this,"Invalid Credentials");
           }
         }

        } else if(user.equals("Admin")){
          if(tf1.getText().trim().length()==0 || tf2.getText().trim().length()==0){
            JOptionPane.showMessageDialog(this , "Enter Username  or Password please");
          }else{
           String check1 ="SELECT * FROM admin WHERE username = '"+tf1.getText()+"' AND password ='"+tf2.getText()+"'";
           ResultSet rs1 =st.executeQuery(check1);
           if(rs1.next()){
             adminpanel obj = new adminpanel();
             this.dispose();
           }else{
             JOptionPane.showMessageDialog(this,"Invalid Credentials");
           }
         }
        }

    }
  } catch(Exception ex) {
     JOptionPane.showMessageDialog(this,"Can not Log You in at moment");
  }
}

}
