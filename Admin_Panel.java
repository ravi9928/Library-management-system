import javax.swing.*;	
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Admin_Panel extends JFrame implements ActionListener{
	
	JButton al,vl,dl,lg;
	Font f;
	public Admin_Panel(String s){
		super(s);
		f= new Font("vardana",Font.BOLD,18);
		setLayout(null);
		al=new JButton("Add Librarian");
		vl=new JButton("View Librarian");
		dl=new JButton("Delete Librarian");
		lg=new JButton("Logout");
		
		al.addActionListener(this);
		vl.addActionListener(this);
		dl.addActionListener(this);
		lg.addActionListener(this);
		al.setFont(f);
		vl.setFont(f);
		dl.setFont(f);
		lg.setFont(f);
		
		al.setBounds(50,150,200,50);
		vl.setBounds(270,150,200,50);
		dl.setBounds(490,150,200,50);
		lg.setBounds(710,150,200,50);
		
		add(al);
		add(vl);
		add(dl);
		add(lg);
	}	
	public void actionPerformed(ActionEvent et){
		if(et.getSource()==al){
			Lrbn_add ad=new Lrbn_add("Add Librarian");
			ad.setSize(1000,1000);
			ad.setVisible(true);
			this.setVisible(false);
		}
		if(et.getSource()==vl){
			Lrbn_view vw=new Lrbn_view("View Librarian");
			vw.setSize(1000,1000);
			vw.setVisible(true);
			this.setVisible(false);
			
		}
		if(et.getSource()==dl){
			
		}
		if(et.getSource()==lg){
			Start iss=new Start("Library Management System");
			iss.setSize(1000,1000);
			iss.setVisible(true);
			this.setVisible(false);
		}
		
	}
}