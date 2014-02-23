import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.*;
class LocalSearchPage 
{
	String localurl;
		// TODO Auto-generated constructor stub
void parseHTML()
{
	
	try
{
	///URL url = new URL("http://www.ehow.com/");
		URL url = new URL("http://www.wikihow.com/Main-Page");
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
	/*FileReader fw=new FileReader("D://Test");
	Parser P=new Parser(this);
	P.parse(fw);*/
	HTMLEditorKit.ParserCallback callback = 
	      new HTMLEditorKit.ParserCallback () {
	        public void handleText(char[] data, int pos) {
	            //System.out.println(data);
	        }
	        public void handleStartTag(HTML.Tag tag, 
	                MutableAttributeSet attrSet, int pos) {
	        	//System.out.print("  "+tag.toString())
	        	if(tag==HTML.Tag.FORM)
	        	{
	        	localurl=(String)(attrSet.getAttribute(HTML.Attribute.ACTION));
	        	}	
	        }
	        public void handleSimpleTag(HTML.Tag tag, 
	                MutableAttributeSet attrSet, int pos) {
	        	//System.out.print(" Simple "+tag.toString());
	        	
	        	if(tag==HTML.Tag.INPUT)
	        	{
	        		
	        		String type,value;
	        		type=(String) attrSet.getAttribute(HTML.Attribute.TYPE);
	        		value=(String) attrSet.getAttribute(HTML.Attribute.VALUE);
	        		
	        		//System.out.println("TYPE  "+type+"  VALUE  "+value);
	        		if(type.equalsIgnoreCase("submit")&& value.equalsIgnoreCase("Search"))
	        		{
	        			//System.out.print("YES   ");
	        			if(localurl!=null)
	        	        	System.out.println(localurl);
	        		}
	        		//System.out.print("In HanadleStart");
	        		/*if(attrSet.containsAttribute(HTML.Attribute.TYPE, "SUBMIT")&&attrSet.containsAttribute(HTML.Attribute.VALUE, "SEARCH") );
	        		{System.out.print("YES   ");
	        		
	        			Iterator it =(Iterator) attrSet.getAttributeNames();
	        			while(it.hasNext())
	        			System.out.print(it.next().toString());
	        			if(localurl!=null)
	        			System.out.println(localurl.toString());
	        		}*/
	        	}
	        }
	        public void handleEndTag(HTML.Tag tag, 
	                 int pos) {
	        	//System.out.print("  "+tag.toString());
	        	/*if(tag==HTML.Tag.HTML)
	        		System.out.print(" DONE ");*/
	        }
	    };
	    
	    Reader reader = new FileReader("D://Test2");
	    new ParserDelegator().parse(reader, callback, true);
	    
	//System.out.print(P.toString());
	
	
	}
	
catch(Exception e)
{
	System.out.print("IN Catch");
	e.printStackTrace();
}
}

public static void main(String arg[])
{
	LocalSearchPage lsp=new LocalSearchPage();
	lsp.parseHTML();
}
}
