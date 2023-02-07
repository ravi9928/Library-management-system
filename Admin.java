import javax.swing.*;	
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Admin extends JFrame implements ActionListener{
	JButton b1;
	JLabel l1,l2,back;
	JTextField t1;
	JPasswordField t2;
	Font f;
	public Admin(String s){
		super(s);
		setLayout(null);
		f= new Font("arial",Font.BOLD,18);
		
		l1=new JLabel("Email    :");
		l2=new JLabel("Password :");
		l1.setFont(f);
		l2.setFont(f);
		
		t1=new JTextField(25);
		t2=new JPasswordField(25);
		t1.setFont(f);
		t2.setFont(f);
		
		b1=new JButton(new ImageIcon("images/login.png"));
		b1.addActionListener(this);
		b1.setFont(f);
		
		back=new JLabel(new ImageIcon("images/bg1.jpg"));
		back.setBounds(0,0,1000,1000);
		l1.setBounds(100,200,100,50);t1.setBounds(300,200,250,40);
		l2.setBounds(100,260,100,50);t2.setBounds(300,260,250,40);
		
		b1.setBounds(300,320,100,40);
		
		add(l1);
		add(l2);
		add(t1);
		add(t2);
		add(b1);
		add(back);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==b1){
			String email=t1.getText();
			String pass=t1.getText();
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
				Statement st=cn.createStatement();
				ResultSet rs=st.executeQuery("select * from admin where email='"+email+"'");
				if(rs.next()){
					if(rs.getString(1).equals(pass)){
						Admin_Panel s1=new Admin_Panel("Admin Panel");
						s1.setSize(1000,1000);
						s1.setVisible(true);
						this.setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(null,"Wrong password");
					}
				}
				else{
						JOptionPane.showMessageDialog(null,"Check Email");
				}
			}
			catch(Exception et){
				System.out.println(et.getMessage());
			}
		}
	}
	public static void main(String []args){
		Admin s=new Admin("Admin Login");
			s.setSize(1000,1000);
			s.setVisible(true);
	}
	
}