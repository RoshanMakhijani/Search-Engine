

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Add_Steps
 */
public class Add_Steps extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_Steps() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		 
		PrintWriter out = response.getWriter();
		
		String p_id_count=request.getParameter("p_id");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title> ADD STEPS </title>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<form name=\"Step_form\" action=\"Process_Steps\" method=\"get\">");
		out.println("<table><tr><td><b>*Step Title: </b></td><td><input type=text name=s_title size=\"50\"></td></tr>");
		out.println("<tr><td>Recommended URL: </td><td><input type=text name=s_url value=\"http://\" ></td></tr>");
		out.println("<tr><td>Description</td><td><textarea name=s_desc rows=3></textarea></td></tr>");
		out.println("<input type=hidden name=p_id value="+p_id_count+">");
		out.println("<tr><td><input type=reset value=\"Remove Step\"></td></tr>");
		out.println("</table><hr/><input type=submit name=cs value=\"Create Step\">");
		//out.println("&nbsp;&nbsp;<input type=button value=\"Cancel\"></form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
