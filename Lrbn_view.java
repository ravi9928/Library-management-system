import javax.swing.*;	
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class Lrbn_view extends JFrame implements ActionListener{
	JLabel header,body;
	
	DefaultTableModel dtm=null;
	JTable jt=null;
	JScrollPane js=null;
	Font f;
	Font ff;
	public Lrbn_view(String s){
		super(s);
		setLayout(null);
		f= new Font("arial",Font.BOLD,15);
		dtm= new DefaultTableModel(0,0);
		jt=new JTable();
		String head[]={"Name","Email","Pasword","Contact","Address"};
		dtm.setColumnIdentifiers(head);
		jt.setModel(dtm);
		js=new JScrollPane(jt);
		
		add(js);
		js.setFont(f);
		jt.setFont(f);
		
		header=new JLabel(new ImageIcon("images/11.jpg"));
		body=new JLabel(new ImageIcon("images/search_body.jpg"));
		header.setBounds(0,0,1000,260);
		body.setBounds(0,260,1000,740);
		js.setBounds(100,260,700,200);
		add(header);
		add(body);
		try{
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
				Statement st=cn.createStatement();
				ResultSet rs2=st.executeQuery("select * from librarian");
				while(rs2.next()){
					dtm.addRow(new Object[]{rs2.getString("name"),rs2.getString("email"),rs2.getString("password"),rs2.getString("contact"),rs2.getString("address")});
					
				}
				
			}
			catch(Exception et){
				System.out.println(et.getMessage());
			}
		
		
		
	}
	
	public void actionPerformed(ActionEvent e){
	}
}