
import java.io.*;
public class Trial 
{
	@SuppressWarnings({"unchecked", "unused"})
	public void extractPOS() throws IOException
	{
		System.out.println("Enter the sentence to be tagged for Parts of Speech");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String tring = new String(br.readLine());
		Tagger ta = new Tagger(tring);
	}
	public static void main(String arg[])
	{
		Trial T=new Trial();
		try {
			T.extractPOS();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

/*
 *
OUTPUT
Enter the sentence to be tagged for Parts of Speech
How to drive a car in Mumbai
How/WRB to/TO drive/VB a/DT car/NN in/IN Mumbai/NNP
 *
 */