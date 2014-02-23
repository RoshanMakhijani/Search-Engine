import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import org.json.*;
class MyGoogle
{
	int getPid(String n, String v)
	{	int pid=0;
	try
	{
		System.out.println(n);
		System.out.println(v);
		DriverManager.registerDriver(
                new oracle.jdbc.OracleDriver());
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracl", "scott", "tiger");
	Statement statement=conn.createStatement();
	String q="SELECT P_ID FROM Annotation where Object='"+n+"'AND Predicate='"+v+"'";
	ResultSet res = statement.executeQuery(q);
	//int i=0;

	while(res.next())
	{
		
		pid=res.getInt(1);
		//System.out.println(steps.elementAt(i));
		
	}
	conn.close();
	statement=null;
	conn=null;
	res=null;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	System.out.println(pid);
		return pid;
	}
	Vector getSteps(int pid)
	{
		Vector steps=new Vector();
		try
		{
		//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
		//Connection conn=DriverManager.getConnection("jdbc:odbc:ProcessBase","","");
			DriverManager.registerDriver(
	                new oracle.jdbc.OracleDriver());
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracl", "scott", "tiger");
		Statement statement=conn.createStatement();
		ResultSet res = statement.executeQuery("SELECT Title FROM Step_Table where Process_ID="+pid);
		//int i=0;
		
		while(res.next())
		{
			steps.add(res.getString(1));
			//System.out.println(steps.elementAt(i));
			
		}
		statement=null;
		conn=null;
		res=null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return steps;
	}
	void fire(String query, PrintWriter out,int no_of_q)throws IOException
	{
		try
		{int z=0;
		int k=0;
			//System.out.println(query);
			while(true)
			{
			URL url = new URL(
				    "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
				    + "q="+query+"&key=ABQIAAAAocJ-KvMi0B_FxpNh7qnRDBQhBt6oGohfqEJBSQzz1VHC9nUx0xRCMrXA-ZboKuhYU4VGreZydDjqwQ&start="+k+"&rsz=large&userip=169.254.99.10");
				URLConnection connection = url.openConnection();
				String line;
				StringBuffer builder = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line= reader.readLine()) != null) {
				 builder.append(line);
				}
				
				JSONObject json = new JSONObject(builder.toString());
				//System.out.println(json.toString());
				//System.out.print(json.get("url"));
				json=(JSONObject)(json.get("responseData"));
				
				//JSONObject js=(JSONObject)json.get("cursor");
				//String more=js.optString("moreResultsUrl");
				JSONArray jarr=(JSONArray)(json.get("results"));
				int results=no_of_q;
				if(no_of_q>jarr.length())
				{
					results=jarr.length();
				}
				for(int i=0;i<results;i++)
				{
					
					if(k==0){
					json=(JSONObject)jarr.get(i);
					out.println("&nbsp;<br/>");
					out.println(" "+(z+1));
					z++;
					out.println("<a href='"+ json.get("url").toString()+"' name='"+json.get("title").toString()+"'>"+json.get("title").toString()+"</a><br/>");
					//out.println("TITLE :"+ json.get("title").toString()+"<br/>");
					out.println(json.get("content").toString()+"<br/>");
					out.println("&nbsp;<br/>");
					}
					else
					{
						json=(JSONObject) jarr.get(jarr.length()-1);
						out.println("&nbsp;<br/>");
						out.println(" "+(z+1));
						z++;
						out.println("<a href='"+ json.get("url").toString()+"' name='"+json.get("title").toString()+"'>"+json.get("title").toString()+"</a><br/>");
						//out.println("TITLE :"+ json.get("title").toString()+"<br/>");
						out.println(json.get("content").toString()+"<br/>");
						out.println("&nbsp;<br/>");
						break;
						
					}
				}
			
				
				if(z==no_of_q)
				{
					break;
				}
				k++;
			}
		}
		catch(Exception e)
		{
			System.out.println("IN Catch");
			e.printStackTrace();
		}
	}
	
	public void go(String arg)throws IOException
	{
		//MyGoogle mg=new MyGoogle();
		//System.out.println("Enter the Query");
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//String mquery=br.readLine();
		Tagger tg=new Tagger(arg);
		String n=tg.extNoun(tg.putout);
		String v=tg.extVerb(tg.putout);
		int pid=getPid(n, v);
		Vector q=getSteps(pid);
		int count=0;
		String query;
		while(count<q.size())
		{
			query=(String) q.elementAt(count);
			System.out.println(" ");
			System.out.println("Step "+(count+1)+" : "+ query);
			System.out.println(" ");
			query=query.replaceAll(" ", "%20");
			//fire(query);
			count++;
		}
		
		
	}
}