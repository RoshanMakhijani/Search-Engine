import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.*;
class Aragog
{	Vector nxturl;
	StringBuffer con;
	 int endpos;
	 int startpos;
	Aragog()
	{	
	startpos=-1;
	endpos=1000;
		nxturl=new Vector();
		con=new StringBuffer();
	}
	void learnURL()
	{
		try {
			URL url = new URL("http://www.ehow.com/");
			URLConnection ucon=url.openConnection();
			InputStream is=ucon.getInputStream();
			BufferedOutputStream outStream = new BufferedOutputStream(new
					FileOutputStream("D:\\Test3"));
			byte[] buf = new byte[65534];
			int ByteRead;
			while ((ByteRead = is.read(buf)) != -1) 
			{
			outStream.write(buf, 0, ByteRead);
			outStream.flush();
			}
	HTMLEditorKit.ParserCallback callback = 
	      new HTMLEditorKit.ParserCallback () {
	        public void handleText(char[] data, int pos) {
	        	
	        }
	        public void handleStartTag(HTML.Tag tag, 
	                MutableAttributeSet attrSet, int pos) {
	        	
	        	if(tag==HTML.Tag.A)
	        	{String str=(String)(attrSet.getAttribute(HTML.Attribute.HREF));
	        		if(!nxturl.contains(str))
	        			{
	        			if(processURL(str))
	        				{System.out.println(str);
	        			nxturl.add(str);}
	        			}
	        	}
	        	
	        		
	        }
	        public void handleSimpleTag(HTML.Tag tag, 
	                MutableAttributeSet attrSet, int pos) {
	        	//System.out.print(" Simple "+tag.toString());
	        		
	        	
	        }
	        public void handleEndTag(HTML.Tag tag, 
	                 int pos) {
	        	//System.out.print("  "+tag.toString());
	        	
	        }
	    };
	    
	    Reader reader=new FileReader("D://Test3");
			new ParserDelegator().parse(reader, callback, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	boolean processURL(String u)
	{
		
		try {
			URL url = new URL(u);
			URLConnection ucon=url.openConnection();
			InputStream is=ucon.getInputStream();
			BufferedOutputStream outStream = new BufferedOutputStream(new
					FileOutputStream("D:\\Test2"));
			byte[] buf = new byte[65534];
			int ByteRead;
			while ((ByteRead = is.read(buf)) != -1) 
			{
			outStream.write(buf, 0, ByteRead);
			outStream.flush();
			}
	HTMLEditorKit.ParserCallback callback = 
	      new HTMLEditorKit.ParserCallback () {
		
	        
			public void handleText(char[] data, int pos) {
	        	
				
				if(pos<endpos && pos>startpos)
	        	{
	        		con.append(data);
	        	}
	        }
	        public void handleStartTag(HTML.Tag tag, 
	                MutableAttributeSet attrSet, int pos) {
	        	if(tag==HTML.Tag.TITLE)
	        	{
	        	startpos=pos;	
	        	}
	        		
	        }
	        public void handleSimpleTag(HTML.Tag tag, 
	                MutableAttributeSet attrSet, int pos) {
	        	//System.out.print(" Simple "+tag.toString());
	        		
	        	
	        }
	        public void handleEndTag(HTML.Tag tag, 
	                 int pos) {
	        	//System.out.print("  "+tag.toString());
	        	if(tag==HTML.Tag.TITLE)
	        	{
	        	startpos=pos;	
	        	}
	        }
	    };
	    
	    Reader reader=new FileReader("D://Test3");
			new ParserDelegator().parse(reader, callback, true);
			
			if(con.indexOf("How To")!=-1)
			{//System.out.println("Content: "+con);
				return true;
			}
			else
				return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return false;
	}
	    public static void main(String arg[])
	    {
	    	Aragog a=new Aragog();
	    	a.learnURL();
	    	int count=0;
	    	while(count<a.nxturl.size())
	    	{
	    		System.out.print(a.nxturl.elementAt(count));
	    	}
	    }
	   
}