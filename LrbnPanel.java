import javax.swing.*;	
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class LrbnPanel extends JFrame implements ActionListener{
	JLabel head1,body;
	JButton ab,vb,db,lb,ib,vib,sb,d1,cross,lg;
	JLabel l1,jl2;
	JTextField t1;
	
	int vib_flag=0;
	DefaultTableModel dtm=null;
	JTable jt=null;
	JScrollPane js=null;
	Font f;
	
	public LrbnPanel(String s){
		super(s);
		f= new Font("vardana",Font.BOLD,18);
		setLayout(null);
		//table for view issuied books
		dtm= new DefaultTableModel(0,0);
		jt=new JTable();
		String head[]={"Student ID","Book ID","Student Name","Contact","Issue Date"};
		dtm.setColumnIdentifiers(head);
		jt.setModel(dtm);
		js=new JScrollPane(jt);
		
		js.setFont(f);
		jt.setFont(f);
		
		head1=new JLabel(new ImageIcon("images/Lrbn1.jpg"));
		body=new JLabel(new ImageIcon("images/Lrbn2.jpg"));
		l1= new JLabel("Enter Book Id :");
		d1=new JButton("Delete");
		t1=new JTextField(20);
		ab=new JButton(new ImageIcon("images/add_book.jpg"));
		vb=new JButton(new ImageIcon("images/view_book.png"));
		db=new JButton(new ImageIcon("images/dl_book.png"));
		lb=new JButton(new ImageIcon("images/ed_book.png"));
		ib=new JButton(new ImageIcon("images/is_book.png"));
		vib=new JButton(new ImageIcon("images/vi_book.png"));
		sb=new JButton(new ImageIcon("images/sb_book.png"));
		cross=new JButton(new ImageIcon("images/cross.png"));
		jl2=new JLabel(new ImageIcon("images/dl.png"));
		lg=new JButton("Logout");
		lg.addActionListener(this);
		ab.addActionListener(this);
		vb.addActionListener(this);
		db.addActionListener(this);
		lb.addActionListener(this);
		ib.addActionListener(this);
		vib.addActionListener(this);
		sb.addActionListener(this);
		d1.addActionListener(this);
		cross.addActionListener(this);
		
		l1.setFont(f);
		t1.setFont(f);
		d1.setFont(f);
		
		
		
		head1.setBounds(0,0,1000,200);
		
		body.setBounds(0,200,1000,800);
		l1.setBounds(490,270,200,50);
		t1.setBounds(490,310,200,40);
		d1.setBounds(490,350,120,30);
		jl2.setBounds(490,250,200,150);
		
		lg.setBounds(900,0,100,30);
		ab.setBounds(50,200,200,50);
		vb.setBounds(270,200,200,50);
		db.setBounds(490,200,200,50);
		lb.setBounds(710,200,200,50);
		ib.setBounds(50,300,200,50);
		vib.setBounds(270,300,200,50);
		sb.setBounds(710,300,200,50);
		
		cross.setBounds(920,350,40,40);
		add(cross);
		cross.setVisible(false);
		js.setBounds(40,350,920,200);
		add(js);
		js.setVisible(false);
		
		add(lg);
		add(ab);
		add(vb);
		add(db);
		add(lb);
		add(ib);
		add(vib);
		add(sb);
		add(l1);
		add(t1);
		add(d1);
		add(jl2);
		add(head1);
		add(body);
		l1.setVisible(false);
		t1.setVisible(false);
		d1.setVisible(false);
		jl2.setVisible(false);
		
	}	
	public void actionPerformed(ActionEvent et){
		if(et.getSource()==ab){ 
			AddBook ad=new AddBook("Add Books");
			ad.setSize(1000,1000);
			ad.setVisible(true);
			this.setVisible(false);
		}
		if(et.getSource()==vb){
			ViewBook vw=new ViewBook("View Books");
			vw.setSize(1000,1000);
			vw.setVisible(true);
			this.setVisible(false);
		}
		if(et.getSource()==db){
			jl2.setVisible(true);
			l1.setVisible(true);
			t1.setVisible(true);
			d1.setVisible(true);
		}
		if(et.getSource()==ib){
			IssBook iss=new IssBook("Issue Book");
			iss.setSize(1000,1000);
			iss.setVisible(true);
			this.setVisible(false);
		}
		
		if(et.getSource()==lb){
			JOptionPane.showMessageDialog(null,"Edit books will Update soon...");
		}
			if(et.getSource()==d1){
				String bname=t1.getText();
				int issued=0;
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
					Statement st=cn.createStatement();
					ResultSet rs=st.executeQuery("select * from book where book_id='"+bname+"'");
					if(rs.next()){
						issued=rs.getInt("issued");
						JOptionPane.showMessageDialog(null,issued+" Books are issued from this set please submit and try again");
					}
					else{
						if(st.executeUpdate("delete from book where book_id='"+bname+"'")>0){
							JOptionPane.showMessageDialog(null,"Books Removed");
							l1.setVisible(false);
							t1.setVisible(false);
							d1.setVisible(false);
							jl2.setVisible(false);
						}
						else{
							JOptionPane.showMessageDialog(null,"Something went wrong");
						}
					}
				}
				catch(Exception ett){
					System.out.println(ett.getMessage());
				}
			}
			
			
		if(et.getSource()==vib && vib_flag==0){
			cross.setVisible(true);
			js.setVisible(true);
			vib_flag=1;
			int sts=0;
			try{
			
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
				Statement st=cn.createStatement();
				ResultSet rs2=st.executeQuery("select * from issued_book where is_status="+sts);
				while(rs2.next()){
					dtm.addRow(new Object[]{rs2.getString("s_id"),rs2.getString("b_id"),rs2.getString("sname"),rs2.getString("contact"),rs2.getString("date")});
					
				}
				
			}
			catch(Exception et1){
				System.out.println(et1.getMessage());
			}
		}
		if(et.getSource()==cross && vib_flag==1){
			cross.setVisible(false);
			js.setVisible(false);
			vib_flag=0;
			dtm.setRowCount(0);
		}
		if(et.getSource()==sb){
			SubmitBook iss=new SubmitBook("Submit Book");
			iss.setSize(1000,1000);
			iss.setVisible(true);
			this.setVisible(false);
		}
		if(et.getSource()==lg){
			Start iss=new Start("Library Management System");
			iss.setSize(1000,1000);
			iss.setVisible(true);
			this.setVisible(false);
		}
		
	}
	public static void main(String []args){
		LrbnPanel s=new LrbnPanel("Librarian Panel");
			s.setSize(1000,1000);
			s.setVisible(true);
	}
	
}