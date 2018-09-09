import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;

public class routemaster extends JInternalFrame implements ActionListener,ItemListener{
    Container c =getContentPane();
    Connection con;
    Statement st;
    JLabel l1,l2,l3,l4,l5,l6,l7;
    JTextField t1,t2,t3,t4,t5;
    JButton b1,b2;
    JComboBox cb1,cb2,cb3;
    routemaster(){
      super("route Master",true,true,true,true);
       setSize(1366,712);
      setLayout(null);
      c.setBackground(Color.white);

      l1 = new JLabel("Route code");
      l2 = new JLabel("Source");
      l3 = new JLabel("Destination");
      l4 = new JLabel("Aircraft code");
      l5 = new JLabel("Distance");
      l6 = new JLabel("Base price");
      l7 = new JLabel("Search");

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

      l1.setBounds(440,100,250,30);
      l2.setBounds(440,150,250,30);
      l3.setBounds(440,200,250,30);
      l4.setBounds(440,250,250,30);
      l5.setBounds(440,300,250,30);
      l6.setBounds(440,350,250,30);
      l7.setBounds(440,500,250,30);

      t1 =new JTextField(30);
      t2 =new JTextField(30);
      t3 =new JTextField(30);
      t4 =new JTextField(30);
      t5 = new JTextField(30);

      add(t1);
      add(t2);
      add(t3);
      add(t4);
      add(t5);

      t1.setFont(new Font("Arial" ,Font.BOLD ,15));
      t2.setFont(new Font("Arial" ,Font.BOLD ,15));
      t3.setFont(new Font("Arial" ,Font.BOLD ,15));
      t4.setFont(new Font("Arial" ,Font.BOLD ,15));
      t5.setFont(new Font("Arial" ,Font.BOLD ,15));




      t1.setBounds(680,150,150,30);
      t2.setBounds(680,200,150,30);
      t3.setBounds(680,350,150,30);
      t4.setBounds(680,300,150,30);
      t5.setBounds(680,250,150,30);

      t1.setEditable(false);
      t2.setEditable(false);
      t3.setEditable(false);
      t4.setEditable(false);
      t5.setVisible(false);

      b1 =new JButton("New");
      b2 =new JButton("Create");

      add(b1);
      add(b2);

      b1.setFont(new Font("Arial" ,Font.BOLD ,17));
      b1.setBackground(Color.white);

      b2.setFont(new Font("Arial" ,Font.BOLD ,17));
      b2.setBackground(Color.white);

      b1.setBounds(560,420,100,30);
      b2.setBounds(680,420,100,30);

      b1.addActionListener(this);
      b2.addActionListener(this);

      cb1 = new JComboBox();
      cb1.setFont(new Font("Arial" ,Font.BOLD ,15));
      cb1.setBackground(Color.white);
      cb1.addItem("Routes");
      add(cb1);
	    cb1.addItemListener(this);
      cb1.setBounds(680,100,150,30);

      cb2 = new JComboBox();
      cb2.setFont(new Font("Arial" ,Font.BOLD ,15));
      cb2.setBackground(Color.white);
      cb2.addItem("Aircraft codes");
      add(cb2);
      cb2.addItemListener(this);
      cb2.setBounds(680,250,150,30);


      cb3 = new JComboBox();
      cb3.setFont(new Font("Arial" ,Font.BOLD ,15));
      cb3.setBackground(Color.white);
      cb3.addItem("Search");
      add(cb3);
      cb3.addItemListener(this);
      cb3.setBounds(680,500,150,30);

	  b2.setEnabled(false);

      try{
        Class.forName("com.mysql.jdbc.Driver");
        con =DriverManager.getConnection("jdbc:mysql://localhost/FlightReservation","root","");
        st = con.createStatement();
        System.out.println("Connected");
        String s = "Select r_code from route_master";
        ResultSet rs = st.executeQuery(s);
          while(rs.next()){
          	cb1.addItem(rs.getString(1));
          }
       String s1 = "select a_code from fleetinfo where status = 'Available'";
       ResultSet rs1 = st.executeQuery(s1);
       while(rs1.next()){
                cb2.addItem(rs1.getString(1));
        }
        String s2 = "select a_code from fleetinfo where status != 'Available'";
        ResultSet rs2 = st.executeQuery(s2);
        while(rs2.next()){
                 cb3.addItem(rs2.getString(1));
         }

      }catch(Exception e){
         JOptionPane.showMessageDialog(this,"Server Not Connected");
      }
      cb1.setEnabled(false);
      cb2.setEnabled(false);

    }
    public void clear(){
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		t5.setText("");
  }

