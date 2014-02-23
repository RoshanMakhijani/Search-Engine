

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
 * Servlet implementation class Update2
 */
public class Update2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update2() {
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
		
		int id=0;
		int i=0;
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Modify A Process </title>");
		
		/*out.println("<script type=\"text/javascript\" language=\"javascript\">");
		out.println("function enable()");
		out.println("{");
		out.println("if(document.Step_form.s_title.disabled == true)");
		out.println("{document.Step_form.s_title.disabled=false;");
		out.println("document.Step_form.s_title.focus();}");
		out.println("}");
		out.println("</script>");
		*/
		out.println("</head>");
		out.println("<body>");
		String p_title=request.getParameter("p_title");
		try
		{
			DriverManager.registerDriver(
	                new oracle.jdbc.OracleDriver());
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracl", "scott", "tiger");
			Statement statement=conn.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM Process_Table where Title="+"'"+p_title+"'");
	
			if(!res.next())
			{

			out.println("No such Process exists<br/><br/>");
			out.println("Do you wish to add the Process<br/><br/>");
			out.println("<input type=button value=Add Process onClick="+"window.location.href="+"'"+"Create_process.html"+"'>");
			out.println("<INPUT TYPE=button VALUE=\"NO, THANK YOU\" onClick="+"window.location.href='"+"process_home.html"+"'>");
			out.println("</body>");
			out.println("</html>");
			
			}
			else
			{
				id=res.getInt(1);
				//out.println(id);
				out.println("<table>");
				out.println("<tr><td>Title :</td>");
				out.println("<td>"+res.getString(2)+"</td>");
				out.println("<tr><td>Category :</td>");
				out.println("<td>"+res.getString(3)+"</td>");
				out.println("<tr><td>Description :</td>");
				out.println("<td>"+res.getString(4)+"</td>");
				out.println("</table>");
				out.println("<hr/>");
				out.println("<br/>STEPS<br/>");
			
			res = statement.executeQuery("SELECT * FROM Step_Table where Process_ID="+id);
			out.println("<form name=\"Step_form\" method=\"get\" action=\"Insertions\">");
			out.println("Step  :");
			while(res.next())
			{
				i=res.getInt(1);
				String t=res.getString(2);
				String u=res.getString(3);
				String d=res.getString(4);
				out.println("<br/>");
						
		
				
				//out.print("<input type=text name=\"s_id[]\" value="+i+" size=75>");
				out.println("<table>");
				out.println(i);
				out.println("<tr><td>Title :</td>");
				out.println("<td><input type=text name=s_title value='"+t+"' ></td>");
				out.println("<tr><td>URL :</td>");
				out.println("<td><input type=text name=s_url value='"+u+"'></td>");
				out.println("<tr><td>Description :</td>");
				out.println("<td><input type=text size=75 name=s_desc value='"+d+"'></td>");
				
				out.println("</table>");
				
				out.println("<br/>");

				/*out.println("<script type=\"text/javascript\" language=\"javascript\">");
				out.println("function update()");
				out.println("{");
				out.println("if(document.Step_form.s_title.disabled == true)");
				out.println("{document.Step_form.s_title.disabled=false;");
				out.println("document.Step_form.s_title.focus();}");
				out.println("}");
				out.println("</script>");
			*/
			
			}
			out.println("<hr></hr>");
			out.println("<tr><td><input type=submit name=submit value=Update></td></tr>");
			
		}
			conn.close();
			conn=null;
			res=null;
			statement=null;
		}
		catch(Exception e)
		{
			  out.println("Sorry! Failure");
		}
		
	

		out.println("<input type=hidden name=p_id value="+id+">");
		out.println("<input type=hidden name=s_id value="+i+">");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		out.close();
		

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
