
/* functions.js
<script language="JavaScript"> 

function displayAlert()
{alert('Currently,no such process!!Help create the same by AddProcess link');
}
</script>
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Query
 */
public class Query extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MyGoogle mg;
	PrintWriter out;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Query() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		out = response.getWriter();
		String choice=request.getParameter("type_of_search");
		
		mg=new MyGoogle();
		//System.out.println("Enter the Query");
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//String mquery=br.readLine();
		if(choice.equals("proc"))
		{
			try {
				processQuery(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(choice.equals("trad"))
		{
			try {
				traditionalQuery(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	void traditionalQuery(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Xplore</title>");
	out.println("<link rel='shortcut icon' type='image/x-icon' href='favicon.ico'>");
	out.println("</head>");
	out.println("<body>");
	out.println("<table width='100%'><tr><td><font size=3'><a href='processhome.html' >Home</a></font>&nbsp;<font size='3'><a href='About.html' >About</a></font>&nbsp;");
	out.println("<font size='3'><a href='partners.html' >Partners</a></font>&nbsp;<font size='3'><a href='Create_process.html' >Add Process </a></font>&nbsp;<font size='3'><a href='modify.html' >Modify Process </a></font></td><td align='right'><font size='3'><a href='Authenticate.html'>Sign In</a>&nbsp;&nbsp;<a href='processhome.html' onClick='parent.location='mailto:roshanm89@gmail.com''>Contact Us</a></font></td>");
	out.println("</tr></table>");
	out.println("<object width='200' height='150' align='absmiddle'><param name='movie' value='fianlBin.swf' />");
	out.println("<param name='wmode' value='transparent'><embed src='finalBin.swf' width='200' height='150' align='absmiddle' wmode='transparent'></embed>");
	if(PersonalGoogle.auth)
	out.println("</object><br/><form action='/Xplore/RedirectPersonalize' method='get' name='frmQuery' id='frmQuery' ><input name='txtQuery' type='text' size='50' style='font-size:13pt'/>&nbsp;&nbsp;&nbsp;&nbsp;<input name='butExp' type='submit' value='Explore It' width='50' style='font-size:13px; width:inherit'/><br><input type='radio' name='type_of_search' value='personal_search' CHECKED>Personalized Search</input><input type='radio' name='type_of_search' value='proc'>Process-Based Search</input></form><br/>");
	else
	out.println("</object><br/><form action='/Xplore/Query' method='get' name='frmQuery' id='frmQuery' ><input name='txtQuery' type='text' size='50' style='font-size:13pt'/>&nbsp;&nbsp;&nbsp;&nbsp;<input name='butExp' type='submit' value='Explore It' width='50' style='font-size:13px; width:inherit'/><br><input type='radio' name='type_of_search' value='trad' CHECKED>Traditional Search</input><input type='radio' name='type_of_search' value='proc'>Process-Based Search</input></form><br/>");
		String query=request.getParameter("txtQuery");
		query=query.replaceAll(" ", "%20");
		mg.fire(query,out,20);
	}
	void processQuery(HttpServletRequest request, HttpServletResponse response) throws Exception
	{//System.out.println("processQuery");
				
		Tagger tg=new Tagger(request.getParameter("txtQuery"));
		String n=tg.extNoun(tg.putout);
		String v=tg.extVerb(tg.putout);
		int pid=mg.getPid(n, v);
		System.out.println("pid value"+pid);
		if(pid!=0)
		{
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Xplore</title>");
			out.println("<link rel='shortcut icon' type='image/x-icon' href='favicon.ico'>");	
		out.println("</head>");
		out.println("<body>");
		out.println("<table width=100%><tr><td ><font size=3'><a href='processhome.html' >Home</a></font>&nbsp;<font size='3'><a href='About.html' >About</a></font>&nbsp;");
		out.println("<font size='3'><a href='partners.html' >Partners</a></font>&nbsp;<font size='3'><a href='Create_process.html' >Add Process </a></font>&nbsp;<font size='3'><a href='modify.html' >Modify Process </a></font></td>");
		//out.println("<img src='images/butPartners1.gif' name='Partners' width='117' height='24' border='0' id='Partners' /></a>");
		if(PersonalGoogle.auth)
		out.println("<td align='right' ><font size='3'>"+PersonalGoogle.name+"&nbsp;&nbsp;</font><font size='4'><a href='Logout'>Sign Out</a>&nbsp;&nbsp;<a href='processhome.html' onClick='parent.location='mailto:pmoulick7@gmail.com,sanjurm16@gmail.com,roshanm89@gmail.com''>Contact Us</a></font></td></tr></table>");
		else
			out.println("<td align='right'><font size='3'><a href='Authenticate.html'>Sign In</a>&nbsp;&nbsp;<a href='processhome.html' onClick='parent.location='mailto:pmoulick7@gmail.com,sanjurm16@gmail.com,roshanm89@gmail.com''>Contact Us</a></font></td></tr></table>");
		out.println("<object width='200' height='150' align='absmiddle'><param name='movie' value='fianlBin.swf' />");
		out.println("<param name='wmode' value='transparent'><embed src='finalBin.swf' width='200' height='150' align='absmiddle' wmode='transparent'></embed>");
		if(PersonalGoogle.auth)
			out.println("</object><br/><form action='/Xplore/RedirectPersonalize' method='get' name='frmQuery' id='frmQuery' ><input name='txtQuery' type='text' size='50' style='font-size:13pt'/>&nbsp;&nbsp;&nbsp;&nbsp;<input name='butExp' type='submit' value='Explore It' width='50' style='font-size:13px; width:inherit'/><br><input type='radio' name='type_of_search' value='personal_search' CHECKED>Personalized Search</input><input type='radio' name='type_of_search' value='proc'>Process-Based Search</input></form><br/>");
			else
			out.println("</object><br/><form action='/Xplore/Query' method='get' name='frmQuery' id='frmQuery' ><input name='txtQuery' type='text' size='50' style='font-size:13pt'/>&nbsp;&nbsp;&nbsp;&nbsp;<input name='butExp' type='submit' value='Explore It' width='50' style='font-size:13px; width:inherit'/><br><input type='radio' name='type_of_search' value='trad' CHECKED>Traditional Search</input><input type='radio' name='type_of_search' value='proc'>Process-Based Search</input></form><br/>");

		Vector q=mg.getSteps(pid);
		int count=0;
		String query;
		
		while(count<q.size())
		{
			query=(String) q.elementAt(count);
			out.println("&nbsp;<br/><fieldset><font size='5'><legend>");
			out.println("<u>Step "+(count+1)+"</u> : " + query+"</legend>");
			out.println("</font>&nbsp;<br/>");
			query=query.replaceAll(" ", "%20");
			mg.fire(query,out,5);
			out.println("</fieldset>");
			count++;
		}
		}
		else
		{//System.out.print("in pid");
			String someMessage="Currently,no such process!!Help create the same by AddProcess link";
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Xplore</title>");
		out.println("<link rel='shortcut icon' type='image/x-icon' href='favicon.ico'>");
		out.println("<script type=\"text/javascript\">window.alert(" + someMessage + ");</script></head>");
		out.println("<body onload='displayAlert()'>");
		
			out.println("<table width='100%'><tr><td><font size='4'><a href='processhome.html'>Home</a></font>&nbsp;");
			//out.println("<img src='images/but1.gif' alt='' name='Home' width='117' height='24' border='0' id='Home' /></a>");
			out.println("</td><td><font size='4'><a href='About.html'>About</a><font>&nbsp;");
			//out.println("<img src='images/butAbout1.gif'  name='About' width='117' height='24' border='0' id='About' onload='' /></a>");
			out.println("</td><td><font size='4'><a href='partners.html'>Partners</a></font>&nbsp;");
			out.println("</td><td><font size='4'><a href='Create_process.html'>Add&nbsp;Process </a></font>");
			out.println("&nbsp;<font size='4'><a href='modify.html' >Modify&nbsp;Process </a></font>");
			//out.println("<img src='images/butPartners1.gif' name='Partners' width='117' height='24' border='0' id='Partners' /></a>");
			if(PersonalGoogle.auth)
			out.println("</td><td align='right'><font size='4'>"+PersonalGoogle.name+"&nbsp;&nbsp;</font><font size='3'><a href='Logout'>Sign Out</a>&nbsp;&nbsp;<a href='processhome.html' onClick='parent.location='mailto:pmoulick7@gmail.com,sanjurm16@gmail.com,roshanm89@gmail.com''>Contact Us</a></font></td></tr></table>");
			else
				out.println("</td><td align='right'><font size='4'><a href='Authenticate.html'>Sign In</a>&nbsp;&nbsp;<a href='processhome.html' onClick='parent.location='mailto:pmoulick7@gmail.com,sanjurm16@gmail.com,roshanm89@gmail.com''>Contact Us</a></font></td></tr></table>");

			out.println("<object width='200' height='150' align='absmiddle'><param name='movie' value='fianlBin.swf' />");
			out.println("<param name='wmode' value='transparent'><embed src='finalBin.swf' width='200' height='150' align='absmiddle' wmode='transparent'></embed>");
			if(PersonalGoogle.auth)
				out.println("</object><br/><form action='/Xplore/RedirectPersonalize' method='get' name='frmQuery' id='frmQuery' ><input name='txtQuery' type='text' size='50' style='font-size:13pt'/>&nbsp;&nbsp;&nbsp;&nbsp;<input name='butExp' type='submit' value='Explore It' width='50' style='font-size:13px; width:inherit'/><br><input type='radio' name='type_of_search' value='personal_search' CHECKED>Personalized Search</input><input type='radio' name='type_of_search' value='proc'>Process-Based Search</input></form><br/>");
				else
				out.println("</object><br/><form action='/Xplore/Query' method='get' name='frmQuery' id='frmQuery' ><input name='txtQuery' type='text' size='50' style='font-size:13pt'/>&nbsp;&nbsp;&nbsp;&nbsp;<input name='butExp' type='submit' value='Explore It' width='50' style='font-size:13px; width:inherit'/><br><input type='radio' name='type_of_search' value='trad' CHECKED>Traditional Search</input><input type='radio' name='type_of_search' value='proc'>Process-Based Search</input></form><br/>");
			out.println("<strong>Currently,no such process!!Help create the same by clicking &nbsp;<a href='Create_process.html' >here</a></strong><br/>");
			String query=request.getParameter("txtQuery");
			query=query.replaceAll(" ", "%20");
			mg.fire(query,out,20);
			out.println("</body>");
			out.println("</html>");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}