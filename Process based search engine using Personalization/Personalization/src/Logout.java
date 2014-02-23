

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
		MyGoogle.auth=false;
		MyGoogle.userID="";
		MyGoogle.userName="";
		MyGoogle.name="";
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
		out.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' /><title>Xplore</title>");
		out.println("<link rel='shortcut icon' type='image/x-icon' href='favicon.ico'></head><body><table width='100%'>	<tr>");
		out.println("<td height='28'><table><tr><td><font size='3'><a href='processhome.html'>Home</a></font>&nbsp; </td>");
		out.println("<td><font size='3'><a href='About.html' >About</a></font>&nbsp;  </td><td><font size='3'><a href='partners.html' >Partners</a></font></td>");
		out.println("</tr><tr></table></td><td width='100%' align='right' valign='top'><table align='right'>");		 
		out.println("<tr><td width='100%'><font size='3'><a href='Authenticate.html'>Sign In</a></font></td></tr></table></td></tr></table>");
		out.println("<table align='center'><tr><td><object width='446' height='269' align='absmiddle'><param name='movie' value='fianlBin.swf' />");
		out.println("<param name='wmode' value='transparent'><embed src='finalBin.swf' width='446' height='269' align='absmiddle' wmode='transparent'> </embed></object>");
		out.println("<br/></td></tr></table><table align='center'><form action='/Personalization/Query' method='get' name='frmQuery' id='frmQuery' ><tr><td ><center>");
		out.println("<input name='txtQuery' type='text' size='50' style='font-size:16pt'/></center></td></tr><tr><td>&nbsp;</td></tr><tr><td align='right'><input name='butExp' type='submit' value='Explore' width='50' align='right' style='font-size:16px; width:inherit'/></td>");
		out.println("</tr></form></table></center></body></html>");
	}

}
