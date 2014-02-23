import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.*;
class ParsePage 
{
	String localurl;
	StringBuffer content; 
	StringBuffer process;
	StringBuffer desc;
	Vector steps;
	// TODO Auto-generated constructor stub
	ParsePage()
	{
		content=new StringBuffer();
		process=new StringBuffer();
		desc=new StringBuffer();
		steps=new Vector();
	}
void parseHTML(String add)
{
	
	
	try
{
	///URL url = new URL("http://www.ehow.com/");
		URL url = new URL(add);
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
	        	content.append(data);
	            //System.out.println(data);
	        }
	        public void handleStartTag(HTML.Tag tag, 
	                MutableAttributeSet attrSet, int pos) {
	        	//System.out.print("  "+tag.toString())
	        		
	        }
	        public void handleSimpleTag(HTML.Tag tag, 
	                MutableAttributeSet attrSet, int pos) {
	        	//System.out.print(" Simple "+tag.toString());
	        	
	        	
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
public void extProcess(StringBuffer con)
{
	try{
		
	int index=con.indexOf("|");
	
	process=new StringBuffer(con.subSequence(0, index));
	int index1=con.indexOf("eHow Contributor");
index1+=16;
	int index2=con.indexOf("\t", index1);
	//System.out.println("index2 "+index2);
	int index3=con.indexOf("\t", index2+1);
	//System.out.println("index3 "+index3);
	desc=new StringBuffer(con.subSequence(index2, index3));
	if(desc.indexOf("Difficulty")==0 || desc.indexOf("Difficulty")==1)
	{
		desc=new StringBuffer(con.subSequence(index1, index2));
	}
	if(desc.indexOf(" ")==0)
	{
		desc.deleteCharAt(0);
	}
	if(desc.indexOf("updated")==0)
	{
		if(desc.indexOf(",")!=-1)
		{
			desc=desc.delete(0, desc.indexOf(",")+6);
		}
	}
	}
	catch(Exception e){}
	System.out.println(content);
	System.out.println("Process Name : "+process);
	System.out.print("Description"+desc);
	
}
public void extSteps()
{try{   
	int index1=content.indexOf("Instructions");
	int indDiff=content.indexOf("Difficulty");
	int index2=index1;
	int count1=0,count2=0;
	while(steps.size()==0 || count1<100)
	{	
		count1++;
	index2=content.indexOf("1", index2);
	int chval=content.charAt(index2+1);
	//System.out.println(chval);
	int numchar=49;
	int tens=0;
	char ch=(char)numchar;
	
	if(chval>=65 && chval<90 && index2>indDiff)
	{
		count2=0;
		while(/*content.indexOf(ch+"")!=-1*/count2<100)
		{
			count2++;
			int start=content.indexOf(ch+"",index2);
			//System.out.println("Hello");
			numchar++;
			if(numchar>57)
			{
				numchar=48;
				tens++;
			}
			ch=(char)numchar;
			String Ten="";
			if(tens!=0)
			{
				Ten=(new Integer(tens).toString());
			}
			int end=content.indexOf(ch+Ten,start+1);
			
			if(end==-1)
				break;
			if(content.indexOf(".\t",start+1)<end)
			{	steps.add(content.substring(start+1,content.indexOf(".\t",start+1) ));
				break;
			}
			steps.add(content.substring(start+1, end));
		}
	}
	}
}
catch(Exception e){}
}
public static void main(String arg[]) throws IOException
{
	ParsePage lsp=new ParsePage();
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	System.out.print("Enter the url");
	String address=br.readLine();
	lsp.parseHTML(address);
	lsp.extProcess(lsp.content);
	lsp.extSteps();
	for(int i=0;i<lsp.steps.size();i++)
		System.out.println("Step no "+(i+1)+": "+lsp.steps.elementAt(i));
}
}
/*
 * How to Delete Your Browser's History | eHow.com @media screen and (max-width: 1050px) { .ChannelBanner { padding:0 5px; } .ChannelBanner .leftArrow, .ChannelBanner .rightArrow { display:none; } } 	 FamilyFoodHealthHomeMoneyStyleMore	 Home» Computers» Common Windows Tasks» Delete History from Windows» How to Delete Your Browser's HistoryTop 5 To TryHow to Delete Cookies on your Opera Web BrowserHow to Delete Browser History in RegistryHow to Delete your Browser's History on a MACHow to Delete Specific Browser HistoryHow to Delete Your Browser History on GoogleRelated TopicsDeleting Browser HistoryDeleting Ie Browser HistoryBrowser HistoryDelete History In RegistryDelete History On Iemore »How to Delete Your Browser's HistoryBy an eHow Contributor Delete Your Browser's History	Want to make sure nobody can snoop and see where you've been on the Internet? Sometimes being able to erase browser history can come in handy, especially on shared computers. By following these instructions you'll be able to do just that with any browsers you use in Windows.	From Essentials: Browser History HelpHow to Delete Your Browser's HistoryWant to make sure nobody can snoop and see where you've been on the Internet? Sometimes being… MoreMore: See All Articles in this EssentialsHow to Clear Your Internet Browser History on Internet Explorer 7Learn how to view and erase your computer's internet browser history in this free educational… MoreMore: See All Articles in this EssentialsAbout Web Browser CookiesA cookie is a cute little name, for a tiny little text file. As you search the World Wide Web,… MoreMore: See All Articles in this EssentialsHow to Delete the Web Addresses in a BrowserTyping a URL into the address bar can be bothersome when all your previous addresses drop down… MoreMore: See All Articles in this EssentialsHow to Erase Your Internet HistoryIt doesn't take too much to learn how to erase your Internet history, commonly known as clear… MoreMore: See All Articles in this EssentialsHow to Clear Internet HistoryIt is a relatively simple process to clear your Internet browsing history in Internet Explorer… MoreMore: See All Articles in this EssentialsHow to Delete History and Cookies in Windows VistaSometimes when your computer is running slow it means that you need to clean out the history,… MoreMore: See All Articles in this EssentialsDeleting Browsing History in Internet Explorer & FirefoxHow to delete browsing history in Internet Explorer and Firefox; learn more about web browsers… MoreMore: See All Articles in this EssentialsHow to Tag Internet Bookmarks Using the Firefox BrowserTagging bookmarks is one of the easiest ways to find your favorite sites. You simply use words… MoreMore: See All Articles in this EssentialsHow to Delete Windows Search History Using Registry EditorThe Microsoft Windows series of operating systems keep a history of all the phrases you search… MoreMore: See All Articles in this EssentialsHow to Delete Google Search HistoryEach time you search for a term in the Google search engine, it saves a copy of that term and… MoreMore: See All Articles in this EssentialsHow to Change the Amount of Days Internet Explorer Keeps HistoryInternet Explorer will keep a history of the websites you have visited. They are viewable by… MoreMore: See All Articles in this EssentialsHow to Search a Deleted Internet HistoryIf a user makes regular backups of the computer, the Internet history can usually be recovered.… MoreMore: See All Articles in this EssentialsHow to Change IE Default Browser SettingsTo make the most out of your browser, you can change your Internet Explorer default settings to… MoreMore: See All Articles in this EssentialsHow to Clear Search Bar MemoryMost search engines and web browsers will collect and remember your previous searches, making it… MoreMore: See All Articles in this EssentialsDifficulty: EasyInstructionsThings You'll Need:ModemsInternet AccessWeb Browsers1Click Start on the desktop.2Go to Settings.3Click Control Panel.4Double-click Internet Options.5Make sure the General tab is selected.6Click Clear History in the History box.7Click OK when it asks if you want to delete all items in your history folder.8Click OK at the bottom of the General box to exit.	Tips & WarningsOn a Macintosh, look for command names like Preferences, Internet Options, or Settings and options like Clear or Delete History.printemailfavoriteshareflag	Comments  Post a Comment | View All 38 CommentsMegan Pickett saidon 1/24/2011 finally get that stuff deleted thanks for the good answer for thisotonilsen saidon 1/24/2011 ThanksDanae Gallop saidon 1/18/2011 or you can simply press Ctrl-Shift-DeleteDiscountTickets saidon 8/13/2010 I've been wondering how to do this in a while, thanks for the wonderful explanation.writer7 saidon 7/19/2010 Nice simple instructions. Thanks!Post a CommentPost this comment to my Facebook ProfileWord Verification*Commentprintemailfavoriteshare	 Related Ads< <Related Articles & VideosHow to Delete Browser History in Registry How to Delete your Browser's History on a MAC How to Delete Specific Browser History How to Delete Your Browser History on Google How do I Delete an Online Search History? How to Delete Cookies on your Opera Web Browser How to Delete Search History How to Delete Internet History How to Permanently Clear Internet History Deleting Browsing History in Internet Explorer & Firefox More	 < < < < < < t 
Others Also ViewedHow to Delete a GMail AddressHow to Delete the History on XPHow to Delete a Brave SentryHow to Delete IE History FilesHow to Delete IE Browser HistoryHome††Article Sitemap†SitemapHow TosCopyright © 1999-2011 eHow, Inc. Use of this web site constitutes acceptance of the eHow Terms of Use † and Privacy Policy †. en-US† requires javascriptPartner SitesLivestrongeHow_eHow Technology and Electronics 	 < < D/ 	<    < 	 	
Process Name : How to Delete Your Browser's History 
Description	Want to make sure nobody can snoop and see where you've been on the Internet? Sometimes being able to erase browser history can come in handy, especially on shared computers. By following these instructions you'll be able to do just that with any browsers you use in Windows.67
Step no 1: Click Start on the desktop.
Step no 2: Go to Settings.
Step no 3: Click Control Panel.
Step no 4: Double-click Internet Options.
Step no 5: Make sure the General tab is selected.
Step no 6: Click Clear History in the History box.
Step no 7: Click OK when it asks if you want to delete all items in your history folder.
Step no 8: Click OK at the bottom of the General box to exit

 */
