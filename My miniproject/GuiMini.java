import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.sql.*;
import com.mysql.jdbc.Driver;
import java.util.*;
import java.lang.*;
//control class:
public class GuiMini extends Frame implements ActionListener
{
	TextField p0,p1,p2,p3,p4;
	TextField m0,m1,m2,m3,m4,m5;
	TextArea disp1, disp2;
	Button b1, b2, b3, b4, b5, b6, b7, b8;
 
	GuiMini()
	{
		setLayout(new FlowLayout());
        this.setLayout(null);
		Label text = new Label("Display: ", Label.LEFT);
		
		/*-------------------------Player side ------------------------------*/
		Label pID = new Label("Player ID: ",Label.LEFT);
		Label pname = new Label("Player name: ",Label.LEFT);
		Label pAge = new Label("Player age: ",Label.LEFT);
		Label pSScore = new Label("Season score: ",Label.LEFT);
		Label pMatchId = new Label("Match: ",Label.LEFT);
		
		disp1 = new TextArea("");
		//disp1.setRows(20);
		b1 = new Button("Insert player record(s)");
		b2 = new Button("Delete player record(s) by ID");	
		b3 = new Button("Display player record(s)");
		b4 = new Button("Clear above display");
		
		p0 = new TextField(80);
		p1 = new TextField(80);
		p2 = new TextField(80);
		p3 = new TextField(80);
		p4 = new TextField(80);
		
		this.add(pID);
		this.add(pname);
		this.add(pAge);
		this.add(pSScore);
		this.add(pMatchId);
		
		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		
		this.add(p0);
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
		
		this.add(text);
		this.add(disp1);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);	
		b4.addActionListener(this);	
		
		
		pID.setBounds(70,50,100,25);
		pID.setFont(new Font("Arial", Font.BOLD, 12));
		p0.setBounds(200,50,250,25);
		
		pname.setBounds(70,100,100,25);
		pname.setFont(new Font("Arial", Font.BOLD, 12));
		p1.setBounds(200,100,250,25);
		
		pAge.setBounds(70,150,100,25);
		pAge.setFont(new Font("Arial", Font.BOLD, 12));
		p2.setBounds(200,150,250,25);
		
		pSScore.setBounds(70,200,120,25);
		pSScore.setFont(new Font("Arial", Font.BOLD, 12));			
		p3.setBounds(200,200,250,25);
		
		pMatchId.setBounds(70,250,120,25);
		pMatchId.setFont(new Font("Arial", Font.BOLD, 12));			
		p4.setBounds(200,250,250,25);
		
		b1.setBounds(100,300,150,40);	
		b2.setBounds(300,300,170,40);
		b3.setBounds(200,350,150,40);
		
		text.setBounds(70,400,90,25);
		text.setFont(new Font("Arial", Font.BOLD, 12));
		disp1.setBounds(80,450,420,150);
		b4.setBounds(200,630,150,40);

		
	}	
	public void actionPerformed(ActionEvent e)
	{ 	/*-----------------------------------------Player side ---------------------------*/
		if(e.getActionCommand().equals("Display player record(s)"))
		{
			try
			{					
				Class.forName("com.mysql.jdbc.Driver");

				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sports_team?useSSL=false","root","aditya");
				//here sonoo (replaced by sports_team) is the database name, root is the username and root (replaced by aditya) is the password 
				
				PreparedStatement stmt=con.prepareStatement("select * from player"); 
			
				ResultSet rs = null;
				rs = stmt.executeQuery(); 
				ArrayList<String> result = new ArrayList<String>();
				
				int r = 0; //Assumption: Records are not present in DB
				while(rs.next())
				{
					r = 1;
					String rt;
					rt="Player ID: "+rs.getInt(1)+"\nPlayer name: "+rs.getString(2)+"\nPlayer age: "+rs.getInt(3)+"\nPlayer season score: "+rs.getInt(4)+"\nMatch ID: "+rs.getInt(5);
					result.add(rt);
					//Converting ArrayList to String and using seperation by a "comma"; We can then disp1 Text in TextArea
					String listString = String.join(" \n\n", result);
					disp1.setText(listString);
				}
				if(r==0)
				{
					disp1.setText("No player records present in database");
				}
				
				con.close();
			}
			catch(Exception ex){ System.out.println(ex);}
		}
		else if(e.getActionCommand().equals("Insert player record(s)"))
		{
			try
			{					
				Class.forName("com.mysql.jdbc.Driver");

				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sports_team?useSSL=false","root","aditya");
				PreparedStatement stmtinsert=con.prepareStatement("insert into player values(?,?,?,?,?)");
				PreparedStatement stmtcheck=con.prepareStatement("select count(*) from player where playerid = ?");
				stmtcheck.setInt(1,Integer.parseInt(p0.getText()));

				ResultSet rset = stmtcheck.executeQuery();
				if(rset.next())
				{
					int count = rset.getInt(1);
					disp1.setText(" "+count);
					if(count == 0)
					{
						stmtinsert.setInt(1,Integer.parseInt(p0.getText()));//1 specifies the first parameter in the query
						stmtinsert.setString(2,p1.getText());
						stmtinsert.setString(3,String.valueOf(p2.getText()));
						stmtinsert.setString(4,String.valueOf(p3.getText()));
						stmtinsert.setString(5,String.valueOf(p4.getText()));
					}
					else
					{
						disp1.setText("Player with ID: "+Integer.parseInt(p0.getText())+" is already present in the database!");
					}
				}
				

				int i=stmtinsert.executeUpdate();  
				disp1.setText(i+" record(s) inserted");  
				
				con.close();
			}
			catch(Exception ex){ System.out.println(ex);}
		}
		else if(e.getActionCommand().equals("Delete player record(s) by ID"))
		{
			try
			{					
				Class.forName("com.mysql.jdbc.Driver");

				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sports_team?useSSL=false","root","aditya");
				PreparedStatement stmtdelete=con.prepareStatement("delete from player where playerid = ?");
				stmtdelete.setInt(1,Integer.parseInt(p0.getText()));
				
				int i=stmtdelete.executeUpdate();  
				disp1.setText(i+" record(s) removed");  
				
				con.close();
			}
			catch(Exception ex){ System.out.println(ex);}
		}
		else
		{
			disp1.setText("");
		} 
		
	}
		
	public static void main(String args[])
    {
        	GuiMini gm = new GuiMini();
        	gm.setVisible(true);
        	gm.setSize(450,450);
        	gm.setTitle("Player and Match Details");
    }
}
	