import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javax.swing.table.*;

public class bookingmaster extends JInternalFrame implements ActionListener  {
  Container c =getContentPane();
  Connection con;
  Statement st;
  String formatted;
  JLabel l0,l1,l2,l3,l4,l5,x;
  JTextField t1;
  JPanel pn ,pn1;
  JDesktopPane pane1;
  java.util.Date cd =new java.util.Date();
  java.util.Date bd =new java.util.Date();
  SimpleDateFormat sdf =new SimpleDateFormat("d-M-yyyy");
  SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-M-d");
  JComboBox cb1,cb2,cb3 ,cb4,cb5,cb6;
  JButton b1 , b2;
  String cols[] ={"Sr No.","A-Code","Departure","Arrival","Hours" ,"Eco-seats" ,"Buis-seats"};
  Object  obj[][];
  JTable tb ;
  int num=0;
  JScrollPane sp;
  JButton but[];
  ArrayList list1;
  int temp1 =0;
  bookingmaster(JDesktopPane p)
  {
      super("Booking Master",true,true,true,true);
    list1 =new ArrayList();
    pane1=p;
     setSize(1366,712);
      setLayout(null);
      c.setBackground(Color.white);

      cb1 =new JComboBox();
      add(cb1);
      cb1.addItem("Choose City");
      cb1.setFont(new Font("Arial" ,Font.BOLD ,15));
      cb1.setBackground(Color.white);

      cb2 =new JComboBox();
      add(cb2);
      cb2.addItem("Choose City");
      cb2.setFont(new Font("Arial" ,Font.BOLD ,15));
      cb2.setBackground(Color.white);

      cb3 =new JComboBox();
      add(cb3);
      cb3.addItem("Adults");
      cb3.addItem("1");
      cb3.addItem("2");
      cb3.addItem("3");
      cb3.addItem("4");
      cb3.addItem("5");

      cb3.setFont(new Font("Arial" ,Font.BOLD ,15));
      cb3.setBackground(Color.white);

      cb4 =new JComboBox();
      add(cb4);
      cb4.addItem("Childern");
      cb4.addItem("1");
      cb4.addItem("2");
      cb4.addItem("3");
      cb4.addItem("4");

      cb4.setFont(new Font("Arial" ,Font.BOLD ,15));
      cb4.setBackground(Color.white);

      cb5 =new JComboBox();
      add(cb5);
      cb5.addItem("Infants");
      cb5.addItem("1");
      cb5.addItem("2");
      cb5.setFont(new Font("Arial" ,Font.BOLD ,15));
      cb5.setBackground(Color.white);

      cb6 =new JComboBox();
      add(cb6);
      cb6.addItem("Choose Class");
      cb6.addItem("Economy");
      cb6.addItem("Buisness");
      cb6.setFont(new Font("Arial" ,Font.BOLD ,15));
      cb6.setBackground(Color.white);

    /**Labels here */
      l1 =new JLabel("Origin");
      l2 =new JLabel("Destination");
      l3 =new JLabel("Departure Date");
      l4 =new JLabel("Passengers");
      l5 =new JLabel("Class");
      x =new JLabel("(DD-MM-YYYY)");


      add(l1);
      add(l2);
      add(l3);
      add(l4);
      add(l5);
      add(x);



      l1.setFont(new Font("Arial" ,Font.BOLD ,18));
      l2.setFont(new Font("Arial" ,Font.BOLD ,18));
      l3.setFont(new Font("Arial" ,Font.BOLD ,18));
      l4.setFont(new Font("Arial" ,Font.BOLD ,18));
      l5.setFont(new Font("Arial" ,Font.BOLD ,18));
      x.setFont(new Font("Arial",Font.BOLD ,13));
      x.setForeground(Color.red);

    // Buttons here
      b1 =new JButton("Search");
      add(b1);



      b1.setFont(new Font("Arial" ,Font.BOLD ,17));
      b1.setBackground(Color.white);




    /**TextField*/
    t1 =new JTextField(30);
    t1.setFont(new Font("Arial" ,Font.BOLD ,15));
    add(t1);



      l1.setBounds(350,100,250,32);
      l2.setBounds(350,150,250,32);
      l3.setBounds(350,200,250,32);
      l4.setBounds(350,250,250,32);
      l5.setBounds(350,300,250,32);



      cb1.setBounds(600,100,150,32);
      cb2.setBounds(600,150,150,32);
      t1.setBounds (600,200,150,32);
        x.setBounds(750,200,100,30);
      cb3.setBounds(600,250,150,32);
      cb4.setBounds(800,250,150,32);
      cb5.setBounds(1000,250,150,32);
      cb6.setBounds(600,300,150,32);

      b1.setBounds(600,350,100,32);
      //connection WHERE
      try{
        Class.forName("com.mysql.jdbc.Driver");
        con =DriverManager.getConnection("jdbc:mysql://localhost/FlightReservation","root","");
        st = con.createStatement();
        String get1 ="SELECT DISTINCT source FROM route";
        ResultSet rs1 = st.executeQuery(get1);
        while(rs1.next()){
          cb1.addItem(rs1.getString(1));
        }
        String get2 ="SELECT DISTINCT destination FROM route";
        ResultSet rs2 = st.executeQuery(get2);
        while(rs2.next()){
          cb2.addItem(rs2.getString(1));
        }

      }catch(Exception e){
        JOptionPane.showMessageDialog(this,"Server Not Connected");
      }


      b1.addActionListener(this);
  }

/**action listeners*/
public void actionPerformed(ActionEvent e)
{
  try{
    if(e.getSource()==b1)
     {

       Calendar d =Calendar.getInstance();
       String current_date = String.valueOf(d.get(Calendar.DATE))+"-"+String.valueOf(d.get(Calendar.MONTH)+1)+"-"+String.valueOf(d.get(Calendar.YEAR));
       if (cb1.getSelectedIndex() == 0 || cb2.getSelectedIndex() == 0 || cb3.getSelectedIndex() == 0 || cb6.getSelectedIndex() == 0 || t1.getText().trim().length()==0)
        {
          if(sp!=null)
                  {
                    remove(sp);
                    sp=null;
                    tb=null;
                    if(but.length>0)
                    {
                      for(int x =0; x<but.length;x++)
                      {
                        remove(but[x]);
                      }
                    }
                    repaint();
                  }
          JOptionPane.showMessageDialog(this ,"Please Fill all the fields");
        }
        else
        {
            String entered =t1.getText();
          try{
              cd = sdf.parse(current_date);
              bd = sdf.parse(entered);
               formatted = sdf.format(sdf.parse(entered));
          }
          catch(ParseException f3)
          {
            temp1=1;
            if(sp!=null)
                    {
                      remove(sp);
                      sp=null;
                      tb=null;
                      if(but.length>0)
                      {
                        for(int x =0; x<but.length;x++)
                        {
                          remove(but[x]);
                        }
                      }
                      repaint();
                    }
            JOptionPane.showMessageDialog(this, "Enter a valid Format");
          }



          // jtable
          String get1;
           if(bd.after(cd) && formatted.equals(entered)){

                 get1 ="SELECT timing.a_code,timing.d_time,timing.a_time,timing.hours FROM timing join route on timing.a_code = route.a_code WHERE source ='"+cb1.getSelectedItem()+"' and destination ='"+cb2.getSelectedItem()+"'";
                 try{
                   String count =" SELECT count(*) AS SIZE  FROM ("+get1+")AS t;";
                   ResultSet dum1 =st.executeQuery(count);
                   if(dum1.next()){ num = dum1.getInt(1);}
                   dum1.close();
                 }catch(Exception e1) {

                 }

                ResultSet rs1 =st.executeQuery(get1);
                 int i=0;
                 int j=1;
                 String data ="";
                 list1.clear();

                 obj = new Object[num][7];
                while(rs1.next())
                {
                  obj[i][0] =String.valueOf(j);
                  j++;
                  data =rs1.getString(1);
                  list1.add(data);
                  obj[i][1]=data;
                  obj[i][2] =rs1.getString(2);
                  obj[i][3] =rs1.getString(3);
                  obj[i][4] =rs1.getString(4);

                  i++;
                }
                i=0;
                int all_tickets = cb3.getSelectedIndex()+cb4.getSelectedIndex();
                int available_eco_seats =0;
                int available_buis_seats =0;
                String showdate =t1.getText();
                java.util.Date d1 =sdf.parse(showdate);
                String matchdate =sdf1.format(d1);
                i=0;
                for(int k=0;k<list1.size();k++)
                {

                  String get2 ="SELECT * FROM flight_booking_info WHERE a_code ='"+list1.get(k)+"' and departure_date='"+matchdate+"'";
                  ResultSet rs2 =st.executeQuery(get2);
                  if(!rs2.next())
                  {
                    String get4 ="SELECT * FROM fleetinfo where a_code = '"+list1.get(k)+"'";
                    ResultSet i2 =st.executeQuery(get4);
                    if(i2.next()){
                      available_eco_seats =i2.getInt(3);
                      available_buis_seats =i2.getInt(4);
                    obj[i][5] =String.valueOf(available_eco_seats);
                    obj[i][6] =String.valueOf(available_buis_seats);
                      i++;
                    }
                  }
                  else{
                    available_eco_seats =rs2.getInt(4);
                    available_buis_seats =rs2.getInt(5);
                    obj[i][5] =String.valueOf(available_eco_seats);
                    obj[i][6] =String.valueOf(available_buis_seats);
                      i++;
                  }
                }
                if(String.valueOf(cb1.getSelectedItem()).trim().equals(String.valueOf(cb2.getSelectedItem()).trim()))
                {
                  if(sp!=null)
                          {
                            remove(sp);
                            sp=null;
                            tb=null;
                            if(but.length>0)
                            {
                              for(int x =0; x<but.length;x++)
                              {
                                remove(but[x]);
                              }
                            }
                            repaint();
                          }
                  JOptionPane.showMessageDialog(this, "Either Choose Different Source or destination");

                }
                else
                {
                  if(sp!=null)
                  {
                    remove(sp);
                    sp=null;
                    tb=null;
                    if(but.length>0)
                    {
                      for(int x =0; x<but.length;x++)
                      {
                        remove(but[x]);
                      }
                    }
                    repaint();
                  }

                                  if((num>0 && ( cb6.getSelectedIndex()==1 && available_eco_seats>=all_tickets))|| (num>0 && ( cb6.getSelectedIndex()==2 && available_buis_seats>=all_tickets)))
                                  {

                                  tb =new JTable(obj ,cols);
                                  sp =new JScrollPane(tb ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                                  add(sp);


                                  tb.setRowHeight(32);
                                  tb.setBackground(Color.white);
                                  tb.setFont(new Font("Arial" ,Font.PLAIN ,15));
                                  tb.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
                                  sp.setBounds(50,400,1000,185);
                                  tb.getTableHeader().setReorderingAllowed(false);   // dragging  false
                                  tb.getColumnModel().getColumn(0).setResizable(false);



                                         TableModel tm =new mytablemodel();
                                         tb.setModel(tm);


                                  but = new JButton[i];

                                  int y=425;
                                  for(int a=0;a<but.length;a++)
                                  {
                                    but[a]=new JButton("Book");
                                    add(but[a]);
                                    but[a].setBounds(1070,y,100,20);
                                    y=y+30;
                                    but[a].addActionListener(this);
                                  }
                                    i=0;
                                }else{
                                  if(sp!=null)
                                          {
                                            remove(sp);
                                            sp=null;
                                            tb=null;
                                            if(but.length>0)
                                            {
                                              for(int x =0; x<but.length;x++)
                                              {
                                                remove(but[x]);
                                              }
                                            }
                                            repaint();
                                          }
                                          JOptionPane.showMessageDialog(this ,"Sorry !! No Flights avalibale");

                                }

                }
            }
            else
            {
              if(sp!=null)
                      {
                        remove(sp);
                        sp=null;
                        tb=null;
                        if(but.length>0)
                        {
                          for(int x =0; x<but.length;x++)
                          {
                            remove(but[x]);
                          }
                        }
                        repaint();
                      }
            JOptionPane.showMessageDialog(this ,"Enter a Future Date");
            }
        }
     }
     // end of b2
     if(num>0){
     for(int i=0;i<but.length;i++)
     {
         if(e.getSource()==but[i])
         {

           Calendar d =Calendar.getInstance();
           String month =String.valueOf(d.get(Calendar.MONTH)+1);
           String date =String.valueOf(d.get(Calendar.DATE));
           String book_date = String.valueOf(d.get(Calendar.YEAR))+"-"+format(month)+"-"+format(date);
           // book-date

           String s1 = "SELECT booking_no FROM booking_master ORDER BY booking_no DESC LIMIT 1";
           ResultSet rs =st.executeQuery(s1);
           int book_no =0;
           if(rs.next())
           {
             book_no =rs.getInt(1);
           }
           book_no++;
           //book no

           String dept_date = t1.getText();
           // depatute date

           String source = String.valueOf(cb1.getSelectedItem());
           // getSource

           String dest = String.valueOf(cb2.getSelectedItem());
            // destination

            int num_tickets = cb3.getSelectedIndex()+cb4.getSelectedIndex();
            // num tickets
            int years =0 , months=0 ,days=0;
            try{
              DateTimeFormatter format1 =DateTimeFormatter.ofPattern("yyyy-M-d");
              LocalDate d2 =LocalDate.parse(book_date , format1);

              DateTimeFormatter format2 =DateTimeFormatter.ofPattern("d-M-yyyy");
              LocalDate d1 =LocalDate.parse(dept_date ,format2);

              Period diff = Period.between(d2 ,d1);
               years = diff.getYears();
               months = diff.getMonths();
               days = diff.getDays();


            int baseprice=0;
            String rcode5 ="";
            String acode = String.valueOf(obj[i][1]);
            String find ="SELECT basic ,r_code FROM route WHERE a_code ='"+acode+"'AND source ='"+source+"' AND destination ='"+dest+"'";
            ResultSet bs =st.executeQuery(find);
            if(bs.next())
            {
               baseprice = bs.getInt(1);
               rcode5 =bs.getString(2);
            }


            double per_ticket_charge = 0.0 ;
            if(years>0){
              per_ticket_charge =(double)baseprice;
            } else {
                if(months>=1){
                  per_ticket_charge =(double)baseprice;
                } else if(days<5) {
                  per_ticket_charge =(double)baseprice + (double)baseprice/2.0;
                } else if(days<10){
                  per_ticket_charge =(double)baseprice + (double)baseprice/3.0;
                } else if(days<20){
                  per_ticket_charge =(double)baseprice + (double)baseprice/4.0;
                }else{
                  per_ticket_charge =(double)baseprice + (double)baseprice/10.0;

                }
            }

            int eco_seats=0 ;
            int buis_seats=0 ;
            if(cb6.getSelectedIndex()==1){
              eco_seats = num_tickets;
              buis_seats =0;
            } else if(cb6.getSelectedIndex()==2){
              eco_seats = 0;
              buis_seats =num_tickets;
              per_ticket_charge =2*per_ticket_charge;
            }

            // d1 =dept_date  d2 = booking_date
            Double total =0.0;
            total = num_tickets*per_ticket_charge;

            String in = "INSERT INTO booking_master VALUES("+book_no+",'"+book_date+"','"+String.valueOf(d1)+"','"+acode+"','"+source+"','"+dest+"',"+eco_seats+","+buis_seats+","+per_ticket_charge+","+total+")";
            st.executeUpdate(in);

            // updsting seats
            String out ="SELECT eco_seats ,buis_seats FROM fleetinfo WHERE a_code ='"+acode+"'";
            ResultSet rout = st.executeQuery(out);
            int t_eco =0;
            int t_buis =0;
            if(rout.next())
            {
              t_eco =rout.getInt(1);
              t_buis =rout.getInt(2);
            }

            int new_eco =t_eco-eco_seats;
            int new_buis =t_buis-buis_seats;

            String dof ="";
            int es1 =0 ,bs1=0;
            String rcodeinflightinfo ="";
            String out1 = "SELECT * FROM flight_booking_info WHERE a_code ='"+acode+"' AND r_code ='"+rcode5+"'AND departure_date='"+d1+"'";
            ResultSet  rout1 =st.executeQuery(out1);
            if(rout1.next())
            {
              // System.out.println("inside if");
              rcodeinflightinfo =rout1.getString(3);
              es1 =  rout1.getInt(4);
              bs1 = rout1.getInt(5);
              String in3 ="UPDATE flight_booking_info SET av_eco_st ="+ (es1 - eco_seats) + ", av_buis_st=" + (bs1 - buis_seats)+" WHERE a_code ='"+acode+"' AND r_code = '"+rcodeinflightinfo+"' AND departure_date ='"+d1+"'";
              st.executeUpdate(in3);
            }
            else
            {
              // System.out.println("indise else");
            String in2 ="INSERT INTO flight_booking_info values('"+acode+"','"+String.valueOf(d1)+"','"+rcode5+"',"+new_eco+","+new_buis+")";
            st.executeUpdate(in2);

            }


            ticket obj =new ticket(book_no);
            obj.show();
            try{
              obj.setMaximum(true);
            }
            catch(Exception ew)
            {

            }
            pane1.add(obj);
            this.dispose();
          }catch(Exception ex2){

          }
        }
     }
   }
    }
   catch(Exception ex) {
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
