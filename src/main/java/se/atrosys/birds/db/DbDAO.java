package se.atrosys.birds.db;

import org.springframework.stereotype.Component;
import se.atrosys.birds.model.BirdModel;

import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 5:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class DbDAO {
	public void storeModel(BirdModel birdModel) {
	}

	public BirdModel readModel(String href) {
		return null;
	}

	/**
	 * Easy way to create your database script (testdb.script in this example):
	 * java -cp ./lib/hsqldb-2.0.0.jar org.hsqldb.util.DatabaseManager -driver org.hsqldb.jdbcDriver -url jdbc:hsqldb:file:testdb -user sa
	 *
	 * Be sure to SHUTDOWN the database before quiting so that it saves your work!!
	 */
/*	public class HSQLDBTest
	{

		public static void addUser( String firstName, String lastName, String email, String password )
		{
			try
			{
				PreparedStatement ps = c.prepareStatement( "insert into customer values (null, ?, ?, ?, ?)" );
				ps.setString( 1, firstName );
				ps.setString( 2, lastName );
				ps.setString( 3, email );
				ps.setString( 4, password );
				int count = ps.executeUpdate();
				ps.close();
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}

		public static void shutdown()
		{
			try
			{
				System.out.println( "Shutting down..." );
				Statement s = c.createStatement();
				int count = s.executeUpdate( "SHUTDOWN" );
				s.close();
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}


		public static void showCustomers()
		{
			try
			{
	      		Statement s = c.createStatement();
	      		ResultSet rs = s.executeQuery( "SELECT * FROM CUSTOMER" );
	      		System.out.println( "Customer list" );
	      		while( rs.next() )
	      		{
	        			System.out.println( "Email: " + rs.getString( "CUSTOMER_EMAIL" ) );
	      		}
	      		rs.close();
	      		s.close();
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}*/

/*	  	public static void main( String[] args )
	  	{
	  		try
	    		{
				// shutdown=true causes it to save the database contents whem the last connection is closed
				// See: http://hsqldb.org/doc/2.0/guide/running-chapt.html#running_inprocess-sect
				// If you don't want to use it then you can explicitly send the database a SHUTDOWN command
	      		//c = DriverManager.getConnection( "jdbc:hsqldb:file:testdb;shutdown=true", "SA", "" );
	      		c = DriverManager.getConnection( "jdbc:hsqldb:file:birddb", "SA", "" );
	      		showCustomers();
	      		addUser( "Michael", "Haines", "michael@javasrc.com", "funkey" );
	      		showCustomers();
	      		shutdown();
	      		c.close();
	    		}
	 	   	catch( Exception e )
	    		{
	      		e.printStackTrace();
	   	 	}
	  	}
	}*/
}
