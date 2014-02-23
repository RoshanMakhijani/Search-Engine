


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
/**
 * Servlet implementation class Process_Steps
 */
public class Process_Steps extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Process_Steps() {
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
		

		String s_title=request.getParameter("s_title");
		String s_url=request.getParameter("s_url");
		String s_desc=request.getParameter("s_desc");
		
		int p_id_count=Integer.parseInt(request.getParameter("p_id"));
		
		int s_id_count=0;
		
		try
		{
			DriverManager.registerDriver(
                    new oracle.jdbc.OracleDriver());
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracl", "scott", "tiger");

		      
			Statement statement=conn.createStatement();
			ResultSet res=statement.executeQuery("SELECT COUNT(*) FROM Step_Table WHERE Process_ID="+p_id_count);
			   while (res.next()){
			          s_id_count = res.getInt(1);
			        }
			        s_id_count=s_id_count+1;
			   
			     out.println("   "+s_id_count);
			      
			   	       
			    int i=statement.executeUpdate("INSERT INTO Step_Table values ("+s_id_count+",'"+s_title+"','"+s_url+"','"+s_desc+"',"+p_id_count+")");
			    	//statement.executeUpdate("INSERT INTO Step_Table values("+s_id_count+",'"+s_title+"','"+s_url+"','"+s_desc+"',"+p_id_count+")");  
					  
					   
					 if(i!=0){
			        out.println("The process has been inserted with the Step titled:");
			        out.println(" "+s_title);
			      }
			      else{
			        out.println("Sorry! Failure");
			      }	
					 statement.close();
					 conn.commit();
					 conn.close();
					 
			}
			
			catch(Exception e)
			{
				e.printStackTrace(out);
			}
			out.println("<hr/><br>");
			out.println("<h1> Do you wish to add another step ? </h1><br>");
			out.println("<form name=\"Step_form\" method=\"get\" action=\"Add_Steps\">");
			out.println("<input type=hidden name=p_id value="+p_id_count+">");
			out.println("<input type=submit name=add_step value=\"Yes\">");
			out.println("<input type=button name=no_step value=\"No\" onClick=\"parent.location='Thankspage.html'\">" );
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
