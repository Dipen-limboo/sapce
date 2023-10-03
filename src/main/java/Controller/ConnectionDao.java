package Controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.mysql.cj.jdbc.Blob;

public class ConnectionDao {
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbs?useSSL=false", "root", "0564");
		} catch (SQLException e) {
			System.out.print("Message" + e.getMessage());
		} catch(ClassNotFoundException ce) {
			System.out.print("Message" + ce.getMessage());
		}
		return conn;
		}
	
	 public static int uploadFile(String username, InputStream file) {
	    	String SQL = "insert into users (username, image) values (?, ?)";
	    	int row = 0;
	    	Connection conn = ConnectionDao.getConnection();
	    	PreparedStatement ps;
	    	try {
	    		ps = conn.prepareStatement(SQL);
	    		ps.setString(1, username);
	    		if(file != null) {
	    			ps.setBlob(2, file); //file from the parameter
	    		}
	    		row = ps.executeUpdate();
	    	} catch (SQLException e) {
	    		System.out.println("message: " + e.getMessage());
	    	}
	    	return row;
	    }
	 
	 public static List <User> get() {
		List <User> list = new ArrayList<>();
		 try {
			 Connection conn = ConnectionDao.getConnection();
			 PreparedStatement ps = conn.prepareStatement("select * from users");
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
				 User usr = new User();
				 usr.setId(rs.getInt("id"));
				
				 usr.setUsername(rs.getString("username"));
				
				 Blob blob = (Blob) rs.getBlob("image");
				 
				 InputStream is = blob.getBinaryStream();
				 ByteArrayOutputStream os = new ByteArrayOutputStream();
				 byte[] buf= new byte[4096]; 
				 int read = -1;
				 
				 while ((read = is.read(buf)) != -1) {
					 os.write(buf, 0, read);
				 }
				 byte [] imagebyte = os.toByteArray();
				 String img = Base64.getEncoder().encodeToString(imagebyte);
				 
				 is.close();
				 os.close();
				 
				 usr.setImage(img);
				 
				 list.add(usr);
			 }
		 } catch (Exception e) {
			 System.out.println("message: " +e.getMessage());
			 e.printStackTrace();
		 }
		 return list;
	 }
}