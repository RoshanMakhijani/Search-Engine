import java.util.Vector;

class Profile
{
	public String name;
	private int id;
	public Vector con_name;
	public Vector con_count[];
	Profile(String name,int doc_count)
	{
		this.name=name;
		con_name=new Vector();
		con_count=new Vector[doc_count];
		for(int i=0;i<doc_count;i++)
		con_count[i]=new Vector();
	}
	public void setID(int id)
	{
		this.id=id;
	}
	public int getID()
	{
		return this.id;
	}
}