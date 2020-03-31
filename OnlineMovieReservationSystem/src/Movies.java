import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 */

/**
 * @author kristiane.paradela
 *
 */
public class Movies {

	/**
	 * 
	 */
		
	public static void main(String[] args){
		Connection conn = null;
		Statement smt = null;
		ResultSet rs = null;
		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost/moviereservations?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		    		   "root", "awsys+123");

		    smt = conn.createStatement();
		    rs = smt.executeQuery("select * from movies;");
		    
		    while(rs.next()) {
		    	System.out.println(rs.getString(2));
		    }
		    
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

}
