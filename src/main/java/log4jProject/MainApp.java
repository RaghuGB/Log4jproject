package log4jProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class MainApp {
	
	
	static Logger logger=LoggerFactory.getLogger(MainApp.class);
	static{
		PropertyConfigurator.configure("src/main/java/properties/log4j.properties");
	    
	
	}
 			
	public static void main(String args[]) {
  
           logger.debug("main method started");
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 logger.debug("jdbc driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mycompany", "root", "manvithgr");
			 logger.info("connection object created");
			stmt = con.createStatement();
			 logger.debug("statment object created");
			rs = stmt.executeQuery("select * from emp3");
			  logger.info("SQL query object executed");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

			 logger.debug("result processed");
			
		}catch ( ClassNotFoundException e) {
			 logger.error(e.getMessage());
		} catch (SQLException e) {
			 logger.error(e.getMessage());
		}catch (Exception e) {
			 logger.error(e.getMessage());
		} 
		finally {
			
			try {
				con.close();
				logger.info("connection closed");
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		
				try {
					stmt.close();
					logger.info("statment closed");
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			
			  try {
				rs.close();
				logger.info("result set closed");
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
		}
 
		logger.debug("main method ended");
	}
}
