import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


class userpanel extends JFrame implements ActionListener{

    Container c= getContentPane();
    JMenu m1 , m2 , m3 , m4 ,m5 ,m6;
    JMenuItem i1 ,i2 ,i3, i4,i5,i6,i7,i8,i9,i10,i11,i12;
    JRadioButtonMenuItem r1 ,r2 ,r3, r4,r5,r6,r7,r8;
    JMenuBar mb;
    JDesktopPane pane=new JDesktopPane();
    ImageIcon mg =new ImageIcon("plane.jpeg");
    String user ="";

    userpanel(String uname)
    {
        user =uname;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        mb =new JMenuBar();
        mb.setBackground(Color.white);
        m1 =new JMenu("User");
        m2 =new JMenu("Transaction");
        m3 =new JMenu("Views");
        m4 =new JMenu("Exit");
        mb.add(Box.createRigidArea(new Dimension(20,30)));

        m1.setFont(new Font("Arial",Font.PLAIN,15));
        m2.setFont(new Font("Arial",Font.PLAIN,15));
        m3.setFont(new Font("Arial",Font.PLAIN,15));
        m4.setFont(new Font("Arial",Font.PLAIN,15));

        setContentPane(pane);

        i1  =new JMenuItem("Change Password");

        i2  =new JMenuItem("Booking Master");

        i3  =new JMenuItem("All Flight Details");
        // i4  =new JMenuItem("View Routes");
        // i5  =new JMenuItem("View Time Table");
        i6  =new JMenuItem("Date Wise Flights");
        i7  =new JMenuItem("Ticket Enquiry");
        i8 =new JMenuItem("Log Out");

        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        // i4.addActionListener(this);
        // i5.addActionListener(this);
        i6.addActionListener(this);
        i7.addActionListener(this);
        i8.addActionListener(this);

        setSize(1366,768);
        setTitle("User Panel");


          // ADDING ITEMS I1-I4 TO m1
          m1.add(i1);

          m2.add(i2);

          m3.add(i3);
          // m3.add(i4);
          // m3.add(i5);
          m3.add(i6);
          m3.add(i7);
          m4.add(i8);


          //ADDING ALL m1 , m2 , m5 ,m6 to mb
          mb.add(m1);
          mb.add(m2);
          mb.add(m3);
          mb.add(m4);

        setJMenuBar(mb);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
}

public void actionPerformed(ActionEvent e)
{
  if(e.getSource()==i1)
  {
   ucp obj= new ucp(user);
   obj.show();
   try{
     obj.setMaximum(true);
   }
   catch(Exception ew)
   {
   }
   pane.add(obj);
  }
  if(e.getSource()==i2)
  {
   bookingmaster obj= new bookingmaster(pane);
   obj.show();
   try{
     obj.setMaximum(true);
   }
   catch(Exception ew)
   {
   }
   pane.add(obj);
  }
  if(e.getSource()==i3)
  {
    flightalldetails obj =new flightalldetails();
   obj.show();
   try{
     obj.setMaximum(true);
   }
   catch(Exception ew)
   {
   }
   pane.add(obj);
  }
  if(e.getSource()==i6)
  {
    datewiseflight obj =new datewiseflight();
   obj.show();
   try{
     obj.setMaximum(true);
   }
   catch(Exception ew)
   {
   }
   pane.add(obj);
  }
  if(e.getSource()==i7)
  {
    datewiseticket obj =new datewiseticket();
   obj.show();
   try{
     obj.setMaximum(true);
   }
   catch(Exception ew)
   {
   }
   pane.add(obj);
  }




  if(e.getSource()==i8)
  {
    new welcome();
    this.dispose();
  }
}

}
