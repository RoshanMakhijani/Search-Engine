import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import org.json.*;
class MyGoogle2
{
	public void googleIt()
	{
		try
		{
			URL url = new URL(
				    "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
				    + "q=allinurl:wikihow+Drive%20Car&key=ABQIAAAAocJ-KvMi0B_FxpNh7qnRDBQhBt6oGohfqEJBSQzz1VHC9nUx0xRCMrXA-ZboKuhYU4VGreZydDjqwQ&start=1&userip=169.254.99.10");
			//URL url=new URL("http://www.wikihow.com/Special:GoogSearch?cx=008953293426798287586%3Amr-gwotjmbs&cof=FORID%3A10&ie=UTF-8&q=hello&siteurl=www.wikihow.com%2FMain-Page&siteurl=www.wikihow.com%2FMain-Page");	
			URLConnection connection = url.openConnection();
				//connection.addRequestProperty("Referer", /* Enter the URL of your site here */);
				//File f1=new File("Rooney");
				//FileWriter fw=new FileWriter(f1);
				//BufferedOutputStream outStream = new BufferedOutputStream(new
						//FileOutputStream("D:\\Vijay\\Java\\eclipseWorkspace\\WebCrawler\\FileToIndex\\WayneRooney"));
				String line;
				StringBuffer builder = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line= reader.readLine()) != null) {
				 builder.append(line);
					//fw.write(ch);
					//outStream.write(line);
					//outStream.flush();
				 //System.out.print((char)(ch));
				}
				//FileReader fr=new FileReader("D:\\Vijay\\Java\\eclipseWorkspace\\WebCrawler\\FileToIndex\\WayneRooney");
				
				/*InputStream is=connection.getInputStream();
				BufferedOutputStream outStream = new BufferedOutputStream(new
				FileOutputStream("D:\\Vijay\\Java\\eclipseWorkspace\\WebCrawler\\FileToIndex\\WayneRooney"));
						byte[] buf = new byte[65534];
						int ByteRead;
						while ((ByteRead = is.read(buf)) != -1) {
						outStream.write(buf, 0, ByteRead);
						System.out.println(ByteRead);
						//ByteWritten += ByteRead;
						}*/
				//System.out.println(builder);
				System.out.println(builder);
				JSONObject json = new JSONObject(builder.toString());
				//System.out.print(json.get("url"));
				json=(JSONObject)(json.get("responseData"));
				JSONArray jarr=(JSONArray)(json.get("results"));
				for(int i=0;i<jarr.length();i++)
				{
					json=(JSONObject) jarr.get(i);
					System.out.println("URL :  "+ json.get("url").toString());
					System.out.println("TITLE :  "+ json.get("title").toString());
				System.out.println("CONTENT :  "+ json.get("content").toString());
				
				
				}
		}
		catch(Exception e)
		{
			System.out.println("IN Catch");
			e.printStackTrace();
		}
	}
		public static void main(String arg[])throws IOException
		{
			MyGoogle2 mg=new MyGoogle2();
			mg.googleIt();
		}
}