package Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.Blob;


@WebServlet("/ImageServlet")
public class getImage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	 Connection conn = null;
         response.setContentType("text/html;charset=UTF-8");

         List<User> list = new ArrayList<>();
         try {
			 conn = ConnectionDao.getConnection();
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
			 request.setAttribute("userList", list);
	         request.getRequestDispatcher("./image.jsp").forward(request, response);
		 } catch (Exception e) {
			 System.out.println("message: " +e.getMessage());
			 e.printStackTrace();
		 }
    }
}
