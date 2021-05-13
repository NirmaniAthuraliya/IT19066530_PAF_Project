package model.PAF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	
	//Connect to the database
	private Connection connect() 
	{	
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fund_management","root", "");
			
			//for testing
			//System.out.println("Successfully connected");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//Insert funds to the database
	public String insertFunds(String FundCode, String FundName , String FundEmail , String FundAddress, String FundContact, String FundAmount) 
	{
		String output = "";
		
		try {
			Connection conn = connect();
			
			if(conn == null) {
				return "Error while connecting to the database for Inserting";
			}
			
			//create a prepared statement
			String query = "INSERT INTO fund(id, fundCode, fundName, fundEmail, fundAddress, fundContact, fundAmount)" 
			+ " values(?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2,FundCode);
			preparedStmt.setString(3,FundName);
			preparedStmt.setString(4,FundEmail);
			preparedStmt.setString(5,FundAddress);
			preparedStmt.setString(6,FundContact);
			preparedStmt.setDouble(7,Double.parseDouble(FundAmount));
			
			//execute the statement
			preparedStmt.execute();
			conn.close();
			
			String newFunds = readFunds();
			output = "{\"status\":\"success\", \"data\": \"" + newFunds + "\"}";
			
		}
		catch(Exception e) {	
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the fund.\"}";
			System.err.println(e.getMessage()); 
		}
		return output;
	}
	
	//Read funds in the database
	public String readFunds() 
	{	
		String output = "";
		
		try {
			Connection conn = connect();
			
			if(conn == null) {	
				return "Error while connecting to the database for the reading";	
			}
			
			//prepare the html table to be displayed
			output = "<table border='1'>"
					+ "<tr>"
						+ "<th> FUND CODE </th>"
						+ "<th> FUND NAME </th>"
						+ "<th> FUND EMAIL </th>"
						+ "<th> FUND ADDRESS </th>"
						+ "<th> FUND CONTACTNO </th>"
						+ "<th> FUND AMOUNT </th>"
						+ "<th> UPDATE </th>"
						+ "<th> REMOVE </th>"
					+ "</tr>";
			
			String query = "SELECT * FROM fund";
	
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
	
			//iterate through the rows in the result set
			while(rs.next()) 
			{
				String fundID = Integer.toString(rs.getInt("id"));
				String fundCode = rs.getString("fundCode");
				String fundName = rs.getString("fundName");
				String fundEmail = rs.getString("fundEmail");
				String fundAddress = rs.getString("fundAddress");
				String fundContact = rs.getString("fundContact");
				String fundAmount = Double.toString(rs.getDouble("fundAmount"));
				
				//Add a row into the html table
				output += "<tr><td><input id='hidFundIDUpdate' name='hidFundIDUpdate' type='hidden' value='" 
						+ fundID + "'>" 
						+ fundCode + "</td>";
						output += "<td>" + fundName + "</td>";
						output += "<td>" + fundEmail + "</td>";
						output += "<td>" + fundAddress + "</td>";
						output += "<td>" + fundContact + "</td>";
						output += "<td>" + fundAmount + "</td>";
				
						output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary'></td>" 
								
								+ "<td><input name='btnRemove' " + "type='button' value='Remove' "
								+ "class='btnRemove btn btn-danger' data-itemid='" + fundID + "'></td></tr>";
			}
			conn.close();
			
			//Complete the html table
			output += "</table>";
		}
		catch(Exception e) {	
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//Update Fund in the database
	public String updateFunds(String FundID, String FundCode, String FundName , String FundEmail , String FundAddress, String FundContact, String FundAmount)
	{
		 String output = "";
		 
		 try
		 {
			 Connection conn = connect();
			 
			 if (conn == null)
			 {
				 return "Error while connecting to the database for updating."; 
			 }
			 
			 // create a prepared statement
			 String query = "UPDATE fund SET fundCode = ?, fundName = ?, fundEmail = ?, fundAddress = ?, fundContact = ? , fundAmount = ? WHERE id = ?";
			 
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, FundCode);
			preparedStmt.setString(2, FundName);
			preparedStmt.setString(3, FundEmail);
			preparedStmt.setString(4, FundAddress);
			preparedStmt.setString(5, FundContact);
			preparedStmt.setDouble(6, Double.parseDouble(FundAmount));
			preparedStmt.setInt(7, Integer.parseInt(FundID));
			
			// execute the statement
			preparedStmt.execute();
			
			conn.close();

			String newFunds = readFunds();
			output = "{\"status\":\"success\", \"data\": \"" + newFunds + "\"}";
			 
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the fund.\"}";
			 System.err.println(e.getMessage());
		 }
		 return output;
	} 
		
	//Remove funds from the database
	public String deleteFunds(String fundID)
	{
		String output = "";
		
		try
		{
			 Connection conn = connect();
			 
			 if (conn == null)
			 {
				 return "Error while connecting to the database for deleting.";
			 }
			 
			 // create a prepared statement
			 String query = "DELETE FROM fund "
			 		+ "WHERE id = ?";
			 
			 PreparedStatement preparedStmt = conn.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(fundID));
		
			 // execute the statement
			 preparedStmt.execute();
			 
			 conn.close();
			 
			 String newFunds = readFunds();
			 output = "{\"status\":\"success\", \"data\": \"" + newFunds + "\"}";
			 
	    }
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the Fund.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}






