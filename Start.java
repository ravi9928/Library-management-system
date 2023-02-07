import javax.swing.*;	
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Start extends JFrame implements ActionListener{
	JButton b1,b2;
	Font f;
	JLabel head,body;
	public Start(String s){
		super(s);
		setLayout(null);
	 	f= new Font("arial",Font.BOLD,18);
		head=new JLabel(new ImageIcon("images/startHead.jpg"));
		body=new JLabel(new ImageIcon("images/startBody.png"));
		b1=new JButton(new ImageIcon("images/lrbn.png"));
		b2=new JButton(new ImageIcon("images/admin.png"));
		b1.addActionListener(this);
		b2.addActionListener(this);
		b1.setFont(f);
		b2.setFont(f);
		head.setBounds(0,0,1000,300);
		body.setBounds(0,300,1000,700);
		b1.setBounds(100,310,300,70);
		b2.setBounds(600,310,300,70);
		add(b1);
		add(b2);
		add(head);
		add(body);
		
	}
	public void actionPerformed(ActionEvent e){
		try{
			if(e.getSource()==b1){
				LrbnLogin l=new LrbnLogin("Librarian Login");
				l.setSize(1000,1000);
				l.setVisible(true);
				this.setVisible(false);
			}
			else if(e.getSource()==b2){
				Admin s=new Admin("Admin Login");
				s.setSize(1000,1000);
				s.setVisible(true);
				this.setVisible(false);
			}
		}
		catch(Exception et){
			System.out.println(et.getMessage());
		}
		
	}
	public static void main(String []args){
		Start sf=new Start("Library Management System");
		sf.setSize(1000,1000);
		sf.setVisible(true);
	}
	
}