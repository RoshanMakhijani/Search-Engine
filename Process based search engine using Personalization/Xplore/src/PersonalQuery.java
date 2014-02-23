

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Query
 */
public class PersonalQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalQuery() {
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
		String query=request.getParameter("txtQuery");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Xplore-'"+query+"'</title>");
		out.println("<link rel='shortcut icon' type='image/x-icon' href='favicon.ico'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table width='100%'><tr><td><font size=3'><a href='processhome.html' >Home</a></font>&nbsp;<font size='3'><a href='About.html' >About</a></font>&nbsp;");
		out.println("<font size='3'><a href='partners.html' >Partners</a></font>&nbsp;<font size='3'><a href='Create_process.html' >Add Process </a></font>&nbsp;<font size='3'><a href='modify.html' >Modify Process </a></font></td><td align='right'><font size='3'>Welcome "+PersonalGoogle.name+"&nbsp;&nbsp;<a href='Logout'>Sign Out</a>&nbsp;&nbsp;<a href='processhome.html' onClick='parent.location='mailto:roshanm89@gmail.com''>Contact Us</a></font></td>");
		out.println("</tr></table>");		
		/*out.println("<table align='left'><tr><td><object width='200' height='150' align='absmiddle'><param name='movie' value='fianlBin.swf' />");
		out.println("<param name='wmode' value='transparent'><embed src='finalBin.swf' width='200' height='150' align='absmiddle' wmode='transparent'></embed>");
		out.println("</object><br/></td></tr><tr><td><form action='/Personalization/Query' method='get' name='frmQuery' id='frmQuery' ><input name='txtQuery' type='text' size='50' style='font-size:13pt'/>&nbsp;&nbsp;&nbsp;&nbsp;<input name='butExp' type='submit' value='Explore' width='50' style='font-size:13px; width:inherit'/></form></td></tr></table><br/>");*/
		out.println("<object width='200' height='150' align='absmiddle'><param name='movie' value='fianlBin.swf' />");
		out.println("<param name='wmode' value='transparent'><embed src='finalBin.swf' width='200' height='150' align='absmiddle' wmode='transparent'></embed>");
		out.println("</object><br/><form action='/Xplore/RedirectPersonalize' method='get' name='frmQuery' id='frmQuery' ><input name='txtQuery' type='text' size='50' style='font-size:13pt'/>&nbsp;&nbsp;&nbsp;&nbsp;<input name='butExp' type='submit' value='Explore It' width='50' style='font-size:13px; width:inherit'/><br><input type='radio' name='type_of_search' value='personal_search' CHECKED>Personalized Search</input><input type='radio' name='type_of_search' value='proc'>Process-Based Search</input></form><br/>");
		PersonalGoogle mg=new PersonalGoogle();
		//System.out.println("Enter the Query");
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//String mquery=br.readLine();
		
			query=query.replaceAll(" ", "%20");
			System.out.println(query);
			//boolean authenticated=false;
			//if(!request.getParameter("butExp").equals("Explore"))
				//authenticated=true;
			mg.fire(query,out);
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
