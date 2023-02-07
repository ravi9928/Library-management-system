import javax.swing.*;	
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class AddBook extends JFrame implements ActionListener{
	JLabel jl[]=new JLabel[10],head,body;
	JTextField jt[]=new JTextField[10];
	JPasswordField jp;
	JButton jb,jb1;
	Font f;
	Font ff;
	public AddBook(String s){
		super(s);
		setLayout(null);
		f= new Font("vardana",Font.BOLD,20);
		ff= new Font("vardana",Font.BOLD,18);
		head=new JLabel(new ImageIcon("images/book_head1.jpg"));
		body=new JLabel(new ImageIcon("images/book_head.jpg"));
		jl[0]=new JLabel("Book id :");
		jl[0].setFont(f);
		jl[1]=new JLabel("Name :");
		jl[1].setFont(f);
		jl[2]=new JLabel("Author:");
		jl[2].setFont(f);
		jl[3]=new JLabel("Publisher:");
		jl[3].setFont(f);
		jl[4]=new JLabel("Quantity :");
		jl[4].setFont(f);
		jl[5]=new JLabel("Date:");
		jl[5].setFont(f);
		
		jt[0]=new JTextField(20);
		jt[0].setFont(ff);
		jt[1]=new JTextField(20);
		jt[1].setFont(ff);
		jt[2]=new JTextField(20);
		jt[2].setFont(ff);
		jt[3]=new JTextField(20);
		jt[3].setFont(ff);
		jt[4]=new JTextField(20);
		jt[4].setFont(ff);
		jt[5]=new JTextField("01/01/2021");
		jt[5].setFont(ff);
		
		
		jb=new JButton(new ImageIcon("images/add_book.jpg"));
		jb.addActionListener(this);
		jb1=new JButton(new ImageIcon("images/back.png"));
		jb1.addActionListener(this);
		
		head.setBounds(0,0,1000,200);
		body.setBounds(0,200,1000,800);
		jl[0].setBounds(100,200,110,60);jt[0].setBounds(250,210,240,40);
		jl[1].setBounds(100,270,110,60);jt[1].setBounds(250,280,240,40);
		jl[2].setBounds(100,340,110,60);jt[2].setBounds(250,350,240,40); 
		jl[3].setBounds(100,410,110,60);jt[3].setBounds(250,420,240,40);
		jl[4].setBounds(100,470,110,60);jt[4].setBounds(250,480,240,40);
		jl[5].setBounds(100,530,110,60);jt[5].setBounds(250,540,240,40);

		jb.setBounds(250,600,200,50);
		jb1.setBounds(800,200,120,40);
		
		add(jl[0]);add(jl[1]);add(jl[2]);add(jl[3]);add(jl[4]);add(jl[5]);
		add(jt[0]);add(jt[1]);add(jt[2]);add(jt[3]);add(jt[4]);add(jt[5]);
		add(jb);
		add(jb1);
		add(head);
		add(body);
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		String id=jt[0].getText();
		String name=jt[1].getText();
		String author=jt[2].getText();
		String publisher=jt[3].getText();
		String qnty=jt[4].getText();
		String date=jt[5].getText();
		int sn=0;
		if(e.getSource()==jb){
			try{
				System.out.println("Success");
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
				Statement st=cn.createStatement();
				ResultSet rs=st.executeQuery("select MAX(sn) from book");
				if(rs.next()){
					sn=rs.getInt(1);
				}
				sn++;
				if(st.executeUpdate("insert into book values("+sn+",'"+id+"','"+name+"','"+author+"','"+publisher+"','"+qnty+"',"+0+",'"+date+"')")>0){
					JOptionPane.showMessageDialog(null,"Book Added");
					AddBook ad1=new AddBook("Add Books");
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
		if(e.getSource()==jb1){ 
			LrbnPanel s1=new LrbnPanel("Librarian Panel");
						s1.setSize(1000,1000);
						s1.setVisible(true);
						this.setVisible(false);
		}
		
	}
}