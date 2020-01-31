import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
public class Details extends JFrame implements ActionListener {
    JLabel label1, l_uname, l_name, l_id, l_sub1, e_uname,l_sub2,l_sub3,stu_lec;
    JTextField tf1, tf2, tf3, tf4, t_uname,tf6,tf7,t_stu_lec;
    JButton Submit,exit,back;

    Details() {
        super("Information");
        e_uname = new JLabel("Enter Username:");
        e_uname.setBounds(20, 20, 100, 20);
        t_uname = new JTextField(20);
        t_uname.setBounds(130, 20, 200, 20);
        Submit = new JButton("Submit");
        Submit.setBounds(50, 50, 100, 20);
        Submit.addActionListener(this);
        label1 = new JLabel("Information From Database");
        label1.setBounds(30, 80, 450, 30);
        label1.setForeground(Color.red);
        label1.setFont(new Font("Serif", Font.BOLD, 20));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        l_uname = new JLabel("Username:");
        l_uname.setBounds(20, 120, 100, 20);
        tf1 = new JTextField(50);
        tf1.setBounds(130, 120, 200, 20);
        l_name = new JLabel("Name:");
        l_name.setBounds(20, 150, 100, 20);
        tf2 = new JTextField(100);
        tf2.setBounds(130, 150, 200, 20);
        l_id = new JLabel("ID:");
        l_id.setBounds(20, 180, 100, 20);
        tf3 = new JTextField(50);
        tf3.setBounds(130, 180, 200, 20);
        l_sub1 = new JLabel("Subject_1:");
        l_sub1.setBounds(20, 210, 100, 20);
        tf4 = new JTextField(50);
        tf4.setBounds(130, 210, 300, 20);
        l_sub2 = new JLabel("Subject_2:");
        l_sub2.setBounds(20, 240, 100, 20);
        tf6 = new JTextField(50);
        tf6.setBounds(130, 240, 300, 20);
        l_sub3 = new JLabel("Subject_3:");
        l_sub3.setBounds(20, 270, 100, 20);
        tf7 = new JTextField(50);
        tf7.setBounds(130, 270, 300, 20);
        stu_lec = new JLabel("Roll:");
        stu_lec.setBounds(20, 300, 100, 20);
        t_stu_lec=new JTextField(50);
        t_stu_lec.setBounds(130, 300, 300, 20);
        setLayout(null);
        exit=new JButton("Exit");
        exit.setBounds(350,400,90,40);
        exit.setBackground(Color.red);
        back=new JButton("Back");
        back.setBounds(260,400,90,40);
        back.setBackground(Color.gray);
        //Add components to the JFrame  
        add(e_uname);
        add(t_uname);
        add(Submit);
        add(label1);
        add(l_uname);
        add(tf1);
        add(l_name);
        add(tf2);
        add(l_id);
        add(tf3);
        add(l_sub1);
        add(tf4);
        add(l_sub2);
        add(tf6);
        add(l_sub3);
        add(tf7);
        add(stu_lec);
        add(t_stu_lec);

        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home_page hp=new Home_page();
                hp.Page_Begin();
            }
        });

        add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        //Set TextField Editable False  
        tf1.setEditable(false);
        tf2.setEditable(false);
        tf3.setEditable(false);
        tf4.setEditable(false);
        tf6.setEditable(false);
        tf7.setEditable(false);
        t_stu_lec.setEditable(false);
    }
    public void actionPerformed(ActionEvent e) {
        try {
            String str = t_uname.getText();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo", "root", "Esiva@98");
            PreparedStatement st = con.prepareStatement("select * from University where username=?");
            st.setString(1, str);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String username = rs.getString(1);
                String name = rs.getString(6);
                String roll = rs.getString(8);
                String id = rs.getString(7);
                String sub_1 = rs.getString(3);
                String sub_2 = rs.getString(4);
                String sub_3 = rs.getString(5);
                tf1.setText(username);
                tf2.setText(name);
                t_stu_lec.setText(roll);
                tf3.setText(id);
                tf4.setText(sub_1);
                tf6.setText(sub_2);
                tf7.setText(sub_3);
            } else {
                JOptionPane.showMessageDialog(null, "Name not Found");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}  