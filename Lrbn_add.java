import javax.swing.*;	
import java.awt.event.*;
import java.awt.*;
import java.sql.*;


public class Lrbn_add extends JFrame implements ActionListener{
	JLabel jl[]=new JLabel[10];
	JTextField jt[]=new JTextField[10];
	JPasswordField jp;
	JButton jb;
	Font f;
	Font ff;
	public Lrbn_add(String s){
		super(s);
		setLayout(null);
		f= new Font("vardana",Font.BOLD,20);
		ff= new Font("vardana",Font.BOLD,18);
		jl[0]=new JLabel("Name :");
		jl[0].setFont(f);
		jl[1]=new JLabel("Email :");
		jl[1].setFont(f);
		jl[2]=new JLabel("Password :");
		jl[2].setFont(f);
		jl[3]=new JLabel("Contact:");
		jl[3].setFont(f);
		jl[4]=new JLabel("Address :");
		jl[4].setFont(f);
		
		jt[0]=new JTextField(20);
		jt[0].setFont(ff);
		jt[1]=new JTextField(20);
		jt[1].setFont(ff);
		jt[3]=new JTextField(20);
		jt[3].setFont(ff);
		jt[4]=new JTextField(20);
		jt[4].setFont(ff);
		jp=new JPasswordField(20);
		jp.setFont(ff);
		
		jb=new JButton("Submit");
		jb.addActionListener(this);
		jb.setFont(f);
		
		jl[0].setBounds(100,200,110,60);jt[0].setBounds(230,210,240,40);
		jl[1].setBounds(100,270,110,60);jt[1].setBounds(230,280,240,40);
		jl[2].setBounds(100,340,110,60);jp.setBounds(230,350,240,40); 
		jl[3].setBounds(100,410,110,60);jt[3].setBounds(230,420,240,40);
		jl[4].setBounds(100,470,110,60);jt[4].setBounds(230,480,240,40);
		jb.setBounds(230,550,110,60);
		
		add(jl[0]);add(jl[1]);add(jl[2]);add(jl[3]);add(jl[4]);
		add(jt[0]);add(jt[1]);add(jt[3]);add(jt[4]);add(jp);
		add(jb);
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		String name=jt[0].getText();
		String email=jt[1].getText();
		String pass=jp.getText();
		String num=jt[3].getText();
		String address=jt[4].getText();
		if(e.getSource()==jb){
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
				Statement st=cn.createStatement();
				if(st.executeUpdate("insert into librarian values('"+name+"','"+email+"','"+pass+"','"+num+"','"+address+"')")>0){
					JOptionPane.showMessageDialog(null,"Record Inserted");
					Lrbn_add ad1=new Lrbn_add("Add Librarian");
					ad1.setSize(1000,1000);
					ad1.setVisible(true);
					this.setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(null,"Something went wrong");
				}
				
			}
			catch(Exception et){
				System.out.println(et.getMessage());
			}
		}
		
	}
}