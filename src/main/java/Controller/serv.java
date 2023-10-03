package Controller;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



@WebServlet("/serv")
@MultipartConfig(maxFileSize = 16177215)
public class serv extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public serv() {
        super();
        // TODO Auto-generated constructor stub
    }

   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("username");
		
		//INput stream to upload file
		InputStream input = null;
		String message = null;
		
		Part filePart = request.getPart("image");
		
		if(filePart != null) {
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());
			
			input = filePart.getInputStream();
		}
		int row = ConnectionDao.uploadFile(uname, input);
		if(row > 0) {
			message = "File uploaded and saved into the database";
			response.sendRedirect("ImageServlet");
			System.out.println(message);
		} else {
			System.out.println("failed to upload an image");
		}
	}

}
