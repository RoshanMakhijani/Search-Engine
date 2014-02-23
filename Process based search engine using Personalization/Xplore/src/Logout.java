

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		PersonalGoogle.auth=false;
		PersonalGoogle.userID="";
		PersonalGoogle.userName="";
		PersonalGoogle.name="";
		//System.out.print("Logging out");
		returnHomePage(out);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	void returnHomePage(PrintWriter out)
	{
		out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'>");
		out.println("<head><meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' /><title>Xplore</title><link rel='shortcut icon' type='image/x-icon' href='favicon.ico'></link>");
		out.println("</head><style type='text/css'>body {font:76% normal verdana,arial,tahoma;}#general{position: fixed;}h1, h2 {font-size:1em;}");
		out.println("</style><body><table width='100%'><tr><td><font size=3'><a href='processhome.html' >Home</a></font>&nbsp;<font size='3'><a href='About.html' >About</a></font>&nbsp;");
		out.println("<font size='3'><a href='partners.html' >Partners</a></font>&nbsp;<font size='3'><a href='Create_process.html' >Add&nbsp;Process </a></font>&nbsp;<font size='3'><a href='modify.html' >Modify&nbsp;Process </a></font></td><td align='right'><font size='3'><a href='Authenticate.html'>Sign In</a>&nbsp;&nbsp;<a href='processhome.html' onClick='parent.location='mailto:roshanm89@gmail.com''>Contact Us</a></font></td>");
		out.println("</tr></table><table align='center'><tr><td><object width='446' height='269' align='absmiddle'><param name='movie' value='finallBin.swf' /><param name='wmode' value='transparent'></param>");
		out.println("<embed src='finalBin.swf' width='446' height='269' align='absmiddle' wmode='transparent'> </embed></object></td></tr></table><form action='RedirectPersonalize' method='get' name='frmQuery' id='frmQuery' >");
		out.println("<table align='center'><tr><td colspan='2'><input name='txtQuery' type='text' size='50' style='font-size:16pt'/></td></tr><tr><td align='center'><input type='radio' name='type_of_search' value='trad' CHECKED>Traditional Search</input>");
		out.println("<input type='radio' name='type_of_search' value='proc'>Process-Based Search</input></td><td align='right'><input name='butExp' type='submit' value='Explore It' align='right' style='font-size:16px; width:inherit'/></td>");
		out.println("</tr></table></form></body></html>");
	}

}
