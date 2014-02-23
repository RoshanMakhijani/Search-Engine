


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class Create_process
 */
public class Create_process extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Create_process() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*
		* Set the content type(MIME Type) of the response.
		*/
		response.setContentType("text/html");
		 
		PrintWriter out = response.getWriter();
		/*
		* Write the HTML to the response
		*/
		
		
		
		int p_id_count=0;
		int s_id_count=0;
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Process Creation </title>");
		out.println("</head>");
		out.println("<body>");
		
		String p_title=request.getParameter("p_title");
		String p_category=request.getParameter("p_category");
		String p_desc=request.getParameter("p_desc");
		
		Tagger tg=new Tagger(p_title);
		String n=tg.extNoun(tg.putout);
		String v=tg.extVerb(tg.putout);
		String s_title=request.getParameter("s_title");
		String s_url=request.getParameter("s_url");
		String s_desc=request.getParameter("s_desc");
		
		try
		{
			DriverManager.registerDriver(
                    new oracle.jdbc.OracleDriver());
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracl", "scott", "tiger");
			Statement statement=conn.createStatement();
			ResultSet res = statement.executeQuery("SELECT COUNT(*) FROM Process_Table");
		        while (res.next()){
		          p_id_count = res.getInt(1);
		        }
		        p_id_count=p_id_count+1;
		   out.println("Count:" + p_id_count);
		   


		   String query1="insert into Process_Table values ("+p_id_count+",'"+p_title+"','"+p_category+"','"+p_desc+"')";
		   statement.executeUpdate(query1);
		   String query2="insert into Annotation values ("+p_id_count+",'"+n+"','"+v+"')";
		   statement.executeUpdate(query2);
		/*  PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Process_Table VALUES (?,?,?,?);");
		   pstmt.setInt(1, p_id_count);
		   pstmt.setString(2, p_title);       
		   pstmt.setString(3, p_category); 
		   pstmt.setString(4, p_desc); 
		   pstmt.executeUpdate();*/
		   
		   //statement.executeUpdate("INSERT INTO Step_Table values("+1+",'HEllo','Hi','How',"+2+")");
		   res=statement.executeQuery("SELECT COUNT(*) FROM Step_Table WHERE Process_ID="+p_id_count);
		   while (res.next()){
		        s_id_count = res.getInt(1);
		       }
		        s_id_count=s_id_count+1;
		   
		     out.println("   "+s_id_count);
		      
		   	        
		    /*PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Step_Table VALUES (?,?,?,?,?)");
				   pstmt1.setInt(1, s_id_count);  
				   pstmt1.setString(2, s_title); 
				   pstmt1.setString(3, s_url); 
				   pstmt1.setString(4, s_desc);
				   pstmt1.setInt(5,p_id_count );    
				   pstmt1.executeUpdate();*/
		     
		     int i=statement.executeUpdate("INSERT INTO Step_Table values("+s_id_count+",'"+s_title+"','"+s_url+"','"+s_desc+"',"+p_id_count+")");
		    	  
				   
				 if(i!=0){
		        out.println("The process has been inserted with the Step titled:");
		        out.println(" "+s_title);
		      }
		      else{
		        out.println("Sorry! Failure");
		      }	
		     //statement.execute("INSERT INTO Step_Table values ("+s_id_count+",'"+s_title+"','"+s_url+"','"+s_desc+"',"+p_id_count+")");
		     
		     statement.close();
		     //statement.close();
		     conn.commit();
		     conn.close();
		    //conn2=null;
		   // conn=null;
		     
		    
		     
		    
				  
				   
				 /*if(i!=0){
		        out.println("The process has been inserted with the first Step titled:");
		        out.println(" "+s_title);
		      }
		      else{
		        out.println("Sorry! Failure");
		      }	*/
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
