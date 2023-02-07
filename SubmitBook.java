import javax.swing.*;	
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class SubmitBook extends JFrame implements ActionListener{
	JLabel jl[]=new JLabel[10],head,body;
	JTextField jt[]=new JTextField[10];
	JPasswordField jp;
	JButton jb,jb1;
	Font f;
	Font ff;
	public SubmitBook(String s){
		super(s);
		setLayout(null);
		f= new Font("vardana",Font.BOLD,20);
		ff= new Font("vardana",Font.BOLD,18);
		head=new JLabel(new ImageIcon("images/submit_head.png"));
		body=new JLabel(new ImageIcon("images/book_head.jpg"));
		jl[0]=new JLabel("Student Id :");
		jl[0].setFont(f);
		jl[1]=new JLabel("Book Id :");
		jl[1].setFont(f);
		jl[2]=new JLabel("Date:");
		jl[2].setFont(f);
		
		jt[0]=new JTextField(20);
		jt[0].setFont(ff);
		jt[1]=new JTextField(20);
		jt[1].setFont(ff);
		jt[2]=new JTextField("01/01/2021");
		jt[2].setFont(ff);
	
		
		
		jb=new JButton(new ImageIcon("images/sb_book.png"));
		jb.addActionListener(this);
		jb1=new JButton(new ImageIcon("images/back.png"));
		jb1.addActionListener(this);
		
		head.setBounds(0,0,1000,200);
		body.setBounds(0,200,1000,800);
		jl[0].setBounds(100,220,110,60);jt[0].setBounds(250,230,240,40);
		jl[1].setBounds(100,290,110,60);jt[1].setBounds(250,300,240,40);
		jl[2].setBounds(100,360,110,60);jt[2].setBounds(250,370,240,40); 

		jb.setBounds(250,440,200,50);
		jb1.setBounds(800,200,120,40);
		
		add(jl[0]);add(jl[1]);
		add(jt[0]);add(jt[1]);
		add(jb);
		add(jb1);
		add(head);
		add(body);
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		String s_id=jt[0].getText();
		String b_id=jt[1].getText();
		String sname=jt[2].getText();
		int issued=0;
		int qnty=0;
		int sts=0;
		if(e.getSource()==jb){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
				Statement st=cn.createStatement();
					if(st.executeUpdate("update issued_book set is_status="+1+" where b_id='"+b_id+"' AND is_status="+sts+" AND s_id='"+s_id+"'")>0){
						ResultSet rs=st.executeQuery("select * from book where book_id='"+b_id+"'");
						if(rs.next()){
							issued=rs.getInt("issued");
						}
						issued-=1;	
						if(st.executeUpdate("update book set issued="+issued+" where book_id='"+b_id+"'")>0){
							JOptionPane.showMessageDialog(null,"Book Submited");
								SubmitBook ad1=new SubmitBook("Submit Book");
								ad1.setSize(1000,1000);
								ad1.setVisible(true);
								this.setVisible(false);
						}
						else{
							JOptionPane.showMessageDialog(null,"Something went wrong");
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"This book not issued or wrong book id/student id");
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