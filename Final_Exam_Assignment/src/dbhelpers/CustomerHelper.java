package dbhelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;


public class CustomerHelper {
	
	private PreparedStatement  authenticateUserStatement;
	
	
	public CustomerHelper(){
		
		//set up the connection 
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/securelogin", "root","password");
			
			//create the preparedstatement 
			
			authenticateUserStatement = conn.prepareStatement("select * from Customer where username=? and password=?");
		} catch (Exception e) {
			System.out.println(e.getClass().getName() +":" + e.getMessage());
		
		}
	}
		
		
		/**
		 * Authenticates a user in the database.
		 * @param username  The username for the user.
		 * @param password  The password for the user.
		 * @return A user object if successful, null if unsuccessful.
		 */
		
		public Customer authenticateCustomer(String username,String password) {
			Customer customer = null;
			try{
			//add parameters to the ?'s in the prepared statement to execute 
			
			authenticateUserStatement.setString(1,username);
			authenticateUserStatement.setString(2,password);
			
			ResultSet rs = authenticateUserStatement.executeQuery();
			
			if(rs.next()) {
				customer= new Customer (rs.getInt("customerId"),
						rs.getString("username"),rs.getString("password"));
			}
			}
			catch(SQLException e){
				System.out.println(e.getClass().getName() + ":" + e.getMessage());
			}
			return customer;
			}
	}		
		
		
	
		
		
	
	
	


