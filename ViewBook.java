import javax.swing.*;	
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class ViewBook extends JFrame implements ActionListener{
	JLabel header,body;
	
	DefaultTableModel dtm=null;
	JTable jt=null;
	JScrollPane js=null;
	JButton jb1;
	Font f;
	Font ff;
	public ViewBook(String s){
		super(s);
		setLayout(null);
		f= new Font("arial",Font.BOLD,16);
		dtm= new DefaultTableModel(0,0);
		jt=new JTable();
		String head[]={"Sn","Book ID","Name","Author","Publisher","quantity","Issuied","Added Date"};
		dtm.setColumnIdentifiers(head);
		jt.setModel(dtm);
		js=new JScrollPane(jt);
		
		
		jb1=new JButton(new ImageIcon("images/back.png"));
		jb1.addActionListener(this);
		
		add(js);
		js.setFont(f);
		jt.setFont(f);
		
		header=new JLabel(new ImageIcon("images/vwbook.jpg"));
		body=new JLabel(new ImageIcon("images/Lrbn2.jpg"));
		header.setBounds(0,0,1000,260);
		body.setBounds(0,260,1000,740);
		js.setBounds(40,260,920,200);
		jb1.setBounds(860,460,120,40);
		add(jb1);
		add(header);
		add(body);
		try{
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
				Statement st=cn.createStatement();
				ResultSet rs2=st.executeQuery("select * from book");
				while(rs2.next()){
					dtm.addRow(new Object[]{rs2.getString("sn"),rs2.getString("book_id"),rs2.getString("name"),rs2.getString("author"),rs2.getString("publisher"),rs2.getString("quantity"),rs2.getInt("issued"),rs2.getString("date")});
					
				}
				
			}
			catch(Exception et){
				System.out.println(et.getMessage());
			}
		
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb1){ 
			LrbnPanel s1=new LrbnPanel("Librarian Panel");
						s1.setSize(1000,1000);
						s1.setVisible(true);
						this.setVisible(false);
		}
	}
}