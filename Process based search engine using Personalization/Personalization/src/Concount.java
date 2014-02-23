import java.util.*;
import java.io.*;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
class Concount implements Serializable
{
	StringBuffer con=new StringBuffer();
	 static int tot_doc;
	 boolean optout;
	 Vector Size;
	 double K[][];
	void getConcepts(String doc_add,int doc_no,Vector con_name,Vector con_count[])
	{
		TestJAWS TJ=new TestJAWS();
		String synwords[];
		//String n=tg.extNoun(tg.putout);
		//String v=tg.extVerb(tg.putout);
		HTMLEditorKit.ParserCallback callback = 
		      new HTMLEditorKit.ParserCallback (){
			 public void handleText(char[] data, int pos) 
			 {	
		           con.append(data); 
			 }    	
		};
		try {
		Reader reader = new FileReader(doc_add);
		    
				new ParserDelegator().parse(reader, callback, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String delims = " ,;!@#$%^&*()<>:?.";
			StringTokenizer words=new StringTokenizer(con.toString(),delims);
			/*int start_ind=0;
			int end_ind=0;
			
			while((end_ind=((con.indexOf(" ",start_ind)<=con.indexOf(".",start_ind))&& con.indexOf(" ",start_ind)!=-1)?(end_ind=con.indexOf(" ",start_ind)):(end_ind=con.indexOf(".",start_ind)))!=-1 )
			{	
				words.add(con.substring(start_ind, end_ind));
				start_ind=end_ind+1;	
			}
			words.add(con.substring(start_ind));*/
			Tagger tg;
			String temp="";
			while(words.hasMoreTokens())
			{	temp=words.nextToken();
				if(!con_name.contains(temp))
				{	
					tg=new Tagger(temp);
					if(tg.putout.indexOf("/NN")!=-1 || tg.putout.indexOf("/VB")!=-1 /*&& tg.putout.indexOf("/VBD")==-1&& tg.putout.indexOf("/VBZ")==-1)*/)
					{
						con_name.add(temp);
						synwords=TJ.getSynset(temp);
						try{
						if(synwords.length>0)
						{
							for(int j=0;j<synwords.length;j++)
								if(!con_name.contains(synwords[j]))
									{
									con_name.add(synwords[j]);
									con_count[doc_no].add(new Integer(1));
									}
								else
								{
									int index=con_name.indexOf(temp);
									Integer I;
									try{
									I=(Integer)con_count[doc_no].elementAt(index);
									}
									catch( ArrayIndexOutOfBoundsException e)
									{
										int k=con_count[doc_no].size();
										while(k<=index)
										{
											con_count[doc_no].add(k,new Integer(0));
											k++;
										}
									 I=(Integer)con_count[doc_no].elementAt(index);
									}
									con_count[doc_no].setElementAt((new Integer(I.intValue()+1)), index);
								}
						}
						}
						catch(Exception e){}
						con_count[doc_no].add(new Integer(1));
					}
				}
				else
				{
					int index=con_name.indexOf(temp);
					Integer I;
					try{
					I=(Integer)con_count[doc_no].elementAt(index);
					}
					catch( ArrayIndexOutOfBoundsException e)
					{
						int k=con_count[doc_no].size();
						while(k<=index)
						{
							con_count[doc_no].add(k,new Integer(0));
							k++;
						}
					 I=(Integer)con_count[doc_no].elementAt(index);
					}
					con_count[doc_no].setElementAt((new Integer(I.intValue()+1)), index);
				}
			}
			
		/*	for(int i=0;i<con_name.size();i++)
			{
				System.out.println(con_name.get(i)+"   Count= "+con_count[0].get(i));
			}*/
	}
	void cal_N(Vector con_name[])
	{	
		Size=new Vector();
		int doc_count=0;
		while(doc_count<tot_doc)
		{
			for(int i=0;i<con_name[doc_count].size();i++)
			{
				if(!Size.contains(con_name[doc_count].elementAt(i)))
				{
					Size.add(con_name[doc_count].elementAt(i));
				}
			}
			doc_count++;
		}
		
	}
	double[][] trans_closure(double K[][],int n)
	{
		//BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		double temp1=0,temp2=0;
		//System.out.println();
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				temp1=0;temp2=0;
				for(int l=0;l<n;l++)
				{	
					//System.out.print(K[i][l]+" "+K[l][j]+"    ");
					/*try {
						String s=br.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					temp1=Math.min(K[i][l], K[l][j]);
					temp2=Math.max(temp1,temp2);
				}
				if(temp2!=K[i][j])
					this.optout=false;
				K[i][j]=temp2;
				
			}
		}
		return K;
	}
	double cal_u(String ci,String cj,Vector con_name[],Vector con_count[])
	{	double u=0;
		int counti=0,countj=0,countij=0;
		int doc_count=0;
		int i,j;
		int N=Size.size();
		while(doc_count<tot_doc)
		{
			i=con_name[doc_count].indexOf(ci);
			j=con_name[doc_count].indexOf(cj);
			if(i!=-1)
			{
			counti+=((Integer)(con_count[doc_count].elementAt(i))).intValue();	
			}
			if(j !=-1)
			{
			countj+=((Integer)(con_count[doc_count].elementAt(j))).intValue();
			}
			if(i!=-1&&j!=-1)
			{
				countij+=Math.min(((Integer)(con_count[doc_count].elementAt(i))).intValue(), ((Integer)(con_count[doc_count].elementAt(j))).intValue());
			}
			//countij+=Math.min(((Integer)(con_count[doc_count].elementAt(i))).intValue(), ((Integer)(con_count[doc_count].elementAt(j))).intValue());
			//N=Math.max(con_name[doc_count].size(), N);
			doc_count++;
		}
		//System.out.print("i "+counti +" j "+countj);
		//u=(double)(counti+countj-(Math.abs(counti-countj)))/(double)(N);
		u=(double)(counti+countj-countij)/(double)(N);
		return u;
	}
	/*public static void main(String arg[])
	{
		Concount CC=new Concount();
		Vector con_name[]=new Vector[tot_doc];
		Vector con_count[]=new Vector[tot_doc];
		for(int i=0;i<tot_doc;i++)
		{
			con_count[i]=new Vector();
			con_name[i]=new Vector();
		}
		CC.getConcepts("D:\\Vijay\\Java\\eclipseWorkspace\\Meta Search\\Sanjay.html", 0, con_name[0], con_count);
		CC.con=new StringBuffer();
		CC.getConcepts("D:\\Vijay\\Java\\eclipseWorkspace\\Meta Search\\Ajay.html", 1, con_name[1], con_count);
		int doc_count=0;
		//System.out.println(con_name.size());
		while(doc_count<tot_doc)
		{
		for(int i=0;i<con_name[doc_count].size();i++)
		{
			System.out.println(i+")  Doc no. : "+doc_count+"  "+ con_name[doc_count].get(i)+"   Count= "+con_count[doc_count].get(i));
		}
		doc_count++;
		}
		CC.cal_N(con_name);
		System.out.println("size is "+CC.Size.size());
		double K[][]=new double[CC.Size.size()][CC.Size.size()];
		for(int i=0;i<CC.Size.size();i++)
		{
			for(int j=0;j<CC.Size.size();j++)
			{
				if(i==j)
					K[i][j]=1;
			}
		}
		for(int i=0;i<CC.Size.size();i++)
		{
			for(int j=0;j<CC.Size.size();j++)
			{	
				K[i][j]=CC.cal_u(CC.Size.elementAt(i).toString(), CC.Size.elementAt(j).toString(), con_name, con_count);
				//u.add(CC.Size.elementAt(i));
				//u.add(CC.Size.elementAt(j));
				//u.add(CC.cal_u(CC.Size.elementAt(i).toString(), CC.Size.elementAt(j).toString(), con_name, con_count));
				System.out.println("u for "+CC.Size.elementAt(i)+" & "+CC.Size.elementAt(j)+" = "+ CC.cal_u(CC.Size.elementAt(i).toString(), CC.Size.elementAt(j).toString(), con_name, con_count));
			}
		}
		while(!CC.optout)
		{
			CC.optout=true;
			K=CC.trans_closure(K,CC.Size.size());
		}
		for(int i=0;i<CC.Size.size();i++)
		{
			for(int j=0;j<CC.Size.size();j++)
			{
				System.out.println(K[i][j]);
			}
		}
	}*/
	void fuzzyCreator(Concount CC)
	{
		Vector con_name[]=new Vector[tot_doc];
		Vector con_count[]=new Vector[tot_doc];
		for(int i=0;i<tot_doc;i++)
		{
			con_count[i]=new Vector();
			con_name[i]=new Vector();
		}
		/*CC.getConcepts("D:\\Vijay\\Java\\eclipseWorkspace\\Meta Search\\Sanjay.html", 0, con_name[0], con_count);
		CC.con=new StringBuffer();
		CC.getConcepts("D:\\Vijay\\Java\\eclipseWorkspace\\Meta Search\\Ajay.html", 1, con_name[1], con_count);
		*/
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<tot_doc;i++)
		{
		System.out.println("Enter the Address of the File");
		try {
			CC.getConcepts(br.readLine(), i, con_name[i], con_count);
			CC.con=new StringBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		int doc_count=0;
		//System.out.println(con_name.size());
		while(doc_count<tot_doc)
		{System.out.print(con_name[doc_count].size());
		for(int i=0;i<con_name[doc_count].size();i++)
		{
			System.out.println(i+")  Doc no. : "+doc_count+"  "+ con_name[doc_count].get(i)+"   Count= "+con_count[doc_count].get(i));
		}
		doc_count++;
		}
		CC.cal_N(con_name);
		System.out.println("size is "+CC.Size.size());
		CC.K=new double[CC.Size.size()][CC.Size.size()];
		for(int i=0;i<CC.Size.size();i++)
		{
			for(int j=0;j<CC.Size.size();j++)
			{
				if(i==j)
					K[i][j]=1;
			}
		}
		for(int i=0;i<CC.Size.size();i++)
		{
			for(int j=0;j<CC.Size.size();j++)
			{	
				K[i][j]=CC.cal_u(CC.Size.elementAt(i).toString(), CC.Size.elementAt(j).toString(), con_name, con_count);
				if(i==j)
					K[i][j]=1;
				//u.add(CC.Size.elementAt(i));
				//u.add(CC.Size.elementAt(j));
				//u.add(CC.cal_u(CC.Size.elementAt(i).toString(), CC.Size.elementAt(j).toString(), con_name, con_count));
				//System.out.println("u for "+CC.Size.elementAt(i)+" & "+CC.Size.elementAt(j)+" = "+ CC.cal_u(CC.Size.elementAt(i).toString(), CC.Size.elementAt(j).toString(), con_name, con_count));
					   //  System.out.println("u for "+CC.Size.elementAt(i)+" & "+CC.Size.elementAt(j)+" = "+K[i][j]);
			}
		}
		//while(!CC.optout)
		//{
			CC.optout=true;
			K=CC.trans_closure(K,CC.Size.size());
		//}
		/*for(int i=0;i<CC.Size.size();i++)
		{
			for(int j=0;j<CC.Size.size();j++)
			{
				System.out.println(K[i][j]);
			}
		}*/
	}
}