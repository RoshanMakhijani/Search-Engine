import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import org.json.*;
class MyGoogle extends ProcessResult
{
	double doc_desc[][];
	Concount CC;
	JSONArray jarr;
	public void googleIt()
	{
		try
		{
			URL url = new URL(
				    "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
				    + "q=Drive%20Car&key=ABQIAAAAocJ-KvMi0B_FxpNh7qnRDBQhBt6oGohfqEJBSQzz1VHC9nUx0xRCMrXA-ZboKuhYU4VGreZydDjqwQ&start=0&userip=169.254.99.10");
			//URL url=new URL("http://www.wikihow.com/Special:GoogSearch?cx=008953293426798287586%3Amr-gwotjmbs&cof=FORID%3A10&ie=UTF-8&q=hello&siteurl=www.wikihow.com%2FMain-Page&siteurl=www.wikihow.com%2FMain-Page");	
			URLConnection connection = url.openConnection();
				BufferedOutputStream outStream = new BufferedOutputStream(new
						FileOutputStream("D:\\Vijay\\Java\\eclipseWorkspace\\WebCrawler\\FileToIndex\\WayneRooney"));
				String line;
				StringBuffer builder = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line= reader.readLine()) != null) {
				 builder.append(line);
				}

				JSONObject json = new JSONObject(builder.toString());
				//System.out.print(json.get("url"));
				json=(JSONObject)(json.get("responseData"));
				 jarr=(JSONArray)(json.get("results"));
				 CC=new Concount();
				
				CC.fuzzyCreator(CC);
				int c_d_count[][]=new int[CC.Size.size()][jarr.length()];
				int total_con_count[]=new int[CC.Size.size()];
				int temp[];
				doc_desc=new double[CC.Size.size()][jarr.length()];
				System.out.println("Concepts");
				for(int k=0;k<CC.Size.size();k++)
				{
				System.out.println(CC.Size.elementAt(k));	
				}
				for(int i=0;i<jarr.length();i++)
				{
					json=(JSONObject) jarr.get(i);
					System.out.println("URL :  "+ json.get("url").toString());
					temp=conCountInDoc(CC.Size,json.get("url").toString());
					
					for(int j=0;j<temp.length;j++)
					{
						c_d_count[j][i]=temp[j];
						total_con_count[j]+=temp[j];
					}
					System.out.println("TITLE :  "+ json.get("title").toString());
					System.out.println("CONTENT :  "+ json.get("content").toString());
					
				}
				System.out.println("no. of result "+jarr.length());
				//Calculate document descriptor
				for(int i=0;i<CC.Size.size();i++)
				{
					for(int j=0;j<jarr.length();j++)
					{
						//System.out.print("total_con_count "+total_con_count[i]+"  ");
						if(total_con_count[i]!=0)
						doc_desc[i][j]=(double)(c_d_count[i][j])/total_con_count[i];
						//if(j==1)
						//System.out.print(i+") "+doc_desc[i][j]+"  ");
					}
				}
				//System.out.println();
		}
		catch(Exception e)
		{
			System.out.println("IN Catch");
			e.printStackTrace();
		}
		res=new double[CC.Size.size()][jarr.length()];
		int rank[]=finalRank(CC.Size.size(),doc_desc,CC.K,jarr);
		System.out.println("ReRanked Result");
		for(int i=0;i<rank.length;i++)
		{
			try {
				System.out.println("Rank "+rank[i]);
				JSONObject j=(JSONObject) jarr.get(rank[i]);
				System.out.println("URL :  "+ j.get("url").toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		public static void main(String arg[])throws IOException
		{
			MyGoogle mg=new MyGoogle();
			mg.googleIt();
		}
}