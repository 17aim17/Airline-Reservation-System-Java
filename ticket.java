import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;


public class ticket extends JInternalFrame implements ActionListener{
  Container c =getContentPane();
  Connection con;
  Statement st;
  JLabel l0,l1,v1,l2,v2,l3,v3,l4,v4,l5,v5,l6,v6,l7,v7,l8,v8,l9,v9,l10,v10;
  JLabel ps0;
  SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  JButton b1 ,b2 ;
  JComboBox cb ,cb1;
  int num=0;
  String cols[] ={"Ticket Number","Name","Email","Contact","Aadhar Card" ,"Age" ,"Type" ,"Gender"};
  Object  obj[][] ;
  JTable tb ;
  JScrollPane sp;
  int flag =0;
  String insert[];
  ArrayList list1 ;
  int booking_no =0;
  ticket(int book_no)
  {
    super("Fill In Details",true,false,false,false);
    // System.out.println("ticket");
    list1 =new ArrayList();

      booking_no =book_no;
       setSize(1366,712);

      setLayout(null);
      c.setBackground(Color.white);

      //connection WHERE
      try{
        Class.forName("com.mysql.jdbc.Driver");
        con =DriverManager.getConnection("jdbc:mysql://localhost/FlightReservation","root","");
        st = con.createStatement();
        }
        catch(Exception e){
         JOptionPane.showMessageDialog(this,"Server Not Connected");
      }
      String var1="" ,var2="" ,var3="" ,var4="",var5="",var8="",var11 ="" ,var31="";
      int var6=0,var7=0, var9=0,var10=0 ;
      try{
        String find  ="SELECT * FROM booking_master WHERE booking_no ="+book_no;
        ResultSet rs = st.executeQuery(find);
        while(rs.next())
        {
            var1 = rs.getString(2);
            var2 =rs.getString(4);
            var3 =rs.getString(3);
            var4 =rs.getString(5);
            var5 =rs.getString(6);
            var6 =rs.getInt(7);
            var7 =rs.getInt(8);
            var9 =rs.getInt(9);
            var10 =rs.getInt(10);
        }
        if(var6>var7)
        {
          num=var6;
          var8 ="Economy";
        }
        else if(var7>var6)
        {
          num=var7;
          var8 ="Buisness";
        }
        java.util.Date d1 = sdf.parse(var1);
         var11 =sdf1.format(d1);
        java.util.Date d2 = sdf.parse(var3);
         var31 =sdf1.format(d2);
      }catch(Exception e)
      {
         JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");
      }


    /**Labels here */
      l1 =new JLabel("Booking No");
      v1 =new JLabel(String.valueOf(book_no));
      l2 =new JLabel("Booking Date");
      v2 =new JLabel(var11);
      l3 =new JLabel("Aircraft Code");
      v3 =new JLabel(var2);
      l4 =new JLabel("Departure Date");
      v4 =new JLabel(var31);
      l5 =new JLabel("Source");
      v5 =new JLabel(var4);
      l6 =new JLabel("Destination");
      v6 =new JLabel(var5);
      l7 =new JLabel("Class");
      v7 =new JLabel(var8);
      l8 =new JLabel("No. Of tickets");
      v8 =new JLabel(String.valueOf(num));
      l9 =new JLabel("Charge Per ticket");
      v9 =new JLabel("$"+String.valueOf(var9)+"/-");
      l10 =new JLabel("Total Amount");
      v10 =new JLabel("$"+String.valueOf(var10)+"/-");


      add(l1);
      add(v1);
      add(l2);
      add(v2);
      add(l3);
      add(v3);
      add(l4);
      add(v4);
      add(l5);
      add(v5);
      add(l6);
      add(v6);
      add(l7);
      add(v7);
      add(l8);
      add(v8);
      add(l9);
      add(v9);
      add(l10);
      add(v10);



      l1.setFont(new Font("Arial" ,Font.BOLD|Font.ITALIC ,18));
      l2.setFont(new Font("Arial" ,Font.BOLD|Font.ITALIC ,18));
      l3.setFont(new Font("Arial" ,Font.BOLD|Font.ITALIC ,18));
      l4.setFont(new Font("Arial" ,Font.BOLD|Font.ITALIC ,18));
      l5.setFont(new Font("Arial" ,Font.BOLD|Font.ITALIC ,18));
      l6.setFont(new Font("Arial" ,Font.BOLD|Font.ITALIC ,18));
      l7.setFont(new Font("Arial" ,Font.BOLD|Font.ITALIC ,18));
      l8.setFont(new Font("Arial" ,Font.BOLD|Font.ITALIC ,18));
      l9.setFont(new Font("Arial" ,Font.BOLD|Font.ITALIC ,18));
      l10.setFont(new Font("Arial" ,Font.BOLD|Font.ITALIC ,18));

      v1.setFont(new Font("Arial" ,Font.PLAIN|Font.ITALIC ,18));
      v2.setFont(new Font("Arial" ,Font.PLAIN|Font.ITALIC ,18));
      v3.setFont(new Font("Arial" ,Font.PLAIN|Font.ITALIC ,18));
      v4.setFont(new Font("Arial" ,Font.PLAIN|Font.ITALIC ,18));
      v5.setFont(new Font("Arial" ,Font.PLAIN|Font.ITALIC ,18));
      v6.setFont(new Font("Arial" ,Font.PLAIN|Font.ITALIC ,18));
      v7.setFont(new Font("Arial" ,Font.PLAIN|Font.ITALIC,18));
      v8.setFont(new Font("Arial" ,Font.PLAIN|Font.ITALIC ,18));
      v9.setFont(new Font("Arial" ,Font.PLAIN|Font.ITALIC ,18));
      v10.setFont(new Font("Arial" ,Font.PLAIN|Font.ITALIC ,18));


        insert = new String[num];


    // Buttons here
      b1 =new JButton("Check-In");
      add(b1);
      b1.setFont(new Font("Arial" ,Font.BOLD|Font.ITALIC ,18));
      b1.setBackground(Color.blue);
      b1.setForeground(Color.white);

      b1.addActionListener(this);

      b2 =new JButton("Cancel");
      add(b2);
      b2.setFont(new Font("Arial" ,Font.BOLD|Font.ITALIC ,18));
      b2.setBackground(Color.red);
      b2.setForeground(Color.white);

      b2.addActionListener(this);


      l1.setBounds(100,100,200,30);
      v1.setBounds(350,100,200,30);
      l2.setBounds(650,100,200,30);
      v2.setBounds(950,100,200,30);

      l3.setBounds(100,150,200,30);
      v3.setBounds(350,150,200,30);
      l4.setBounds(650,150,200,30);
      v4.setBounds(950,150,200,30);

      l5.setBounds(100,200,200,30);
      v5.setBounds(350,200,200,30);
      l6.setBounds(650,200,200,30);
      v6.setBounds(950,200,200,30);


      l7.setBounds(100,250,200,30);
      v7.setBounds(350,250,200,30);
      l8.setBounds(650,250,200,30);
      v8.setBounds(950,250,200,30);

      l9.setBounds(100,300,200,30);
      v9.setBounds(350,300,200,30);
      l10.setBounds(650,300,200,30);
      v10.setBounds(950,300,200,30);


      /** PASSENGERS DETAILS*/
      ps0 =new JLabel("Passengers Details");
      ps0.setFont(new Font("Arial" ,Font.BOLD|Font.ITALIC ,22));
      ps0.setForeground(Color.blue);

      obj= new Object[num][8];
      try{
        String s1 = "SELECT t_no FROM booking_tran ORDER BY t_no DESC LIMIT 1";
        ResultSet rs =st.executeQuery(s1);
        int t_no =0;
        if(rs.next())
        {
         t_no =rs.getInt(1);
        }
        t_no++;
        for(int i=0;i<num;i++){
          for(int j=0;j<8;j++){
            obj[i][0] =t_no;
          }
          t_no++;
        }

      }catch(Exception ex){
         JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment");
      }


      add(ps0);
      tb =new JTable(obj ,cols);
      sp =new JScrollPane(tb ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      add(sp);
      tb.setRowHeight(32);
      tb.setBackground(Color.white);
      tb.setFont(new Font("Arial" ,Font.PLAIN ,15));
      tb.getTableHeader().setFont(new Font("Arial", Font.ITALIC, 18));
      tb.getTableHeader().setReorderingAllowed(false);   // dragging  false
      tb.getColumnModel().getColumn(0).setResizable(false);

      ps0.setBounds(550,350,300,40);
      sp.setBounds(30,400,1300,185);
      b2.setBounds(450,600,150,35);
      b1.setBounds(650,600,150,35);


      TableModel tm =new mytablemodel();
      tb.setModel(tm);
      // combobox inside JTable
      TableColumn type  =tb.getColumnModel().getColumn(6);
      cb =new JComboBox();
      cb.addItem("Adult");
      cb.addItem("Child");
      type.setCellEditor(new DefaultCellEditor(cb));

      TableColumn gender  =tb.getColumnModel().getColumn(7);
      cb1 =new JComboBox();
      cb1.addItem("Male");
      cb1.addItem("Female");
      gender.setCellEditor(new DefaultCellEditor(cb1));


       setVisible(true);
  }
public class mytablemodel extends DefaultTableModel{
  mytablemodel()
  {
    super(obj ,cols);
    // System.out.println("inside");
  }
  public boolean isCellEditable(int row ,int cols){
    if(cols==0){
      return false;
    }
    return true;
  }
}
/**action listeners*/
public void actionPerformed(ActionEvent e)
{
  try
  {
    if(e.getSource()==b1)
    {
      for(int i=0;i<num;i++)
      {

        String a="",b="",c="",d="",e1="",f="",h="",j="",g="";
         a =String.valueOf(booking_no);
         b =String.valueOf(tb.getValueAt(i,0));

         c =(String)(tb.getValueAt(i,1));
         d =(String)(tb.getValueAt(i,2));
         e1 =(String)(tb.getValueAt(i,3));
         f =(String)(tb.getValueAt(i,4));

         g =(String)(tb.getValueAt(i,5));
         h =(String)(tb.getValueAt(i,6));
         j =(String)(tb.getValueAt(i,7));

    try{
      if( (c!=null || !c.isEmpty() ) && (d!=null || !d.isEmpty() )&& (e1!=null || !e1.isEmpty() ) && (f!=null || !f.isEmpty() ) && (g!=null || !g.isEmpty() ) && (h!=null || !h.isEmpty() ) && (j!=null || !j.isEmpty() ) )
      {
         System.out.println("asd");
         if(!validation.checkString(c))
         {
           JOptionPane.showMessageDialog(this ,"Enter a valid Name e.g only letter ");
         }
         else if(!validation.checkEmail(d)){
          JOptionPane.showMessageDialog(this ,"Enter a valid Email ");
          }
         else if(!validation.checkContact(e1)){
          JOptionPane.showMessageDialog(this ,"Enter a valid Contact e.g only digits and 10 length");
         }
         else if(!validation.checkAadhar(f)){
          JOptionPane.showMessageDialog(this ,"Enter a valid Aadhar only digits and 12 length");
         }
         else if(!validation.checkAge(g)){
           JOptionPane.showMessageDialog(this ,"Enter a valid Age ");
         }else{
                       insert[i] ="INSERT INTO booking_tran VALUES("+a+","+b+",'"+c+"','"+d+"',"+e1+",'"+f+"',"+g+",'"+h+"','"+j+"');";
                    
                         if(list1.size()>1){
                           if(list1.contains(i)){
                             list1.remove(i);
                           }
                         }
                         else{
                           list1.clear();
                         }



         }
      }
     }catch(NullPointerException ef)
        {
            if(list1.contains(i)){}
              else{
                list1.add(i);
              }
        }

    }

      if(list1.size()==0){
        for(int k=0;k<num;k++)
        {

           st.executeUpdate(insert[k]);
        }
        JOptionPane.showMessageDialog(this, "Flight is booked Succesfully");
        this.dispose();
      }
      else{
        JOptionPane.showMessageDialog(this, "Please fill all the details");
      }



    }



    if(e.getSource()==b2)
    {
       int eco_seats =0;
       int buis_seats=0;
       String acode = v3.getText();
       String dd =  sdf.format(sdf1.parse(v4.getText()));
       String class1 =v7.getText();
       int seats = Integer.parseInt(v8.getText());
       // System.out.println(acode);
       // System.out.println(dd);
       // System.out.println(class1);
       // System.out.println(seats);

       String getseats ="SELECT * FROM  flight_booking_info WHERE a_code='"+acode+"' AND departure_date ='"+dd+"'";
       ResultSet rs =st.executeQuery(getseats);
       if(rs.next())
       {
         eco_seats = rs.getInt(4);
         buis_seats =rs.getInt(5);
       }
       if(class1.equals("Economy"))
       {
          int updatedeco =  eco_seats+seats;
          String update1 ="UPDATE flight_booking_info SET av_eco_st ="+updatedeco+" WHERE a_code='"+acode+"' AND departure_date ='"+dd+"'";
          st.executeUpdate(update1);

       }
       else if(class1.equals("Buisness"))
       {
         int updatedbuis =  buis_seats+seats;
         String update2 ="UPDATE flight_booking_info SET av_buis_st ="+updatedbuis+" WHERE a_code='"+acode+"' AND departure_date ='"+dd+"';";
         st.executeUpdate(update2);
       }

        String delete ="DELETE  FROM booking_master WHERE booking_no='"+v1.getText()+"'";
        st.executeUpdate(delete);
        this.dispose();

    }
  }

  catch(Exception ex)
  {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(this,"Unfortunately ,App Not working at moment"+ex);
  }
}

}
