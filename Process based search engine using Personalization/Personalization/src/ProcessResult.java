import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

import org.json.JSONArray;

class ProcessResult
{
	double res[][];
	boolean optout;
	StringBuffer con;
	int[] conCountInDoc(Vector Size,String res_url)
	{	con=new StringBuffer();
		int conCount[]=new int[Size.size()];
		try {
		URL url = new URL(res_url);
		URLConnection ucon=url.openConnection();
		InputStream is=ucon.getInputStream();
		BufferedOutputStream outStream = new BufferedOutputStream(new
				FileOutputStream("D:\\Test3"));
		byte[] buf = new byte[65534];
		int ByteRead;
		while ((ByteRead = is.read(buf)) != -1) 
		{
			//System.out.print("buff");	
		outStream.write(buf, 0, ByteRead);
		outStream.flush();
		}
		
		HTMLEditorKit.ParserCallback callback = 
		      new HTMLEditorKit.ParserCallback (){
			 public void handleText(char[] data, int pos) 
			 {	    
				con.append(data); 
			 }    	
		};
		Reader reader =  new FileReader("D://Test3");
		    
				new ParserDelegator().parse(reader, callback, true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int ind=0;
			//System.out.print(Size.size());
			for(int i=0;i<Size.size();i++)
			{	ind=0;
				//System.out.println("ind  "+ind);
				//System.out.println(con);
				while(con.indexOf((String) Size.get(i),ind)!=-1)
				{	
					ind=con.indexOf((String) Size.get(i),ind);
					ind++;
					conCount[i]++;
				}
				/*System.out.print(" i"+i);
				System.out.print("hello");*/
			}
			
		return conCount;
	}
	int[] finalRank(int tot_con,double doc_desc[][],double K[][],JSONArray jarr)
	{
		//double res[][]=new double[tot_con][jarr.length()];
		double temp1=0,temp2=0;
		boolean change=true;
		//while(change)
		//{
			//change=false;
		for(int i=1;i<tot_con;i++)
		{
			for(int j=0;j<jarr.length();j++)
			{
				for(int l=0;l<tot_con;l++)
				{
					temp1=Math.min(K[i][l], doc_desc[l][j]);
					temp2=Math.max(temp2, temp1);
				}
				//if(temp2!=res[i][j])
				//{
					//change=true;
				res[i][j]=temp2;
				//}
			}
		}
		
		//}
		/*for(int i=0;i<tot_con;i++)
			for(int j=0;j<jarr.length();j++)
				res[i][j]=doc_desc[i][j];*/
		//order the urls:
		//sum of columns
		double sum[]=new double[jarr.length()];
		for(int i=0;i<jarr.length();i++)
		{
			for(int j=0;j<tot_con;j++)
			{
				System.out.print("  "+i+") "+res[j][i]);
				sum[i]=sum[i]+res[j][i];
			}
			System.out.println("");
		}
		//find max
		int rank[]=new int[jarr.length()];
		for(int i=0;i<jarr.length();i++)
			rank[i]=i;
		double temp=0;
		int r=0;
		for(int i=0;i<jarr.length();i++)
		{
			System.out.println("sum["+i+"]= "+sum[i]);
		}
		for(int i=0;i<jarr.length();i++)
		{
			//System.out.println("sum["+i+"]= "+sum[i]);
			for(int j=i+1;j<jarr.length();j++)
			{
				if(sum[i]<sum[j])
					{
					//System.out.println("sum["+i+"]="+sum[i]+" sum["+j+"]="+sum[j]);
					r=rank[j];
					rank[j]=rank[i];
					rank[i]=r;
					temp=sum[j];
					sum[j]=sum[i];
					sum[i]=temp;
					}
			}
			for(int k=0;k<jarr.length();k++)
				System.out.print(rank[k]+" ");
			System.out.println();
			
		}
		
	return rank;	
	}
	
}