

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("txtName");
		String password=request.getParameter("password");
		PrintWriter out=response.getWriter();
		String name="";
		MyGoogle.auth=false;
		Connection connection =null;
		//System.out.print("in oracle");
		//String rdNew=request.getParameter("rdnewUser");
		String newName=request.getParameter("txtNewUser");
		if(newName.equals("New Name Here"))
		{
			try
		
		{
			DriverManager.registerDriver(
                    new oracle.jdbc.OracleDriver());

				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracl", "scott", "tiger");
				Statement statement = connection.createStatement();
				ResultSet rs= statement.executeQuery("select userID,name from Personalization where userName='"+username+"'");
				while(rs.next())
				{
					String pass = rs.getString(1);
					name=rs.getString(2);
					if(pass.equals(password))
					{
						MyGoogle.auth=true;
					}
				}
				if(MyGoogle.auth)
				{
					MyGoogle.userName=username;
					MyGoogle.userID=password;
					MyGoogle.name=name;
					returnHomePage(out,name);
				}
				else
				{
					returnAuthenticate(out);
				}
		}
		catch(SQLException sqlEx)
		{
			sqlEx.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			connection=null;
		}
		}
		else
		{
			try{
			DriverManager.registerDriver(
                    new oracle.jdbc.OracleDriver());

			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracl", "scott", "tiger");
			//Statement statement = connection.createStatement();
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter the no. of documents");
			Concount.tot_doc=Integer.parseInt(br.readLine());
			Concount CC=new Concount();
			CC.fuzzyCreator(CC);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream objOstream = new ObjectOutputStream(baos);
			objOstream.writeObject(CC);
			byte[] bArray = baos.toByteArray();
			PreparedStatement objStatement = connection.prepareStatement("insert into Personalization(userName,userID,name,Concepts) values (?,?,?,?)");
			objStatement.setString(1,username );
			objStatement.setString(2,password );
			objStatement.setString(3,newName );
			objStatement.setBytes(4, bArray);
			objStatement.execute();
			returnHomePage(out,newName);
			//statement.execute("insert into personalization values('"+username+"','"+password+"','"+newName+"','"+CC+"')");
			}
			catch(Exception e)
			{
				
			}
			finally
			{
				connection=null;
			}
			}

	}

	void returnHomePage(PrintWriter out,String name)
	{
		out.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' /><title>Xplore</title>");
		out.println("<link rel='shortcut icon' type='image/x-icon' href='favicon.ico'></head><body><table width='100%'>	<tr>");
		out.println("<td height='28'><table><tr><td><font size='3'><a href='processhome.html'>Home</a></font>&nbsp; </td>");
		out.println("<td><font size='3'><a href='About.html' >About</a></font>&nbsp;  </td><td><font size='3'><a href='partners.html' >Partners</a></font></td>");
		out.println("</tr><tr></table></td><td width='100%' align='right' valign='top'><table align='right'>");		 
		out.println("<tr><td width='100%'><font size='4'>Welcome "+name+"&nbsp;&nbsp;</font><a href='Logout'>Sign Out</a></td></tr></table></td></tr></table>");
		out.println("<table align='center'><tr><td><object width='446' height='269' align='absmiddle'><param name='movie' value='fianlBin.swf' />");
		out.println("<param name='wmode' value='transparent'><embed src='finalBin.swf' width='446' height='269' align='absmiddle' wmode='transparent'> </embed></object>");
		out.println("<br/></td></tr></table><table align='center'><form action='/Personalization/Query' method='get' name='frmQuery' id='frmQuery' ><tr><td ><center>");
		out.println("<input name='txtQuery' type='text' size='50' style='font-size:16pt'/></center></td></tr><tr><td>&nbsp;</td></tr><tr><td align='right'><input name='butExp' type='submit' value='Explore' width='50' align='right' style='font-size:16px; width:inherit'/></td>");
		out.println("</tr></form></table></center></body></html>");
	}
	void returnAuthenticate(PrintWriter out)
	{
		out.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'><title>Xplore-Signin</title><link rel='shortcut icon' type='image/x-icon' href='favicon.ico'>");
		out.println("</head><body><h2>UserName and Password don't match</h2></br><form action='Login' method='get'><table width='410'><tr><td height='62' colspan='3' valign='top'><font size='3'><b>UserName :</b></font><input type='text' size='50' name='txtName'>");
		out.println("</input></td></tr></table><table><tr><td height='62' colspan='3' valign='top'><font size='3'><b>UserID :</b></font>");
		out.println("<input type='password' size='50' name='password'></input></td><tr><td width='281' rowspan='2'><input type='submit' value='Signin' style='font-size:16px'></input></td><td width='104' height='32' valign='top' ><input name='rdnewUser' type='radio' value='New User'>");
		out.println("</input><font size='3'><b>New User</b></font></td><td width='1' rowspan='2' valign='top'></td></tr>");
		out.println("<tr><td height='26'>&nbsp;</td></tr></table></form></body></html>");

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
