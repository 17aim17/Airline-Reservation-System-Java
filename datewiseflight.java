import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.text.*;



public class datewiseflight extends JInternalFrame implements ActionListener{
  Container c =getContentPane();
  String query= "SELECT  distinct f.a_code , f.a_name , b.dept_date,b.source ,b.destination,f.eco_seats,fi.av_eco_st,f.buis_seats ,fi.av_buis_st FROM booking_master AS b JOIN fleetinfo AS f ON b.a_code =f.a_code JOIN flight_booking_info AS fi on b.a_code = fi.a_code AND fi.departure_date=b.dept_date " ; //order by b.dept_date";
  Connection con;
  Statement st;
  JLabel l1,x;
  JTextField t1;
  JButton b1;
  int num;
  String cols[] ={"Sr No","A_code","Name","Depatarture Date","Source","Destination" ,"Total Eco Seats","Avaialable Eco Seats" ,"Total Buisness Seats"  ,"Available Buis Seats"};
  Object  obj[][] ;
  JTable tb ;
  JScrollPane sp;
  SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");
  SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd");

  datewiseflight()
  {
    super("Check All Flight On Particular Dater",true,true,true,true);
     setSize(1366,712); 

    setLayout(null);
    c.setBackground(Color.white);
    l1 =new JLabel("Enter Date");
    l1.setFont(new Font("Arial" ,Font.PLAIN ,18));

    x =new JLabel("DD-MM-YYYY");
    x.setFont(new Font("Arial",Font.BOLD ,13));
    x.setForeground(Color.red);


    b1 =new JButton("Show");
    b1.setFont(new Font("Arial" ,Font.PLAIN ,18));
    b1.setBackground(Color.white);

    t1 = new JTextField(30);
    t1.setFont(new Font("Arial" ,Font.PLAIN ,15));
    t1.setBackground(Color.white);


    add(l1);
    add(t1);
    add(b1);
    add(x);

    /*set bounds*/
    l1.setBounds(530,50,100,30);
    t1.setBounds(670,50,100,30);
    b1.setBounds(630,100,100,30);
    x.setBounds(790,50,100,30);

    /*Connection here*/
    try{
      Class.forName("com.mysql.jdbc.Driver");
      con =DriverManager.getConnection("jdbc:mysql://localhost/FlightReservation","root","");
      st = con.createStatement();
      }
      catch(Exception e){
        JOptionPane.showMessageDialog(this,"Server Not Connected");
    }
    createtable(query);
    b1.addActionListener(this);
    setVisible(true);
  }



/*Action listener*/
public void actionPerformed(ActionEvent e)
{
  if(e.getSource()==b1)
  {

    try{
      if(t1.getText().trim().length()==0)
      {
        if(sp!=null)
        {
          remove(sp);
          sp=null;
          tb=null;
          repaint();

        }
        createtable(query);
      }
      else{
      String d = t1.getText();
      java.util.Date d1 =sdf.parse(d);
      String date =sdf1.format(d1);
      String newquery = query+ " WHERE b.dept_date='"+date+"'";
      if(sp!=null)
      {
        remove(sp);
        sp=null;
        tb=null;
        repaint();
      }
      createtable(newquery);
    }
    }catch(Exception z){
      JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");
    }
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

     if(num!=0){
       int i=0;
       int sr_no =1;
       obj= new Object[num][10];
       try{
         ResultSet rs1 = st.executeQuery(x);
         while(rs1.next())
         {
           obj[i][0] =String.valueOf(sr_no);
           sr_no++;
           obj[i][1] =rs1.getString(1);
           obj[i][2] =rs1.getString(2);

           String d = rs1.getString(3);
           java.util.Date d1 =sdf1.parse(d);
           String date =sdf.format(d1);
           obj[i][3] =date;
           obj[i][4] =rs1.getString(4);
           obj[i][5] =rs1.getString(5);
           obj[i][6] =String.valueOf(rs1.getInt(6));
           obj[i][7] =String.valueOf(rs1.getInt(7));
           obj[i][8] =String.valueOf(rs1.getInt(8));
           obj[i][9] =String.valueOf(rs1.getInt(9));
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

       sp.setBounds(30,150,1300,y);
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
