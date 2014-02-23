

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
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Xplore</title>");
		out.println("<link rel='shortcut icon' type='image/x-icon' href='favicon.ico'><script type='text/JavaScript'>");
		out.println("<!--function MM_preloadImages() { //v3.0var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();");
		out.println("var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)if (a[i].indexOf('#)!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}}");

		out.println("function MM_findObj(n, d) { //v4.01var p,i,x;  if(!d) d=document; if((p=n.indexOf('?'))>0&&parent.frames.length) {d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}");
		out.println("if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);");
		out.println("if(!x && d.getElementById) x=d.getElementById(n); return x;}");

		out.println("function MM_nbGroup(event, grpName) { //v6.0var i,img,nbArr,args=MM_nbGroup.arguments;");
		out.println("if (event == 'init' && args.length > 2) {if ((img = MM_findObj(args[2])) != null && !img.MM_init) {");
		out.println("img.MM_init = true; img.MM_up = args[3]; img.MM_dn = img.src;if ((nbArr = document[grpName]) == null) nbArr = document[grpName] = new Array();");
		out.println("nbArr[nbArr.length] = img;for (i=4; i < args.length-1; i+=2) if ((img = MM_findObj(args[i])) != null) {if (!img.MM_up) img.MM_up = img.src;");
		out.println("img.src = img.MM_dn = args[i+1];nbArr[nbArr.length] = img;} }} else if (event == 'over') {");
		out.println("document.MM_nbOver = nbArr = new Array();for (i=1; i < args.length-1; i+=3) if ((img = MM_findObj(args[i])) != null) {");
		out.println("if (!img.MM_up) img.MM_up = img.src;img.src = (img.MM_dn && args[i+2]) ? args[i+2] : ((args[i+1])? args[i+1] : img.MM_up);nbArr[nbArr.length] = img;");
		out.println("}} else if (event == 'out' ) {for (i=0; i < document.MM_nbOver.length; i++) {img = document.MM_nbOver[i]; img.src = (img.MM_dn) ? img.MM_dn : img.MM_up; }");
		out.println("} else if (event == 'down') {nbArr = document[grpName];if (nbArr)for (i=0; i < nbArr.length; i++) { img=nbArr[i]; img.src = img.MM_up; img.MM_dn = 0; }");
		out.println("document[grpName] = nbArr = new Array();for (i=2; i < args.length-1; i+=2) if ((img = MM_findObj(args[i])) != null) {if (!img.MM_up) img.MM_up = img.src;");
		out.println("img.src = img.MM_dn = (args[i+1])? args[i+1] : img.MM_up;nbArr[nbArr.length] = img;} }}");
		//-->
		out.println("</script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table><tr><td><font size='3'><a href='processhome.html'>Home</a></font>&nbsp;");
		//out.println("<img src='images/but1.gif' alt='' name='Home' width='117' height='24' border='0' id='Home' /></a>");
		out.println("</td><td><font size='3'><a href='About.html'>About</a><font>&nbsp;");
		//out.println("<img src='images/butAbout1.gif'  name='About' width='117' height='24' border='0' id='About' onload='' /></a>");
		out.println("</td><td><font size='3'><a href='partners.html'>Partners</a></font>&nbsp;");
		out.println("</td><td><font size='3'><a href='Create_process.html'>Add Process </a></font>");
		//out.println("<img src='images/butPartners1.gif' name='Partners' width='117' height='24' border='0' id='Partners' /></a>");
		out.println("</td></tr><tr></table>");
		out.println("<table align='center'><tr><td><object width='200' height='150' align='absmiddle'><param name='movie' value='fianlBin.swf' />");
		out.println("<param name='wmode' value='transparent'><embed src='finalBin.swf' width='200' height='150' align='absmiddle' wmode='transparent'></embed>");
		out.println("</object><br/></td></tr></table>");
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
		String query=request.getParameter("txtQuery");
		query=query.replaceAll(" ", "%20");
		mg.fire(query,out,20);
	}
	void processQuery(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Tagger tg=new Tagger(request.getParameter("txtQuery"));
		String n=tg.extNoun(tg.putout);
		String v=tg.extVerb(tg.putout);
		int pid=mg.getPid(n, v);
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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}