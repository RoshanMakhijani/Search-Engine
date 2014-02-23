


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import opennlp.tools.postag.POSDictionary;
import opennlp.tools.lang.english.PosTagger;
//import opennlp.tools.postag.TagDictionary;
public class Tagger 
{
	private String ao;
	public String a, putout;
	public Tagger(String ai) 
	{
		this.a = ai;
		putout = tagging(ai);
		//System.out.println(putout);
		
	}
	public String tagging(String ai)
	{
		String s=new String();
		try {
			final POSDictionary POSD = new POSDictionary("D:/Sanjay/Resources/models/tagdict.htm");
			PosTagger pt = new PosTagger("D:/Sanjay/Resources/models/tag.bin.gz", POSD);
			s = pt.tag(ai);
			}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		return s;
	}
	public String extNoun(String s)
	{
		String str[]=s.split(" ");
		String res = null;
		for(int i=0; i<str.length;i++)
		{
			if(str[i].indexOf("/NN")!=-1)
				{
				res=str[i].substring(0,str[i].indexOf("/NN"));
				}		
		}
		return res;
	}
	public String extVerb(String s)
	{
		String str[]=s.split(" ");
		String res = null;
		for(int i=0; i<str.length;i++)
		{
			if(str[i].indexOf("/VB")!=-1)
				{
				res=str[i].substring(0,str[i].indexOf("/VB"));
				}		
		}
		return res;
	}
	public static void main(String arg[]) throws IOException
	{
		System.out.println("Enter the sentence to be tagged for Parts of Speech");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String tring = new String(br.readLine());
		Tagger ta = new Tagger(tring);
		//System.out.println(ta.extNoun(ta.putout));
		//System.out.println(ta.extVerb(ta.putout));
		System.out.println(ta.putout);
	}
	
}
