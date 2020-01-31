import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Home_page extends JFrame {
    //frame
    JFrame frame = new JFrame("University Management System ");
    //Sign In
    JLabel label1=new JLabel("Sign In");
    JLabel head1=new JLabel();
    JLabel label2 = new JLabel("Username");
    JTextField username1=new JTextField(10);
    JLabel label3 = new JLabel("Password");
    JPasswordField password1=new JPasswordField(10);
    //Sign Up
    JLabel label4 = new JLabel("Sign Up");
    JLabel label5 = new JLabel("Username");
    JTextField username2=new JTextField(10);
    JLabel label6 = new JLabel("Password");
    JPasswordField password2=new JPasswordField(10);
    JLabel label7 = new JLabel("Re-Password");
    JPasswordField password3=new JPasswordField(10);
    //Button
    JButton sign_in=new JButton("Sign In");
    JButton sign_up=new JButton("Sign Up");
    JButton next=new JButton("Next");
    JButton exit=new JButton("Exit");
    JButton ok=new JButton("Ok");
    JButton back=new JButton("Back");
    //Menu
    JPanel panel=new JPanel();
    JMenuBar menuBar=new JMenuBar();
    JMenu menu1=new JMenu("Enrollment");
    JMenu menu2=new JMenu("Profile");
    JMenuItem item1=new JMenuItem("Enrollment");
    JMenuItem item2=new JMenuItem("Profile");
    JCheckBox checkBox1=new JCheckBox();
    JCheckBox checkBox2=new JCheckBox();
    JCheckBox checkBox3=new JCheckBox();
    //********************************************************** FIRST PAGE***************************************************************************
    public void Page_Begin() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //sign in
        label1.setBounds(20,20,90,30);
        label1.setForeground(Color.green);
        label2.setBounds(40, 50, 90, 30);
        label2.setForeground(Color.green);
        username1.setBounds(130,50,100,30);
        label3.setBounds(40, 80, 90, 30);
        label3.setForeground(Color.green);
        password1.setBounds(130,80,100,30);
        //sign up
        label4.setBounds(20, 120, 90, 30);
        label4.setForeground(Color.gray);
        label5.setBounds(40, 150, 90, 30);
        label5.setForeground(Color.gray);
        username2.setBounds(130,150,100,30);
        label6.setBounds(40, 180, 90, 30);
        label6.setForeground(Color.gray);
        password2.setBounds(130,180,100,30);
        label7.setBounds(40, 210, 90, 30);
        label7.setForeground(Color.gray);
        password3.setBounds(130,210,100,30);
        head1=new JLabel("You are a:");
        head1.setBounds(40,240,60,30);
        head1.setForeground(Color.orange);
        checkBox1=new JCheckBox("Lecture");
        checkBox1.setBounds(100,240,80,30);
        checkBox1.setForeground(Color.orange);
        checkBox2=new JCheckBox("Student");
        checkBox2.setBounds(180,240,80,30);
        checkBox2.setForeground(Color.orange);
        // Button
        sign_in.setBounds(330,100,90,30);
        sign_in.setBackground(Color.GREEN);
        sign_up.setBounds(330,260,90,30);
        sign_up.setBackground(Color.gray);
        exit.setBounds(350,400,90,30);
        exit.setBackground(Color.RED);

        frame.add(head1);
        frame.add(checkBox1);
        frame.add(checkBox2);
        frame.add(sign_up);
        ButtonGroup buttonGroup=new ButtonGroup();
        buttonGroup.add(checkBox1);
        buttonGroup.add(checkBox2);
        sign_up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Check();
                ShowData_Up();
                String Lecture= "Lecture";
                String Student="Student";
                String uname=username2.getText();
                if(checkBox1.isSelected())
                {
                    Lecture = "Lecture";
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sonoo","root","Esiva@98");
                        PreparedStatement ps = con.prepareStatement("update  University set Roll=? WHERE username=?");
                        ps.setString(1, Lecture);
                        ps.setString(2, uname);
                        ps.executeUpdate();
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(Home_page.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(checkBox2.isSelected())
                {
                    Student = "Student";
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sonoo","root","Esiva@98");
                        PreparedStatement ps = con.prepareStatement("update  University set Roll=? WHERE username=?");
                        ps.setString(1, Student);
                        ps.setString(2, uname);
                        ps.executeUpdate();
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(Home_page.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        frame.add(sign_in);
        sign_in.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowData_In();
            }
        });
        frame.add(label1);
        frame.add(label2);
        frame.add(username1);
        frame.add(label3);
        frame.add(password1);
        frame.add(label4);
        frame.add(label5);
        frame.add(username2);
        frame.add(label6);
        frame.add(password2);
        frame.add(label7);
        frame.add(password3);
        frame.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
//********************************************************SHOW DATA FOR SIGN UP**************************************************************************************

    public void ShowData_In() {
        String str1 = username1.getText();
        char[] p = password1.getPassword();
        String str2 = new String(p);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sonoo","root","Esiva@98");
            PreparedStatement ps = con.prepareStatement("select  *from University where username=? and password=?");
            ps.setString(1, str1);
            ps.setString(2, str2);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                new Details();
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "Incorrect email-Id or password..Try Again with correct detail");
                username1.setText(null);
                password1.setText(null);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    //********************************************************SHOW DATA FOR SIGN IN**************************************************************************************
    public void ShowData_Up() {
        String uname=username2.getText();
        String pass2=password2.getText();
        String pass3=password2.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sonoo","root","Esiva@98");
            PreparedStatement ps = con.prepareStatement("insert into University(username,password)"+"values(?,?)");
            ps.setString(1, uname);
            ps.setString(2, pass2);
            ps.executeUpdate();
            ResultSet rs = ps.executeQuery("select *from University");
            if (rs.next())
            {
                Menu();
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "Incorrect password or re_password..Try Again with correct detail");
                username2.setText(null);
                password2.setText(null);
                password3.setText(null);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void Menu(){
        JFrame frame = new JFrame("University Management System ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label_m=new JLabel(new ImageIcon("C:\\Users\\DELL\\Downloads\\i1.jpg"));
        label_m.setBounds(00,00,500,500);
        //sign in
        label1.setBounds(00,00,450,450);
        label1.setText(null);
        next.setBounds(350,350,90,40);
        next.setText("Enroll");
        next.setBackground(Color.green);
        back.setBounds(260,350,90,40);
        back.setText("Home");
        back.setBackground(Color.gray);
        frame.add(label1);
        menuBar.add(menu1);
        menuBar.add(menu2);
        menu1.add(item1);
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Enrollment();
            }
        });
        menu2.add(item2);
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proFile_sign_up();
            }
        });
        frame.add(label1);
        label1.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Page_Begin();
            }
        });

        label1.add(next);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Enrollment();
            }
        });
        frame.add(label_m);
        frame.setJMenuBar(menuBar);
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    //*****************************************************************************Check Password and Re-password**********************************************************************************
    public void Check() {
        String uname=username2.getText();
        String pass2 = password2.getText();
        String pass3 = password3.getText();

        if(uname.trim().equals("")||pass2.trim().equals("")||pass3.trim().equals("")){
            JOptionPane.showMessageDialog(null, "One or More Fields are Missing");
            username2.setText(null);
            password2.setText(null);
            password3.setText(null);
            return ;
        }
        else if(pass2.equals(pass3)){

        }
        else {
            JOptionPane.showMessageDialog(null, "Password Incorrect Re Type Password");
            username2.setText(null);
            password2.setText(null);
            password3.setText(null);
            return ;
        }
    }
    //********************************************************STUDENT ENROLLMENT**************************************************************************************
    public void Enrollment() {
        JFrame frame = new JFrame("University Management System //Enrollment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label=new JLabel("Please Select 3 Courses");
        label.setBounds(100,50,400,50);
        next=new JButton("Profile");
        next.setBounds(260,400,90,40);
        next.setBackground(Color.gray);
        exit.setText("Exit");
        exit.setBounds(350,400,90,40);
        exit.setBackground(Color.RED);
        checkBox1 = new JCheckBox("SENG 11111=Introduction of Programming");
        checkBox1.setBounds(100, 100, 300, 50);
        checkBox2 = new JCheckBox("SENG 11112=Fundamentals of Engineering");
        checkBox2.setBounds(100, 150, 300, 50);
        checkBox3 = new JCheckBox("SENG 11113=Data structure and Algorithms");
        checkBox3.setBounds(100, 200, 300, 50);

        frame.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        frame.add(next);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subject_1 = "SENG 11111=Introduction of Programming";
                String subject_2="SENG 11112=Fundamentals of Engineering";
                String subject_3="SENG 11113=Data structure and Algorithms";
                String uname=username2.getText();
                if(checkBox1.isSelected())
                {
                    subject_1 = "SENG 11111=Introduction of Programming";
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sonoo","root","Esiva@98");
                        PreparedStatement ps = con.prepareStatement("update  University set subject_1=? WHERE username=?");
                        ps.setString(1, subject_1);
                        ps.setString(2, uname);
                        ps.executeUpdate();
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(Home_page.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(checkBox2.isSelected())
                {
                    subject_2 = "SENG 11112=Fundamentals of Engineering";
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sonoo","root","Esiva@98");
                        PreparedStatement ps = con.prepareStatement("update  University set subject_2=? WHERE username=?");
                        ps.setString(1, subject_2);
                        ps.setString(2, uname);
                        ps.executeUpdate();
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(Home_page.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(checkBox3.isSelected())
                {
                    subject_3 = "SENG 11113=Data structure and Algorithms";
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sonoo","root","Esiva@98");
                        PreparedStatement ps = con.prepareStatement("update  University set subject_3=? WHERE username=?");
                        ps.setString(1, subject_3);
                        ps.setString(2, uname);
                        ps.executeUpdate();
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(Home_page.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                proFile_sign_up();
            }
        });

        frame.add(label);
        frame.add(checkBox1);
        frame.add(checkBox2);
        frame.add(checkBox3);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    //********************************************************SIGN UP PROFILE**************************************************************************************
    public void proFile_sign_up() {
        JFrame frame = new JFrame("University Management System //Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label1=new JLabel("Name:");
        label2 = new JLabel("ID:");
        label1.setBounds(40,20,90,40);
        label2.setBounds(40, 60, 90, 40);
        JTextField Name=new JTextField(10);
        Name.setBounds(130,20,100,40);
        JTextField ID=new JTextField(12);
        ID.setBounds(130,60,100,40);
        ok.setBounds(170,400,90,40);
        ok.setText("Finish");
        ok.setBackground(Color.green);
        back.setBounds(260,400,90,40);
        back.setBackground(Color.gray);
        exit.setBounds(350,400,90,40);
        exit.setBackground(Color.RED);

        JLabel label_m=new JLabel(new ImageIcon("C:\\Users\\DELL\\Downloads\\m.png"));
        JLabel label_w=new JLabel(new ImageIcon("C:\\Users\\DELL\\Downloads\\w.png"));
        label_m.setBounds(300,00,64,64);
        label_w.setBounds(364,00,64,64);

        frame.add(label_m);
        frame.add(label_w);
        frame.add(label1);
        frame.add(label2);
        frame.add(Name);
        frame.add(ID);

        frame.add(ok);
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name=Name.getText();
                String id=ID.getText();
                String uname=username2.getText();
                try
                {
                    String myDriver = "com.mysql.cj.jdbc.Driver";
                    String myUrl = "jdbc:mysql://localhost/sonoo";
                    Class.forName(myDriver);
                    Connection conn = DriverManager.getConnection(myUrl, "root","Esiva@98");

                    String query = "update University set Name = ?,id = ? where username = ?;";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString  (1, name);
                    preparedStmt.setString  (2, id);
                    preparedStmt.setString  (3, uname);
                    preparedStmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Successfully Updated");
                    username2.setText(null);
                    password2.setText(null);
                    password3.setText(null);
                }
                catch (Exception e1)
                {
                    System.err.println("Got an exception! ");
                    System.err.println(e1.getMessage());
                }
            }
        });

        frame.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Enrollment();
            }
        });

        frame.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}