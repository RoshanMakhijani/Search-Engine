import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.*;

public class getOracleConnection 
{
     
	public static void insertRows(Concount CC)
	{
		Connection connection =null;
		System.out.print("in oracle");
		try
		{
			DriverManager.registerDriver(
                    new oracle.jdbc.OracleDriver());

				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracl", "scott", "tiger");
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream objOstream = new ObjectOutputStream(baos);
				objOstream.writeObject(CC);
				byte[] bArray = baos.toByteArray();

				System.out.println("*** bArray = " + bArray);

				PreparedStatement objStatement = connection.prepareStatement("insert into Concept(id,javaObject) values (?,?)");
				objStatement.setInt(1,1);
				objStatement.setBytes(2, bArray);
				objStatement.execute();

		}
		catch(SQLException sqlEx)
		{
			sqlEx.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			connection=null;
		}

	}
	public static void main(String[] args) 
        {
                DB db = new DB();
                Connection conn=db.dbConnect(
                  "jdbc:oracle:thin:@localhost:1521:oracl", "scott", "tiger");
        }

}

class DB
{
        public DB() {}

        public Connection dbConnect(String db_connect_string, 
          String db_userid, String db_password)
        {
                try
                {
                        DriverManager.registerDriver(
                          new oracle.jdbc.OracleDriver());
                          
                        Connection conn = DriverManager.getConnection(
                          db_connect_string, db_userid, db_password);
        
                        System.out.println("connected");
                        return conn;
                        
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        return null;
                }
        }
        

};