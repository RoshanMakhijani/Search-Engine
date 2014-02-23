import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.*;
class ProcessExt implements Runnable
{
	Vector address,wait_add;
	
	boolean flag;
	ProcessExt()
	{
		address=new Vector();
		wait_add=new Vector();
	}
	synchronized void setFlag()
	{
		flag=true;
	}
	 void addAddress(String add)
	{
		if(!address.contains(add))
			address.addElement(add);
		if(!wait_add.contains(add))
			wait_add.addElement(add);
	}
	 String removeAddress()
	{
		String add=(String) address.remove(0);
		return add;
	}
	 String removeWait_Add()
	{
		String add=(String) wait_add.remove(0);
		return add;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		if(!flag)
		{
			setFlag();
			while(address.size()>0)
			{
				try
				{
					String add=removeAddress();
					///URL url = new URL("http://www.ehow.com/");
						URL url = new URL(add);
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
					        public void handleStartTag(HTML.Tag tag, 
					                MutableAttributeSet attrSet, int pos) {
					        	//System.out.print("  "+tag.toString())
					        	try{
					        	if(tag==HTML.Tag.A)
					        	{
					        		
					        		String add=(String) attrSet.getAttribute(HTML.Attribute.HREF);
					        		//System.out.println("Address "+add);
					        		if(add.indexOf("www.ehow.com")!=-1)
					        			{
					        			addAddress(add);
					        			try{
					        			Thread.sleep(10);
					        			}
					        			catch(Exception e)
					        			{}
					        			}
					        	}
					        	}
					        	catch(Exception e){}
					        }
					    };
					    
					    Reader reader = new FileReader("D://Test3");
					    new ParserDelegator().parse(reader, callback, true);
					    
					//System.out.print(P.toString());
					
					
					}
					
				catch(Exception e)
				{
					System.out.print("IN Catch");
					e.printStackTrace();
				}
			}
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			
			
			while(true)
			{
				ParsePage lsp=new ParsePage();
				try{
				String nxtadd=removeWait_Add();System.out.println("NxtAdd "+nxtadd);
				lsp.parseHTML(nxtadd);
				lsp.extProcess(lsp.content);
				lsp.extSteps();
				System.out.print("Done");
				}
				catch(Exception e){}
				
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			//System.out.print("Out of here");
		}
	}
	
}
class ProcessExtractor
{
	public static void main(String arg[])
	{
		ProcessExt PE=new ProcessExt();
		PE.address.add("http://www.ehow.com");
		PE.wait_add.add("http://www.ehow.com");
		Thread Crawler=new Thread(PE,"Crawler");
		Thread Extractor=new Thread(PE,"Extractor");
		Crawler.start();
		Extractor.start();
	}
}