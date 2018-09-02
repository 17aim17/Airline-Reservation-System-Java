import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public  class welcome extends JFrame implements ActionListener{
    Container c =getContentPane();
    JLabel l1 ,l2;
    ImageIcon mg =new ImageIcon("plane.jpeg");
    JButton al , ul;
    welcome()
    {
      setDefaultCloseOperation(EXIT_ON_CLOSE);

      setTitle("Welcome to our page");
      setSize(1366,768);
      setLayout(null);
      ul =new JButton("User Login");
      al =new JButton("Admin Login");
      add(al);
      add(ul);
      al.setBounds(480,450,140 ,45);
      al.setBackground(Color.blue);
      al.setForeground(Color.white);
      al.setFont(new Font("Arial" ,Font.PLAIN,18));
      ul.setBounds(650,450,140,45);
      ul.setBackground(Color.red);
      ul.setForeground(Color.white);
      ul.setFont(new Font("Arial" ,Font.PLAIN,18));


      c.setBackground(Color.white);
      l1 =new JLabel("Flight Reservation System");
      l1.setFont(new Font("Arial",Font.PLAIN,60));
      add(l1);

       l2 =new JLabel(mg);
       add(l2);
      l1.setForeground(Color.white);
      l2.setBounds(0,0,1920,1280);
      l1.setBounds(350,150,700,65);

      al.addActionListener(this);
      ul.addActionListener(this);
      setExtendedState(JFrame.MAXIMIZED_BOTH);
      setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
      if(e.getSource()==ul)
      {
        new login("User");

      }
      else
        new login("Admin");

        this.dispose();
    }

    public static void main(String[] args) {
      new welcome();
    }
}
