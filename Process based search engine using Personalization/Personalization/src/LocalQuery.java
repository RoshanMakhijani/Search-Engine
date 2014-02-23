import java.io.*;
import java.util.*;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.*;
class LocalQuery
{
	String locurl,finURL;
	Vector nameinput,valinput;
	int startpos,endpos;
	
	LocalQuery(String str)
	{	locurl=str;
		startpos=0;
		finURL="";
		endpos=999999;
		nameinput=new Vector();
		valinput=new Vector();
	}
	void genQuery()
	{
		HTMLEditorKit.ParserCallback callback = 
		      new HTMLEditorKit.ParserCallback (){
			 public void handleStartTag(HTML.Tag tag, 
		                MutableAttributeSet attrSet, int pos) {
		        	//System.out.print("  "+tag.toString())
				 if(tag==HTML.Tag.FORM)
		        	{
		        	startpos=pos;
		        	}
		        }
			 public void handleSimpleTag(HTML.Tag tag, 
		                MutableAttributeSet attrSet, int pos) {
		        	//System.out.print(" Simple "+tag.toString());
		        	
		        	if(tag==HTML.Tag.INPUT)
		        	{
		        	
		        		//System.out.println("YES");
		        	nameinput.add((String) attrSet.getAttribute(HTML.Attribute.NAME));
		        	valinput.add((String) attrSet.getAttribute(HTML.Attribute.VALUE));
		        	}
			 }
		        	 public void handleEndTag(HTML.Tag tag, 
			                 int pos) {
		        		 endpos=pos;
		        	 }
		        	
		};
		try
		{
			
		URL lurl=new URL(locurl);
		
		InputStream is =lurl.openConnection().getInputStream();
		BufferedOutputStream outStream = new BufferedOutputStream(new
				FileOutputStream("D:\\Test2"));
		byte[] buf = new byte[65534];
		int ByteRead;
		while ((ByteRead = is.read(buf)) != -1) 
		{
		outStream.write(buf, 0, ByteRead);
		outStream.flush();
		}
		 Reader reader = new FileReader("D://Test2");
		    new ParserDelegator().parse(reader, callback, true);
		}
		catch(Exception e)
		{
			
		}
	}
	void fireQuery(String q)
	{
		finURL=locurl+"?";
		int count=0;
		while(nameinput.elementAt(count)!=null)
		{	if(count==0)
			finURL+=nameinput.elementAt(count)+"="+valinput.elementAt(count);			
		else
			finURL+="&"+nameinput.elementAt(count)+"="+valinput.elementAt(count);
		count++;
		}
		
		finURL+=q;
		//finURL+="&output=xml_no_dtd";
		//finURL=finURL.replaceAll(":", "%3A");
		try
		{
			URL query=new URL(finURL);
			System.out.println(query.toString());
			InputStream is =query.openConnection().getInputStream();
			BufferedOutputStream outStream = new BufferedOutputStream(new
					FileOutputStream("D:\\Test3"));
			byte[] buf = new byte[65534];
			int ByteRead;
			while ((ByteRead = is.read(buf)) != -1) 
			{
			outStream.write(buf, 0, ByteRead);
			outStream.flush();
			}
		}
		catch(Exception e)
		{
			System.out.print("IN Catch");
			e.printStackTrace();
		}
	}
	
	public static void main(String arg[])
	{
		LocalQuery lq=new LocalQuery("http://www.wikihow.com/Special:GoogSearch");
		lq.genQuery();
		int count=0;
		while(lq.nameinput.elementAt(count)!=null)
		{
			System.out.print(lq.nameinput.elementAt(count)+"=");
			System.out.print(lq.valinput.elementAt(count)+"  ");
			count++;
		}
		lq.fireQuery("Hello");
			
	}
}