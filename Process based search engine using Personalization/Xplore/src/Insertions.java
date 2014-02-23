

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Insertions
 */
public class Insertions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insertions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		 
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Process Creation </title>");
		out.println("</head>");
		out.println("<body>");
		
		String p_title="";
		String p_category="";
		String p_desc="";
		String n="";
		String v="";
		
		/*Tagger tg =  new Tagger(p_title);
		String n=tg.extNoun(tg.putout);
		String v=tg.extVerb(tg.putout);
		*/
		String titleval[]=request.getParameterValues("s_title");
		String urlval[]=request.getParameterValues("s_url");
		String descval[]=request.getParameterValues("s_desc");
		int l=Integer.parseInt(request.getParameter("s_id"));
		int p_id=Integer.parseInt(request.getParameter("p_id"));
		System.out.println(p_id+"  "+l);
		

		try
		{
			DriverManager.registerDriver(
	                new oracle.jdbc.OracleDriver());
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracl", "scott", "tiger");
			
			Statement statement=conn.createStatement();
			
			ResultSet res = statement.executeQuery("SELECT * FROM Process_Table WHERE ID="+p_id);
			while(res.next())
			{
				p_title=res.getString(2);
				p_category=res.getString(3);
				p_desc=res.getString(4);
			}
			
			res = statement.executeQuery("SELECT * FROM Annotation WHERE P_ID="+p_id);
			while(res.next())
			{
				n=res.getString(2);
				v=res.getString(3);
			}
		int i=0;
		int s_id=1;
			String query1="insert into TempProcess_Table values ("+p_id+",'"+p_title+"','"+p_category+"','"+p_desc+"')";
			statement.executeUpdate(query1);
			String query2="insert into TempAnnotation values ("+p_id+",'"+v+"','"+n+"')";
			statement.executeUpdate(query2);
			 
			for(int x=0;x<l;x++)
			{
			i=statement.executeUpdate("insert into TempStep_Table values ("+s_id+",'"+titleval[x]+"','"+urlval[x]+"','"+descval[x]+"',"+p_id+")");
			
			
			//("UPDATE Step_Table SET ID="+s_id+", Title='"+titleval[x]+"', url='"+urlval[x]+"', desc='"+descval[x]+"' WHERE Process_ID="+p_id);
			s_id++;
			}
			
			if(i!=0)
				out.println("Process has been Updated -- Thank you for valuable contribution");
			else
				out.println("Could not update");
	           conn.close();
	           conn=null;
	           statement=null;
	           res=null;
		}
		catch(Exception e)
		{
		    System.out.println(e);

		}
		out.println("<br></br>");
		out.println("<INPUT TYPE=button VALUE=\"DONE\" onClick="+"window.location.href='"+"processhome.html"+"'>");
	
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
