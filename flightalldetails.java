import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;


public class flightalldetails extends JInternalFrame implements ActionListener{
  Container c =getContentPane();
  String query= "SELECT f.a_code,f.a_name,r.source,r.destination,t.d_time,t.a_time,t.hours,f.eco_seats,f.buis_seats FROM fleetInfo as f JOIN route as r on f.a_code =r.a_code JOIN timing as t on f.a_code =t.a_code";
  Connection con;
  Statement st;
  JLabel l1,l2;
  JComboBox cb1 ,cb2;
  JButton b1;
  int num;
  String cols[] ={"Sr No","A_Code","Name","Source","Destination" ,"Departure time" ,"Arrival Time" ,"Journey Hours" ,"Class E Seats", "Class B Seats","Total Seats"};
  Object  obj[][] ;
  JTable tb ;
  JScrollPane sp;

  flightalldetails()
  {
    super("Flight All details Report",true,true,true,true);
    setSize(1366,712);
     setLayout(null);
    c.setBackground(Color.white);
    l1 =new JLabel("Source");
    l2 =new JLabel("Destination");

    l1.setFont(new Font("Arial" ,Font.PLAIN ,18));
    l2.setFont(new Font("Arial" ,Font.PLAIN ,18));

    b1 =new JButton("Show");
    b1.setFont(new Font("Arial" ,Font.PLAIN ,18));
    b1.setBackground(Color.white);

    cb1 = new JComboBox();
    cb1.setFont(new Font("Arial" ,Font.PLAIN ,15));
    cb1.setBackground(Color.white);
    cb1.addItem("All");

    cb2 = new JComboBox();
    cb2.setFont(new Font("Arial" ,Font.PLAIN ,15));
    cb2.setBackground(Color.white);
    cb2.addItem("All");

    add(l1);
    add(l2);
    add(cb1);
    add(cb2);
    add(b1);

    /*set bounds*/
    l1.setBounds(400,50,100,30);
    cb1.setBounds(530,50,100,30);
    l2.setBounds(670,50,100,30);
    cb2.setBounds(790,50,100,30);
    b1.setBounds(630,100,100,30);

    /*Connection here*/
    try{
      Class.forName("com.mysql.jdbc.Driver");
      con =DriverManager.getConnection("jdbc:mysql://localhost/FlightReservation","root","");
      st = con.createStatement();
        String find1 ="SELECT DISTINCT source FROM route_master";
        ResultSet rs1 =st.executeQuery(find1);
        while(rs1.next())
        {
          cb1.addItem(rs1.getString(1));
        }
        String find2 ="SELECT DISTINCT destination FROM route_master";
        ResultSet rs2 =st.executeQuery(find2);
        while(rs2.next())
        {
          cb2.addItem(rs2.getString(1));
        }
      }
      catch(Exception e){
        JOptionPane.showMessageDialog(this,"Server Not Connected");
    }
    createtable(query);
    b1.addActionListener(this);

  }



/*Action listener*/
public void actionPerformed(ActionEvent e)
{
  try{
      if(cb1.getSelectedIndex()==0 && cb2.getSelectedIndex()==0)
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
      else if(cb2.getSelectedIndex()==0){
          String  newquery = query+" WHERE source ='"+cb1.getSelectedItem()+"'";
          if(sp!=null)
          {
            remove(sp);
            sp=null;
            tb=null;
            repaint();
          }
            createtable(newquery);
      }
      else if(cb1.getSelectedIndex()==0)
      {
        if(sp!=null)
        {
          remove(sp);
          sp=null;
          tb=null;
          repaint();
        }
        String  newquery = query+" WHERE destination='"+cb2.getSelectedItem()+"'";
        createtable(newquery);
      }
      else{
        if(sp!=null)
        {
          remove(sp);
          sp=null;
          tb=null;
          repaint();
        }
        String  newquery = query+" WHERE source ='"+cb1.getSelectedItem()+"' AND destination='"+cb2.getSelectedItem()+"'";
          createtable(newquery);
      }

  }catch(Exception z){

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

     if(num!=0){
       int i=0;
       int sr_no =1;
       obj= new Object[num][11];
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
           obj[i][6] =rs1.getString(6);
           int es =rs1.getInt(8);
           int bs =rs1.getInt(9);
           int total =es+bs;
           obj[i][7] =String.valueOf(rs1.getInt(7));
           obj[i][8] =String.valueOf(es);
           obj[i][9] =String.valueOf(bs);
           obj[i][10] =String.valueOf(total);

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
       sp.setBounds(30,150,1300,500);

       TableModel tm =new mytablemodel();
       tb.setModel(tm);

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