    public void itemStateChanged(ItemEvent e){
    	if(e.getSource()==cb3 && e.getStateChange()==ItemEvent.SELECTED){
    		if(cb3.getSelectedIndex()==0){
				clear();
				cb3.setSelectedIndex(0);
			}else{
				try{
                    cb2.setVisible(false);
					          b2.setEnabled(false);
					          t1.setEditable(false);
                    t2.setEditable(false);
                    t3.setEditable(false);
                    t4.setEditable(false);
                    t5.setVisible(true);
                    t5.setEditable(false);


					String s = "select * from route where a_code='"+cb3.getSelectedItem()+"'";
        		    ResultSet rs = st.executeQuery(s);
        		if(rs.next()){
        			String c1 = rs.getString(1);
              String c = rs.getString(2);
        			t1.setText(rs.getString(3));
        			t2.setText(rs.getString(4));
        			t4.setText(rs.getString(5));
        			t3.setText(rs.getString(6));
              t5.setText(c);
              cb1.setSelectedItem(c1);
        		}

				}
				catch(Exception ex){
					 JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");
				}
			}

    	}
      if(e.getSource()==cb1 && e.getStateChange()==ItemEvent.SELECTED){
        if(cb1.getSelectedIndex()==0){
          t1.setText("");
          t2.setText("");
          cb1.setSelectedIndex(0);
        }else{
          try{
            String sd = "select * from route_master where r_code='"+cb1.getSelectedItem()+"'";
            ResultSet rs = st.executeQuery(sd);
            if(rs.next()){
               t1.setText(rs.getString(2));
               t2.setText(rs.getString(3));
             }

          }
          catch(Exception ex1){
             JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");
          }
        }

      }
    }

    public void actionPerformed(ActionEvent e){
    	try{
    	if(e.getSource()==b1){
    		    t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            b2.setEnabled(true);
            t3.setEditable(true);
            t4.setEditable(true);
            cb2.setEnabled(true);
            cb2.setVisible(true);

            cb1.setEnabled(true);

            t5.setVisible(false);
            cb1.setSelectedIndex(0);
            cb2.setSelectedIndex(0);
            cb3.setSelectedIndex(0);
    	}
    	if(e.getSource()==b2){
    		if(cb1.getSelectedIndex()==0 || t1.getText().trim().length()==0 || t2.getText().trim().length()==0 || t3.getText().trim().length()==0 || t4.getText().trim().length()==0 ||cb2.getSelectedIndex()==0){
                JOptionPane.showMessageDialog(this,"Fields can not be empty");
              }
            else{
                if(!t3.getText().matches("^-?\\d+$") || !t4.getText().matches("^-?\\d+$")){
                  JOptionPane.showMessageDialog(this ,"Use Whole Numbers please");
                }else{
                String create = "insert into route values('"+cb1.getSelectedItem()+"','"+cb2.getSelectedItem()+"','"+t1.getText()+"','"+t2.getText()+"',"+t4.getText()+","+t3.getText()+")";
                st.executeUpdate(create);
                JOptionPane.showMessageDialog(this,"Added successfully");
                cb3.addItem(cb2.getSelectedItem());
                clear();
                b2.setEnabled(false);



                String update = "update fleetinfo set status='Not Available' where a_code='"+cb2.getSelectedItem()+"'";
                cb2.removeItem(cb2.getSelectedItem());
                cb1.setSelectedIndex(0);
                cb3.setSelectedIndex(0);
                st.executeUpdate(update);

    	       }
           }
           }
        }
        catch(Exception ex){
  	      JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");
        }
    }

}
