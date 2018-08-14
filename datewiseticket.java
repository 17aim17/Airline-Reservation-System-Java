import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.text.*;



public class datewiseticket extends JInternalFrame implements ActionListener{
  Container c =getContentPane();
  Connection con;
  Statement st;
  JLabel l1,l2,l3,l4;
  JTextField t1,t2;
  JButton b1,b2;
  int num;
  String cols[] ={"Sr No","Booking Number","Ticket","Name","email","Aadhar Number","gender" ,"Age"};
  Object  obj[][] ;
  JTable tb ;
  JScrollPane sp;
  JComboBox cb1 ,cb2;
  SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");
  SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd");

  datewiseticket()
  {
    super("All Tickets For particular Departure date",true,true,true,true);
   setSize(1366,712);
    setLayout(null);
    c.setBackground(Color.white);
    l1 =new JLabel("Choose AirCraft Code");
    l1.setFont(new Font("Arial" ,Font.BOLD ,18));

    l2 =new JLabel("Source");
    l2.setFont(new Font("Arial" ,Font.BOLD ,18));

    l3 =new JLabel("Destination");
    l3.setFont(new Font("Arial" ,Font.BOLD ,18));

    l4 =new JLabel("Depature Dates");
    l4.setFont(new Font("Arial" ,Font.BOLD ,18));

    t1 =new JTextField(30);
    t1.setFont(new Font("Arial" ,Font.BOLD ,15));

    t2 =new JTextField(30);
    t2.setFont(new Font("Arial" ,Font.BOLD ,15));

    b1 =new JButton("Show");
    b1.setFont(new Font("Arial" ,Font.BOLD ,18));
    b1.setBackground(Color.white);

    b2 =new JButton("Get");
    b2.setFont(new Font("Arial" ,Font.BOLD ,18));
    b2.setBackground(Color.white);

    cb1 = new JComboBox();
    cb1.setFont(new Font("Arial" ,Font.BOLD ,15));
    cb1.setBackground(Color.white);
    cb1.addItem("Select");

    cb2 = new JComboBox();
    cb2.setFont(new Font("Arial" ,Font.BOLD ,15));
    cb2.setBackground(Color.white);
    cb2.addItem("Select");

    add(l1);
    add(l2);
    add(l3);
    add(l4);
    add(t1);
    add(t2);
    add(cb1);
    add(cb2);
    add(b1);
    add(b2);


    /*set bounds*/
    l1.setBounds(450,50,200,30);
    cb1.setBounds(670,50,100,30);
    b1.setBounds(570,100,100,30);


    l2.setBounds(350,150,100,30);
    t1.setBounds(480,150,100,30);
    l3.setBounds(620,150,100,30);
    t2.setBounds(750,150,100,30);

    l4.setBounds(480,200,150,30);
    cb2.setBounds(670,200,150,30);
    b2.setBounds(570,250,100,30);

    t1.setEditable(false);
    t2.setEditable(false);

    b2.setEnabled(false);

    /*Connection here*/
    try{
      Class.forName("com.mysql.jdbc.Driver");
      con =DriverManager.getConnection("jdbc:mysql://localhost/FlightReservation","root","");
      st = con.createStatement();
      String get ="SELECT DISTINCT a_code FROM booking_master ";
      ResultSet rs = st.executeQuery(get);
        while(rs.next())
        {
            cb1.addItem(rs.getString(1));
        }
      }
      catch(Exception e){
        JOptionPane.showMessageDialog(this,"Server Not Connected");
      }
    b1.addActionListener(this);
    b2.addActionListener(this);

  }



/*Action listener*/
public void actionPerformed(ActionEvent e)
{
try{
  if(e.getSource()==b1)
  {
      if(cb1.getSelectedIndex()==0)
      {
        JOptionPane.showMessageDialog(this,"No Item Selected");

      }
      else{
        String get ="SELECT source , destination FROM route WHERE a_code ='"+cb1.getSelectedItem()+"';";
        ResultSet rs =st.executeQuery(get);
        if(rs.next()){
          t1.setText(rs.getString(1));
          t2.setText(rs.getString(2));
        }
        String get1 ="SELECT distinct dept_date FROM booking_master WHERE a_code ='"+cb1.getSelectedItem()+"';";
        ResultSet rs1 =st.executeQuery(get1);
        while(rs1.next()){
          java.util.Date d1 = sdf1.parse(rs1.getString(1));
          String d =sdf.format(d1);
          cb2.addItem(d);
        }
        b2.setEnabled(true);
    }
  }
  if(e.getSource()==b2)
  {
    if(cb2.getSelectedIndex()==0)
    {
        JOptionPane.showMessageDialog(this,"No Date Selected");
    }
    else{
      java.util.Date d1 = sdf.parse(String.valueOf(cb2.getSelectedItem()));
      String d =sdf1.format(d1);
      String query = "SELECT t.b_no ,t.t_no,t.name,t.email,t.aadhar_no,t.gender,t.age FROM booking_tran as t JOIN booking_master as b ON t.b_no = b.booking_no WHERE b.dept_date ='"+d+"'";
      createtable(query);
    }
  }
}catch(Exception ex)
{
  JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");
}

}


  public void createtable(String x){
    try{
      String query1 =" SELECT count(*) AS SIZE  FROM ("+x+")AS t;";
      ResultSet rs = st.executeQuery(query1);
      while(rs.next()){
        num = rs.getInt(1);
      }
    }catch(Exception z){
      JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");

    }
    if(sp!=null)
    {
      remove(sp);
    }
     if(num!=0){
       int i=0;
       int sr_no =1;
       obj= new Object[num][8];
       try{
         ResultSet rs1 = st.executeQuery(x);
         while(rs1.next())
         {
           obj[i][0] =String.valueOf(sr_no);
           sr_no++;
           obj[i][1] =rs1.getString(1);
           obj[i][2] =rs1.getString(2);
           obj[i][3] =rs1.getString(3);
           obj[i][4] =rs1.getString(4);
           obj[i][5] =rs1.getString(5);
           obj[i][6] =rs1.getString(6);;
           obj[i][7] =rs1.getString(7);;
           i++;
         }
       }catch(Exception ex){
         JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");

       }


       tb =new JTable(obj ,cols);
       sp =new JScrollPane(tb ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       add(sp);
       tb.setRowHeight(32);
       tb.setBackground(Color.white);
       tb.setFont(new Font("Arial" ,Font.PLAIN ,15));
       tb.getTableHeader().setFont(new Font("Arial", Font.ITALIC, 18));
       tb.getTableHeader().setReorderingAllowed(false);   // dragging  false
       tb.setFont(new Font("Arial", Font.PLAIN, 15));
       int y = 32+num*32;

       sp.setBounds(30,300,1300,y);
       sp.setBackground(Color.white);

       TableModel tm =new mytablemodel();
       tb.setModel(tm);
       tb.getColumnModel().getColumn(0).setPreferredWidth(8);

     }
     else{
        JOptionPane.showMessageDialog(this,"Selected Data not found");

     }
  }

  public class mytablemodel extends DefaultTableModel{
    mytablemodel()
    {
      super(obj ,cols);
      // System.out.println("inside");
    }
    public boolean isCellEditable(int row ,int cols){
      return false;
    }
  }

}
